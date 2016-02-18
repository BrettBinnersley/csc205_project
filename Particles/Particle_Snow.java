/* Particle
Brett Binnersley, V00776751

Dummy Template for any particle
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class Particle_Snow extends Particle {
  public Particle_Snow(int s_x, int s_y) {
    super(s_x, s_y, 120, "person", 40, 40);
    rotation = Math.random() * 2.0 * 3.141;  // 2PI radians in circle
    speed = 0.2 + Math.random() * 0.2;
  }

  @Override
  public void Step() {
  };

}
