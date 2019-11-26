package classRoom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

//extends Frame으로 하면 실행 후 플랫폼 창이 X를 눌러도 종료되지 않으면 작업관리자로 강제종료 시켜야합니다.
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
		
		//전체 패널
		JPanel pn = new JPanel();
		pn.setLayout(new BorderLayout());
		pn.setBackground(Color.white);
		// 날짜 시간 층 선택할 수 있는 창 패널
		JPanel choicePn = new JPanel();
		choicePn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		choicePn.setBackground(Color.white);
		//강의실 패널
		JPanel roomPn = new JPanel();
		roomPn.setBackground(Color.white);
		// 확인버튼 패널
		JPanel btnPan = new JPanel();
		btnPan.setBackground(Color.white);
		
		JButton btn = new JButton("확인");
		btn.setFont(new Font("", Font.PLAIN, 20));
		btnPan.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// chDay = 날짜, chTime = 시간, chFloor = 층, /*chClassroom = 강의실*/
		Choice chDay = new Choice();
		Choice chTime = new Choice();
		Choice chFloor = new Choice();
		
		//글씨 크기
		chDay.setFont(new Font("", Font.PLAIN, 14));
		chTime.setFont(new Font("", Font.PLAIN, 14));
		chFloor.setFont(new Font("", Font.PLAIN, 14));
	
		//월~금 요일
		chDay.addItem("월");
		chDay.addItem("화");
		chDay.addItem("수");
		chDay.addItem("목");
		chDay.addItem("금");
		chDay.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
            	int dayInt = chDay.getSelectedIndex() + 1; // chDay 0~4 mon ~ fry 그래서 +1
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
		//지하1~지상3층 5호관
		chFloor.addItem("F3");
		chFloor.addItem("F2");
		chFloor.addItem("B1");
		chFloor.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                System.out.print(chFloor.getSelectedIndex() + ":");
                System.out.print(chFloor.getSelectedItem() + " ");
            
            }
        });
		// 강의실보여주기
		 //showRoom(chDay, chTime, chFloor);

	
		
		roomPn = li.getLectureGUI();
		
		
		 // 날짜 시간 층 입력
		choicePn.add(dateLB);
		choicePn.add(chDay);
		choicePn.add(chTime);
		choicePn.add(chFloor);
		
		
		// 예약하기전에 확인하기 JOptionPane return integer. 0 = ok,syes 1 = no 2 = cancel
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "확인") {
					int yesorno =
					JOptionPane.showConfirmDialog(null, chDay.getSelectedItem() + "요일 " + "시간 : "
							+ chTime.getSelectedItem() + " 층 : " + chFloor.getSelectedItem() + "\n강의실 번호: " + sltRoomnum, "확인",
							JOptionPane.YES_NO_OPTION);
					
					
					  if(yesorno == 0) {
						  sltDay = chDay.getSelectedItem();
						  sltTime = chTime.getSelectedItem();
						  sltFloor =chFloor.getSelectedItem();
//						  sltRoomnum =	
						  dispose();
						  // + 예약화면 불러오는 함수 또는 생성자 => sltDay sltTime sltFloor sltRoomnum 을 받아야함. String item or int index로
					  }		  
					  	
				}
				/*확인 = 0 취소 = 1
				 *
				 */

			}
		});

		btnPan.add(btn);
		
		//확인 버튼 패널
		pn.add("South", btnPan);
		//선택 패널
		pn.add("North", choicePn);
		//강의실 패널
		pn.add("Center", roomPn);

	
		
		//전체패널
		add(pn);
		
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
	
	
	// 날짜 시간 층 에 따른 강의실 보여주기
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
	
		
		 * DB에서 오늘 날짜, 현재시간 받아와서 층수에 맞게 초기화?
		 * 
		 *오늘 날짜 월요일 = chDay.select(1)  1~5 = 월 ~ 금 
		 *현재 시간  10시 = chTime.select(1)  
		 *("09:00 - 10:30");// 1A1B2A	0
		 *("10:30 - 12:00");// 2B3A3B	1
		 *("12:00 - 13:30");// 4A4B5A	2
		 *("13:30 - 15:00");// 5B6A6B	3
		 *("15:00 - 16:30");// 7A7B8A	4
		 *("16:30 - 18:00");// 8B9A9B	5
		 *지난 시간은,,?
		 *층은 3층으로 초기값 (0)   (0~2) => 3 2 B1
		
	}

	 */
      
    



}
