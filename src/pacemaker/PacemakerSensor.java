package pacemaker;

import java.net.*;

import heartbeat.HeartbeatSender;

import java.io.*;

/**
 * 
 */
public class PacemakerSensor extends Thread {
	//Port to be used for pacemaker sensor to read users's heart rate
	private static int  pacemakerPort = 4444;

	//The interval at which a heartbeat message will be sent in milliseconds
	private static int  sendingInterval = 1000;
	
	// Name of the sensor so we can keep track of the active sensor
	private String sensorName;
	
	protected static long getSendingInterval(){
		return (long)sendingInterval;
	}
	
	public PacemakerSensor(String name){
		sensorName = name;
	}
	
	public String getSensorName(){
		return sensorName;
	}
	
	public void run(){ 
		//Start a heartbeat sender on a separate thread so that we don't run into issues where 
		//the main thread can block the sending of the heartbeat and mistake this process for dead
		Thread heartbeatSender = new Thread(new HeartbeatSender(sendingInterval));
		heartbeatSender.start();

		//Start a server socket to continually check for and receive incoming heart rate data 
		try (
				ServerSocket serverSocket = new ServerSocket(pacemakerPort, 1);
				){
			System.out.println("Pacemaker sensor is now online.");	
			int count = 10;
			while (true) {
				try (
						Socket clientSocket = serverSocket.accept();
						BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	
						)
				{
					String heartRateSignal;
					while ((heartRateSignal = in.readLine()) != null) {
						System.out.println("Pulse detected by " + getSensorName() + " sensor.");
						
						// This code artificially injects a fault which causes the sensor to fail after 10 iterations through this loop.
						int breaker = count / count;
						count --;
					}
				} catch (IOException e) {
					System.err.println("Heart was disconnected!");
				}

			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ArithmeticException e) {
			System.err.println("Caught the injected math error.");
		} finally {
			System.err.println("Sensor crashed!");
			
			// Causes the heartbeat sender thread to stop when we encounter an error with the sensor
			heartbeatSender.interrupt();
		}
	}
}