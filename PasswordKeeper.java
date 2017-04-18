package votingMachine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PasswordKeeper {
	private String path;
	private Scanner input;
	@SuppressWarnings("unused")
	private FileWriter file;
	private String password;
	
	public PasswordKeeper() throws IOException
	{
		path = "Password File";
		file = new FileWriter(path, true);
		input = new Scanner(new File(path));
		if(input.hasNext())
		{
			password = input.next();
		}
		else
		{
			password = "password";
		}	
	}
	
	public PasswordKeeper(String p) throws IOException
	{
		path = "Election Information";
		file = new FileWriter(path, true);
		input = new Scanner(new File(path));
		password = p;
	}

	public void store() throws IOException
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(password);
			System.out.println("Written");
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
	}
	
	public void changePassword(String p)
	{
		password = p;
	}
	
	public String getPassword()
	{
		return password;
	}

}
