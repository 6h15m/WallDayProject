import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import bean.DdayBean;
 
public class DBconnect {
	
	// 필요 변수 선언
    String driver = "org.mariadb.jdbc.Driver";
    Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;

    // mariaDB와 연결을 위한 생성자
    public DBconnect() {
         try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:8888/mydb",
                    "root",
                    "1234");     
            if( con != null ) {
                System.out.println("DB 접속 성공");
            }
        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
    }
    
    // DB 닫기 기능
    public void dbClose() {
    	try {
    		if(rs != null) rs.close();
    		if(st != null) st.close();
    		if(ps != null) ps.close();
    	} catch (Exception e) {
    		System.out.println(e + "=> DB 닫기에 실패하였습니다.");
    	}
    }
    
    // dDay의 정보를 입력하는 기능
    public int dDayInsert(newDdayGUI dDay) {
    	int result = 0;
    	try {
    		ps = con.prepareStatement("insert into Calendar values(?,?,?,?,?,?)");
    		ps.setInt(1, Integer.parseInt(dDay.year.getText()));
    		ps.setInt(2, Integer.parseInt(dDay.month.getText()));
    		ps.setInt(3, Integer.parseInt(dDay.day.getText()));
    		ps.setString(4, dDay.title.getText());
    		ps.setString(5, dDay.bgColor.getText());
    		ps.setString(6, dDay.textColor.getText());		
    		result = ps.executeUpdate(); // 실행 -> 저장
    	} catch (SQLException e) {
    		System.out.println(e + "=> 정보 입력에 실패하였습니다.");
    	} finally {
    		dbClose();
    	}
    	return result;
    }
    
    // dDay의 모든 정보를 조회하는 기능
    public void dDaySelectAll(DefaultTableModel t_model) {
    	try {
    		st = con.createStatement();
    		rs = st.executeQuery("select * from Calendar order by title");
    		
    		for (int i = 0; i < t_model.getRowCount();) {
    			t_model.removeRow(0);
    		}
    		while (rs.next()) {
    			Object data[] = {rs.getInt(1), rs.getInt(2), rs.getInt(3), 
    					 rs.getString(4), rs.getString(5), rs.getString(6)};
    			t_model.addRow(data);
    		}
    	} catch (SQLException e) {
    		System.out.println(e + "=> 정보 조회에 실패하였습니다.");
    	} finally {
    		dbClose();
    	}
    }
    
    // Title에 해당하는 dDay 삭제하는 기능
    public int dDayDelete(String title) {
    	int result = 0;
    	try {
    		ps = con.prepareStatement("delete from Calendar where title =?");
    		ps.setString(1, title.trim());
    		result = ps.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e + "=> 정보 삭제에 실패하였습니다.");
    	} finally {
    		dbClose();
    	}
    	return result;
    }
    
    // Title에 해당하는 dDay의 정보를 수정하는 기능
    public int dDayUpdate(newDdayGUI dDay) {
    	int result = 0;
    	String sql = "UPDATE Calendar SET year=?, month=?, day=?, bgColor=?, textColor=? WHERE title=?";
    	try {
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, Integer.parseInt(dDay.year.getText()));
    		ps.setInt(2, Integer.parseInt(dDay.month.getText()));
    		ps.setInt(3, Integer.parseInt(dDay.day.getText()));
    		ps.setString(4, dDay.bgColor.getText());
    		ps.setString(5, dDay.textColor.getText());
    		ps.setString(6, dDay.title.getText().trim());
    		
    		result = ps.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e + "=> 정보 수정에 실패하였습니다.");
    	} finally {
    		dbClose();
    	}
    	return result;
    }
    
    //디데이 실행창을 실행하는 기능
    public void dDayGo(String title, int row) {
    	int result = 0;
    	int year = 0; int month = 0; int day = 0; String bgcolor = null; String textcolor = null;
    	try {
    		st = con.createStatement();
    		rs = st.executeQuery("SELECT * FROM Calendar ORDER BY title");
    		
    		ArrayList<DdayBean> list = new ArrayList<DdayBean>();
    		
    		while(rs.next()) {
    			DdayBean bean = new DdayBean();
    			bean.setYear(rs.getInt("year"));
    			bean.setMonth(rs.getInt("month"));
    			bean.setDay(rs.getInt("day"));
    			bean.setBgColor(rs.getString("bgColor"));
    			bean.setTextColor(rs.getString("textColor"));
    			list.add(bean);
    		}
    		
    		year = list.get(row).getYear();
    		month = list.get(row).getMonth();
    		day = list.get(row).getDay();
    		bgcolor = list.get(row).getBgColor();
    		textcolor = list.get(row).getTextColor();
    		
    		dDayGUI d = new dDayGUI();
    		d.dDayGUI(year, month, day, title, bgcolor, textcolor);	
    	} catch (SQLException e) {
    		System.out.println(e + "=> 실행에 실패하였습니다.");
    	}
    }

    
}
