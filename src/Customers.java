import java.awt.*;
import javax.swing.*;

public class Customers {
	public Customers(JPanel listPn, JPanel btnPn) {
		init(listPn, btnPn);
	}
	public void init(JPanel listPn, JPanel btnPn) {
		JTextArea txtResult = new JTextArea(15,50);
		txtResult.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtResult);
	    
		listPn.add("Center",scrollPane);
		
		JButton submitBtn = new JButton("입력");
		JButton updateBtn = new JButton("변경");
		
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
		
		btnPn.setLayout(new GridLayout(2,5)); 
		
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
		
		listPn.revalidate();
		listPn.repaint();
	}
}
