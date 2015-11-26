package pacemaker;

import java.util.ArrayList;

import heartbeat.HeartbeatReciever;

public class PacemakerController {
	// Specify how often we want to check for the aliveness of the heartbeat
	// sender
	private static long checkingInterval = 1000;

	// Specify how long the heartbeat sender can be inactive before we declare
	// it to be dead.
	// The value of this should be the sending interval of the heartbeat sender
	// plus a small buffer
	// to account for possible delays
	private static long expireTime;

	private static PacemakerSensor mainSensor = new PacemakerSensor("Main");
	private static PacemakerSensor backupSensor1 = new PacemakerSensor("Backup1");
	private static PacemakerSensor backupSensor2 = new PacemakerSensor("Backup2");
	private static PacemakerSensor activeSensor;
	private static PacemakerSensor brokenSensor;
	private static ArrayList<PacemakerSensor> sensorsList = new ArrayList<PacemakerSensor>();

	protected static String getWorkingSensor() {
		return activeSensor.getSensorName();
	}

	
	private static void startNewSensor(){
		for (PacemakerSensor sensor : sensorsList) {
			if (sensor.getSensorName() != brokenSensor.getSensorName()) {
				activeSensor = sensor;
				activeSensor.start();
				System.err.println("Sensor switched to " + activeSensor.getSensorName() + "...");
				break;
			}
		}
	}
	
	private static void reconfigureBrokenSensor() {
		for (PacemakerSensor sensor : sensorsList) {
			if (sensor.getSensorName() == brokenSensor.getSensorName()) {
				sensorsList.remove(sensor);
				System.err.println("Failed sensor (" + brokenSensor.getSensorName() + ") was removed...");
				break;
			}
		}
		
		switch (brokenSensor.getSensorName()) {
		case "Main":
			mainSensor = new PacemakerSensor("Main");
			sensorsList.add(mainSensor);
			break;
		case "Backup1":
			backupSensor1 = new PacemakerSensor("Backup1");
			sensorsList.add(backupSensor1);
			break;
		case "Backup2":
			backupSensor2 = new PacemakerSensor("Backup2");
			sensorsList.add(backupSensor2);
			break;
		}
		
		System.err.println(brokenSensor.getSensorName() + " sensor was successfully reconfigured!");
	}
	
	public static void initiateRecovery() {
		System.err.println("Recovery initiated.");
		brokenSensor = activeSensor;
		startNewSensor();
		reconfigureBrokenSensor();
	}
	
	public static void updateGUI() {
		PacemakerGUI.updateData("Active sensor: " + activeSensor.getSensorName());
	}


	public static void main(String[] args) {
		PacemakerGUI.showGUI();
		
		// Add a 10% buffer so we don't declare a false positive of the
		// heartbeat sender being dead
		expireTime = (long) Math.floor(PacemakerSensor.getSendingInterval() * 1.1);
		Thread heartbeatReciever = new Thread(new HeartbeatReciever(checkingInterval, expireTime));
		heartbeatReciever.start();
		System.out.println("Pacemaker controller is now online.");

		sensorsList.add(mainSensor);
		sensorsList.add(backupSensor1);
		sensorsList.add(backupSensor2);

		activeSensor = mainSensor;
		activeSensor.start();
		
		updateGUI();
	}
}
