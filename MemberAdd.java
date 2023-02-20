
import java.io.*;
public class MemberAdd extends Misc {
	public void Add() throws IOException, NumberFormatException, InterruptedException {
		Asc();	cls();
		int MemNum = MemCheck(), MemArc = ArcCheck(0, false);

		if(MemNum < MemArc)	MemNum = MemArc;
		MemNum++;

		P("\t\t\t**********MEMBERSHIP**********");
		P("\n Your member id is:- M" + MemNum);
		PrintWriter pw = new PrintWriter(new FileWriter("members.dat", true));
		pw.print("M" + MemNum + "," + FirstName() + "," + LastName() + 
		"," + PhoneNo() + "," + Gender() + "," + City() + ",");

		ActMeth();
		for(int i = 0; i < Act.length; i++)
			for(int j = 0 ; j < Act.length; j++)
				if(Act[i] < Act[j]) {
					int temp = Act[i];
					Act[i] = Act[j];
					Act[j] = temp;
				}

		pw.println(Act[0] + "," + Act[1] + "," + Act[2] + "," + Act[3]);
		pw.close();
		cls(); load(); cls();
		boolean flg = false;
		P("\n\n\t\t>>>>>>>>> Member has been added successfully!!! <<<<<<<<<\n\n");

		do {
			if(flg) P("Error! Enter only Y/ y for yes or N/ n for no!\n");
			flg = false;
			System.out.print(" Do you wish to add more members? (Enter Y/ y for yes or N/ n for no):- ");
			char ch = Character.toUpperCase((char)br.read());
			br.readLine();
			if(ch == 'Y') Add();
			else if(ch == 'N') menu(3);
			else flg = true;
		}while(flg);
	}
	public String FirstName() throws IOException {
		String fname;
		boolean flg = false;
		do {
			if(flg) P("\nError! Enter only LETTERS!!!");
			flg = false;
			System.out.print("\n1. Enter your First Name:- ");
			fname = br.readLine().trim().toUpperCase();
			for(int i = 0; i < fname.length(); i++)
				if(!Character.isLetter(fname.charAt(i))) {flg = true; break;}
		}while(flg);
		return fname;
	}
	public String LastName() throws IOException {
		String lname;
		boolean flg = false;
		do {
			if(flg) P("\nError! Enter only LETTERS!!!");
			flg = false;
			System.out.print("\n2. Enter your Last Name:- ");
			lname = br.readLine().trim().toUpperCase();
			for(int i = 0; i < lname.length(); i++)
				if(!Character.isLetter(lname.charAt(i))) {flg = true; break;}
		}while(flg);
		return lname;
	}
	public long PhoneNo() throws IOException, NumberFormatException, InterruptedException {
		long PhNo = 0;
		byte n = 0;
		cls();
		do {
			if(n == 1) P("\n\"NumberFormatException\" - Enter Only DIGITS!!!");
			else if(n == 2) P("\nError! Enter 10 Digits!!!");
			n = 0;

			System.out.print("\n3. Enter your Phone Number:- ");
			try {PhNo = Long.parseLong(br.readLine().trim());}
			catch(NumberFormatException e) {n = 1;}
			if(Long.toString(PhNo).length() != 10) n = 2;
		}while(n > 0);
		return PhNo;
	}
	public String Gender() throws IOException, InterruptedException {
		int Gen = 0, n = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only 1, 2, 3!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS(1 - 3)!!!\n");
			n = 0;

			P("\t\t\t***********GENDER***********\n");
			P("\t\t\t|Sr.No.|...Choice...|");
			P("\t\t\t|______|____________|");
			P("\t\t\t|1.    |Male        |");
			P("\t\t\t|______|____________|");
			P("\t\t\t|2.    |Female      |");
			P("\t\t\t|______|____________|");
			P("\t\t\t|3.    |Others      |");
			P("\t\t\t|______|____________|");
			System.out.print("\n\n Enter your choice:- ");
			try {
				Gen = Integer.parseInt(br.readLine());
				if(Gen < 1 || Gen > 3) n = 1;
			}
			catch(NumberFormatException e) {n = 2;}
		}while(n > 0);
		if(Gen == 1) return "MALE";
		else if(Gen == 2) return "FEMALE";
		return "OTHER";
	}
	public int City() throws IOException, NumberFormatException, InterruptedException {
		int n = 0, choice = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only 1, 2, 3, 4, 5, 6 or 7!!!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter Only DIGITS(1 - 7)!!!\n");
			n = 0;
			CityOpt();
			System.out.print("\n Enter your choice:- ");
			
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice < 1 || choice > 7) n = 1;
			}
			catch(NumberFormatException e) {n = 2;}
		}while(n > 0);
		return choice;
	}
	int[] Act = new int[4];
	public void ActMeth() throws IOException, NumberFormatException, InterruptedException {
		for(int x = 1; x < 5; x++) {
		int n = 0,ct = 0;
		do {
			ct = 0;
			BufferedReader fr = read(2);
			String str = fr.readLine();
			cls();
			if(n == 1) P("Error! Enter only the numbers at the side of each activity!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS!");
			n = 0;

			P("\t\t\t***************Activities***************\n");
			System.out.format("\t\t\t|%-31s|\n","Sr.No.|\tChoice.");
			P("\t\t\t|______|_______________________________|");
			while(str != null) {
				String[] arr = str.split(",");
				System.out.format("\t\t\t|%-38s|\n", arr[0] + ".    | " + arr[1]);
				P("\t\t\t|______|_______________________________|");
				str = fr.readLine();
				ct++;
			}
			System.out.print("\n Enter Your Choice:- ");
			try {
				Act[x - 1] = Integer.parseInt(br.readLine());
				switch(x) {
				case 1: if(Act[0] < 1 || Act[0] > ct) n = 1; break;
				case 2: if(Act[1] < 1 || Act[1] > ct || Act[1] == Act[0]) n = 1; break;
				case 3: if(Act[2] < 1 || Act[2] > ct || Act[0] == Act[2] || Act[1] == Act[2]) n = 1; break;
				case 4: if(Act[3] < 1 || Act[3] > ct || Act[0] == Act[3] || Act[1] == Act[3]
				 || Act[2] == Act[3]) n = 1; break;
				}
			}
			catch(NumberFormatException e) {n = 2;}
			fr.close();
		}while(n > 0);
	}
	}
	public int MemCheck() throws IOException {
		int max = 0;
		BufferedReader fr = read(1);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			if(Integer.parseInt(arr[0].substring(1)) > max)	max = Integer.parseInt(arr[0].substring(1));
			str = fr.readLine();
		}
		fr.close();
		return max;
	}
	public int ArcCheck() throws IOException {
		int max = 0;
		BufferedReader fr = read(3);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			if(Integer.parseInt(arr[0].substring(1)) > max) max = Integer.parseInt(arr[0].substring(1));
			str = fr.readLine();
		}
		fr.close();
		return max;
	}
	public static void main(String[] a) throws Exception {
		try {new MemberAdd().ActMeth();}
		catch(IOException e) {System.out.println("IOError!!!");}
		catch(InterruptedException e) {System.out.println("InterruptedError!!!");}
	}
}