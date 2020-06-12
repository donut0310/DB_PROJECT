
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerFrame {

   Connection con;
   Statement stmt;
   ResultSet rs;
//   String Driver = "";
//   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
//   String userid = "madang";
//   String pwd = "madang";
   
   String customerSearchQuery = "select  from Customers";
   String accessibleSearchQuery = "";

   public CustomerFrame() {
      init();
   }

   public void init() {
      JFrame customFrame = new JFrame();
      customFrame.setVisible(true);
      customFrame.setBounds(200, 200, 550, 550); // ������ġ,������ġ,���α���,���α���

      JPanel northPn = new JPanel();
      JPanel listPn = new JPanel();
      JPanel southPn = new JPanel();

      JButton accessibleListBtn = new JButton("�뿩 ���� ����Ʈ ����");
      JTextField startDate = new JTextField("��Ʈ ���� ��", 11);
      JTextField rentPeriod = new JTextField("�뿩 �Ⱓ", 6);
      northPn.add(accessibleListBtn);
      northPn.add(startDate);
      northPn.add(rentPeriod);

      JButton chooseCustomerBtn = new JButton("�� ����");
      JTextField customerName = new JTextField("�� �̸�", 7);
      JTextField listNumber = new JTextField("����Ʈ ��ȣ", 7);
      JButton rentBtn = new JButton("�뿩 ��û");
      southPn.add(chooseCustomerBtn);
      southPn.add(customerName);
      southPn.add(listNumber);
      southPn.add(rentBtn);

      customFrame.add("North", northPn);
      customFrame.add("Center", listPn);
      customFrame.add("South", southPn);

      chooseCustomerBtn.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            try {
               if (e.getSource() == chooseCustomerBtn) {
//                  rs = stmt.executeQuery(customerSearchQuery);
//                  while (rs.next()) {
//                     String str = rs.getInt(1) + "\t" + rs.getString(2) + (rs.getString(2).length() > 11 ? "\t" : "\t\t")
//                           + rs.getString(3) + "\t" + rs.getInt(4) + "\n";
//                     txtResult.append(str);
//                  }
                  
                  new newWindow();
               }

            } catch (Exception e2) {
               System.out.println("���� �б� ���� :" + e2);
            }

         }

      });

   }

}


class newWindow extends JFrame {
    newWindow() {
        setTitle("���� ��� â");
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("�� â�� ����");
        
        NewWindowContainer.add(NewLabel);
        
        setSize(300,100);
        setResizable(false);
        setVisible(true);
    }
}

