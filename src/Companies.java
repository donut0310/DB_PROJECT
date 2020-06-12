import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Companies {
	public Companies(JPanel listPn, JPanel btnPn) {
		init(listPn, btnPn);
	}
	public void init(JPanel listPn, JPanel btnPn) {
		
		JTextArea txtResult = new JTextArea(15,50);
		txtResult.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtResult);
	    
		listPn.add("Center",scrollPane);
		
		JButton submitBtn = new JButton("입력");
		JButton updateBtn = new JButton("변경");
		
		JTextField input1 = new JTextField("회사명");
		JTextField input2 = new JTextField("주소");
		JTextField input3 = new JTextField("전화번호");
		JTextField input4 = new JTextField("담당자 이름");
		JTextField input5 = new JTextField("담당자 이메일");
		
		JTextField update1 = new JTextField("회사명");
		JTextField update2 = new JTextField("주소");
		JTextField update3 = new JTextField("전화번호");
		JTextField update4 = new JTextField("담당자 이름");
		JTextField update5 = new JTextField("담당자 이메일");
		
		btnPn.setLayout(new GridLayout(2,6)); 
		
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
