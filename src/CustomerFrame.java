
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CustomerFrame {

	Connection con;
	Statement stmt;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	static JTextField startDateTF;
	static JTextField startRentPeriodTF;
	JTextArea txtResult;
	JTextArea txtCustomers;
	static JTextField customerLicense;
	static JTextField listNumber;

	static JTextField etcCostTF;
	static JTextField etcCostInfoTF;

	public CustomerFrame() {
		init();
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

	public void init() {
		JFrame customFrame = new JFrame("고객 접속");
		customFrame.setVisible(true);
		customFrame.setBounds(200, 200, 1200, 550); // 가로위치,세로위치,가로길이,세로길이

		JPanel northPn = new JPanel();
		JPanel listPn = new JPanel();
		JPanel southPn = new JPanel();
		JPanel eastPn = new JPanel();
		JPanel customersListPn = new JPanel();

		JButton accessibleListBtn = new JButton("대여 가능 리스트 보기");
		startDateTF = new JTextField("렌트 시작 일", 11);
		startRentPeriodTF = new JTextField("대여 기간", 6);

		txtResult = new JTextArea(50, 60);
		txtResult.setEditable(false);

		txtCustomers = new JTextArea(5, 40);
		txtCustomers.setEditable(false);

		northPn.add(accessibleListBtn);
		northPn.add(startDateTF);
		northPn.add(startRentPeriodTF);

		listPn.add(txtResult);

		customerLicense = new JTextField("운전면허번호", 7);
		listNumber = new JTextField("리스트 번호", 7);
		etcCostTF = new JTextField("기타 청구 내역", 10);
		etcCostInfoTF = new JTextField("기타 청구 요금 정보", 10);
		JButton rentBtn = new JButton("대여 신청");
		southPn.add(customerLicense);
		southPn.add(listNumber);
		southPn.add(etcCostTF);
		southPn.add(etcCostInfoTF);
		southPn.add(rentBtn);

		txtCustomers.setText("운전면허증번호\t\t이름\n");
		try {
			conDB();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from Customers;");
			while (rs.next()) {
				String str = rs.getString(1) + "\t\t" + rs.getString(2) + "\n";
				txtCustomers.append(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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

		customersListPn.add(txtCustomers);
		customersListPn.revalidate();
		customersListPn.repaint();

		customFrame.add("North", northPn);
		customFrame.add("Center", listPn);
		customFrame.add("South", southPn);
//      customFrame.add("East", eastPn);
		customFrame.add("West", customersListPn);

		accessibleListBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					conDB();
					stmt = con.createStatement();

					ArrayList<Integer> inAccessibleCarID = new ArrayList<>();

					ArrayList<Integer> carIDList = new ArrayList<>();
					ArrayList<String> licenseNumList = new ArrayList<>();
					ArrayList<Integer> cpIDList = new ArrayList<>();
					ArrayList<String> rentDateList = new ArrayList<>();
					ArrayList<Integer> rentPeriodList = new ArrayList<>();

					rs = stmt.executeQuery("select * from CarRentInfo;");
					while (rs.next()) {
						rs.getInt(1);
						carIDList.add(rs.getInt(2));
						licenseNumList.add(rs.getString(3));
						cpIDList.add(rs.getInt(4));
						rentDateList.add(rs.getString(5));
						rentPeriodList.add(rs.getInt(6));
						rs.getInt(7);
						rs.getString(8);
						rs.getInt(9);
						rs.getString(10);
					}

					// 전역 변수 값 불러오기
					String startDateStr = startDateTF.getText();
					int period = Integer.parseInt(startRentPeriodTF.getText());

					for (int i = 0; i < carIDList.size(); ++i) {

						String rentDateStr = rentDateList.get(i);
						int rentPeriod = rentPeriodList.get(i);

						Date startDate, rentDate;

						try {
							startDate = java.sql.Date.valueOf(startDateStr);
							rentDate = java.sql.Date.valueOf(rentDateStr);

							if ((startDate.compareTo(
									java.sql.Date.valueOf(addDate(rentDate.toString(), 0, 0, rentPeriod + 5))) == -1
									|| startDate.compareTo(java.sql.Date
											.valueOf(addDate(rentDate.toString(), 0, 0, rentPeriod + 5))) == 0)
									&& ((rentDate.compareTo(
											java.sql.Date.valueOf(addDate(startDate.toString(), 0, 0, period))) == -1)
											|| (rentDate.compareTo(java.sql.Date
													.valueOf(addDate(startDate.toString(), 0, 0, period))) == 0))) {
								inAccessibleCarID.add(carIDList.get(i));
							}
						} catch (Exception e1) {
							// TODO: handle exception
						}
					}

					ArrayList<Integer> accessibleCarID = new ArrayList<>();
					rs = stmt.executeQuery("select id from CampingCars;");
					while (rs.next()) {
						accessibleCarID.add(rs.getInt(1));
					}

					for (int i = 0; i < inAccessibleCarID.size(); ++i) {
						if (accessibleCarID.contains(inAccessibleCarID.get(i)))
							accessibleCarID.remove(inAccessibleCarID.get(i));
					}

					txtResult.setText("리스트번호\t캠핑카이름\t승차인원수\t제조회사\t대여비용\t누적주행거리\n");
					rs = stmt.executeQuery("select * from CampingCars;");
					while (rs.next()) {
						int carID = rs.getInt(1);
						String str = carID + "\t" + rs.getString(2) + "\t" + rs.getInt(4) + "\t" + rs.getString(5)
								+ "\t" + rs.getInt(8) + "\t" + rs.getInt(7) + "\n";

						if (accessibleCarID.contains(carID))
							txtResult.append(str);
					}

					listPn.removeAll();
					listPn.add(txtResult);
					listPn.revalidate();
					listPn.repaint();

				} catch (Exception e2) {
					System.out.println("쿼리 읽기 실패 :" + e2);
				}

			}

		});

		// TODO static JTextField startDateTF;
		// TODO static JTextField startRentPeriodTF;
		// TODO JTextArea txtResult;
		// TODO static JTextField customerLicense;
		// TODO static JTextField listNumber;
		rentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conDB();
					stmt = con.createStatement();

					int carID = Integer.parseInt(listNumber.getText());
					String licenseNum = customerLicense.getText();
					String rentDate = startDateTF.getText();
					int rentPeriod = Integer.parseInt(startRentPeriodTF.getText());
					int etcCost = Integer.parseInt(etcCostTF.getText());
					String paymentDeadline = addDate(startDateTF.getText(), 0, 0,
							Integer.parseInt(startRentPeriodTF.getText())).toString();
					String etcCostInfo = etcCostInfoTF.getText();

					int cpID = 0;
					int cost = 0;

					rs = stmt.executeQuery("select * from CampingCars where id=" + listNumber.getText() + ";");
					while (rs.next()) {
						cpID = rs.getInt(9);
						cost = rs.getInt(8);
					}

					String rentQuery = "insert into CarRentInfo (carID,licenseNum,cpID,rentDate,rentPeriod,cost,paymentDeadline,etcCost,etcCostInfo) values ("
							+ carID + ",'" + licenseNum + "'," + cpID + ",'" + rentDate + "'," + rentPeriod + ","
							+ cost * rentPeriod + ",'" + paymentDeadline + "'," + etcCost + ",'" + etcCostInfo + "'"
							+ ");";
					System.out.println((rentQuery));

					stmt.executeUpdate(rentQuery);

					accessibleListBtn.doClick();

				} catch (Exception e2) {
					System.out.println("쿼리 읽기 실패 :" + e2);
				}
			}
		});
	}
}