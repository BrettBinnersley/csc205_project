/* Bullet
Brett Binnersley, V00776751

Defines a bullet shot by the player.
*/

import java.awt.Color;
import java.awt.Graphics2D;

class HUD extends GameObject {
  public HUD() {
    super(0, 0);
    SetType(OBJECTTYPE.HUD);
    FlagNoClipDraw();
    depth = -100;
    hudDamage = 0;
  }

  @Override
  public void LogicStep() {
    hudDamage -= 3;
  }

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    if (hudDamage < 0) {
      hudDamage = 0;
    }
    if (hudDamage > 100) {
      hudDamage = 100;
    }
    if (hudDamage > 0) {
      canvas.setColor(new Color(160, 0, 0, hudDamage));
      canvas.fillRect(0, 0, Constants.windowWidth, Constants.windowHeight);
    }
  }

  public static void AddPlayerDamage(int dmg) {
    hudDamage += dmg;
  }

  private double speed;
  private static int hudDamage;  // Owner can not be killed by their own bullet
}
