import java.awt.*;
import javax.swing.*;

public class CampingCars {
	public CampingCars(JPanel listPn, JPanel btnPn) {
		init(listPn, btnPn);
	}
	public void init(JPanel listPn, JPanel btnPn) {
		JTextArea txtResult = new JTextArea(15,50);
		txtResult.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtResult);
	    
		listPn.add("Center",scrollPane);
		
		JButton submitBtn = new JButton("�Է�");
		JButton updateBtn = new JButton("����");
		
		JTextField input1 = new JTextField("ķ��ī �̸�");
		JTextField input2 = new JTextField("ķ��ī ������ȣ");
		JTextField input3 = new JTextField("ķ��ī ���� �ο� ��");
		JTextField input4 = new JTextField("ķ��ī ���� ȸ��");
		JTextField input5 = new JTextField("ķ��ī ���� ����");
		JTextField input6 = new JTextField("ķ��ī ���� ��Ⱦ�Ÿ�");
		JTextField input7 = new JTextField("ķ��ī �뿩 ���");
		JTextField input8 = new JTextField("ķ��ī �뿩 ȸ�� ID");
		JTextField input9 = new JTextField("ķ��ī ��� ����");
		
		JTextField update1 = new JTextField("ķ��ī �̸�");
		JTextField update2 = new JTextField("ķ��ī ������ȣ");
		JTextField update3 = new JTextField("ķ��ī ���� �ο� ��");
		JTextField update4 = new JTextField("ķ��ī ���� ȸ��");
		JTextField update5 = new JTextField("ķ��ī ���� ����");
		JTextField update6 = new JTextField("ķ��ī ���� ��Ⱦ�Ÿ�");
		JTextField update7 = new JTextField("ķ��ī �뿩 ���");
		JTextField update8 = new JTextField("ķ��ī �뿩 ȸ�� ID");
		JTextField update9 = new JTextField("ķ��ī ��� ����");
		
		btnPn.setLayout(new GridLayout(2,10)); 
		
		btnPn.add(submitBtn);
		btnPn.add(input1);
		btnPn.add(input2);
		btnPn.add(input3);
		btnPn.add(input4);
		btnPn.add(input5);
		btnPn.add(input6);
		btnPn.add(input7);
		btnPn.add(input8);
		btnPn.add(input9);
		
		btnPn.add(updateBtn);
		btnPn.add(update1);
		btnPn.add(update2);
		btnPn.add(update3);
		btnPn.add(update4);
		btnPn.add(update5);
		btnPn.add(update6);
		btnPn.add(update7);
		btnPn.add(update8);
		btnPn.add(update9);
		
		listPn.revalidate();
		listPn.repaint();
	}
}
