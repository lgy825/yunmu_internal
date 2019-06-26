package com.yunmu.back.controller;

import com.google.code.kaptcha.Producer;

import com.yunmu.back.service.project.ProjectService;
import com.yunmu.back.service.sys.SysUserService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PermisionConstants;
import com.yunmu.core.constant.ResultConstants;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.sys.SysUser;
import com.yunmu.core.util.ShiroUtils;
import com.yunmu.core.util.ValidCodeUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static com.yunmu.core.constant.SessionConstants.SESSION_KEY_ALL_MY_CINEMA;
import static com.yunmu.core.constant.SessionConstants.SESSION_KEY_DEFAULT_CINEMA;

/**
 * Created by 13544 on 2019/5/19.
 */
@Controller
//@RequestMapping("/index")
public class IndexController extends BaseController {

    private static  final Logger log= LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private Producer captchaProducerMath;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ProjectService projectService;

//    @Autowired
//    private AdminService adminService;

    @RequestMapping("/tologin")
    public String toLogin(HttpSession session) {
        log.info("开始访问登录页面");
        return "login";
    }

    @RequestMapping("/workbench")
    public String toWorkBench() {
        log.info("开始访问登录页面");
        return "/index/workbench";
    }

    @RequestMapping(value = "/index/getCodeImg", method = RequestMethod.GET)
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response)
    {
        ServletOutputStream out = null;
        try
        {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String type = request.getParameter("type");
            String capStr = null;
            String code = null;
            BufferedImage bi = null;
            if ("math".equals(type))//验证码为算数 8*9 类型
            {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            }
            else if ("char".equals(type))//验证码为 abcd类型
            {
                capStr = code = captchaProducer.createText();
                bi = captchaProducer.createImage(capStr);
            }
            session.setAttribute("validateCode", code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result<String> login(String username,
                                String passwd,
                                String verifyCode,
                                String flag,
                                HttpSession session,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                Model model) {
        if (!ValidCodeUtil.validate(request, verifyCode)) {
            return createFailedResult("验证码错误");
        }
        // 获取shiro中信息
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, passwd, username);

        Boolean loginSuccess = false;
        try {
            currentUser.login(token);
            loginSuccess = currentUser.isAuthenticated();
        } catch (Exception e) {
            log.error(username + ",登陆失败", e);
            return createFailedResult(e.getMessage());
        }

        if(loginSuccess) {
            SysUser sysUser = (SysUser)currentUser.getPrincipal();
            if ("1".equals(flag)) {
                Cookie cookie = new Cookie("cookie_user", username + "-" + passwd + "-" + flag);
                cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("cookie_user", username + "-" + "" + "-" + flag);
                cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
                response.addCookie(cookie);
            }
            Cookie cookie = new Cookie("cookie_user_flychannle", null);
            response.addCookie(cookie);
            SecurityUtils.getSubject().getSession().setTimeout(3 * 60 * 60 * 1000);
            String companyCode = sysUser.getCompanyCode();
            List<Project>  cinemaListResult;
            if(sysUser.getChooseProjectId() == null) {
                return createFailedResult("用户已过期");
            }
            if(PermisionConstants.USER_CINEMA_CHOOSE_WAY_ALL == sysUser.getChooseProjectId()) {
                cinemaListResult = projectService.getProjectListByCompanyCode(companyCode);
            } else {
                cinemaListResult = sysUserService.getProjectByUserId(sysUser.getId());
            }
            if(cinemaListResult.size()==0) {
                // TODO 请求失败
                return createFailedResult("查询用户授权影院失败");
            }
            session.setAttribute(SESSION_KEY_ALL_MY_CINEMA, cinemaListResult);
        } else {
            return createFailedResult("登录失败");
        }

        return createSuccessResult("index/main.do");
    }

    @RequestMapping("/main")
    public String toindex(Model model) {
        return "main";
    }

    @RequestMapping("/isout")
    @ResponseBody
    public Result<String> isOut() {
        return createSuccessResult("ingcore");
    }


    /**
     * 查询密码
     * @param sysUser
     * @return
     */
    @RequestMapping("/selectPassWorld")
    @ResponseBody
    public Result<Boolean> selectPassWorld(SysUser sysUser) {
        sysUser.setId(ShiroUtils.getUserId());
        //Result<Boolean> selectWeek = sysUserService.selectPassWorld(sysUser);
        return null;
    }

    /**
     * 修改密码
     * @param sysUser
     * @return
     */
    @RequestMapping("/updatePassWord")
    @ResponseBody
    public Result<Boolean> updatePassWord(SysUser sysUser){
        sysUser.setId(ShiroUtils.getUserId());
//        Result<Boolean> updatePassWord =sysUserService.updatePassWord(sysUser);
////        return updatePassWord;
        return null;
    }

}
