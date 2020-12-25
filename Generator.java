package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.Random;

public class Generator {
    int width;
    int height;
    long seed;
    int num;

    public Generator(long seed, int width, int height, int num) {
        this.width = width;
        this.height = height;
        this.seed = seed;
        this.num = num;
    }

    public TETile[][] generate() {
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        ArrayList<Makeroom> allrooms = addrooms(world);
        for (int i = 0; i < allrooms.size() - 1; i++) {
            addhallways(world, allrooms.get(i), allrooms.get(i + 1));
        }
        return world;
    }

    public ArrayList<Makeroom> addrooms(TETile[][] world) {
        ArrayList<Makeroom> rooms = new ArrayList<>();
        Random r = new Random(seed);
        for (int i = 0; i < num; i++) {
            int roomX = r.nextInt(5) + 6;
            int roomY = r.nextInt(4) + 6;
            int pointX = r.nextInt(width - roomX);
            int pointY = r.nextInt(height - roomY);

            do {
                roomX = r.nextInt(5) + 6;
                roomY = r.nextInt(4) + 6;
                pointX = r.nextInt(width - roomX);
                pointY = r.nextInt(height - roomY);
            } while (!testroom(roomX, roomY, pointX, pointY, world));

            Point p = new Point(pointX, pointY);
            Makeroom newroom = new Makeroom(roomX, roomY, p);
            newroom.buildnormalroom(world);
            rooms.add(newroom);
        }
        return rooms;
    }

    private Boolean testroom(int roomX, int roomY, int pointX, int pointY, TETile[][] world) {
        for (int m = 0; m < roomX; m++) {
            for (int n = 0; n < roomY; n++) {
                if (!world[pointX + m][pointY + n].description().equals("nothing")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addhallways(TETile[][] world, Makeroom r1, Makeroom r2) {
        Random r = new Random(seed);
        Point c1 = connectingpoint(r1);
        Point c2 = connectingpoint(r2);

        Point start;
        Point rest;
        if (c1.getX() < c2.getX()) {
            start = new Point(c1.getX(), c1.getY() - 1);
            rest = c2;
        } else {
            start = new Point(c2.getX(), c2.getY() - 1);
            rest = c1;
        }
        Point turning = new Point(rest.getX(), start.getY());
        Makeroom.buildturning(world, turning);
        MakeHallway.buildhorizontal(world, turning.getX() - start.getX() + 1, start);
        Point start2;
        Point end;
        if (turning.getY() < rest.getY()) {
            start2 = new Point(turning.getX() - 1, turning.getY() + 1);
            end = new Point(rest.getX(), rest.getY());
        } else {
            start2 = new Point(rest.getX() - 1, rest.getY() + 1);
            end = new Point(turning.getX(), turning.getY());
        }
        MakeHallway.buildvertical(world, end.getY() - start2.getY() + 1, start2);
    }

    private Point connectingpoint(Makeroom room) {
        Random r = new Random(seed);
        int x = r.nextInt(room.x - 2) + room.lbcorner.getX() + 1;
        int y = r.nextInt(room.y - 2) + room.lbcorner.getY() + 1;
        Point connect = new Point(x, y);
        return connect;
    }
}
