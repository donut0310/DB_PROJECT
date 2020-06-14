
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
import javax.swing.JLabel;
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
		JFrame customFrame = new JFrame();
		customFrame.setVisible(true);
		customFrame.setBounds(200, 200, 550, 550); // 가로위치,세로위치,가로길이,세로길이

		JPanel northPn = new JPanel();
		JPanel listPn = new JPanel();
		JPanel southPn = new JPanel();

		JButton accessibleListBtn = new JButton("대여 가능 리스트 보기");
		startDateTF = new JTextField("렌트 시작 일", 11);
		startRentPeriodTF = new JTextField("대여 기간", 6);

		txtResult = new JTextArea(15, 100);
		txtResult.setEditable(false);

		northPn.add(accessibleListBtn);
		northPn.add(startDateTF);
		northPn.add(startRentPeriodTF);
		
		listPn.add(txtResult);
		
		JButton chooseCustomerBtn = new JButton("고객 선택");
		JTextField customerName = new JTextField("고객 이름", 7);
		JTextField listNumber = new JTextField("리스트 번호", 7);
		JButton rentBtn = new JButton("대여 신청");
		southPn.add(chooseCustomerBtn);
		southPn.add(customerName);
		southPn.add(listNumber);
		southPn.add(rentBtn);

		customFrame.add("North", northPn);
		customFrame.add("Center", listPn);
		customFrame.add("South", southPn);

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

							if ((startDate.compareTo(rentDate) == -1 && rentDate.compareTo(
									java.sql.Date.valueOf(addDate(startDate.toString(), 0, 0, period))) == -1)
									|| (startDate
											.compareTo(java.sql.Date
													.valueOf(addDate(rentDate.toString(), 0, 0, rentPeriod + 5))) == -1
											&& (java.sql.Date
													.valueOf(addDate(rentDate.toString(), 0, 0, rentPeriod + 5)))
															.compareTo(java.sql.Date.valueOf(addDate(
																	startDate.toString(), 0, 0, period))) == -1)) {
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

					txtResult.setText("");
					rs = stmt.executeQuery("select * from CampingCars;");
					while (rs.next()) {
						int carID = rs.getInt(1);
						String str = carID + " " + rs.getString(2) + " " + rs.getInt(4) + " " + rs.getString(5) + " "
								+ rs.getInt(7) + " " + rs.getInt(7) + "\n";

						if (accessibleCarID.contains(carID))
							txtResult.append(str);
					}
					
					listPn.removeAll();
					listPn.add(txtResult);
					listPn.revalidate();
					listPn.repaint();
					
					System.out.println(txtResult.getText());

				} catch (Exception e2) {
					System.out.println("쿼리 읽기 실패 :" + e2);
				}

			}

		});

		chooseCustomerBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					conDB();
					stmt = con.createStatement();

					new newWindow();

				} catch (Exception e2) {
					System.out.println("쿼리 읽기 실패 :" + e2);
				}

			}

		});
		
		
		rentBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					conDB();
					stmt = con.createStatement();
					
						
					
					
					
				} catch (Exception e2) {
					System.out.println("쿼리 읽기 실패 :" + e2);
				}
				
			}
			
		});

	}

}

class newWindow extends JFrame {
	newWindow() {
		setTitle("새로 띄운 창");

		JPanel NewWindowContainer = new JPanel();
		setContentPane(NewWindowContainer);

		JLabel NewLabel = new JLabel("새 창을 띄우기");

		NewWindowContainer.add(NewLabel);

		setSize(300, 100);
		setResizable(false);
		setVisible(true);
	}
}
