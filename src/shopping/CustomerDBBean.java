package shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustomerDBBean {
	
	private static CustomerDBBean instance = new CustomerDBBean();
	   
    public static CustomerDBBean getInstance() {
    	return instance;
    }
   
    private CustomerDBBean() {}
   
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/oracledb");
        return ds.getConnection();
    }

    //회원가입
    public void insertMember(CustomerDataBean member) 
    throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
           	 "insert into member14 values (?,?,?,?,?,?,?,?)");
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPasswd());
            pstmt.setString(3, member.getName());
            pstmt.setTimestamp(4, member.getReg_date());
            pstmt.setString(5, member.getTel());
            pstmt.setString(6, member.getAddress());
          
            pstmt.executeUpdate();
        }catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
    }

	 public int userCheck(String id, String passwd) //사용자 인증처리
	 throws Exception {
		 Connection conn = null;
         PreparedStatement pstmt = null;
		 ResultSet rs= null;
         String dbpasswd="";
		 int x=-1;
       
		 try {
			conn = getConnection();
           
            pstmt = conn.prepareStatement(
            	"select passwd from member14 where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

			if(rs.next()){
				dbpasswd= rs.getString("passwd"); 
				if(dbpasswd.equals(passwd))
					x= 1; //인증 성공
				else
					x= 0; //비밀번호 틀림
			}else
				x= -1;//해당 아이디 없음
         }catch(Exception ex) {
        	 ex.printStackTrace();
         } finally {
			 if (rs != null) 
				 try { rs.close(); } catch(SQLException ex) {}
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
		 return x;
	 }

	 public int confirmId(String id) //중복아이디 체크
	 throws Exception {
		 Connection conn = null;
         PreparedStatement pstmt = null;
		 ResultSet rs= null;
		 int x=-1;
       
		 try {
			 conn = getConnection();
           
             pstmt = conn.prepareStatement(
           	  "select id from member14 where id = ?");
             pstmt.setString(1, id);
             rs= pstmt.executeQuery();

			 if(rs.next())
				 x= 1; //해당 아이디 있음
			 else
				 x= -1;//해당 아이디 없음		
         }catch(Exception ex) {
             ex.printStackTrace();
         } finally {
			 if (rs != null) 
				 try { rs.close(); } catch(SQLException ex) {}
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
		 return x;
	 }
	 
	 //회원정보를 수정하기 위해 기존의 정보를 표시
     public CustomerDataBean getMember(String id) 
     throws Exception {
    	 Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         CustomerDataBean member=null;
         
         try {
        	 conn = getConnection();
           
             pstmt = conn.prepareStatement(
           	     "select * from member14 where id = ?");
             pstmt.setString(1, id);
             rs = pstmt.executeQuery();

             if (rs.next()) {
            	 member = new CustomerDataBean();
            	 
                 member.setId(rs.getString("id"));
                 member.setPasswd(rs.getString("passwd"));
				 member.setName(rs.getString("name"));
				 member.setReg_date(rs.getTimestamp("reg_date")); 
                 member.setTel(rs.getString("tel"));
                 member.setAddress(rs.getString("address"));     
			 }
         }catch(Exception ex) {
             ex.printStackTrace();
         }finally {
             if (rs != null) 
            	 try { rs.close(); } catch(SQLException ex) {}
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
		 return member;
     }
   
     public void updateMember(CustomerDataBean member) //회원의 정보수정
     throws Exception {
    	 Connection conn = null;
         PreparedStatement pstmt = null;
       
         try {
        	 conn = getConnection();
           
             pstmt = conn.prepareStatement(
               "update member14 set passwd=?,name=?,tel=?,address=? "+
               "where id=?");
             pstmt.setString(1, member.getPasswd());
             pstmt.setString(2, member.getName());
             pstmt.setString(3, member.getTel());
             pstmt.setString(4, member.getAddress());
             pstmt.setString(5, member.getId());
           
             pstmt.executeUpdate();
         }catch(Exception ex) {
             ex.printStackTrace();
         }finally {
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
     }
   
     public int deleteMember(String id, String passwd) //회원탈퇴
     throws Exception {
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs= null;
         String dbpasswd="";
         int x=-1;
         
         try {
			 conn = getConnection();

             pstmt = conn.prepareStatement(
           	  "select passwd from member14 where id = ?");
             pstmt.setString(1, id);
             rs = pstmt.executeQuery();
           
			 if(rs.next()){
				 dbpasswd= rs.getString("passwd"); 
				 if(dbpasswd.equals(passwd)){
					 pstmt = conn.prepareStatement(
           	             "delete from member14 where id=?");
                     pstmt.setString(1, id);
                     pstmt.executeUpdate();
					 x= 1; //회원탈퇴 성공
				 }else
					 x= 0; //비밀번호 틀림
			 }
         }catch(Exception ex) {
             ex.printStackTrace();
         }finally {
             if (rs != null) 
            	 try { rs.close(); } catch(SQLException ex) {}
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
		 return x;
     }

}