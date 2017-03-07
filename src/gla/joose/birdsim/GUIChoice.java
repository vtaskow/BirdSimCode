package gla.joose.birdsim;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIChoice extends JPanel implements ActionListener  {
	private JButton saveButton;
	private JComboBox<String> petList;

	GUIChoice(){		
		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		
		petList = new JComboBox<>(petStrings);
		petList.setSelectedIndex(0);
		petList.addActionListener(this);
		
		saveButton = new JButton("Choose");
		saveButton.addActionListener(this);
		
		Box topBox = Box.createHorizontalBox();
		topBox.add(petList);
		topBox.add(Box.createHorizontalStrut(100));
		topBox.add(saveButton);
		
		add(petList);
		add(saveButton);
	}
	
	public void actionPerformed(ActionEvent event) {
		// Determine which button was pushed
		Object source = event.getSource();
		if (source == saveButton) {
			System.out.println("button was clicked");
		} else if (source == petList) {
			String petName = (String)petList.getSelectedItem();
			System.out.println(petName);
		}
	}
	
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("ComboBoxDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		GUIChoice newPanel = new GUIChoice();
		newPanel.setOpaque(true);
		frame.setContentPane(newPanel);
		
		//Display the window.
        frame.pack();
        frame.setVisible(true);

	}
	

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }

}
