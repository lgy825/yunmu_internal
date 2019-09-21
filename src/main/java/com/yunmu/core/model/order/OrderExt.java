package com.yunmu.core.model.order;

import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.product.Product;
import com.yunmu.core.util.ParamVo;
import com.yunmu.core.util.ProductObj;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderExt implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.hourse_code
     *
     * @mbggenerated
     */
    private String hourseCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_start_date
     *
     * @mbggenerated
     */
    private Date orderStartDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_end_time
     *
     * @mbggenerated
     */
    private Date orderEndTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_act_amount
     *
     * @mbggenerated
     */
    private Long orderActAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_rec_amount
     *
     * @mbggenerated
     */
    private Long orderRecAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_source
     *
     * @mbggenerated
     */
    private String orderSource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_status
     *
     * @mbggenerated
     */
    private Integer orderStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_way
     *
     * @mbggenerated
     */
    private String orderWay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    private Long orderProAmount;
    private Integer isChooseProduct;

    private String operaterBy;

    public String getOperaterBy() {
        return operaterBy;
    }

    public void setOperaterBy(String operaterBy) {
        this.operaterBy = operaterBy;
    }

    public Long getOrderProAmount() {
        return orderProAmount;
    }

    public void setOrderProAmount(Long orderProAmount) {
        this.orderProAmount = orderProAmount;
    }

    public Integer getIsChooseProduct() {
        return isChooseProduct;
    }

    public void setIsChooseProduct(Integer isChooseProduct) {
        this.isChooseProduct = isChooseProduct;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.order_channel
     *
     * @mbggenerated
     */
    private Integer orderChannel;

    private String payWay;
    private String sourceWay;

    private Double incomeAll;

    private String hourseNumber;

    private Long payAmount;

    List<OrderProduct> orderProducts;
    List<OrderDetail> orderDetails;

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(Integer isChoose) {
        this.isChoose = isChoose;
    }

    private Integer isChoose;

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    private String  typeCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    List<ParamVo> paramVos;

    List<ProductObj> productObjs;

    public List<ProductObj> getProductObjs() {
        return productObjs;
    }

    public void setProductObjs(List<ProductObj> productObjs) {
        this.productObjs = productObjs;
    }

    List<Pay> payExts;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    List<Product> products;

    public List<Pay> getPayExts() {
        return payExts;
    }

    public void setPayExts(List<Pay> payExts) {
        this.payExts = payExts;
    }

    private String hourseCodes;

    public String getHourseCodes() {
        return hourseCodes;
    }

    public void setHourseCodes(String hourseCodes) {
        this.hourseCodes = hourseCodes;
    }

    public List<ParamVo> getParamVos() {
        return paramVos;
    }

    public void setParamVos(List<ParamVo> paramVos) {
        this.paramVos = paramVos;
    }

    public String getHourseNumber() {
        return hourseNumber;
    }

    public void setHourseNumber(String hourseNumber) {
        this.hourseNumber = hourseNumber;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getSourceWay() {
        return sourceWay;
    }

    public void setSourceWay(String sourceWay) {
        this.sourceWay = sourceWay;
    }

    public Double getIncomeAll() {
        return incomeAll;
    }

    public void setIncomeAll(Double incomeAll) {
        this.incomeAll = incomeAll;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order.project_id
     *
     * @mbggenerated
     */
    private String projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.id
     *
     * @return the value of yunmu_order.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.id
     *
     * @param id the value for yunmu_order.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.hourse_code
     *
     * @return the value of yunmu_order.hourse_code
     *
     * @mbggenerated
     */
    public String getHourseCode() {
        return hourseCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.hourse_code
     *
     * @param hourseCode the value for yunmu_order.hourse_code
     *
     * @mbggenerated
     */
    public void setHourseCode(String hourseCode) {
        this.hourseCode = hourseCode == null ? null : hourseCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.create_time
     *
     * @return the value of yunmu_order.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.create_time
     *
     * @param createTime the value for yunmu_order.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.create_by
     *
     * @return the value of yunmu_order.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.create_by
     *
     * @param createBy the value for yunmu_order.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_start_date
     *
     * @return the value of yunmu_order.order_start_date
     *
     * @mbggenerated
     */
    public Date getOrderStartDate() {
        return orderStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_start_date
     *
     * @param orderStartDate the value for yunmu_order.order_start_date
     *
     * @mbggenerated
     */
    public void setOrderStartDate(Date orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_end_time
     *
     * @return the value of yunmu_order.order_end_time
     *
     * @mbggenerated
     */
    public Date getOrderEndTime() {
        return orderEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_end_time
     *
     * @param orderEndTime the value for yunmu_order.order_end_time
     *
     * @mbggenerated
     */
    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_act_amount
     *
     * @return the value of yunmu_order.order_act_amount
     *
     * @mbggenerated
     */
    public Long getOrderActAmount() {
        return orderActAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_act_amount
     *
     * @param orderActAmount the value for yunmu_order.order_act_amount
     *
     * @mbggenerated
     */
    public void setOrderActAmount(Long orderActAmount) {
        this.orderActAmount = orderActAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_rec_amount
     *
     * @return the value of yunmu_order.order_rec_amount
     *
     * @mbggenerated
     */
    public Long getOrderRecAmount() {
        return orderRecAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_rec_amount
     *
     * @param orderRecAmount the value for yunmu_order.order_rec_amount
     *
     * @mbggenerated
     */
    public void setOrderRecAmount(Long orderRecAmount) {
        this.orderRecAmount = orderRecAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_source
     *
     * @return the value of yunmu_order.order_source
     *
     * @mbggenerated
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_source
     *
     * @param orderSource the value for yunmu_order.order_source
     *
     * @mbggenerated
     */
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource == null ? null : orderSource.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_status
     *
     * @return the value of yunmu_order.order_status
     *
     * @mbggenerated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_status
     *
     * @param orderStatus the value for yunmu_order.order_status
     *
     * @mbggenerated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.order_way
     *
     * @return the value of yunmu_order.order_way
     *
     * @mbggenerated
     */
    public String getOrderWay() {
        return orderWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.order_way
     *
     * @param orderWay the value for yunmu_order.order_way
     *
     * @mbggenerated
     */
    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay == null ? null : orderWay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.del_flag
     *
     * @return the value of yunmu_order.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.del_flag
     *
     * @param delFlag the value for yunmu_order.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order.project_id
     *
     * @return the value of yunmu_order.project_id
     *
     * @mbggenerated
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order.project_id
     *
     * @param projectId the value for yunmu_order.project_id
     *
     * @mbggenerated
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }
}