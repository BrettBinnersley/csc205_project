/* Background
Brett Binnersley, V00776751

Renders the background of the scene (Grass)
*/

import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

class MenuControls extends GameObject {

  public MenuControls() {
    super(0, 0);
    SetType(OBJECTTYPE.MENUITEM);
    depth = 0;
    font = new Font("Serif", Font.PLAIN, 48);
    xmin = 0;
    ymin = 0;
    width = 0;
    height = 0;
  }

  private boolean MouseIsHovering() {
    return (Mouse.X() > xmin && Mouse.X() < xmin + width && Mouse.Y() > ymin && Mouse.Y() < ymin + height) ;
  }

  @Override
  public void HandleMousePress(int btn) {
    if (btn == MouseEvent.BUTTON1 && MouseIsHovering()) {
      SceneManager.SetScene("menucontrols");  // Switch scene to credits.
    }
  }

  // Render the button
  @Override
  public void Render(Graphics2D canvas) {
    String message = "Controls";
    // Do the maths.
    FontMetrics metrics = canvas.getFontMetrics(font);
    int strHeight = metrics.getHeight();
    int drawX = Constants.windowWidth / 2;
    int drawY = Constants.windowHeight - 300;  // Bad hardcoded constants. BLEH
    int strWidth = metrics.stringWidth(message) / 2;

    // Set XM, YM, Width, Height (Lazy here... Derp)
    xmin = drawX - strWidth - 20;
    ymin = drawY - strHeight;
    width = strWidth * 2 + 40;
    height = strHeight + 20;

    // Mouse hovering?
    Color ctext;
    Color cback;
    if (MouseIsHovering()) {
      ctext = new Color(0, 255, 0);
      cback = new Color(255, 0, 0, 120);
    } else {
      ctext = new Color(0, 0, 120);
      cback = new Color(0, 255, 0, 120);
    }
    // Draw the things
    canvas.setColor(cback);
    canvas.fillRect(xmin, ymin, width, height);
    canvas.setFont(font);
    canvas.setColor(ctext);
    canvas.drawString(message, drawX - strWidth, drawY);
  }

  // Button Variables
  private Font font;
  private int xmin;
  private int ymin;
  private int width;
  private int height;
}
