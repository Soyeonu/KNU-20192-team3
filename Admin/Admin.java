package classRoom;

import java.awt.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class Admin extends JFrame {
	/**
	 * @������ : data
	 * @���� : ���̺� ǥ�õ� �����͸� �����ϴ� �����Դϴ�.
	 */
	private Vector<Vector> data;
	/**
	 * @������ : in
	 * @���� : ���̺� ǥ�õ� 1���� ��(���ڵ�)�� �����ϴ� �����Դϴ�.
	 */
	private Vector<String> in;
	
	/**
	 * @������ : title
	 * @���� : ���̺� ǥ�õ� Ÿ��Ʋ�� �����ϴ� �����Դϴ�.
	 */
	private Vector<String> title;
	
	/**
	 * @������ : connetionUrl
	 * @���� : �����ͺ��̽� ������ ���� URL�� �����ϴ� ���ڿ��Դϴ�.
	 */
	private String connetionUrl = ""

	
	/**
	 * @������ : user
	 * @���� : �����ͺ��̽� ������ ���� user�� �����ϴ� ���ڿ��Դϴ�.
	 */
	private String user = ""


	/**
	 * @������ : password
	 * @���� : �����ͺ��̽� ������ ���� password�� �����ϴ� ���ڿ��Դϴ�.
	 */
	private String password = ""


	/**
	 * @������ : conn
	 * @���� : �����ͺ��̽� ������ ���� Connection�� �����ϴ� ���ڿ��Դϴ�
	 */
	private Connection conn = null;
	
	/**
	 * @������ : stmt
	 * @���� : �����ͺ��̽� ������ ���� ����� ���ϴ� ��ü�Դϴ�.
	 */
	private Statement stmt = null;
	
	/**
	 * @�����ڸ� : Admin
	 * @���� : Frame�� �����ϰ�, ���̺� ���� , model�� ����� ������(����) �����ϴ� �������Դϴ�.
	 */
	public Admin() {

		super("���ǽ� ���� ��Ȳ");
		

//		���̺� ���� Ÿ��Ʋ�� �����͸� �����ϴ� ���� ��ü ����
		title = new Vector<>();
		data = new Vector<>();
		
//		���̺� ���� Ÿ��Ʋ ���� �ʱ�ȭ

		title.add("���ǽǹ�ȣ");
		title.add("�ð�");
		title.add("��¥");
		title.add("���� �ο���");
		title.add("������ �л�");
		

//		���̺� ���� �����͸� ���ϴ� �޼ҵ��Դϴ�.

		//getData();

//		���̺�(���߰�, title, data) ����, ��ũ�ѿ� �߰�

		JTable table = new JTable(data, title);

		JScrollPane sp = new JScrollPane(table);
//		ȭ�� �����̳� ����

		Container c = getContentPane();
		
//		ȭ�鿡 ���̺� �߰�
		c.add(sp,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Frame Close�� �ڿ� �ݳ� ó��

//		addWindowListener(new WindowAdapter(){
//
//			@Override
//
//			public void windowClosing(WindowEvent w) {
//
//				try{
//
//					stmt.close();
//
//					conn.close();
//
//					setVisible(true);
//
//					dispose();
//
//					System.exit(0);
//
//				}catch(Exception e){
//
//					
//
//				}
//
//			}
//
//		});
		
		pack();

		setBackground(Color.white);
		setVisible(true);

	}
	
	/**
	 * @Method Name : getData
	 * @���� : ���̺� ǥ�õ� ������ setting �޼ҵ��Դϴ�.
	 */
	private void getData() {

		try{

//			1. ����̹� �ε�

			Class.forName("oracle.jdbc.driver.OracleDriver");

		}catch(ClassNotFoundException e){

			e.printStackTrace();

		}
		
		try{
			
//			2. �����ͺ��̽� Connection ����
			conn = DriverManager.getConnection(connetionUrl,user,password);

//			3. ���� ���� Statement ����
			stmt = conn.createStatement();
			String sql = "select * from member";
			
//			4. ���� ������ ����� ResultSet ��ü�� setting
			ResultSet rs = stmt.executeQuery(sql);
			
//			5. ��������� ���Ϳ� �ݺ��ؼ� ó���ϴ� �ݺ���
			while(rs.next()){
//				�����ͺ��̽����� 1���� ��(���ڵ�)�� �����ϴ� ���� ���� �� �� �Է�
				in = new Vector<>();

				in.add(rs.getString(2));//���ǽǹ�ȣ
				in.add(rs.getString(3));//�ð�
				in.add(rs.getString(4));//��¥
				in.add(rs.getString(5));//���� �ο���
				in.add(rs.getString(6));//������ �л�
				
//				��ü �����͸� �����ϴ� data ���Ϳ� 1���� ��(���ڵ�) �߰�

				data.add(in);

			}

		}catch(SQLException sqle){

			sqle.printStackTrace();

		}

	}

}

