package com.grandland.qdhx.core.provider;

import org.apache.ibatis.jdbc.SQL;

import com.grandland.qdhx.core.utils.StringUtil;

/**
 * 查询岗位层级信息
 * @author loverqi
 * @date 2018年2月7日
 */
public class LevelSqlProvider {

    public String selectPostLevel(final String deptName, final String postName, final String realName) {

        StringBuffer sb = new StringBuffer();
        SQL deptSql = new SQL() {
            {
                SELECT_DISTINCT("CONCAT('dept_id_', d.id) AS id, d.dept_name AS `name`, '#' AS pid");
                FROM("gl_sys_dept AS d");
                WHERE("d.`enable` = TRUE");
                if (StringUtil.isNotNull(deptName)) {
                    WHERE("d.dept_name like CONCAT(CONCAT('%', #{deptName}),'%')");
                }
            }
        };
        sb.append(deptSql.toString());
        SQL postSql = new SQL() {
            {
                SELECT_DISTINCT("CONCAT('post_id_', p.id) AS id, p.post_name AS `name`, CONCAT('dept_id_', p.dept_id) AS pid");
                FROM("gl_sys_post AS p");
                WHERE("p.`enable` = TRUE");
                WHERE("p.dept_id is NOT NULL");
                if (StringUtil.isNotNull(postName)) {
                    WHERE("p.post_name like CONCAT(CONCAT('%', #{postName}),'%')");
                }
            }
        };

        sb.append("\r\n UNION \r\n");
        sb.append(postSql.toString());

        String userSql = new SQL() {
            {
                SELECT_DISTINCT("u.greek_id AS id, u.real_name AS `name`, CONCAT('post_id_', u.post_id) AS pid");
                FROM("gl_sys_user AS u");
                WHERE("u.`enable` = TRUE");
                WHERE("u.post_id is NOT NULL");
                if (StringUtil.isNotNull(realName)) {
                    WHERE("u.real_name like CONCAT(CONCAT('%', #{realName}),'%')");
                }
            }
        }.toString();

        sb.append("\r\n UNION \r\n");
        sb.append(userSql.toString());

        return sb.toString();
    }

    public String selectPostLevelNotUser(final String deptName, final String postName) {
        StringBuffer sb = new StringBuffer();
        SQL deptSql = new SQL() {
            {
                SELECT_DISTINCT("CONCAT('dept_id_', d.id) AS id, d.dept_name AS `name`, '#' AS pid");
                FROM("gl_sys_dept AS d");
                WHERE("d.`enable` = TRUE");
                if (StringUtil.isNotNull(deptName)) {
                    WHERE("d.dept_name like CONCAT(CONCAT('%', #{deptName}),'%')");
                }
            }
        };
        sb.append(deptSql.toString());
        SQL postSql = new SQL() {
            {
                SELECT_DISTINCT("p.id AS id, p.post_name AS `name`, CONCAT('dept_id_', p.dept_id) AS pid");
                FROM("gl_sys_post AS p");
                WHERE("p.`enable` = TRUE");
                WHERE("p.dept_id  is NOT NULL");
                if (StringUtil.isNotNull(postName)) {
                    WHERE("p.post_name like CONCAT(CONCAT('%', #{postName}),'%')");
                }
            }
        };

        sb.append("\r\n UNION \r\n");
        sb.append(postSql.toString());

        return sb.toString();
    }
}
