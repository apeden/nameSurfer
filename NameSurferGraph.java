/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		
		}
		
		//	 You fill in the rest //
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		displayedEntries.clear();
		
		//	 You fill this in //
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		displayedEntries.add(entry);
		
		// You fill this in //
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		addVerticalLines();
		addHorizontalLines();
		addDecadeLabels();
		addGraphLabelsAndLines();
		//for (int i = 0; i > displayedEntries.size(); i++) {
			
	} 		
		
		//	 You fill this in //

	private void addVerticalLines() {
		for (int i = 1; i< NDECADES; i++) {
			add (new GLine(	GRAPH_MARGIN_SIZE + i*((getWidth()-(2*GRAPH_MARGIN_SIZE))/NDECADES), 
							(4*getHeight()/5)-GRAPH_MARGIN_SIZE,
							GRAPH_MARGIN_SIZE + i*((getWidth()-(2*GRAPH_MARGIN_SIZE))/NDECADES), 
							GRAPH_MARGIN_SIZE));
		}
	}
	
	
	private void addHorizontalLines() {
		add (new GLine(	GRAPH_MARGIN_SIZE, 
			GRAPH_MARGIN_SIZE, 
			GRAPH_MARGIN_SIZE + (getWidth()-(2*GRAPH_MARGIN_SIZE)), 
			GRAPH_MARGIN_SIZE));

		add (new GLine(		GRAPH_MARGIN_SIZE, 
			(4*getHeight()/5) - GRAPH_MARGIN_SIZE, 
			GRAPH_MARGIN_SIZE + (getWidth()-(2*GRAPH_MARGIN_SIZE)), 
			(4*getHeight()/5) - GRAPH_MARGIN_SIZE));
	}
	
	private void addDecadeLabels() {
		for (int i = 0; i< NDECADES; i++) {
			add (new GLabel(Integer.toString(1900 +(i*10))+"s", 
					(getWidth()/(2*NDECADES)) + (i*((getWidth()-(2*GRAPH_MARGIN_SIZE))/NDECADES)),
					(4*getHeight()/5) - (GRAPH_MARGIN_SIZE/2)));
		}
	}
	
	private void addGraphLabelsAndLines() {
		for (int entry = 0; entry < displayedEntries.size(); entry++) {
			String name = displayedEntries.get(entry).getName();
			for (int decade = 1900; decade < 2010; decade += 10) { 
				int rank =  displayedEntries.get(entry).getRank(decade);
				if (rank > MAX_RANK) {
					rank = MAX_RANK;
				}
			
				add (new GLabel	(name, 
								GRAPH_MARGIN_SIZE + ((decade-1900)/10)*((getWidth()-(2*GRAPH_MARGIN_SIZE))/NDECADES),
								GRAPH_MARGIN_SIZE + (((4*getHeight()/5)- (2*GRAPH_MARGIN_SIZE))*(1-(Double.valueOf(rank)/Double.valueOf(MAX_RANK))))
								));
				if (decade < 2000) {
					int rankNext =  displayedEntries.get(entry).getRank(decade+10);
					if (rankNext > MAX_RANK) {
						rankNext = MAX_RANK;
					}
					GLine line =    new GLine  (GRAPH_MARGIN_SIZE + ((decade-1900)/10)*((getWidth()-(2*GRAPH_MARGIN_SIZE))/NDECADES),
									GRAPH_MARGIN_SIZE + (((4*getHeight()/5)- (2*GRAPH_MARGIN_SIZE))*(1-(Double.valueOf(rank)/Double.valueOf(MAX_RANK)))),
									GRAPH_MARGIN_SIZE + ((decade-1900+10)/10)*((getWidth()-(2*GRAPH_MARGIN_SIZE))/NDECADES),
									GRAPH_MARGIN_SIZE + (((4*getHeight()/5)- (2*GRAPH_MARGIN_SIZE))*(1-(Double.valueOf(rankNext)/Double.valueOf(MAX_RANK)))));
					line.setColor(colors[entry % 4]);
					add(line);
				}
			}											
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }

	/* Private instance variables */
	private ArrayList <NameSurferEntry> displayedEntries = new ArrayList <NameSurferEntry>();
	private Color[] colors = { Color.MAGENTA, Color.BLACK, Color.RED, Color.BLUE};
	
	
}
