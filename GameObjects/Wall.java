/* Wall
Brett Binnersley, V00776751

Defines a static wall that is rendered in the
center of the scene.
*/

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
    canvas.drawImage(image, drawX - origin_x, drawY - origin_y, null);
  }
}
