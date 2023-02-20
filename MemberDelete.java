import java.io.*;
public class MemberDelete extends Misc {
	public void delete() throws IOException, InterruptedException {
		Asc();
		String ID = IDCheck(3, true);
		BufferedReader fr = read(1);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			if(arr[0] == ID) {new MemberView().DetailView(arr, 0); break;}
			str = fr.readLine();
		}
		fr.close();
		char ch = ' ';
		do {
			if(ch == 'O') P("\n\nError! Enter only yes or no!\n");
			P("Do you wish to archive this record? (Enter Y/ y for yes and N/ n for no)\n");
			P("[Note:- The details, if needed, have to be unarchived manually.]");
			ch = (char)br.read();
			br.readLine();
			ch = Character.toUpperCase(ch);
			if(ch == 'Y') {
				ArcRec(ID);	cls(); load();
				do {
					cls();
					if(ch == 'O') P("\nError! Enter only yes or no!");
					P("\n\t\t\t>>>>>>>Record has been archived!<<<<<<<\n");
					P("Do you wish to archive more record? (Enter Y/ y for yes and N/ n for no)");
					ch = (char)br.read();
					br.readLine();
					ch = Character.toUpperCase(ch);
					if(ch == 'Y') delete();
					else if(ch == 'N') menu(2);
					else ch = 'O';
				}while(ch == 'O');
			}
			else if(ch == 'N') delete();
			else ch = 'O';
		}while(ch == 'O');
	}
	public void ArcRec(String ID) throws IOException, InterruptedException {
		PrintWriter pw = temp();
		PrintWriter pw2 = new PrintWriter(new FileWriter("archive.dat", true));
		BufferedReader fr = read(1);
		String str = fr.readLine();
		while(str != null) {
			String[] arr = str.split(",");
			if(arr[0] == ID) pw2.println(str);
			else pw.println(str);
			str = fr.readLine();
		}

		fr.close(); pw.close(); pw2.close();
		File f1 = new File("temp.dat"), f2 = new File("members.dat");
		f2.delete(); f1.renameTo(f2);
	}
	public static void main(String[] a) {
		try	{new MemberDelete().delete();}
		catch(IOException e) {System.out.println("IOError!");}
		catch(InterruptedException e) {System.out.println("InterruptedError!");}
	}
}