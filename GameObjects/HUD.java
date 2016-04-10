/* Bullet
Brett Binnersley, V00776751

Defines a bullet shot by the player.
*/

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;

class HUD extends GameObject {
  public HUD() {
    super(0, 0);
    SetType(OBJECTTYPE.HUD);
    FlagNoClipDraw();
    depth = -1000;
    hudDamage = 0;
    font = new Font("Serif", Font.PLAIN, 25);
  }

  @Override
  public void LogicStep() {
    hudDamage -= 2;
  }

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    // Draw Damage over the HUD
    if (hudDamage < 0) {
      hudDamage = 0;
    }
    if (hudDamage > 120) {
      hudDamage = 120;
    }
    if (hudDamage > 0) {
      canvas.setColor(new Color(160, 0, 0, hudDamage));
      canvas.fillRect(0, 0, Constants.windowWidth, Constants.windowHeight);
    }

    // Draw healthbar
    canvas.setColor(new Color(255, 255, 255));
    canvas.fillRect(10, 10, 200, 32);
    canvas.setColor(new Color(0, 255, 0));
    canvas.fillRect(12, 12, (int)(196 * (double)Globals.playerHealth / (double)Globals.maxPlayerHealth), 28);
    canvas.setColor(new Color(0, 0, 0));
    canvas.setFont(font);
    canvas.drawString("Kills: " + Globals.kills, 20, 34);
  }

  public static void AddPlayerDamage(int dmg) {
    hudDamage += dmg;
  }


  private Font font;
  private double speed;
  private static int hudDamage;  // Owner can not be killed by their own bullet
}
