package aug_06;
import java.util.*;
//import java.nio.*;
import java.io.*;
class FileOp{
	Scanner in=new Scanner(System.in);
	String gt_loc;
}
class Create extends FileOp{
	void get_location() throws IOException
	{
		System.out.println("Enter the location and file name you want to save"+"\n"
				+"eg.C:\\\\Users\\\\abc.txt");
		gt_loc=in.nextLine();
		crt_file();
		System.out.println("Flie Created at "+gt_loc);
	}
	void crt_file() throws IOException
	{
		File f = new File(gt_loc);
		f.createNewFile();
	}
}
class Read extends FileOp{
	/*Scanner re=new Scanner(System.in);
	String r;*/
	void get_location() throws IOException
	{
		System.out.println("Enter the location and file name you want to read"+"\n"
				+"eg.C:\\\\Users\\\\abc.txt");
		gt_loc=in.nextLine();
		rd_file();
	}
	void rd_file() throws IOException
	{	
		FileReader fr =new FileReader(gt_loc);
		File f=new File(gt_loc);
		BufferedReader br =new BufferedReader(fr);
		String line;
		int line_count=0;
		int doc_length=0;
		int word_count=0;
		while((line=br.readLine()) != null)
		{
			System.out.println(line);
			line_count++;
			String[]temp=line.split("[ .?=*@#&%]+");
			word_count+=temp.length;
			doc_length+=f.length();
		}
		br.close();
		fr.close();
		System.out.println("\nlenght:"+doc_length+"  lines:"+line_count+"  words:"+word_count);
	}
}
class Write extends FileOp{

	void get_location() throws IOException
	{
		System.out.println("Enter the location and file name you want to Write"+"\n"
				+"eg.C:\\\\Users\\\\abc.txt");
		gt_loc=in.nextLine();
		System.out.println("Start Writing"+"\t type: 'sv\\ext' to save and exit");
		wr_file();
	}
	void wr_file() throws IOException
	{
		FileWriter fw=new FileWriter(gt_loc,true);
		BufferedWriter bw = new BufferedWriter(fw);
		String gt_line;
		String ext="sv\\ext";
		while(true)
		{
			gt_line=in.nextLine();
			if(gt_line.equalsIgnoreCase(ext))
				break;
			else
			{
				gt_line=gt_line+"\n";
				bw.write(gt_line);
			}
		}
		System.out.println("\n"+"Your data has been Written");
		bw.flush();
		bw.close();
		fw.close();
	}
}
class Find_File extends FileOp{
	void search_file() {
		System.out.println("Enter the File name you want to search");
		String name=in.nextLine();
		System.out.println("Enter the Directory you want to search "+"eg. C:\\\\Users\\\\abc.txt");
		gt_loc=in.nextLine();
		
		File f=new File(gt_loc);
		File[] list = f.listFiles();
        if(list!=null)
        for (File fil : list)
        {
            if (name.equalsIgnoreCase(fil.getName()))
            {
                System.out.println("File "+name+" exists at:"+fil.getParentFile());
            }
            else 
            	continue;
        }
	}
}
public class txt_edit {

	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		System.out.println("-------------------Welcome to file Handeling 101------------------");
		while(true){
		System.out.println("1. Create file");
		System.out.println("2. Write data in file");
		System.out.println("3. Read data from File");
		System.out.println("4. Find Files");
		System.out.println("5. Exit");
		int n=sc.nextInt();
		switch(n) {
			case 1:{
				Create create=new Create();
				create.get_location();
				break;
			}
			case 2:{
				Write write=new Write();
				write.get_location();
				break;
			}
			case 3:{
				Read read=new Read();
				read.get_location();
				break;
			}
			case 4:
			{
				Find_File find=new Find_File();
				find.search_file();
				break;
			}
			case 5:System.exit(0);
			default:System.out.println("Option not valid please choose again");
		}	
	}
}
}
