/* Particle
Brett Binnersley, V00776751

Interface that every particle that can exist in any ParticleSystem.
Every particle should Extend this base class.
*/

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class Particle {
  public int id;  // Used by the particle systems. Treat as READ ONLY
  public int life;  // Used by the particle system. Treat as READ ONLY
  public double x;
  public double y;
  public double rotation;
  public double speed;
  public float alpha;
  public int origin_x;
  public int origin_y;
  public BufferedImage image;

  public Particle(int s_x, int s_y, int s_life, String s_img, int s_origin_x, int s_origin_y) {
    x = s_x;
    y = s_y;
    life = s_life;
    image = Resources.GetImage(s_img);
    origin_x = s_origin_x;
    origin_y = s_origin_y;
    alpha = 1.0f;
  }

  public void Step() { };   // Update particle properties

  public void Destroy() {
    life = 0;
  }

  public void Render(Graphics2D canvas) {
    AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    canvas.setComposite(alcom);
    canvas.rotate(rotation, x, y);
    canvas.drawImage(image, (int)x - origin_x, (int)y - origin_y, null);
  };
}
