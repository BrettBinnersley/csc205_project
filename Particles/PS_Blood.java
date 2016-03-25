/* Particle System Blood.
Brett Binnersley, V00776751

Particle System representing a blood splatter.
Created at the center of a destroyed enemy
*/

class PS_Blood extends ParticleSystem {
  public PS_Blood(double x, double y) {
    super((int)x, (int)y, 10);
    int intx = (int)x;
    int inty = (int)y;

    for (int i=0; i<50; ++i) {
      AddParticle(new Particle_Blood(intx, inty));
    }
  }

  public void ParticleSystemStep() {
    if (particles.isEmpty()) {
      Destroy(); // Only keep the PS alive as long as there is blood on the screen.
    }
  }
}
