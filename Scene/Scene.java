/* Scene Creator
Brett Binnersley, V00776751

Creates and returns scenes (sets of objects).
*/


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

class Scene {
  public static void Initialize() {
    if (objects == null) {
      objects = new HashMap<Integer, GameObject>();
      objectsToBeAdded = new ArrayList<GameObject>();
    } else {
      System.out.println("Warning: Scene has already been initialized.");
    }
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
      objects.put(o.id, o);
    }
  }

  public static void RemoveFlaggedObjects() {
		ArrayList<Integer> deleteObjects = new ArrayList<Integer>();
    Collection<GameObject> unorderedObjs = GetUnorderedObjects();
		for (GameObject obj : unorderedObjs) {
			if (obj.IsFlaggedDeleted()) {
				deleteObjects.add(obj.id);
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

  private static HashMap<Integer, GameObject> objects;
  private static ArrayList<GameObject> objectsToBeAdded;

}
