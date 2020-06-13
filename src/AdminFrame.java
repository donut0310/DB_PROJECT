import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AdminFrame implements ActionListener {
	    JButton resetBtn, companyBtn, campingCarsBtn, customersBtn, serviceCentersBtn;
	    JPanel searchBtnPn, listPn, btnPn;
	    GridLayout btns;
	    JTextArea txtResult;
		 
	    public AdminFrame() {
		    init();
	    }
	    
	    public void init() {
		   JFrame adminFrame = new JFrame();
		   adminFrame.setVisible(true);
		   adminFrame.setBounds(200, 200, 1200, 550); // 가로위치,세로위치,가로길이,세로길이
		   adminFrame.setTitle("관리자 접속");
		   adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		   searchBtnPn = new JPanel();
		   listPn = new JPanel();
		   btnPn = new JPanel();
		   txtResult = new JTextArea();
		   
		   listPn.add(txtResult);
		   JTextArea txtResult = new JTextArea(15,50);
		   txtResult.setEditable(false);
		   JScrollPane scrollPane = new JScrollPane(txtResult);
		    
		   listPn.add("Center",scrollPane);
		   resetBtn = new JButton("초기화");
		   companyBtn = new JButton("대여 회사");
		   campingCarsBtn = new JButton("캠핑 카");
		   customersBtn = new JButton("고객");
		   serviceCentersBtn = new JButton("정비소");
		   
		   companyBtn.addActionListener(this);
		   campingCarsBtn.addActionListener(this);
		   customersBtn.addActionListener(this);
		   serviceCentersBtn.addActionListener(this);
		   resetBtn.addActionListener(this);

		   JButton s1 = new JButton("search 1");
		   JButton s2 = new JButton("search 2");
		   JButton s3 = new JButton("search 3");
		   JButton s4 = new JButton("search 4");
		   
		   searchBtnPn.add(resetBtn);
		   searchBtnPn.add(companyBtn);
		   searchBtnPn.add(campingCarsBtn);
		   searchBtnPn.add(customersBtn);
		   searchBtnPn.add(serviceCentersBtn);
		   searchBtnPn.add(s1);
		   searchBtnPn.add(s2);
		   searchBtnPn.add(s3);
		   searchBtnPn.add(s4);
		   
		   adminFrame.add("North",searchBtnPn);
		   adminFrame.add("Center",listPn);
		   adminFrame.add("South", btnPn);
	   }
	   
	   public void actionPerformed(ActionEvent e) {
			Connection con = Start_main.con;
			Statement stmt = Start_main.stmt;
			ResultSet rs = Start_main.rs;
			try {				
				stmt = con.createStatement();
				String resetTable;
				String getCompanies = "SELECT * FROM Companies";
				// String getCapingCars = "SELECT * FROM CampingCars";
				// String getCustomers = "SELECT * FROM Customers";
				// String getServiceCenters = "SELECT * FROM ServiceCenters";
				
				if(e.getSource() == resetBtn){
					String querySafeModeOff = "SET SQL_SAFE_UPDATES=0;";
					stmt.executeUpdate(querySafeModeOff);
					// 테이블 존재시 삭제
					for(int i=0;i<7;i++){
						if(createTableQuery.tableName[i]=="Companies"){
							stmt.executeUpdate("SET foreign_key_checks = 0");
							stmt.executeUpdate("drop table if exists Companies");
							stmt.executeUpdate("SET foreign_key_checks = 1");
						}
						else{
							resetTable = "drop table if exists " + createTableQuery.tableName[i];
							stmt.executeUpdate(resetTable);
						}
					}
					//테이블 생성
					for(int i=0;i<4;i++){
						// System.out.println(Start_main.stmt.executeUpdate(createTableQuery.create[i]));
						stmt.executeUpdate(createTableQuery.create[i]);
						// Start_main.rs = Start_main.stmt.executeQuery("Select * from "+createTableQuery.tableName[i]);
						// System.out.println(Start_main.rs.getString(0));
					}
					 //데이터 추가
					for(int i=0;i<4;i++){
						stmt.executeUpdate(createTableQuery.insertSql[i]);
					}
				}
				else if(e.getSource() == companyBtn) {
					listPn.removeAll();
	            	btnPn.removeAll();
	            	
					Companies a = new Companies(btnPn);
					
	            	txtResult.setText("");
	            	txtResult.setText("test");
	            	rs = stmt.executeQuery(getCompanies);
					while(rs.next()) {
	            		String str = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) +
	            				" " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + "\n";
	            		txtResult.append(str);
	            	}
	            	listPn.revalidate();
	        		listPn.repaint();
				} 
// 				else if(e.getSource() == campingCarsBtn){
// 					listPn.removeAll();
// 	            	btnPn.removeAll();
// 	            	CampingCars a = new CampingCars(listPn, btnPn);
// 				}
// 				else if(e.getSource() == customersBtn){
// 					listPn.removeAll();
// 	            	btnPn.removeAll();
// 	            	Customers a = new Customers(listPn, btnPn);
// 				}
// 				else if(e.getSource() == serviceCentersBtn){
// 					listPn.removeAll();
// 	            	btnPn.removeAll();
// 	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
// 			 	}
//				else if(e.getSource() == s1){
//					listPn.removeAll();
//	            	btnPn.removeAll();
//	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
//				}
//				else if(e.getSource() == s2){
//					listPn.removeAll();
//	            	btnPn.removeAll();
//	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
//				}
//				else if(e.getSource() == s3){
//					listPn.removeAll();
//	            	btnPn.removeAll();
//	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
//				}
//				else if(e.getSource() == s4){
//					listPn.removeAll();
//	            	btnPn.removeAll();
//	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
//				}
			} catch (Exception e2) {
				System.out.println("쿼리 읽기 실패 :" + e2);
			 } 
			finally {
				try {
					rs.close();
					stmt.close();
					con.close();
					// if (rs != null)
					// 	rs.close();
					// if (stmt != null)
					// 	stmt.close();
					// if (con != null)
					// 	con.close();
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}

}