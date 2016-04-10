/* Particle_Credit
Brett Binnersley, V00776751

Particle for the credits
*/

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

class Particle_Credit extends Particle {
  public Particle_Credit(String text, int size) {
    super(0, 0, 2000, null, 0, 0);
    speed = 1.5f;
    trueAlpha = 1.5f;
    font = new Font("Serif", Font.PLAIN, size);
    message = text;
  }

  @Override
  public void Step() {
    y += speed;
    trueAlpha -= 0.004f;
    alpha = trueAlpha;

    if (trueAlpha < 0.01f) {
      trueAlpha = 0.01f;
      Destroy();
    }
    if (alpha > 1.0f) {
      alpha = 1.0f;
    }
  };


  public void Render(Graphics2D canvas) {
    FontMetrics metrics = canvas.getFontMetrics(font);
    int strHeight = metrics.getHeight();
    int drawX = Constants.windowWidth / 2;
    int drawY = Constants.windowHeight - 300 - (int)y;  // Bad hardcoded constants. BLEH
    int strWidth = metrics.stringWidth(message) / 2;

    Color ctext = new Color(0, 0, 0);
    AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    canvas.setComposite(alcom);
    canvas.setFont(font);
    canvas.setColor(ctext);
    canvas.drawString(message, drawX - strWidth, drawY);
  };


  private Font font;
  private String message;
  private float trueAlpha;
}
