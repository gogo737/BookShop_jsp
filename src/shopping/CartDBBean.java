package shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDBBean {
	private static CartDBBean instance = new CartDBBean();
	   
    public static CartDBBean getInstance() {
    	return instance;
    }
   
    private CartDBBean() {}
   
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/oracledb");
        return ds.getConnection();
    }
    
    //[장바구니에 담기]를 클릭하면 수행되는 것으로 cart 테이블에 새로운 레코드를 추가
    public void insertCart(CartDataBean cart) 
    throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        String sql="";
        
        try {
            conn = getConnection();
            sql = "insert into cart (cart_id,book_id, buyer," +
            		"book_title,buy_price,buy_count,book_image) " +
            		"values ((select nvl(max(cart_id),0)+1 from cart),?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, cart.getBook_id());
            pstmt.setString(2, cart.getBuyer());
            pstmt.setString(3, cart.getBook_title());
            pstmt.setInt(4, cart.getBuy_price());
            pstmt.setByte(5, cart.getBuy_count());
            pstmt.setString(6, cart.getBook_image());
            
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
    
    //id에 해당하는 레코드의 수를 얻어내는 메소드
    public int getListCount(String id)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            		"select count(*) from cart where buyer=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception ex) {
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

	 
     //id에 해당하는 레코드의 목록을 얻어내는 메소드
     public List<CartDataBean> getCart(String id) 
     throws Exception {
    	 Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         CartDataBean cart=null;
         String sql = "";
         List<CartDataBean> lists = null;
         
         try {
        	 conn = getConnection();
        	 
        	 sql = "select * from cart where buyer = ?";
             pstmt = conn.prepareStatement(sql);
             
             pstmt.setString(1, id);
             rs = pstmt.executeQuery();
             
             lists = new ArrayList<CartDataBean>();

             while (rs.next()) {
            	 cart = new CartDataBean();
            	 
            	 cart.setCart_id(rs.getInt("cart_id"));
            	 cart.setBook_id(rs.getInt("book_id"));
            	 cart.setBook_title(rs.getString("book_title"));
            	 cart.setBuy_price(rs.getInt("buy_price"));
            	 cart.setBuy_count(rs.getByte("buy_count")); 
            	 cart.setBook_image(rs.getString("book_image"));
            	 
            	 lists.add(cart);
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
		 return lists;
     }
   
     //장바구니에서 수량을 수정시 실행되는 메소드
     public void updateCount(int cart_id, byte count) 
     throws Exception {
    	 Connection conn = null;
         PreparedStatement pstmt = null;
       
         try {
        	 conn = getConnection();
            
             pstmt = conn.prepareStatement(
               "update cart set buy_count=? where cart_id=?");
             pstmt.setByte(1, count);
             pstmt.setInt(2, cart_id);
                        
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
   
     //장바구니에서 cart_id에대한 레코드를 삭제하는 메소드
     public void deleteList(int cart_id) 
     throws Exception {
         Connection conn = null;
         PreparedStatement pstmt = null;
               
         try {
			 conn = getConnection();

             pstmt = conn.prepareStatement(
           	   "delete from  cart where cart_id=?");
             pstmt.setInt(1, cart_id);
             
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
     
     //id에 해당하는 모든 레코드를 삭제하는 메소드로 [장바구니 비우기]단추를 클릭시 실행된다.
     public void deleteAll(String id)
     throws Exception {
         Connection conn = null;
         PreparedStatement pstmt = null;
                  
         try {
			 conn = getConnection();

             pstmt = conn.prepareStatement(
               "delete from cart where buyer=?");
             pstmt.setString(1, id);
             
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

}