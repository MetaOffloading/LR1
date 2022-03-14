//The SequenceHandler is the piece of code that defines the sequence of events
//that constitute the experiment.
//
//SequenceHandler.Next() will run the next step in the sequence.
//
//We can also switch between the main sequence of events and a subsequence
//using the SequenceHandler.SetLoop command. This takes two inputs:
//The first sets which loop we are in. 0 is the main loop. 1 is the first
//subloop. 2 is the second subloop, and so on.
//
//The second input is a Boolean. If this is set to true we initialise the 
//position so that the sequence will start from the beginning. If it is
//set to false, we will continue from whichever position we were currently in.
//
//So SequenceHandler.SetLoop(1,true) will switch to the first subloop,
//starting from the beginning.
//
//SequenceHandler.SetLoop(0,false) will switch to the main loop,
//continuing from where we left off.

//TODO:
//scroll
//data output
//resume where you left off

package com.sam.webtasks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sam.webtasks.basictools.CheckIdExists;
import com.sam.webtasks.basictools.CheckScreenSize;
import com.sam.webtasks.basictools.ClickPage;
import com.sam.webtasks.basictools.Consent;
import com.sam.webtasks.basictools.Counterbalance;
import com.sam.webtasks.basictools.InfoSheet;
import com.sam.webtasks.basictools.Initialise;
import com.sam.webtasks.basictools.Names;
import com.sam.webtasks.basictools.PHP;
import com.sam.webtasks.basictools.ProgressBar;
import com.sam.webtasks.basictools.SessionKey;
import com.sam.webtasks.basictools.Slider;
import com.sam.webtasks.basictools.TimeStamp;
import com.sam.webtasks.iotask1.IOtask1Block;
import com.sam.webtasks.iotask1.IOtask1BlockContext;
import com.sam.webtasks.iotask1.IOtask1InitialiseTrial;
import com.sam.webtasks.iotask1.IOtask1RunTrial;
import com.sam.webtasks.iotask2.IOtask2Block;
import com.sam.webtasks.iotask2.IOtask2BlockContext;
import com.sam.webtasks.iotask2.IOtask2RunTrial;
import com.sam.webtasks.perceptualTask.PerceptBlock;
import com.sam.webtasks.timeBasedOffloading.TimeBlock;
import com.sam.webtasks.iotask2.IOtask2InitialiseTrial;
import com.sam.webtasks.iotask2.IOtask2PreTrial;

public class SequenceHandler {
	static int totalCircles, nTargets, reminderLockout;
	
	public static void Next() {	
		//Window.alert(whichLoop + "," + sequencePosition.get(whichLoop));
		
		// move forward one step in whichever loop we are now in
		sequencePosition.set(whichLoop, sequencePosition.get(whichLoop) + 1);

		switch (whichLoop) {
		case 0: // MAIN LOOP
			switch (sequencePosition.get(0)) {
			/***********************************************************************
			 * The code here defines the main sequence of events in the experiment *
			 **********************************************************************/
			//Practice 1: no special circle
			case 1:
				SessionInfo.sessionKey = SessionKey.Get();
				ClickPage.Run(Instructions.Get(0), "Next");
				break;
			case 2:
				IOtask2Block block0 = new IOtask2Block();
				block0.totalCircles = 7;
				block0.blockNum = -1;
				block0.nTargets = 0;
				block0.logDragData = true;
				
				block0.Run();
				break;
				
			//Practice 2: two special circles
			case 3:
				ClickPage.Run(Instructions.Get(1),  "Next");
				break;
			case 4:
				IOtask2Block block1 = new IOtask2Block();
				block1.totalCircles = 8;
				block1.blockNum = -2;
				block1.nTargets = 2;
				block1.offloadCondition=Names.REMINDERS_NOTALLOWED;
				block1.logDragData = true;
				block1.Run();
				break;
			case 5:
				if (IOtask2BlockContext.getnHits() == 0) { 
					SequenceHandler.SetPosition(SequenceHandler.GetPosition()-2); //this line means that instead of moving forward we will repeat the previous instructions
					ClickPage.Run(Instructions.Get(2), "Try again");
				} else {
					SequenceHandler.Next(); //move to the next instruction
				}
				break;
				
			//Practice 3: four special circles
			case 6:
				ClickPage.Run(Instructions.Get(3), "Next");
				break;
			case 7:
				IOtask2Block block2 = new IOtask2Block();
				block2.totalCircles = 10;
				block2.blockNum = -3;
				block2.nTargets = 4;
				block2.offloadCondition=Names.REMINDERS_NOTALLOWED;
				block2.logDragData = true;
				block2.Run();
				break;
				
			//Practice 4: six special circles
			case 8:
				ClickPage.Run(Instructions.Get(4), "Next");
				break;
			case 9:
				IOtask2Block block3 = new IOtask2Block();
				block3.totalCircles = 12;
				block3.blockNum = -4;
				block3.nTargets = 6;
				block3.offloadCondition=Names.REMINDERS_NOTALLOWED;
				block3.logDragData = true;
				block3.Run();
				break;
				
			//Metacognitive evaluations
			case 10:
				Slider.Run(Instructions.Get(6), "0%", "100%");
				break;
			case 11:
				PHP.logData("jol1", ""+Slider.getSliderValue(), true);
				break;
			case 12:
				Slider.Run(Instructions.Get(7), "0%", "100%");
				break;
			case 13:
				PHP.logData("jol4", ""+Slider.getSliderValue(), true);
				break;
			case 14:
				Slider.Run(Instructions.Get(8), "0%", "100%");
				break;
			case 15:
				PHP.logData("jol8", ""+Slider.getSliderValue(), true);
				break;
			
			//Forced Internal Block
			case 16:
				ClickPage.Run(Instructions.Get(19),  "Next");
				break;
			case 17:
				IOtask2Block block4= new IOtask2Block();
				
				block4.blockNum = 1;
				block4.WMC = true;
				block4.offloadCondition=Names.REMINDERS_NOTALLOWED;
				block4.nTargetsVariable = true;
				block4.nTargetsShuffle = true;
				
				
				block4.nTargetsList.add(2);
				block4.nTargetsList.add(4);
				block4.nTargetsList.add(6);
				block4.nTargetsList.add(2);
				block4.nTargetsList.add(4);
				block4.nTargetsList.add(6);
				block4.nTargetsList.add(2);
				block4.nTargetsList.add(4);
				block4.nTargetsList.add(6);
			
				block4.nTrials = 9;
				block4.Run();	
				break;
				
			//Low-effort or High-effort reminder practice
			case 18:
				if(Counterbalance.getFactorLevel("effort")==0) {
					ClickPage.Run(Instructions.Get(10), "Next");
				} else {
					ClickPage.Run(Instructions.Get(101), "Next");
				}
				
				break;
			case 19:
				IOtask2Block block5 = new IOtask2Block();
				if(Counterbalance.getFactorLevel("effort")==1) {
					block5.highEffort = true;
				}
				block5.totalCircles = 10;
				block5.blockNum = -5;
				block5.nTargets = 4;
				block5.offloadCondition=Names.REMINDERS_MANDATORY_TARGETONLY;
				block5.logDragData = true;
				block5.Run();
				break;
			case 20:
				if (IOtask2BlockContext.getnHits() < 3) { 
					SequenceHandler.SetPosition(SequenceHandler.GetPosition()-2); //this line means that instead of moving forward we will repeat the previous instructions
					ClickPage.Run(Instructions.Get(11), "Try again");
				} else {
					SequenceHandler.Next(); //move to the next instruction
				}
				break;
				
			//Block 1: Low-effort or High-effort offloading
			case 21:
				ClickPage.Run(Instructions.Get(16),  "Next");
				break;
			case 22:
				IOtask2Block block6= new IOtask2Block();
				if(Counterbalance.getFactorLevel("effort")==1) {
					block6.highEffort = true;
				}
				block6.blockNum = 2;
				block6.WMC = true;
				block6.nTargetsVariable = true;
				block6.nTargetsShuffle = true;
				
				block6.nTargetsList.add(2);
				block6.nTargetsList.add(4);
				block6.nTargetsList.add(6);
				block6.nTargetsList.add(2);
				block6.nTargetsList.add(4);
				block6.nTargetsList.add(6);
				block6.nTargetsList.add(2);
				block6.nTargetsList.add(4);
				block6.nTargetsList.add(6);
				
				block6.nTrials = 9;
				block6.Run();	
				break;
				
			//Change in effort: practice
			case 23:
				if(Counterbalance.getFactorLevel("effort")==0) {
					ClickPage.Run(Instructions.Get(17), "Next");
				} else {
					ClickPage.Run(Instructions.Get(171), "Next");
				}
				break;
			case 24:
				IOtask2Block block7 = new IOtask2Block();
				if(Counterbalance.getFactorLevel("effort")==0) {
					block7.highEffort = true;
				}
				block7.totalCircles = 10;
				block7.blockNum = -6;
				block7.nTargets = 4;
				block7.offloadCondition=Names.REMINDERS_MANDATORY_TARGETONLY;
				block7.logDragData = true;
				block7.Run();
				break;
			case 25:
				if (IOtask2BlockContext.getnHits() < 3) { 
					SequenceHandler.SetPosition(SequenceHandler.GetPosition()-2); //this line means that instead of moving forward we will repeat the previous instructions
					ClickPage.Run(Instructions.Get(11), "Try again");
				} else {
					SequenceHandler.Next(); //move to the next instruction
				}
				break;
				
			//Block 2: High-effort or Low-effort offloading
			case 26:
				if(Counterbalance.getFactorLevel("effort")==0) {
					ClickPage.Run(Instructions.Get(18), "Next");
				} else {
					ClickPage.Run(Instructions.Get(181), "Next");
				}
				break;
			case 27:
				IOtask2Block block8= new IOtask2Block();
				if(Counterbalance.getFactorLevel("effort")==0) {
					block8.highEffort = true;
				}
				block8.blockNum = 3;
				block8.WMC = true;
				block8.nTargetsVariable = true;
				block8.nTargetsShuffle = true;
				
				block8.nTargetsList.add(2);
				block8.nTargetsList.add(4);
				block8.nTargetsList.add(6);
				block8.nTargetsList.add(2);
				block8.nTargetsList.add(4);
				block8.nTargetsList.add(6);
				block8.nTargetsList.add(2);
				block8.nTargetsList.add(4);
				block8.nTargetsList.add(6);
				
				block8.nTrials = 9;
				block8.Run();	
				break;
				
			}
			break;

		/********************************************/
		/* no need to edit the code below this line */
		/********************************************/

		case 1: // initialisation loop
			switch (sequencePosition.get(1)) {
			case 1:
				// initialise experiment settings
				Initialise.Run();
				break;
			case 2:
				// make sure that a participant ID has been registered.
				// If not, the participant may not have accepted the HIT
				CheckIdExists.Run();
				break;
			case 3:
				// check the status of this participant ID.
				// have they already accessed or completed the experiment? if so,
				// we may want to block them, depending on the setting of
				// SessionInfo.eligibility
				PHP.CheckStatus();
				break;
			case 4:
				// check whether this participant ID has been used to access a previous experiment
				PHP.CheckStatusPrevExp();
				break;
			case 5:
				// clear screen, now that initial checks have been done
				RootPanel.get().clear();

				// make sure the browser window is big enough
				CheckScreenSize.Run(SessionInfo.minScreenSize, SessionInfo.minScreenSize);
				break;
			case 6:
				if (SessionInfo.runInfoConsentPages) { 
					InfoSheet.Run(Instructions.InfoText());
				} else {
					SequenceHandler.Next();
				}
				break;
			case 7:
				if (SessionInfo.runInfoConsentPages) { 
					Consent.Run();
				} else {
					SequenceHandler.Next();
				}
				break;
			case 8:
				//record the participant's counterbalancing condition in the status table				
				if (!SessionInfo.resume) {
					PHP.UpdateStatus("" + Counterbalance.getCounterbalancingCell() + ",1,0,0,0,0");
				} else {
					SequenceHandler.Next();
				}
				break;
			case 9:
				SequenceHandler.SetLoop(0, true); // switch to and initialise the main loop
				SequenceHandler.Next(); // start the loop
				break;
			}
			break;
		case 2: // IOtask1 loop
			switch (sequencePosition.get(2)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 2 *
			 * This runs a single trial of IOtask1                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask1Block block = IOtask1BlockContext.getContext();

				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(0, false);
				}

				SequenceHandler.Next();
				break;
			case 2:
				// now initialise trial and present instructions
				IOtask1InitialiseTrial.Run();
				break;
			case 3:
				// now run the trial
				IOtask1RunTrial.Run();
				break;
			case 4:
				// we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(2, true);
				SequenceHandler.Next();
				break;
			}
			break;
		case 3: //IOtask2 loop
			switch (sequencePosition.get(3)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 3 *
			 * This runs a single trial of IOtask2                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask2Block block = IOtask2BlockContext.getContext();
				
				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(0,  false);
				}
				
				SequenceHandler.Next();
				break;
			case 2:
				IOtask2InitialiseTrial.Run();
				break;
			case 3:;
				//present the pre-trial choice if appropriate
				if (IOtask2BlockContext.currentTargetValue() > -1) {
					IOtask2PreTrial.Run();
				} else { //otherwise just skip to the start of the block
					if ((IOtask2BlockContext.getTrialNum() > 0)&&(IOtask2BlockContext.countdownTimer())) {
						//if we're past the first trial and there's a timer, click to begin
						ClickPage.Run("Ready?", "Continue");
					} else if (IOtask2BlockContext.WMC()) {
						String s = "";
						
						if (IOtask2BlockContext.getNtargets()>1) {
							s = "s";
						}
						
						String reminderString = "It is up to you whether to set reminders or remember them with your own memory.";
						
						if (IOtask2BlockContext.getOffloadCondition() == Names.REMINDERS_MANDATORY_TARGETONLY) {
							reminderString = "You must set a reminder for each special circle.";
						}
						
						if (IOtask2BlockContext.getOffloadCondition() == Names.REMINDERS_NOTALLOWED) {
							reminderString = "You will not be able to set reminders, so you have to use your own memory.";
						}
						
						ClickPage.Run("This time you will get " + IOtask2BlockContext.getNtargets() + " special circle" + s + ".<br><br>"
								+ reminderString, "Continue");					
					} else	{
						SequenceHandler.Next();
					}
				}
				break;
			case 4:
				if (IOtask2BlockContext.getNTrials() == -1) { //if nTrials has been set to -1, we quit before running
					SequenceHandler.SetLoop(0,  false);
					SequenceHandler.Next();
				} else {
					//otherwise, run the trial
					IOtask2RunTrial.Run();
				}
				break;
			case 5:
				IOtask2PostTrial.Run();
				break;
			case 6:
				//we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(3,  true);
				SequenceHandler.Next();
				break;
			}
		}
	}
	
	private static ArrayList<Integer> sequencePosition = new ArrayList<Integer>();
	private static int whichLoop;

	public static void SetLoop(int loop, Boolean init) {
		whichLoop = loop;

		while (whichLoop + 1 > sequencePosition.size()) { // is this a new loop?
			// if so, initialise the position in this loop to zero
			sequencePosition.add(0);
		}

		if (init) { // go the beginning of the sequence if init is true
			sequencePosition.set(whichLoop, 0);
		}
	}
	
	// get current loop
	public static int GetLoop() {
		return (whichLoop);
	}

	// set a new position
	public static void SetPosition(int newPosition) {
		sequencePosition.set(whichLoop, newPosition);
	}

	// get current position
	public static int GetPosition() {
		return (sequencePosition.get(whichLoop));
	}
	
	// get current position from particular loop
	public static int GetPosition(int nLoop) {
		return (sequencePosition.get(nLoop));
	}
}
