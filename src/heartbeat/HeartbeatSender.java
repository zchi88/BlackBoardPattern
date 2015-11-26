package heartbeat;

public class HeartbeatSender implements Runnable {
	private int sendingInterval;

	public HeartbeatSender(int sendingInterval) {
		this.sendingInterval = sendingInterval;
	}
	
	public void sendHeartBeat(){
		long currentTime = System.currentTimeMillis();
		HeartbeatReciever.updateTime(currentTime);
	}

	@Override
	public void run() {
		// Send a heartbeat signal to heartbeat receiver at the sending interval
		while (true) {
			try {
				sendHeartBeat();
				Thread.sleep(sendingInterval);
			} catch (InterruptedException e) {
				System.err.println("Heartbeat sender appears to have crashed.");
				break;
			}
		} // End While
	}
}
