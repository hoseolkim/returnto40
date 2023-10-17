package kr.or.ddit.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import kr.or.ddit.vo.AddressVO;

public class SampleDataMapperUtils {
	public static String snakeToCamel(String columnName) {
		String propName = WordUtils.capitalizeFully(columnName,'_');
//		System.out.println(propName);
		propName = StringUtils.replace(propName, "_", "");
//		System.out.println(propName);
		propName = WordUtils.uncapitalize(propName);
//		System.out.println(propName);
		return propName;
	}
	
	public static <T> T recordToVO(ResultSet rs, Class<T> resultType) throws SQLException {
//		AddressVO vo = new AddressVO();
		try {
			Object vo = resultType.newInstance();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCnt = rsmd.getColumnCount();
			
			for(int idx = 1 ; idx <= colCnt ; idx++) {
				String columnName = rsmd.getColumnName(idx);
				String propName = SampleDataMapperUtils.snakeToCamel(columnName);
				
				PropertyDescriptor pd = new PropertyDescriptor(propName, resultType);
				Field property = resultType.getDeclaredField(propName);
				Method setter =  pd.getWriteMethod();
				if(property.getType().equals(int.class)) {
					setter.invoke(vo, rs.getInt(columnName));
				}else {
					setter.invoke(vo, rs.getString(columnName));
				}
		//		vo.setAdrsNo(rs.getInt("ADRS_NO"));
		//		vo.setMemId(rs.getString("MEM_ID"));
		//		vo.setAdrsName(rs.getString("ADRS_NAME"));
		//		vo.setAdrsHp(rs.getString("ADRS_HP"));
		//		vo.setAdrsAdd(rs.getString("ADRS_ADD"));
				
			}
			return (T) vo;
			
		}catch(Exception e) {
			throw new SQLException(e);
		}
	
	}
	/*
		INSERT INTO ADDRESSBOOK(
		ADRS_NO,    
		MEM_ID,   
		ADRS_NAME,  
		ADRS_HP,   
		ADRS_ADD) 
		 values( #{adrsNo}, #{memId}, #{adrsName}, #{adrsHp}, #{adrsAdd} )
	*/
	
	public static PreparedStatement generatePreparedStatement(Connection conn, String beforeSql, Object paramVO) throws SQLException {
		Pattern pattern = Pattern.compile("#\\{(\\w+)\\}");
		Matcher matcher = pattern.matcher(beforeSql);
		List<String> propNames  = new ArrayList<String>();
		StringBuffer afterSql = new StringBuffer(); 
		while(matcher.find()) {
			propNames.add(matcher.group(1));
			matcher.appendReplacement(afterSql, "?");
		}
		matcher.appendTail(afterSql);
		PreparedStatement pstmt = conn.prepareStatement(afterSql.toString());
		
		Class<?> paramType = paramVO.getClass();
		try {
			for(int i = 0 ; i < propNames.size() ; i++) {
				String propName = propNames.get(i);
				PropertyDescriptor pd = new PropertyDescriptor(propName, paramType);
				Method getter =  pd.getReadMethod();
				Object propValue = getter.invoke(paramVO);
				if(pd.getPropertyType().equals(int.class)) {
					pstmt.setInt(i+1,(Integer)propValue);
				}else {
					pstmt.setString(i+1, (String)propValue);
				}
			}
			return pstmt;
			
		} catch(Exception e) {
			throw new SQLException(e);
		}
	}
	
}











