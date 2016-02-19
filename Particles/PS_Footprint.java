/* Particle System Footprint.
Brett Binnersley, V00776751

Particle System holding footprints that follow the player.
*/

class PS_Footprint extends ParticleSystem {
  public PS_Footprint() {
    super(0, 0, 99);
    stepSize = 8;
    steps = stepSize;
  }

  public void ParticleSystemStep() {
    if (Math.abs(x - x_prev) > 0.01 || Math.abs(y - y_prev) > 0.01) {
      steps -= 1;
      if (steps <= 0) {
        AddParticle(new Particle_FootPrint((int)x, (int)y, rotation));
        steps = stepSize;
      }
    }
  }

  private int steps;
  private int stepSize;
}
