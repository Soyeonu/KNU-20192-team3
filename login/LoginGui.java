package login;
/***
 * You cannot release or distribute 
 * this code without instructor's permission.
 * COMP217: Java Programming
 * Instructor: Prof. Suh, Young-Kyoon
 * Lab #9-3: A simplified calculator
 * @author yksuh
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class LoginGui extends JFrame implements ActionListener{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	public static final int NUMBER_OF_DIGITS =30;
	private JTextField ioField;
	
	public static void main(String[] args) {
		// TODO stub code
		LoginGui LoginMenu = new LoginGui();
		LoginMenu.setVisible(true);
	}
	
	public LoginGui() {
		setTitle("LoginMenu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setLayout(new BorderLayout());

		
	
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());
		JLabel IdTitle = new JLabel("Studend ID:");
		textPanel.add(IdTitle);
		ioField = new JTextField("Enter numbers here.",NUMBER_OF_DIGITS);
		ioField.setBackground(Color.WHITE);
		textPanel.add(ioField);
		ioField.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseClicked(MouseEvent e) {
	            ioField.setText("");
	         }
	      });
		add(textPanel,BorderLayout.NORTH);
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.WHITE);
		btnPanel.setLayout(new FlowLayout());
		
		JButton addButton = new JButton("register");
		addButton.addActionListener(this);
		btnPanel.add(addButton);
		
		JButton subtractButton = new JButton("sign in");
		subtractButton.addActionListener(this);
		btnPanel.add(subtractButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		btnPanel.add(resetButton);
		
		add(btnPanel,BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Complete this stub code
		try {
			assumingCorrectNumberFormats(e);
		}catch(NumberFormatException e2) {
			ioField.setText("Error: Reenter Number.");
		}
	}
	
	// Throws NumberFormatException.
	public void assumingCorrectNumberFormats(ActionEvent e) {
		// TODO Complete this stub code
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("register")) {
			RegisterGui registerMenu = new RegisterGui();
			registerMenu.setVisible(true);
			//LoginMain.register(Integer.parseInt(ioField.getText()));
		}else if(actionCommand.equals("sign in")){
			LoginMain.login(Integer.parseInt(ioField.getText()));
		}else if(actionCommand.equals("Reset")) {
			ioField.setText("Enter numbers here.");
		}else {
			ioField.setText("Unexpected error");
		}
	}
	
	
}
