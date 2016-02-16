/* TopDownShooter.java

   Brett Binnersley
	 Csc 205, Uvic
	 V00776751

	 This file is the entry point for the application. It starts the GameLoop, and
	 Handles exiting the program gracefully.
*/


import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class Start {

	private GameLoop loop;
	private JFrame viewerWindow;

	// Initialize and define the width / height of the window.
	private Start() {
		initialize(1024, 768);
	}

	private void initialize(int width, int height) {
		viewerWindow = new JFrame();
		viewerWindow.setTitle("CSC 205 Game - Spring 2016, Brett Binnersley");
		viewerWindow.setBounds(100, 100, width, height + 25);  // magic number added from example. Left here.
		viewerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loop = new GameLoop(width, height);
		viewerWindow.getContentPane().add(loop, BorderLayout.CENTER);
	}

	private void start_render_loop() {
		Thread t = new Thread() {
			public void run() {
				try {
					frame_loop();
				} catch (Exception e) {
					e.printStackTrace();
				}
		 	}
		};
		t.start();
	}


	private void frame_loop() {
		long last_frame = System.nanoTime();
		while (true) {
			long this_frame = System.nanoTime();
			long frame_delta = this_frame - last_frame;
			double frame_delta_ms = frame_delta/1000000.0;
			loop.drawFrame(frame_delta_ms);
			last_frame = this_frame;
		}
	}

	public static void spawn() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.viewerWindow.setVisible(true);
					window.start_render_loop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		spawn();
	}

	/* Prints an error message and exits (intended for user errors) */
	private static void ErrorExit(String errorMessage, Object... formatArgs) {
		System.err.printf("ERROR: " + errorMessage + "\n",formatArgs);
		System.exit(0);
	}

	/* Throws a runtime error (intended for logic errors) */
	private static void ErrorAbort(String errorMessage, Object... formatArgs) {
		throw new Error(String.format(errorMessage,formatArgs));
	}
}
