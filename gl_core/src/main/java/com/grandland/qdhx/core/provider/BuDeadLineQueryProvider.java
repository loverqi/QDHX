package com.grandland.qdhx.core.provider;

import com.grandland.qdhx.core.domain.BudeadLine;
import com.grandland.qdhx.core.domain.param.BuDeadLineQuParam;
import com.grandland.qdhx.core.utils.DateUtil;
import com.grandland.qdhx.core.utils.TableName;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class BuDeadLineQueryProvider {
	
 public String query(final BuDeadLineQuParam buDeadLineQuParam) {
		 
		 String sql =  new SQL()  
	        {  
	            {  
	                SELECT(   "t.id, "
	                		+ "t.business_id     as businessId, "
	                		+ "t.is_shuhuo       as isShuhuo,   "
	                		+ "t.dead_line       as deadLine,   "
	                		+ "t.shuhuo_value    as shuhuoValue,   "
	                		+ "t.shuhuo_Count     as shuhuoCount,   "
	                		+ "t.shuhuo_times    as shuhuoTimes,   "
	                		+ "date_format(t.create_time,'%Y-%m-%d %H:%i:%s') as createTime  "
	                		);
	                SELECT(  "b.business_conum   as businessConum,"
	                		+"b.borrower_name    as borrowerName  "
	                		 );
	                FROM("budead_line t");  
	                LEFT_OUTER_JOIN("business_mortgage b on b.id=t.business_id");
	                if (buDeadLineQuParam.getBegainTime() !=null ) {
	                	
	                	WHERE("DATE_FORMAT(t.create_time,'%Y-%m-%d') >= " + DateUtil.toSQLDateDay(buDeadLineQuParam.getBegainTime().toString()));
	                }
	                
	                if (buDeadLineQuParam.getEndTime() !=null ) {
	                	WHERE("DATE_FORMAT(t.create_time,'%Y-%m-%d') <= " + DateUtil.toSQLDateDay(buDeadLineQuParam.getEndTime().toString()));
	                    
	                }
	                
	            }  
	        } .toString();      
	     System.out.println(sql);
	   	 return sql;
	    }


	public String insert(final BudeadLine budeadLine) {
		return new SQL() {
			{
				INSERT_INTO(TableName.BS_DEAD_LINE);
				if (!ObjectUtils.isEmpty(budeadLine.getBusinessId())) {
					VALUES("business_id", "#{budeadLine.businessId}");
				}
				if (!StringUtils.isEmpty(budeadLine.getRizhaoId())) {
					VALUES("rizhao_id", "#{budeadLine.rizhaoId}");
				}
				if (!ObjectUtils.isEmpty(budeadLine.getIsShuhuo())) {
					VALUES("is_shuhuo", "#{budeadLine.isShuhuo}");
				}
				if (!ObjectUtils.isEmpty(budeadLine.getDeadLine())) {
					VALUES("dead_line", "#{budeadLine.deadLine}");
				}
				if (!ObjectUtils.isEmpty(budeadLine.getShuhuoValue())) {
					VALUES("shuhuo_value", "#{budeadLine.shuhuoValue}");
				}
				if (!ObjectUtils.isEmpty(budeadLine.getShuhuoCount())) {
					VALUES("shuhuo_count", "#{budeadLine.shuhuoCount}");
				}
				if (!ObjectUtils.isEmpty(budeadLine.getNotRedeemValue())) {
					VALUES("not_redeem_value", "#{budeadLine.notRedeemValue}");
				}
				if (!ObjectUtils.isEmpty(budeadLine.getNotRedeemCount())) {
					VALUES("not_redeem_count", "#{budeadLine.notRedeemCount}");
				}
				if (!ObjectUtils.isEmpty(budeadLine.getShuhuoTimes())) {
					VALUES("shuhuo_times", "#{budeadLine.shuhuoTimes}");
				}
				if (!ObjectUtils.isEmpty(budeadLine.getCreateTime())) {
					VALUES("create_time", "#{budeadLine.createTime}");
				}
			}
		}.toString();
	}
}
