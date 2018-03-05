package com.grandland.qdhx.core.provider;

import org.apache.ibatis.jdbc.SQL;
import com.grandland.qdhx.core.domain.param.BsFallQueryParam;
import com.grandland.qdhx.core.utils.DateUtil;

public class BuFallQuerySqlProvider {
	
	 public String query(final BsFallQueryParam bsFallQueryParam) {
		 
		 String sql =  new SQL()  
	        {  
	            {  
	                SELECT(   "t.id, "
	                		+ "t.business_id     as businessId, "
	                		+ "t.price_id        as priceId, "
	                		+ "t.price           as curPrice,"
	                		+ "t.drop_value      as dropValue, "
	                		+ "t.percent         as percent, "
	                		+ "t.value           as curValue, "
	                		+ "t.create_time     as createTime "
	                		);
	                SELECT(  "b.unit_price       as origPrice, "
	                		+"b.business_conum   as business_conum, "
	                		+"b.total_value      as origValue, "
	                		+"b.price_unit       as priceUnit, "
	                		+"b.count            as cout, "
	                		+"b.measure_unit     as measureUnit " );
	                FROM("business_fall t");  
	                LEFT_OUTER_JOIN("business_mortgage b on b.id=t.business_id");
	                if (bsFallQueryParam.getBegainTime() !=null && !"".equals(bsFallQueryParam.getBegainTime())) {
	                	
	                	//WHERE("t.create_time >= " + DateUtil.toSQLDateDefault(bsFallQueryParam.getBegainTime().toString()));
	                	WHERE("DATE_FORMAT(t.create_time,'%Y-%m-%d') >= " + DateUtil.toSQLDateDay(bsFallQueryParam.getBegainTime().toString()));
	                }
	                
	                if (bsFallQueryParam.getEndTime() !=null && !"".equals(bsFallQueryParam.getEndTime())) {
	                	//WHERE("t.create_time <= " + DateUtil.toSQLDateDefault(bsFallQueryParam.getEndTime().toString()));
	                	WHERE("DATE_FORMAT(t.create_time,'%Y-%m-%d') <= " + DateUtil.toSQLDateDay(bsFallQueryParam.getEndTime().toString()));
	                    
	                }
	                
	            }  
	        } .toString();      
	     System.out.println(sql);
	   	 return sql;
	    } 
}
