
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
public class Start_main extends JFrame implements ActionListener {
//	
	JFrame customFrame,adminFrame;
	JButton custom, admin;
//	
	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
	
	public Start_main() {
		super("검성듀오");
		layInit();
		conDB();
		GridLayout grid = new GridLayout(1,2);
		setLayout(grid);
		setVisible(true);
		setBounds(200, 200, 550, 550); // 가로위치,세로위치,가로길이,세로길이
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void layInit() {
		custom = new JButton("일반");
		admin = new JButton("관리자");
		
		custom.addActionListener(this);
		admin.addActionListener(this);
		add(custom);
		add(admin);
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
			Start_main.con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		

	}

	// string 정수 확인 함수
	public static boolean isNumeric(String string) {
		boolean isNumber = false;
		try {
			Integer.parseInt(string);
			isNumber = true;
		} catch (NumberFormatException e) {

		}
		return isNumber;
	}

	// string 올바른 날짜 확인 함수
	public static boolean isDate(String string) {
		String format = "yyyyMMdd";
		SimpleDateFormat dateFormatParser = new SimpleDateFormat(format, Locale.KOREA);
		dateFormatParser.setLenient(false);
		try {
			dateFormatParser.parse(string);
			return true;
		} catch (Exception Ex) {
			return false;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		//  try{
			
		// 	stmt = con.createStatement();
				
		// 	stmt.executeUpdate("drop database if exists madang");
		// 	stmt.executeUpdate("create database madang");
		// 	stmt.executeQuery("use madang");
			if(e.getSource() == custom) {
				CustomerFrame a = new CustomerFrame();
			} 
			else if(e.getSource() == admin){
				AdminFrame b = new AdminFrame();
			}
	// } catch (Exception e2) {
	//  	System.out.println("쿼리 읽기 실패 :" + e2);
	// 	} 
	// //finally {
	// 	try {
	// 	   if (rs != null)
	// 		  rs.close();
	// 	   if (stmt != null)
	// 		  stmt.close();
	// 	   if (con != null)
	// 		  con.close();
	// 	} catch (Exception e3) {
	// 	   // TODO: handle exception
	// 	}
	//  }
}

	public static void main(String[] args) {
		new Start_main();
	}
}