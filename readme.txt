Csc205 (Introduction to 2D Graphics) Project
Brett Binnersley, V00776751, Uvic
License: MIT (see license)

Simple top down shooter.
Continuation of my assignment #2. This can be found at:
https://github.com/BrettBinnersley/csc205_a2


Description:
A simple top-down shooter where the player tries to
get as high of a score as possible by shooting enemies.

To compile & run (on OSX / Linux):
./run

Or on a different OS:
javac *.java Scene/*.java GameObjects/*.java Particles/*.java Menu/*.java -d bin
cd bin
java Start

Features Implemented:
- Moving ViewPort
  - Follows the player (or any object that is assigned to it)
  - Optimizations to NOT call render event on any object outside of the viewport
  - HUD that does not follow viewport (for displaying health & score)
- Window resizing works in any scene
  - Viewport gets adjusted accordingly, which will modify all the objects draw positions.
- Several Scenes with smooth transitions between them
  - Gameloop stops processing all logic events while the scene is transitioning (IE: Pauses), while the render logic still goes on.
- Menu with interactive buttons (hover to change color)
- Scrolling Credits Text (click credits from the menu)
  - Fade out over time
- Particle Systems for handling large amounts of objects that don't have any logic (not to be handlded by the gameloop)
  - Snow
  - Blood
  - Footprints
- Dynamic collision system
  - Able to detect differences between instances of the same object (enemies)
    - Bullets from enemies and users are all the same (instance).
- Working depth system (handled by the game engine automatically).
  - Order that objects get processed and rendered depend on their depth, not the order that they were created.
- Event logic and graphic logic separated so that if the graphics lag, the events will still get processed & handled leaving a smoother feeling.
- Trees turn transparent when walked under
- Enemies spawn randomly in scene
  - Allows for objects to be dynamically added and removed at any given time easily.
- Healthbar and Score (that dynamically change)
- Automatic resource loading & allocation (images) so that an image will never be loaded more than once for multiple objects (same one will be re-used)
- SceneManager handles smooth transitions
- Rotation matrices (rotates trees, enemies, etc.)
- Solid objects that can't be shot through.




On Github:
https://github.com/BrettBinnersley/csc205_project
