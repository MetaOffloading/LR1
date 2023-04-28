package com.sam.webtasks.basictools;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.sam.webtasks.client.SequenceHandler;
import com.sam.webtasks.client.SessionInfo;

public class Initialise {
	public static void Run() {
		//set timestamp for the beginning of the experiment
		TimeStamp.Start();
		
		//generate a reward code, which can be used to claim payment at end
		RewardCode.Generate();
				
		if (SessionInfo.localTesting) {
			Window.alert("Set to local testing mode. Data will not be stored on server.");
		}
		
		if (SessionInfo.experimentType == Names.EXPERIMENT_MTURK) {
			RootPanel.get().add(new Label("initalising..."));
			
			//generate a random session key so that we have an identifier for this session
		    SessionInfo.sessionKey=SessionKey.Get();
		    
		    //get participant ID from query line
		    SessionInfo.participantID = Window.Location.getParameter("workerId");
			
			if (SessionInfo.participantID == null) {
				SessionInfo.participantID = "null";
			}
		    
			//set up the counterbalancing
			for (int i = 0; i < SessionInfo.counterbalanceFactors.length; i++) {
				if (SessionInfo.specifiedLevels[i]==-1) { //randomised level
					Counterbalance.addFactor(SessionInfo.counterbalanceFactors[i], SessionInfo.counterbalanceLevels[i]);
				} else { //specified level
					Counterbalance.addFactor(SessionInfo.counterbalanceFactors[i], SessionInfo.counterbalanceLevels[i], SessionInfo.specifiedLevels[i]);
				}
			}
		}
		
		if (SessionInfo.experimentType == Names.EXPERIMENT_PROLIFIC) {
			RootPanel.get().add(new Label("initalising..."));
			
			//get session key from query line
			SessionInfo.sessionKey = Window.Location.getParameter("SESSION_ID");
			SessionInfo.prolificExperimentCode = Window.Location.getParameter("STUDY_ID");
			SessionInfo.participantID = Window.Location.getParameter("PROLIFIC_PID");
			
			if (SessionInfo.participantID == null) {
				if (SessionInfo.localTesting == false) {
					SessionInfo.participantID = Window.prompt("What is your participant ID?",  "");
				}
			}
			
			if (SessionInfo.sessionKey == null) {
				SessionInfo.sessionKey = SessionKey.Get();
			}
			
			if (SessionInfo.prolificExperimentCode == null) {
				SessionInfo.prolificExperimentCode = SessionInfo.experimentCode;
			}
			
			//set up the counterbalancing
			for (int i = 0; i < SessionInfo.counterbalanceFactors.length; i++) {
				if (SessionInfo.specifiedLevels[i]==-1) { //randomised level
					Counterbalance.addFactor(SessionInfo.counterbalanceFactors[i], SessionInfo.counterbalanceLevels[i]);
				} else { //specified level
					Counterbalance.addFactor(SessionInfo.counterbalanceFactors[i], SessionInfo.counterbalanceLevels[i], SessionInfo.specifiedLevels[i]);
				}
			}	
		}
		
		if (SessionInfo.experimentType == Names.EXPERIMENT_WMC) {
			SessionInfo.participantID = Window.Location.getParameter("id");
		}
		

		if (SessionInfo.experimentType == Names.EXPERIMENT_STANDALONE) {
			//generate a random session key so that we have an identifier for this session
		    SessionInfo.sessionKey=SessionKey.Get();
		    
			HTML participantHTML = new HTML("Experiment: " + SessionInfo.experimentCode + ", Version: " + SessionInfo.experimentVersion + 
					"<br>Participant code:");
			final TextBox textBox = new TextBox();
			Button continueButton = new Button("Continue");
			
			RootPanel.get().add(participantHTML);
			RootPanel.get().add(textBox);
			RootPanel.get().add(continueButton);
			
			continueButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (textBox.getText().length() > 0) {
						SessionInfo.participantID = textBox.getText();
						
						//set up counterbalancing
						
						//first, check for any factors that should be fully (and reproducibly) counterbalanced, according to participant code
						
						//here, we calculate the number of unique counterbalancing cells for these levels.
						int nCells = 1;
						ArrayList<Integer> fullMax = new ArrayList<Integer>(); //maximum level for factors that will be counterbalanced fully
						
						for (int i = 0; i < SessionInfo.counterbalanceFactors.length; i++) {
							if (SessionInfo.specifiedLevels[i] == -2) {
								fullMax.add(SessionInfo.counterbalanceLevels[i]);
								nCells = nCells * SessionInfo.counterbalanceLevels[i];
							}
						}
						
						//now we assign this participant to a cell, based on their participant code
						final int ID = Integer.parseInt(SessionInfo.participantID);
						int cell = ID % nCells;
						
						//now set factor levels, according to this code
						ArrayList<Integer> fullLevel = new ArrayList<Integer>();
						
						nCells /= SessionInfo.counterbalanceLevels[SessionInfo.counterbalanceLevels.length - 1];

						for (int i = fullMax.size()-1; i >= 0; i--) {							
							fullLevel.add(cell / nCells);
	
							cell -= nCells*(cell/nCells);
							
							if (i>0) {
								nCells /= fullMax.get(i-1);
							}
						}
									
						//the code below actually sets up the factors, whether they are random, specified, or fully counterbalanced
						for (int i = 0; i < SessionInfo.counterbalanceFactors.length; i++) {
							if (SessionInfo.specifiedLevels[i]==-2) { //fully counterbalanced level
								Counterbalance.addFactor(SessionInfo.counterbalanceFactors[i], SessionInfo.counterbalanceLevels[i], fullLevel.get(fullLevel.size()-1));
								fullLevel.remove(fullLevel.size()-1);
							} else if (SessionInfo.specifiedLevels[i]==-1) { //randomised level
								Counterbalance.addFactor(SessionInfo.counterbalanceFactors[i], SessionInfo.counterbalanceLevels[i]);
							} else { //specified level
								Counterbalance.addFactor(SessionInfo.counterbalanceFactors[i], SessionInfo.counterbalanceLevels[i], SessionInfo.specifiedLevels[i]);
							}
						}
						
						
						
						SequenceHandler.Next();
					}
					
				}
			});
		} else {
			SequenceHandler.Next();
		}
	}
}
