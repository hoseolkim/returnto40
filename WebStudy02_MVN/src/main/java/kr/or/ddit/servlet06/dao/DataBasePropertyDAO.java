package kr.or.ddit.servlet06.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;


// POJO(Plain Old Java Object)
public class DataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDBPropertyList(){
		List<DataBasePropertyVO> list = new ArrayList<>();
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt =  conn.createStatement();
		){
		
			StringBuffer sql = new StringBuffer();
			sql.append("	select property_name, property_value, description ");
			sql.append("	from database_properties                          ");
			ResultSet rs =  stmt.executeQuery(sql.toString());
		//		ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
				Map<String,Object> record = new HashMap<>();
				DataBasePropertyVO vo = new DataBasePropertyVO();
				list.add(vo);
				vo.setPropertyName(rs.getString("property_name".toUpperCase()));
				vo.setPropertyValue(rs.getString("property_value".toUpperCase()));
				vo.setDescription(rs.getString("description".toUpperCase()));
			}
			// 여기서 리턴을 해야한다.
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
			// 여기서 리턴을 시키면 쓰로우때문에 어짜피 안됨
		}
		// 여기서 리턴하지 않는 이유는 뭘까..
	}
}
