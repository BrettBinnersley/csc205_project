/* GameLoop
Brett Binnersley, V00776751

Generates and returns unique ID's as integers.
*/

class ID {
  private static int _id = 0;
  public static int GenID() {
    int id = _id;
    ++_id;
    return id;
  }
}
