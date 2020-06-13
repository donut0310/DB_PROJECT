
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
      customFrame.setBounds(200, 200, 550, 550); // 가로위치,세로위치,가로길이,세로길이

      JPanel northPn = new JPanel();
      JPanel listPn = new JPanel();
      JPanel southPn = new JPanel();

      JButton accessibleListBtn = new JButton("대여 가능 리스트 보기");
      JTextField startDate = new JTextField("렌트 시작 일", 11);
      JTextField rentPeriod = new JTextField("대여 기간", 6);
      northPn.add(accessibleListBtn);
      northPn.add(startDate);
      northPn.add(rentPeriod);

      JButton chooseCustomerBtn = new JButton("고객 선택");
      JTextField customerName = new JTextField("고객 이름", 7);
      JTextField listNumber = new JTextField("리스트 번호", 7);
      JButton rentBtn = new JButton("대여 신청");
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
               System.out.println("쿼리 읽기 실패 :" + e2);
            }

         }

      });

   }

}


class newWindow extends JFrame {
    newWindow() {
        setTitle("새로 띄운 창");
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("새 창을 띄우기");
        
        NewWindowContainer.add(NewLabel);
        
        setSize(300,100);
        setResizable(false);
        setVisible(true);
    }
}

