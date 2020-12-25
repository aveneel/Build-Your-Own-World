package byow.Core;

import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class UI {
    public static void hud(TETile[][] world, int width, int height) {
        int posX = (int) Math.round(StdDraw.mouseX());
        int posY = (int) Math.round(StdDraw.mouseY());
        if (posX >= 0 && posX <= width && posY >= 0 && posY <= width) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(6, height - 3, 6,2);
            String tile = world[posX][posY].description();
            tile = tile.toUpperCase();
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(3, height - 3, tile);
            StdDraw.show();
        }
    }

    public static void name(String name, int width, int height){
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.textLeft(3, height-6, "Avatar: "+name);
        StdDraw.show();
    }
}
