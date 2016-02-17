/* GameLoop
Brett Binnersley, V00776751

This file controls the main gameloop, performing all the events and the 'main' logic.
It contains all the objects in the scene at any given time, and runs the events on them
when they are issued.
*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.HashSet;

class GameLoop extends JComponent {

	public enum KeyState {
		KEYPRESS,
		KEYHELD,
		KEYRELEASE
	}

	public GameLoop(int width, int height) {
    canvas_width = width;
    canvas_height = height;
		gameobjects = new HashMap<Integer, GameObject>();  // Objects in game
		downKeys = new ConcurrentHashMap<Integer, KeyState>();

		for (int i=0; i<10; ++i) {
			Enemy e = new Enemy((int)(Math.random() * (double)width), (int)(Math.random() * (double)height));
			gameobjects.put(e.id, e);
		}
		Player p = new Player(200, 200);
		gameobjects.put(p.id, p);
		setDoubleBuffered(true);
		setSize(width, height);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				HandleMouseDown(x,y,e.getButton());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				HandleMouseUp(x,y,e.getButton());
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				HandleMouseMove(x,y);
			}
		});

    addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				HandleKeyDown(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				HandleKeyUp(e);
			}
		});

		setFocusable(true);
		requestFocusInWindow();
	}


	private int canvasWidth() {
		return canvas_width;
	}


	private int canvasHeight() {
		return canvas_height;
	}


	private void HandleMouseDown(int x, int y, int button_number) {
		if (x < 0 || x >= canvasWidth()) {
      return;
    }
		if (y < 0 || y >= canvasHeight()) {
      return;
    }
		for (GameObject obj : gameobjects.values()) {
			obj.HandleMouseUp(x, y, button_number);
		}
	}


	private void HandleMouseUp(int x, int y, int button_number) {
		if (x < 0 || x >= canvasWidth()) {
      return;
    }
		if (y < 0 || y >= canvasHeight()) {
      return;
    }
		for (GameObject obj : gameobjects.values()) {
			obj.HandleMouseUp(x, y, button_number);
		}
	}

	private void HandleMouseMove(int x, int y) {
		if (x < 0 || x >= canvasWidth()) {
      return;
    }
		if (y < 0 || y >= canvasHeight()) {
      return;
    }
		Mouse.SetMouseX(x);
		Mouse.SetMouseY(y);
		for (GameObject obj : gameobjects.values()) {
			obj.HandleMouseMove(x, y);
		}
	}

	private void HandleKeyDown(KeyEvent e) {
		int keyCode = e.getKeyCode();
		downKeys.put(keyCode, KeyState.KEYPRESS);
	}

	private void HandleKeyUp(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (downKeys.containsKey(keyCode)) {
			downKeys.replace(keyCode, KeyState.KEYRELEASE);
		} else {
			downKeys.put(keyCode, KeyState.KEYRELEASE);
		}
	}

	// Draw all the objects that still exist in the game.
	@Override
	public void paintComponent(Graphics g) {

		// Make a copy of the hashmap so that it is thread safe.
		HashMap<Integer, KeyState> keyStates = downKeys.values();

		for (HashMap.Entry<Integer, KeyState> t_state : downKeys.entrySet()) {
			int key = t_state.getKey();
    	KeyState state = t_state.getValue();
			if (state == KeyState.KEYPRESS) {
				for (GameObject obj : gameobjects.values()) {
					obj.HandleKeyPress(key);
				}
				// Change concurrent state to held.
			}
			else if (state == KeyState.KEYHELD) {
				for (GameObject obj : gameobjects.values()) {
					obj.HandleKeyDown(key);
				}
			}
			else if (state == KeyState.KEYRELEASE) {
				for (GameObject obj : gameobjects.values()) {
					obj.HandleKeyRelease(key);
				}
				// Delete concurrent state.
			} else {
				System.out.println('Error: Unknown Keystate');
			}
		}

		// Step every object and handle key events
		for (GameObject obj : gameobjects.values()) {
			obj.LogicStep();
		}

		// Remove every object flagged for deletion
		ArrayList<Integer> deleteObjects = new ArrayList<Integer>();
		for (GameObject obj : gameobjects.values()) {
			if (obj.IsFlaggedDeleted()) {
				deleteObjects.add(obj.id);
			}
		}
		for (Integer key : deleteObjects) {
			gameobjects.remove(key);
		}
		deleteObjects.clear();

		// Render the component
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(128,128,128));
		g2d.fillRect(0, 0, canvas_width, canvas_height);
		for (GameObject obj : gameobjects.values()) {
			obj.Render(g2d);
		}
	}

	public void RunLogicDrawEntities(double frame_delta_ms) {
		repaint();
	}

  private int canvas_width, canvas_height;
	private HashMap<Integer, GameObject> gameobjects;
	private ConcurrentHashMap<Integer, KeyState> downKeys;
}
