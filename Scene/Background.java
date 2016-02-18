/* Player
Brett Binnersley, V00776751

Defines the user controller player.
*/

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics2D;

class Background extends GameObject {

  public Background(int width, int height) {
    super(0, 0);
    SetImage("background", 0, 0);
    SetType(OBJECTTYPE.BACKGROUND);
    depth = 1000;
    scene_width = width;
    scene_height = height;
  }

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    for (int y=0; y<scene_height; y+=256) {
      for (int x=0; x<scene_width; x+=256) {
        canvas.drawImage(image, x, y, null);
      }
    }
  }

  private int scene_width;
  private int scene_height;
}
