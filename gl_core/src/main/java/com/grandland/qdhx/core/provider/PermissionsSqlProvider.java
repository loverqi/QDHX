package com.grandland.qdhx.core.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * 查询授权信息
 * @author loverqi
 * @date 2018年2月7日
 */
public class PermissionsSqlProvider {

    public String selectGlSysFuncsWithPrivs(final Integer postId) {

        String sql = new SQL() {
            {
                SELECT_DISTINCT("f.*, ifnull(p.`enable`, 0) AS `is_selected`");
                FROM("gl_sys_func AS f");
                if (postId != null) {
                    LEFT_OUTER_JOIN("gl_sys_priv AS p ON (p.func_id = f.id AND p.post_id = 10000)");
                } else {
                    LEFT_OUTER_JOIN("gl_sys_priv AS p ON (p.func_id = f.id AND p.post_id = 0)");
                }
                WHERE("f.`enable` = TRUE");
            }
        }.toString();

        return sql;
    }
}
