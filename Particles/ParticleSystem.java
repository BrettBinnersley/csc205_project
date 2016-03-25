/* Particle System.
Brett Binnersley, V00776751

Interface for any particle system. Any particle
system that can exist in the scene should extend
this object.
*/

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


class ParticleSystem extends GameObject {
  public ParticleSystem(int x, int y, int s_depth) {
    super(x, y);
    depth = s_depth;
    SetType(OBJECTTYPE.PARTICLESYSTEM);
    FlagNoClipDraw();
    particles = new HashMap<Integer, Particle>();
    particleID = 0;
    destroySelfWhenDone = false;
  }

  public void ParticleSystemStep() { }  // Override this

  public void AddParticle(Particle p) {
    particles.put(particleID, p);
    p.id = particleID;
    ++particleID;
  }

  public void DestroyWhenEmpty() {
    destroySelfWhenDone = true;
  }

  @Override
  public void LogicStep() {
    ArrayList<Integer> removeParticles = new ArrayList<Integer>();
    Collection<Particle> parts = particles.values();
    for (Particle particle : parts) {
      particle.Step();
      particle.life -= 1;
      if (particle.alpha < 0.0f) {
        particle.alpha = 0.0f;
      }
      if (particle.life <=0 ) {
        removeParticles.add(particle.id);
      }
    }
    for (Integer key : removeParticles) {
      particles.remove(key);
    }
    if (destroySelfWhenDone == true && particles.isEmpty()) {
      Destroy();
    } else {
      ParticleSystemStep();
    }
  }

  @Override
  public void Render(Graphics2D canvas) {
    Collection<Particle> parts = particles.values();
    AffineTransform trans = canvas.getTransform();  // Get current transform state.
    for (Particle particle : parts) {
      particle.Render(canvas);
      canvas.setTransform(trans);  // Reset transformation state.
    }
  }

  public HashMap<Integer, Particle> particles;
  private int particleID;
  public boolean destroySelfWhenDone;
}
