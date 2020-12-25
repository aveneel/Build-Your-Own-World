package byow.Core;

import byow.TileEngine.Tileset;
import byow.TileEngine.TETile;

public class Makeroom {
    int x;
    int y;
    Point lbcorner;

    public Makeroom(int x, int y, Point p) {
        this.x = x;
        this.y = y;
        this.lbcorner = p;
    }

    public void buildnormalroom(TETile[][] world) {
        for (int i = 0; i < x; i++) {
            world[lbcorner.getX() + i][lbcorner.getY()] = Tileset.WALL;
            world[lbcorner.getX() + i][lbcorner.getY() + y - 1] = Tileset.WALL;
        }
        for (int n = 0; n < y; n++) {
            world[lbcorner.getX()][lbcorner.getY() + n] = Tileset.WALL;
            world[lbcorner.getX() + x - 1][lbcorner.getY() + n] = Tileset.WALL;
        }
        for (int o = 1; o < x - 1; o++) {
            for (int p = 1; p < y - 1; p++) {
                world[lbcorner.getX() + o][lbcorner.getY() + p] = Tileset.FLOOR;
            }
        }
    }

    public static void buildturning(TETile[][] world, Point lbcorner) {
        for (int i = 0; i < 3; i++) {
            world[lbcorner.getX() + i][lbcorner.getY()] = Tileset.WALL;
            world[lbcorner.getX() + i][lbcorner.getY() + 2] = Tileset.WALL;
        }
        for (int n = 0; n < 3; n++) {
            world[lbcorner.getX()][lbcorner.getY() + n] = Tileset.WALL;
            world[lbcorner.getX() + 2][lbcorner.getY() + n] = Tileset.WALL;
        }
        for (int o = 1; o < 2; o++) {
            for (int p = 1; p < 2; p++) {
                world[lbcorner.getX() + o][lbcorner.getY() + p] = Tileset.FLOOR;
            }
        }
    }


}
