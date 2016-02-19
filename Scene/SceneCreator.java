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
    objects.add(new Player(200, 200));
    for (int i=0; i<10; ++i) {
      objects.add(new Enemy(1 + (int)(Math.random() * scene_width), 1 + (int)(Math.random() * scene_height)));
    }
    return objects;
  }
}
