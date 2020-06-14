import java.awt.*;
import javax.swing.*;

public class Customers {
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
	}
}
