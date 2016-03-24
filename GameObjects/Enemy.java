/* Enemy
Brett Binnersley, V00776751

Defines a simple ai controlled enemy. The AI is simple.
It randomly picks a point in the scene, and attempts to
move towards it. If it collides with any other objects,
it picks a different point and attempts to move towards
that.
*/

import java.awt.Color;
import java.awt.Graphics2D;

class Enemy extends GameObject {
  public Enemy(int s_x, int s_y) {
    super(s_x, s_y);
    SetSolid(18, 18);
    SetImage("person", 39, 39);
    SetType(OBJECTTYPE.ENEMY);
    ResetTarget();
    footprintSystem = new PS_Footprint();
    Scene.AddObject(footprintSystem);
    shootCooldown = 120;
  }

  @Override
  public void OnDestroyed() {
    footprintSystem.DestroyWhenEmpty();
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

    // Place footprints!
    footprintSystem.rotation = rotation;
    footprintSystem.x = x;
    footprintSystem.y = y;

    // Shoot a bullet
    shootCooldown -= (int)(Math.random() * 4);
    if (shootCooldown <= 0) {
      Bullet bullet = new Bullet((int)x, (int)y, rotation, id);
      Scene.AddObject(bullet);
      shootCooldown = 120;
    }
  }

  @Override
  public void Collision(GameObject other) {
    if (other.type == OBJECTTYPE.BULLET) {
      if (((Bullet)other).ownerId != id) {
        Destroy();
      }
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
    // canvas.setColor(new Color(255, 0, 0));
    // canvas.fillOval((int)x - 20, (int)y - 20, 40, 40);
    canvas.rotate(rotation, x, y);
    canvas.drawImage(image, (int)x - origin_x, (int)y - origin_y, null);
  }

  // Set target for this object to walk towards.
  private void ResetTarget() {
    targetX = Math.random() * (double)Scene.Width();
    targetY = Math.random() * (double)Scene.Height();
    rotation = Math.atan2(targetY - y, targetX - x);
  }

  private double targetX;
  private double targetY;
  private PS_Footprint footprintSystem;
  private int shootCooldown;
}
