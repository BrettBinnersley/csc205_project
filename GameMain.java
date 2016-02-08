/* CSC205A2SampleBall.java

   A sample program consisting of an animated ball which bounces off of the edges of the screen.

   B. Bird - 01/21/2016
*/

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

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






class Canvas205 extends JComponent{

	private static final long serialVersionUID = 1L;

	public static final int CANVAS_SIZE_X = 800;
	public static final int CANVAS_SIZE_Y = 600;
	public static final int BALL_RADIUS = 15;
	public static final double BALL_VELOCITY = 150; //Pixels/second
	public static final Color[] BALL_COLOURS = {
			new Color(0,0,0),
			new Color(255,0,0),
			new Color(0,255,0),
			new Color(0,0,255),
			new Color(0,255,255),
			new Color(255,0,255),
			new Color(255,255,0)
		};



	public Canvas205(){
		setDoubleBuffered(true);
		setSize(CANVAS_SIZE_X,CANVAS_SIZE_Y);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				handleMouseDown(x,y,e.getButton());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				handleMouseUp(x,y,e.getButton());
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				handleMouseMove(x,y);
			}
		});
		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				HandleKeyDown(e);
			}
			@Override
			public void keyReleased(KeyEvent e){
				HandleKeyUp(e);
			}
		});
		setFocusable(true);
		requestFocusInWindow();

		//Start the ball at 30 degrees from horizontal.
		ball_direction[0] = Math.cos(30*(Math.PI/180));
		ball_direction[1] = Math.sin(30*(Math.PI/180));
	}

	private int canvasWidth(){
		return CANVAS_SIZE_X;
	}
	private int canvasHeight(){
		return CANVAS_SIZE_Y;
	}

	/* Event handlers and drawing functions */

	private void handleMouseDown(int x, int y, int button_number){

		//Reject the point if it's out of bounds
		if (x < 0 || x >= canvasWidth())
			return;
		if (y < 0 || y >= canvasHeight())
			return;
	}


	private void handleMouseUp(int x, int y, int button_number){

		//Reject the point if it's out of bounds
		if (x < 0 || x >= canvasWidth())
			return;
		if (y < 0 || y >= canvasHeight())
			return;
	}

	private void handleMouseMove(int x, int y){

		//Reject the point if it's out of bounds
		if (x < 0 || x >= canvasWidth())
			return;
		if (y < 0 || y >= canvasHeight())
			return;
	}

	private void HandleKeyDown(KeyEvent e){
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP){
			ball_colour_idx++;
			if (ball_colour_idx >= BALL_COLOURS.length)
				ball_colour_idx = 0;
		}else if (keyCode == KeyEvent.VK_DOWN){
			ball_colour_idx--;
			if (ball_colour_idx < 0)
				ball_colour_idx = BALL_COLOURS.length-1;
		}else if (keyCode == KeyEvent.VK_R){
			double[] rotate = {Math.cos(30*(Math.PI/180.0)),Math.sin(30*(Math.PI/180.0))};
			double[] new_direction = {rotate[0]*ball_direction[0] - rotate[1]*ball_direction[1],
						  rotate[0]*ball_direction[1] + rotate[1]*ball_direction[0]};
			ball_direction[0] = new_direction[0];
			ball_direction[1] = new_direction[1];
		}
	}

	private void HandleKeyUp(KeyEvent e){
		int keyCode = e.getKeyCode();
	}



	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(128,128,128));
		g2d.fillRect(0,0,canvasWidth(),canvasHeight());

		g2d.setColor(BALL_COLOURS[ball_colour_idx]);

		g2d.fillOval((int)ball_position[0]-BALL_RADIUS,(int)ball_position[1]-BALL_RADIUS,2*BALL_RADIUS,2*BALL_RADIUS);



	}

	public void drawFrame(double frame_delta_ms){

		double frame_delta_seconds = frame_delta_ms/1000.0;

		double position_delta = frame_delta_seconds*BALL_VELOCITY;

		double new_x = ball_position[0] + ball_direction[0]*position_delta;
		double new_y = ball_position[1] + ball_direction[1]*position_delta;

		//The ball collides with the edge of the screen if the new position is less than BALL_RADIUS
		//pixels away from any edge.



		if (new_x <= BALL_RADIUS){
			//Collide with left edge

			//Determine how far past the collision point the new position is.
			double offset_x = BALL_RADIUS-new_x;
			//Mirror the direction around the y axis (since the ball bounces)
			ball_direction[0] = -ball_direction[0];
			new_x += 2*offset_x;
		}else if(new_x >= CANVAS_SIZE_X - BALL_RADIUS){
			//Collide with right edge

			//Determine how far past the collision point the new position is.
			double offset_x = new_x - (CANVAS_SIZE_X-BALL_RADIUS);
			//Mirror the direction around the y axis (since the ball bounces)
			ball_direction[0] = -ball_direction[0];
			new_x -= 2*offset_x;
		}else if(new_y <= BALL_RADIUS){
			//Collide with top

			//Determine how far past the collision point the new position is.
			double offset_y = BALL_RADIUS-new_y;
			//Mirror the direction around the x axis (since the ball bounces)
			ball_direction[1] = -ball_direction[1];
			new_y += 2*offset_y;
		}else if(new_y >= CANVAS_SIZE_Y - BALL_RADIUS){
			//Collide with bottom

			//Determine how far past the collision point the new position is.
			double offset_y = new_y - (CANVAS_SIZE_Y-BALL_RADIUS);
			//Mirror the direction around the x axis (since the ball bounces)
			ball_direction[1] = -ball_direction[1];
			new_y -= 2*offset_y;
		}
		ball_position[0] = new_x;
		ball_position[1] = new_y;

		repaint(); //This results in the paintComponent method above being called.
	}

	double[] ball_position = {CANVAS_SIZE_X/2, CANVAS_SIZE_Y/2};
	double[] ball_direction = {0,0};
	int ball_colour_idx = 0;
}



public class GameMain {

	private Canvas205 canvas;
	private JFrame viewerWindow;


	private GameMain() {
		initialize();
	}


	private void initialize() {
		viewerWindow = new JFrame();
		viewerWindow.setTitle("CSC 205 - Spring 2016");
		viewerWindow.setBounds(100, 100, canvas.CANVAS_SIZE_X, canvas.CANVAS_SIZE_Y+25);
		viewerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		canvas = new Canvas205();
		viewerWindow.getContentPane().add(canvas, BorderLayout.CENTER);

	}

	private void start_render_loop(){
		Thread t = new Thread()
		{
		 public void run()
		 {
				try {
					frame_loop();
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		};
		t.start();
	}

	private void frame_loop(){
		//nanoTime returns the time in nanoseconds (so divide by 1000000000 to recover the time in seconds)
		long last_frame = System.nanoTime();
		while (true){
			long this_frame = System.nanoTime();
			long frame_delta = this_frame-last_frame;

			//Convert the frame delta to milliseconds
			double frame_delta_ms = frame_delta/1000000.0;
			canvas.drawFrame(frame_delta_ms);
			last_frame = this_frame;
		}
	}



	public static void spawn() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMain window = new GameMain();
					window.viewerWindow.setVisible(true);
					window.start_render_loop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args){
		spawn();
	}

	/* Prints an error message and exits (intended for user errors) */
	private static void ErrorExit(String errorMessage, Object... formatArgs){
		System.err.printf("ERROR: " + errorMessage + "\n",formatArgs);
		System.exit(0);
	}
	/* Throws a runtime error (intended for logic errors) */
	private static void ErrorAbort(String errorMessage, Object... formatArgs){
		throw new Error(String.format(errorMessage,formatArgs));
	}

}
