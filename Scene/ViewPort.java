/* ViewPort
Brett Binnersley, V00776751

Handles a scrolling View
*/

class ViewPort {

  public static int GetX(int x) {
    return x - _x;
  }
  public static int GetY(int y) {
    return y - _y;
  }

  public static void SetFollowObject(GameObject s_followObject) {
    followObject = s_followObject;
    _x = 0;
    _y = 0;
  }

  public static void UpdateView() {
    if (followObject != null) {
      _x = (int)followObject.x - (Constants.windowWidth / 2);
      _y = (int)followObject.y - (Constants.windowHeight / 2);
    }
  }
  private static int _x = 0;
  private static int _y = 0;
  private static GameObject followObject = null;  // Object that this follow.
}
