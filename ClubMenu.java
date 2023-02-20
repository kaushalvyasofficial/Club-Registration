import java.io.*;
class ClubMenu extends Misc {
	static private int ct = 0;
	static private boolean flg = false;

	void Menu() throws IOException, InterruptedException {
		Asc();
		int n = 0, choice = 0;
		
		do {
			cls();
			if(n == 1) P("Error! Enter only 1, 2 or 3!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS(1 - 3)!\n");

			n = 0;
			P("\t\t\t**********Kaushal's Club**********\n");
			System.out.format("\t\t\t|%-25s|\n", "Sr.No.|\tChoice.");
			P("\t\t\t|" + dash(5) + "|" + dash(24) + "|");
			System.out.format("\t\t\t|%-32s|\n", "1.    | ADMIN.");
			P("\t\t\t|" + dash(5) + "|" + dash(24) + "|");
			System.out.format("\t\t\t|%-32s|\n", "2.    | USER.");
			P("\t\t\t|" + dash(5) + "|" + dash(24) + "|");
			System.out.format("\t\t\t|%-32s|\n", "3.    | EXIT.");
			P("\t\t\t|" + dash(5) + "|" + dash(24) + "|");
			System.out.print("\n\n Enter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine().trim());
				if(choice < 1 || choice > 3) n = 1;
			}
			catch(NumberFormatException e) {
				n = 2;
			}
		}while(n > 0);

		load();
		cls();
		switch(choice) {
			case 1: Admin();
			case 2: User();
			default:
			P("\n\n\t\t>>>>>>> Thank You Very Much For Coming!!! <<<<<<<\n");
			System.exit(0);
		}
	}
	void Admin() throws IOException, InterruptedException {
		Asc();
		final String PASSWORD = "Password#1024";

		if(!flg) {
			boolean flg2 = false;
			do {
				cls();
				P("\t\t\t**********ADMIN**********\n");
				
				if(ct != 3) {
					ct++;
					if(flg2) P("\nError! You have entered an incorrect password!\n");
					flg2 = false;

					P("(Type CM to go back to main menu)\n");
					P("Chances left:- " + (4 - ct));
					System.out.print("Enter Your Password:- ");
					String pass = br.readLine().trim();

					if(pass != PASSWORD && pass != "CM") flg2 = true;
					else if(pass == "CM") {
						ct--;
						load();
						menu(1);
					}
					else if(pass == PASSWORD) {
						flg = true;
						break;
					}
				}
				else {
					P("Your chances are over! Restart the program to get more chances!");
					System.out.print("Press enter to go back to main menu:- ");
					br.readLine();
					menu(1);
				}
			}while(ct <= 3);
		}

		int n = 0, choice = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only 1, 2, 3, 4 or 5!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS(1 - 5)!\n");

			n = 0;
			P("\t\t\t    **********ADMIN**********\n\n");
			System.out.format("\t\t\t|Sr.No.|\t%-15s|\n", "Choice.");
			P("\t\t\t|______|_______________________|");
			System.out.format("\t\t\t|%-30s|\n", "1.    |MODIFY MEMBER DETAILS.");
			P("\t\t\t|______|_______________________|");
			System.out.format("\t\t\t|%-30s|\n", "2.    |CHANGES IN ACTIVITIES.");
			P("\t\t\t|______|_______________________|");
			System.out.format("\t\t\t|%-30s|\n", "3.    |DELETE RECORDS.");
			P("\t\t\t|______|_______________________|");
			System.out.format("\t\t\t|%-30s|\n", "4.    |VIEW RECORDS.");
			P("\t\t\t|______|_______________________|");
			System.out.format("\t\t\t|%-30s|\n", "5.    |RETURN TO MAIN MENU.");
			P("\t\t\t|______|_______________________|");
			System.out.print("\n\n Enter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine().trim());
				if(choice < 1 || choice > 5) n = 1;
			}
			catch(NumberFormatException e) {
				n = 2;
			}
		}while(n > 0);

		load();
		switch(choice) {
			case 1: new MemberModify().modify();
			break;
			case 2: new MemberAct().change(); 
			break;
			case 3: new MemberDelete().delete(); 
			break;
			case 4:	new MemberView().MainView(true); 
			break;
			default: flg = false; 
			menu(1);
		}
		System.exit(0);
	}
	void User() throws IOException, InterruptedException {
		Asc();
		int n = 0, choice = 0;
		do {
			cls();
			if(n == 1) P("Error! Enter only 1, 2 or 3!\n");
			else if(n == 2) P("\"NumberFormatException\" - Enter only NUMBERS(1 - 3)!\n");

			n = 0;
			P("\t\t\t**********USER'S MENU**********\n\n");
			System.out.format("\t\t\t|%-22s|\n", "Sr.No.|\tChoice.");
			P("\t\t\t|______|______________________|");
			System.out.format("\t\t\t|%-29s|\n", "1.    | ADD MEMBERS TO CLUB");
			P("\t\t\t|______|______________________|");
			System.out.format("\t\t\t|%-29s|\n", "2.    | VIEW RECORDS.");
			P("\t\t\t|______|______________________|");
			System.out.format("\t\t\t|%-29s|\n", "3.    | RETURN TO MAIN MENU.");
			P("\t\t\t|______|______________________|");
			System.out.print("\n\n Enter your choice:- ");
			try {
				choice = Integer.parseInt(br.readLine());
				if(choice < 1 || choice > 3) n = 1;
			}
			catch(NumberFormatException e) {
				n = 2;
			}
		}while(n > 0);

		load();
		if(choice == 1) new MemberAdd().Add();
		else if(choice == 2) new MemberView().MainView(false);
		else menu(1);
		System.exit(0);
	}
	public static void main(String[] args) {
		try {
			new ClubMenu().Menu();
		}
		catch(IOException e) {
			System.out.println("IOError!!!");
		}
		catch(InterruptedException e) {
			System.out.println("InterruptedError!!!");
		}
	}
}
