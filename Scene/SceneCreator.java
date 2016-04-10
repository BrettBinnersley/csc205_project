/* Scene Creator
Brett Binnersley, V00776751

Creates and returns scenes (sets of objects).
*/

import java.util.ArrayList;

class SceneCreator {
  // Menu
  public static ArrayList<GameObject> CreateMainMenu() {
    Globals.kills = 0;  // Bad place for this, but it is here :/
    Globals.playerHealth = Globals.maxPlayerHealth;
    double scene_width = Constants.windowWidth;
    double scene_height = Constants.windowHeight;
    Scene.SetSize(scene_width, scene_height);
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new MenuText());
    objects.add(new MenuControls());
    objects.add(new MenuCredits());
    objects.add(new MenuPlay());
    objects.add(new MenuPS());
    objects.add(new MenuBackground(true));
    for (int i=0; i<2; ++i) {
      objects.add(new Enemy(1 + (int)(Math.random() * scene_width), 1 + (int)(Math.random() * scene_height)));
    }
    return objects;
  }

  public static ArrayList<GameObject> CreateCreditsMenu() {
    double scene_width = Constants.windowWidth;
    double scene_height = Constants.windowHeight;
    Scene.SetSize(scene_width, scene_height);
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new CreditsStream());
    objects.add(new MenuBack());
    objects.add(new MenuBackground(false));
    return objects;
  }


  public static ArrayList<GameObject> CreateGameOver() {
    double scene_width = Constants.windowWidth;
    double scene_height = Constants.windowHeight;
    Scene.SetSize(scene_width, scene_height);
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new GameOverText());
    objects.add(new MenuBack());
    objects.add(new MenuBackground(false));
    return objects;
  }

  public static ArrayList<GameObject> CreateControlsMenu() {
    double scene_width = Constants.windowWidth;
    double scene_height = Constants.windowHeight;
    Scene.SetSize(scene_width, scene_height);
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new Player(200, 450));
    objects.add(new MenuControlsText());
    objects.add(new MenuBack());
    objects.add(new MenuBackground(false));
    return objects;
  }


  // First Room
  public static ArrayList<GameObject> CreateRoom1() {
    double scene_width = 2048;
    double scene_height = 2048;
    Scene.SetSize(scene_width, scene_height);
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new Background((int)scene_width, (int)scene_height));
    objects.add(new Sky());
    objects.add(new PS_Snow());
    objects.add(new HUD());
    objects.add(new Wall());

    // Follow the player in this scene.
    GameObject player = new Player(200, 200);
    objects.add(player);
    ViewPort.SetFollowObject(player);

    // Create the enemies
    double halfWidth = (double)(Scene.Width() / 2) - 30.0;
    for (int i=0; i<20; ++i) {
      if (Math.random() > 0.5) {  // Create on left or right side of the wall.
        objects.add(new Enemy(1 + (int)(Math.random() * halfWidth), 1 + (int)(Math.random() * scene_height)));
      } else {
        objects.add(new Enemy(Scene.Width() - 1 - (int)(Math.random() * halfWidth), 1 + (int)(Math.random() * scene_height)));
      }
    }

    for (int i=0; i<4; ++i) {
      objects.add(new Tree(1 + (int)(Math.random() * scene_width), 1 + (int)(Math.random() * scene_height)));
    }
    return objects;
  }

}
