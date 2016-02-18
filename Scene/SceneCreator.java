/* Scene Creator
Brett Binnersley, V00776751

Creates and returns scenes (sets of objects).
*/

import java.util.ArrayList;

class SceneCreator {
  public static ArrayList<GameObject> CreateSimpleScene() {
    double scene_width = (double)Scene.Width();
    double scene_height = (double)Scene.Height();
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new Background((int)scene_width, (int)scene_height));
    objects.add(new Player(200, 200));
    for (int i=0; i<10; ++i) {
      objects.add(new Enemy((int)(Math.random() * scene_width), (int)(Math.random() * scene_height)));
    }
    return objects;
  }
}
