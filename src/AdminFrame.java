import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AdminFrame extends JFrame implements ActionListener {
	JFrame adminFrame;
	JButton resetBtn, returnCarBtn, companyBtn, campingCarsBtn, customersBtn, serviceCentersBtn;
	JPanel searchBtnPn, listPn, btnPn;
	GridLayout btns;
	JTextArea txtResult;
	 
	Connection con;
	Statement stmt= null;
	ResultSet rs = null;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
	
	public AdminFrame() {
		super("관리자 접속");
		setVisible(true);
	    setBounds(200, 200, 1200, 550); // 가로위치,세로위치,가로길이,세로길이
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    init();
	}
	    
	public void init() {
		
	   searchBtnPn = new JPanel();
	   listPn = new JPanel();
	   btnPn = new JPanel();
	   
	   txtResult = new JTextArea(15,100);
	   txtResult.setEditable(false);
	
	   resetBtn = new JButton("초기화");
	   returnCarBtn = new JButton("반환 대기 목록");
	   companyBtn = new JButton("대여 회사");
	   campingCarsBtn = new JButton("캠핑 카");
	   customersBtn = new JButton("고객");
	   serviceCentersBtn = new JButton("정비소");
	   
	   resetBtn.addActionListener(this);
	   returnCarBtn.addActionListener(this);
	   companyBtn.addActionListener(this);
	   campingCarsBtn.addActionListener(this);
	   customersBtn.addActionListener(this);
	   serviceCentersBtn.addActionListener(this);
	   JButton s1 = new JButton("search 1");
	   JButton s2 = new JButton("search 2");
	   JButton s3 = new JButton("search 3");
	   JButton s4 = new JButton("search 4");
	   
	   searchBtnPn.add(resetBtn);
	   searchBtnPn.add(returnCarBtn);
	   searchBtnPn.add(companyBtn);
	   searchBtnPn.add(campingCarsBtn);
	   searchBtnPn.add(customersBtn);
	   searchBtnPn.add(serviceCentersBtn);
	   searchBtnPn.add(s1);
	   searchBtnPn.add(s2);
	   searchBtnPn.add(s3);
	   searchBtnPn.add(s4);
	   
	   add("North",searchBtnPn);
	   add("Center",listPn);
	   add("South", btnPn);
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

	public void actionPerformed(ActionEvent e) {
		try {	
			conDB();		
			stmt = con.createStatement();
			String resetTable;
			String getCompanies = "SELECT * FROM Companies";
			String getCapingCars = "SELECT * FROM CampingCars";
			String getCustomers = "SELECT * FROM Customers";
			String getServiceCenters = "SELECT * FROM ServiceCenters";
			
			if(e.getSource() == resetBtn){
				listPn.removeAll();
				btnPn.removeAll();
				listPn.revalidate();
				listPn.repaint();
				
				String querySafeModeOff = "SET SQL_SAFE_UPDATES=0;";
				stmt.executeUpdate(querySafeModeOff);
				// 테이블 존재시 삭제
				for(int i=0;i<7;i++){
					stmt.executeUpdate("SET foreign_key_checks = 0");
					stmt.executeUpdate("drop table if exists " + createTableQuery.tableName[i]);
					stmt.executeUpdate("SET foreign_key_checks = 1");
				}
				//테이블 생성
				for(int i=0;i<7;i++){
					stmt.executeUpdate(createTableQuery.create[i]);
				}
				 //데이터 추가
				for(int i=0;i<4;i++){
					stmt.executeUpdate(createTableQuery.insertSql[i]);
				}
			}
			else if(e.getSource() == returnCarBtn) {
				listPn.removeAll();
            	btnPn.removeAll();
            	
				new returnCar(btnPn);
				
            	txtResult.setText("");
            	rs = stmt.executeQuery("select * from CarRentInfo");
				while(rs.next()) {
            		String str = rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) +
							" " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + 
							" " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + 
							" " + rs.getString(10)+"\n";
            		txtResult.append(str);
				}
				listPn.add(txtResult);
            	listPn.revalidate();
        		listPn.repaint();
			}
			else if(e.getSource() == companyBtn) {
				listPn.removeAll();
            	btnPn.removeAll();
            	
				new Companies(btnPn);
				
            	txtResult.setText("");
            	rs = stmt.executeQuery(getCompanies);
				while(rs.next()) {
            		String str = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) +
            				" " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + "\n";
            		txtResult.append(str);
				}
				listPn.add(txtResult);
            	listPn.revalidate();
        		listPn.repaint();
			} 
			else if(e.getSource() == campingCarsBtn){
				listPn.removeAll();
				btnPn.removeAll();
				
				new CampingCars(btnPn);
				
				txtResult.setText("");
            	rs = stmt.executeQuery(getCapingCars);
				while(rs.next()) {
            		String str = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) +
							" " + rs.getInt(4) + " " + rs.getString(5) + " " + rs.getInt(6) + 
							" " + rs.getInt(7) + " " + rs.getInt(8) + " " + rs.getInt(9) + 
							" " + rs.getString(10) + "\n";
            		txtResult.append(str);
				}
				listPn.add(txtResult);
            	listPn.revalidate();
        		listPn.repaint();
			}
			else if(e.getSource() == customersBtn){
				listPn.removeAll();
	        	btnPn.removeAll();
				
				new Customers(btnPn);
				txtResult.setText("");
	        	rs = stmt.executeQuery(getCustomers);
				while(rs.next()) {
	        		String str = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) +
	        				" " + rs.getString(4) + " " + rs.getString(5) + "\n";
	        		txtResult.append(str);
				}
					listPn.add(txtResult);
	            	listPn.revalidate();
	        		listPn.repaint();
			}
			else if(e.getSource() == serviceCentersBtn){
				listPn.removeAll();
	        	btnPn.removeAll();
				
				new ServiceCenters(btnPn);
				txtResult.setText("");
	        	rs = stmt.executeQuery(getServiceCenters);
				while(rs.next()) {
	        		String str = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) +
	        				" " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + "\n";
	        		txtResult.append(str);
				}
				listPn.add(txtResult);
	        	listPn.revalidate();
	    		listPn.repaint();
		 	}
//			else if(e.getSource() == s1){
//				listPn.removeAll();
//	           	btnPn.removeAll();
//			}
//			else if(e.getSource() == s){
//				listPn.removeAll();
//	           	btnPn.removeAll();
//			}
//			else if(e.getSource() == s3){
//				listPn.removeAll();
//	           	btnPn.removeAll();
//			}
//			else if(e.getSource() == s4){
//				listPn.removeAll();
//	           	btnPn.removeAll();
//			}
			}
			catch (Exception e2) {
				System.out.println();
				System.out.println("쿼리 읽기 실패 :" + e2);
			} 
			finally {
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

}