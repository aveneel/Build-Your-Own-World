package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private long seed;
    private String userinput = " ";
    private Avatar avatar;
    private TETile[][] world;
    private String name= "";

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        mainmenu();
        char input;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                input = StdDraw.nextKeyTyped();
                userinput += input;
                System.out.println(userinput);
                if (input == 'N' || input == 'n') {
                    newGame();
                } else if (input == 'L' || input == 'l') {
                    loadGame();
                    char[] chars1 = userinput.toCharArray();
                    userinput = "";
                    for (int n = 0; n < chars1.length - 2; n++) {
                        if (chars1[n] != 'q'|| chars1[n] != 'Q') {
                            userinput += chars1[n];
                        }
                    }
                } else if (input == 'Q' || input == 'q' && userinput.charAt(userinput.length()-2) == ':') {
                    saveGame();
                    System.exit(0);
                } else if (input == 'w' || input == 'a' || input == 's' || input == 'd'
                        || input == 'W' || input == 'A' || input == 'S' || input == 'D') {
                    Movement.move(avatar, input);
                    UI.name(name,WIDTH,HEIGHT);
                    UI.hud(world, WIDTH, HEIGHT);
                } else if (userinput.equals("Q") || userinput.equals("q")){
                    System.exit(0);
                }

            }
        }
    }

    private void newGame() {
        StdDraw.clear(Color.BLACK);
        StdDraw.text(250, 300, "Please enter the seed.");
        String seedstr = "";
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char input = StdDraw.nextKeyTyped();
                if (input == 's' || input == 'S') {
                    break;
                }
                userinput += input;
                seedstr += input;
                StdDraw.clear(Color.BLACK);
                StdDraw.text(250, 250, "Your seed:  " + seedstr);
                seed = Long.parseLong(seedstr);
                StdDraw.show();
            }
        }
        StdDraw.clear(Color.BLACK);
        StdDraw.text(250, 300, "Please enter the name of avatar.");
        while(true){
            if (StdDraw.hasNextKeyTyped()) {
                char input = StdDraw.nextKeyTyped();
                name += input;
                if (input == 's' || input == 'S') {
                    break;
                }
                StdDraw.clear(Color.BLACK);
                StdDraw.text(250, 250, "Your name:  " + name);
            }
        }
        ter.initialize(WIDTH, HEIGHT);
        Generator g = new Generator(seed, WIDTH, HEIGHT, 15);
        world = g.generate();
        avatar = new Avatar(world, seed, WIDTH, HEIGHT);
        ter.renderFrame(world);
    }

    private void saveGame() {
        File file = new File("userinput.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("userinput.txt", false));
            writer.append(userinput);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGame() {
        try {
            ter.initialize(WIDTH, HEIGHT);
            File userfile = new File("userinput.txt");
            Scanner file = new Scanner(userfile);
            userinput = file.next();
            seed = findSeed(userinput);
            Generator g = new Generator(seed, WIDTH, HEIGHT, 15);
            world = g.generate();
            avatar = new Avatar(world, seed, WIDTH, HEIGHT);
            UI.hud(world,WIDTH,HEIGHT);
            ter.renderFrame(world);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void mainmenu() {
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(0, 500);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(250, 300, "CS61B: The Game");
        StdDraw.text(250, 250, "New Game (N)");
        StdDraw.text(250, 200, "Load Game (L)");
        StdDraw.text(250, 150, "Quit (Q)");
        StdDraw.show();
    }

    private long findSeed(String input) {
        String seedstr = "";
        int endpos = 0;
        char[] chars = input.toCharArray();
        for (int n = 0; n < chars.length; n++) {
            if (chars[n] == 's' || chars[n] == 'S') {
                endpos = n;
                break;
            }
        }
        for (int i = 1; i < endpos; i++) {
            seedstr += chars[i];
        }
        seed = Long.valueOf(seedstr.trim());
        return seed;
    }


    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, both of these calls:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        /**int maxnum = 10;
         char[] chars = input.toCharArray();
         String str = "";
         for (int n = 1; n < (chars.length - 1); n++) {
         str += chars[n];
         }
         long seed = Long.parseLong(str);
         Random r = new Random(seed);
         int num = r.nextInt(maxnum) + 5;
         Generator g = new Generator(seed, WIDTH, HEIGHT, num);
         TETile[][] world = g.generate();
         return world;*/
        char[] chars = input.toCharArray();
        if (chars[0] == 'L' || chars[0] == 'l') {
            loadGame();
            char[] chars1 = userinput.toCharArray();
            userinput = "";
            for (int n = 0; n < chars1.length - 2; n++) {
                if (chars1[n] != 'q'|| chars1[n] != 'Q') {
                    userinput += chars1[n];
                }
            }
            for (int q = 1; q < chars.length; q++) {
                userinput += chars[q];
            }
            System.out.println(userinput);
            chars = userinput.toCharArray();
            String seedstr = Long.toString(seed);
            for (int i = seedstr.length() + 2; i < chars.length; i++) {
                if (chars[i] == 'w' || chars[i] == 'a' || chars[i] == 's'
                        || chars[i] == 'd' || chars[i] == 'W' || chars[i] == 'A'
                        || chars[i] == 'S' || chars[i] == 'D') {
                    Movement.move(avatar, chars[i]);
                } else if (chars[i] == 'q' || chars[i] == 'Q'&& chars[i-1]== ':'){
                    saveGame();
                    break;
                }
            }
        } else if (chars[0] == 'N' || chars[0] == 'n') {
            userinput = input;
            seed = findSeed(input);
            ter.initialize(WIDTH, HEIGHT);
            Generator g = new Generator(seed, WIDTH, HEIGHT, 15);
            world = g.generate();
            avatar = new Avatar(world, seed, WIDTH, HEIGHT);
            //UI.HUD(world,WIDTH,HEIGHT);
            ter.renderFrame(world);
            String seedstr = Long.toString(seed);
            for (int i = seedstr.length() + 2; i < chars.length; i++) {
                if (chars[i] == 'w' || chars[i] == 'a' || chars[i] == 's'
                        || chars[i] == 'd' || chars[i] == 'W' || chars[i] == 'A'
                        || chars[i] == 'S' || chars[i] == 'D') {
                    Movement.move(avatar, chars[i]);
                } else if (chars[i] == 'q' || chars[i] == 'Q'&& chars[i-1]== ':'){
                    saveGame();
                    break;
                }
            }

        }
        return world;
    }

    /**public static void main(String[] args) {
     Engine engine = new Engine();
     while (StdDraw.hasNextKeyTyped()){
     input = StdDraw.nextKeyTyped();
     interactWithKeyboard();
     }
     System.out.println(engine.toString());
     }*/

}
