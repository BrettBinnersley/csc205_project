/* Enemy
Brett Binnersley, V00776751

Creates and returns scenes (sets of objects).
*/

import java.util.ArrayList;

class SceneCreator {
  private SceneCreator() { }  // Empty constructor.

  public static ArrayList<GameObject> Create(int scene_width, int scene_height) {
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new Background(scene_width, scene_height));
    objects.add(new Player(200, 200));

    for (int i=0; i<10; ++i) {
      objects.add(new Enemy((int)(Math.random() * (double)scene_width), (int)(Math.random() * (double)scene_height)));
    }
    return objects;
  }

}
