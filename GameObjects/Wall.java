/* Enemy
Brett Binnersley, V00776751

Defines a simple ai controlled enemy
*/

import java.awt.Color;
import java.awt.Graphics2D;

class Wall extends GameObject {
  public Wall() {
    super(Scene.Width() / 2, Scene.Height() / 2);
    SetSolid(16, 256);
    SetImage("wall", 16, 256);
    SetType(OBJECTTYPE.WALL);
  }

  // Render the wall
  @Override
  public void Render(Graphics2D canvas) {
    canvas.drawImage(image, (int)x - origin_x, (int)y - origin_y, null);
  }
}
