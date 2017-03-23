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
	private Icon [] symbols = {new ImageIcon(getClass().getResource(picture[0])), new ImageIcon(getClass().getResource(picture[1])), 
			new ImageIcon(getClass().getResource(picture[2])), new ImageIcon(getClass().getResource(picture[3]))};
	private JButton vote;
	private static int[] voteCount = new int [4];
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserInterface()
	{
		super("Voting Machine");
		setLayout(new FlowLayout());
		
		parties = new JComboBox(picture);
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
						voteCount[parties.getSelectedIndex()]++;
						System.out.println(voteCount[parties.getSelectedIndex()]);
					}
				}
				);
		
		add(vote);
		add(parties);
		candidatePicture = new JLabel(symbols[0]);
		add(candidatePicture);
	}

}
