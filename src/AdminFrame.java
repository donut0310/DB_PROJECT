
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminFrame {
	   JButton companyBtn, campingCarsBtn, customersBtn, serviceCentersBtn;
	   JPanel searchBtnPn, listPn, btnPn;
	   GridLayout btns;
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
		   
		   companyBtn.addActionListener( new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	            	listPn.removeAll();
	            	btnPn.removeAll();
	            	Companies a = new Companies(listPn, btnPn);
	            }
	        });
		   campingCarsBtn.addActionListener( new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	            	listPn.removeAll();
	            	btnPn.removeAll();
	            	CampingCars a = new CampingCars(listPn, btnPn);
	            }
	        });
		   customersBtn.addActionListener( new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	            	listPn.removeAll();
	            	btnPn.removeAll();
	            	Customers a = new Customers(listPn, btnPn);
	            }
	        });
		   serviceCentersBtn.addActionListener( new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	            	listPn.removeAll();
	            	btnPn.removeAll();
	            	ServiceCenters a = new ServiceCenters(listPn, btnPn);
	            }
	        });

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

}