package aug_06;
import java.util.*;
import java.sql.*;
import java.lang.*;
class Queries{
	Statement stmt;
	Connection con;
	String query;
	Scanner sc = new Scanner(System.in);
	public Queries(){
		try{  
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/blood_bank","root","");
			stmt=con.createStatement();  
		}
		catch(Exception e){ 
				System.out.println(e);
		} 
	}
	void login() {
	
		System.out.print("Enter username :");
		String name=sc.nextLine();
		System.out.print("Enter password :");
		String pword=sc.nextLine();
		query = "SELECT * FROM user WHERE user_name='"+name+"' AND password='"+pword+"'";
		boolean t=true;
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				System.out.println("----Welcome "+name+"----\n");
				while(t == true) {
				System.out.println("1: Find Users by blood type:");
				System.out.println("2: go to main menu");
				System.out.println("3: Exit Program");
				int n=sc.nextInt();
				switch(n) {
				case 1:{
					find();
					break;
				}
				case 2:
					t=false;
					break;
					
				case 3:{
					System.exit(0);
				}
				}
				}
			}
			else
				System.out.println("Incorrect Username or Password");
		}
		catch(Exception e)
		{
			System.out.println("Error in try block querry execution");
		}
		
		
	}
	void signup() {
	
		System.out.print("Enter Username: ");
		String name=sc.nextLine();
		query = "SELECT * FROM user WHERE user_name='"+name+"'";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				System.out.println("name already taken");
			}
			else {
				System.out.print("Enter password: ");
				String pword=sc.nextLine();
				query = "INSERT INTO user VALUES ('"+name+"','"+pword+"')";
				
				try {
					stmt.executeUpdate(query);
					System.out.println("User created");
					
					}
					catch(Exception e) {
						System.out.println("unable to add");
					}
				donate();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error creating user");
		}		
	}
	void donate() {

		System.out.println("Enter Details : ");
		System.out.print("Name : ");
		String name = sc.nextLine();
		System.out.print("Age : ");
		int age = sc.nextInt();
		System.out.print("Blood Type : ");
		String btype = sc.next();
		System.out.print("Gender(M/F) : ");
		char gender = sc.next().charAt(0);
		System.out.print("Date(yyyy-mm-dd) : ");
		String date = sc.next();
		System.out.print("Phone Number : ");
		String phone = sc.next();
		System.out.print("City : ");
		sc.nextLine();
		String address = sc.nextLine();
		query = "INSERT INTO details VALUES ('"+name+"',"+age+",'"+btype+"','"+gender+"','"+
												date+"',"+phone+",'"+address+"')";
		try {
		stmt.executeUpdate(query);
		System.out.println("Details Inserted into DB");
		}
		catch(Exception e) {
			System.out.println("Error while running the Insert Query");
		}

	}
	void find() {
	
		System.out.print("Blood Type : ");
		String btype = sc.next();
		query = "SELECT * FROM details WHERE blood_type='"+btype+"'";
		try {
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
		do{
			System.out.println(rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getString(3)+"\t"
								+rs.getString(4)+"\t"+rs.getDate(5)+"\t"+rs.getString(6)+
								"\t"+rs.getString(7));
			}while(rs.next());
		}
		else
			System.out.println("Blood Group Not Available");
		}
		catch(Exception e) {
			System.out.println("Error while finding Blood Group");
			System.out.println(e.getMessage());
		}
	}
}
public class Blood_Donation {

	public static void main(String[] args) {
		System.out.println("Welcome to the Blood Bank.");
		Queries obj = new Queries();
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("1: Login\n2: Signup\n3: Exit");
			int n=in.nextInt();
			switch(n) {
			case 1:{
				obj.login();
				break;
			}
			case 2:
				obj.signup();
				break;
			case 3:
				System.exit(0);
			}
		}
	}
}
