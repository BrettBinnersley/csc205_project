/* Particle System Snow.
Brett Binnersley, V00776751

Particle System that contains all the snow particles.
*/

class PS_Snow extends ParticleSystem {
  public PS_Snow() {
    super(0, 0, -100);
  }

  public void ParticleSystemStep() {
    int setx = (int)(Math.random() * Scene.Width());
    int sety = (int)(Math.random() * Scene.Height());
    AddParticle(new Particle_Snow(setx, sety));
  }
}
