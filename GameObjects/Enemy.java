/* Enemy
Brett Binnersley, V00776751

Defines a simple ai controlled enemy
*/

import java.awt.Color;
import java.awt.Graphics2D;

class Enemy extends GameObject {
  public Enemy(int s_x, int s_y) {
    super(s_x, s_y);
    SetSolid(18, 18);
    SetType(OBJECTTYPE.ENEMY);
    ResetTarget();
  }

  @Override
  public void OnDestroyed() {
    Scene.AddObject(new PS_Blood(x, y));

    if (Scene.GetNumberObjectType(OBJECTTYPE.ENEMY) == 0) {
      GameLoop.EndGame();
    }
  }

  @Override
  public void LogicStep() {
    double dir = Math.atan2(targetY - y, targetX - x);
    x += Math.cos(dir) * 3.0;
    y += Math.sin(dir) * 3.0;
    double diffx = targetX - x;
    double diffy = targetY - y;
    while (Math.sqrt(diffx * diffx + diffy * diffy) < 10.0f) {
      ResetTarget();
      diffx = targetX - x;
      diffy = targetY - y;
    }
  }

  @Override
  public void Collision(GameObject other) {
    if (other.type == OBJECTTYPE.BULLET) {
      Destroy();
    }
    if (other.type == OBJECTTYPE.ENEMY ||
        other.type == OBJECTTYPE.PLAYER ||
        other.type == OBJECTTYPE.WALL) {
      x = x_prev;
      y = y_prev;
      ResetTarget();
    }
  }

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setColor(new Color(255, 0, 0));
    canvas.fillOval((int)x - 20, (int)y - 20, 40, 40);
  }

  // Set target for this object to walk towards.
  private void ResetTarget() {
    targetX = Math.random() * (double)Scene.Width();
    targetY = Math.random() * (double)Scene.Height();
  }

  private double targetX;
  private double targetY;
}
