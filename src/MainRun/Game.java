package MainRun;

import Planets.PlanetObj;

public class Game implements Runnable {
    private GamePanel gamePanel;
    private GameFrame gameFrame;
    private PlanetObj earth;
    private Thread thread;
    private final int FPS = 120;
    private final int UPS = 200;
    Game(){
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        startGame();
    }
    public void startGame(){
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        double frametime = 1000000000.0/FPS;
        double updateTime = 1000000000.0/UPS;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastTime = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        while(true){
            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime)/frametime;
            deltaU += (currentTime - previousTime)/updateTime;
            previousTime = currentTime;
            if(deltaU >= 1){
                gamePanel.update();
                updates++;
                deltaU--;
            if(deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            if(System.currentTimeMillis() - lastTime >= 1000){
                lastTime = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " || UPS: " +updates);
                frames = 0;
                updates = 0;
            }
            }
            }
        }


    }
}
