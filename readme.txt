Csc205 (Introduction to 2D Graphics) Assignment #2
Brett Binnersley, V00776751, Uvic


This is a very simple top down shooter game.

To run:
javac *.java Scene/*.java GameObjects/*.java Particles/*.java -d bin
cd bin
java Start

Or, on Mac / Linux
./run


On Github:
https://github.com/BrettBinnersley/csc205_a2


Features added:
Scene for managing all objects and their interactions.
Dynamically create & destroy objects in the scene at any time.
Static & Dynamic Objects.
Dynamic & Robust Collision System.
Depths for the objects (render order).
Particle Systems for optimized render speed.
Transparent rendering of images.
Rotations.

Framerate limiter (set to 60 in the constants)
Thread-safe event system. (Using concurrent data structures)
Dynamically (automatically) load all the resources (images) from the /resource folder.
Graceful error handling and program exiting.
