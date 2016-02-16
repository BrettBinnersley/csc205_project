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
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComponent;
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
import javax.swing.KeyStroke;
import java.util.ArrayList;

class GameLoop extends JComponent {

	public GameLoop(int width, int height) {
    canvas_width = width;
    canvas_height = height;
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
		for (GameObject obj : gameobjects) {
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
		for (GameObject obj : gameobjects) {
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
		for (GameObject obj : gameobjects) {
			obj.HandleMouseMove(x, y);
		}
	}

	private void HandleKeyDown(KeyEvent e) {
		int keyCode = e.getKeyCode();
		for (GameObject obj : gameobjects) {
			obj.HandleKeyDown(keyCode);
		}
	}

	private void HandleKeyUp(KeyEvent e) {
		int keyCode = e.getKeyCode();
		for (GameObject obj : gameobjects) {
			obj.HandleKeyUp(keyCode);
		}
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(128,128,128));
		g2d.fillRect(0, 0, canvas_width, canvas_height);
		for (GameObject obj : gameobjects) {
			obj.Render(g2d);
		}
	}

	public void drawFrame(double frame_delta_ms) {
		// Step every object
		for (GameObject obj : gameobjects) {
			obj.LogicStep();
		}
		repaint();  //This results in the paintComponent method above being called.
	}

  private int canvas_width, canvas_height;
	private GameObject[] gameobjects;
}
