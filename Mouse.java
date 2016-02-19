/* GameLoop
Brett Binnersley, V00776751

Handle the mouse position.
*/

class Mouse {
  private static int _x = 0;
  private static int _y = 0;
  public static int X() {
    return _x;
  }
  public static int Y() {
    return _y;
  }
  public static void SetMouseX(int sx) {
    _x = sx;
  }
  public static void SetMouseY(int sy) {
    _y = sy;
  }
}
