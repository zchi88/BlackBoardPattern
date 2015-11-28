package blackboard;

import java.util.ArrayList;


/**
 * Blackboard serves as a memory store of knowledge provided by knowledge sources.
 * For the pacemaker application, the blackboard holds knowledge which is relevant
 * to figuring out the health status of a patient's heart
 */
public class Blackboard {
	// A list containing the timestamps of patient's heart pulsations when they were
	// detected by the pacemaker
	private static ArrayList<Long> timeStamps = new ArrayList<Long>();
	
	// The patient's heart beats per minute
	private static Long bpm;
	
	// The patient's pulse status (unknown, normal, or arrhythmic)
	private static String pulseStatus;
	
	// The patient's activity level (resting, moderate activity, intense activity, or danger)
	private static String activityLevel;
	
	// For keeping track of when the sensor crashes
	private static Long sensorDown;
	
	// For keeping track of when a crashed sensor recovers
	private static Long sensorUp;
	
	

	public static ArrayList<Long> getTimeStamps() {
		return timeStamps;
	}
	
	// Ensures that the timeStamps array does not exceed 10 items
	public static void updateTimeStamps(Long timeStamp) {
		if (timeStamps.size() >= 10) {
			timeStamps.remove(0);
		}
		timeStamps.add(timeStamp);
	}
	public static Long getBpm() {
		return bpm;
	}
	public static void setBpm(Long bpm2) {
		Blackboard.bpm = bpm2;
	}
	public static String getPulseStatus() {
		return pulseStatus;
	}
	public static void setPulseStatus(String heartStatus) {
		Blackboard.pulseStatus = heartStatus;
	}
	public static String getActivityLevel() {
		return activityLevel;
	}
	public static void setActivityLevel(String activityLevel) {
		Blackboard.activityLevel = activityLevel;
	}
	public static Long getSensorDown() {
		return sensorDown;
	}
	public static void setSensorDown(Long sensorDown) {
		Blackboard.sensorDown = sensorDown;
	}
	public static Long getSensorUp() {
		return sensorUp;
	}
	public static void setSensorUp(Long sensorUp) {
		Blackboard.sensorUp = sensorUp;
	}
}
