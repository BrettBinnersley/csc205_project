/* Background
Brett Binnersley, V00776751

Renders the background of the scene (Grass)
*/

import java.awt.Graphics2D;

class Sky extends GameObject {

  public Sky() {
    super(0, 0);
    SetImage("sky", 0, 0);
    SetType(OBJECTTYPE.BACKGROUND);
    FlagNoClipDraw();
    depth = 1001;
  }

  // Render the background
  @Override
  public void Render(Graphics2D canvas) {
    for (int y=0; y<Constants.windowHeight; y+=256) {
      for (int x=0; x<Constants.windowWidth; x+=256) {
        canvas.drawImage(image, x, y, null);
      }
    }
  }

}
