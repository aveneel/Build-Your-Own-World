package byow.Core;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

public class Movement {
    public static void move(Avatar avatar, char input) {
        if (input == 'w' || input == 'W') {
            makemovement("up", avatar);
        } else if (input == 's' || input == 'S') {
            makemovement("down", avatar);
        } else if (input == 'a' || input == 'A') {
            makemovement("left", avatar);
        } else if (input == 'd' || input == 'D') {
            makemovement("right", avatar);
        }

    }

    private static void makemovement(String direction, Avatar avatar) {
        if (direction.equals("left")) {
            if (avatar.world[avatar.posX - 1][avatar.posY].description().equals("floor")
                    ||avatar.world[avatar.posX - 1][avatar.posY].description().equals("you")) {
                Tileset.FLOOR.draw(avatar.posX, avatar.posY);
                avatar.posX = avatar.posX - 1;
                Tileset.AVATAR.draw(avatar.posX, avatar.posY);
                StdDraw.show();
            }
        } else if (direction.equals("right")) {
            if (avatar.world[avatar.posX + 1][avatar.posY].description().equals("floor")
                    ||avatar.world[avatar.posX + 1][avatar.posY].description().equals("you")) {
                Tileset.FLOOR.draw(avatar.posX, avatar.posY);
                avatar.posX = avatar.posX + 1;
                Tileset.AVATAR.draw(avatar.posX, avatar.posY);
                StdDraw.show();
            }
        } else if (direction.equals("up")) {
            if (avatar.world[avatar.posX][avatar.posY + 1].description().equals("floor")
            ||avatar.world[avatar.posX][avatar.posY + 1].description().equals("you")) {
                Tileset.FLOOR.draw(avatar.posX, avatar.posY);
                avatar.posY = avatar.posY + 1;
                Tileset.AVATAR.draw(avatar.posX, avatar.posY);
                StdDraw.show();
            }
        } else if (direction.equals("down")) {
            if (avatar.world[avatar.posX][avatar.posY - 1].description().equals("floor")
                    ||avatar.world[avatar.posX][avatar.posY - 1].description().equals("you")) {
                Tileset.FLOOR.draw(avatar.posX, avatar.posY);
                avatar.posY = avatar.posY - 1;
                Tileset.AVATAR.draw(avatar.posX, avatar.posY);
                StdDraw.show();
            }
        }
    }
}
