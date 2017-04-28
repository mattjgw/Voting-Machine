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
	private boolean used = false;
	
	public PasswordKeeper() throws IOException
	{
		path = "Password.txt";
		file = new FileWriter(path, true);
		input = new Scanner(new File(path));
		
		if(input.hasNext())
		{
			password = decrypt();
		}
		else
		{
			password = "password";
		}	
	}
	
	private String decrypt() {
		String encypted = input.nextLine();
		String decrypted = "";
		int originalLength = encypted.length();
		char tempChar;
		for(int i = 0; i < originalLength; i++)
		{
			tempChar = encypted.charAt(i);
			tempChar-=100;
			decrypted+=tempChar;
		}
		return decrypted;
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
			writer.write(encrypt(password));
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
	}
	
	private String encrypt(String p) {
		String encripted = "";
		char tempChar;
		for(int i = 0; i < p.length(); i++)
		{
			tempChar = p.charAt(i);
			tempChar+=100;
			encripted+=tempChar;
		}
		return encripted;
	}

	public void changePassword(String p)
	{
		password = p;
		used = true;
	}
	
	public boolean used()
	{
		return used;
	}
	
	public String getPassword()
	{
		return password;
	}

}
