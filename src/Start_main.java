
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Start_main extends JFrame implements ActionListener {
//	
	JButton custom, admin;
//	
	// static Connection con;

	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public Start_main() {
		super("캠핑카 대여 시스템");
		// conDB();
		layInit();
		super.setLocationRelativeTo(null);

		setSize(400, 200);
		GridLayout grid = new GridLayout(1, 2);
		setLayout(grid);
		setVisible(true);
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
		if (e.getSource() == custom) {
			new CustomerFrame();
		}

		else if (e.getSource() == admin) {
			new AdminFrame();
		}
	}

	public static void main(String[] args) {
		new Start_main();
	}
}