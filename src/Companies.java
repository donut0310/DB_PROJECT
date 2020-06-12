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
		
		JButton submitBtn = new JButton("�Է�");
		JButton updateBtn = new JButton("����");
		
		JTextField input1 = new JTextField("ȸ���");
		JTextField input2 = new JTextField("�ּ�");
		JTextField input3 = new JTextField("��ȭ��ȣ");
		JTextField input4 = new JTextField("����� �̸�");
		JTextField input5 = new JTextField("����� �̸���");
		
		JTextField update1 = new JTextField("ȸ���");
		JTextField update2 = new JTextField("�ּ�");
		JTextField update3 = new JTextField("��ȭ��ȣ");
		JTextField update4 = new JTextField("����� �̸�");
		JTextField update5 = new JTextField("����� �̸���");
		
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
