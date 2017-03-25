package votingMachine;

import javax.swing.JFrame;

public class Machine {

	public static void main(String[] args) {
		
		UserInterface vote = new UserInterface();
		vote.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vote.setSize(400, 300);
		vote.setResizable(false);
		vote.setVisible(true);

	}

}
