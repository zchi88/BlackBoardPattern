package pacemaker;

/**
 * Starts a thread which updates the GUI at the specified udpate interval
 */
public class GuiUpdater implements Runnable {
	public static final int UPDATE_INTERVAL = 500;
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(UPDATE_INTERVAL);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PacemakerController.updateGUI();
		}
	}
}
