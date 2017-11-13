package votingMachine;

import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class VoteKeeper{
	
	private String path = "Election Information.txt";
	private int [] voteCount = new int[4];
	
	public VoteKeeper() throws IOException
	{
		decrypt(voteCount);
	}
	
	private void decrypt(int[] voteCount) {
		Cipher cipher2 = getCipher(Cipher.DECRYPT_MODE, path);
		
		try {
			DataInputStream input = new DataInputStream(new CipherInputStream(new FileInputStream(new File(path)), cipher2));
			for (int i = 0; i < voteCount.length; i++)
			{
				voteCount[i] = input.read();
			}
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void count(int x)
	{
		voteCount[x]++;
	}

	public void record(int[] voteCount) throws IOException
	{
		Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, path);
		try {
			DataOutputStream output = new DataOutputStream(new CipherOutputStream(new FileOutputStream(new File(path)), cipher));
			for (int i = 0; i < voteCount.length; i++)
			{
				output.write(voteCount[i]);
			}
			
			output.flush();
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	}
	
	public Cipher getCipher(int mode, String key)
	{
		Random random = new Random(9999L);
		byte salt[] = new byte [8];
		random.nextBytes(salt);
		PBEParameterSpec pSpecs = new PBEParameterSpec(salt, 5);
		try {
			SecretKey PBEKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(key.toCharArray()));
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(mode, PBEKey, pSpecs);
			return cipher;
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

}