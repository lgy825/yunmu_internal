$(function () {

    loadProject();
    $("#saveBtn").click(function () {

        var customerName = $.trim($("#customerName").val());
        if (!customerName) {
            layer.msg("请输入姓名");
            return;
        } else if (!ValidUtils.validText(customerName, 1, 15)) {
            layer.msg("姓名不超过15个字，不能出现其他特殊字符");
            return;
        }

        var projectId = $("#projectSel").val();

        var customerEmail = $.trim($("#customerEmail").val());
        if (customerEmail && !ValidUtils.validEmail(customerEmail, 1, 10)) {
            layer.msg("请输入正确的邮箱格式，可为空");
            return;
        }

        var customerTel = $.trim($("#customerTel").val());
        if (customerTel && !ValidUtils.validNum(customerTel, 15)) {
            layer.msg("联系电话过长或输入格式有误");
            return;
        }

        if ($("#projectSel").val() == -1) {
            layer.msg("请选择客户所属项目");
            return;
        }

        if ($("#customerSex").val() == -1) {
            layer.msg("请选客户的性别");
            return;
        }

        var openingBank = $.trim($("#openingBank").val());
        if (!openingBank) {
            layer.msg("请输入客户银行卡开户行");
            return;
        }

        var banksName = $.trim($("#banksName").val());
        if (!banksName) {
            layer.msg("请输入客户银行卡户名");
            return;
        }

        var idCard = $.trim($("#idCard").val());
        if (!idCard) {
            layer.msg("请输入客户社会身份唯一代码");
            return;
        }

        var bankNumber = $.trim($("#bankNumber").val());
        if (!bankNumber) {
            layer.msg("请输入客户银行卡账号");
            return;
        }else if (!ValidUtils.validNum(bankNumber)) {
            layer.msg("开户行只能是数字，不能包含特殊字符");
            return;
        }


        $.ajax({
            url: ctx + "personnal/save",
            type: "POST",
            cache: false,
            dataType: 'json',
            data: {
                id: $("#customerId").val(),
                projectId: projectId,
                customerName: customerName,
                customerEmail: customerEmail,
                customerTel: customerTel,
                customerSex: $("#customerSex").val(),
                customerAddr: $("#customerAddr").val(),
                openingBank:openingBank,
                banksName:banksName,
                bankNumber:bankNumber,
                idCard:idCard,
                customerProxyName:$("#customerProxyName").val()

            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "personnel/toPersonnellist";
                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('保存失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('保存失败 !');
            },
            beforeSend: function () {
                layer.load(1, {shade: [0.3]})
            }
        });
    });

    function loadProject() {
        $.ajax({
                   url: ctx + "project/getpage",
                   type: "GET",
                   cache: false,
                   async: false,
                   dataType: 'json',
                   data: {
                       pageIndex: 1,
                       pageSize: 99999
                   },
                   success: function (data) {
                       if (data && data.resultCode === '0') {
                           // // 城市列表
                           $("#projectSel").select2({placeholder: '请选择所属项目'});
                           $("#projectSel").append("<option value='-1'>*所属项目*</option>");
                           $(data.resultData.list).each(function (idx, pro) {
                               $("#projectSel").append("<option value='" + pro.id + "'>" + pro.projectName + "</option>");
                           });

                           // 加载数据 -------------
                           if ($("#ownerId").val()) {
                               $.ajax({
                                          url: ctx + "owner/get",
                                          type: "GET",
                                          cache: false,
                                          async: false,
                                          dataType: 'json',
                                          data: {
                                              id: $("#ownerId").val(),
                                          },
                                          success: function (data) {
                                              if (data && data.resultCode === '0') {
                                                  su = data.resultData;
                                                  $("#ownerName").val(su.ownerName);
                                                  $("#ownerPwd").attr("placeholder", "如需修改，请直接输入新密码");
                                                  $("#ownerEmail").val(su.ownerEmail);
                                                  $("#ownerTel").val(su.ownerTel);
                                                  $("#ownerAddr").val(su.ownerAddr);
                                                  $("#projectSel").val(su.projectId);
                                              } else {
                                                  if (data.resultDesc) {
                                                      layer.msg(data.resultDesc);
                                                  } else {
                                                      layer.msg('查询失败 !');
                                                  }
                                              }
                                          },
                                          error: function () {
                                              layer.msg('查询失败 !');
                                          }
                                      });
                           }
                       }else {
                           if (data.resultDesc) {
                               layer.msg(data.resultDesc);
                           } else {
                               layer.msg('查询失败 !');
                           }
                       }
                   },
                   error: function () {
                       layer.msg('查询失败 !');
                   }
               });
    }


    $("#personnalName, #personnalTel").blur(function () {
        $(this).val($.trim($(this).val()));

        if (ValidUtils.validText($(this).val(), 1, 15)) {
            $(this).addClass("inputBg");
            $(this).next().addClass("none");
        } else {
            if ($(this).hasClass("inputBg")) {
                $(this).removeClass("inputBg");
            }
        }
    }).focus(function () {
        $(this).next().removeClass("none");
    });
    $("#personnalTel").blur(function () {
        $(this).val($.trim($(this).val()));

        if (ValidUtils.validNum($(this).val(), 15)) {
            $(this).addClass("inputBg");
            $(this).next().addClass("none");
        } else {
            if ($(this).hasClass("inputBg")) {
                $(this).removeClass("inputBg");
            }
        }
    }).focus(function () {
        $(this).next().removeClass("none");
    });


    $("#personnalEmail").blur(function () {
        $(this).val($.trim($(this).val()));

        if ($(this).val()) {
            if (ValidUtils.validEmail($(this).val())) {
                $(this).addClass("inputBg");
                $(this).next().addClass("none");
            } else {
                if ($(this).hasClass("inputBg")) {
                    $(this).removeClass("inputBg");
                }
            }
        }
    }).focus(function () {
        $(this).next().removeClass("none");
    });
});
