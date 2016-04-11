/* Background
Brett Binnersley, V00776751

A simple scene transition (fade in)
*/

import java.awt.Graphics2D;
import java.awt.Color;

class SceneEntry extends GameObject {

  public SceneEntry(int targetR, int targetG, int targetB) {
    super(0, 0);
    SetType(OBJECTTYPE.SCENETRANSITION);
    FlagNoClipDraw();
    depth = -10000;  // Overtop everything (even ui's)...
    alpha = 255;
    r = targetR;
    g = targetG;
    b = targetB;
  }

  public boolean IsDoneTransition() {
    return alpha <= 255;
  }

  // Render the background
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setColor(new Color(r, g, b, alpha));
    canvas.fillRect(0, 0, Constants.windowWidth, Constants.windowHeight);
    alpha -= 12;
    if (alpha < 0) {
      Destroy(); // Only keep the PS alive as long as there is blood on the screen.
    }
  }

  private int alpha;
  private int r;
  private int g;
  private int b;
}
