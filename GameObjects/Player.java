/* Player
Brett Binnersley, V00776751

Defines the user controller player.
*/

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;

class Player extends GameObject {

  public Player(int s_x, int s_y) {
    super(s_x, s_y);
    SetImage("person", 39, 39);
    SetSolid(16, 16);
    SetType(OBJECTTYPE.PLAYER);
    moveSpeed = 3.0;
    depth = -10;
    footprintSystem = new PS_Footprint();
    Scene.AddObject(footprintSystem);
  }

  @Override
  public void OnDestroyed() {
    footprintSystem.Destroy();
  }

  // Move using WADS or the ARROW keys
  @Override
  public void HandleKeyDown(int key) {
    switch(key) {
      // Move North
      case(KeyEvent.VK_W):
      case(KeyEvent.VK_UP):
        y -= moveSpeed;
      break;

      // Move West
      case(KeyEvent.VK_A):
      case(KeyEvent.VK_LEFT):
        x -= moveSpeed;
      break;

      // Move East
      case(KeyEvent.VK_D):
      case(KeyEvent.VK_RIGHT):
        x += moveSpeed;
      break;

      // Move south
      case(KeyEvent.VK_S):
      case(KeyEvent.VK_DOWN):
        y += moveSpeed;
      break;

      // Do nothing
      default:
      break;
    }
  }

  // Set player rotation
  @Override
  public void LogicStep() {
    rotation = Math.atan2(Mouse.Y() - y, Mouse.X() - x);
    if (x < 0) {
      x = 0;
    }
    if (y < 0) {
      y = 0;
    }
    if (x > Scene.Width()) {
      x = Scene.Width();
    }
    if (y > Scene.Height()) {
      y = Scene.Height();
    }
    footprintSystem.rotation = rotation;
    footprintSystem.x = x;
    footprintSystem.y = y;
  }

  @Override
  public void HandleMousePress(int btn) {
    Bullet bullet = new Bullet((int)x, (int)y, rotation);
    Scene.AddObject(bullet);
  }

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setColor(new Color(0, 0, 255));
    canvas.drawLine((int)x, (int)y, Mouse.X(), Mouse.Y());
    canvas.rotate(rotation, x, y);
    canvas.drawImage(image, (int)x - origin_x, (int)y - origin_y, null);
  }

  private double moveSpeed;
  private PS_Footprint footprintSystem;
}
