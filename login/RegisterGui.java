package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterGui extends JFrame implements ActionListener{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	public static final int NUMBER_OF_DIGITS =30;
	private JTextField ioField;
	private JTextField NameField;
	
	public RegisterGui() {
		setTitle("RegisterMenu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setLayout(new GridLayout(3,1));
		JPanel IdPanel = new JPanel();
		IdPanel.setLayout(new FlowLayout());
		JLabel IdTitle = new JLabel("Studend ID:");
		IdPanel.add(IdTitle);
		ioField = new JTextField("Enter numbers here.",NUMBER_OF_DIGITS);
		ioField.setBackground(Color.WHITE);
		ioField.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseClicked(MouseEvent e) {
	            ioField.setText("");
	         }
	      });
		IdPanel.add(ioField);
		add(IdPanel);
		
		JPanel NamePanel = new JPanel();
		NamePanel.setLayout(new FlowLayout());
		JLabel NameTitle = new JLabel("Name:");
		NamePanel.add(NameTitle);
		NameField = new JTextField("Enter name here.",NUMBER_OF_DIGITS);
		NameField.setBackground(Color.WHITE);
		NameField.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseClicked(MouseEvent e) {
	            NameField.setText("");
	         }
	      });
		NamePanel.add(NameField);
		add(NamePanel);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.WHITE);
		btnPanel.setLayout(new FlowLayout());
		
		JButton addButton = new JButton("register");
		addButton.addActionListener(this);
		btnPanel.add(addButton);
		add(btnPanel);
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
			LoginMain.register(Integer.parseInt(ioField.getText()),NameField.getText());
			dispose();
		}else {
			ioField.setText("Unexpected error");
		}
	}
	
	
}