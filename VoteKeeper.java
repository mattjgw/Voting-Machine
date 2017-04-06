package votingMachine;

import java.io.*;

public class VoteKeeper {
	
	private String path;
	
	public VoteKeeper()
	{
		path = "Election Information";
	}
	
	public void Record(int x) throws IOException
	{
		FileWriter file = new FileWriter(path, true);
		PrintWriter write = new PrintWriter(file);
		
		write.print(x + " ");
		write.close();
	}

}
