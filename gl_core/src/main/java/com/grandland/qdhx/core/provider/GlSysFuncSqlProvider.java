package com.grandland.qdhx.core.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 查询岗位层级信息
 * @author loverqi
 * @date 2018年2月7日
 */
public class GlSysFuncSqlProvider {
    public String selectWithPriv(final List<String> privs) {

        final boolean ifHasRule = privs != null && !privs.isEmpty();

        String sql = new SQL() {
            {
                SELECT("f.id,f.path, f.parent, f.text, f.func_name, f.icon, f.`enable`, f.is_leaf, f.is_juris");
                FROM("gl_sys_func AS f");
                WHERE("f.`enable` = TRUE");
                WHERE("f.is_leaf = FALSE");
                if (ifHasRule) {
                    final StringBuffer sb = new StringBuffer();
                    for (String priv : privs) {
                        if (priv.startsWith("ROLE_")) {
                            priv = priv.substring("ROLE_".length(), priv.length());
                        }
                        sb.append("'");
                        sb.append(priv);
                        sb.append("'");
                        sb.append(",");
                    }

                    //删除最后的一个逗号
                    int i = sb.lastIndexOf(",");
                    if (i > -1) {
                        sb.delete(i, i + 1);
                    }

                    String sqlList = new SQL() {
                        {
                            SELECT_DISTINCT("ft.parent");
                            FROM("gl_sys_func AS ft");
                            WHERE("ft.`enable` = TRUE");
                            WHERE("ft.is_leaf = TRUE");
                            WHERE("ft.func_name IN (" + sb.toString() + ")");
                        }
                    }.toString();

                    WHERE("(f.is_juris = FALSE OR f.id IN (" + sqlList + "))");
                } else {
                    WHERE("f.is_juris = FALSE");
                }

            }
        }.toString();

        return sql;
    }
}
