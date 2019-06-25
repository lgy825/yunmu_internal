package com.yunmu.core.model.order;

import java.io.Serializable;
import java.util.Date;

public class OrderDetail implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order_detail.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order_detail.order_code
     *
     * @mbggenerated
     */
    private String orderCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order_detail.pay_code
     *
     * @mbggenerated
     */
    private String payCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order_detail.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order_detail.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_order_detail.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_order_detail
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order_detail.id
     *
     * @return the value of yunmu_order_detail.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order_detail.id
     *
     * @param id the value for yunmu_order_detail.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order_detail.order_code
     *
     * @return the value of yunmu_order_detail.order_code
     *
     * @mbggenerated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order_detail.order_code
     *
     * @param orderCode the value for yunmu_order_detail.order_code
     *
     * @mbggenerated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order_detail.pay_code
     *
     * @return the value of yunmu_order_detail.pay_code
     *
     * @mbggenerated
     */
    public String getPayCode() {
        return payCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order_detail.pay_code
     *
     * @param payCode the value for yunmu_order_detail.pay_code
     *
     * @mbggenerated
     */
    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order_detail.create_time
     *
     * @return the value of yunmu_order_detail.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order_detail.create_time
     *
     * @param createTime the value for yunmu_order_detail.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order_detail.create_by
     *
     * @return the value of yunmu_order_detail.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order_detail.create_by
     *
     * @param createBy the value for yunmu_order_detail.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_order_detail.del_flag
     *
     * @return the value of yunmu_order_detail.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_order_detail.del_flag
     *
     * @param delFlag the value for yunmu_order_detail.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}