package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class Avatar {
    int posX;
    int posY;
    final int width;
    final int height;
    long seed;
    TETile[][] world;

    public Avatar(TETile[][] world, long seed, int width, int height) {
        Random r = new Random(seed);
        int randomx = r.nextInt(80);
        int randomy = r.nextInt(30);
        while (!world[randomx][randomy].description().equals("floor")) {
            randomx = r.nextInt(80);
            randomy = r.nextInt(30);
        }
        this.posX = randomx;
        this.posY = randomy;
        this.width = width;
        this.height = height;
        this.world = world;
        world[posX][posY] = Tileset.AVATAR;
    }

    public Avatar(TETile[][] world, long seed, int width, int height, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        world[posX][posY] = Tileset.AVATAR;
    }


}
