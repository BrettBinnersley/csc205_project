/* Background
Brett Binnersley, V00776751

Renders the background of the scene (Grass)
*/

import java.awt.Graphics2D;

class Background extends GameObject {

  public Background(int width, int height) {
    super(0, 0);
    SetImage("background", 0, 0);
    SetType(OBJECTTYPE.BACKGROUND);
    FlagNoClipDraw();
    depth = 1000;
    scene_width = width;
    scene_height = height;
  }

  // Render the background
  @Override
  public void Render(Graphics2D canvas) {
    for (int y=0; y<scene_height; y+=256) {
      for (int x=0; x<scene_width; x+=256) {
        canvas.drawImage(image, drawX + x, drawY + y, null);
      }
    }
  }

  private int scene_width;
  private int scene_height;
}
