import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AdminFrame extends JFrame implements ActionListener {
	JButton resetBtn, returnCarBtn, companyBtn, campingCarsBtn, customersBtn, serviceCentersBtn, requestBtn;
	JButton s1,s2,s3,s4;
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
	   companyBtn = new JButton("대여 회사");
	   campingCarsBtn = new JButton("캠핑 카");
	   customersBtn = new JButton("고객");
	   serviceCentersBtn = new JButton("정비소");
	   requestBtn = new JButton("정비의뢰");
	   
	   resetBtn.addActionListener(this);
	   companyBtn.addActionListener(this);
	   campingCarsBtn.addActionListener(this);
	   customersBtn.addActionListener(this);
	   serviceCentersBtn.addActionListener(this);
	   requestBtn.addActionListener(this);

	   s1 = new JButton("반환 대기 목록");
	   s2 = new JButton("우수회원");
	   s3 = new JButton("블랙리스트");
	   s4 = new JButton("폐차 예정");
	   s1.addActionListener(this);
	   s2.addActionListener(this);
	   s3.addActionListener(this);
	   s4.addActionListener(this);
	   
	   searchBtnPn.add(resetBtn);
	   searchBtnPn.add(companyBtn);
	   searchBtnPn.add(campingCarsBtn);
	   searchBtnPn.add(customersBtn);
	   searchBtnPn.add(serviceCentersBtn);
	   searchBtnPn.add(requestBtn);
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
			else if(e.getSource() == companyBtn) {
				listPn.removeAll();
            	btnPn.removeAll();
            	
				new Companies(btnPn);
				
            	txtResult.setText("리스트번호\t회사명\t주소\t\t전화번호\t담당자 이름\t담당자 이메일\n");
            	rs = stmt.executeQuery(getCompanies);
				while(rs.next()) {
            		String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +
            				"\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";
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
				
				txtResult.setText("리스트번호\t캠핑카 이름\t차량번호\t승차 인원수\t제조회사\t제조 연도\t누적 주행 거리\t대여비용\t대여회사ID\t등록일자\n");
            	rs = stmt.executeQuery(getCapingCars);
				while(rs.next()) {
            		String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +
							"\t" + rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getInt(6) + 
							"\t" + rs.getInt(7) + "\t" + rs.getInt(8) + "\t" + rs.getInt(9) + 
							"\t" + rs.getString(10) + "\n";
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
				txtResult.setText("운전면허증번호\t고객명\t고객주소\t\t고객 전화번호\t고객 이메일\n");
	        	rs = stmt.executeQuery(getCustomers);
				while(rs.next()) {
	        		String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +
	        				"\t" + rs.getString(4) + "\t" + rs.getString(5) + "\n";
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
				txtResult.setText("리스트번호\t정비소명\t정비소 주소\t\t정비소 전화번호\t담당자 이름\t담당자 이메일\n");
	        	rs = stmt.executeQuery(getServiceCenters);
				while(rs.next()) {
	        		String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +
	        				"\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";
	        		txtResult.append(str);
				}
				listPn.add(txtResult);
	        	listPn.revalidate();
	    		listPn.repaint();
			 }
			 else if(e.getSource() == requestBtn){
				listPn.removeAll();
	        	btnPn.removeAll();
				
				txtResult.setText("리스트번호\t고유 대여 번호\t캠핑카 등록 ID\t수리필요 여부\n");
	        	rs = stmt.executeQuery("select * from CarCheckList where repairRequired = "+"'O'");
				while(rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) +
								"\t" + rs.getString(8) + "\n";
						txtResult.append(str);
						System.out.println(str);
					}
				new RequestFix(btnPn);
				listPn.add(txtResult);
	        	listPn.revalidate();
	    		listPn.repaint();
		 	}
			 else if(e.getSource() == s1) {
				listPn.removeAll();
            	btnPn.removeAll();
            	
				new ReturnCar(btnPn);
				
            	txtResult.setText("리스트번호\t캠핑카이름\t운전면허증번호\t대여회사\t대여 시작일\t대여 기간\t청구 요금\t납입 기한\t기타 청구내역\t기타 청구 요금 정보\n");
				rs = stmt.executeQuery("select * from CarRentInfo where id not in (select rentInfoID from CarCheckList)");
				while(rs.next()) {
            	String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) +
						"\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + 
						"\t" + rs.getString(7) + "\t" + rs.getString(8) + "\t" + rs.getString(9) + 
						"\t" + rs.getString(10)+"\n";
            		txtResult.append(str);
				}
				listPn.add(txtResult);
				listPn.revalidate();
        		listPn.repaint();
			}
//			우수회원
//			else if(e.getSource() == s2){
//				listPn.removeAll();
//	           	btnPn.removeAll();
//			}
//			블랙회원
			else if(e.getSource() == s3){
				listPn.removeAll();
				btnPn.removeAll();
				ArrayList<String> licenseNumInCustomers = new ArrayList<>();
				ArrayList<String> licenseNumInRepairInfo = new ArrayList<>();
				ArrayList<String> blackListCustomer = new ArrayList<>();
				txtResult.setText("운전면허증번호\t고객명\t고객 주소\t\t고객 전화번호\t고객 이메일\n");
				rs = stmt.executeQuery("select licenseNum from Customers");
				while(rs.next()){
					licenseNumInCustomers.add(rs.getString(1));
				}
				
				rs = stmt.executeQuery("select licenseNum from RepairInfo");
				while(rs.next()){
					licenseNumInRepairInfo.add(rs.getString(1));
				}
				int cnt=0;
				// System.out.println(licenseNumInCustomers.size() + " " + licenseNumInRepairInfo.size());
				for(int i=0;i<licenseNumInRepairInfo.size();i++){
					for(int j=0;j<licenseNumInCustomers.size();j++){
						if((licenseNumInRepairInfo.get(i)).equals(licenseNumInCustomers.get(j))){
							System.out.println(licenseNumInRepairInfo.get(i) + " " +licenseNumInCustomers.get(j));
							cnt++;
						}
					}
					if(cnt>=3){
						blackListCustomer.add(licenseNumInRepairInfo.get(i));
					}
					cnt=0;
				}
				System.out.println(blackListCustomer.size());
				for(int i=0;i<blackListCustomer.size();i++){
					// rs = stmt.executeQuery("select * from Customers where licenseNum = "+blackListCustomer.get(i));
					// while(rs.next()){
					// 	String str = rs.getString(1) + "\t" + rs.getString(2) + "\t"
					// 	+ rs.getString(3) + "\t" + rs.getString(4) + "\t"
					// 	+ rs.getString(5) + "\n";
					// 	txtResult.append(str);
					// }
				}
				listPn.add(txtResult);
	        	listPn.revalidate();
	    		listPn.repaint();
				// select count(*) from RepairInfo where licenseNum = "  서울시 마포구 망원동";
			}
//			폐차
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