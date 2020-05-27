package shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BuyDBBean {
private static BuyDBBean instance = new BuyDBBean();
	
	public static BuyDBBean getInstance() {
    	return instance;
    }
   
    private BuyDBBean() {}
   
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/oracledb");
        return ds.getConnection();
    }
    
    // bank테이블에 있는 전체 레코드를 얻어내는 메소드
    public List<String> getAccount(){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> accountList = null;
        try {
            conn = getConnection();
                        
            pstmt = conn.prepareStatement("select * from bank");
            rs = pstmt.executeQuery();
            
            accountList = new ArrayList<String>();
            
            while (rs.next()) {
           	  String account = new String(rs.getString("account")+" "
                     + rs.getString("bank")+" "+rs.getString("name"));
           	  accountList.add(account);
		    }
        }catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
        return accountList;
    }
    
    //구매 테이블인 buy에 구매목록 등록
    public void insertBuy( List<CartDataBean> lists,
    		String id, String account, String deliveryName, String deliveryTel,
    		String deliveryAddress) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Timestamp reg_date = null;
        String sql = "";
        String maxDate =" ";
        String number = "";
        String todayDate = "";
        String compareDate = "";
        long buyId = 0;       
        short nowCount ;
        try {
            conn = getConnection();
            reg_date = new Timestamp(System.currentTimeMillis());
            todayDate = reg_date.toString();
            compareDate = todayDate.substring(0, 4) + todayDate.substring(5, 7) + todayDate.substring(8, 10);
            
            pstmt = conn.prepareStatement("select max(buy_id) from buy");
            
            rs = pstmt.executeQuery();
            rs.next();
            if (rs.getLong(1) > 0){         
            	Long val = new Long(rs.getLong(1));
                maxDate = val.toString().substring(0, 8);
                number =  val.toString().substring(8);
                if(compareDate.equals(maxDate)){
                	if((Integer.parseInt(number)+1)<10000)
                	    buyId = Long.parseLong(maxDate + (Integer.parseInt(number)+1+10000));
                	else
                		buyId = Long.parseLong(maxDate + (Integer.parseInt(number)+1));
                }else{
                	compareDate += "00001";
        		    buyId = Long.parseLong(compareDate);
                }
            }else {
            	compareDate += "00001";
    		    buyId = Long.parseLong(compareDate);
            }
            //103~151라인까지 하나의 트랜잭션으로 처리
            conn.setAutoCommit(false);
            for(int i=0; i<lists.size();i++){
            	//해당 아이디에 대한 cart테이블 레코드를을 가져온후 buy테이블에 추가
            	CartDataBean cart = lists.get(i);
            	
            	sql = "insert into buy (buy_id,buyer,book_id,book_title,buy_price,buy_count,";
                sql += "book_image,buy_date,account,deliveryName,deliveryTel,deliveryAddress)";
                sql += " values (?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
            
                pstmt.setLong(1, buyId);
                pstmt.setString(2, id);
                pstmt.setInt(3, cart.getBook_id());
                pstmt.setString(4, cart.getBook_title());
                pstmt.setInt(5, cart.getBuy_price());
                pstmt.setByte(6, cart.getBuy_count());
                pstmt.setString(7, cart.getBook_image());
                pstmt.setTimestamp(8, reg_date);
                pstmt.setString(9, account);
                pstmt.setString(10, deliveryName);
                pstmt.setString(11, deliveryTel);
                pstmt.setString(12, deliveryAddress);
                pstmt.executeUpdate();
                
                //상품이 구매되었으므로 book테이블의 상품수량을 재조정함
                pstmt = conn.prepareStatement(
                		"select book_count from book where book_id=?");
                pstmt.setInt(1, cart.getBook_id());
                rs = pstmt.executeQuery();
                rs.next();
                
                nowCount = (short)(rs.getShort(1) - cart.getBuy_count());
                
                sql = "update book set book_count=? where book_id=?";
                pstmt = conn.prepareStatement(sql);
           
                pstmt.setShort(1, nowCount);
    			pstmt.setInt(2, cart.getBook_id());
                
                pstmt.executeUpdate(); 
            }
            
            pstmt = conn.prepareStatement(
              "delete from cart where buyer=?");
            pstmt.setString(1, id);
          
            pstmt.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
        }catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
    //id에 해당하는 buy테이블의 레코드수를 얻어내는 메소드
    public int getListCount(String id)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select count(*) from buy where buyer=?");
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
    
    //buy테이블의 전체 레코드수를 얻어내는 메소드
    public int getListCount()
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            		"select count(*) from buy");
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
    
    //id에 해당하는 buy테이블의 구매목록을 얻어내는 메소드
    public List<BuyDataBean> getBuyList(String id) 
    throws Exception {
   	    Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BuyDataBean buy=null;
        String sql = "";
        List<BuyDataBean> lists = null;
        
        try {
       	    conn = getConnection();
       	 
       	    sql = "select * from buy where buyer = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            lists = new ArrayList<BuyDataBean>();

            while (rs.next()) {
              buy = new BuyDataBean();
           	 
           	  buy.setBuy_id(rs.getLong("buy_id"));
           	  buy.setBook_id(rs.getInt("book_id"));
           	  buy.setBook_title(rs.getString("book_title"));
           	  buy.setBuy_price(rs.getInt("buy_price"));
           	  buy.setBuy_count(rs.getByte("buy_count")); 
           	  buy.setBook_image(rs.getString("book_image"));
           	  buy.setSanction(rs.getString("sanction"));
           	 
           	  lists.add(buy);
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
    
    //buy테이블의 전체 목록을 얻어내는 메소드
    public List<BuyDataBean> getBuyList() 
    throws Exception {
   	    Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BuyDataBean buy=null;
        String sql = "";
        List<BuyDataBean> lists = null;
        
        try {
       	 conn = getConnection();
       	 
       	 sql = "select * from buy";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            lists = new ArrayList<BuyDataBean>();

            while (rs.next()) {
              buy = new BuyDataBean();
           	 
           	  buy.setBuy_id(rs.getLong("buy_id"));
           	  buy.setBuyer(rs.getString("buyer"));
           	  buy.setBook_id(rs.getInt("book_id"));
           	  buy.setBook_title(rs.getString("book_title"));
           	  buy.setBuy_price(rs.getInt("buy_price"));
           	  buy.setBuy_count(rs.getByte("buy_count")); 
           	  buy.setBook_image(rs.getString("book_image"));
           	  buy.setBuy_date(rs.getTimestamp("buy_date"));
           	  buy.setAccount(rs.getString("account"));
           	  buy.setDeliveryName(rs.getString("deliveryName"));
           	  buy.setDeliveryTel(rs.getString("deliveryTel"));
           	  buy.setDeliveryAddress(rs.getString("deliveryAddress"));
           	  buy.setSanction(rs.getString("sanction"));
           	 
           	  lists.add(buy);
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
}