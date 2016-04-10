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
    SetType(OBJECTTYPE.MENUITEM);
    depth = 0;
    font = new Font("Serif", Font.PLAIN, 64);
  }

  // Render the background
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setFont(font);
    canvas.setColor(new Color(0, 0, 0));
    FontMetrics metrics = canvas.getFontMetrics(font);

    int strHeight = metrics.getHeight();
    String messages[] = {
      "Csc 205 Project!!!"
    };

    int drawX = Constants.windowWidth / 2;
    int drawY = 75;  // Bad hardcoded constants. BLEH
    for (String msg : messages) {
      drawY += (int)(strHeight * 1.2);
      int strWidth = metrics.stringWidth(msg) / 2;
      canvas.drawString(msg, drawX - strWidth, drawY - strHeight);
    }
  }

  private Font font;
}
