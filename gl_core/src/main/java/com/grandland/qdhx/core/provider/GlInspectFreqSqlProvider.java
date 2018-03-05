package com.grandland.qdhx.core.provider;


import com.grandland.qdhx.core.utils.TableName;
import org.apache.ibatis.jdbc.SQL;

public class GlInspectFreqSqlProvider {

    /**
     * 通过监管单位编号查找对应的巡核频率
     * @param superCode
     * @param isDeleted
     * @return
     */
    public String findBySuperCode(String superCode, final Boolean isDeleted) {
        return new SQL() {
            {
                SELECT("i.*");
                FROM(TableName.BS_INSPECT_FREQ + " i ");
                LEFT_OUTER_JOIN(TableName.BS_SUPER_UNIT + " s ON s.super_level=i.level ");
                WHERE("s.super_code=#{superCode}");
                if (isDeleted != null) {
                    WHERE("i.is_deleted=#{isDeleted}");
                }
            }
        }.toString();
    }

}
