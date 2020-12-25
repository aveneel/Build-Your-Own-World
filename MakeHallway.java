package byow.Core;

import byow.TileEngine.Tileset;
import byow.TileEngine.TETile;

public class MakeHallway {
    public static void buildvertical(TETile[][] world, int y, Point lbcorner) {
        for (int i = 0; i < y; i++) {
            for (int n = 0; n < 3; n++) {
                if (world[lbcorner.getX() + n][lbcorner.getY() + i].description().
                        equals("nothing")) {
                    world[lbcorner.getX()][lbcorner.getY() + i] = Tileset.WALL;
                    world[lbcorner.getX() + 2][lbcorner.getY() + i] = Tileset.WALL;
                }
            }
            world[lbcorner.getX() + 1][lbcorner.getY() + i] = Tileset.FLOOR;
        }
    }

    public static void buildhorizontal(TETile[][] world, int x, Point lbcorner) {
        for (int i = 0; i < x; i++) {
            for (int n = 0; n < 3; n++) {
                if (world[lbcorner.getX() + i][lbcorner.getY() + n].description().
                        equals("nothing")) {
                    world[lbcorner.getX() + i][lbcorner.getY()] = Tileset.WALL;
                    world[lbcorner.getX() + i][lbcorner.getY() + 2] = Tileset.WALL;
                }
            }
            world[lbcorner.getX() + i][lbcorner.getY() + 1] = Tileset.FLOOR;
        }
    }
}
