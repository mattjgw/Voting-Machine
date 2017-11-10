package votingMachine;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

@SuppressWarnings("serial")
public class UserInterface extends JFrame{
	
	@SuppressWarnings("rawtypes")
	private JComboBox parties;
	private JLabel candidatePicture;
	private static String[] picture = {"conservative.png", "green.png", "liberal.png", "NDP.png"};
	private static String[] partyName = {"Conservative", "Green", "Liberal", "NDP"};
	private Icon [] symbols = {new ImageIcon(getClass().getResource(picture[0])), new ImageIcon(getClass().getResource(picture[1])), 
			new ImageIcon(getClass().getResource(picture[2])), new ImageIcon(getClass().getResource(picture[3]))};
	private JButton vote;
	private JButton getResults;
	private JButton newElection;
	private JButton resetPassword;
	private static int[] voteCount = new int [4];
	private VoteKeeper scoreKeeper = new VoteKeeper();
	private PasswordKeeper password = new PasswordKeeper();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserInterface() throws IOException
	{
		super("Voting Machine");
		setLayout(new FlowLayout());
		
		for(int i = 0; i < voteCount.length; i++)
		{
			voteCount[i]=scoreKeeper.remember(i);
		}
		scoreKeeper.record();
		
		parties = new JComboBox(partyName);
		parties.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent event)
					{
						if(event.getStateChange() == ItemEvent.SELECTED)
							candidatePicture.setIcon(symbols[parties.getSelectedIndex()]);
					}
				}
			);
		
		vote = new JButton("Caste Vote");
		vote.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						Object[] options = { "Yes", "No" };
						int yes = 0;
						yes = JOptionPane.showOptionDialog(null, "Are you sure you want to vote?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
						
						if(yes == 0)
						{
							voteCount[parties.getSelectedIndex()]++;
							try 
							{
								scoreKeeper.count(parties.getSelectedIndex());
								scoreKeeper.record();
							} 
							
							catch (IOException e) 
							{
								e.printStackTrace();
							}
						}
						
					}
				}
			);
		
		getResults = new JButton("Get Results");
		getResults.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						String tempPassword = null;
						tempPassword = JOptionPane.showInputDialog(null, "Enter the password:");
		
						if(tempPassword.equalsIgnoreCase(password.getPassword()))
						{
							JOptionPane.showMessageDialog(null, showResults());	
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The password you entered is incorrect");
						}
						
					}

					private String showResults() {
						String message = "";
						for (int i = 0; i < partyName.length; i++) {
							message+=partyName[i]+": "+voteCount[i]+"\n";
						}
						return message;
						
					}
				}
			);
		
		newElection = new JButton("Start new election");
		newElection.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						String tempPassword = null;
						tempPassword = JOptionPane.showInputDialog(null, "Enter the password:");
		
						if(tempPassword.equalsIgnoreCase(password.getPassword()))
						{
							for(int i = 0; i < voteCount.length; i++)
							{
								voteCount[i] = 0;
								scoreKeeper.newElection();
								try {
									scoreKeeper.record();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The password you entered is incorrect");
						}

					}
				}
			);
		if(password.used())
		{
			resetPassword = new JButton("Set Password");
		}
		
		else
		{
			resetPassword = new JButton("Reset Password");
		}
		
		resetPassword.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						String tempPassword = null;
						tempPassword = JOptionPane.showInputDialog(null, "Enter the old password:");
		
						if(tempPassword.equalsIgnoreCase(password.getPassword()))
						{
							tempPassword = JOptionPane.showInputDialog(null, "Enter the new password");
							password.changePassword(tempPassword);
							try {
								password.store();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The password you entered is incorrect");
						}

					}
				}
			);
			
		add(vote);
		add(getResults);
		add(newElection);
		add(parties);
		add(resetPassword);
		candidatePicture = new JLabel(symbols[0]);
		add(candidatePicture);
	}

}
