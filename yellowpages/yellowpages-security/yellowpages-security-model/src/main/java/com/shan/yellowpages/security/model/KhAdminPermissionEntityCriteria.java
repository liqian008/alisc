//package com.shan.yellowpages.security.model;
//
//import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class KhAdminPermissionEntityCriteria extends KhCriteriaBase implements Serializable {
//    /**
//     * 查询或条件
//     */
//    protected List<Criteria> oredCriteria;
//
//    /**
//     * 显示指定serialVersionUID生成方式
//     */
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 构造查询条件,kh_admin_permission
//     */
//    public KhAdminPermissionEntityCriteria() {
//        oredCriteria = new ArrayList<>();
//    }
//
//    /**
//     * 获取当前的查询条件实例,kh_admin_permission
//     */
//    public List<Criteria> getOredCriteria() {
//        return oredCriteria;
//    }
//
//    /**
//     * ,kh_admin_permission
//     *
//     * @param criteria 过滤条件实例
//     */
//    public void or(Criteria criteria) {
//        oredCriteria.add(criteria);
//    }
//
//    /**
//     * ,kh_admin_permission
//     */
//    public Criteria or() {
//        Criteria criteria = createCriteriaInternal();
//        oredCriteria.add(criteria);
//        return criteria;
//    }
//
//    /**
//     * 创建一个查询条件,kh_admin_permission
//     */
//    public Criteria createCriteria() {
//        Criteria criteria = createCriteriaInternal();
//        if (oredCriteria.size() == 0) {
//            oredCriteria.add(criteria);
//        }
//        return criteria;
//    }
//
//    /**
//     * 内部构建查询条件对象,kh_admin_permission
//     */
//    protected Criteria createCriteriaInternal() {
//        Criteria criteria = new Criteria();
//        return criteria;
//    }
//
//    /**
//     * 清除查询条件,kh_admin_permission
//     */
//    public void clear() {
//        oredCriteria.clear();
//        orderByClause = null;
//        distinct = false;
//    }
//
//    /**
//     * 管理后台权限表
//     * GeneratedCriteria
//     * 数据库表：kh_admin_permission
//     */
//    protected abstract static class GeneratedCriteria implements Serializable {
//        protected List<Criterion> criteria;
//
//        /**
//         * 显示指定serialVersionUID生成方式
//         */
//        private static final long serialVersionUID = 1L;
//
//        protected GeneratedCriteria() {
//            super();
//            criteria = new ArrayList<>();
//        }
//
//        public boolean isValid() {
//            return criteria.size() > 0;
//        }
//
//        public List<Criterion> getAllCriteria() {
//            return criteria;
//        }
//
//        public List<Criterion> getCriteria() {
//            return criteria;
//        }
//
//        protected void addCriterion(String condition) {
//            if (condition == null) {
//                throw new RuntimeException("Value for condition cannot be null");
//            }
//            criteria.add(new Criterion(condition));
//        }
//
//        protected void addCriterion(String condition, Object value, String property) {
//            if (value == null) {
//                throw new RuntimeException("Value for " + property + " cannot be null");
//            }
//            criteria.add(new Criterion(condition, value));
//        }
//
//        protected void addCriterion(String condition, Object value1, Object value2, String property) {
//            if (value1 == null || value2 == null) {
//                throw new RuntimeException("Between values for " + property + " cannot be null");
//            }
//            criteria.add(new Criterion(condition, value1, value2));
//        }
//
//        public Criteria andIdIsNull() {
//            addCriterion("id is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdIsNotNull() {
//            addCriterion("id is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdEqualTo(Integer value) {
//            addCriterion("id =", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotEqualTo(Integer value) {
//            addCriterion("id <>", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdGreaterThan(Integer value) {
//            addCriterion("id >", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
//            addCriterion("id >=", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdLessThan(Integer value) {
//            addCriterion("id <", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdLessThanOrEqualTo(Integer value) {
//            addCriterion("id <=", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdIn(List<Integer> values) {
//            addCriterion("id in", values, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotIn(List<Integer> values) {
//            addCriterion("id not in", values, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdBetween(Integer value1, Integer value2) {
//            addCriterion("id between", value1, value2, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotBetween(Integer value1, Integer value2) {
//            addCriterion("id not between", value1, value2, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdIsNull() {
//            addCriterion("category_id is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdIsNotNull() {
//            addCriterion("category_id is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdEqualTo(Integer value) {
//            addCriterion("category_id =", value, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdNotEqualTo(Integer value) {
//            addCriterion("category_id <>", value, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdGreaterThan(Integer value) {
//            addCriterion("category_id >", value, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
//            addCriterion("category_id >=", value, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdLessThan(Integer value) {
//            addCriterion("category_id <", value, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
//            addCriterion("category_id <=", value, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdIn(List<Integer> values) {
//            addCriterion("category_id in", values, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdNotIn(List<Integer> values) {
//            addCriterion("category_id not in", values, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
//            addCriterion("category_id between", value1, value2, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
//            addCriterion("category_id not between", value1, value2, "categoryId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameIsNull() {
//            addCriterion("permission_name is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameIsNotNull() {
//            addCriterion("permission_name is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameEqualTo(String value) {
//            addCriterion("permission_name =", value, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameNotEqualTo(String value) {
//            addCriterion("permission_name <>", value, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameGreaterThan(String value) {
//            addCriterion("permission_name >", value, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameGreaterThanOrEqualTo(String value) {
//            addCriterion("permission_name >=", value, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameLessThan(String value) {
//            addCriterion("permission_name <", value, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameLessThanOrEqualTo(String value) {
//            addCriterion("permission_name <=", value, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameLike(String value) {
//            addCriterion("permission_name like", value, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameNotLike(String value) {
//            addCriterion("permission_name not like", value, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameIn(List<String> values) {
//            addCriterion("permission_name in", values, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameNotIn(List<String> values) {
//            addCriterion("permission_name not in", values, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameBetween(String value1, String value2) {
//            addCriterion("permission_name between", value1, value2, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionNameNotBetween(String value1, String value2) {
//            addCriterion("permission_name not between", value1, value2, "permissionName");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeIsNull() {
//            addCriterion("type is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeIsNotNull() {
//            addCriterion("type is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeEqualTo(Short value) {
//            addCriterion("type =", value, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeNotEqualTo(Short value) {
//            addCriterion("type <>", value, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeGreaterThan(Short value) {
//            addCriterion("type >", value, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
//            addCriterion("type >=", value, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeLessThan(Short value) {
//            addCriterion("type <", value, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeLessThanOrEqualTo(Short value) {
//            addCriterion("type <=", value, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeIn(List<Short> values) {
//            addCriterion("type in", values, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeNotIn(List<Short> values) {
//            addCriterion("type not in", values, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeBetween(Short value1, Short value2) {
//            addCriterion("type between", value1, value2, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andTypeNotBetween(Short value1, Short value2) {
//            addCriterion("type not between", value1, value2, "type");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeIsNull() {
//            addCriterion("access_type is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeIsNotNull() {
//            addCriterion("access_type is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeEqualTo(Short value) {
//            addCriterion("access_type =", value, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeNotEqualTo(Short value) {
//            addCriterion("access_type <>", value, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeGreaterThan(Short value) {
//            addCriterion("access_type >", value, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeGreaterThanOrEqualTo(Short value) {
//            addCriterion("access_type >=", value, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeLessThan(Short value) {
//            addCriterion("access_type <", value, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeLessThanOrEqualTo(Short value) {
//            addCriterion("access_type <=", value, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeIn(List<Short> values) {
//            addCriterion("access_type in", values, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeNotIn(List<Short> values) {
//            addCriterion("access_type not in", values, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeBetween(Short value1, Short value2) {
//            addCriterion("access_type between", value1, value2, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andAccessTypeNotBetween(Short value1, Short value2) {
//            addCriterion("access_type not between", value1, value2, "accessType");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeIsNull() {
//            addCriterion("permission_code is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeIsNotNull() {
//            addCriterion("permission_code is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeEqualTo(String value) {
//            addCriterion("permission_code =", value, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeNotEqualTo(String value) {
//            addCriterion("permission_code <>", value, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeGreaterThan(String value) {
//            addCriterion("permission_code >", value, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeGreaterThanOrEqualTo(String value) {
//            addCriterion("permission_code >=", value, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeLessThan(String value) {
//            addCriterion("permission_code <", value, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeLessThanOrEqualTo(String value) {
//            addCriterion("permission_code <=", value, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeLike(String value) {
//            addCriterion("permission_code like", value, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeNotLike(String value) {
//            addCriterion("permission_code not like", value, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeIn(List<String> values) {
//            addCriterion("permission_code in", values, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeNotIn(List<String> values) {
//            addCriterion("permission_code not in", values, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeBetween(String value1, String value2) {
//            addCriterion("permission_code between", value1, value2, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andPermissionCodeNotBetween(String value1, String value2) {
//            addCriterion("permission_code not between", value1, value2, "permissionCode");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlIsNull() {
//            addCriterion("match_url is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlIsNotNull() {
//            addCriterion("match_url is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlEqualTo(String value) {
//            addCriterion("match_url =", value, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlNotEqualTo(String value) {
//            addCriterion("match_url <>", value, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlGreaterThan(String value) {
//            addCriterion("match_url >", value, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlGreaterThanOrEqualTo(String value) {
//            addCriterion("match_url >=", value, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLessThan(String value) {
//            addCriterion("match_url <", value, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLessThanOrEqualTo(String value) {
//            addCriterion("match_url <=", value, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLike(String value) {
//            addCriterion("match_url like", value, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlNotLike(String value) {
//            addCriterion("match_url not like", value, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlIn(List<String> values) {
//            addCriterion("match_url in", values, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlNotIn(List<String> values) {
//            addCriterion("match_url not in", values, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlBetween(String value1, String value2) {
//            addCriterion("match_url between", value1, value2, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlNotBetween(String value1, String value2) {
//            addCriterion("match_url not between", value1, value2, "matchUrl");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelIsNull() {
//            addCriterion("match_url_level is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelIsNotNull() {
//            addCriterion("match_url_level is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelEqualTo(Short value) {
//            addCriterion("match_url_level =", value, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelNotEqualTo(Short value) {
//            addCriterion("match_url_level <>", value, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelGreaterThan(Short value) {
//            addCriterion("match_url_level >", value, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelGreaterThanOrEqualTo(Short value) {
//            addCriterion("match_url_level >=", value, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelLessThan(Short value) {
//            addCriterion("match_url_level <", value, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelLessThanOrEqualTo(Short value) {
//            addCriterion("match_url_level <=", value, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelIn(List<Short> values) {
//            addCriterion("match_url_level in", values, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelNotIn(List<Short> values) {
//            addCriterion("match_url_level not in", values, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelBetween(Short value1, Short value2) {
//            addCriterion("match_url_level between", value1, value2, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andMatchUrlLevelNotBetween(Short value1, Short value2) {
//            addCriterion("match_url_level not between", value1, value2, "matchUrlLevel");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkIsNull() {
//            addCriterion("remark is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkIsNotNull() {
//            addCriterion("remark is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkEqualTo(String value) {
//            addCriterion("remark =", value, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkNotEqualTo(String value) {
//            addCriterion("remark <>", value, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkGreaterThan(String value) {
//            addCriterion("remark >", value, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
//            addCriterion("remark >=", value, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkLessThan(String value) {
//            addCriterion("remark <", value, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkLessThanOrEqualTo(String value) {
//            addCriterion("remark <=", value, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkLike(String value) {
//            addCriterion("remark like", value, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkNotLike(String value) {
//            addCriterion("remark not like", value, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkIn(List<String> values) {
//            addCriterion("remark in", values, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkNotIn(List<String> values) {
//            addCriterion("remark not in", values, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkBetween(String value1, String value2) {
//            addCriterion("remark between", value1, value2, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andRemarkNotBetween(String value1, String value2) {
//            addCriterion("remark not between", value1, value2, "remark");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortIsNull() {
//            addCriterion("sort is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortIsNotNull() {
//            addCriterion("sort is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortEqualTo(Integer value) {
//            addCriterion("sort =", value, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortNotEqualTo(Integer value) {
//            addCriterion("sort <>", value, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortGreaterThan(Integer value) {
//            addCriterion("sort >", value, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
//            addCriterion("sort >=", value, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortLessThan(Integer value) {
//            addCriterion("sort <", value, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortLessThanOrEqualTo(Integer value) {
//            addCriterion("sort <=", value, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortIn(List<Integer> values) {
//            addCriterion("sort in", values, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortNotIn(List<Integer> values) {
//            addCriterion("sort not in", values, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortBetween(Integer value1, Integer value2) {
//            addCriterion("sort between", value1, value2, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andSortNotBetween(Integer value1, Integer value2) {
//            addCriterion("sort not between", value1, value2, "sort");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusIsNull() {
//            addCriterion("status is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusIsNotNull() {
//            addCriterion("status is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusEqualTo(Short value) {
//            addCriterion("status =", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusNotEqualTo(Short value) {
//            addCriterion("status <>", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusGreaterThan(Short value) {
//            addCriterion("status >", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
//            addCriterion("status >=", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusLessThan(Short value) {
//            addCriterion("status <", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusLessThanOrEqualTo(Short value) {
//            addCriterion("status <=", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusIn(List<Short> values) {
//            addCriterion("status in", values, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusNotIn(List<Short> values) {
//            addCriterion("status not in", values, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusBetween(Short value1, Short value2) {
//            addCriterion("status between", value1, value2, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusNotBetween(Short value1, Short value2) {
//            addCriterion("status not between", value1, value2, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeIsNull() {
//            addCriterion("create_time is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeIsNotNull() {
//            addCriterion("create_time is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeEqualTo(Date value) {
//            addCriterion("create_time =", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeNotEqualTo(Date value) {
//            addCriterion("create_time <>", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeGreaterThan(Date value) {
//            addCriterion("create_time >", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
//            addCriterion("create_time >=", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeLessThan(Date value) {
//            addCriterion("create_time <", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
//            addCriterion("create_time <=", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeIn(List<Date> values) {
//            addCriterion("create_time in", values, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeNotIn(List<Date> values) {
//            addCriterion("create_time not in", values, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeBetween(Date value1, Date value2) {
//            addCriterion("create_time between", value1, value2, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
//            addCriterion("create_time not between", value1, value2, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeIsNull() {
//            addCriterion("update_time is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeIsNotNull() {
//            addCriterion("update_time is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeEqualTo(Date value) {
//            addCriterion("update_time =", value, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeNotEqualTo(Date value) {
//            addCriterion("update_time <>", value, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeGreaterThan(Date value) {
//            addCriterion("update_time >", value, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
//            addCriterion("update_time >=", value, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeLessThan(Date value) {
//            addCriterion("update_time <", value, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
//            addCriterion("update_time <=", value, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeIn(List<Date> values) {
//            addCriterion("update_time in", values, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeNotIn(List<Date> values) {
//            addCriterion("update_time not in", values, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
//            addCriterion("update_time between", value1, value2, "updateTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
//            addCriterion("update_time not between", value1, value2, "updateTime");
//            return (Criteria) this;
//        }
//    }
//
//    /**
//     * 类注释
//     * Criteria
//     * 数据库表：kh_admin_permission
//     */
//    public static class Criteria extends GeneratedCriteria implements Serializable {
//        /**
//         * 显示指定serialVersionUID生成方式
//         */
//        private static final long serialVersionUID = 1L;
//
//        protected Criteria() {
//            super();
//        }
//    }
//
//    /**
//     * 管理后台权限表
//     * Criterion
//     * 数据库表：kh_admin_permission
//     */
//    public static class Criterion implements Serializable {
//        private String condition;
//
//        private Object value;
//
//        private Object secondValue;
//
//        private boolean noValue;
//
//        private boolean singleValue;
//
//        private boolean betweenValue;
//
//        private boolean listValue;
//
//        private String typeHandler;
//
//        /**
//         * 显示指定serialVersionUID生成方式
//         */
//        private static final long serialVersionUID = 1L;
//
//        public String getCondition() {
//            return condition;
//        }
//
//        public Object getValue() {
//            return value;
//        }
//
//        public Object getSecondValue() {
//            return secondValue;
//        }
//
//        public boolean isNoValue() {
//            return noValue;
//        }
//
//        public boolean isSingleValue() {
//            return singleValue;
//        }
//
//        public boolean isBetweenValue() {
//            return betweenValue;
//        }
//
//        public boolean isListValue() {
//            return listValue;
//        }
//
//        public String getTypeHandler() {
//            return typeHandler;
//        }
//
//        protected Criterion(String condition) {
//            super();
//            this.condition = condition;
//            this.typeHandler = null;
//            this.noValue = true;
//        }
//
//        protected Criterion(String condition, Object value, String typeHandler) {
//            super();
//            this.condition = condition;
//            this.value = value;
//            this.typeHandler = typeHandler;
//            if (value instanceof List<?>) {
//                this.listValue = true;
//            } else {
//                this.singleValue = true;
//            }
//        }
//
//        protected Criterion(String condition, Object value) {
//            this(condition, value, null);
//        }
//
//        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
//            super();
//            this.condition = condition;
//            this.value = value;
//            this.secondValue = secondValue;
//            this.typeHandler = typeHandler;
//            this.betweenValue = true;
//        }
//
//        protected Criterion(String condition, Object value, Object secondValue) {
//            this(condition, value, secondValue, null);
//        }
//    }
//}