package com.sam.webtasks.iotask2;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.sam.webtasks.basictools.Names;
import com.sam.webtasks.client.Params;
import com.sam.webtasks.client.SequenceHandler;

public class IOtask2BlockContext {
	private static IOtask2Block blockContext;

	public static int getSubLoop() {
		return (blockContext.subLoop);
	}
	
	public static void setContext(IOtask2Block block) {
		blockContext = block;
	}

	public static IOtask2Block getContext() {
		return (blockContext);
	}
	
	public static boolean WMC() {
		return (blockContext.WMC);
	}
	
	public static int getNtargets() {
		return (blockContext.nTargets);
	}

	// use these methods to set and find out the currently clicked circle
	public static void setClickedCircle(int clickedCircle) {
		blockContext.clickedCircle = clickedCircle;
	}
	
	// decrement the number of drags to a particular location allowed during the surprise memory test
	public static void decrementSurpriseDrags(int side) {
		blockContext.surpriseDrags[side]--;
	}
	
	public static int getSurpriseDrags(int side) {
		return(blockContext.surpriseDrags[side]);
	}

	public static int getClickedCircle() {
		return (blockContext.clickedCircle);
	}
	
	//update progress bar?
	public static boolean getUpdateProgress() {
		return (blockContext.updateProgress);
	}

	// find out the current total number of points
	public static int getTotalPoints() {
		return (blockContext.totalPoints);
	}
	
	public static boolean getShowPointLabels() {
		return (blockContext.showPointLabels);
	}
	
	// find out the amount of money earned
	public static String getMoneyString() {
		String money = "";
		int nPoints = blockContext.totalPoints;
		
		if (blockContext.pointDisplay == Names.POINT_GAINLOSS) {
			int nCents = (int) Math.ceil( (float) (100*nPoints) / Params.pointsPerDollar);
			int nDollars = nCents / 100;
			int nRemainderCents = nCents % 100;
		
			money = money + "$" + nDollars + ".";
		
			if (nRemainderCents < 10) {
				money = money + "0" + nRemainderCents;
			} else {
				money = money + nRemainderCents;
			}
		} else {
			int nPence = (int) Math.ceil( (float) (100*nPoints) / Params.pointsPerPound);
			int nPounds = nPence / 100;
			int nRemainderPence = nPence % 100;
		
			money = money + "£" + nPounds + ".";
		
			if (nRemainderPence < 10) {
				money = money + "0" + nRemainderPence;
			} else {
				money = money + nRemainderPence;
			}
		}
		return (money);	
	}
	
	//how should points be displayed?
	public static int getPointDisplay() {
		return (blockContext.pointDisplay);
	}
	
	// is the countdown timer running?
	public static boolean countdownTimer() {
		return (blockContext.countdownTimer);
	}
	
	public static int countdownTime() {
		return (blockContext.countdownTime);
	}
	
	public static void setCountdownTime(int cTime) {
		blockContext.countdownTime = cTime;
	}
	
	public static void countdown() {
		if (blockContext.countdownTime > 0) {
			blockContext.countdownTime--;
		}
	}
	
	// are we logging drag data?
	public static boolean getLogDragData() {
		return (blockContext.logDragData);
	}

	// are we displaying points to participants?
	public static boolean showPoints() {
		return (blockContext.showPoints);
	}

	// what is the target value for the current trial?
	public static int currentTargetValue() {
		return (blockContext.targetValues.get(blockContext.currentTrial));
	}

	// what is the maximum number of points (i.e. for targets in the no reminder
	// condition)
	public static int maxPoints() {
		return (blockContext.maxPoints);
	}

	// what is the current trial number
	public static int getTrialNum() {
		return (blockContext.currentTrial);
	}
	
	// what is the total number of trials
	public static int getNTrials() {
		return (blockContext.nTrials);
	}
	
	// is this a high effort block?
	public static boolean getHighEffort() {
		return (blockContext.highEffort);
	}

	// what reward frame are we in?
	public static int getRewardFrame() {
		return (blockContext.rewardFrame);
	}

	// use these methods to set and find out which is the next circle in the
	// sequence
	public static void setNextCircle(int nextCircle) {
		blockContext.nextCircle = nextCircle;
	}

	public static int getNextCircle() {
		return (blockContext.nextCircle);
	}

	public static boolean incrementNextCircle() { // returns true if there are more circles to go
		blockContext.nextCircle++;

		if (blockContext.nextCircle < blockContext.nCircles) {
			return (true);
		} else {
			return (false);
		}
	}

	// offloading condition
	public static int getOffloadCondition() {
		return(blockContext.offloadCondition);
	}
	
	public static boolean getMoveableStatus(int side) {
		return(blockContext.moveableSides[side]);
	}
	
	//instruction time
	public static int getInstructionTime() {
		return(blockContext.instructionTime);
	}
	
	public static void setOffloadCondition(int newCondition) {
		blockContext.offloadCondition = newCondition;
	}
	
	//completed circles
	public static void setCompletedCircles(int completedCircles) {
		blockContext.completedCircles=completedCircles;
	}
	
	public static void incrementCompletedCircles() {
		blockContext.completedCircles++;
	}
	
	public static int getCompletedCircles() {
		return(blockContext.completedCircles);
	}
	
	public static void setReminderCompletedCircles(int completedCircles) {
		blockContext.reminderCompletedCircles=completedCircles;
	}
	
	public static int getReminderCompletedCircles() {
		return(blockContext.reminderCompletedCircles);
	}
	
	public static void setBackupCompletedCircles(int completedCircles) {
		blockContext.backupCompletedCircles=completedCircles;
	}
	
	public static int getBackupCompletedCircles() {
		return(blockContext.backupCompletedCircles);
	}
	
	//block number
	public static int getBlockNum() {
		return(blockContext.blockNum);
	}
	
	// hits
	public static int getnHits() {
		return(blockContext.nHits);
	}
	
	//reminder choice
	public static int getReminderChoice() {
		return(blockContext.reminderChoice);
	}
	
	public static void setReminderChoice(int choice) {
		blockContext.reminderChoice=choice;
	}
	
	//circleAdjust
	public static void setCircleAdjust(int circleAdjust) {
		blockContext.circleAdjust = circleAdjust;
	}
	
	public static int getCircleAdjust()  {
		return(blockContext.circleAdjust);
	}
	
	public static void doCircleAdjustment() {
		blockContext.nextCircle -= blockContext.nCircles;
		blockContext.circleAdjust += blockContext.nCircles;
	}
	
	//nHits
	public static void setnHits(int hits) {
		blockContext.nHits = hits;
	}
	
	public static void incrementHits() {
		blockContext.nHits++;
		
		if ((blockContext.scorePoints)&&(!blockContext.gainLossExp)) {
			if (blockContext.variablePoints) {
				blockContext.totalPoints += blockContext.pointValues[blockContext.exitFlag];
			} else {
				blockContext.totalPoints += blockContext.actualPoints;
			}
		}
	}
	
	public static void decrementPoints() {
		if (blockContext.scorePoints) {
			if (blockContext.variablePoints) {
				blockContext.totalPoints -= 1;
			}
		}
	}
	
	public static void incrementPoints(int nPoints) {
		blockContext.totalPoints += nPoints;
	}
	
	public static void setReminderCost(int reminderCost) {
		blockContext.gainLossReminderCost = reminderCost;
	}
	
	public static void chargeReminderCost() {
		if (blockContext.gainLossExp) {
			blockContext.totalPoints += blockContext.gainLossReminderCost;
		}
	}
	
	//get target side - use this for checking target status
	public static int getTargetSide(int side) {
		return(blockContext.targetSide[side]);
	}

	//checkExitFlag
	public static void setCheckExitFlag(int exitFlag) {
		blockContext.checkExitFlag=exitFlag;
	}
	
	public static int getCheckExitFlag() {
		return(blockContext.checkExitFlag);
	}

	// how many points per target will be scored?
	public static void setActualPoints(int newPoints) {
		blockContext.actualPoints = newPoints;
		
		int rememberPoints = 0;
		int forgetPoints = 0;
		
		
		if (blockContext.gainLossExp) {
			if (newPoints == blockContext.maxPoints) { //forced ext or forced int condition
				rememberPoints = 10;
			} else {
				rememberPoints = newPoints;
			}
		}
		
		if (blockContext.rewardFrame == Names.LOSS_FRAME) {
			rememberPoints -= blockContext.maxPoints;
			forgetPoints -= blockContext.maxPoints;
		}
		
		blockContext.gainLossRememberPoints = rememberPoints;
		blockContext.gainLossForgetPoints = forgetPoints;
	}
	
	//adjust points if this is a gain / loss experiment
	public static void gainLossRemember() {
		if (blockContext.gainLossExp) {
			blockContext.totalPoints += blockContext.gainLossRememberPoints;
		}
	}
	
	public static void gainLossForget() {
		if (blockContext.gainLossExp) {
			blockContext.totalPoints += blockContext.gainLossForgetPoints;
		}
	}

	// post-trial feedback
	public static boolean showPostTrialFeedback() {
		return (blockContext.showPostTrialFeedback);
	}

	// set and check the doubleClickFlag
	public static void setDoubleClickFlag(boolean doubleClickFlag) {
		blockContext.doubleClickFlag = doubleClickFlag;
	}

	public static boolean getDoubleClickFlag() {
		return (blockContext.doubleClickFlag);
	}
	
	//increment current trial
	public static void incrementCurrentTrial() {
		blockContext.currentTrial++;
	}
	
	// flash flag
	public static void setFlashFlag(boolean flag) {
		blockContext.flashFlag = flag;
	}
	
	public static boolean getFlashFlag() {
		return(blockContext.flashFlag);
	}
	
	//reminder lockout
	public static boolean getReminderLockout() {
		return(blockContext.reminderLockout);
	}
	
	public static int getReminderLockoutTime() {
		return(blockContext.reminderLockoutTime);
	}
	
	//how many circles?
	public static int getnCircles() {
		return(blockContext.nCircles);
	}
	
	public static int getTotalCircles() {
		return(blockContext.totalCircles);
	}


	// use these methods to set and find which side of the box the circle has been
	// dragged to
	public static void setExitFlag(int exitFlag) {
		blockContext.exitFlag = exitFlag;
	}

	public static int getExitFlag() {
		return (blockContext.exitFlag);
	}

	// use these methods for enforcing reminders
	public static void setReminderFlag(int reminderFlag) {
		blockContext.reminderFlag = reminderFlag;
	}

	public static void setBackupReminderFlag(int reminderFlag) {
		blockContext.backupReminderFlag = reminderFlag;
	}

	public static int getReminderFlag() {
		return (blockContext.reminderFlag);
	}

	public static int getBackupReminderFlag() {
		return (blockContext.backupReminderFlag);
	}
	
	public static int getSurpriseTest() {
		return (blockContext.surpriseTest);
	}
}
