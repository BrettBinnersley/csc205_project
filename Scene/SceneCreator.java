/* Scene Creator
Brett Binnersley, V00776751

Creates and returns scenes (sets of objects).
*/

import java.util.ArrayList;

class SceneCreator {
  public static ArrayList<GameObject> CreateSimpleScene() {
    double scene_width = (double)(Scene.Width() - 2);
    double scene_height = (double)(Scene.Height() - 2);
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new Background((int)scene_width, (int)scene_height));
    objects.add(new PS_Snow());
    objects.add(new Wall());
    objects.add(new Player(200, 200));
    double halfWidth = (double)(Scene.Width() / 2) - 30.0;
    for (int i=0; i<10; ++i) {
      if (Math.random() > 0.5) {  // Create on left or right side of the wall.
        objects.add(new Enemy(1 + (int)(Math.random() * halfWidth), 1 + (int)(Math.random() * scene_height)));
      } else {
        objects.add(new Enemy(Scene.Width() - 1 - (int)(Math.random() * halfWidth), 1 + (int)(Math.random() * scene_height)));
      }
    }
    return objects;
  }
}
