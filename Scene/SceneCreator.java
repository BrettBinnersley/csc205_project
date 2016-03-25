/* Scene Creator
Brett Binnersley, V00776751

Creates and returns scenes (sets of objects).
*/

import java.util.ArrayList;

class SceneCreator {
  // Menu
  public static ArrayList<GameObject> CreateMenu() {
    double scene_width = (double)(Scene.Width() - 2);
    double scene_height = (double)(Scene.Height() - 2);
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new MenuText());
    objects.add(new MenuPlay());
    objects.add(new MenuPS());
    objects.add(new MenuBackground());
    for (int i=0; i<2; ++i) {
      objects.add(new Enemy(1 + (int)(Math.random() * scene_width), 1 + (int)(Math.random() * scene_height)));
    }
    return objects;
  }

  // First Room
  public static ArrayList<GameObject> CreateRoom1() {
    double scene_width = (double)(Scene.Width() - 2);
    double scene_height = (double)(Scene.Height() - 2);
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new Background((int)scene_width, (int)scene_height));
    objects.add(new Sky((int)scene_width, (int)scene_height));
    objects.add(new PS_Snow());
    objects.add(new Wall());

    // Follow the player in this scene.
    GameObject player = new Player(200, 200);
    objects.add(player);
    ViewPort.SetFollowObject(player);

    // Create the enemies
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

  // Second Room
  public static ArrayList<GameObject> CreateRoom2() {
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
