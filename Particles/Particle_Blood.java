/* Particle
Brett Binnersley, V00776751

Dummy Template for any particle
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class Particle_Blood extends Particle {
  public Particle_Blood(int s_x, int s_y) {
    super(s_x, s_y, 120, "person", 40, 40);
    rotation = Math.random() * 2.0 * 3.141;  // 2PI radians in circle
    speed = 1.0 + Math.random();
  }

  @Override
  public void Step() {
    x += Math.cos(rotation) * speed;
    y += Math.sin(rotation) * speed;
  };

}
