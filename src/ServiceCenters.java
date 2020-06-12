import java.awt.*;
import javax.swing.*;

public class ServiceCenters {
	public ServiceCenters(JPanel listPn, JPanel btnPn) {
		init(listPn, btnPn);
	}
	public void init(JPanel listPn, JPanel btnPn) {
		JTextArea txtResult = new JTextArea(15,50);
		txtResult.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtResult);
	    
		listPn.add("Center",scrollPane);
		
		JButton submitBtn = new JButton("입력");
		JButton updateBtn = new JButton("변경");
		
		JTextField input1 = new JTextField("캠핑카 정비소 ID");
		JTextField input2 = new JTextField("정비소 명");
		JTextField input3 = new JTextField("정비소 주소");
		JTextField input4 = new JTextField("정비소 전화번호");
		JTextField input5 = new JTextField("담당자 이름");
		JTextField input6 = new JTextField("담당자 이메일");
		
		JTextField update1 = new JTextField("캠핑카 정비소 ID");
		JTextField update2 = new JTextField("정비소 명");
		JTextField update3 = new JTextField("정비소 주소");
		JTextField update4 = new JTextField("정비소 전화번호");
		JTextField update5 = new JTextField("담당자 이름");
		JTextField update6 = new JTextField("담당자 이메일");
		
		btnPn.setLayout(new GridLayout(2,6)); 
		
		btnPn.add(submitBtn);
		btnPn.add(input1);
		btnPn.add(input2);
		btnPn.add(input3);
		btnPn.add(input4);
		btnPn.add(input5);
		btnPn.add(input6);
		
		btnPn.add(updateBtn);
		btnPn.add(update1);
		btnPn.add(update2);
		btnPn.add(update3);
		btnPn.add(update4);
		btnPn.add(update5);
		btnPn.add(update6);
		
		listPn.revalidate();
		listPn.repaint();
	}
}
