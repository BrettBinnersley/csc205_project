/* Tree
Brett Binnersley, V00776751

Defines a Tree
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;

class Tree extends GameObject {
  public Tree(int s_x, int s_y) {
    super(s_x, s_y);
    SetSolid(25, 25);
    SetImage("treetrunk", 32, 32);
    SetType(OBJECTTYPE.TREE);
    depth = -5;
    rotation = Math.random() * 3.1415 * 2.0;  // Between 0 and 2 PI
    treeLeafBot = Resources.GetImage("treeleaf_bot");
  }

  // Render a player
  @Override
  public void Render(Graphics2D canvas) {
    float dX = (float)((Constants.windowWidth / 2) - drawX) / 300.0f;
    float dY = (float)((Constants.windowHeight / 2) - drawY) / 300.0f;
    float alpha = (float)Math.sqrt(dX * dX + dY * dY);
    if (alpha < 0.1f) {
      alpha = 0.1f;
    }
    if (alpha > 1.0f) {
      alpha = 1.0f;
    }

    AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    canvas.rotate(rotation, drawX, drawY);
    canvas.drawImage(image, drawX - origin_x, drawY - origin_y, null);
    canvas.setComposite(alcom);
    canvas.drawImage(treeLeafBot, drawX - 256, drawY - 256, null);
  }

  private BufferedImage treeLeafBot = null;
  private BufferedImage treeLeafMid = null;
  private BufferedImage treeLeafTop = null;
}
