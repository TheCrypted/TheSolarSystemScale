package Planets;

import MainRun.GamePanel;

import java.awt.*;
import java.util.Random;

public class BackgroundStars {
    private static final int numStars = 200;
    private static Random random;
    private static int[][] pos;
    private static int cycle = 0;
    public BackgroundStars(){
        random = new Random();
        pos = new int[numStars][2];
        for(int i=0; i<numStars; i++){
            if(pos[i][0] == 0 && pos[i][1] == 0) {
                int[] coord = {random.nextInt(GamePanel.SCREEN_WIDTH), random.nextInt(GamePanel.SCREEN_HEIGHT)};
                pos[i] = coord;
            }
        }
    }
    public static void draw(Graphics g){
        cycle++;
        g.setColor(Color.GRAY);
        for(int i=0; i < numStars; i++) {
//                g.setColor(new Color(200 - random.nextInt(70), 200 - random.nextInt(70), 200 - random.nextInt(70), random.nextInt(200)));
            g.fillOval(pos[i][0], pos[i][1], 4, 4);
        }
    }
}
