package login;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LoginMain {/*
	public void loginmain(String args[]) /*{
	
		System.out.println("register: 1, login: 2");
		Scanner keyboard = new Scanner(System.in);
		int select = keyboard.nextInt();
		System.out.println("input your ID");
		int Id = keyboard.nextInt();
		if(select==1) {
			
			LoginMain.register(Id);
		}
		else if (select ==2) {
			while(!LoginMain.login(Id)) {
				System.out.println("ID not found");
			}
		}
		
	}*/
	public static boolean login(int Id) {
		//데이터베이스에 ID select
		Statement state = null;
		try {
			String sql; //SQL문을 저장할 String
			sql = "SELECT * FROM student WHERE ID ="+Id;
			ResultSet rs = state.executeQuery(sql);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public static void register(int Id,String name) {
		//데이터베이스에 ID insert
		System.out.println("id: "+Id+", Name: "+name);
	}
}
