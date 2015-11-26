package heartbeat;

public class HeartbeatReciever implements Runnable {
	// The frequency at which we will check for a heartbeat sender update in
	// miliseconds
	private static long checkingInterval;

	// The length of time a heartbeat sender can be idle before we declare it
	// dead
	private static long expireTime;

	// The last time we recieved a heartbeat sender aliveness message
	private static long lastUpdatedTime;

	// The time at which we check for the aliveness of the heartbeat sender
	private static long checkingTime;

	public HeartbeatReciever(long checkingInterval, long expireTime) {
		HeartbeatReciever.checkingInterval = checkingInterval;
		HeartbeatReciever.expireTime = expireTime;
	}

	// Checks at the checkingInterval if the heartbeat sender has been sending heartbeat messages. 
	// Returns: false if a message has not been received within the expireTime limit. True otherwise.
	private boolean checkAlive() {
		try {
			Thread.sleep(checkingInterval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		checkingTime = System.currentTimeMillis();

		if ((checkingTime - lastUpdatedTime) > expireTime) {
			return false;
		}
		return true;
	}

	public static void updateTime(long updateTime) {
		lastUpdatedTime = updateTime;
	}


	@Override
	public void run() {
		while (true) {
			if (!checkAlive()) {
				FaultMonitor.notify("Heartbeat sender is dead");
			} 
		}
	}
}
