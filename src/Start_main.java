
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
	//static Connection con;
	
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
	
	public Start_main() {
		super("검성듀오");
		//conDB();
		layInit();
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

	

	// string 정수 확인 함수
	// public static boolean isNumeric(String string) {
	// 	boolean isNumber = false;
	// 	try {
	// 		Integer.parseInt(string);
	// 		isNumber = true;
	// 	} catch (NumberFormatException e) {

	// 	}
	// 	return isNumber;
	// }

	// // string 올바른 날짜 확인 함수
	// public static boolean isDate(String string) {
	// 	String format = "yyyyMMdd";
	// 	SimpleDateFormat dateFormatParser = new SimpleDateFormat(format, Locale.KOREA);
	// 	dateFormatParser.setLenient(false);
	// 	try {
	// 		dateFormatParser.parse(string);
	// 		return true;
	// 	} catch (Exception Ex) {
	// 		return false;
	// 	}

	// }

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == custom) {
				new CustomerFrame();
			} 
			else if(e.getSource() == admin){
				new AdminFrame();
			}
	} 

	public static void main(String[] args) {
		new Start_main();
	}
}