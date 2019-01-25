/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment 	handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		graph = new NameSurferGraph();
		add(graph);
		// You fill this in, along with any helper method
		database = new NameSurferDataBase(NAMES_DATA_FILE);
		nameField = new JTextField(10);
		nameField.addActionListener(this);
		add(new JLabel("Name"), NORTH);
		add(nameField, NORTH);
		add(new JButton("Graph"), NORTH);
		add(new JButton("Clear"), NORTH);
		//must call this method to get button press events
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ((cmd.equals("Graph"))||(e.getSource() == nameField)) {
			NameSurferEntry n = database.findEntry(nameField.getText());
			if (n != null) {
				graph.addEntry(n);
				graph.update();
			}
		}else if (cmd.equals("Clear")) {
			graph.clear();
			graph.update();
		}
	}
	
	/*private instance variables*/
	private JTextField nameField;
	private NameSurferDataBase database;
	private NameSurferGraph graph;
}
