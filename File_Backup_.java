package aug_06;
import java.util.*;
import java.sql.*;
import java.io.*;
class Manager{
	Scanner sc=new Scanner(System.in);
	String Querry,gt_loc,fl_name;
	String Central_Dir="D:\\";
	Connection conn;
	Statement stmt;
	Manager(){
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Backup_Manager","root","");
		stmt=conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void Login()
	{
		System.out.print("Enter username :");
		String name=sc.nextLine();
		System.out.print("Enter password :");
		String pword=sc.nextLine();
		Querry = "SELECT * FROM user WHERE user_name='"+name+"' AND password='"+pword+"'";

		try {
			ResultSet rs = stmt.executeQuery(Querry);
			if(rs.next()) {
				System.out.println("----Welcome "+name+"----\n");
				boolean t=true;
				while(t==true)
				{
					System.out.println("1: Create File\n2: Read Files\n3: Write File"
							+ "\n4: View/Open File Backup \n5: Previous Menu");
					int n=sc.nextInt();
					switch(n)
					{
					case 1:
					{
						Cr_File(name);
						break;
					}
					case 2:
					{
						Rd_File();
						break;
					}
					case 3:
					{
						Wr_File();
						break;
					}
					case 4:{
						
						Opn_Backup(name);
						break;
					}
					
					case 5:
					{
						t=false;
						break;
					}
					}
				}
			}
			else
				System.out.println("Incorrect UserName or Password OR No such User Exist");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void Cr_File(String USER) throws IOException
	{
		String temp=sc.nextLine();
		System.out.println("Enter the location eg.C:\\\\Users");
		
		gt_loc=sc.nextLine();
		System.out.println("Enter the File Name eg.abc.txt");
		fl_name=sc.nextLine();
		File f = new File((gt_loc+fl_name));
		File f1 = new File((Central_Dir+fl_name));
		f.createNewFile();
		f1.createNewFile();
		System.out.println("Flie Created at "+(gt_loc+fl_name));
		Querry="Insert into data Values('"+USER+"','"+fl_name+"','"+gt_loc+"')";
		try {
			stmt.executeUpdate(Querry);				
			}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	void Rd_File()throws IOException
	{
		String temp=sc.nextLine();
		System.out.println("Enter the location eg.C:\\\\Users");
		gt_loc=sc.nextLine();
		System.out.println("Enter the File Name eg.abc.txt");
		fl_name=sc.nextLine();
		FileReader fr =new FileReader((gt_loc+fl_name));
		BufferedReader br =new BufferedReader(fr);
		String line;
		while((line=br.readLine()) != null)
		{
			System.out.println(line);
		}
		br.close();
		fr.close();
	}
	void Wr_File()throws IOException
	{
		String temp=sc.nextLine();
		System.out.println("Enter the location eg.C:\\\\Users");
		gt_loc=sc.nextLine();
		System.out.println("Enter the File Name eg.abc.txt");
		fl_name=sc.nextLine();
		System.out.println("Start Writing"+"\t type: 'sv\\ext' to save and exit");
	/*	Querry="Insert into data Values('"+USER+"','"+fl_name+"','"+gt_loc+"')";
		try {
			stmt.executeUpdate(Querry);				
			}
			catch(Exception e) {
				System.out.println(e);
			}*/
		FileWriter fw=new FileWriter((gt_loc+fl_name),true);
		BufferedWriter bw = new BufferedWriter(fw);
		FileWriter fw1=new FileWriter((Central_Dir+fl_name),true);
		BufferedWriter bw1 = new BufferedWriter(fw1);
		String gt_line;
		String ext="sv\\ext";
		while(true)
		{
			gt_line=sc.nextLine();
			if(gt_line.equalsIgnoreCase(ext))
				break;
			else
			{
				gt_line=gt_line+"\n";
				bw.write(gt_line);
				bw1.write(gt_line);
			}
		}
		System.out.println("\n"+"Your data has been Written");
		bw.flush();
		bw1.flush();
		bw.close();
		bw1.close();
		fw.close();
		fw1.close();
	}
	void Opn_Backup(String USER)throws IOException
	{
		boolean t=true;
		while(t==true) {
			System.out.println("1: List All Files\n2: Open a file\n3: Previous Menu");
			int n=sc.nextInt();
			switch(n)
			{
			case 1:
			{
				Querry = "SELECT * FROM data WHERE user_name='"+USER+"'";
				System.out.println(USER+"'s Files:");
				try {
					ResultSet rs = stmt.executeQuery(Querry);
					if(rs.next()) {
					do{
						System.out.println(rs.getString(2));
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
			case 2:
			{
				String temp=sc.nextLine();
				System.out.println("Enter a file name");
				fl_name=sc.nextLine();
				Querry = "SELECT * FROM data WHERE user_name='"+USER+"' and file_name='"+fl_name+"'";
				try {
					ResultSet rs = stmt.executeQuery(Querry);
					if(rs.next()) {
				FileReader fr =new FileReader((Central_Dir+fl_name));
				BufferedReader br =new BufferedReader(fr);
				String line;
				while((line=br.readLine()) != null)
				{
					System.out.println(line);
				}
				br.close();
				fr.close();
				break;
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
			case 3:{
				t=false;
				break;
			}
			}
		}
	}
	void Signup()
	{
		System.out.print("Enter Username: ");
		String name=sc.nextLine();
		System.out.print("Enter password: ");
		String pword=sc.nextLine();
		Querry = "INSERT INTO user VALUES ('"+name+"','"+pword+"')";
		try {
			stmt.executeUpdate(Querry);
			System.out.println("User created");					
			}
			catch(Exception e) {
				System.out.println("UserName already Taken try a different Username");
			}
	}
}
public class File_Backup_ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		Manager m=new Manager();
		while(true)
		{
			System.out.println("1: Login\n2: Signup\n3: Exit");
			int n=in.nextInt();
			switch(n) {
			case 1:
			{
				m.Login();
				break;
			}
			case 2:
			{
				m.Signup();
				break;
			}
			case 3:
			{
				System.exit(0);
			}
			}
		}
	}

}
