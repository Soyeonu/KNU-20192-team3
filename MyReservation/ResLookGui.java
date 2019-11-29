package lectureRoom;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ResLookGui extends JFrame{
	Font font = new Font("����������", Font.PLAIN, 20);
	Dimension maxSize = new Dimension(800, 100);
	Color lightCol = new Color(255, 240, 240);
	Color lightCol2 = new Color(255, 212, 212);
	MouseHandler mh = new MouseHandler();
	JFrame frame;
	JPanel panel[], listPanel;
	int size;
	
	public ResLookGui() {
		frame = new JFrame("���� ��ȸ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000,700));
		frame.setSize(1000,700);
		
		Dimension frameSize = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		
		Container container = frame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel nameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nameL = new JLabel("���� ���� ����");
		nameL.setFont(new Font("����������", Font.BOLD, 22));
		nameL.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0));
		nameP.setMaximumSize(new Dimension(1000, 50));
		nameP.add(nameL);
		nameP.setBackground(Color.white);
		container.add(nameP);
		
		size = 5;		// size = db���� ����� ���� ���� �ޱ�
		int height = size * 100;

		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

		panel = new JPanel[size];

		for(int i = 0; i < size; i++) {	//���� �迭�� ����� �ε����� 1���͸� 1~ size����
			//j �� �� ���� ��ȣ�� db���� �޾ƿ���
		//	panel[i] = makeAList(j);	//j = �����ȣ
			panel[i].setMaximumSize(maxSize);	//Dimension
			panel[i].setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
			panel[i].addMouseListener(mh);
		}
		
		for(int i = 0; i < size; i++) {
			listPanel.add(panel[i]);
		}
		
		//��ũ�� �����ϰ� �����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(null);
		listPanel.setPreferredSize(new Dimension(800, height));
		listPanel.setBackground(Color.white);
		scrollPane.setViewportView(listPanel);

		container.add(scrollPane);
		container.setBackground(Color.white);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public JPanel makeAList(int resNum) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3, 1, 1));
		panel.setBackground(Color.white);
		
		
		panel.setName(Integer.toString(resNum));	//panel�� �̸��� ���� ��ȣ�� ����
		
		JLabel label[] = new JLabel[6];
		// db�� index���� �ִ� ���� ��ȣ, �ð� , ��, ȣ��, �ο����� ����
		int i;
		for(i = 0; i < size; i++) {
			//if(resNum == reslist[i].resNum)
			//	 break;	//�ɾ reslist[i].getTime
		}
		
		label[0] = new JLabel(Integer.toString(resNum));	// reslist[index].resnum?? ���� �ŷ� �ٲ��ָ� ��
		label[1] = new JLabel("// �ð�");	// ��� int���� ���� string���� �ٲ������
		label[2] = new JLabel("���� �ο�");
		label[3] = new JLabel("3��");
		label[4] = new JLabel("// ȣ��");	//reslist[i].getRoomNum();
		label[5] = new JLabel("//�ο���");
		
		for(int i = 0; i < 6; i++) {
			label[i].setOpaque(true);
			label[i].setFont(font);
			if(i < 3) {
				label[i].setBackground(lightCol2);
			}
			else {
				label[i].setBackground(lightCol);
			}
			panel.add(label[i]);
		}
		return panel;
	}
	
	public void delete(int index) {	//���� ���
		size--;
		
		for(int i = index; i < size; i++) {
			panel[i] = panel[i+1];
		}
				
		listPanel.removeAll();
		for(int i = 0; i < size; i++)
			listPanel.add(panel[i]);
		
		frame.revalidate();
		frame.repaint();

	}
	
	public class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int num;
			JPanel ClickedPanel = (JPanel)e.getSource();
			
			for(int i = 0; i < size; i++) {
				if(ClickedPanel.equals(panel[i])) {
					System.out.println(i);
					num = Integer.parseInt(panel[i].getName());
					int answer = JOptionPane.showConfirmDialog(null, "�����ȣ: " + num + "\n������ ����Ͻðڽ��ϱ�?");
					if(answer == 0) {
						//db ���ó��
						delete(i);	//�� �Լ� �ȿ��� ���� ��� dbó��
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
	
	public static void main(String[] args) {
		// TODO stub code
		ResLookGui resLook = new ResLookGui();
	}
	
}
