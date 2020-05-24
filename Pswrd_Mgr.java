package aug_06;

import java.sql.*;
import java.util.*;

class Pword{
	Scanner sc = new Scanner(System.in);
	Statement stmt;
	Connection con;
	String query;
	public Pword(){
		try{  
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/Pswrdmgr","root","");
			
			stmt=con.createStatement();  
		}
		catch(Exception e){ 
				System.out.println(e);
		} 
	}
	void signup()
	{
		System.out.print("Enter Username: ");
		String name=sc.nextLine();
		//query = "SELECT * FROM user WHERE user_name='"+name+"'";
		System.out.print("Enter password: ");
		String pword=sc.nextLine();
		query = "INSERT INTO user VALUES ('"+name+"','"+pword+"')";
		try {
			stmt.executeUpdate(query);
			System.out.println("User created");					
			}
			catch(Exception e) {
				System.out.println("UserName already Taken try a different Username");
			}
	}
	void get_details(String User)
	{
		boolean t=true;
		while(t == true)
		{
			System.out.println("1: Webiste password\n2: Mobile Password\n3: Bank Password\n"
					+ "4: Previous Menu\n5: Exit program");
			int c=sc.nextInt();
			switch(c) {
			case 1:
			{
				
				Website_Pass(User);
				break;
			}
			case 2:
			{
				
				Mobile_Pass(User);
				break;
			}
			case 3:
			{
				
				Bank_Pass(User);
				break;
			}
			case 4:
			{
				t=false;
				break;
			}
			case 5:
			{
				System.exit(0);
			}
			}
		}
		}
		
		//String temp=sc.nextLine();
	/*boolean confirm(String p)
	{
		String cp = sc.nextLine();
		if(cp.equals(p))
		{
			return true;
		}
		else
		{
			System.out.println("Password not confirmed");
			return false;
		}
	}*/
	void Website_Pass(String user)
	{
		String wbsite,sitepswrd;
		System.out.print("name of website:");
		String temp=sc.nextLine();
		wbsite=sc.nextLine();
		System.out.print("Enter password of website: ");
		sitepswrd=sc.nextLine();
		
		query = "INSERT INTO web VALUES ('"+user+"','"+wbsite+"','"+sitepswrd+"')";
		try {
			stmt.executeUpdate(query);
			System.out.println("Details Inserted into DB");
			}
			catch(Exception e) {
				System.out.println("Error while running the Insert Query");
			}
	}
	void Mobile_Pass(String user)
	{
		String mobilepas,mobile_no;
		System.out.print("Enter mobile Name:");
		String temp=sc.nextLine();
		mobile_no=sc.nextLine();
		System.out.print("Enter password of mobile: ");
		mobilepas=sc.nextLine();
		
		query = "INSERT INTO mobile VALUES ('"+user+"','"+mobile_no+"','"+mobilepas+"')";
		try {
			stmt.executeUpdate(query);
			System.out.println("Details Inserted into DB");
			}
			catch(Exception e) {
				System.out.println("Error while running the Insert Query");
			}
	}
	void Bank_Pass(String user)
	{
		String bankpas,acc_no;
		System.out.print("Enter the Account No:");
		String temp=sc.nextLine();
		acc_no=sc.nextLine();
		System.out.print("Enter password of bank: ");
		bankpas=sc.nextLine();
		
		query = "INSERT INTO bank VALUES ('"+user+"','"+acc_no+"','"+bankpas+"')";
		try {
			stmt.executeUpdate(query);
			System.out.println("Details Inserted into DB");
			}
			catch(Exception e) {
				System.out.println("Error while running the Insert Query");
			}
	}
	void login()
	{
			System.out.print("Enter username :");
			String name=sc.nextLine();
			System.out.print("Enter password :");
			String pword=sc.nextLine();
			query = "SELECT * FROM user WHERE user_name='"+name+"' AND password='"+pword+"'";
			try {
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()) {
					System.out.println("----Welcome "+name+"----\n");
					while(true) {
						System.out.println("1: List password:");
						System.out.println("2: Store password");
						System.out.println("3: Exit Program");
						int n=sc.nextInt();
						switch(n) {
						case 1:{
							find(name);
							break;
							}
						case 2:{
							get_details(name);
							break;
						}
						case 3:{
							System.exit(0);
							}
						}
					}
				}
				else
					System.out.println("Incorrect Username or Password  OR No such User");
			}
			catch(Exception e)
			{
				System.out.println("Error occured in login");
			}
	}
	void find(String nme)
	{
		//String  = sg.next();
		boolean t=true;
		while(t == true) {
			System.out.println("1: List all Passwords\n2: List WebSite Passwords\n3: List Mobile Passwords\n"
					+ "4: List Bank Passwords\n5: Previous Menu");
			int n=sc.nextInt();
			switch(n) {
			case 1:{
				query = "SELECT * FROM web WHERE user_name='"+nme+"'";
				System.out.println("Website Passwords:");
				try {
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
					do{
						System.out.println(rs.getString(2)+": "+rs.getString(3));
						}while(rs.next());
					}
					else
						System.out.println("Data Not Available");
					}
					catch(Exception e) {
						System.out.println("Error while finding Data");
						System.out.println(e.getMessage());
					}
				query = "SELECT * FROM mobile WHERE user_name='"+nme+"'";
				System.out.println("Mobile Passwords:");
				try {
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
					do{
						System.out.println(rs.getString(2)+": "+rs.getString(3));
						}while(rs.next());
					}
					else
						System.out.println("Data Not Available");
					}
					catch(Exception e) {
						System.out.println("Error while finding Data");
						System.out.println(e.getMessage());
					}
				query = "SELECT * FROM bank WHERE user_name='"+nme+"'";
				System.out.println("Bank Passwords:");
				try {
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
					do{
						System.out.println(rs.getString(2)+": "+rs.getString(3));
						}while(rs.next());
					}
					else
						System.out.println("Data Not Available");
					}
					catch(Exception e) {
						System.out.println("Error while finding Data");
						System.out.println(e.getMessage());
					}
				break;
			}
			case 2:{
				query = "SELECT * FROM web WHERE user_name='"+nme+"'";
				System.out.println("Website Passwords:");
				try {
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
					do{
						System.out.println(rs.getString(2)+": "+rs.getString(3));
						}while(rs.next());
					}
					else
						System.out.println("Data Not Available");
					}
					catch(Exception e) {
						System.out.println("Error while finding Data");
						System.out.println(e.getMessage());
					}
				break;
			}
			case 3:{
				query = "SELECT * FROM mobile WHERE user_name='"+nme+"'";
				System.out.println("Mobile Passwords:");
				try {
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
					do{
						System.out.println(rs.getString(2)+": "+rs.getString(3));
						}while(rs.next());
					}
					else
						System.out.println("Data Not Available");
					}
					catch(Exception e) {
						System.out.println("Error while finding Data");
						System.out.println(e.getMessage());
					}
				break;
			}
			case 4:{
				query = "SELECT * FROM bank WHERE user_name='"+nme+"'";
				System.out.println("Bank PAsswords:");
				try {
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
					do{
						System.out.println(rs.getString(2)+": "+rs.getString(3));
						}while(rs.next());
					}
					else
						System.out.println("Data Not Available");
					}
					catch(Exception e) {
						System.out.println("Error while finding Data");
						System.out.println(e.getMessage());
					}
				break;
			}
			case 5:{
				t=false;
				break;
			}
			}
		}
		
	}
}
public class Pswrd_Mgr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pword p=new Pword();
		Scanner in=new Scanner(System.in);
		while(true)
		{
			System.out.println("1: Login");
			System.out.println("2: Signup");
			System.out.println("3: Exit");
			int n=in.nextInt();
			switch(n)
			{
			case 1:
				{
					p.login();
					break;
				}
			case 2:
				{
					p.signup();
				break;
				}
			case 3:System.exit(0);	
			}
		}
	}

}
