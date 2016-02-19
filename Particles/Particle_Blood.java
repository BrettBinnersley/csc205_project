/* Particle
Brett Binnersley, V00776751

Particle for displaying blood of a dead enemy
*/

class Particle_Blood extends Particle {
  public Particle_Blood(int s_x, int s_y) {
    super(s_x, s_y, 120 + (int)(Math.random() * 100.0), "particle_blood", 4, 8);
    rotation = Math.random() * 2.0 * 3.141;  // Drawing Rotation
    move_rot = Math.random() * 2.0 * 3.141;  // Moving Direction
    speed = 0.5 + Math.random() * 6.0;
    alpha = 0.8f;
  }

  @Override
  public void Step() {
    x += Math.cos(move_rot) * speed;
    y += Math.sin(move_rot) * speed;
    speed *= 0.9;
    speed -= 0.05;
    alpha -= 0.001;
    if (speed < 0) {
      speed = 0.0;
    }
  };

  private double move_rot;

}
