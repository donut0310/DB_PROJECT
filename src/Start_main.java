
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
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
	JButton custom, admin;
//	
	//static Connection con;
	
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
	
	public Start_main() {
		super("캠핑카 대여 시스템");
		//conDB();
		layInit();
		Dimension frameSize = getSize();
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
		
		setSize(200,200);
		GridLayout grid = new GridLayout(1,2);
		setLayout(grid);
		setVisible(true);
		// setBounds(750, 400, 200, 200); // 가로위치,세로위치,가로길이,세로길이
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