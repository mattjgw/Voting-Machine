package votingMachine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PasswordKeeper {
	private String path;
	@SuppressWarnings("unused")
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
		password = decrypt();
	}
	
	public PasswordKeeper(String p) throws IOException
	{
		path = "Election Information";
		file = new FileWriter(path, true);
		input = new Scanner(new File(path));
		password = p;
	}
	
	private String decrypt() {
		Cipher cipher = getCipher(Cipher.DECRYPT_MODE, "Password.txt");
		try {
			DataInputStream input = new DataInputStream(new CipherInputStream(new FileInputStream(new File("Password.txt")), cipher));
			String password = input.readUTF();
			input.close();
			return password;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "password";
	}

	public void store() throws IOException
	{
		encrypt(password);
	}
	
	private String encrypt(String p) {
		Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, "Password.txt");
		try {
			DataOutputStream output = new DataOutputStream(new CipherOutputStream(new FileOutputStream(new File("Password.txt")), cipher));
			output.writeUTF(p);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return decrypt();
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
