/* Scene
Brett Binnersley, V00776751

Contains all the objects in the scene at any given time,
and maintains their states. Objects can be added to the
Scene with Scene.AddObject(...). Objects will automatically
be removed and handled when their Destroy() method is invoked.
*/


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

class Scene {
  public static void Initialize() {
    if (objects == null) {
      ClearSceneObjects();
    } else {
      System.out.println("Warning: Scene has already been initialized.");
    }
    width = 0;
    height = 0;
  }

  // Kill all the objects from the scene.
  // This should only ever be called from the SceneManager
  public static void ClearSceneObjects() {
    objects = new HashMap<Integer, GameObject>();
    objectsToBeAdded = new ArrayList<GameObject>();
    objectTypeCount = new HashMap<GameObject.OBJECTTYPE, Integer>();
    for (GameObject.OBJECTTYPE val : GameObject.OBJECTTYPE.values()) {
      objectTypeCount.put(val, 0);
    }
  }

  public static void SetSize(double s_width, double s_height) {
    width = (int)s_width;
    height = (int)s_height;
  }

  public static int Width() {
    return width;
  }

  public static int Height() {
    return height;
  }

  public static void AddObject(GameObject obj) {
    objectsToBeAdded.add(obj);
  }

  public static void AddObjects(ArrayList<GameObject> objs) {
    for (GameObject obj : objs) {
      objectsToBeAdded.add(obj);
    }
  }

  public static void AddQueuedObjectsToScene() {
    for (GameObject o : objectsToBeAdded) {
      objectTypeCount.put(o.type, objectTypeCount.get(o.type) + 1);
      objects.put(o.id, o);
    }
    objectsToBeAdded.clear();
  }

  public static void RemoveFlaggedObjects() {
		ArrayList<Integer> deleteObjects = new ArrayList<Integer>();
    Collection<GameObject> unorderedObjs = GetUnorderedObjects();
		for (GameObject obj : unorderedObjs) {
			if (obj.IsFlaggedDeleted()) {
				deleteObjects.add(obj.id);
        objectTypeCount.put(obj.type, objectTypeCount.get(obj.type) - 1);
				obj.OnDestroyed();
			}
		}
		for (Integer key : deleteObjects) {
			objects.remove(key);
		}
		deleteObjects.clear();
  }

  public static Collection<GameObject> GetUnorderedObjects() {
    return objects.values();
  }

  public static ArrayList<GameObject> GetSortedObjects() {
    ArrayList<GameObject> sorted = new ArrayList<GameObject>(objects.values());
    Collections.sort(sorted);
    return sorted;
  }

  public static int GetNumberObjectType(GameObject.OBJECTTYPE objType) {
    return objectTypeCount.get(objType);
  }

  private static HashMap<Integer, GameObject> objects;
  private static HashMap<GameObject.OBJECTTYPE, Integer> objectTypeCount;
  private static ArrayList<GameObject> objectsToBeAdded;
  private static int width;
  private static int height;

}
