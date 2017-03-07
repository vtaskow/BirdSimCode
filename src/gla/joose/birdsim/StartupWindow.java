package gla.joose.birdsim;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class StartupWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5216565892893346384L;

	private JComboBox<String> boardsBox;
	private JComboBox<String> behaviorsBox;

	private JLabel title;
	private JLabel chooseBoard;
	private JLabel chooseFlying;
	private JButton sendButton;

	private int selectedBoard = 0;
	private int selectedBehavior = 0;
	
	private Play play;
	
	public StartupWindow(Play play) {
		super("Bird Simulation - Choose settings");
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.play = play;

		title = new JLabel("Choose a type of board and a bird behavior");

		sendButton = new JButton("Start the simulation!");
		sendButton.addActionListener(this);

		String[] boardsList = { "Flock board", "Static forage board", "Moving forage board" };
		String[] behaviourList = { "Moving Forage, Birds die off and multiply", "No Forage, birds die off",
				"Static Forage, birds multiply" };

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
		// getContentPane().setPreferredSize(new Dimension(400, 200));
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
			selectedBoard = boardsBox.getSelectedIndex() + 1;
			//String boardName = (String) boardsBox.getSelectedItem();
			//System.out.println(boardName);
		} else if (source == behaviorsBox) {
			selectedBehavior = behaviorsBox.getSelectedIndex() + 1;
			//String behaviorName = (String) behaviorsBox.getSelectedItem();
			//System.out.println(behaviorName);
		} else if (source == sendButton) {
			System.out.println("Sending info...");
			play.getSelectedBoardMenu(this);
			play.getSelectedBehaviorMenu(this);
		}
	}
	
	
	
	public int getSelectedBoard() {
		return selectedBoard;
	}

	public int getSelectedBehavior() {
		return selectedBehavior;
	}

	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new StartupWindow().setVisible(true);
			}

		});
	}*/
}
