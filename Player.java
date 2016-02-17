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
    moveSpeed = 0.5;
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

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setColor(new Color(0, 255, 0));
    canvas.fillOval((int)x - 10, (int)y - 10, 20, 20);
    canvas.setColor(new Color(0, 0, 0));
    canvas.drawLine((int)x, (int)y, Mouse.X(), Mouse.Y());
  }

  private double moveSpeed;
}
