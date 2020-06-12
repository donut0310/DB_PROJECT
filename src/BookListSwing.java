
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
		tf1.setText("주문번호");
		tf2.setText("고객번호");
		tf3.setText("책번호");
		tf4.setText("판매가격");
		tf5.setText("주문날짜(숫자만)");
	}

	public BookListSwing() {
		super("16011062 김진성");
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
		
		
//		btnReset = new JButton("초기화");
//		btnInput = new JButton("입력1");
//		btnSearch1 = new JButton("검색1");
//		btnSearch2 = new JButton("검색2");
//		btnSearch3 = new JButton("검색3");
//
//		tf1 = new JTextField("주문번호", 6);
//		tf2 = new JTextField("고객번호", 6);
//		tf3 = new JTextField("책번호", 5);
//		tf4 = new JTextField("판매가격", 8);
//		tf5 = new JTextField("주문날짜(숫자만)", 12);
//
//		txtResult = new JTextArea();
//		
////		panels of left
//		lp1 = new JPanel();
//		lp2 = new JPanel();
//		lp3 = new JPanel();
//		
//		llabel = new JLabel("대여가능 리스트");
//		
//		lbtn = new JButton("입력");
//		ltf1 = new JTextField("대여 시작일",10);
//		ltf2 = new JTextField("대여 기간",4);
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
//		대여회사
		
//		캠핑카
//		고객
//		정비소
		

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
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
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
				txtResult.setText("		             초기화 완료");
				stmt.executeUpdate(querySafeModeOff);
				stmt.executeUpdate(queryReset1);
				stmt.executeUpdate(queryReset2);
				stmt.executeUpdate(queryReset3);

				// book 20개
				queryInput = "INSERT INTO Book VALUES(1, '축구의 역사', '굿스포츠', 7000)," + "(2, '축구아는 여자', '나무수', 13000),"
						+ "(3, '축구의 이해', '대한미디어', 22000)," + "(4, '골프 바이블', '대한미디어', 35000),"
						+ "(5, '피겨 교본', '굿스포츠', 8000)," + "(6, '역도 단계별기술', '굿스포츠', 6000),"
						+ "(7, '야구의 추억', '이상미디어', 20000)," + "(8, '야구를 부탁해', '이상미디어', 13000),"
						+ "(9, '올림픽 이야기', '삼성당', 7500)," + "(10, 'Olympic Champions', 'Pearson', 13000),"
						+ "(11, 'The Having', '수오서재', 14400)," + "(12, '여행의 이유', '문학동네', 12150),"
						+ "(13, '페스트', '나무수', 11700)," + "(14, '1cm 다이빙', '피카', 12150)," + "(15, '아몬드', '창비',10800 ),"
						+ "(16, '나에게 시간을 주기로 했다', '수오서재', 12420)," + "(17, '내일의 부 1', '피카', 14400),"
						+ "(18, '제11회 젊은작가상 수상작품집', '문학동네', 5500)," + "(19, '어린 왕자', '문학동네', 8550),"
						+ "(20, '알사탕', '창비', 10800);";
				stmt.executeUpdate(queryInput);

				// customer 10개
				queryInput = "INSERT INTO Customer VALUES (1, '박지성', '영국 맨체스타', '000-5000-0001'),"
						+ "(2, '김연아', '대한민국 서울', '000-6000-0001')," + "(3, '장미란', '대한민국 강원도', '000-7000-0001'),"
						+ "(4, '추신수', '미국 클리블랜드', '000-8000-0001')," + "(5, '박세리', '대한민국 대전',  NULL),"
						+ "(6, '김진성', '대한민국 서울', '000-2020-1010')," + "(7, '신짜오', '중국 베이징',  '090-8043-2939'),"
						+ "(8, '무지개', '이탈리아 로마',  '103-3942-1992')," + "(9, '허경영', '대한민국 제주도', '010-3030-2014'),"
						+ "(10, '문재경', '미국 워싱턴',  '040-2030-1010');";
				stmt.executeUpdate(queryInput);

				// orders 20개
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
					warningText = warningText.concat("주문 번호는 숫자여야 합니다.\n");
					inputErrorCheck = true;
				} else {
					// orderid 중복 확인
					rs = stmt.executeQuery("SELECT orderid FROM orders;");
					while (rs.next()) {
						Integer orderid = rs.getInt(1);
						if (orderid == Integer.parseInt(tf1Text)) {
							warningText = warningText.concat("존재하는 주문 번호입니다.\n");
							inputErrorCheck = true;
							break;
						}
					}
				}

				if (!isNumeric(tf2Text)) {
					warningText = warningText.concat("알맞는 고객 번호 형식이 아닙니다.\n");
					inputErrorCheck = true;
				}else {
					// custid 존재하는지 확인
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
						warningText = warningText.concat("존재하지 않는 고객 번호입니다.\n");
						inputErrorCheck = true;
					}
				}
				
				
				if (!isNumeric(tf3Text)) {
					warningText = warningText.concat("알맞는 책 번호 형식이 아닙니다.\n");
					inputErrorCheck = true;
				}else {
					// bookid 존재하는지 확인
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
						warningText = warningText.concat("존재하지 않는 책 번호입니다.\n");
						inputErrorCheck = true;
					}
				}
				
				
				if (!isNumeric(tf4Text)) {
					warningText = warningText.concat("알맞는 판매 가격 형식이 아닙니다.\n");
					inputErrorCheck = true;
				}
				
				if (!isNumeric(tf5Text) || !isDate(tf5Text)) {
					warningText = warningText.concat("알맞는 주문 일자 형식이 아닙니다.\n");
					inputErrorCheck = true;
				}
				

				if (inputErrorCheck == true) {
					// 경고창 띄우기
					JOptionPane.showMessageDialog(null, warningText, "잘못된 입력 발생", JOptionPane.ERROR_MESSAGE);
					System.out.println(warningText);
				} else {
					// queryInput 쿼리문 진행
					queryInput = "INSERT INTO ORDERS(orderid, custid, bookid, saleprice, orderdate) VALUES(" + tf1Text
							+ ',' + tf2Text + ',' + tf3Text + ',' + tf4Text + ',' + tf5Text + ");";
					stmt.executeUpdate(queryInput);

					// 업데이트 된 orders 띄우기
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

	public static void main(String[] args) {
		new BookListSwing();
	}
}