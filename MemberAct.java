import java.io.*;
public class MemberAct extends Misc {
	public void change() throws IOException, InterruptedException {
		Asc();
		int n = 0, choice = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only 1, 2, 3, 4, or 5!\n");
			if(n==2) P("\"NumberFormatException\" - Enter only NUMBERS!\n");
			n = 0;

			P("\n\n\t\t\t  **********Activity Change**********\n\n");
			P("\t\t|Sr.No.|               Choice                  |");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|1.    |%-39s|\n", "Add activities.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|2.    |%-39s|\n", "Modify activity's name.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|3.    |%-39s|\n", "Archive activities.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|4.    |%-39s|\n", "View activities.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|5.    |%-39s|\n", "Return to admin menu.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.print("\nEnter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine().trim());
				if(choice < 1 || choice > 5) n = 1;
			}
			catch(NumberFormatException e) {n = 2;}
		}while(n > 0);
		load();
		cls();
		
		switch(choice) {
		case 1: add(); break;
		case 2: modify(); break;
		case 3: archive(); break;
		case 4: view(); break;
		default: menu(2);
		}
	}
	public void add() throws IOException, InterruptedException {
		Asc();
		int ActNum = ActCheck(), ActArc = ArcCheck(0, false);

		if(ActNum < ActArc)	ActNum = ActArc;
		ActNum++;

		boolean flg = false;
		do {
			cls();
			P("\n\t\t\t**********ADD ACTIVITIES**********\n");

			if(flg)	P("Error! Enter only letters!\n");
			flg = false;

			P("(Enter AC/ ac/ aC/ Ac to go back to the previous menu directly)\n");
			P("Your Activity No:- " + ActNum);
			System.out.print("\nEnter the activity name:- ");
			String act = br.readLine().trim().toUpperCase();
			if(act == "AC") {load(); change(); System.exit(0);}

			for(int i = 0; i < act.length(); i++)
				if(!Character.isLetter(act.charAt(i)) && !Character.isWhitespace(act.charAt(i))) {flg = true; break;}
			if(!flg) {
				PrintWriter pw = new PrintWriter(new FileWriter("activities.dat", true));
				pw.println(ActNum + "," + act);
				pw.close();
			}

		}while(flg);
		char ch = ' ';
		cls();
		load();
		
		do {
			cls();
			P("\n\t\t >>>>>>>Activity has been added successfully!!!<<<<<<<\n");
			if(ch == 'O') P("Error! Enter only yes or no!\n");
			P("Do you wish to add more activities? (Enter Y/ y for yes & N/ n for no)");
			ch = (char)br.read();
			br.readLine();
			ch = Character.toUpperCase(ch);
			
			if(ch == 'Y') add();
			else if(ch == 'N') {load(); cls(); change();}
			else ch = 'O';
		}while(ch == 'O');
	}
	public void modify() throws IOException, InterruptedException {
		Asc(); cls();
		P("\t\t\t  **********MODIFY ACTIVITIES**********\n");
		ActView();
		int num = LastActNum();
		System.out.format("\t\t\t|%-6s|", num + 1);
		System.out.format("%-31s|\n","Return to previous menu");
		P("\t\t\t|" + dash(5) + "|" + dash(30) + "|");

		int n = 0, choice = 0;
		do {
			if(n == 1) P("\nError! Enter only the numbers beside the activities!\n");
			else if(n == 2)	P("\n\"NumberFormatException\" -  Enter only NUMBERS!\n");
			n = 0;
			
			System.out.print("\n Enter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice == num + 1) {load(); change(); System.exit(0);}
				n = ActCheck(choice);
			}
			catch(NumberFormatException e) {n = 2;}
		}while(n > 0);

		BufferedReader fr = read(2);
		String str = fr.readLine();
		PrintWriter pw = new PrintWriter(new FileWriter("temp.dat"));
		while(str != null) {
			String[] arr = str.split(",");
			if(Integer.parseInt(arr[0]) == choice) pw.println(choice + "," + NewVal(arr[1]));
			else pw.println(str);
			str = fr.readLine();
		}
		fr.close(); pw.close();
		File f1 = new File("temp.dat"), f2 = new File("activities.dat");
		f2.delete(); f1.renameTo(f2);

		char ch = ' ';
		cls(); load();
		do {
			cls();
			P("\n\t\t >>>>>>>Activity has been modified successfully!!!<<<<<<<\n");
			if(ch == 'O') P("Error! Enter only yes or no!\n");
			P("Do you wish to modify more activities? (Enter Y/ y for yes & N/ n for no)");
			ch = (char)br.read();
			br.readLine();
			ch = Character.toUpperCase(ch);
			
			if(ch == 'Y') modify();
			else if(ch == 'N') {load(); cls(); change();}
			else ch = 'O';
		}while(ch == 'O');
	}
	public void archive() throws IOException, InterruptedException {
		Asc(); cls();
		int num = NumOfActs();
		P("\n\t\t\t*********ARCHIVE ACTIVITIES**********");
		if(num < 6) {
			P("\nError!!! There are only " + num + " activities left.\n");
			P("You cannot archive activities if they are less than 6!\n");
			P("Add or Unarchive a minimum of " + (6 - num) + " activities.\n");
			P("Press enter to go back to the previous menu.");
			br.readLine();
			load(); change(); System.exit(0);
		}
		num = LastActNum();
		ActView();
		System.out.format("\t\t\t|%-6s|", num + 1);
		System.out.format("%-31s|\n", "Return to previous menu");
		P("\t\t\t|" + dash(5) + "|" + dash(30) + "|");

		int n = 0, choice = 0;
		do{
			if(n == 1) P("\nError! Enter only the numbers beside the activities!\n");
			else if(n == 2)	P("\n\"NumberFormatException\" -  Enter only NUMBERS!\n");
			n = 0;
			
			System.out.print("\n Enter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice == num + 1) {load(); change(); System.exit(0);}
				n = ActCheck(choice);
			}
			catch(NumberFormatException e) {n = 2;}
		}while(n > 0);
		P("\nYour activity is " + FindName(choice));
		char ch = ' ';
		do {
			if(ch == 'O') P("\nError! Enter only yes or no!\n");

			P("\nAre you sure you want to archive this record? (Enter Y/ y for yes and N/ n for no)");
			P("\n[Note:- Once archived, if needed, the activity must be archived manually].");
			ch = (char)br.read();
			br.readLine();
			ch = Character.toUpperCase(ch);

			if(ch == 'Y') break;
			else if(ch == 'N') {load(); archive(); System.exit(0);}
			else ch = 'O';
		}while(ch == 'O');

		BufferedReader fr = read(2);
		String str = fr.readLine();
		PrintWriter pw = temp();
		PrintWriter pw2 = new PrintWriter(new FileWriter("archiveAct.dat", true));
		while(str != null){
			String[] arr = str.split(",");
			if(Integer.parseInt(arr[0]) == choice) pw2.println(arr[0] + "," + arr[1]);
			else pw.println(str);
			str = fr.readLine();
		}
		pw.close(); pw2.close(); fr.close();

		File f1 = new File("temp.dat"), f2 = new File("activities.dat");
		f2.delete();
		f1.renameTo(f2);

		ch = ' ';
		cls(); load();
		do {
			cls();
			P("\n\t\t >>>>>>>Activity has been archived successfully!!!<<<<<<<\n");
			if(ch == 'O') P("Error! Enter only yes or no!\n");
			P("Do you wish to archive more activities? (Enter Y/ y for yes & N/ n for no)");
			ch = (char)br.read();
			br.readLine();
			ch = Character.toUpperCase(ch);
			if(ch == 'Y') archive();
			else if(ch == 'N') {load(); cls(); change();}
			else ch = 'O';
		}while(ch == 'O');
	}
	public void view() throws IOException, InterruptedException {
		Asc();
		P("\n\t\t\t   *********VIEW ACTIVITIES**********");
		ActView();
		P("\nNumber of activities are " + NumOfActs() + ".");
		P("\nPress enter to go back to the previous menu.");
		br.readLine();
		load(); change();
	}
	public int ActCheck() throws IOException {
		int max = 0;
		BufferedReader fr = read(2);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			if(Integer.parseInt(arr[0]) > max) max = Integer.parseInt(arr[0]);
			str = fr.readLine();
		}
		fr.close();
		return max;
	}
	public int ActCheck(int choice) throws IOException {
		int n = 0;
		BufferedReader fr = read(2);
		String str = fr.readLine();
		while(str !=  null) {
			String[] arr = str.split(",");
			if(Integer.parseInt(arr[0]) == choice) {n = 0; break;}
			else n = 1;
			str = fr.readLine();
		}
		fr.close();
		return n;
	}
	public void ActView() throws IOException {
		BufferedReader fr = read(2);
		String str = fr.readLine();
		System.out.format("\n\t\t\t|Sr.No.|\t%-23s|\n", "Activites");
		P("\t\t\t|" + dash(5) + "|" + dash(30) + "|");
		while(str != null) {
			String[] arr = str.split(",");
			System.out.format("\t\t\t|%-6s|", arr[0]);
			System.out.format("%-31s|\n", arr[1]);
			P("\t\t\t|" + dash(5) + "|" + dash(30) + "|");
			str = fr.readLine();
		}
		fr.close();
	}
	public String NewVal(String OldAct) throws IOException, InterruptedException {
		String act;
		boolean flg = false;
		do {
			if(flg) P("\nError! Enter only letters!\n");
			flg = false;
			P("\nYour Activity is:- " + OldAct + "\n");
			char ch = ' ';
			do {
				if(ch == 'O') P("\nError! Enter only yes or no!\n");
				P("\nAre you sure you want to modify it? (Enter Y/ y for yes & N/ n for no)");
				ch = (char)br.read();
				br.readLine();
				ch = Character.toUpperCase(ch);

				if(ch == 'Y') break;
				else if(ch == 'N') {load(); modify(); System.exit(0);}
				else ch = 'O';

			}while(ch == 'O');
			System.out.print("\nEnter the new activity:- ");
			act = br.readLine().trim().toUpperCase();

			for(int i = 0; i < act.length(); i++)
				if(!Character.isLetter(act.charAt(i)) && !Character.isWhitespace(act.charAt(i))) {flg = true; break;}
		}while(flg);
		return act;
	}
	public int LastActNum() throws IOException {
		int num = 0;
		BufferedReader fr = read(2);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			if(Integer.parseInt(arr[0]) > num) num = Integer.parseInt(arr[0]);
			str = fr.readLine();
		}
		fr.close();
		return num;
	}
	public int NumOfActs() throws IOException {
		int num = 0;
		BufferedReader fr = read(2);
		String str = fr.readLine();
		while(str != null) {num++; str = fr.readLine();}
		fr.close();
		return num;	
	}
	public String FindName(int choice) throws IOException {
		BufferedReader fr = read(2);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			if(Integer.parseInt(arr[0]) == choice) {str = arr[1]; break;}
			str = fr.readLine();
		}
		fr.close();
		return str;
	}
	public static void main(String[]args) {
		try {new MemberAct().change();}
		catch(IOException e) {System.out.println("IOError!");}
		catch(InterruptedException e) {System.out.println("InterruptedError!");}
	}
}