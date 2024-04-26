package conwaygame.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;


public class Game extends JPanel implements Runnable{
    private final int width;
    private final int height;
    private final float fpsLimit;
    private boolean finish;
    private Modelo conway;
    private Canvas canvas;
    private BufferStrategy bs;
    private int simulationRate;
    private Graphics g;

    public Game(int width, int height, int fpsLimit, int simulationRate){
        this.fpsLimit = fpsLimit;
        this.height = height;
        this.width = width;
        this.simulationRate = simulationRate;
        
        this.finish = false;
        this.canvas = new Canvas();
        add(canvas);
        
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    public void start(){
        this.conway = new Modelo(320, 210);
          
        Thread thread = new Thread(this);
        
        thread.start();
        

        
    }

    @Override
    public void run() {
        final double NANOS_BETWEEN_UPDATE = 1000000000 / fpsLimit;
        System.out.println("Entra");
        final double NANOS_BETWEEN_UPDATE_SIMULATION = 1000000000 / simulationRate;
        System.out.println(NANOS_BETWEEN_UPDATE);
        long lastFrame = System.nanoTime();
        long lastSimulation = System.nanoTime();
        
        long currentFrame = System.nanoTime();
        conway.restart();
        while (!finish) {
            currentFrame = System.nanoTime(); 
            if (currentFrame - lastFrame > NANOS_BETWEEN_UPDATE) {
                procesInput();
                render();

                lastFrame = currentFrame;
                
            }
            if (currentFrame - lastSimulation > NANOS_BETWEEN_UPDATE_SIMULATION) {
                update();
                
                lastSimulation = currentFrame;
            }
            
            
           
        }
    }

    private void update(){
        conway.updateMap();
    }
    private void render() {
        
        repaint();
    }

    private void procesInput() {
        // TODO proecsInput
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        conway.print(g);
    }
    
    
}