package Planets;

import MainRun.GamePanel;
import Utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import javax.swing.*;

public class PlanetObj {
    public static double AU = 149.6e6 * 1000;
    public static double G = 6.67428e-11;
    public static double SCALE = 250 / AU;
    private BufferedImage image;
    private BufferedImage imageMars;
    private BufferedImage imageMercury;
    private BufferedImage imageVenus;
    private BufferedImage[] RotationSpritesVenus;
    private BufferedImage[] RotationSpritesMercury;
    private BufferedImage[] RotationSprites;
    private BufferedImage[] RotationSpritesMars;
    public double mass;
    public double radius;
    public double x;
    public double y;
    public double velX = 0;
    public double velY = 0;
    private boolean isSun;
    private int planetNum;
    private int aniTick = 0;
    private int aniIndex = 0;
    private final int aniSpeed = 15;
    public PlanetObj(double mass, double radius, double x, double y, boolean isSun, int planetNum){
        this.mass = mass;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.isSun = isSun;
        this.planetNum = planetNum;
        loadStuff();
    }
    private void resetAnimation(){
        aniIndex = 0;
        aniTick = 0;
    }

    private void loadStuff() {
        loadImage();
        loadRotationArray();
    }
    public void loadImage(){
        image = LoadSave.getPlayerAtlas(LoadSave.PLAYER_ATLAS);
        imageMars = LoadSave.getPlayerAtlas(LoadSave.MARS_ATLAS);
        imageMercury = LoadSave.getPlayerAtlas(LoadSave.MERCURY_ATLAS);
        imageVenus = LoadSave.getPlayerAtlas(LoadSave.VENUS_ATLAS);
    }
    public void loadRotationArray(){
        int k = 0;
        RotationSprites = new BufferedImage[12];
        RotationSpritesMars = new BufferedImage[18];
        RotationSpritesMercury = new BufferedImage[19];
        RotationSpritesVenus = new BufferedImage[17];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                RotationSprites[k+j] = image.getSubimage(j*116, i*116, 114, 114);
            }
            k+=4;
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
                RotationSpritesMars[i*6+j] = imageMars.getSubimage(j*270, i*270, 270, 270);
            }
        }

        for(int i = 0; i < 4; i++){
            if(i!= 3) {
                for (int j = 0; j < 5; j++) {
                    RotationSpritesMercury[i * 5 + j] = imageMercury.getSubimage(j * 72, i * 72, 72, 72);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    RotationSpritesMercury[i * 5 + j] = imageMercury.getSubimage(j * 72, i * 72, 72, 72);
                }
            }
        }

        for(int i = 0; i < 4; i++){
            if(i!= 3) {
                for (int j = 0; j < 5; j++) {
                    RotationSpritesVenus[i * 5 + j] = imageVenus.getSubimage(j * 344, i * 321, 344, 321);
                }
            } else {
                for (int j = 0; j < 2; j++) {
                    RotationSpritesVenus[i * 5 + j] = imageVenus.getSubimage(j * 344, i * 321, 344, 321);
                }
            }
        }

    }
    public void updateAnimation(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex ++;
            if(aniIndex >= RotationSprites.length){
//                aniIndex = 0;
                resetAnimation();
            }
        }
    }

    public void render(Graphics g){
        PlanetObj[] planets = GamePanel.planets;
        for(int i = 0; i < planets.length; i++) {
            if (!planets[i].isSun) {
                switch(planets[i].planetNum){
                    case 0:
                        ImageRotate(g, planets, i, RotationSprites);
                        break;
                    case 1:
                        ImageRotate(g, planets, i, RotationSpritesMars);
                        break;
                    case 2:
                        ImageRotate(g, planets, i, RotationSpritesMercury);
                        break;
                    case 3:
                        ImageRotate(g, planets, i, RotationSpritesVenus);
                        break;
                }
//                g.setColor(Color.BLUE);
//                g.drawImage( RotationSpritesMercury[aniIndex],(int) (GamePanel.SCREEN_WIDTH / 2) +(int)(planets[i].x*SCALE) - (int) (planets[i].radius / 2),  (int) (GamePanel.SCREEN_HEIGHT / 2) +(int)(planets[i].y*SCALE) - (int) (planets[i].radius / 2),
//                        (int)planets[i].radius, (int)planets[i].radius, null);
//                g.drawImage(RotationSprites[aniIndex], (int) (GamePanel.SCREEN_WIDTH / 2) + (int) (this.x * SCALE) - (int) (this.radius / 2), (int) (GamePanel.SCREEN_HEIGHT / 2) + (int) (this.y * SCALE) - (int) (this.radius / 2), (int) this.radius, (int) this.radius, null);
            } else {
                g.setColor(new Color(200, 200, 140));
                g.fillOval((int) planets[i].x, (int) planets[i].y, 100, 100);
                g.setColor(Color.YELLOW);
                g.fillOval((int) planets[i].x+10, (int) planets[i].y+10, 80, 80);
            }
        }
//        for(int i = 0; i < RotationSprites.length; i++){
//            g.drawImage(RotationSprites[i], x, y, null);
//        }
//        g.drawImage(image, x , y, null);
    }

    private void ImageRotate(Graphics g, PlanetObj[] planets, int i, BufferedImage[] rotationSpritesMercury) {
        g.drawImage( rotationSpritesMercury[aniIndex],(int) (GamePanel.SCREEN_WIDTH / 2) +(int)(planets[i].x*SCALE) - (int) (planets[i].radius / 2),  (int) (GamePanel.SCREEN_HEIGHT / 2) +(int)(planets[i].y*SCALE) - (int) (planets[i].radius / 2),
        (int)planets[i].radius, (int)planets[i].radius, null);
    }


    public boolean isSun() {
        return isSun;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
