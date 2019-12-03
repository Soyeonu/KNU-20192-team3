import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceGui extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final int NUMBER_OF_DIGITS =30;
    private JTextField ioField;
    Font font = new Font("나눔스퀘어", Font.PLAIN, 20);

    DBQuery choice;

    public ChoiceGui(DBQuery login) {

        setTitle("선택 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width)/5, (screenSize.height-frameSize.height)/5);

        setSize(WIDTH,HEIGHT);
        setLayout(new BorderLayout());

        this.choice = login;

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setLayout(new BoxLayout(btnPanel,BoxLayout.Y_AXIS));

        JButton searchButton = new RoundedButton("강의실 검색");
        searchButton.setFont(font);
        searchButton.setBackground(Color.white);
        searchButton.setForeground(new Color(43, 135, 255));
        searchButton.addActionListener(this);
        searchButton.setAlignmentX(CENTER_ALIGNMENT);


        JButton myresvButton = new RoundedButton("나의 예약 조회");
        myresvButton.addActionListener(this);
        myresvButton.setFont(font);
        myresvButton.setBackground(Color.white);
        myresvButton.setForeground(new Color(218, 59, 59));
        myresvButton.setAlignmentX(CENTER_ALIGNMENT);


        btnPanel.add(searchButton);
        btnPanel.add(Box.createVerticalStrut(20));
        btnPanel.add(myresvButton);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0));

        add(btnPanel,BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Complete this stub code
        try {
            assumingCorrectNumberFormats(e);
        }catch(NumberFormatException e2) {
            ioField.setText("Error: Reenter Number.");
        }
    }

    public void assumingCorrectNumberFormats(ActionEvent e) {
        // TODO Complete this stub code
        String actionCommand = e.getActionCommand();
        if(actionCommand.equals("강의실 검색")) {
            SearchGui searchGui = new SearchGui(choice);
            dispose();
        }
        else if(actionCommand.equals("나의 예약 조회")) {
            MyResGui myResGui = new MyResGui(choice.myinfo);
            dispose();
        }
        else
            ioField.setText("Unexpected error");

    }
}