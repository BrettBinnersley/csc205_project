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
		x = s_x;
		y = s_y;
		id = ID.GenID();
	}
	public void Delete() {}  // Simulated destructor.

	// Run one step for this object
	public void LogicStep() {}
	public void Render(Graphics2D canvas) {}

	// Events - Optional to override everyone
	public void HandleMouseUp(int x, int y, int btn) {}
	public void HandleMouseDown(int x, int y, int btn) {}
	public void HandleMouseMove(int x, int y) {}
	public void HandleKeyDown(int keyCode) { }
	public void HandleKeyUp(int keyCode) { }

	// Variables.
  public int id;    // Unique Identifier for this object
	public int x;  // X position in the scene
  public int y;  // Y position in the scene
}
