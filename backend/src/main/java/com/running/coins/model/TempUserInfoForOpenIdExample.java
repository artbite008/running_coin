package com.running.coins.model;

import java.util.ArrayList;
import java.util.List;

public class TempUserInfoForOpenIdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TempUserInfoForOpenIdExample() {
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

        public Criteria andOldUserIdIsNull() {
            addCriterion("OldUserId is null");
            return (Criteria) this;
        }

        public Criteria andOldUserIdIsNotNull() {
            addCriterion("OldUserId is not null");
            return (Criteria) this;
        }

        public Criteria andOldUserIdEqualTo(Integer value) {
            addCriterion("OldUserId =", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdNotEqualTo(Integer value) {
            addCriterion("OldUserId <>", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdGreaterThan(Integer value) {
            addCriterion("OldUserId >", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("OldUserId >=", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdLessThan(Integer value) {
            addCriterion("OldUserId <", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("OldUserId <=", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdIn(List<Integer> values) {
            addCriterion("OldUserId in", values, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdNotIn(List<Integer> values) {
            addCriterion("OldUserId not in", values, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdBetween(Integer value1, Integer value2) {
            addCriterion("OldUserId between", value1, value2, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("OldUserId not between", value1, value2, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("OpenId is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("OpenId is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("OpenId =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("OpenId <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("OpenId >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("OpenId >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("OpenId <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("OpenId <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("OpenId like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("OpenId not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("OpenId in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("OpenId not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("OpenId between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("OpenId not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andSessionKeyIsNull() {
            addCriterion("SessionKey is null");
            return (Criteria) this;
        }

        public Criteria andSessionKeyIsNotNull() {
            addCriterion("SessionKey is not null");
            return (Criteria) this;
        }

        public Criteria andSessionKeyEqualTo(String value) {
            addCriterion("SessionKey =", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyNotEqualTo(String value) {
            addCriterion("SessionKey <>", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyGreaterThan(String value) {
            addCriterion("SessionKey >", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyGreaterThanOrEqualTo(String value) {
            addCriterion("SessionKey >=", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyLessThan(String value) {
            addCriterion("SessionKey <", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyLessThanOrEqualTo(String value) {
            addCriterion("SessionKey <=", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyLike(String value) {
            addCriterion("SessionKey like", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyNotLike(String value) {
            addCriterion("SessionKey not like", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyIn(List<String> values) {
            addCriterion("SessionKey in", values, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyNotIn(List<String> values) {
            addCriterion("SessionKey not in", values, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyBetween(String value1, String value2) {
            addCriterion("SessionKey between", value1, value2, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyNotBetween(String value1, String value2) {
            addCriterion("SessionKey not between", value1, value2, "sessionKey");
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