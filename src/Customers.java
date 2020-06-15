import java.awt.*;
import java.awt.event.*;
import java.sql.*;import javax.swing.*;

public class Customers {
   Connection con;
   Statement stmt= null;
   ResultSet rs = null;
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";
   
   public Customers(JPanel btnPn) {
      init(btnPn);
   }
   public void init(JPanel btnPn) {
      JButton submitBtn = new JButton("입력");
      JButton updateBtn = new JButton("변경");
      JButton deleteBtn = new JButton("삭제");
      
      JTextField input1 = new JTextField("운전 면허증 번호");
      JTextField input2 = new JTextField("고객명");
      JTextField input3 = new JTextField("고객 주소");
      JTextField input4 = new JTextField("고객 전화번호");
      JTextField input5 = new JTextField("고객 이메일");
      
      JTextField update1 = new JTextField("운전 면허증 번호");
      JTextField update2 = new JTextField("고객명");
      JTextField update3 = new JTextField("고객 주소");
      JTextField update4 = new JTextField("고객 전화번호");
      JTextField update5 = new JTextField("고객 이메일");

      JTextField delete1 = new JTextField("운전 면허증 번호");
      JTextField delete2 = new JTextField("DISABLE");
      JTextField delete3 = new JTextField("DISABLE");
      JTextField delete4 = new JTextField("DISABLE");
      JTextField delete5 = new JTextField("DISABLE");
      
      btnPn.setLayout(new GridLayout(3,5)); 
      
      btnPn.add(submitBtn);
      btnPn.add(input1);
      btnPn.add(input2);
      btnPn.add(input3);
      btnPn.add(input4);
      btnPn.add(input5);
      
      btnPn.add(updateBtn);
      btnPn.add(update1);
      btnPn.add(update2);
      btnPn.add(update3);
      btnPn.add(update4);
      btnPn.add(update5);

      update2.disable();
      
      btnPn.add(deleteBtn);
      btnPn.add(delete1);
      btnPn.add(delete2);
      btnPn.add(delete3);
      btnPn.add(delete4);
      btnPn.add(delete5);

      delete2.disable();
      delete3.disable();
      delete4.disable();
      delete5.disable();

      submitBtn.addActionListener( new ActionListener(){
         public void actionPerformed(ActionEvent e) {
         String licenseNum = (input1.getText());
         String name = (input2.getText());
         String address = (input3.getText());
         String phone = (input4.getText());
         String email = (input5.getText());
         try{
            conDB();
            stmt = con.createStatement();
            String str = "insert into Customers (licenseNum, name, address, phone, email)"
            + " VALUES (" +"'" + licenseNum + "','" + name +  "','" 
            + address + "','" + phone + "','" + email + "')";

            stmt.executeUpdate(str);
         }catch (Exception e2) {
            System.out.println();
            System.out.println("쿼리 읽기 실패 :" + e2);
         } 
         finally {
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
      
   });
   updateBtn.addActionListener( new ActionListener(){
         public void actionPerformed(ActionEvent e) {
         String licenseNum = (update1.getText());
         String address = (update3.getText());
         String phone = (update4.getText());
         String email = (update5.getText());
         try{
            conDB();
            stmt = con.createStatement();
            String str = "update Customers set address = " + "'" + address + "'," 
            + "phone = " + "'" + phone + "'," 
            + "email = " + "'" + email 
            + "' where licenseNum = " + "'" + licenseNum + "'";
            stmt.executeUpdate(str);

         }catch (Exception e2) {
            System.out.println();
            System.out.println("쿼리 읽기 실패 :" + e2);
         } 
         finally {
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
   });
   deleteBtn.addActionListener( new ActionListener(){
         public void actionPerformed(ActionEvent e) {
         String licenseNum = (delete1.getText());
         try{
            conDB();
            stmt = con.createStatement();
            stmt.executeUpdate("delete from Customers where licenseNum = " + "'" 
            + licenseNum + "'");
         }catch (Exception e2) {
            System.out.println();
            System.out.println("쿼리 읽기 실패 :" + e2);
         } 
         finally {
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
   });
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
         con = DriverManager.getConnection(url, userid, pwd);
         System.out.println("데이터베이스 연결 성공");
      } catch (SQLException e1) {
         e1.printStackTrace();
      }
   }
}