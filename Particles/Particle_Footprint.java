/* Particle
Brett Binnersley, V00776751

Particle that follows players
*/

class Particle_FootPrint extends Particle {
  public Particle_FootPrint(int s_x, int s_y, double s_rotation) {
    super(s_x, s_y, 120, "particle_footprint", 16, 16);
    rotation = s_rotation;  // Drawing Rotation
    alpha = 0.7f;
  }

  @Override
  public void Step() {
    alpha -= 0.005f;
  };
}
