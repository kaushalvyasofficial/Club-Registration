import java.io.*;
public class MemberView extends Misc {
	private boolean check;
	public void MainView(boolean flg) throws IOException, InterruptedException {
		Asc();
		check = flg;
		int n = 0, choice = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only 1,2,3,4,5,6,7,8 or 9!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS!\n");
			n = 0;

			P("\n\n\t\t\t  **********VIEW RECORDS**********\n\n");
			P("\t\t|Sr.No.|               Choice                  |");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|1.    |%-39s|\n", "View records by entering member ID.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|2.    |%-39s|\n", "View records by entering first name.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|3.    |%-39s|\n", "View records by entering last name.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|4.    |%-39s|\n", "View records by entering phone number.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|5.    |%-39s|\n", "View records by entering gender.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|6.    |%-39s|\n", "View records by entering city.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|7.    |%-39s|\n", "View records by entering the activity.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|8.    |%-39s|\n", "View all records.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.format("\t\t|9.    |%-39s|\n", "Return to previous menu.");
			P("\t\t|" + dash(5) + "|" + dash(38) + "|");
			System.out.print("\nEnter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice < 1 || choice > 9) n = 1;
			}
			catch(NumberFormatException e) {n = 2;}
		}while(n > 0);

		load();
		switch(choice) {
		case 1: viewList(); break;
		case 2:	view(1); break;
		case 3:	view(2); break;
		case 4: view(3); break;
		case 5:	view(4); break;
		case 6:	view(5); break;
		case 7: view(6); break;
		case 8: viewActs(); break;
		case 9: if(check) menu(2); else menu(3); break;
		}
		System.exit(0);
	}
	public void viewList() throws IOException, InterruptedException {
		cls();
		BufferedReader fr = read(1);
		String str = fr.readLine(), ID = IDCheck(1, check);
		cls();
		while(str != null) {
			String[] arr = str.split(",");
			if(arr[0] == ID) {
				String[] Acts = {arr[6], arr[7], arr[8], arr[9]};
				Acts = ActName(Acts);
				P("\t\t\t*****MEMBER DETAILS*****\n");
				P("1.Member Id......" + arr[0] + "\n");
				P("2.First Name....." + arr[1] + "\n");
				P("3.Last Name......" + arr[2] + "\n");
				P("4.Phone No......." + arr[3] + "\n");
				P("5.Gender........." + arr[4] + "\n");
				P("6.City..........." + CityName(Integer.parseInt(arr[5])) + "\n");
				P("7.Activity1......" + Acts[0] + "\n");
				P("8.Activity2......" + Acts[1] + "\n");
				P("9.Activity3......" + Acts[2] + "\n");
				P("10.Activity4....." + Acts[3] + "\n");
				P("-------------------------------------\n");
				break;
			}
			str = fr.readLine();
		}
		fr.close();
		GoToView();
	}
	public void view(int n) throws IOException, InterruptedException {
		int ct = 0;
		String Entity;
		boolean f = false;
		switch(n) {
		case 1: Entity = NCheck(1); break;
		case 2: Entity = NCheck(2); break;
		case 3: Entity = PNCheck(); break;
		case 4: Entity = GRCheck(); break;
		case 5: Entity = CityName(CNCheck()); break;
		default: Entity = Integer.toString(ATCheck()); f = true; 
		}

		BufferedReader fr = read(1);
		String str = fr.readLine();
		cls();

		P("\t\t\t*****VIEW RECORDS*****\n");
		while(str != null) {
			String[]arr = str.split(",");
			if(!f) { 
				if(arr[n] == Entity) {
					ct++;
					DetailView(arr, ct);
				}
			}
			else if(arr[6] == Entity || arr[7] == Entity || arr[8] == Entity || arr[9] == Entity) {
				ct++;
				DetailView(arr, ct);
			}
			str = fr.readLine();
		}
		fr.close();
		if(!f) GoToView();
		else {
			P("\n Press any key to go back to the previous menu.");
			br.readLine(); load(); view(6);
		}
	}
	public void viewActs() throws IOException, NumberFormatException, InterruptedException {
		cls();
		P("\t\t\t**********VIEW RECORDS**********\n");

		P("(Note:- The Table Given Below Is The List Of All\n");
		P("The Activities With Corresponding Numbers.\n");
		P("In Viewing All Records, Only The Corresponding Numbers Will Be Shown).\n");
		P("\t\t\t********ACTIVITIES********\n");
		BufferedReader fr = read(2);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			P("\t\t\t" + arr[0] + "." + arr[1]);
			P("\t\t\t__________________________");
			str = fr.readLine();
		}
		fr.close();

		System.out.print("\nPress enter to view all the records:- ");
		br.readLine();
		cls();
		P("\n\t\t\t********ACTIVITIES********\n");
		fr = read(2);
		str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			P("\t\t\t" + arr[0] + "." + arr[1]);
			P("\t\t\t__________________________");
			str=fr.readLine();
		}
		fr.close();

		P("\n\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");
		P("\t\t\t**********VIEW RECORDS**********\n\n");
		int ct = 0;
		fr = read(1);
		str = fr.readLine();
		while(str != null) {
			ct++;
			String[] arr = str.split(",");
			P(ct + ".");
			P("-----------------------------------------------------------------");
			P("Member Id:-" + arr[0] + ".");
			P("First Name:-" + arr[1] + ".");
			P("Last Name:- " + arr[2] + ".");
			P("Phone Number:- " + arr[3] + ".");
			P("Gender:- " + arr[4] + ".");
			P("City:- " + CityName(Integer.parseInt((arr[5]))) + ".");
			P("Activities:- " + arr[6] + "," + arr[7] + "," + arr[8] + "," + arr[9] + ".");
			P("");
			P("-----------------------------------------------------------------\n");
			str = fr.readLine();
		}
		GoToView();
	}
	public String NCheck(int x) throws IOException, NumberFormatException, InterruptedException {
		int n = 0;
		String Name, SearchName;
		if(x == 1) Name = "First Name"; else Name = "Last Name"; 
		do {
			cls();
			if(n==1) P("Your Input " + Name + " does not exist!\n");
			else if(n==2) P("Error! Enter only LETTERS!\n");

			n=0;
			P("(Enter VM/ vm/ vM/ Vm to return to previous menu)\n");
			P("\t\t\t*****View Record*****");
			System.out.print("\nEnter the " + Name + " :- ");
			SearchName = br.readLine().trim().toUpperCase();
			if(SearchName == "VM") GoToView();

			BufferedReader fr = read(1);
			String str = fr.readLine();
			while(str != null) {
				String[] Detail = str.split(",");
				if(Detail[x] == SearchName) {n=0; break;}
				else n=1;
				str = fr.readLine();
			}
			fr.close();
			if(n == 1)
				for(int i = 0; i < SearchName.length(); i++)
					if(!Character.isLetter(SearchName.charAt(i))) {n=2; break;}
		}while(n > 0);
		cls();
		return SearchName;
	}
	public String PNCheck() throws IOException, NumberFormatException, InterruptedException{
		int n = 0;
		String PhNo = null;
		do {
			cls();
			if(n == 1) P("\"NumberFormatException\" - Enter only NUMBERS(10 DIGITS)!\n");
			else if(n == 2)	P("Error! Number must have 10 digits!\n");
			else if(n == 3) P("Your input Phone Number does not exist!\n");
			n = 0;
			P("(Enter VM/ vm/ vM/ Vm to return to previous menu)\n");
			P("\t\t\t*****View Record*****");
			System.out.print("\nEnter the Phone Number:- ");
			try {
				PhNo = br.readLine().trim().toUpperCase();
				if(PhNo == "VM") GoToView();
				Integer.parseInt(PhNo);
			}
			catch(NumberFormatException e){n = 1;}

			if(PhNo.length() < 10 ||  PhNo.length() > 10) n = 2;

			BufferedReader fr = read(1);
			String str = fr.readLine();

			while(str != null) {
				String[] arr = str.split(",");
				if(arr[3] == PhNo) {n = 0; break;}
				else n = 3;
				str = fr.readLine();
			}
			fr.close();
		}while(n > 0);
		cls();
		return PhNo;
	}
	public String GRCheck() throws IOException, NumberFormatException, InterruptedException	{
		int n = 0, choice = 0;
		String gen = "";
		do {
			cls();
			if(n == 1) P("Error! Enter only 1, 2, 3 or 4!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS(1 - 4)!\n");
			else if(n == 3) P("The inputted gender does not exist!");

			n = 0;
			P("\t\t\t**********View Record**********\n");
			P("\t\t\t|Sr.No.|        Choice        |");
			P("\t\t\t|______|______________________|");
			P("\t\t\t|1.    |Male.                 |");
			P("\t\t\t|______|______________________|");
			P("\t\t\t|2.    |Female.               |");
			P("\t\t\t|______|______________________|");
			P("\t\t\t|3.    |Other                 |");
			P("\t\t\t|______|______________________|");
			P("\t\t\t|4.    |Return to View Menu.  |");
			P("\t\t\t|______|______________________|");
			System.out.print("\nEnter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice < 1 || choice > 4) {n = 1; continue;}
			}
			catch(NumberFormatException e) {n = 2; continue;}

			if(choice == 1) gen = "MALE";
			else if(choice == 2) gen = "FEMALE";
			else if(choice == 3) gen = "OTHER";
			else {load(); MainView(check);}

			BufferedReader fr = read(1);
			String str = fr.readLine();
			while(str != null){
				String[] arr = str.split(",");
				if(arr[4] == gen) {n = 0; break;}
				else n = 3;
				str = fr.readLine();
			}
			fr.close();
		}while(n > 0);
		return gen;
	}
	public int CNCheck() throws IOException, NumberFormatException, InterruptedException {
		int n = 0, choice = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only 1, 2, 3, 4, 5, 6, 7 or 8!\n");
			else if(n == 2)	P("\"NumberFormatException\" - Enter only NUMBERS(1 - 8)!\n");
			else if(n == 3)	P("The input city does not exist!");

			n = 0;
			P("\t\t\t**********View Record**********\n");
			CityOpt();
			P("\t\t\t|8     |RETURN TO VIEW MENU |");
			P("\t\t\t|______|____________________|");
			System.out.print("\n Enter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice < 1 || choice > 8){n = 1; continue;}
			}
			catch(NumberFormatException e){n = 2; continue;}

			if(choice == 8)	GoToView();
			else {
				BufferedReader fr = read(1);
				String str = fr.readLine();
				while(str != null) {
					String[] arr = str.split(",");
					if(Integer.parseInt(arr[5]) == choice){n = 0; break;}
					else n = 3;
					str = fr.readLine();
				}
				fr.close();
			}
		}while(n > 0);
		return choice;
	}
	public int ATCheck() throws IOException, NumberFormatException, InterruptedException {
		int n = 0, choice = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only the numbers beside the options!\n");
			else if(n == 2)	P("\"NumberFormatException\" - Enter only NUMBERS!\n");
			else if(n == 3)	P("The input activity does not exist!");
			n = 0;

			P("\t\t\t**********View Record**********\n\n");
			P("\t\t\t__________________________");
			int ct = 0;
			BufferedReader fr = read(2);
			String str = fr.readLine();
			while(str != null) {
				ct++;
				String[] arr = str.split(",");
				P("\t\t\t" + arr[0] + "." + arr[1]);
				P("\t\t\t__________________________");
				str = fr.readLine();
			}
			fr.close();
			P("\t\t\t" + ct + ".RETURN TO VIEW MENU");
			P("\t\t\t__________________________");
			System.out.print("\n Enter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice < 1 || choice > ct) {n = 1; continue;}
			}
			catch(NumberFormatException e){n = 2; continue;}

			if(choice == ct){load(); MainView(check);}
			else {
				fr = read(1);
				str = fr.readLine();
				while(str != null) {
					String[] arr = str.split(",");
					str = Integer.toString(choice);
					if(arr[6] == str || arr[7] == str || arr[8] == str || arr[9] == str){n = 0; break;}
					else n = 3;
					str = fr.readLine();
				}
				fr.close();
			}
		}while(n > 0);
		return choice;
	}
	public void GoToView() throws IOException, InterruptedException {
		P("\nPress enter to go back to the previous menu.");
		br.readLine(); load(); MainView(check);
	}
	public void DetailView(String[] arr, int ct) throws IOException	{
		String[] Act = {arr[6], arr[7], arr[8], arr[9]};
		Act = ActName(Act);
		if(ct != 0) P("\n"+ct+".");

		P("---------------------------------------\n");
		P("0.ProductID......" + arr[0] + "\n");
		P("1.First Name....." + arr[1] + "\n");
		P("2.Last Name......" + arr[2] + "\n");
		P("3.Phone No......." + arr[3] + "\n");
		P("4.Gender........." + arr[4] + "\n");
		P("5.City..........." + CityName(Integer.parseInt(arr[5])) + "\n");
		P("6.Activity1......" + Act[0] + "\n");
		P("7.Activity2......" + Act[1] + "\n");
		P("8.Activity3......" + Act[2] + "\n");
		P("9.Activity4......" + Act[3] + "\n");
		P(dash(38) + "\n");
	}
	public static void main(String[] a) {
		try {new MemberView().MainView(true);}
		catch(IOException e){System.out.println("IOError!!!");}
		catch(InterruptedException e){System.out.println("InterruptedError!!!");}
	}
}
