import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CampingCars {
	Connection con;
	Statement stmt = null;
	ResultSet rs = null;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public CampingCars(JPanel btnPn) {
		init(btnPn);
	}

	public void init(JPanel btnPn) {
		JButton submitBtn = new JButton("입력");
		JButton updateBtn = new JButton("변경");
		JButton deleteBtn = new JButton("삭제");

		JTextField input1 = new JTextField("번호");
		JTextField input2 = new JTextField("캠핑카 이름");
		JTextField input3 = new JTextField("캠핑카 차량번호");
		JTextField input4 = new JTextField("캠핑카 승차 인원 수");
		JTextField input5 = new JTextField("캠핑카 제조 회사");
		JTextField input6 = new JTextField("캠핑카 제조 연도");
		JTextField input7 = new JTextField("캠핑카 누적 주횡거리");
		JTextField input8 = new JTextField("캠핑카 대여 비용");
		JTextField input9 = new JTextField("캠핑카 대여 회사 ID");
		JTextField input10 = new JTextField("캠핑카 등록 일자");

		JTextField update1 = new JTextField("번호");
		JTextField update2 = new JTextField("캠핑카 이름");
		JTextField update3 = new JTextField("캠핑카 차량번호");
		JTextField update4 = new JTextField("캠핑카 승차 인원 수");
		JTextField update5 = new JTextField("캠핑카 제조 회사");
		JTextField update6 = new JTextField("캠핑카 제조 연도");
		JTextField update7 = new JTextField("캠핑카 누적 주횡거리");
		JTextField update8 = new JTextField("캠핑카 대여 비용");
		JTextField update9 = new JTextField("캠핑카 대여 회사 ID");
		JTextField update10 = new JTextField("캠핑카 등록 일자");

		JTextField delete1 = new JTextField("번호");
		JTextField delete2 = new JTextField("DISABLE");
		JTextField delete3 = new JTextField("DISABLE");
		JTextField delete4 = new JTextField("DISABLE");
		JTextField delete5 = new JTextField("DISABLE");
		JTextField delete6 = new JTextField("DISABLE");
		JTextField delete7 = new JTextField("DISABLE");
		JTextField delete8 = new JTextField("DISABLE");
		JTextField delete9 = new JTextField("DISABLE");
		JTextField delete10 = new JTextField("DISABLE");

		btnPn.setLayout(new GridLayout(3, 11));

		btnPn.add(submitBtn);
		btnPn.add(input1);
		btnPn.add(input2);
		btnPn.add(input3);
		btnPn.add(input4);
		btnPn.add(input5);
		btnPn.add(input6);
		btnPn.add(input7);
		btnPn.add(input8);
		btnPn.add(input9);
		btnPn.add(input10);
		input1.disable();

		btnPn.add(updateBtn);
		btnPn.add(update1);
		btnPn.add(update2);
		btnPn.add(update3);
		btnPn.add(update4);
		btnPn.add(update5);
		btnPn.add(update6);
		btnPn.add(update7);
		btnPn.add(update8);
		btnPn.add(update9);
		btnPn.add(update10);

		btnPn.add(deleteBtn);
		btnPn.add(delete1);
		btnPn.add(delete2);
		btnPn.add(delete3);
		btnPn.add(delete4);
		btnPn.add(delete5);
		btnPn.add(delete6);
		btnPn.add(delete7);
		btnPn.add(delete8);
		btnPn.add(delete9);
		btnPn.add(delete10);

		delete2.disable();
		delete3.disable();
		delete4.disable();
		delete5.disable();
		delete6.disable();
		delete7.disable();
		delete8.disable();
		delete9.disable();
		delete10.disable();

		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (input2.getText());
				String carNumber = (input3.getText());
				int passengers = Integer.parseInt(input4.getText());
				String madeCp = (input5.getText());
				int madeYear = Integer.parseInt(input6.getText());
				int totalMileage = Integer.parseInt(input7.getText());
				int rentCost = Integer.parseInt(input8.getText());
				int rentCpID = Integer.parseInt(input9.getText());
				String carRegisterDate = (input10.getText());
				try {
					conDB();
					stmt = con.createStatement();
					String str = "insert into CampingCars" + "(name,carNumber,passengers,madeCp,madeYear,"
							+ "totalMileage,rentCost,rentCpID,carRegisterDate) VALUES (" + "'" + name + "','"
							+ carNumber + "','" + passengers + "','" + madeCp + "','" + madeYear + "','" + totalMileage
							+ "','" + rentCost + "','" + rentCpID + "','" + carRegisterDate + "')";
					stmt.executeUpdate(str);
				} catch (Exception e2) {
					System.out.println();
					System.out.println("쿼리 읽기 실패 :" + e2);
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
			}

		});
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(update1.getText());
				String name = (update2.getText());
				String carNumber = (update3.getText());
				int passengers = Integer.parseInt(update4.getText());
				String madeCp = (update5.getText());
				int madeYear = Integer.parseInt(update6.getText());
				int totalMileage = Integer.parseInt(update7.getText());
				int rentCost = Integer.parseInt(update8.getText());
				int rentCpID = Integer.parseInt(update9.getText());
				String carRegisterDate = (update10.getText());
				try {
					conDB();
					stmt = con.createStatement();
					String str = "update CampingCars set " + "name = " + "'" + name + "'," + "carNumber = " + "'"
							+ carNumber + "'," + "passengers = " + "'" + passengers + "'," + "madeCp = " + "'" + madeCp
							+ "'," + "madeYear = " + "'" + madeYear + "'," + "totalMileage = " + "'" + totalMileage
							+ "'," + "rentCost = " + "'" + rentCost + "'," + "rentCpID = " + "'" + rentCpID + "',"
							+ "carRegisterDate = " + "'" + carRegisterDate + "' where id = " + id;
					// System.out.println(str);
					stmt.executeUpdate(str);
					// UPDATE 테이블명 SET 필드명 = "바꿀 값" WHERE 필드명= "조건 값"

				} catch (Exception e2) {
					System.out.println();
					System.out.println("쿼리 읽기 실패 :" + e2);
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
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(delete1.getText());
				try {
					conDB();
					stmt = con.createStatement();
					stmt.executeUpdate("delete from CampingCars where id = " + id);
				} catch (Exception e2) {
					System.out.println();
					System.out.println("쿼리 읽기 실패 :" + e2);
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
