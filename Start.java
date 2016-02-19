/* TopDownShooter.java

   Brett Binnersley
	 Csc 205, Uvic
	 V00776751

	 This file is the entry point for the application. It starts the GameLoop, and
	 Handles exiting the program gracefully.
*/


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class Start {

	private GameLoop loop;
	private JFrame viewerWindow;

	// Initialize and define the width / height of the window.
	private Start() {
		try {
			Resources.Initialize();
		} catch (Exception e) {
			System.out.println("Errors loading resources");
			return;
		}
		Initialize(Constants.windowWidth, Constants.windowHeight);
	}

	private void Initialize(int width, int height) {
		viewerWindow = new JFrame();
		viewerWindow.setTitle("CSC 205 Game - Brett Binnersley. V00776751");
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
			// Do some time stuff
			long this_frame = System.nanoTime();
			double frame_delta_ms = (double)(this_frame - last_frame) / 1000000.0;

			// Run game logic
			loop.RunLogicDrawEntities(frame_delta_ms);

			// Figure out render time.
			long now_time = System.nanoTime();
			double renderTime = (double)(now_time - this_frame) / 1000000.0;  // Time in MS to run logic
			double targetDelay = (1000.0 / (double)Constants.targetFPS);  // Target render time.
			int sleepTime = Math.max((int)(targetDelay - renderTime), 0);

			// Update the last time.
			last_frame = now_time;

			if (GameLoop.GameHasEnded()) {
				JOptionPane.showMessageDialog(null, "Congrats! You have destroyed all the enemies. Click 'Ok' to exit the program.");
				System.exit(0);
				break;
			} else {
				// Sleep (Zzz) time. This saves the CPU from killing itself.
				try {
					Thread.sleep(sleepTime);
				} catch (Exception e) {
					System.out.println("Interrupted Exception");
				}
			}
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
