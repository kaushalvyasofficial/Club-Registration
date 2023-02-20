import java.io.*;
public class Misc {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public void cls() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	public String dash(int n) {
		String Dash = "_";
		while(n-- > 0)
			Dash += "_";
		return Dash;
	}
	public void CityOpt() {
		P("\t\t\t************CITY*************\n");
		P("\t\t\t|Sr.No.|       Choice       |");
		P("\t\t\t|______|____________________|");
		P("\t\t\t|1     |BICHOLIM            |");
		P("\t\t\t|______|____________________|");
		P("\t\t\t|2     |CARANZALEM          |");
		P("\t\t\t|______|____________________|");
		P("\t\t\t|3     |DONA PAULA          |");
		P("\t\t\t|______|____________________|");
		P("\t\t\t|4     |MARGAO              |");
		P("\t\t\t|______|____________________|");
		P("\t\t\t|5     |OLD GOA             |");
		P("\t\t\t|______|____________________|");
		P("\t\t\t|6     |PANAJI              |");
		P("\t\t\t|______|____________________|");
		P("\t\t\t|7     |VASCO               |");
		P("\t\t\t|______|____________________|");
	}
	public void load() throws InterruptedException {
		System.out.print("\nLoading");
		Thread.sleep(175);
		System.out.print(".");
		Thread.sleep(175);
		System.out.print(".");
		Thread.sleep(175);
		System.out.print(".");
		Thread.sleep(175);
	}
	public BufferedReader read(int n) throws IOException {
		switch(n) {
		case 1: return new BufferedReader(new FileReader("members.dat"));
		case 2:	return new BufferedReader(new FileReader("activities.dat"));
		case 3:	return new BufferedReader(new FileReader("archive.dat"));
		default:return new BufferedReader(new FileReader("archiveAct.dat"));
		}
	}

	public String IDCheck(int type, boolean flg) throws IOException, NumberFormatException, InterruptedException {
		String ID;
		int n = 0, temp = 0;
		do {
			cls();
			if(n == 1) P("WRONG FORMAT!!! Enter a correct ID. Examples:- m1, m001, M1, M001, etc.\n");
			else if(n == 2) P("Your ID does not exist!\n");

			if(flg) P("(Enter am/ AM/ aM/ Am to return to Admin Menu)\n");
			else P("(Enter um/ UM/ uM/ Um to return to User Menu)\n");

			n = 0;
			if(type == 1) P("\t\t\t*****VIEW RECORDS*****\n");
			else if(type == 2) P("\t\t\t*****MODIFY RECORDS*****\n");
			else if(type == 3) P("\t\t\t*****DELETE RECORDS*****\n");

			System.out.print("Enter your Member ID:- ");
			ID = br.readLine().trim().toUpperCase();
			if(flg) {
				if(ID.equals("AM")) menu(2);
			}
			else {
				if(ID.equals("UM")) menu(3);
			}

			if(ID.length() > 1) {
				if(ID.charAt(0) != 'M') {
					n = 1;
					continue;
				}

				try {temp = Integer.parseInt(ID.substring(1));}
				catch(NumberFormatException e) {
					n = 1;
					continue;
				}

				BufferedReader fr = read(1);
				String str = fr.readLine();
				while(str != null) {
					String[] arr = str.split(",");
					if(Integer.parseInt(arr[0].substring(1)) == temp) {
						n = 0;
						break;
					}
					else n = 2;
					str = fr.readLine();
				}
				fr.close();
			}
			else n = 1;
		}while(n > 0);
		return "M" + temp;
	}
	public void menu(int n) throws IOException, InterruptedException {
		switch(n) {
		case 1:  new ClubMenu().Menu();
		case 2:  new ClubMenu().Admin();
		default: new ClubMenu().User();
		}
	}
	public String CityName(int n) {
		switch(n) {
		case 1: return "BICHOLIM";
		case 2: return "CARANZALEM";
		case 3:	return "DONA PAULA";
		case 4:	return "MARGAO";
		case 5:	return "OLD GOA";
		case 6:	return "PANAJI";
		default:return "VASCO";
		}
	}
	public String[] ActName(String[] Act) throws IOException {
		String[] MAct = new String[4];
		for(int i = 0; i < 4; i++) {
			BufferedReader fr = read(2);

			String str = fr.readLine();
			while(str != null) {
				String[] arr = str.split(",");
				if(arr[0].equals(Act[i])) MAct[i] = arr[1];
				str = fr.readLine();
			}
		}
		return MAct;
	}
	public void P(String print) {System.out.println(print);}
	public int ArcCheck(int n, boolean flg) throws IOException {
		int greater = 0;
		BufferedReader fr;
		if(flg) fr = read(3);
		else fr = read(4);

		String str = fr.readLine();
		while(str != null) {
			String[]arr = str.split(",");
			if(Integer.parseInt(arr[0]) > greater) greater = Integer.parseInt(arr[0]);
			str = fr.readLine();
		}
		fr.close();
		if(greater > n) return greater;
		return n;
	}
	public PrintWriter temp() throws IOException {return new PrintWriter(new FileWriter("temp.dat"));}
	public void Asc() throws IOException {
		for(int x = 1; x < 5; x++) {
			BufferedReader fr = read(x);
			int ID[] = new int[NumOfEntities(x)], i = 0;



			String str = fr.readLine();
			while(str != null) {
				String[] arr = str.split(",");
				if(x % 2 == 1) ID[i] = Integer.parseInt(arr[0].substring(1));
				else ID[i] = Integer.parseInt(arr[0]);
				str = fr.readLine();
				i++;
			}
			fr.close();

			for(i = 0; i < ID.length; i++)
				for(int j = 0; j < ID.length; j++)
					if(ID[i] < ID[j]) {
						int t = ID[i];
						ID[i] = ID[j];
						ID[j] = t;
					}

			PrintWriter pw = temp();
			for(int j: ID) {
				fr = read(x);
				str = fr.readLine();

				while(str != null) {
					String[] arr = str.split(",");
					String temp;
					if(x % 2 == 1) temp = arr[0].substring(1);
					else temp = arr[0];

					if(j == Integer.parseInt(temp)) {
						pw.println(str);
						break;
					}
					str = fr.readLine();
				}
				fr.close();
			}
			fr.close();
			pw.close();
			File f1 = new File("temp.dat"), f2;

			switch(x) {
			case 1: f2 = new File("members.dat"); break;
			case 2: f2 = new File("activities.dat"); break;
			case 3: f2 = new File("archive.dat"); break;
			default:f2 = new File("archiveAct.dat");
			}
			f2.delete();
			f1.renameTo(f2);
		}
	}
	public int NumOfEntities(int n) throws IOException {
		BufferedReader fr = read(n);
		String str = fr.readLine();
		int ct = 0;
		while(str != null) {
			ct++;
			str = fr.readLine();
		}
		fr.close();
		return ct;
	}
}
