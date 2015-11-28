package pacemaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;



/**
 * PacemakerGUI is a simple GUI to allow users to see what the pacemaker system is doing 
 * as it runs.
 */
public class PacemakerGUI {
	static JPanel contentPane, heartDataPane, dataPane, bpmPane, pulseStatusPane, activityLevelPane;
	static TextArea logText;
	static JTextField activeSensor, bpm, pulseStatus, activityLevel;
	static JLabel bpmLabel, pulseStatusLabel, activityLevelLabel;

	// For redirecting the console log of the application to the GUI
	private static void redirectSystemStreams() {
		OutputStream out = new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				updateLog(String.valueOf((char) b));
			}

			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				updateLog(new String(b, off, len));
			}

			@Override
			public void write(byte[] b) throws IOException {
				write(b, 0, b.length);
			}
		};
		System.setOut(new PrintStream(out, true));
		System.setErr(new PrintStream(out, true));
	}

	// For dynamically updating the text area in the GUI's log
	private static void updateLog(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logText.append(text);
			}
		});
	}

	protected static void updateData(String sensor, String beats, String pulse, String activity) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				activeSensor.setText(sensor);
				bpm.setText(beats);
				pulseStatus.setText(pulse);
				activityLevel.setText(activity);
			}
		});
	}

	public static void createGui() {
		// Create the main GUI window
		JFrame pacemakerGUI = new JFrame("Pacemaker GUI");
		
		// Create a content panel which will house all the GUI components 
		contentPane = new JPanel(new GridLayout(2, 1));

		// Create the Data panel which will show all the patient's heart data
		dataPane = new JPanel(new BorderLayout());
		
		// Create the Data panel which will show all the patient's heart data
		heartDataPane = new JPanel(new GridLayout(1, 3));
		
		// Create the component in the GUI which displays the active sensor
		// detected by the pacemaker
		activeSensor = new JTextField();
		activeSensor.setFont(new Font("Arial", Font.BOLD, 30));
		activeSensor.setEditable(false);
		activeSensor.setHorizontalAlignment(JTextField.CENTER);
		activeSensor.setBorder(null);
		
		
		// Create the component in the GUI which displays the patient's BPM
		bpm = new JTextField();
		bpm.setFont(new Font("Arial", Font.PLAIN, 24));
		bpm.setEditable(false);
		bpm.setHorizontalAlignment(JTextField.CENTER);
		bpm.setBackground(Color.WHITE);
		bpm.setBorder(null);
		bpmLabel = new JLabel("BPM", SwingConstants.CENTER);
		bpmLabel.setFont(new Font("Arial", Font.PLAIN, 24));

		
		// Create the component in the GUI which displays the patient's heart pulse status
		pulseStatus = new JTextField();
		pulseStatus.setFont(new Font("Arial", Font.PLAIN, 24));
		pulseStatus.setEditable(false);
		pulseStatus.setHorizontalAlignment(JTextField.CENTER);
		pulseStatus.setBackground(Color.WHITE);
		pulseStatus.setBorder(null);
		pulseStatusLabel = new JLabel("Heart Status", SwingConstants.CENTER);
		pulseStatusLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		
		// Create the component in the GUI which displays the patient's current activity level.
		activityLevel = new JTextField();
		activityLevel.setFont(new Font("Arial", Font.PLAIN, 24));
		activityLevel.setEditable(false);
		activityLevel.setHorizontalAlignment(JTextField.CENTER);
		activityLevel.setBackground(Color.WHITE);
		activityLevel.setBorder(null);
		activityLevelLabel = new JLabel("Activity Level", SwingConstants.CENTER);
		activityLevelLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		
		Border dataBorder = BorderFactory.createLineBorder(Color.GRAY);
		
		bpmPane = new JPanel(new BorderLayout());
		bpmPane.add(bpmLabel, BorderLayout.PAGE_START);
		bpmPane.add(bpm, BorderLayout.CENTER);
		bpmPane.setBorder(dataBorder);
		
		pulseStatusPane = new JPanel(new BorderLayout());
		pulseStatusPane.add(pulseStatusLabel, BorderLayout.PAGE_START);
		pulseStatusPane.add(pulseStatus, BorderLayout.CENTER);
		pulseStatusPane.setBorder(dataBorder);
		
		activityLevelPane = new JPanel(new BorderLayout());
		activityLevelPane.add(activityLevelLabel, BorderLayout.PAGE_START);
		activityLevelPane.add(activityLevel, BorderLayout.CENTER);
		activityLevelPane.setBorder(dataBorder);
		
		heartDataPane.add(bpmPane);
		heartDataPane.add(pulseStatusPane);
		heartDataPane.add(activityLevelPane);

		dataPane.add(activeSensor, BorderLayout.PAGE_START);
		dataPane.add(heartDataPane, BorderLayout.CENTER);
	
		// Create the log of the GUI which displays pacemaker runtime information
		logText = new TextArea();
		logText.setBackground(Color.WHITE);
		logText.setEditable(false);

		contentPane.add(dataPane);
		contentPane.add(logText);

		pacemakerGUI.add(contentPane);
		pacemakerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pacemakerGUI.setVisible(true);
		pacemakerGUI.setMinimumSize(new Dimension(800, 400));
		pacemakerGUI.setLocationRelativeTo(null);
	}

	public static void showGUI() {
		redirectSystemStreams();
		createGui();
	}
}
