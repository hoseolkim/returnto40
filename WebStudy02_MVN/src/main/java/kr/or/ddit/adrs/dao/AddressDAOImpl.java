package kr.or.ddit.adrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.common.exception.PersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.utils.SampleDataMapperUtils;
import kr.or.ddit.vo.AddressVO;

public class AddressDAOImpl implements AddressDAO {

	private int generateAdrsNo(Connection conn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NVL(MAX(ADRS_NO),0)+1 FROM ADDRESSBOOK");
		
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql.toString())
		){
			ResultSet rs = pstmt.executeQuery();
			rs.next();
				return rs.getInt(1);
		}
		
		
	}
	
	@Override
	public int insertAddress(AddressVO adrsVO) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ADDRESSBOOK(");
		sql.append("ADRS_NO,     ");
		sql.append("MEM_ID,   ");
		sql.append("ADRS_NAME,   ");
		sql.append("ADRS_HP,    ");
		sql.append("ADRS_ADD)   ");
		sql.append(" values( #{adrsNo}, #{memId}, #{adrsName}, #{adrsHp}, #{adrsAdd} )");
		try(
			Connection conn = ConnectionFactory.getConnection();
		) {
			int adrsNo = generateAdrsNo(conn);
			adrsVO.setAdrsNo(adrsNo);
			PreparedStatement pstmt = SampleDataMapperUtils.generatePreparedStatement(conn , sql.toString() , adrsVO );
			
			int rowcnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			return rowcnt;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} 
	}

	@Override
	public List<AddressVO> selectAddressList(String memId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("ADRS_NO,    ");
		sql.append("MEM_ID ,    ");
		sql.append("ADRS_NAME,  ");
		sql.append("ADRS_HP ,   ");
		sql.append("ADRS_ADD   ");
		sql.append(" from addressbook ");
		sql.append(" where mem_id = ? ");
		List<AddressVO> list = new ArrayList<AddressVO>();
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, memId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				AddressVO vo = SampleDataMapperUtils.recordToVO(rs, AddressVO.class);
				list.add(vo);
			}
			return list;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public int updateAddress(AddressVO adrsVO) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ADDRESSBOOK  ");
		sql.append("SET                 ");
		sql.append("	ADRS_NAME = ? , ");
		sql.append("	ADRS_HP = ? ,   ");
		sql.append("	ADRS_ADD = ?    ");
		sql.append("WHERE ADRS_NO = ?   ");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, adrsVO.getAdrsName());
			pstmt.setString(2, adrsVO.getAdrsHp());
			pstmt.setString(3, adrsVO.getAdrsAdd());
			pstmt.setInt(4, adrsVO.getAdrsNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public int deleteAddress(int adrsNo) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM ADDRESSBOOK   ");
		sql.append("WHERE                     ");
		sql.append("        ADRS_NO = ?     ");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, adrsNo);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
