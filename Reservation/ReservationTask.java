package lectureRoom;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservationTask extends JFrame{
	Reservation reservation;
	int reservNum; // 예약 인원수
	String entered_num;
	JPanel statPanel, resPanel, resBtnPanel;	// 컨테이너 위에 붙이는 페널
//	JTable resTable;		
	JButton resBtn;	// 예약하기 버튼
	JLabel label;	// 예약 패널 위의 레이블
	JTextField resNumText;	//예약 숫자 입력하는 텍스트 필드
	JLabel resNum[][];
	Font font = new Font("나눔스퀘어", Font.PLAIN, 20);
	Font boldFont = new Font("나눔스퀘어", Font.BOLD, 20);
	
	public ReservationTask() {
		// 여러 시간대의 인원수 보여주기
		// 예약 인원 입력하기
		// 예약하기 버튼
		
		JFrame frame = new JFrame("예약");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000,700));
		frame.setSize(1000,700);
		Dimension frameSize = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		
		Container container = frame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		
		//여기에 예약 인원 출력하는 패널 하나 더 추가해야함
		statPanel = new JPanel();
		statPanel.setLayout(new GridLayout(0, 2, 1, 1));
		statPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		resNum = new JLabel[10][2];
		
		String header[] = {"시간", "예약 인원 수"};	//강의실 정보 받아와야함
		String content[] = {" 9:00 ~ 10:30", 
				"10:30 ~ 12:00", 
				"12:00 ~  1:30",
				" 1:30 ~  3:00",
				" 3:00 ~  4:30",
				" 4:30 ~  6:00",
				" 6:00 ~  7:30",
				" 7:30 ~  9:00"
		};
		//여기에 아마 reservation 숫자를 넣을듯
		//DB에서 reservation number받아와서 temp_num에 넣기
		//
		int temp_num[] = { 1, 5, 8, 5, 2, 3, 10, 9, 8, 0};
		
		//라벨들 합치기
		resNum[0][0] = new JLabel(header[0]);
		resNum[0][1] = new JLabel(header[1]);
		for(int i = 0; i < 8; i++) {
			resNum[i+1][0] = new JLabel(content[i]);
			resNum[i+1][1] = new JLabel(Integer.toString(temp_num[i]));
		}
		
		//표 만들기
		Color col = new Color(219, 105, 105);
		Color lightCol = new Color(255, 240, 240);
		Color lightCol2 = new Color(255, 212, 212);
		for(int i = 0; i <= 8; i++) {
			for(int j = 0; j < 2; j++){
				resNum[i][j].setFont(font);
				resNum[i][j].setHorizontalAlignment(JLabel.CENTER);
				if(i == 0) {
					resNum[i][j].setFont(boldFont);
					resNum[i][j].setOpaque(true);
					resNum[i][j].setBackground(col);
					resNum[i][j].setForeground(Color.white);
				}
				else if(i % 2 == 0) {
					resNum[i][j].setOpaque(true);
					resNum[i][j].setBackground(lightCol);
				}
				else {
					resNum[i][j].setOpaque(true);
					resNum[i][j].setBackground(lightCol2);
				}
			}
		}

		//panel에 label추가
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 2; j++)
				statPanel.add(resNum[i][j]);
		}
		statPanel.setBackground(Color.white);
		
		
		//예약 인원 입력 패널
		JPanel resEnterPanel = new JPanel();
		resEnterPanel.setLayout(new FlowLayout());
		
		JLabel resLabel = new JLabel("예약 인원 수 ");
		resLabel.setFont(font);
		
		// 예약 인원 입력창
		resNumText = new JTextField("예약할 인원을 입력하세요", 15);
		resNumText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resNumText.setText("");
			}
		});
		resNumText.setFont(font);
	//	resNumText.setMaximumSize(resNumText.getPreferredSize());
		
		resEnterPanel.add(resLabel);
		resEnterPanel.add(resNumText);
		resEnterPanel.setBackground(Color.white);
		
		resBtn = new RoundedButton("예약하기");
		MouseHandler handler = new MouseHandler();
		resBtn.addMouseListener(handler);
		resBtn.setFont(font);
		Color c = new Color(218, 59, 59);
		resBtn.setBackground(Color.white);
		resBtn.setForeground(c);
		JPanel resBtnPanel = new JPanel();
		resBtnPanel.setLayout(new FlowLayout());
		resBtnPanel.add(resBtn);
		resBtnPanel.setBackground(Color.white);
		
		JLabel warning = new JLabel("예약 인원을 확인하고 예약해주세요");
		warning.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		resPanel = new JPanel();
		resPanel.setLayout(new BoxLayout(resPanel, BoxLayout.Y_AXIS));
		resPanel.add(resEnterPanel);
		resPanel.add(resBtnPanel);
	//	resPanel.add(warning);
		resPanel.setBackground(Color.white);
		resPanel.setBorder(BorderFactory.createEmptyBorder(250, 0, 250, 0));

		container.add(statPanel);
		container.add(resPanel);
		container.setBackground(Color.white);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public class MouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			entered_num = resNumText.getText();
			JButton button = (JButton)e.getSource();
			if(button == resBtn) {
				if(entered_num.equals("")) {
					JOptionPane.showMessageDialog(null, "숫자를 입력하세요");
				}
				else {
					try {
						reservNum = Integer.parseInt(entered_num);
						//DB에 업데이트하기
						
						System.out.println(reservNum);
					} catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "숫자를 입력해 주세요");
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
	public int getReservNum() {
		return reservNum;
	}
	
	public void setReservNum(int num) {
		reservNum = num;
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method st
//		
//		ReservationTask reservation_task = new ReservationTask();
//	}


}

