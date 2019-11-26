package classRoom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

//extends Frame���� �ϸ� ���� �� �÷��� â�� X�� ������ ������� ������ �۾������ڷ� �������� ���Ѿ��մϴ�.
public class Search extends JFrame {
	
	String sltRoomnum;
	String sltDay;
	String sltTime;
	String sltFloor;
	lectureImage li = new lectureImage();
	LocalDate today = LocalDate.now();
	
	public Search(String d) {
		super(d);
		
		JLabel dateLB = new JLabel(today.toString());
		dateLB.setFont(new Font("", Font.PLAIN, 18));;
		
		//��ü �г�
		JPanel pn = new JPanel();
		pn.setLayout(new BorderLayout());
		pn.setBackground(Color.white);
		// ��¥ �ð� �� ������ �� �ִ� â �г�
		JPanel choicePn = new JPanel();
		choicePn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		choicePn.setBackground(Color.white);
		//���ǽ� �г�
		JPanel roomPn = new JPanel();
		roomPn.setBackground(Color.white);
		// Ȯ�ι�ư �г�
		JPanel btnPan = new JPanel();
		btnPan.setBackground(Color.white);
		
		JButton btn = new JButton("Ȯ��");
		btn.setFont(new Font("", Font.PLAIN, 20));
		btnPan.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// chDay = ��¥, chTime = �ð�, chFloor = ��, /*chClassroom = ���ǽ�*/
		Choice chDay = new Choice();
		Choice chTime = new Choice();
		Choice chFloor = new Choice();
		
		//�۾� ũ��
		chDay.setFont(new Font("", Font.PLAIN, 14));
		chTime.setFont(new Font("", Font.PLAIN, 14));
		chFloor.setFont(new Font("", Font.PLAIN, 14));
	
		//��~�� ����
		chDay.addItem("��");
		chDay.addItem("ȭ");
		chDay.addItem("��");
		chDay.addItem("��");
		chDay.addItem("��");
		chDay.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
            	int dayInt = chDay.getSelectedIndex() + 1; // chDay 0~4 mon ~ fry �׷��� +1
                today.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dayInt)));//1 ~ 7 == mon ~ sun 
                LocalDate changed = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dayInt)));
                dateLB.setText(changed.toString());
            }
        });
		chDay.select(today.getDayOfWeek().getValue() - 1);
		
		chTime.addItem("09:00 - 10:30");// 1A1B2A
		chTime.addItem("10:30 - 12:00");// 2B3A3B
		chTime.addItem("12:00 - 13:30");// 4A4B5A
		chTime.addItem("13:30 - 15:00");// 5B6A6B
		chTime.addItem("15:00 - 16:30");// 7A7B8A
		chTime.addItem("16:30 - 18:00");// 8B9A9B
		chTime.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                System.out.print(chTime.getSelectedIndex() + ":");
                System.out.print(chTime.getSelectedItem() + " ");
                //showRoom(chDay, chTime, chFloor);
                
            }
        });
		//����1~����3�� 5ȣ��
		chFloor.addItem("F3");
		chFloor.addItem("F2");
		chFloor.addItem("B1");
		chFloor.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                System.out.print(chFloor.getSelectedIndex() + ":");
                System.out.print(chFloor.getSelectedItem() + " ");
            
            }
        });
		// ���ǽǺ����ֱ�
		 //showRoom(chDay, chTime, chFloor);

	
		
		roomPn = li.getLectureGUI();
		
		
		 // ��¥ �ð� �� �Է�
		choicePn.add(dateLB);
		choicePn.add(chDay);
		choicePn.add(chTime);
		choicePn.add(chFloor);
		
		
		// �����ϱ����� Ȯ���ϱ� JOptionPane return integer. 0 = ok,syes 1 = no 2 = cancel
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Ȯ��") {
					int yesorno =
					JOptionPane.showConfirmDialog(null, chDay.getSelectedItem() + "���� " + "�ð� : "
							+ chTime.getSelectedItem() + " �� : " + chFloor.getSelectedItem() + "\n���ǽ� ��ȣ: " + sltRoomnum, "Ȯ��",
							JOptionPane.YES_NO_OPTION);
					
					
					  if(yesorno == 0) {
						  sltDay = chDay.getSelectedItem();
						  sltTime = chTime.getSelectedItem();
						  sltFloor =chFloor.getSelectedItem();
//						  sltRoomnum =	
						  dispose();
						  // + ����ȭ�� �ҷ����� �Լ� �Ǵ� ������ => sltDay sltTime sltFloor sltRoomnum �� �޾ƾ���. String item or int index��
					  }		  
					  	
				}
				/*Ȯ�� = 0 ��� = 1
				 *
				 */

			}
		});

		btnPan.add(btn);
		
		//Ȯ�� ��ư �г�
		pn.add("South", btnPan);
		//���� �г�
		pn.add("North", choicePn);
		//���ǽ� �г�
		pn.add("Center", roomPn);

	
		
		//��ü�г�
		add(pn);
		
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
	
	
	// ��¥ �ð� �� �� ���� ���ǽ� �����ֱ�
	public void showRoom(Choice day, Choice time, Choice floor, LocalDate date) {
		
		/*
		 * select L_ROOM 
		 * from schedule 
		 * where L_DAY = day.getSelectedIndex()+1 
		 * and  
		 *  and floor = floor
		 * and R_DATE = date
		 */

	}

/*
	public void setclsroom() {
		LocalTime currentTime = LocalTime.now();
		currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
	
		
		 * DB���� ���� ��¥, ����ð� �޾ƿͼ� ������ �°� �ʱ�ȭ?
		 * 
		 *���� ��¥ ������ = chDay.select(1)  1~5 = �� ~ �� 
		 *���� �ð�  10�� = chTime.select(1)  
		 *("09:00 - 10:30");// 1A1B2A	0
		 *("10:30 - 12:00");// 2B3A3B	1
		 *("12:00 - 13:30");// 4A4B5A	2
		 *("13:30 - 15:00");// 5B6A6B	3
		 *("15:00 - 16:30");// 7A7B8A	4
		 *("16:30 - 18:00");// 8B9A9B	5
		 *���� �ð���,,?
		 *���� 3������ �ʱⰪ (0)   (0~2) => 3 2 B1
		
	}

	 */
      
    



}
