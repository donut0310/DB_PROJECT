
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminFrame implements ActionListener {
	   JButton companyBtn, campingCarsBtn, customersBtn, serviceCentersBtn;
	   JPanel searchBtnPn, listPn, btnPn;
	   GridLayout btns;
	   
	   Connection con;
		Statement stmt;
		ResultSet rs;
		String Driver = "";
		String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
		String userid = "madang";
		String pwd = "madang";
		
	   public AdminFrame() {
		   init();
	   }
	   
	   public void init() {
		   JFrame adminFrame = new JFrame();
		   adminFrame.setVisible(true);
		   adminFrame.setBounds(200, 200, 1200, 550); // ������ġ,������ġ,���α���,���α���
		   
		   searchBtnPn = new JPanel();
		   listPn = new JPanel();
		   btnPn = new JPanel();
		   		   
		   companyBtn = new JButton("�뿩 ȸ��");
		   campingCarsBtn = new JButton("ķ�� ī");
		   customersBtn = new JButton("��");
		   serviceCentersBtn = new JButton("�����");
		   
		   companyBtn.addActionListener(this);
		   campingCarsBtn.addActionListener(this);
		   customersBtn.addActionListener(this);
		   serviceCentersBtn.addActionListener(this);

		   JButton s1 = new JButton("search 1");
		   JButton s2 = new JButton("search 2");
		   JButton s3 = new JButton("search 3");
		   JButton s4 = new JButton("search 4");
		   
		   searchBtnPn.add(companyBtn);
		   searchBtnPn.add(campingCarsBtn);
		   searchBtnPn.add(customersBtn);
		   searchBtnPn.add(serviceCentersBtn);
		   searchBtnPn.add(s1);
		   searchBtnPn.add(s2);
		   searchBtnPn.add(s3);
		   searchBtnPn.add(s4);
		   
		   adminFrame.add("North",searchBtnPn);
		   adminFrame.add("Center",listPn);
		   adminFrame.add("South", btnPn);
	   }
	   
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
				
				if(e.getSource() == companyBtn) {
					listPn.removeAll();
	            	btnPn.removeAll();
	            	Companies a = new Companies(listPn, btnPn);
				} 
				else if(e.getSource() == campingCarsBtn){
					listPn.removeAll();
	            	btnPn.removeAll();
	            	CampingCars a = new CampingCars(listPn, btnPn);
				}
				else if(e.getSource() == customersBtn){
					listPn.removeAll();
	            	btnPn.removeAll();
	            	Customers a = new Customers(listPn, btnPn);
				}
				else if(e.getSource() == serviceCentersBtn){
					listPn.removeAll();
	            	btnPn.removeAll();
	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
				}
//				else if(e.getSource() == s1){
//					listPn.removeAll();
//	            	btnPn.removeAll();
//	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
//				}
//				else if(e.getSource() == s2){
//					listPn.removeAll();
//	            	btnPn.removeAll();
//	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
//				}
//				else if(e.getSource() == s3){
//					listPn.removeAll();
//	            	btnPn.removeAll();
//	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
//				}
//				else if(e.getSource() == s4){
//					listPn.removeAll();
//	            	btnPn.removeAll();
//	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
//				}
				

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

}