/* GameObject
Brett Binnersley, V00776751

Interface for all objects that can exist in the scene
at any given time.

Base object that all other objects in the game extend.
Extended objects can override any of the given methods
below.
*/

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class GameObject implements Comparable<GameObject> {

	public enum OBJECTTYPE {
		UNKNOWN,
		PLAYER,
		ENEMY,
		WALL,
		BULLET,
		BACKGROUND,
		PARTICLESYSTEM,
    MENUITEM,
	}

	// Constructor / Destructor. Should be invoked with super (...) from the Constructor
	// of any object that implements this interface.
	public GameObject(int s_x, int s_y) {
		x = (double)s_x;
		y = (double)s_y;
		x_prev = x;
		y_prev = y;
		origin_x = 0;
		origin_y = 0;
		depth = 0;
		id = ID.GenID();
		flaggedDelete = false;
		image = null;
		solid = false;
		type = OBJECTTYPE.UNKNOWN;
	}

	public void SetImage(String s_image, int o_x, int o_y) {
		if (s_image == null) {
			image = null;
		} else {
			image = Resources.GetImage(s_image);
		}
		origin_x = o_x;
		origin_y = o_y;
	}

	// Create a bounding box around this object (around its origin)
	public void SetSolid(double width, double height) {
		solid = true;
		coll_width = width;
		coll_height = height;
	}

	public void SetType(OBJECTTYPE s_type) {
		type = s_type;
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


	/*
	SAFELY OVERRIDE ANY OF THE METHODS BELOW.
	*/

	// Simulated destructor
	public void OnDestroyed() { }

	// Collision event with any other gameobject.
	public void Collision(GameObject other) {	}

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
	public OBJECTTYPE type;  // Different for every object.
	public int depth; // Depth of this object.
	public double x;  // X position in the scene
  public double y;  // Y position in the scene
	public double x_prev;  // Previous XPosition for this object
	public double y_prev;  // Previous YPosition for this object
	public double rotation;  // Rotation of this object (in radians).

	// For rendering
	public int origin_x;  // Origin (rotaiton point) for the image (pixel)
	public int origin_y;  // Origin (rotaiton point) for the image (pixel)
	public BufferedImage image;  // Image used for rendering. Optional - Can be null.
	// Collision component (Must have an image)
	public boolean solid;
	public double coll_width;
	public double coll_height;

	// Private that every object contains.
	private boolean flaggedDelete;

	// For drawing the objects in the correct order (depth)
	@Override
	public int compareTo(GameObject o) {
		if (depth < o.depth) {
			return 1;
		}
    if (depth > o.depth) {
			return -1;
		}
    if (id > o.id) {
      return 1;
    }
    return -1;
	}
}
