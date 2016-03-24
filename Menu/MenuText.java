/* Background
Brett Binnersley, V00776751

Renders the background of the scene (Grass)
*/

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

class MenuText extends GameObject {

  public MenuText() {
    super(0, 0);
    SetImage("menuBackground", 0, 0);
    SetType(OBJECTTYPE.BACKGROUND);
    depth = 0;
    font = new Font("Serif", Font.PLAIN, 96);
  }

  // Render the background
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setFont(font);
    canvas.setColor(new Color(0, 0, 0));
    FontMetrics metrics = canvas.getFontMetrics(font);

    int strHeight = metrics.getHeight();
    String messages[] = {
      "Controls",
      "Move: <WADS>",
      "Shoot: <LMB>"
    };

    int drawX = Scene.Width() / 2;
    int drawY = Scene.Height() / 2 - (messages.length * strHeight / 2) - 150;  // Bad hardcoded constants. BLEH
    for (String msg : messages) {
      drawY += (int)(strHeight * 1.2);
      int strWidth = metrics.stringWidth(msg) / 2;
      canvas.drawString(msg, drawX - strWidth, drawY - strHeight);
    }
  }

  private int scene_width;
  private int scene_height;
  private Font font;
}
