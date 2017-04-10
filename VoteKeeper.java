package votingMachine;

import java.io.*;
import java.util.*;

public class VoteKeeper{
	
	private String path;
	private Scanner input;
	FileWriter file;
	
	public VoteKeeper() throws IOException
	{
		path = "Election Information";
		file = new FileWriter(path, true);
		input = new Scanner(new File(path));		
	}
	
	public void record(int x) throws IOException
	{
		
		PrintWriter write = new PrintWriter(file);
		
		write.print(x + " ");
		write.close();
	}
	
	public int remember(int x) throws IOException
	{
		x = input.nextInt();
		return x;
	}

}
