package example01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class Example01DAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public Connection dbConn() {
		 
	try {
			
			String dbdriver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId="example01";
			String dbPasswd = "1234";
			
			Class.forName(dbdriver);
			
			conn = DriverManager.getConnection(dbUrl,dbId,dbPasswd);
			System.out.println("--오라클 접속 성공--");
			}catch(Exception e) {
	  		System.out.println("--오라클 접속실패--"); 
	  		e.printStackTrace();
			}
	return conn;
			
			
	}
		

	public void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {

		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(conn != null) {conn.close();}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("--오라클 접속 끊기--");
		}
	
		}
	
public int setInsert(ExampleDTO dto) {
		
		int result = 0;
		dbConn();
		
		try {
			String sql = "insert into o values(seq_memo.nextval,?,?,current_timestamp)";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1,dto.getName());
			pstmt.setString(2,dto.getMemo());
			

			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConnClose(rs,pstmt,conn);
		}
		 
		return result;
	}
	



public ArrayList<ExampleDTO> getSelectAll(int startRecord,int lastRecord){
	ArrayList<ExampleDTO> list =new ArrayList<>(); 
	dbConn();
	
	try {
		String basicSql="";
		   basicSql = "select * from o where id > ?";
		   basicSql+= "order by id desc";
	String sql="";
	sql+="select * from (select A.*, Rownum Rnum from("+basicSql+") A)";
	sql+="where Rnum >=? and Rnum <=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, 0);
		pstmt.setInt(2, startRecord);
		pstmt.setInt(3, lastRecord);
		rs = pstmt.executeQuery();
		
		while(rs.next()){	
			
			ExampleDTO dto = new ExampleDTO();
			
			dto.setId(rs.getInt("id"));
			dto.setName(rs.getString("name"));
			dto.setMemo(rs.getString("memo"));
			dto.setWdate(rs.getTimestamp("wdate"));
	
			list.add(dto);
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}

	return list;
}

public int getTotalRecord() {
	int result = 0;
	dbConn();
	try {
		String sql="select count(*) from o where id > ?";
		pstmt =conn.prepareStatement(sql);
		pstmt.setInt(1, 0);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			result=rs.getInt(1);
		}
	}catch(Exception e) {
		e.printStackTrace();
	 }finally {
		 dbConnClose(rs,pstmt,conn);	
	}return result;
}

}

