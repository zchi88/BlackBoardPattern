package blackboard;

/**
 * BlackboardController defines the order in which different knowledge sources
 * interacts with and modifies knowledge written on the Blackboard.
 * Currently, it is following a very simple process of going through each 
 * component in the order defined in the loop() method and letting them
 * update the Blackboard one after another.
 */
import pacemaker.ActivityStatusCalculator;
import pacemaker.BpmCalculator;
import pacemaker.PulseStatusCalculator;

public class BlackboardController {
	private static BpmCalculator bpm = new BpmCalculator();
	private static PulseStatusCalculator pulseStatus = new PulseStatusCalculator();
	private static ActivityStatusCalculator activityStatus = new ActivityStatusCalculator();
	
	// Loop through the following knowledge sources and have them update the 
	// knowledge written on the Blackboard.
	public static void loop(){
		bpm.updateBlackboard();
		pulseStatus.updateBlackboard();
		activityStatus.updateBlackboard();
	}
}
