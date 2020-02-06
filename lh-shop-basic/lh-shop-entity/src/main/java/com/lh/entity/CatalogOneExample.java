package com.lh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatalogOneExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CatalogOneExample() {
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

        public Criteria andOneIdIsNull() {
            addCriterion("one_id is null");
            return (Criteria) this;
        }

        public Criteria andOneIdIsNotNull() {
            addCriterion("one_id is not null");
            return (Criteria) this;
        }

        public Criteria andOneIdEqualTo(Integer value) {
            addCriterion("one_id =", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdNotEqualTo(Integer value) {
            addCriterion("one_id <>", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdGreaterThan(Integer value) {
            addCriterion("one_id >", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_id >=", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdLessThan(Integer value) {
            addCriterion("one_id <", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdLessThanOrEqualTo(Integer value) {
            addCriterion("one_id <=", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdIn(List<Integer> values) {
            addCriterion("one_id in", values, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdNotIn(List<Integer> values) {
            addCriterion("one_id not in", values, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdBetween(Integer value1, Integer value2) {
            addCriterion("one_id between", value1, value2, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdNotBetween(Integer value1, Integer value2) {
            addCriterion("one_id not between", value1, value2, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneNameIsNull() {
            addCriterion("one_name is null");
            return (Criteria) this;
        }

        public Criteria andOneNameIsNotNull() {
            addCriterion("one_name is not null");
            return (Criteria) this;
        }

        public Criteria andOneNameEqualTo(String value) {
            addCriterion("one_name =", value, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameNotEqualTo(String value) {
            addCriterion("one_name <>", value, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameGreaterThan(String value) {
            addCriterion("one_name >", value, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameGreaterThanOrEqualTo(String value) {
            addCriterion("one_name >=", value, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameLessThan(String value) {
            addCriterion("one_name <", value, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameLessThanOrEqualTo(String value) {
            addCriterion("one_name <=", value, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameLike(String value) {
            addCriterion("one_name like", value, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameNotLike(String value) {
            addCriterion("one_name not like", value, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameIn(List<String> values) {
            addCriterion("one_name in", values, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameNotIn(List<String> values) {
            addCriterion("one_name not in", values, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameBetween(String value1, String value2) {
            addCriterion("one_name between", value1, value2, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneNameNotBetween(String value1, String value2) {
            addCriterion("one_name not between", value1, value2, "oneName");
            return (Criteria) this;
        }

        public Criteria andOneImageIsNull() {
            addCriterion("one_image is null");
            return (Criteria) this;
        }

        public Criteria andOneImageIsNotNull() {
            addCriterion("one_image is not null");
            return (Criteria) this;
        }

        public Criteria andOneImageEqualTo(String value) {
            addCriterion("one_image =", value, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageNotEqualTo(String value) {
            addCriterion("one_image <>", value, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageGreaterThan(String value) {
            addCriterion("one_image >", value, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageGreaterThanOrEqualTo(String value) {
            addCriterion("one_image >=", value, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageLessThan(String value) {
            addCriterion("one_image <", value, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageLessThanOrEqualTo(String value) {
            addCriterion("one_image <=", value, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageLike(String value) {
            addCriterion("one_image like", value, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageNotLike(String value) {
            addCriterion("one_image not like", value, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageIn(List<String> values) {
            addCriterion("one_image in", values, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageNotIn(List<String> values) {
            addCriterion("one_image not in", values, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageBetween(String value1, String value2) {
            addCriterion("one_image between", value1, value2, "oneImage");
            return (Criteria) this;
        }

        public Criteria andOneImageNotBetween(String value1, String value2) {
            addCriterion("one_image not between", value1, value2, "oneImage");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
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