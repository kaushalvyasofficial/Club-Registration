import java.io.*;
public class MemberModify extends Misc {
	int city = 1;
	public void modify() throws IOException, InterruptedException {
		Asc(); cls();
		String ID = IDCheck(2, true);
		ModChoice(ID);
		File f1 = new File("temp.dat"), f2 = new File("members.dat");
		f2.delete(); f1.renameTo(f2);
		cls(); load();
		char ch = ' ';
		do {
			cls();
			if(ch == 'O') P("\nError! Enter only yes or no!");
			P("\n\t\t>>>>>>>MEMBER DETAILS HAVE BEEN MODIFIED SUCCESSFULLY!<<<<<<<\n");
			P("Do you wish to modify more records? (Enter Y/ y for yes and N/ n for no)");
			ch = (char)br.read();
			br.readLine();
			ch = Character.toUpperCase(ch);
			if(ch == 'Y') modify();
			else if(ch == 'N') menu(2);
			else ch = 'O';
		}while(ch == 'O');
	}
	public void ModChoice(String ID) throws IOException, NumberFormatException, InterruptedException {
		cls();
		BufferedReader fr = read(1);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			if(arr[0] == ID) {
				int choice = DetailView(arr);
				char ch = ' ';
				do {
					if(ch == 'O') P("Error! Enter only yes or no!\n");
					P("Are you sure you want to modify it? (Enter Y/ y for yes & N/ n for no)\n");
					ch = (char)br.read();
					br.readLine();
					ch = Character.toUpperCase(ch);
					if(ch == 'Y') {SendModify(choice, ID); break;}
					else if(ch == 'N') ModChoice(ID);
					else ch = 'O';
				}while(ch == 'O');
				break;
			}
			str = fr.readLine();
		}
		fr.close();
	}
	public void SendModify(int choice, String ID) throws IOException, InterruptedException {
		String ans;

		switch(choice) {
			case 1: ans = Name(); break;
			case 2: ans = Name(); break;
			case 3: ans = Long.toString(PhoneNo()); break;
			case 4: ans = Gender(); break;
			case 5: ans = City(); break;
			default: ans = "act";
		}

		if(ans != "act") {
			PrintWriter pw = temp();
			BufferedReader fr = read(1);
			String str = fr.readLine();
			while(str != null) {
				String[] arr = str.split(",");
				if(arr[0] == ID)
					for(int i = 0; i < arr.length; i++) {
						if(i == choice) pw.print(ans + ",");
						else if(i == arr.length - 1) pw.println(arr[i]);
						else pw.print(arr[i] + ",");
					}
				else pw.println(str);
				str = fr.readLine();
			}
			fr.close(); pw.close();
		}
		else {
			PrintWriter pw = temp();
			BufferedReader fr = read(1);
			String str = fr.readLine();
			while(str != null) {
				String[] arr = str.split(",");
				if(arr[0] == ID) {
					int[] Act = new int[4];
					Act[0] = Integer.parseInt(arr[6]);
					Act[1] = Integer.parseInt(arr[7]);
					Act[2] = Integer.parseInt(arr[8]);
					Act[3] = Integer.parseInt(arr[9]);

					for(int i = 0; i < Act.length; i++)
						if(i == choice - 6)	Act[i] = ActMod(Act);
					for(int i = 0; i < Act.length; i++)
						for(int j = 0; j < Act.length; j++)
							if(Act[j] > Act[i]) {
								int t = Act[j];
								Act[j] = Act[i];
								Act[i] = t;
							}
						
					pw.println(arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3] + ","
					+ arr[4] + "," + arr[5] + "," + Act[0] + "," + Act[1] + "," + Act[2] + "," + Act[3]);
				}
				else pw.println(str);
				str = fr.readLine();
			}
			fr.close();	pw.close();
		}
	}
	public int ActMod(int[] Act) throws IOException, InterruptedException {
		int n = 0,ct = 0, acts = 0;
		do {
			ct = 0;
			BufferedReader fr = read(2);
			String str = fr.readLine();
			cls();
			if(n == 1) P("Error! Enter only the numbers at the side of each activity!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS!\n");
			n = 0;

			P("\t\t\t***************Activities***************\n");
			System.out.format("\t\t\t|%-31s|\n", "Sr.No.|\tChoice.");
			P("\t\t\t|______|_______________________________|");
			while(str != null) {
				String[] arr = str.split(",");
				int actID = Integer.parseInt(arr[0]);
				if(actID != Act[0] && actID != Act[1] && actID != Act[2] && actID != Act[3]) {
					System.out.format("\t\t\t|%-38s|\n", arr[0] + ".    | " + arr[1]);
					P("\t\t\t|______|_______________________________|");
				}
				str = fr.readLine();
				ct++;
			}
			System.out.print("\n Enter Your Choice:- ");
			try {
				acts = Integer.parseInt(br.readLine());
				if(acts < 1 || acts > ct) n = 1;
				else if(acts == Act[0] || acts == Act[1] || acts == Act[2] || acts == Act[3]) n = 1;
			}
			catch(NumberFormatException e) {n = 2;}
			fr.close();
		}while(n > 0);
		return acts;
	}
	public String Name() throws IOException {
		String FN = "";
		boolean flg = false;
		do {
			if(flg) P("Error! Enter only Letters!\n");
			flg = false;
			System.out.print("Enter the new value:- ");
			FN = br.readLine().trim().toUpperCase();
			for(int i = 0; i < FN.length(); i++)
				if(!Character.isLetter(FN.charAt(i))) {flg = true; break;}
		}while(flg);
		return FN;
	}
	public long PhoneNo() throws IOException {
		long Ph = 0;
		int n = 0;
		do {
			if(n == 1) P("Enter only 10 digits!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS!\n");
			n = 0;
			System.out.print("\nEnter the new value:- ");
			try	{
				Ph = Long.parseLong(br.readLine());
				if(Long.toString(Ph).length() != 10) n = 1;
			}
			catch(NumberFormatException e) {n = 2;}
		}while(n > 0);
		return Ph;
	}
	public String Gender() throws IOException {
		int Gen = 0, n = 0;
		do {
			if(n == 1) P("Error! Enter only 1, 2, or 3!\n");
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
	public String City() throws IOException, InterruptedException {
		int n = 0, choice = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only the numbers beside the cities!!!\n");
			else if(n == 2)
				P("Error! You have entered the previous city number.\nPlease enter another number!!!\n");
			else if(n == 3)	P("\"NumberFormatException\" - Enter Only NUMBERS!!!\n");
			n = 0;

			P("\t\t\t************CITY*************\n");
			P("\t\t\t|Sr.No.|       Choice       |");
			P("\t\t\t|______|____________________|");
			if(city != 1) {
				P("\t\t\t|1     |BICHOLIM            |");
				P("\t\t\t|______|____________________|");
			}
			if(city != 2) {
				P("\t\t\t|2     |CARANZALEM          |");
				P("\t\t\t|______|____________________|");
			}
			if(city != 3) {
				P("\t\t\t|3     |DONA PAULA          |");
				P("\t\t\t|______|____________________|");
			}
			if(city != 4) {
				P("\t\t\t|4     |MARGAO              |");
				P("\t\t\t|______|____________________|");
			}
			if(city != 5) {
				P("\t\t\t|5     |OLD GOA             |");
				P("\t\t\t|______|____________________|");
			}
			if(city != 6) {
				P("\t\t\t|6     |PANAJI              |");
				P("\t\t\t|______|____________________|");
			}
			if(city != 7) {
				P("\t\t\t|7     |VASCO               |");
				P("\t\t\t|______|____________________|");
			}
			System.out.print("\n Enter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice < 1 || choice > 7) n = 1;
				else if(choice == city) n = 2;
			}
			catch(NumberFormatException e) {n = 3;}
		}while(n > 0);
		return Integer.toString(choice);
	}
	public int DetailView(String[] arr) throws IOException, InterruptedException {
		int n = 0, choice = 0;
		String[] all = new String[10];
		do {
			load(); cls();
			if(n == 1) P("Error! Enter only numbers from 1 - 10!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS(1 - 10)!\n");
			n = 0;

			String[] Act = {arr[6], arr[7], arr[8], arr[9]};
			Act = ActName(Act);
			for(int i = 0; i < 6; i++) all[i] = arr[i];
			for(int i = 0; i < 4; i++) all[i + 6] = Act[i];

			P("\t\t\t*******MEMBER DETAILS MODIFICATION*******\n");
			P(dash(38));
			P("\n0.ProductID(Cannot be changed)......" + all[0]);
			P("\n1.First Name........................" + all[1]);
			P("\n2.Last Name........................." + all[2]);
			P("\n3.Phone No.........................." + all[3]);
			P("\n4.Gender............................" + all[4]);
			P("\n5.City.............................." + CityName(Integer.parseInt(all[5])));
			P("\n6.Activity1........................." + all[6]);
			P("\n7.Activity2........................." + all[7]);
			P("\n8.Activity3........................." + all[8]);
			P("\n9.Activity4........................." + all[9]);
			P("\n10.Return to Admin Menu.............");
			P(dash(38));
			System.out.print("\nEnter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice < 1 || choice > 10) n = 1;
			}
			catch(NumberFormatException e) {n = 2;}
		}while(n > 0);
		if(choice == 10) menu(2);
		P("\nYour Value is " + all[choice] + "\n");
		return choice;
	}
	public String Actname(String choice) throws IOException {
		BufferedReader fr = read(2);
		String str = fr.readLine(), t = null;
		while(str != null) {
			String[] arr = str.split(",");
			if(arr[0] == choice) {t = arr[1]; break;}
			str = fr.readLine();
		}
		fr.close();
		return t;
	}
	public static void main(String[]args) {
		try {new MemberModify().modify();}
		catch(IOException e) {System.out.println("IOError!");}
		catch(InterruptedException e) {System.out.println("InterruptedError!");}
	}
}