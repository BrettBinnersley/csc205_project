/* GameLoop
Brett Binnersley, V00776751

This file controls the main gameloop, performing all the events and the 'main' logic.
It controls all the events, and the scene. The scene in turn handles passing the events
off to the objects contained within it.
*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Composite;
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
import java.awt.geom.AffineTransform;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import java.util.Arrays.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.HashSet;

class GameLoop extends JComponent {

	public enum KeyState {
		KEYPRESS,
		KEYHELD,
		KEYRELEASE
	}

	public enum MouseState {
		MOUSEPRESS,
		MOUSEHELD,
		MOUSERELEASE
	}

	public GameLoop(int width, int height) {
		// Window & Render state.
		canvas_width = width;
		canvas_height = height;
		setDoubleBuffered(true);
		setSize(width, height);
		gameHasEnded = false;

		// Create environment
		keyStates = new ConcurrentHashMap<Integer, KeyState>();
		mouseStates = new ConcurrentHashMap<Integer, MouseState>();

		// Initalize Scene and fill with the basic example.
		SceneManager.Initialize("mainmenu");  // Scene dimensions can be different than the canvases

		// Mouse Events
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				HandleMousePress(x,y,e.getButton());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				HandleMouseRelease(x,y,e.getButton());
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

	private void HandleMouseMove(int x, int y) {
		if (x < 0 || x >= canvasWidth()) {
      return;
    }
		if (y < 0 || y >= canvasHeight()) {
      return;
    }
		Mouse.SetMouseX(x);
		Mouse.SetMouseY(y);
	}

	private void HandleMousePress(int x, int y, int button_number) {
		if (x < 0 || x >= canvasWidth()) {
      return;
    }
		if (y < 0 || y >= canvasHeight()) {
      return;
    }
		if (keyStates.containsKey(button_number)) {
			mouseStates.replace(button_number, MouseState.MOUSEPRESS);
		} else {
			mouseStates.put(button_number, MouseState.MOUSEPRESS);
		}
	}

	private void HandleMouseRelease(int x, int y, int button_number) {
		if (x < 0 || x >= canvasWidth()) {
      return;
    }
		if (y < 0 || y >= canvasHeight()) {
      return;
    }
		if (keyStates.containsKey(button_number)) {
			mouseStates.replace(button_number, MouseState.MOUSERELEASE);
		} else {
			mouseStates.put(button_number, MouseState.MOUSERELEASE);
		}
	}

	private void HandleKeyDown(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyStates.containsKey(keyCode)) {
			keyStates.replace(keyCode, KeyState.KEYPRESS);
		} else {
			keyStates.put(keyCode, KeyState.KEYPRESS);
		}
	}

	private void HandleKeyUp(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyStates.containsKey(keyCode)) {
			keyStates.replace(keyCode, KeyState.KEYRELEASE);
		} else {
			keyStates.put(keyCode, KeyState.KEYRELEASE);
		}
	}

	// Handle all the keyboard and mouse events (including persistent events, IE: HELD).
	// Also handle safely removing objects from the scene.
	private void RunEvents() {
		Collection<GameObject> allObjects = Scene.GetUnorderedObjects();

		// Handle keyboard events for all of the objects.
		for (HashMap.Entry<Integer, KeyState> t_state : keyStates.entrySet()) {
			int key = t_state.getKey();
			KeyState state = t_state.getValue();
			if (state == KeyState.KEYPRESS) {
				for (GameObject obj : allObjects) {
					obj.HandleKeyPress(key);
				}
				keyStates.replace(key, KeyState.KEYHELD);
			}
			else if (state == KeyState.KEYHELD) {
				for (GameObject obj : allObjects) {
					obj.HandleKeyDown(key);
				}
			}
			else if (state == KeyState.KEYRELEASE) {
				for (GameObject obj : allObjects) {
					obj.HandleKeyRelease(key);
				}
				keyStates.remove(key);
			} else {
				System.out.println("Error: Unknown Keystate");
			}
		}

		// Handle Mouse Events for all the objects
		for (HashMap.Entry<Integer, MouseState> t_state : mouseStates.entrySet()) {
			int key = t_state.getKey();
			MouseState state = t_state.getValue();
			if (state == MouseState.MOUSEPRESS) {
				for (GameObject obj : allObjects) {
					obj.HandleMousePress(key);
				}
				mouseStates.replace(key, MouseState.MOUSEHELD);
			}
			else if (state == MouseState.MOUSEHELD) {
				for (GameObject obj : allObjects) {
					obj.HandleMouseHeld(key);
				}
			}
			else if (state == MouseState.MOUSERELEASE) {
				for (GameObject obj : allObjects) {
					obj.HandleMouseRelease(key);
				}
				mouseStates.remove(key);
			} else {
				System.out.println("Error: Unknown MouseState");
			}
		}

		// Step every object
		for (GameObject obj : allObjects) {
			obj.LogicStep();
		}

		// Find all the solid objects in the scene (ones that have a collision box)
		ArrayList<GameObject> solidObjects = new ArrayList<GameObject>();
		for (GameObject obj : allObjects) {
			if (obj.solid && !obj.IsFlaggedDeleted()) {
				solidObjects.add(obj);
			}
		}

		// Run collision logic for each object (after they have all stepped & moved)
		for (GameObject obj1 : solidObjects) {
			for (GameObject obj2 : solidObjects) {
				if (obj1.id == obj2.id) {
					continue;
				}
				// Test axis aligned bounding box (collisions)
				double o1l = obj1.x - obj1.coll_width;
				double o1r = obj1.x + obj1.coll_width;
				double o1t = obj1.y - obj1.coll_height;
				double o1b = obj1.y + obj1.coll_height;
				double o2l = obj2.x - obj2.coll_width;
				double o2r = obj2.x + obj2.coll_width;
				double o2t = obj2.y - obj2.coll_height;
				double o2b = obj2.y + obj2.coll_height;
				if (o1l < o2r && o1r > o2l && o1t < o2b && o1b > o2t) {
					obj1.Collision(obj2);
				}
			}
		}

		// Update all the object variables (end step ALL)
		for (GameObject obj : allObjects) {
			obj.x_prev = obj.x;
			obj.y_prev = obj.y;
      obj.drawX = ViewPort.GetDrawX((int)obj.x);
      obj.drawY = ViewPort.GetDrawY((int)obj.y);
		}
	}

	private void RenderAllObjects(Graphics g) {
		// Render the background
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(128,128,128));
		g2d.fillRect(0, 0, canvas_width, canvas_height);

		// Render the objects in the correct depth order
		ArrayList<GameObject> sorted = Scene.GetSortedObjects();
    int maxWidth = Constants.windowWidth + 256;
    int maxHeight = Constants.windowHeight + 256;
		for (GameObject obj : sorted) {
      if (obj.clipViewPort && (obj.drawX < -256 || obj.drawY < -256 || obj.drawX > maxWidth || obj.drawY > maxHeight)) {
        continue;
      }
			AffineTransform trans = g2d.getTransform();  // Get current transform state.
			Composite comp = g2d.getComposite();
			obj.Render(g2d);
			g2d.setTransform(trans);  // Reset transformation state.
			g2d.setComposite(comp);  //reset the composite
		}
	}

	// Draw all the objects that still exist in the game.
	@Override
	public void paintComponent(Graphics g) {
		RunEvents();									  // Run all the events for every object.
    Scene.RemoveFlaggedObjects();   // Remove all flagged objects (from events)
    ViewPort.UpdateView();          // Update the viewport
    RenderAllObjects(g);				    // Render Everything
    if (!SceneManager.RunSceneChangeFromGameLoop()) {
      // Scene didn't change. Add queued objects.
  		Scene.AddQueuedObjectsToScene();  // Add newly created objects after render (from the scene)
    }
	}

	public void RunLogicDrawEntities(double frame_delta_ms) {
		repaint();
	}

	public static boolean GameHasEnded() {
		return gameHasEnded;
	}

	public static void EndGame() {
		gameHasEnded = true;
	}

  private int canvas_width, canvas_height;
	private static boolean gameHasEnded;
	private ConcurrentHashMap<Integer, KeyState> keyStates;
	private ConcurrentHashMap<Integer, MouseState> mouseStates;
}
