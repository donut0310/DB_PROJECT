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
		
		JButton submitBtn = new JButton("�Է�");
		JButton updateBtn = new JButton("����");
		
		JTextField input1 = new JTextField("���� ������ ��ȣ");
		JTextField input2 = new JTextField("����");
		JTextField input3 = new JTextField("�� �ּ�");
		JTextField input4 = new JTextField("�� ��ȭ��ȣ");
		JTextField input5 = new JTextField("�� �̸���");
		
		JTextField update1 = new JTextField("���� ������ ��ȣ");
		JTextField update2 = new JTextField("����");
		JTextField update3 = new JTextField("�� �ּ�");
		JTextField update4 = new JTextField("�� ��ȭ��ȣ");
		JTextField update5 = new JTextField("�� �̸���");
		
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
