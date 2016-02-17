Csc205 (Introduction to 2D Graphics) Assignment #2
Brett Binnersley, V00776751, Uvic


This is a very simple top down shooter game.

To run:
javac *.java -d bin
cd bin
java Start

Or, on Mac / Linux
./run



On Github:
https://github.com/BrettBinnersley/csc205_a2


Features added:
Dynamically create & destroy objects (using polymorphism)
Framerate limiter (set to 60 in the constants)
Thread-safe event system. (Using concurrent data structures)
Dynamically (automatically) load all the resources (images) from the /resource folder.
Rotations!
Depths for the objects (render order)
