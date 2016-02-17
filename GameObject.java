/* GameLoop
Brett Binnersley, V00776751

Interface for all objects that we use.

Base object that all other objects in the game extend.
Extended objects can override any of the given methods
below.
*/

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class GameObject {

	// Constructor / Destructor
	public GameObject(int s_x, int s_y, String s_image) {
		x = (double)s_x;
		y = (double)s_y;
		id = ID.GenID();
		flaggedDelete = false;
		if (s_image == null) {
			image = null;
		} else {
			image = Resources.GetImage(s_image);
		}
	}

	// Flag this object to be destroyed, and delete it in the destroy object phase.
	public void Destroy() {
		if (flaggedDelete) {
			return;
		}
		flaggedDelete = true;
	}

	// Simply for flagging purposes
	public boolean IsFlaggedDeleted() {
		return flaggedDelete;
	}

	// Run one step for this object
	public void LogicStep() {}
	public void Render(Graphics2D canvas) {}

	// Events - Optional to override everyone
	public void HandleMousePress(int btn) { }
	public void HandleMouseHeld(int btn) { }
	public void HandleMouseRelease(int btn) { }
	public void HandleKeyPress(int keyCode) { }  // Called when a key is pressed (single frame)
	public void HandleKeyDown(int keyCode) { }  // Called when a key is held (every frame AFTER the first)
	public void HandleKeyRelease(int keyCode) { }  // Called when a key is released.


	// Variables.
  public int id;  // Unique Identifier for this object - automatically set.
	public double x;  // X position in the scene
  public double y;  // Y position in the scene
	public double direction;  // Direction of this object.
	public BufferedImage image;  // Image used for rendering. Optional - Can be null.
	private boolean flaggedDelete;
}
