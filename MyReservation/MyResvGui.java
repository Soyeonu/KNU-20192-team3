import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class MyResvGui extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    JList list;
    String RList;
    int idx = 0;
    int del = 0;

    ListHandler handler = new ListHandler();
    DBQuery database = new DBQuery();
    DefaultListModel listModel = new DefaultListModel();

    public MyResvGui()
    {

        //database.setRselist(database.myinfo.getId());
        database.setRselist(2017115259);

        setTitle("나의 예약 조회");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setLayout(new BorderLayout());

        JButton btn = new JButton("삭제");

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                int change = JOptionPane.showConfirmDialog(null,"예약을 삭제하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);

                if(change == JOptionPane.CLOSED_OPTION);
                else if(change == JOptionPane.YES_OPTION)
                {
                    Object o = list.getModel().getElementAt(idx);
                    String tt = o.toString();
                    tt = tt.substring(0,4);
                    System.out.println("====================="+tt);
                    del = Integer.parseInt(tt);
                    listModel.remove(idx);
                    database.Update(del);
                }
                else ;
            }
        });


        JLabel list1 = new JLabel("예약 번호");JLabel list2 = new JLabel(" 강의실 ");JLabel list3 = new JLabel(" 예약 날짜  ");
        JLabel list4 = new JLabel("예약 시간 ");JLabel list5 = new JLabel("예약 인원 ");
        list1.setFont(new Font("돋움", Font.BOLD, 20));
        list2.setFont(new Font("돋움", Font.BOLD, 20));
        list3.setFont(new Font("돋움", Font.BOLD, 20));
        list4.setFont(new Font("돋움", Font.BOLD, 20));
        list5.setFont(new Font("돋움", Font.BOLD, 20));


        for(int j = 0; j< database.cnt;j++) {
            RList = database.rselist[j].getR_id()+ "   "+  database.rselist[j].getRoomnum()+"   " + database.rselist[j].getR_date() + "  "
                    + database.rselist[j].getR_time() +"   "+database.rselist[j].getNop()+ "명";
            listModel.addElement(RList);
        }

        JPanel show = new JPanel();
        show.add(list1);show.add(list2);show.add(list3);show.add(list4);show.add(list5);
        add(show,BorderLayout.NORTH);



        JPanel panel1 = new JPanel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(handler);
        list.setFont(new Font("돋움", Font.PLAIN, 25));
        panel1.add(list);

        add(panel1,BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.add(btn);

        add(panel2, BorderLayout.SOUTH);

    }

    private class ListHandler implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            idx = list.getSelectedIndex();
        }
    }
}
