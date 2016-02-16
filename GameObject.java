/* GameLoop
Brett Binnersley, V00776751

Interface for all objects that we use.

Base object that all other objects in the game extend.
Extended objects can override any of the given methods
below.
*/

import java.awt.Graphics2D;

class GameObject {

	// Constructor / Destructor
	public GameObject(int s_x, int s_y) {
		x = (double)s_x;
		y = (double)s_y;
		id = ID.GenID();
		flaggedDelete = false;
	}

	// Flag this object to be destroyed, and delete it in the destroy object phase.
	public void Destroy() {
		if (flaggedDelete) {
			return;
		}
		flaggedDelete = true;
	}

	// Run one step for this object
	public void LogicStep() {}
	public void Render(Graphics2D canvas) {}

	// Events - Optional to override everyone
	public void HandleMouseUp(int x, int y, int btn) { }
	public void HandleMouseDown(int x, int y, int btn) { }
	public void HandleMouseMove(int x, int y) { }
	public void HandleKeyPress(int keyCode) { }  // Called when a key is pressed (single frame)
	public void HandleKeyDown(int keyCode) { }  // Called when a key is held (every frame AFTER the first)
	public void HandleKeyRelease(int keyCode) { }  // Called when a key is released.
	public boolean IsFlaggedDeleted() {
		return flaggedDelete;
	}

	// Variables.
  public int id;  // Unique Identifier for this object
	public double x;  // X position in the scene
  public double y;  // Y position in the scene
	private boolean flaggedDelete;
}
