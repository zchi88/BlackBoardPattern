package pacemaker;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * HeartSimulator is a very simplistic simulation of a heart sending a heart
 * pulse signal with occasional missed heart beats
 */
public class HeartSimulator {
	// The time interval between heart pulses in milliseconds. An interval of
	// 1000 milliseconds
	// equates to a heart rate of 60 beats per minute.
	private static int pulseInterval = 1000;

	// Used to modify pulseInterval to simulate changes in heart rate. Should be
	// a decimal
	// number between 0 and 1.
	private static double heartrateDelta = 0.15;

	public static void main(String[] args) {
		System.out.println("Heart simulation started.");
		String hostName = "localhost";
		int portNumber = 4444;
		while (true) {
			try (Socket pacemakerSocket = new Socket(hostName, portNumber);
					PrintWriter out = new PrintWriter(pacemakerSocket.getOutputStream(), true);) {
				// Send a pulse at the given pulse interval, then calculate a
				// new pulse interval.

				Random randomNumber = new Random();
				try {
					// Simulates a 10% chance of missing a beat
					int heartFailure = randomNumber.nextInt(10);
					if (heartFailure == 0) {
						Thread.sleep(pulseInterval);
					}

					Thread.sleep(pulseInterval);
					out.println("Pulse");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}

				// Used to calculate a new pulse interval, which may be faster
				// or slower than the current
				int maxPulseInterval = (int) Math.ceil(pulseInterval * (1 + heartrateDelta));
				int minPulseInterval = (int) Math.floor(pulseInterval * (1 - heartrateDelta));

				// Calculate the new pulse interval
				int newPulseInterval = randomNumber.nextInt((maxPulseInterval - minPulseInterval) + 1)
						+ minPulseInterval;

				// Ensures that the pulse rate cannot ever get too high by
				// setting a limit of 200 (=300 BPM)
				if (newPulseInterval < 200) {
					newPulseInterval = 200;
				}

				// Set the pulse interval to the new value
				pulseInterval = newPulseInterval;

			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + hostName);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " + hostName + ". Retrying...");
			}
		} // End While
	}
}
