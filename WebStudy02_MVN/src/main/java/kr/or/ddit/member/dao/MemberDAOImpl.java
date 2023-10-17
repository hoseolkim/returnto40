package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.common.exception.PersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
//		String sql = "select * from member where mem_id= ? and mem_pass = ? "; 
		StringBuffer sql = new StringBuffer();
		sql.append("select mem_id, mem_pass, mem_name, mem_hp, mem_mail ");
		sql.append("from member                                         ");
		sql.append("where mem_id = ? and mem_pass = ?     ");
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			
			pstmt.setString(1, inputData.getMemId());
			pstmt.setString(2, inputData.getMemPass());
			
			System.out.println(sql);
			ResultSet rs= pstmt.executeQuery();
			
//			ResultSet rs = stmt.executeQuery(sql.toString());
			MemberVO saved = null;
			if(rs.next()) {
				saved = new MemberVO();
				saved.setMemId(rs.getString("MEM_ID"));
				saved.setMemPass(rs.getString("MEM_PASS"));
				saved.setMemName(rs.getString("MEM_NAME"));
				saved.setMemHp(rs.getString("MEM_HP"));
				saved.setMemMail(rs.getString("MEM_MAIL"));
			}
			return saved;
			
//			
//			ResultSet rs = pstmt.executeQuery();
////			ResultSetMetaData rsmd = rs.getMetaData();
//			while(rs.next()) {
//				result = new MemberVO();
//				result.setMemId(rs.getString("MEM_ID"));
//				result.setMemPass(rs.getString("MEM_PASS"));
//				result.setMemName(rs.getString("MEM_NAME"));
//				result.setMemRegno1(rs.getString("MEM_REGNO1"));
//				result.setMemRegno2(rs.getString("MEM_REGNO2"));
//				result.setMemBir(rs.getString("MEM_BIR"));
//				result.setMemZip(rs.getString("MEM_ZIP"));
//				result.setMemAdd1(rs.getString("MEM_ADD1"));
//				result.setMemAdd2(rs.getString("MEM_ADD2"));
//				result.setMemHometel(rs.getString("MEM_HOMETEL"));
//				result.setMemComtel(rs.getString("MEM_COMTEL"));
//				result.setMemHp(rs.getString("MEM_HP"));
//				result.setMemMail(rs.getString("MEM_MAIL"));
//				result.setMemJob(rs.getString("MEM_JOB"));
//				result.setMemLike(rs.getString("MEM_LIKE"));
//				result.setMemMemorial(rs.getString("MEM_MEMORIAL"));
//				result.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
//				result.setMemMileage(rs.getInt("MEM_MILEAGE"));
//				result.setMemDelete(rs.getString("MEM_DELETE"));
//			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}
}