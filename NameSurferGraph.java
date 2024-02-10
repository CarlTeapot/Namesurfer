
import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {


	RandomGenerator rgen = RandomGenerator.getInstance();

	HashMap<NameSurferEntry,ArrayList<GObject>> names;
	HashMap<NameSurferEntry, Color> nameColors;
	private int width = APPLICATION_WIDTH;

	// constructor initializes the colors arraylist
	public NameSurferGraph() {
		addComponentListener(this);
		names = new HashMap<>();
		nameColors = new HashMap<>();
	}

	// clears the graph and draws the axis lines and the dates again
	public void clear() {
		names.clear();
		nameColors.clear();
		removeAll();
		update();
	}
	// adds the new name to the arraylist and draws it
	public void addEntry(NameSurferEntry entry) {
				names.put(entry, new ArrayList<>());
	}
	// draws the graph associated with the name


	// draws the lines using a for loop
	private void drawLine(NameSurferEntry entry) {
		final double BOTTOMLINEY = getHeight() - 4.5 * GRAPH_MARGIN_SIZE;
		Color color = null;
		if (nameColors.get(entry) == null) {
			color = rgen.nextColor();
			nameColors.put(entry, color);
		}

		else color = nameColors.get(entry);

		String name = entry.getName();

		double margin = 0;
		double rank = entry.getRank(10);
		double function = (BOTTOMLINEY - GRAPH_MARGIN_SIZE) / MAX_RANK;
		double position = GRAPH_MARGIN_SIZE + function * rank;

		GLabel label = new GLabel(name + " " + (int) rank, margin, position);
		label.setColor(color);

		if (rank == 0) {
			position = BOTTOMLINEY;
			label = new GLabel(name + " *", margin, position);
		}

		label.setColor(color);
		add(label);
		names.get(entry).add(label);
		for (int i = 1; i < NDECADES; i++) {
			double margin2 = (double) (getWidth() / NDECADES) * i;

			double rank2 = entry.getRank(i * 10);

			double position2 = GRAPH_MARGIN_SIZE + function * rank2;

			if (rank2 == 0)
				position2 = BOTTOMLINEY;
			GLine line = new GLine(margin, position, margin2, position2);
			names.get(entry).add(line);

			GLabel label2 = new GLabel(name + " " + (int) rank2, margin2, position2);

			if (rank2 == 0) {
				label2 = new GLabel(name + " *", margin2, position2);
			}
			if (position2 < position && position != BOTTOMLINEY) {
				label.setLocation(margin,position + 10);
				add(label);
			}

			label2.setColor(color);
			line.setColor(color);
			names.get(entry).add(label2);

			add(label2);
			add(line);
			label = label2;
			margin = margin2;
			rank = rank2;
			position = position2;
		}
	}
	public void deleteName(NameSurferEntry entry) {
		ArrayList<NameSurferEntry> delete = new ArrayList<>();
		for (NameSurferEntry temp : names.keySet()) {
			if (Objects.equals(temp.getName(), entry.getName())) {
				for (GObject object : names.get(temp))
					remove(object);
				delete.add(temp);
			}
		}
		for (NameSurferEntry name :delete)
			names.remove(name);
	}

	// updates the graph
	public void update() {
		removeAll();
		drawGraph();
		for (NameSurferEntry entry : names.keySet()) {
			drawLine(entry);
		}
	}
	// draws the graph
	private void drawGraph() {
		drawVerticalLines();
		drawHorizontalLines();
		drawDates();
	}
	// draws vertical lines using a for loop
	private void drawVerticalLines() {
		for (int i = 0; i < NDECADES; i++) {
			double margin = (double) (getWidth() / NDECADES) * i;
			GLine line = new GLine(margin, GRAPH_MARGIN_SIZE,
					margin, getHeight() - GRAPH_MARGIN_SIZE);
			add(line);
		}
	}

	// draws two horizontal lines
	private void drawHorizontalLines() {
		double bottomLineY = getHeight() - 4.5 * GRAPH_MARGIN_SIZE;
		GLine line1 = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine line2 = new GLine(0, bottomLineY,
				getWidth(), bottomLineY);
		add(line1);
		add(line2);
	}

	// draws the dates using a for loop
	private void drawDates() {
		for (int i = 0; i < NDECADES; i++) {
			double margin = ((double) getWidth() / NDECADES) * i + 5;
			add(new GLabel(START_DECADE + i * 10 + "", margin, getHeight() - 3.5 * GRAPH_MARGIN_SIZE));
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) {
		update();
	}
	public void componentShown(ComponentEvent e) { }
}
