package votingMachine;

import java.io.*;
import java.util.*;

public class VoteKeeper{
	
	private String path;
	private Scanner input;
	@SuppressWarnings("unused")
	private FileWriter readFile;
	private FileWriter writeFile;
	private int [] voteCount = new int[4];
	
	public VoteKeeper() throws IOException
	{
		path = "Election Information";
		readFile = new FileWriter(path, true);
		input = new Scanner(new File(path));
		for(int i = 0; i < voteCount.length; i++)
		{
			if(input.hasNext())
			{
				voteCount[i] = input.nextInt();	
			}
			else
			{
				voteCount[i] = 0;
			}
		}
		writeFile = new FileWriter(path, false);
	}
	
	public void count(int x)
	{
		voteCount[x]++;
	}

	public void record() throws IOException
	{
		PrintWriter write = new PrintWriter(writeFile);
		for (int i = 0; i < voteCount.length; i++) 
		{
			write.print(voteCount[i] + " ");
		}
		write.close();
	}
	
	public int remember(int i)
	{
		if(i < voteCount.length)
		{
			return voteCount[i];
		}
		else
		{
			System.out.println("Error");
			return 0;
		}
		
	}
	
	public void newElection()
	{
		for(int i = 0; i < voteCount.length; i++)
		{
			voteCount[i] = 0;
		}
	}

}
