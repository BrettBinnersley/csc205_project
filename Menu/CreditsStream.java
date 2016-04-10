/* Particle System Snow.
Brett Binnersley, V00776751

Creates and streams the credits (bottom to top)
*/

import java.util.ArrayList;

class CreditsStream extends ParticleSystem {
  public CreditsStream() {
    super(0, 0, -10);  // Above everything else.
    time = 10;
    credits = new ArrayList<Credit>() {{
      add(new Credit("Credits", 75, 40));
      add(new Credit("Brett Binnnersley", 50, 30));
      add(new Credit("Programming, Graphics", 50, 50));
      add(new Credit("Special Thanks", 75, 30));
      add(new Credit("Bill Bird - Instructor", 50, 120));
    }};
  }

  public void ParticleSystemStep() {
    int setx = (int)(Math.random() * Constants.windowWidth);
    int sety = (int)(Math.random() * Constants.windowHeight);
    time = time - 1;
    if (time < 0) {
      Credit c = credits.get(creditIndex);
      AddParticle(new Particle_Credit(c.text, c.size));
      time = c.delay;
      // Spawn next credit soon
      creditIndex++;
      if (creditIndex >= credits.size()) {
        creditIndex = 0;
      }
    }
  }

  private ArrayList<Credit> credits;
  private int creditIndex;
  private int time;
}

class Credit {
  public Credit(String s_text, int s_size, int s_delay) {
    text = s_text;
    size = s_size;
    delay = s_delay;
  }
  public String text;
  public int size;
  public int delay;
}
