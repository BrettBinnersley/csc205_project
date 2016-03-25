/* Particle System Snow.
Brett Binnersley, V00776751

Particle System that contains all the snow particles.
*/

class MenuPS extends ParticleSystem {
  public MenuPS() {
    super(0, 0, 100);  // Under everything, other than the background
  }

  public void ParticleSystemStep() {
    int setx = (int)(Math.random() * Constants.windowWidth);
    int sety = (int)(Math.random() * Constants.windowHeight);
    AddParticle(new Particle_Snow(setx, sety));
  }
}
