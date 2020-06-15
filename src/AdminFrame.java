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
				for(int i=0;i<6;i++){
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
				
				txtResult.setText("리스트번호\t고유 대여 번호\t캠핑카 등록 ID\t앞부분 설명\t\t왼쪽설명\t\t오른쪽설명\t\t뒤쪽설명\t\t수리필요 여부\n");
	        	rs = stmt.executeQuery("select * from CarCheckList where repairRequired = "+"'O'");
				while(rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) +
								"\t" + rs.getString(4)+"\t\t" + rs.getString(5)+"\t\t" + rs.getString(6)+ 
								"\t\t" + rs.getString(7)+"\t\t" + rs.getString(8) + "\n";
						txtResult.append(str);
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
//			우수회원 : 반환된 차량의 수리필요 여부에 따라 정비 정보 테이블로 데이터가 옮겨지는데, 캠핑카 대여정보 테이블의 
// 			운전 면허증과 비교해 정비 정보테이블에 같은 면허증이 없다면 무사고로 인정해 우수회원으로 추가
			else if(e.getSource() == s2){
					listPn.removeAll();
				    btnPn.removeAll();
				    ArrayList<String> licenseNumInRentInfor = new ArrayList<>();
				 	ArrayList<String> licenseNumInRepairInfo = new ArrayList<>();
					ArrayList<String> pirmeCustomer = new ArrayList<>();

					txtResult.setText("운전면허증번호\t고객명\t고객 주소\t\t고객 전화번호\t고객 이메일\n");
					rs = stmt.executeQuery("select licenseNum from CarRentInfo");
					while(rs.next()){
						licenseNumInRentInfor.add(rs.getString(1));
					}
					rs = stmt.executeQuery("select licenseNum from RepairInfo");
					while(rs.next()){
						licenseNumInRepairInfo.add(rs.getString(1));
					}

					int cnt = 0;
					System.out.println(licenseNumInRentInfor.size() + " " + licenseNumInRepairInfo.size());
				
					for(int i=0;i<licenseNumInRentInfor.size();i++){
						for(int j=0;j<licenseNumInRepairInfo.size();j++){
							if((licenseNumInRepairInfo.get(j)).equals(licenseNumInRentInfor.get(i))){
								cnt++;
							}
						}
						if(cnt==0){
							pirmeCustomer.add(licenseNumInRentInfor.get(i));
						}
						cnt=0;
					}
					for(int i=0;i<pirmeCustomer.size();i++){
						rs = stmt.executeQuery("select * from Customers where licenseNum = " + "'" +pirmeCustomer.get(i)+"'");
						while(rs.next()){
							String str = rs.getString(1) + "\t" + rs.getString(2) + "\t"
							+ rs.getString(3) + "\t" + rs.getString(4) + "\t"
							+ rs.getString(5) + "\n";
							System.out.println(str);
							txtResult.append(str);
						}
					}
					listPn.add(txtResult);
	        		listPn.revalidate();
	    			listPn.repaint();
			}
//			블랙회원 : 반환된 차량의 수리필요 여부에 따라 정비 정보 테이블로 데이터가 옮겨진다
// 			정비 정보 테이블 내에 운전면허증을 기준으로 고객 테이블의 운전면허증과 비교해 3번이상 같은 면허증이 발견되면
// 			블랙회원으로 추가
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
				int cnt = 0;
				System.out.println(licenseNumInCustomers.size() + " " +licenseNumInRepairInfo.size() );
				for(int i=0;i<licenseNumInCustomers.size();i++){
					System.out.println(licenseNumInCustomers.get(i));
				}System.out.println("---------------------------");
				for(int i=0;i<licenseNumInRepairInfo.size();i++){
					System.out.println(licenseNumInRepairInfo.get(i));
				}
				for(int i=0;i<licenseNumInCustomers.size();i++){
					for(int j=0;j<licenseNumInRepairInfo.size();j++){
						if((licenseNumInCustomers.get(i)).equals(licenseNumInRepairInfo.get(j))){
							System.out.println(licenseNumInCustomers.get(i)+ " and " + licenseNumInRepairInfo.get(j));
							cnt++;
						}
					}
					if(cnt>=3){
						blackListCustomer.add(licenseNumInCustomers.get(i));
					}
					cnt=0;
				}
				for(int i=0;i<blackListCustomer.size();i++){
					rs = stmt.executeQuery("select * from Customers where licenseNum = " + "'" +blackListCustomer.get(i)+"'");
					while(rs.next()){
						String str = rs.getString(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getString(3) + "\t" + rs.getString(4) + "\t"
						+ rs.getString(5) + "\n";
						System.out.println(str);
						txtResult.append(str);
					}
				}
				listPn.add(txtResult);
	        	listPn.revalidate();
	    		listPn.repaint();
			}
//         폐차
//         사용 회수가 5회 이상이고 수리를 3회 이상 받아 더 이상 캠핑카로써의 메리트가 없어 폐차 예정인 목록 출력하는 버튼
//         사용 회수가 5회 이상인지 확인 -> CarRentInfo 테이블 검사
//         수리 회수가 3회 이상인지 확인 -> RepairInfo 테이블 검사
else if (e.getSource() == s4) {
	listPn.removeAll();
	btnPn.removeAll();

	ArrayList<Integer> carIDList = new ArrayList<Integer>();
	ArrayList<Integer> rentCountList = new ArrayList<Integer>();
	ArrayList<Integer> repairCountList = new ArrayList<Integer>();

	txtResult.setText("폐차예정차량\t차량번호\t제조연도\t대여회수\t수리회수\n");

	rs = stmt.executeQuery("select id from campingcars;");
	while (rs.next()) {
	   carIDList.add(rs.getInt(1));
	}

	rs = stmt.executeQuery("select carID from carRentInfo;");
	while (rs.next()) {
	   rentCountList.add(rs.getInt(1));
	}

	rs = stmt.executeQuery("select carID from RepairInfo;");
	while (rs.next()) {
	   repairCountList.add(rs.getInt(1));
	}

	for (int i = 0; i < carIDList.size(); ++i) {
//               if(rentCountList.get(i) >= 5 && repairCountList.get(i) >= 3 ) {
	   if (rentCountList.get(i) >= 1 && repairCountList.get(i) >= 1) {
		  rs = stmt.executeQuery("select * from campingcars where id = " + carIDList.get(i) + ";");
		  while (rs.next()) {
			 String str = "" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(6) + "\t"
				   + rentCountList.get(i) + "\t" + repairCountList.get(i);
			 txtResult.append(str);
		  }
	   }
	}
	System.out.println(txtResult);
	listPn.add(txtResult);
	listPn.revalidate();
	listPn.repaint();
 }
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