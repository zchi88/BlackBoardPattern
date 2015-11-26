package pacemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class PacemakerGUI {
	static JPanel contentPane, dataPane;
	static TextArea logText;
	static JTextArea pacemakerData;

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

	protected static void updateData(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pacemakerData.setText(text);
			}
		});
	}

	public static void createGui() {
		JFrame pacemakerGUI = new JFrame("Pacemaker GUI");
		contentPane = new JPanel(new GridLayout(2, 1));

		pacemakerData = new JTextArea();
		pacemakerData.setFont(new Font("Arial", Font.BOLD, 30));
		pacemakerData.setEditable(false);
		pacemakerData.setOpaque(false);

		dataPane = new JPanel();
		dataPane.add(pacemakerData);

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
