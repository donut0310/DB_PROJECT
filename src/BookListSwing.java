
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class BookListSwing extends JFrame implements ActionListener {
	JButton btnReset, btnInput, btnSearch1, btnSearch2, btnSearch3;
	JButton lbtn;
	JTextField ltf1,ltf2;
	
	JTextArea txtResult;
	
	JPanel lp1,lp2,lp3;
	JPanel rp1, rp2;
	JPanel lentCompanyPn;
	JTextField tf1, tf2, tf3, tf4, tf5;
	JLabel llabel;
	
//	
	JFrame customFrame,adminFrame;
	JButton custom, admin;
//	
	Connection con;
	Statement stmt;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public void tfReset() {
		tf1.setText("�ֹ���ȣ");
		tf2.setText("����ȣ");
		tf3.setText("å��ȣ");
		tf4.setText("�ǸŰ���");
		tf5.setText("�ֹ���¥(���ڸ�)");
	}

	public BookListSwing() {
		super("16011062 ������");
		layInit();
		conDB();
		GridLayout grid = new GridLayout(1,2);
		setLayout(grid);
		setVisible(true);
		setBounds(200, 200, 550, 550); // ������ġ,������ġ,���α���,���α���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void layInit() {
		custom = new JButton("�Ϲ�");
		admin = new JButton("������");
		
		custom.addActionListener(this);
		admin.addActionListener(this);
		
		add(custom);
		add(admin);
		
		
//		btnReset = new JButton("�ʱ�ȭ");
//		btnInput = new JButton("�Է�1");
//		btnSearch1 = new JButton("�˻�1");
//		btnSearch2 = new JButton("�˻�2");
//		btnSearch3 = new JButton("�˻�3");
//
//		tf1 = new JTextField("�ֹ���ȣ", 6);
//		tf2 = new JTextField("����ȣ", 6);
//		tf3 = new JTextField("å��ȣ", 5);
//		tf4 = new JTextField("�ǸŰ���", 8);
//		tf5 = new JTextField("�ֹ���¥(���ڸ�)", 12);
//
//		txtResult = new JTextArea();
//		
////		panels of left
//		lp1 = new JPanel();
//		lp2 = new JPanel();
//		lp3 = new JPanel();
//		
//		llabel = new JLabel("�뿩���� ����Ʈ");
//		
//		lbtn = new JButton("�Է�");
//		ltf1 = new JTextField("�뿩 ������",10);
//		ltf2 = new JTextField("�뿩 �Ⱓ",4);
//		
//		lp1.add(llabel);
//		
//		lp3.add(lbtn);
//		lp3.add(ltf1);
//		lp3.add(ltf2);

//		add(leftPn);
//		add("North",lp1);
//		add("Center",lp2);
//		add("South",lp3);
		
//		right pannel
//		rightPn = new JPanel();
//		�뿩ȸ��
		
//		ķ��ī
//		��
//		�����
		

//		txtResult.setEditable(false);
//		JScrollPane scrollPane = new JScrollPane(txtResult);
//		add("North", pn1);
//		add("South", pn2);
//		add("Center", scrollPane);
//		btnSearch1.addActionListener(this);
//		btnSearch2.addActionListener(this);
//		btnSearch3.addActionListener(this);
//		btnInput.addActionListener(this);
//		btnReset.addActionListener(this);
	}

	public void conDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// string ���� Ȯ�� �Լ�
	public static boolean isNumeric(String string) {
		boolean isNumber = false;
		try {
			Integer.parseInt(string);
			isNumber = true;
		} catch (NumberFormatException e) {

		}
		return isNumber;
	}

	// string �ùٸ� ��¥ Ȯ�� �Լ�
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
		try { /* �����ͺ��̽��� �����ϴ� ���� */
			System.out.println("�����ͺ��̽� ���� �غ�...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("�����ͺ��̽� ���� ����");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			stmt = con.createStatement();

			String querySafeModeOff = "SET SQL_SAFE_UPDATES=0;";
			String queryReset1 = "DELETE FROM orders";
			String queryReset2 = "DELETE FROM book";
			String queryReset3 = "DELETE FROM customer";
			String queryInput = "";
			String query1 = "SELECT * FROM Book ";
			String query2 = "SELECT * FROM Orders ";
			String query3 = "SELECT * FROM Customer ";

			if (e.getSource() == btnReset) {
				tfReset();
				txtResult.setText("		             �ʱ�ȭ �Ϸ�");
				stmt.executeUpdate(querySafeModeOff);
				stmt.executeUpdate(queryReset1);
				stmt.executeUpdate(queryReset2);
				stmt.executeUpdate(queryReset3);

				// book 20��
				queryInput = "INSERT INTO Book VALUES(1, '�౸�� ����', '�½�����', 7000)," + "(2, '�౸�ƴ� ����', '������', 13000),"
						+ "(3, '�౸�� ����', '���ѹ̵��', 22000)," + "(4, '���� ���̺�', '���ѹ̵��', 35000),"
						+ "(5, '�ǰ� ����', '�½�����', 8000)," + "(6, '���� �ܰ躰���', '�½�����', 6000),"
						+ "(7, '�߱��� �߾�', '�̻�̵��', 20000)," + "(8, '�߱��� ��Ź��', '�̻�̵��', 13000),"
						+ "(9, '�ø��� �̾߱�', '�Ｚ��', 7500)," + "(10, 'Olympic Champions', 'Pearson', 13000),"
						+ "(11, 'The Having', '��������', 14400)," + "(12, '������ ����', '���е���', 12150),"
						+ "(13, '�佺Ʈ', '������', 11700)," + "(14, '1cm ���̺�', '��ī', 12150)," + "(15, '�Ƹ��', 'â��',10800 ),"
						+ "(16, '������ �ð��� �ֱ�� �ߴ�', '��������', 12420)," + "(17, '������ �� 1', '��ī', 14400),"
						+ "(18, '��11ȸ �����۰��� ������ǰ��', '���е���', 5500)," + "(19, '� ����', '���е���', 8550),"
						+ "(20, '�˻���', 'â��', 10800);";
				stmt.executeUpdate(queryInput);

				// customer 10��
				queryInput = "INSERT INTO Customer VALUES (1, '������', '���� ��ü��Ÿ', '000-5000-0001'),"
						+ "(2, '�迬��', '���ѹα� ����', '000-6000-0001')," + "(3, '��̶�', '���ѹα� ������', '000-7000-0001'),"
						+ "(4, '�߽ż�', '�̱� Ŭ������', '000-8000-0001')," + "(5, '�ڼ���', '���ѹα� ����',  NULL),"
						+ "(6, '������', '���ѹα� ����', '000-2020-1010')," + "(7, '��¥��', '�߱� ����¡',  '090-8043-2939'),"
						+ "(8, '������', '��Ż���� �θ�',  '103-3942-1992')," + "(9, '��濵', '���ѹα� ���ֵ�', '010-3030-2014'),"
						+ "(10, '�����', '�̱� ������',  '040-2030-1010');";
				stmt.executeUpdate(queryInput);

				// orders 20��
				queryInput = "INSERT INTO Orders VALUES (1, 1, 1, 6000, STR_TO_DATE('2014-07-01','%Y-%m-%d')), "
						+ "(2, 1, 3, 21000, STR_TO_DATE('2014-07-03','%Y-%m-%d')),"
						+ "(3, 2, 5, 8000, STR_TO_DATE('2014-07-03','%Y-%m-%d')), "
						+ "(4, 3, 6, 6000, STR_TO_DATE('2014-07-04','%Y-%m-%d')), "
						+ "(5, 4, 7, 20000, STR_TO_DATE('2014-07-05','%Y-%m-%d')),"
						+ "(6, 1, 2, 12000, STR_TO_DATE('2014-07-07','%Y-%m-%d')),"
						+ "(7, 4, 8, 13000, STR_TO_DATE( '2014-07-07','%Y-%m-%d')),"
						+ "(8, 3, 10, 12000, STR_TO_DATE('2014-07-08','%Y-%m-%d')), "
						+ "(9, 2, 10, 7000, STR_TO_DATE('2014-07-09','%Y-%m-%d')), "
						+ "(10, 3, 8, 13000, STR_TO_DATE('2014-07-10','%Y-%m-%d')),"
						+ "(11, 5, 5, 7500, STR_TO_DATE('2014-07-14','%Y-%m-%d')),"
						+ "(12, 2, 13, 11000, STR_TO_DATE('2014-07-14','%Y-%m-%d')),"
						+ "(13, 7, 15, 9000, STR_TO_DATE('2014-08-15','%Y-%m-%d')),"
						+ "(14, 1, 17, 13000, STR_TO_DATE('2014-08-20','%Y-%m-%d')),"
						+ "(15, 9, 6, 5000, STR_TO_DATE('2014-09-10','%Y-%m-%d')),"
						+ "(16, 10, 18, 5500, STR_TO_DATE('2014-09-10','%Y-%m-%d')),"
						+ "(17, 8, 20, 10000, STR_TO_DATE('2014-09-16','%Y-%m-%d')),"
						+ "(18, 6, 16, 11000, STR_TO_DATE('2014-09-20','%Y-%m-%d')),"
						+ "(19, 4, 1, 6000, STR_TO_DATE('2014-10-10','%Y-%m-%d')),"
						+ "(20, 8, 11, 13500, STR_TO_DATE('2014-11-10','%Y-%m-%d'));";
				stmt.executeUpdate(queryInput);

			} else if (e.getSource() == btnInput) {

				String tf1Text = tf1.getText();
				String tf2Text = tf2.getText();
				String tf3Text = tf3.getText();
				String tf4Text = tf4.getText();
				String tf5Text = tf5.getText();

				tfReset();

				String warningText = "";
				boolean inputErrorCheck = false;

				if (!isNumeric(tf1Text)) {
					warningText = warningText.concat("�ֹ� ��ȣ�� ���ڿ��� �մϴ�.\n");
					inputErrorCheck = true;
				} else {
					// orderid �ߺ� Ȯ��
					rs = stmt.executeQuery("SELECT orderid FROM orders;");
					while (rs.next()) {
						Integer orderid = rs.getInt(1);
						if (orderid == Integer.parseInt(tf1Text)) {
							warningText = warningText.concat("�����ϴ� �ֹ� ��ȣ�Դϴ�.\n");
							inputErrorCheck = true;
							break;
						}
					}
				}

				if (!isNumeric(tf2Text)) {
					warningText = warningText.concat("�˸´� �� ��ȣ ������ �ƴմϴ�.\n");
					inputErrorCheck = true;
				}else {
					// custid �����ϴ��� Ȯ��
					rs = stmt.executeQuery("SELECT custid FROM customer;");
					boolean isExist = false;
					while (rs.next()) {
						Integer custid = rs.getInt(1);
						if (custid == Integer.parseInt(tf2Text)) {
							isExist = true;
							break;
						}
					}
					if(!isExist) {
						warningText = warningText.concat("�������� �ʴ� �� ��ȣ�Դϴ�.\n");
						inputErrorCheck = true;
					}
				}
				
				
				if (!isNumeric(tf3Text)) {
					warningText = warningText.concat("�˸´� å ��ȣ ������ �ƴմϴ�.\n");
					inputErrorCheck = true;
				}else {
					// bookid �����ϴ��� Ȯ��
					rs = stmt.executeQuery("SELECT bookid FROM book;");
					boolean isExist = false;
					while (rs.next()) {
						Integer bookid = rs.getInt(1);
						if (bookid == Integer.parseInt(tf3Text)) {
							isExist = true;
							break;
						}
					}
					if(!isExist) {
						warningText = warningText.concat("�������� �ʴ� å ��ȣ�Դϴ�.\n");
						inputErrorCheck = true;
					}
				}
				
				
				if (!isNumeric(tf4Text)) {
					warningText = warningText.concat("�˸´� �Ǹ� ���� ������ �ƴմϴ�.\n");
					inputErrorCheck = true;
				}
				
				if (!isNumeric(tf5Text) || !isDate(tf5Text)) {
					warningText = warningText.concat("�˸´� �ֹ� ���� ������ �ƴմϴ�.\n");
					inputErrorCheck = true;
				}
				

				if (inputErrorCheck == true) {
					// ���â ����
					JOptionPane.showMessageDialog(null, warningText, "�߸��� �Է� �߻�", JOptionPane.ERROR_MESSAGE);
					System.out.println(warningText);
				} else {
					// queryInput ������ ����
					queryInput = "INSERT INTO ORDERS(orderid, custid, bookid, saleprice, orderdate) VALUES(" + tf1Text
							+ ',' + tf2Text + ',' + tf3Text + ',' + tf4Text + ',' + tf5Text + ");";
					stmt.executeUpdate(queryInput);

					// ������Ʈ �� orders ����
					btnSearch2.doClick();
				}

			} else if (e.getSource() == btnSearch1) {
				tfReset();
				txtResult.setText("");
				txtResult.setText("bookid	   bookname  	    	publisher	   price\n");
				rs = stmt.executeQuery(query1);
				while (rs.next()) {
					String str = rs.getInt(1) + "\t" + rs.getString(2) + (rs.getString(2).length() > 11 ? "\t" : "\t\t")
							+ rs.getString(3) + "\t" + rs.getInt(4) + "\n";
					txtResult.append(str);
				}

			} else if (e.getSource() == btnSearch2) {
				tfReset();
				txtResult.setText("");
				txtResult.setText("orderid	   custid 	  bookid  	 saleprice 	  orderdate\n");
				rs = stmt.executeQuery(query2);
				while (rs.next()) {
					String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t"
							+ rs.getString(5) + "\n";
					txtResult.append(str);
				}

			} else if (e.getSource() == btnSearch3) {
				tfReset();
				txtResult.setText("");
				txtResult.setText("custid  	 name	   address 		     phone\n");
				rs = stmt.executeQuery(query3);
				while (rs.next()) {
					String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t\t"
							+ rs.getString(4) + "\n";
					txtResult.append(str);
				}
			} else if(e.getSource() == custom) {
				CustomerFrame a = new CustomerFrame();
			} else if(e.getSource() == admin){
				AdminFrame b = new AdminFrame();
			}

		} catch (Exception e2) {
			System.out.println("���� �б� ���� :" + e2);
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

	public static void main(String[] args) {
		new BookListSwing();
	}
}