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

public class ServiceCenters {
	Connection con;
	Statement stmt = null;
	ResultSet rs = null;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public ServiceCenters(JPanel btnPn) {
		init(btnPn);
	}

	public void init(JPanel btnPn) {
		JButton submitBtn = new JButton("입력");
		JButton updateBtn = new JButton("변경");
		JButton deleteBtn = new JButton("삭제");

		JTextField input1 = new JTextField("캠핑카 정비소 ID");
		JTextField input2 = new JTextField("정비소 명");
		JTextField input3 = new JTextField("정비소 주소");
		JTextField input4 = new JTextField("정비소 전화번호");
		JTextField input5 = new JTextField("담당자 이름");
		JTextField input6 = new JTextField("담당자 이메일");

		JTextField update1 = new JTextField("캠핑카 정비소 ID");
		JTextField update2 = new JTextField("정비소 명");
		JTextField update3 = new JTextField("정비소 주소");
		JTextField update4 = new JTextField("정비소 전화번호");
		JTextField update5 = new JTextField("담당자 이름");
		JTextField update6 = new JTextField("담당자 이메일");

		JTextField delete1 = new JTextField("번호");
		JTextField delete2 = new JTextField("DISABLE");
		JTextField delete3 = new JTextField("DISABLE");
		JTextField delete4 = new JTextField("DISABLE");
		JTextField delete5 = new JTextField("DISABLE");
		JTextField delete6 = new JTextField("DISABLE");

		btnPn.setLayout(new GridLayout(3, 6));

		btnPn.add(submitBtn);
		btnPn.add(input1);
		btnPn.add(input2);
		btnPn.add(input3);
		btnPn.add(input4);
		btnPn.add(input5);
		btnPn.add(input6);
		input1.disable();

		btnPn.add(updateBtn);
		btnPn.add(update1);
		btnPn.add(update2);
		btnPn.add(update3);
		btnPn.add(update4);
		btnPn.add(update5);
		btnPn.add(update6);

		btnPn.add(deleteBtn);
		btnPn.add(delete1);
		btnPn.add(delete2);
		btnPn.add(delete3);
		btnPn.add(delete4);
		btnPn.add(delete5);
		btnPn.add(delete6);
		delete2.disable();
		delete3.disable();
		delete4.disable();
		delete5.disable();
		delete6.disable();

		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (input2.getText());
				String address = (input3.getText());
				String phone = (input4.getText());
				String keeperName = (input5.getText());
				String keeperEmail = (input6.getText());
				try {
					conDB();
					stmt = con.createStatement();
					String str = "insert into ServiceCenters (name,address,phone,keeperName,keeperEmail) VALUES (" + "'"
							+ name + "','" + address + "','" + phone + "','" + keeperName + "','" + keeperEmail + "')";
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
				String address = (update3.getText());
				String phone = (update4.getText());
				String keeperName = (update5.getText());
				String keeperEmail = (update6.getText());
				try {
					conDB();
					stmt = con.createStatement();
					String str = "update ServiceCenters set name = " + "'" + name + "'," + "address = " + "'" + address
							+ "'," + "phone = " + "'" + phone + "'," + "keeperName = " + "'" + keeperName + "',"
							+ "keeperEmail = " + "'" + keeperEmail + "' where id = " + id;
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
					stmt.executeUpdate("delete from ServiceCenters where id = " + id);
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
