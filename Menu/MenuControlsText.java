/* Background
Brett Binnersley, V00776751

Renders the controls text
*/

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

class MenuControlsText extends GameObject {

  public MenuControlsText() {
    super(0, 0);
    SetType(OBJECTTYPE.MENUITEM);
    depth = -10;
    font = new Font("Serif", Font.PLAIN, 48);
  }

  // Render the background
  @Override
  public void Render(Graphics2D canvas) {
    canvas.setFont(font);
    canvas.setColor(new Color(0, 0, 0));
    FontMetrics metrics = canvas.getFontMetrics(font);

    int strHeight = metrics.getHeight();
    String messages[] = {
      "Control the player below",
      "WADS to move",
      "Left mouse button to shoot",
      "Mouse to aim",
      "These will be the exact same during gameplay!",
      "Have fun!",
      "~Brett"
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
