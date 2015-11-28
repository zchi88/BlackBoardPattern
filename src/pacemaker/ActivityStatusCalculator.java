package pacemaker;

import blackboard.Blackboard;
import blackboard.KnowledgeSource;

/**
 * ActivityStatusCalculator is a knowledge source in the Blackboard pattern.
 * It's responsibility is to determine how hard a patient's heart is working
 * based on how fast it is beating.
 */
public class ActivityStatusCalculator implements KnowledgeSource{

	@Override
	public void updateBlackboard() {
		Long bpm = Blackboard.getBpm();
		if (bpm <= 80) {
			Blackboard.setActivityLevel("Resting");
			return;
		} 
		
		if (bpm > 80 && bpm <= 150) {
			Blackboard.setActivityLevel("Moderate Activity");
			return;
		} 
		
		if (bpm > 150 && bpm <= 200) {
			Blackboard.setActivityLevel("Intense Activity");
			return;
		} 
		
		if (bpm > 200) {
			Blackboard.setActivityLevel("Danger!");
			return;
		} 
	}

	@Override
	public void activateController() {
		// TODO Auto-generated method stub
		
	}

}
