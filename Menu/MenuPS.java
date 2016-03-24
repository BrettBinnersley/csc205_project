/* Particle System Snow.
Brett Binnersley, V00776751

Particle System that contains all the snow particles.
*/

class MenuPS extends ParticleSystem {
  public MenuPS() {
    super(0, 0, 100);  // Under everything, other than the background
    width = Scene.Width();
    height = Scene.Height();
  }

  public void ParticleSystemStep() {
    int setx = (int)(Math.random() * width);
    int sety = (int)(Math.random() * height);
    AddParticle(new Particle_Snow(setx, sety));
  }

  private int width;
  private int height;
}
