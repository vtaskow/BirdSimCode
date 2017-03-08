package gla.joose.birdsim;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartupWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -5216565892893346384L;
	
	/* combo boxes for different boards and different flying behaviours */
	private JComboBox<String> boardsBox;
	private JComboBox<String> behaviorsBox;

	/* lists containing board and behavior choices */
	private String[] boardsList;
	private String[] behaviourList;

	/* title for the window */
	private JLabel title;
	
	/* labels associated with combo boxes */
	private JLabel chooseBoard;
	private JLabel chooseFlying;
	
	/* button for sending the required information to the Play class and disposing the current window */
	private JButton sendButton;

	/* set to one in case the user decides not to change either one of the options of the combo boxes*/
	/* variables for holding the ids of selected choices */
	private int menuSelectedBoard = 1;
	private int menuSelectedBehavior = 1;

	/* a simulation associated with it */
	private Play play;

	/* constructor - takes a simulation instance */
	public StartupWindow(Play play) {
		super("Bird Simulation - Choose settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.play = play;

		/* label for the window and its purpose */
		title = new JLabel("Choose a type of board and a bird behavior");

		/* button for sending the choices to Play class and launching the simulation */
		sendButton = new JButton("Start the simulation!");
		sendButton.addActionListener(this);

		/* lists with board and behavior choices */
		boardsList = new String[] { "Flock board", "Static forage board", "Moving forage board" };
		behaviourList = new String[] { "No Forage", "No Forage, Birds Die", "Static Forage", "Static Forage, Birds Die",
				"Static Forage, Birds Die and Multiply", "Moving Forage", "Moving Forage, Birds Multiply",
				"Moving Forage, Birds Die and Multiply", };

		/* combo box with board choices */
		chooseBoard = new JLabel("Choose type of board");
		boardsBox = new JComboBox<>(boardsList);
		boardsBox.setSelectedIndex(0);
		boardsBox.addActionListener(this);

		/* combo box with behaviour choices */
		chooseFlying = new JLabel("Choose a flying behavior");
		behaviorsBox = new JComboBox<>(behaviourList);
		behaviorsBox.setSelectedIndex(0);
		behaviorsBox.addActionListener(this);

		/* box layout for the title label */
		Box titleBox = Box.createHorizontalBox();
		titleBox.add(Box.createVerticalStrut(50));
		titleBox.add(title);
		titleBox.add(Box.createVerticalStrut(50));

		/* box layout for the two combo boxes */
		Box chooseBox = Box.createHorizontalBox();
		chooseBox.add(Box.createHorizontalStrut(15));
		chooseBox.add(chooseBoard);
		chooseBox.add(Box.createHorizontalStrut(15));
		chooseBox.add(boardsBox);
		chooseBox.add(Box.createHorizontalStrut(15));
		chooseBox.add(chooseFlying);
		chooseBox.add(Box.createHorizontalStrut(15));
		chooseBox.add(behaviorsBox);
		chooseBox.add(Box.createHorizontalStrut(15));

		/* box layout for the send button */
		Box sendData = Box.createHorizontalBox();
		sendData.add(Box.createVerticalStrut(50));
		sendData.add(sendButton);
		sendData.add(Box.createVerticalStrut(50));

		/* set a BorderLayout and lay them in it, specifying their orientations */
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(titleBox, BorderLayout.NORTH);
		getContentPane().add(chooseBox, BorderLayout.CENTER);
		getContentPane().add(sendData, BorderLayout.SOUTH);

		/* pack every component */
		pack();
		/* lay the window at the center of the screen */
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* get the source of event performed */
		Object source = e.getSource();

		/* find which one it comes from */
		if (source == boardsBox) {
			/* if a board choice has been selected update its id */
			menuSelectedBoard = boardsBox.getSelectedIndex() + 1;
		} else if (source == behaviorsBox) {
			/* if a behavior choice has been selected update its id */
			menuSelectedBehavior = behaviorsBox.getSelectedIndex() + 1;
		} else if (source == sendButton) {
			/* update simulation's choices and launch it, and close this window */
			play.setSelectedBoard(menuSelectedBoard);
			play.setSelectedBehavior(menuSelectedBehavior);
			play.setSelected(true);
			
			/* close the current JFrame */
			dispose();
		}
	}
}
