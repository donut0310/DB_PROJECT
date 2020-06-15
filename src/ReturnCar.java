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

public class ReturnCar {
	Connection con;
	Statement stmt = null;
	ResultSet rs = null;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public ReturnCar(JPanel btnPn) {
		init(btnPn);
	}

	public void init(JPanel btnPn) {
		JButton deleteBtn = new JButton("반환");

		JTextField delete1 = new JTextField("리스트 번호");

		JTextField input1 = new JTextField("캠핑카 등록 ID");
		JTextField input2 = new JTextField("앞부분 설명");
		JTextField input3 = new JTextField("왼쪽 설명");
		JTextField input4 = new JTextField("오른쪽 설명");
		JTextField input5 = new JTextField("뒤쪽 설명");
		JTextField input6 = new JTextField("수리필요 여부");

		btnPn.setLayout(new GridLayout(1, 8));

		btnPn.add(deleteBtn);
		btnPn.add(delete1);
		btnPn.add(input1);
		btnPn.add(input2);
		btnPn.add(input3);
		btnPn.add(input4);
		btnPn.add(input5);
		btnPn.add(input6);

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rentInfoID = Integer.parseInt(delete1.getText());
				int carID = Integer.parseInt(input1.getText());
				String frontInfor = (input2.getText());
				String leftInfor = (input3.getText());
				String rightInfor = (input4.getText());
				String backInfor = (input5.getText());
				String repairRequired = (input6.getText());
				try {
					conDB();
					stmt = con.createStatement();

					stmt.executeUpdate(
							"insert into CarCheckList (rentInfoID,carID,frontInfor,leftInfor,rightInfor,backInfor,repairRequired) VALUES ("
									+ rentInfoID + "," + carID + ",'" + frontInfor + "','" + leftInfor + "','"
									+ rightInfor + "','" + backInfor + "','" + repairRequired + "')");
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