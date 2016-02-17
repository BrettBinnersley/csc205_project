
import java.awt.Image;
import javax.imageio.*;

// Handle importing resources in a single place.
class Resources {

  public static Initialize() {
    _resources = new HashMap<string, Image>();
  }

  // Lazily load resources.
  // Find a resource and return the handle to it. Ensures that only one copy of each
  // resource will ever exist at any given time. This is quick.
  public Image GetResource(name) {
    Image img = _resources.get(name);
    if (img == null) {
      img =
      File pathToFile = new File("resources/" + name);
      Image image = ImageIO.read(pathToFile);
    } else {
      return img;
    }
  }

  private static HashMap<string, Image> _resources;
}
