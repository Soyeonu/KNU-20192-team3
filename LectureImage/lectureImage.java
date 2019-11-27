package lectureRoom;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class lectureImage extends JFrame{
	JFrame frame;
	JPanel mainPanel;
	JButton btn[] = new JButton[10];
	JLabel label[] = new JLabel[10];
	int fWidth = 1000, fHeight = 700;
	File srcimg, src301, src309, src324, src342, src345, src348, src351, src355;	// 모든 강의실 정보를 불러올 파일
	BufferedImage img[] = new BufferedImage[10];
	ImageIcon icon[] = new ImageIcon[10];
	int imgWidth, imgHeight, w, h;
	int rWidth, rHeight, rw, rh;
	double ratio;
	int color = new Color(145, 222, 231).getRGB();
	int gray = new Color(204, 204, 204).getRGB();
	int pink = new Color(255, 147, 147).getRGB();
	int blue = new Color(188, 221, 255).getRGB();
	int red = new Color(255, 71, 71).getRGB();
	int roomColor[] = {color, gray, pink, blue, red, blue, blue, blue, gray};
	int usage[] = {-1};
	MouseHandler mh = new MouseHandler();
	JButton entered_btn = null;
	String date, time, floor; // 검색에서 띄울 날짜 시간 층 정보
	
	public void lecturImage(String date, String time, String floor) {
		this.date = date;
		this.time = time;
		this.floor = floor;
	}
	
	public JPanel getLectureGUI() {
		return this.mainPanel;
	}

	public lectureImage() {
		init();

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		icon[0] = new ImageIcon(img[0]);
		label[0] = new JLabel(icon[0]);
		label[0].setBounds(0, 0, icon[0].getIconWidth(), icon[0].getIconHeight());

		//색을 인트형으로 받아서 roomColor에 저장
		// 레이블에 컬러 추가하기
		int i;
		for(i = 1; i <= 8; i++) {
			label[i] = setColor(img[i], icon[i], label[i], roomColor[i]);
		}
				
		setButton();
		
		for(i = 0; i <= 8; i++)
			mainPanel.add(label[i]);

		mainPanel.setBackground(Color.white);
		for(i = 1; i <= 8; i++)
			mainPanel.add(btn[i]);
		
//		frame.add(mainPanel);
//		frame.pack();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		frame.setResizable(true);
	}

	public void getColor() {
		//인원 수에 따라서 색을 어떻게 바꿀지 결정하기
		//DB에서 받아오기
		
	}
	
	public void setButton() {
		btn[1] = new JButton("301");
		btn[1].setBounds(125, 430, 160, 75);
		btn[1].setBorderPainted(false);
		btn[1].setContentAreaFilled(false);
		btn[1].addMouseListener(mh);
		
		btn[2] = new JButton("309");
		btn[2].setBounds(280, 430, 160, 75);
		btn[2].setBorderPainted(false);
		btn[2].setContentAreaFilled(false);
		btn[2].addMouseListener(mh);
		
		btn[3] = new JButton("324");
		btn[3].setBounds(555, 320, 155, 75);
		btn[3].setBorderPainted(false);
		btn[3].setContentAreaFilled(false);
		btn[3].addMouseListener(mh);
		
		btn[4] = new JButton("342");
		btn[4].setBounds(470, 145, 100, 50);
		btn[4].setBorderPainted(false);
		btn[4].setContentAreaFilled(false);
		btn[4].addMouseListener(mh);
		
		btn[5] = new JButton("345");
		btn[5].setBounds(350, 125, 100, 50);
		btn[5].setBorderPainted(false);
		btn[5].setContentAreaFilled(false);
		btn[5].addMouseListener(mh);
		
		btn[6] = new JButton("348");
		btn[6].setBounds(230, 105, 100, 50);
		btn[6].setBorderPainted(false);
		btn[6].setContentAreaFilled(false);
		btn[6].addMouseListener(mh);
		
		btn[7] = new JButton("351");
		btn[7].setBounds(105, 82, 100, 50);
		btn[7].setBorderPainted(false);
		btn[7].setContentAreaFilled(false);
		btn[7].addMouseListener(mh);
		
		btn[8] = new JButton("355");
		btn[8].setBounds(125, 240, 75, 160);//315
		btn[8].setBorderPainted(false);
		btn[8].setContentAreaFilled(false);
		btn[8].addMouseListener(mh);
	}
	
	public JLabel setColor(BufferedImage image, ImageIcon icon, JLabel label, int set_color) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				if(image.getRGB(w,  h) == color)
					image.setRGB(w, h, set_color);
			}
		}
		
		icon = new ImageIcon(image);
		label = new JLabel(icon);
		label.setBounds(0, 0, icon.getIconWidth(), (int)icon.getIconHeight());
		
		return label;
	}
	
	void init() {
	//	System.out.println("init");
		//프레임 설정
//		frame = new JFrame("LectureRoom Scheduler");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setPreferredSize(new Dimension(fWidth,fHeight));
//		Dimension frameSize = frame.getSize();
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setLocation((screenSize.width - frameSize.width)/5, (screenSize.height-frameSize.height)/5);
//		frame.setSize(fWidth, fHeight);
		
		//이미지 파일 불러와서 저장하기
		srcimg = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\3f_with_num.png");
		try {
			img[0] = ImageIO.read(srcimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		src301 = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\301.png");
		try {
			img[1] = ImageIO.read(src301);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		src309 = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\309.png");
		try {
			img[2] = ImageIO.read(src309);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		src324 = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\324.png");
		try {
			img[3] = ImageIO.read(src324);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		src342 = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\342.png");
		try {
			img[4] = ImageIO.read(src342);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		src345 = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\345.png");
		try {
			img[5] = ImageIO.read(src345);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		src348 = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\348.png");
		try {
			img[6] = ImageIO.read(src348);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		src351 = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\351.png");
		try {
			img[7] = ImageIO.read(src351);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		src355 = new File("C:\\Users\\pc\\Documents\\Java_Programming\\_SD_integrate\\src\\lectureRoom\\355.png");
		try {
			img[8] = ImageIO.read(src355);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getRoomNum() {
		if(entered_btn != null)
			return entered_btn.getText();
		else
			return null;
	}
	
	public class MouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			//예약 페이지로 넘어가기
			entered_btn = (JButton) e.getSource();
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		//	System.out.println("entered");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		//	System.out.println("exited");
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
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		lectureImage li = new lectureImage();
	}
	
}
