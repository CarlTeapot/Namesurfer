

import acm.program.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {


/* Method: init() */
/**
 * This method has the responsibility for reading in the database
 * and initializing the interactors at the bottom of the window.
 */
	public NameSurfer() throws IOException {
		graph = new NameSurferGraph();
		base = new NameSurferDataBase(NAMES_DATA_FILE);
		add(graph);
	}
	public void run() {

	}
	public void init() {
		drawButtons();
		nameField.addActionListener(this);
		clearButton.addActionListener(this);
		graphButton.addActionListener(this);
		deleteField.addActionListener(this);
	}
	// draws the necessary labels
	private void drawLabels() {
		nameLabel = new JLabel("Name");
		deleteLabel = new JLabel("Delete Name");
	}

	// draws the buttons
	private void drawButtons() {
		drawLabels();
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		nameField = new JTextField(15);
		deleteField = new JTextField(15);
		add(deleteLabel, SOUTH);
		add(deleteField, SOUTH);
		add(nameLabel, SOUTH);
		add(nameField,SOUTH);
		add(graphButton, SOUTH);
		add(clearButton, SOUTH);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), graphButton.getActionCommand())) {
			graph.addEntry(base.findEntry(nameField.getText()));
		}
		else if (Objects.equals(e.getActionCommand(), clearButton.getActionCommand()))
			graph.clear();
		if (Objects.equals(e.getActionCommand(), deleteField.getText())) {
			graph.deleteName(base.findEntry(deleteField.getText()));
		}
		graph.update();
	}
	private NameSurferGraph graph;
	private NameSurferDataBase base;
	private JButton clearButton;
	private JButton graphButton;
	private JLabel nameLabel;
	private JLabel deleteLabel;
	private JTextField nameField;
	private JTextField deleteField;
}
