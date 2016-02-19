/* Constants
Brett Binnersley, V00776751

Hardcoded constants - quick and dirty solution to constants.
*/


class Constants {
  public static int windowWidth = 1024;
  public static int windowHeight = 768;
  public static int sceneWidth = 1024;  // Should be equal to window size FOR NOW. TODO: Add views
  public static int sceneHeight = 768;  // Should be equal to window size FOR NOW. TODO: Add views
  public static int targetFPS = 60;  // Target FPS (Frames / Second). Setting this saves the CPU from running @ 100%.
                                     // NOTE: This is not perfect (due to reliance on a sleep function), which itself
                                     // is inheritely bad and unreliable*.
}
