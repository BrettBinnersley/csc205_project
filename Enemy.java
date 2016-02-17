/* Player
Brett Binnersley, V00776751

Defines a simple ai controlled enemy
*/

import java.awt.Color;
import java.awt.Graphics2D;

class Enemy extends GameObject {
  public Enemy(int s_x, int s_y) {
    super(s_x, s_y, null);
  }

  @Override
  public void LogicStep() {
    x += -1.0 + Math.random() * 2;
    y += -1.0 + Math.random() * 2;
  }

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setColor(new Color(255, 0, 0));
    canvas.fillOval((int)x - 20, (int)y - 20, 40, 40);
  }
}
