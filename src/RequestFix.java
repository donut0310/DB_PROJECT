import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RequestFix {
    Connection con;
	Statement stmt= null;
	ResultSet rs = null;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
    String pwd = "madang";

    public RequestFix(JPanel btnPn){
        init(btnPn);
    }

    private static String addDate(String dt, int y, int m, int d) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = format.parse(dt);
        cal.setTime(date);
        cal.add(Calendar.YEAR, y);
        cal.add(Calendar.MONTH, m);
        cal.add(Calendar.DATE, d);
        return format.format(cal.getTime());
     }
    public void init(JPanel btnPn) {
		JButton submitBtn = new JButton("입력");
		
		JTextField input1 = new JTextField("고유대여 번호");
		JTextField input2 = new JTextField("캠핑카 정비소 ID");
		JTextField input3 = new JTextField("정비 내역");
		JTextField input4 = new JTextField("수리 비용");
		JTextField input5 = new JTextField("기타 정비내역");
		
		btnPn.setLayout(new GridLayout(1,6)); 
		
		btnPn.add(submitBtn);
		btnPn.add(input1);
		btnPn.add(input2);
		btnPn.add(input3);
		btnPn.add(input4);
		btnPn.add(input5);

		submitBtn.addActionListener( new ActionListener(){
			String str;
			int primaryID;
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(input1.getText());
				int carID;
				int scID = Integer.parseInt(input2.getText());
				int cpID;
				String licenseNum;
				String repairDetail = (input3.getText());
				String repairDate;
				int repairCost = Integer.parseInt(input4.getText());
				String paymentDeadline;
				String etcRepairDetail = (input5.getText());
				
				try{
					conDB();
					stmt = con.createStatement();
					rs = stmt.executeQuery("select * from CarRentInfo where id = " + id);
					while(rs.next()){
						primaryID = rs.getInt(1);
						System.out.println((primaryID));
						carID = rs.getInt(2);
						licenseNum = rs.getString(3);
						cpID = rs.getInt(4);
						repairDate = addDate(rs.getString(5), 0, 0,  rs.getInt(6) + 5);
						paymentDeadline = addDate( repairDate, 0, 0,   5);
						System.out.println(carID+","+licenseNum);
						str = "insert into RepairInfo (carID,scID,cpID,licenseNum,repairDetail,repairDate,repairCost,paymentDeadline,etcRepairDetail) VALUES (" +  
						+ carID + "," 
						+ scID +  "," 
						+ cpID + ",'" 
						+ licenseNum + "','" 
						+ repairDetail + "','"
						+ repairDate + "',"
						+ repairCost + ",'"
						+ paymentDeadline + "','"
						+ etcRepairDetail + "')";
						// System.out.println(str);
						
						// String str2 = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t"
						// + rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getInt(6) 
						// + "\t" + rs.getInt(7) + "\t" + rs.getString(8) + "\t" + rs.getInt(9) +"\t" + rs.getString(10) +"\n";
						// System.out.println(str2 + "\n");
					}					
				}catch (Exception e2) {
					System.out.println("쿼리 읽기 실패 :" + e2);
				}finally {
					try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();
					} catch (Exception e3) {
					// TODO: handle exception
					}
				}
				try{
					conDB();
					stmt = con.createStatement();
					stmt.executeUpdate("delete from carchecklist where rentInfoID = " + primaryID);				
				}catch (Exception e2) {
					System.out.println("쿼리 읽기 실패 :" + e2);
				}finally {
					try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();
					} catch (Exception e3) {
					// TODO: handle exception
					}
				}
				try{
					conDB();
					stmt = con.createStatement();
					stmt.executeUpdate(str);
				}catch (Exception e2) {
					System.out.println("쿼리 읽기 실패 :" + e2);
				}finally {
					try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();
					} catch (Exception e3) {
					// TODO: handle exception
					}
				}
			}
		
		});
	}
	public void conDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}