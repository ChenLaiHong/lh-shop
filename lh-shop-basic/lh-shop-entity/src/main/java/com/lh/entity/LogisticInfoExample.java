package com.lh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogisticInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogisticInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNull() {
            addCriterion("order_number is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNotNull() {
            addCriterion("order_number is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberEqualTo(String value) {
            addCriterion("order_number =", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotEqualTo(String value) {
            addCriterion("order_number <>", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThan(String value) {
            addCriterion("order_number >", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThanOrEqualTo(String value) {
            addCriterion("order_number >=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThan(String value) {
            addCriterion("order_number <", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThanOrEqualTo(String value) {
            addCriterion("order_number <=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLike(String value) {
            addCriterion("order_number like", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotLike(String value) {
            addCriterion("order_number not like", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIn(List<String> values) {
            addCriterion("order_number in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotIn(List<String> values) {
            addCriterion("order_number not in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberBetween(String value1, String value2) {
            addCriterion("order_number between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotBetween(String value1, String value2) {
            addCriterion("order_number not between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberIsNull() {
            addCriterion("courier_number is null");
            return (Criteria) this;
        }

        public Criteria andCourierNumberIsNotNull() {
            addCriterion("courier_number is not null");
            return (Criteria) this;
        }

        public Criteria andCourierNumberEqualTo(Integer value) {
            addCriterion("courier_number =", value, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberNotEqualTo(Integer value) {
            addCriterion("courier_number <>", value, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberGreaterThan(Integer value) {
            addCriterion("courier_number >", value, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("courier_number >=", value, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberLessThan(Integer value) {
            addCriterion("courier_number <", value, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberLessThanOrEqualTo(Integer value) {
            addCriterion("courier_number <=", value, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberIn(List<Integer> values) {
            addCriterion("courier_number in", values, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberNotIn(List<Integer> values) {
            addCriterion("courier_number not in", values, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberBetween(Integer value1, Integer value2) {
            addCriterion("courier_number between", value1, value2, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andCourierNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("courier_number not between", value1, value2, "courierNumber");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeIsNull() {
            addCriterion("logistic_time is null");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeIsNotNull() {
            addCriterion("logistic_time is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeEqualTo(Date value) {
            addCriterion("logistic_time =", value, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeNotEqualTo(Date value) {
            addCriterion("logistic_time <>", value, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeGreaterThan(Date value) {
            addCriterion("logistic_time >", value, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("logistic_time >=", value, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeLessThan(Date value) {
            addCriterion("logistic_time <", value, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeLessThanOrEqualTo(Date value) {
            addCriterion("logistic_time <=", value, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeIn(List<Date> values) {
            addCriterion("logistic_time in", values, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeNotIn(List<Date> values) {
            addCriterion("logistic_time not in", values, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeBetween(Date value1, Date value2) {
            addCriterion("logistic_time between", value1, value2, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticTimeNotBetween(Date value1, Date value2) {
            addCriterion("logistic_time not between", value1, value2, "logisticTime");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceIsNull() {
            addCriterion("logistic_place is null");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceIsNotNull() {
            addCriterion("logistic_place is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceEqualTo(String value) {
            addCriterion("logistic_place =", value, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceNotEqualTo(String value) {
            addCriterion("logistic_place <>", value, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceGreaterThan(String value) {
            addCriterion("logistic_place >", value, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("logistic_place >=", value, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceLessThan(String value) {
            addCriterion("logistic_place <", value, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceLessThanOrEqualTo(String value) {
            addCriterion("logistic_place <=", value, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceLike(String value) {
            addCriterion("logistic_place like", value, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceNotLike(String value) {
            addCriterion("logistic_place not like", value, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceIn(List<String> values) {
            addCriterion("logistic_place in", values, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceNotIn(List<String> values) {
            addCriterion("logistic_place not in", values, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceBetween(String value1, String value2) {
            addCriterion("logistic_place between", value1, value2, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticPlaceNotBetween(String value1, String value2) {
            addCriterion("logistic_place not between", value1, value2, "logisticPlace");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainIsNull() {
            addCriterion("logistic_explain is null");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainIsNotNull() {
            addCriterion("logistic_explain is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainEqualTo(String value) {
            addCriterion("logistic_explain =", value, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainNotEqualTo(String value) {
            addCriterion("logistic_explain <>", value, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainGreaterThan(String value) {
            addCriterion("logistic_explain >", value, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainGreaterThanOrEqualTo(String value) {
            addCriterion("logistic_explain >=", value, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainLessThan(String value) {
            addCriterion("logistic_explain <", value, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainLessThanOrEqualTo(String value) {
            addCriterion("logistic_explain <=", value, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainLike(String value) {
            addCriterion("logistic_explain like", value, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainNotLike(String value) {
            addCriterion("logistic_explain not like", value, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainIn(List<String> values) {
            addCriterion("logistic_explain in", values, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainNotIn(List<String> values) {
            addCriterion("logistic_explain not in", values, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainBetween(String value1, String value2) {
            addCriterion("logistic_explain between", value1, value2, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticExplainNotBetween(String value1, String value2) {
            addCriterion("logistic_explain not between", value1, value2, "logisticExplain");
            return (Criteria) this;
        }

        public Criteria andLogisticStateIsNull() {
            addCriterion("logistic_state is null");
            return (Criteria) this;
        }

        public Criteria andLogisticStateIsNotNull() {
            addCriterion("logistic_state is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticStateEqualTo(String value) {
            addCriterion("logistic_state =", value, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateNotEqualTo(String value) {
            addCriterion("logistic_state <>", value, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateGreaterThan(String value) {
            addCriterion("logistic_state >", value, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateGreaterThanOrEqualTo(String value) {
            addCriterion("logistic_state >=", value, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateLessThan(String value) {
            addCriterion("logistic_state <", value, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateLessThanOrEqualTo(String value) {
            addCriterion("logistic_state <=", value, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateLike(String value) {
            addCriterion("logistic_state like", value, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateNotLike(String value) {
            addCriterion("logistic_state not like", value, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateIn(List<String> values) {
            addCriterion("logistic_state in", values, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateNotIn(List<String> values) {
            addCriterion("logistic_state not in", values, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateBetween(String value1, String value2) {
            addCriterion("logistic_state between", value1, value2, "logisticState");
            return (Criteria) this;
        }

        public Criteria andLogisticStateNotBetween(String value1, String value2) {
            addCriterion("logistic_state not between", value1, value2, "logisticState");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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