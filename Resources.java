/* Resource
Brett Binnersley, V00776751

Resource handler for all the resources. This will automatically
load all the resources (images) located in the resources/ folder
and make them readily available for use.
*/

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.String;
import java.util.HashMap;
import javax.imageio.*;

class Resources {

  // Load all the resources from the resources folder.
  public static void Initialize() throws Exception {
    _resources = new HashMap<String, BufferedImage>();
    File dir = new File("resources");
    File[] items = dir.listFiles();
    if (items != null) {
      for (File f : items) {
        BufferedImage imgData = null;
        try {
          imgData = ImageIO.read(f);
        } catch (Exception e) {
          System.out.println("Error loading image data");
        }
        _resources.put(withoutExtension(f.getName()), imgData);
      }
    } else {
      System.out.println("Resources directory not found. Failed to load any resources.");
      throw new Exception("Resources directory not found. Failed to load any resources.");
    }
  }

  private static String withoutExtension (String str) {
       int pos = str.lastIndexOf(".");
       if (pos == -1) {
         return str;
       }
       return str.substring(0, pos);
   }

  // Lazily load resources.
  // Find a resource and return the handle to it. Ensures that only one copy of each
  // resource will ever exist at any given time. This is quick.
  public static BufferedImage GetImage(String name) {
    BufferedImage img = _resources.get(name);
    if (img == null) {
      System.out.println("ERROR: Resource not found.");
    }
    return img;
  }

  private static HashMap<String, BufferedImage> _resources;
}
