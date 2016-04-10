/* Background
Brett Binnersley, V00776751

Renders the background of the scene (Grass)
*/

import java.awt.Graphics2D;

class MenuBackground extends GameObject {

  public MenuBackground(boolean s_spawnEnemies) {
    super(0, 0);
    SetImage("menuBackground", 0, 0);
    SetType(OBJECTTYPE.BACKGROUND);
    depth = 1000;
    spawnEnemies = s_spawnEnemies;
  }

  // Render the background
  @Override
  public void Render(Graphics2D canvas) {
    scene_width = Constants.windowWidth;
    scene_height = Constants.windowHeight;
    // Randomly spawn enemys on the menu
    if (spawnEnemies) {
      if (Math.random() < 0.03) {
        Enemy e = new Enemy(1 + (int)(Math.random() * scene_width), 1 + (int)(Math.random() * scene_height));
        e.depth = 1;
        Scene.AddObject(e);
      }
    }

    for (int y=0; y<scene_height; y+=256) {
      for (int x=0; x<scene_width; x+=256) {
        canvas.drawImage(image, x, y, null);
      }
    }
  }

  private int scene_width;
  private int scene_height;
  private boolean spawnEnemies;
}
