# Build-Your-Own-World
A world generator that creates an avatar to move around in a randomly designed map.

## Classes and Data Structures

**Generator:** Class to add rooms, hallways, and generate world

**MakeHallway:** Class to create vertical and horizontal hallways

**Makeroom:** Class to create rooms

**Point:** Class to give a position on the map

**Avatar:** Class to create the user's avatar

**Movement:** Class to incorporate the user's movement

**UI:** Class to present the UI, including the HUD

## Algorithms

**Generator:** 
This class has methods such as addrooms and addhallways to add rooms and hallways to the world randomly while utilizing the MakeHallway and Makeroom classes. The connectingpoint method makes sure everything is connected to each other in the final world.

## Persistence

The Engine class contains methods (saveGame, loadGame) to write the save data data to a text file and then gives the ability to load the game at a later time using this text file.
