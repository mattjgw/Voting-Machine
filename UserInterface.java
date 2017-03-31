package votingMachine;

import java.awt.*;
import java.awt.event.*;
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
	private static int[] voteCount = new int [4];
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserInterface()
	{
		super("Voting Machine");
		setLayout(new FlowLayout());
		
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
						String password = null;
						password = JOptionPane.showInputDialog(null, "Enter the password:");
		
						if(password.equalsIgnoreCase("password"))
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
						for(int i = 0; i < voteCount.length; i++)
						{
							voteCount[i] = 0;
						}
						
					}
				}
				);
			
		add(vote);
		add(getResults);
		add(newElection);
		add(parties);
		candidatePicture = new JLabel(symbols[0]);
		add(candidatePicture);
	}

}
