/* Bullet
Brett Binnersley, V00776751

Defines a bullet shot by the player.
*/

import java.awt.Color;
import java.awt.Graphics2D;

class Bullet extends GameObject {
  public Bullet(int s_x, int s_y, double rot, int s_ownerId) {
    super(s_x, s_y);
    SetSolid(16, 16);
    SetType(OBJECTTYPE.BULLET);
    depth = -2;
    speed = 20.0;
    rotation = rot;
    ownerId = s_ownerId;
  }

  @Override
  public void LogicStep() {
    x += speed * Math.cos(rotation);
    y += speed * Math.sin(rotation);
    // Remove when outside of the scene
    if (x < 0 || x > Scene.Width() ||
        y < 0 || y > Scene.Height()) {
      Destroy();
    }
  }

  // Hit an enemy - stop the bullet
  @Override
  public void Collision(GameObject other) {
    if (other.type == OBJECTTYPE.WALL) {
      Destroy();
    }
    if (other.type == OBJECTTYPE.ENEMY || other.type == OBJECTTYPE.PLAYER) {
      if (other.id != ownerId) {
        Destroy();
      }
    }
  }

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setColor(new Color(0, 0, 0));
    canvas.fillOval(drawX - 5, drawY - 5, 10, 10);
  }

  private double speed;
  public int ownerId;  // Owner can not be killed by their own bullet
}
