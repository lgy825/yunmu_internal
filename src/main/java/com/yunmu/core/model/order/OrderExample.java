package com.yunmu.core.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public OrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andHourseCodeIsNull() {
            addCriterion("hourse_code is null");
            return (Criteria) this;
        }

        public Criteria andHourseCodeIsNotNull() {
            addCriterion("hourse_code is not null");
            return (Criteria) this;
        }

        public Criteria andHourseCodeEqualTo(String value) {
            addCriterion("hourse_code =", value, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeNotEqualTo(String value) {
            addCriterion("hourse_code <>", value, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeGreaterThan(String value) {
            addCriterion("hourse_code >", value, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("hourse_code >=", value, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeLessThan(String value) {
            addCriterion("hourse_code <", value, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeLessThanOrEqualTo(String value) {
            addCriterion("hourse_code <=", value, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeLike(String value) {
            addCriterion("hourse_code like", value, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeNotLike(String value) {
            addCriterion("hourse_code not like", value, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeIn(List<String> values) {
            addCriterion("hourse_code in", values, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeNotIn(List<String> values) {
            addCriterion("hourse_code not in", values, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeBetween(String value1, String value2) {
            addCriterion("hourse_code between", value1, value2, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andHourseCodeNotBetween(String value1, String value2) {
            addCriterion("hourse_code not between", value1, value2, "hourseCode");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateIsNull() {
            addCriterion("order_start_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateIsNotNull() {
            addCriterion("order_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateEqualTo(Date value) {
            addCriterion("order_start_date =", value, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateNotEqualTo(Date value) {
            addCriterion("order_start_date <>", value, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateGreaterThan(Date value) {
            addCriterion("order_start_date >", value, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("order_start_date >=", value, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateLessThan(Date value) {
            addCriterion("order_start_date <", value, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateLessThanOrEqualTo(Date value) {
            addCriterion("order_start_date <=", value, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateIn(List<Date> values) {
            addCriterion("order_start_date in", values, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateNotIn(List<Date> values) {
            addCriterion("order_start_date not in", values, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateBetween(Date value1, Date value2) {
            addCriterion("order_start_date between", value1, value2, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderStartDateNotBetween(Date value1, Date value2) {
            addCriterion("order_start_date not between", value1, value2, "orderStartDate");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeIsNull() {
            addCriterion("order_end_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeIsNotNull() {
            addCriterion("order_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeEqualTo(Date value) {
            addCriterion("order_end_time =", value, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeNotEqualTo(Date value) {
            addCriterion("order_end_time <>", value, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeGreaterThan(Date value) {
            addCriterion("order_end_time >", value, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_end_time >=", value, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeLessThan(Date value) {
            addCriterion("order_end_time <", value, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_end_time <=", value, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeIn(List<Date> values) {
            addCriterion("order_end_time in", values, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeNotIn(List<Date> values) {
            addCriterion("order_end_time not in", values, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeBetween(Date value1, Date value2) {
            addCriterion("order_end_time between", value1, value2, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_end_time not between", value1, value2, "orderEndTime");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountIsNull() {
            addCriterion("order_act_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountIsNotNull() {
            addCriterion("order_act_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountEqualTo(Long value) {
            addCriterion("order_act_amount =", value, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountNotEqualTo(Long value) {
            addCriterion("order_act_amount <>", value, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountGreaterThan(Long value) {
            addCriterion("order_act_amount >", value, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("order_act_amount >=", value, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountLessThan(Long value) {
            addCriterion("order_act_amount <", value, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountLessThanOrEqualTo(Long value) {
            addCriterion("order_act_amount <=", value, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountIn(List<Long> values) {
            addCriterion("order_act_amount in", values, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountNotIn(List<Long> values) {
            addCriterion("order_act_amount not in", values, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountBetween(Long value1, Long value2) {
            addCriterion("order_act_amount between", value1, value2, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderActAmountNotBetween(Long value1, Long value2) {
            addCriterion("order_act_amount not between", value1, value2, "orderActAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountIsNull() {
            addCriterion("order_rec_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountIsNotNull() {
            addCriterion("order_rec_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountEqualTo(Long value) {
            addCriterion("order_rec_amount =", value, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountNotEqualTo(Long value) {
            addCriterion("order_rec_amount <>", value, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountGreaterThan(Long value) {
            addCriterion("order_rec_amount >", value, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("order_rec_amount >=", value, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountLessThan(Long value) {
            addCriterion("order_rec_amount <", value, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountLessThanOrEqualTo(Long value) {
            addCriterion("order_rec_amount <=", value, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountIn(List<Long> values) {
            addCriterion("order_rec_amount in", values, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountNotIn(List<Long> values) {
            addCriterion("order_rec_amount not in", values, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountBetween(Long value1, Long value2) {
            addCriterion("order_rec_amount between", value1, value2, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRecAmountNotBetween(Long value1, Long value2) {
            addCriterion("order_rec_amount not between", value1, value2, "orderRecAmount");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNull() {
            addCriterion("order_source is null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNotNull() {
            addCriterion("order_source is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceEqualTo(String value) {
            addCriterion("order_source =", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotEqualTo(String value) {
            addCriterion("order_source <>", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThan(String value) {
            addCriterion("order_source >", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThanOrEqualTo(String value) {
            addCriterion("order_source >=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThan(String value) {
            addCriterion("order_source <", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThanOrEqualTo(String value) {
            addCriterion("order_source <=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLike(String value) {
            addCriterion("order_source like", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotLike(String value) {
            addCriterion("order_source not like", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIn(List<String> values) {
            addCriterion("order_source in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotIn(List<String> values) {
            addCriterion("order_source not in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceBetween(String value1, String value2) {
            addCriterion("order_source between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotBetween(String value1, String value2) {
            addCriterion("order_source not between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderWayIsNull() {
            addCriterion("order_way is null");
            return (Criteria) this;
        }

        public Criteria andOrderWayIsNotNull() {
            addCriterion("order_way is not null");
            return (Criteria) this;
        }

        public Criteria andOrderWayEqualTo(String value) {
            addCriterion("order_way =", value, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayNotEqualTo(String value) {
            addCriterion("order_way <>", value, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayGreaterThan(String value) {
            addCriterion("order_way >", value, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayGreaterThanOrEqualTo(String value) {
            addCriterion("order_way >=", value, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayLessThan(String value) {
            addCriterion("order_way <", value, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayLessThanOrEqualTo(String value) {
            addCriterion("order_way <=", value, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayLike(String value) {
            addCriterion("order_way like", value, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayNotLike(String value) {
            addCriterion("order_way not like", value, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayIn(List<String> values) {
            addCriterion("order_way in", values, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayNotIn(List<String> values) {
            addCriterion("order_way not in", values, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayBetween(String value1, String value2) {
            addCriterion("order_way between", value1, value2, "orderWay");
            return (Criteria) this;
        }

        public Criteria andOrderWayNotBetween(String value1, String value2) {
            addCriterion("order_way not between", value1, value2, "orderWay");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("project_id like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("project_id not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andIsChooseIsNull() {
            addCriterion("is_choose is null");
            return (Criteria) this;
        }

        public Criteria andIsChooseIsNotNull() {
            addCriterion("is_choose is not null");
            return (Criteria) this;
        }

        public Criteria andIsChooseEqualTo(Integer value) {
            addCriterion("is_choose =", value, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseNotEqualTo(Integer value) {
            addCriterion("is_choose <>", value, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseGreaterThan(Integer value) {
            addCriterion("is_choose >", value, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_choose >=", value, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseLessThan(Integer value) {
            addCriterion("is_choose <", value, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseLessThanOrEqualTo(Integer value) {
            addCriterion("is_choose <=", value, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseIn(List<Integer> values) {
            addCriterion("is_choose in", values, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseNotIn(List<Integer> values) {
            addCriterion("is_choose not in", values, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseBetween(Integer value1, Integer value2) {
            addCriterion("is_choose between", value1, value2, "isChoose");
            return (Criteria) this;
        }

        public Criteria andIsChooseNotBetween(Integer value1, Integer value2) {
            addCriterion("is_choose not between", value1, value2, "isChoose");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yunmu_order
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yunmu_order
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}