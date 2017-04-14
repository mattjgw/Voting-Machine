package votingMachine;

import java.io.*;
import java.util.*;

public class VoteKeeper{
	
	private String path;
	private Scanner input;
	private FileWriter file;
	private int [] voteCount = new int[4];
	
	public VoteKeeper() throws IOException
	{
		path = "Election Information";
		file = new FileWriter(path, true);
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
	}
	
	public void count(int x)
	{
		voteCount[x]++;
	}

	public void record() throws IOException
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < voteCount.length; i++) 
			{
				writer.write(voteCount[i] + " ");
			}
			System.out.println("Written");
			writer.close();
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
		
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
		System.out.println("Executed");
	}

}
