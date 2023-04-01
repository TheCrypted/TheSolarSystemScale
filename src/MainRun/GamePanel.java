package MainRun;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import Planets.BackgroundStars;
import Planets.PlanetMove;
import Planets.PlanetObj;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public final static int SCREEN_WIDTH = 1280;
    public final static int SCREEN_HEIGHT = 800;
    private Game game;
    private BackgroundStars backgroundStars;
    private PlanetObj earth;
    private PlanetObj sun;
    private PlanetObj mars;
    private PlanetObj venus;
    private PlanetObj mercury;
    public static PlanetObj[] planets;


    public GamePanel(Game game){
        this.game = game;
        MouseInputs mouseInputs = new MouseInputs(this);
        KeyboardInputs keyboardInputs = new KeyboardInputs(this);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addMouseListener(mouseInputs);
        this.addMouseMotionListener(mouseInputs);
        this.addKeyListener(keyboardInputs);
        initClasses();
    }

    public void initClasses(){
        this.backgroundStars = new BackgroundStars();
        this.earth = new PlanetObj(5.9742 * Math.pow(10, 24) ,50, -1*PlanetObj.AU, 0, false, 0);
        earth.velY = 29.783 * 1000;
        this.mars = new PlanetObj(6.39 * Math.pow(10, 23) ,40, -1.524 * PlanetObj.AU, 0, false, 1);
        mars.velY = 24.077 * 1000;
        this.mercury = new PlanetObj(3.3 * Math.pow(10, 24) ,30, 0.387 * PlanetObj.AU, 0, false, 2);
        mercury.velY = -47.4 * 1000;
        this.venus = new PlanetObj(4.8685 * Math.pow(10, 24) ,50, 0.723*PlanetObj.AU, 0, false, 3);
        venus.velY = -35.02 * 1000;
        this.sun = new PlanetObj(1.98892 * Math.pow(10, 30) ,75, (int)(GamePanel.SCREEN_WIDTH/2)-60, (int)(GamePanel.SCREEN_HEIGHT/2)-60, true, 4);
        planets = new PlanetObj[]{this.sun, this.mercury, this.venus, this.earth, this.mars};
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
//        repaint();
    }

    public PlanetObj[] getPlanets() {
        return planets;
    }

    public void update(){
        PlanetMove.updatePlanetPos(earth);
        PlanetMove.updatePlanets(planets);
    }


    public void draw(Graphics g) {
        BackgroundStars.draw(g);
        earth.render(g);

//        sun.render(g);
    }
}
