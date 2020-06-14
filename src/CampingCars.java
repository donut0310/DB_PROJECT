import java.awt.*;
import javax.swing.*;

public class CampingCars {
	public CampingCars(JPanel btnPn) {
		init( btnPn);
	}
	public void init(JPanel btnPn) {
		JButton submitBtn = new JButton("입력");
		JButton updateBtn = new JButton("변경");
		JButton deleteBtn = new JButton("삭제");
		
		JTextField input1 = new JTextField("캠핑카 이름");
		JTextField input2 = new JTextField("캠핑카 차량번호");
		JTextField input3 = new JTextField("캠핑카 승차 인원 수");
		JTextField input4 = new JTextField("캠핑카 제조 회사");
		JTextField input5 = new JTextField("캠핑카 제조 연도");
		JTextField input6 = new JTextField("캠핑카 누적 주횡거리");
		JTextField input7 = new JTextField("캠핑카 대여 비용");
		JTextField input8 = new JTextField("캠핑카 대여 회사 ID");
		JTextField input9 = new JTextField("캠핑카 등록 일자");
		
		JTextField update1 = new JTextField("캠핑카 이름");
		JTextField update2 = new JTextField("캠핑카 차량번호");
		JTextField update3 = new JTextField("캠핑카 승차 인원 수");
		JTextField update4 = new JTextField("캠핑카 제조 회사");
		JTextField update5 = new JTextField("캠핑카 제조 연도");
		JTextField update6 = new JTextField("캠핑카 누적 주횡거리");
		JTextField update7 = new JTextField("캠핑카 대여 비용");
		JTextField update8 = new JTextField("캠핑카 대여 회사 ID");
		JTextField update9 = new JTextField("캠핑카 등록 일자");

		JTextField delete1 = new JTextField("번호");
		JTextField delete2 = new JTextField("DISABLE");
		JTextField delete3 = new JTextField("DISABLE");
		JTextField delete4 = new JTextField("DISABLE");
		JTextField delete5 = new JTextField("DISABLE");
		JTextField delete6 = new JTextField("DISABLE");
		JTextField delete7 = new JTextField("DISABLE");
		JTextField delete8 = new JTextField("DISABLE");
		JTextField delete9 = new JTextField("DISABLE");
		
		btnPn.setLayout(new GridLayout(3,10)); 
		
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
		
		btnPn.add(deleteBtn);
		btnPn.add(delete1);
		btnPn.add(delete2);
		btnPn.add(delete3);
		btnPn.add(delete4);
		btnPn.add(delete5);
		btnPn.add(delete6);
		btnPn.add(delete7);
		btnPn.add(delete8);
		btnPn.add(delete9);	
		
		delete2.disable();
		delete3.disable();
		delete4.disable();
		delete5.disable();
		delete6.disable();
		delete7.disable();
		delete8.disable();
		delete9.disable();
	}
}
