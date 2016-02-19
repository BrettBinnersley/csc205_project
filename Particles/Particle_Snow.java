/* Particle
Brett Binnersley, V00776751

Dummy Template for any particle
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class Particle_Snow extends Particle {
  public Particle_Snow(int s_x, int s_y) {
    super(s_x, s_y, 200 + (int)(Math.random() * 100.0), "particle_snow", 40, 40);
    rotation = 0.8 + Math.random() * 0.4;  // 2PI radians in circle
    speed = 0.4 + Math.random() * 0.4;
    alpha = (float)(0.4 + Math.random() * 0.3);
  }

  @Override
  public void Step() {
    x += Math.cos(rotation) * speed;
    y += Math.sin(rotation) * speed;
    alpha -= 0.004f;
    if (alpha < 0.01f) {
      alpha = 0.01f;
      Destroy();
    }
  };

}
