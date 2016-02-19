/* Particle
Brett Binnersley, V00776751

Template for what a bullet hits a wall
*/

class Particle_Spark extends Particle {
  public Particle_Spark(int s_x, int s_y) {
    super(s_x, s_y, 120 + (int)(Math.random() * 100.0), "particle_spark", 40, 40);
    rotation = Math.random() * 2.0 * 3.141;  // 2PI radians in circle
    speed = 0.5 + Math.random();
    alpha = 0.2f;
  }

  @Override
  public void Step() {
    x += Math.cos(rotation) * speed;
    y += Math.sin(rotation) * speed;
    speed -= 0.05;
    alpha -= 0.001;
    if (speed < 0) {
      speed = 0.0;
    }
  };

}
