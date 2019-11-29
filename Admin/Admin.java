package classRoom;

import java.awt.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class Admin extends JFrame {
	/**
	 * @변수명 : data
	 * @설명 : 테이블에 표시될 데이터를 저장하는 벡터입니다.
	 */
	private Vector<Vector> data;
	/**
	 * @변수명 : in
	 * @설명 : 테이블에 표시될 1개의 줄(레코드)를 저장하는 벡터입니다.
	 */
	private Vector<String> in;
	
	/**
	 * @변수명 : title
	 * @설명 : 테이블에 표시될 타이틀을 저장하는 벡터입니다.
	 */
	private Vector<String> title;
	
	/**
	 * @변수명 : connetionUrl
	 * @설명 : 데이터베이스 연동시 사용될 URL을 저장하는 문자열입니다.
	 */
	private String connetionUrl = ""

	
	/**
	 * @변수명 : user
	 * @설명 : 데이터베이스 연동시 사용될 user를 저장하는 문자열입니다.
	 */
	private String user = ""


	/**
	 * @변수명 : password
	 * @설명 : 데이터베이스 연동시 사용될 password를 저장하는 문자열입니다.
	 */
	private String password = ""


	/**
	 * @변수명 : conn
	 * @설명 : 데이터베이스 연동시 사용될 Connection을 저장하는 문자열입니다
	 */
	private Connection conn = null;
	
	/**
	 * @변수명 : stmt
	 * @설명 : 데이터베이스 연동후 쿼리 결과를 구하는 객체입니다.
	 */
	private Statement stmt = null;
	
	/**
	 * @생성자명 : Admin
	 * @설명 : Frame을 생성하고, 테이블 생성 , model에 사용할 데이터(벡터) 생성하는 생성자입니다.
	 */
	public Admin() {

		super("강의실 예약 현황");
		

//		테이블에 사용될 타이틀과 데이터를 저장하는 벡터 객체 생성
		title = new Vector<>();
		data = new Vector<>();
		
//		테이블에 사용될 타이틀 벡터 초기화

		title.add("강의실번호");
		title.add("시간");
		title.add("날짜");
		title.add("예약 인원수");
		title.add("예약한 학생");
		

//		테이블에 사용될 데이터를 구하는 메소드입니다.

		//getData();

//		테이블(모델추가, title, data) 생성, 스크롤에 추가

		JTable table = new JTable(data, title);

		JScrollPane sp = new JScrollPane(table);
//		화면 컨테이너 생성

		Container c = getContentPane();
		
//		화면에 테이블 추가
		c.add(sp,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Frame Close시 자원 반납 처리

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
	 * @설명 : 테이블에 표시될 데이터 setting 메소드입니다.
	 */
	private void getData() {

		try{

//			1. 드라이버 로딩

			Class.forName("oracle.jdbc.driver.OracleDriver");

		}catch(ClassNotFoundException e){

			e.printStackTrace();

		}
		
		try{
			
//			2. 데이터베이스 Connection 생성
			conn = DriverManager.getConnection(connetionUrl,user,password);

//			3. 쿼리 실행 Statement 생성
			stmt = conn.createStatement();
			String sql = "select * from member";
			
//			4. 쿼리 실행후 결과를 ResultSet 객체에 setting
			ResultSet rs = stmt.executeQuery(sql);
			
//			5. 쿼리결과를 벡터에 반복해서 처리하는 반복문
			while(rs.next()){
//				데이터베이스에서 1개의 줄(레코드)를 저장하는 벡터 생성 및 값 입력
				in = new Vector<>();

				in.add(rs.getString(2));//강의실번호
				in.add(rs.getString(3));//시간
				in.add(rs.getString(4));//날짜
				in.add(rs.getString(5));//예약 인원수
				in.add(rs.getString(6));//예약한 학생
				
//				전체 데이터를 저장하는 data 벡터에 1개의 줄(레코드) 추가

				data.add(in);

			}

		}catch(SQLException sqle){

			sqle.printStackTrace();

		}

	}

}

