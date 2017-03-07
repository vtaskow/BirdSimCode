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
	/**
	 * 
	 */
	private static final long serialVersionUID = -5216565892893346384L;

	private JComboBox<String> boardsBox;
	private JComboBox<String> behaviorsBox;

	private String[] boardsList;
	private String[] behaviourList;

	private JLabel title;
	private JLabel chooseBoard;
	private JLabel chooseFlying;
	private JButton sendButton;

	private int menuSelectedBoard = 0;
	private int menuSelectedBehavior = 0;

	private Play play;

	public StartupWindow(Play play) {
		super("Bird Simulation - Choose settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.play = play;

		title = new JLabel("Choose a type of board and a bird behavior");

		sendButton = new JButton("Start the simulation!");
		sendButton.addActionListener(this);

		boardsList = new String[] { "Flock board", "Static forage board", "Moving forage board" };
		
		behaviourList = new String[] { "No Forage", "No Forage, Birds Die", "Static Forage", "Static Forage, Birds Die",
				"Static Forage, Birds Die and Multiply", "Moving Forage", "Moving Forage, Birds Multiply",
				"Moving Forage, Birds Die and Multiply", };

		chooseBoard = new JLabel("Choose type of board");
		boardsBox = new JComboBox<>(boardsList);
		boardsBox.setSelectedIndex(0);
		boardsBox.addActionListener(this);

		chooseFlying = new JLabel("Choose a flying behavior");
		behaviorsBox = new JComboBox<>(behaviourList);
		behaviorsBox.setSelectedIndex(0);
		behaviorsBox.addActionListener(this);

		Box titleBox = Box.createHorizontalBox();
		titleBox.add(Box.createVerticalStrut(50));
		titleBox.add(title);
		titleBox.add(Box.createVerticalStrut(50));

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

		Box sendData = Box.createHorizontalBox();
		sendData.add(Box.createVerticalStrut(50));
		sendData.add(sendButton);
		sendData.add(Box.createVerticalStrut(50));

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(titleBox, BorderLayout.NORTH);
		getContentPane().add(chooseBox, BorderLayout.CENTER);
		getContentPane().add(sendData, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * String cmd = e.getActionCommand(); System.out.println(cmd); if
		 * (cmd.equals("Open")) { dispose(); new AnotherJFrame(); }
		 */
		Object source = e.getSource();

		if (source == boardsBox) {
			menuSelectedBoard = boardsBox.getSelectedIndex() + 1;
			// String boardName = (String) boardsBox.getSelectedItem();
			// System.out.println(boardName);
		} else if (source == behaviorsBox) {
			menuSelectedBehavior = behaviorsBox.getSelectedIndex() + 1;
			// String behaviorName = (String) behaviorsBox.getSelectedItem();
			// System.out.println(behaviorName);
		} else if (source == sendButton) {
			play.setSelectedBoard(menuSelectedBoard);
			play.setSelectedBehavior(menuSelectedBehavior);
			play.setSelected(true);
			dispose();
		}
	}

	/*
	 * public static void main(String[] args) { SwingUtilities.invokeLater(new
	 * Runnable() {
	 * 
	 * @Override public void run() { new StartupWindow().setVisible(true); }
	 * 
	 * }); }
	 */
}
