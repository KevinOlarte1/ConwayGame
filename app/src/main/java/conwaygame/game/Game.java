package conwaygame.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;

import conwaygame.game.Interfaz.IControler;


public class Game extends JPanel implements Runnable{
    private double NANOS_BETWEEN_UPDATE = 1000000000;
    private double NANOS_BETWEEN_UPDATE_SIMULATION = 1000000000;

    private final static int DEFAULT_WIDTH_MAP = 320;
    private final static int DEFAULT_HEIGHT_MAP = 210;

    private final int width;
    private final int height;
    private final float fpsLimit;
    private boolean finish;
    private boolean stop;
    private Modelo conway;
    private Canvas canvas;
    private BufferStrategy bs;
    private int simulationRate;
    private Graphics g;

    private IControler conwayController;

    

    public Game(int width, int height, int fpsLimit, int simulationRate){
        JPanel asdas = new JPanel();
        asdas.setBounds(fpsLimit, simulationRate, width, height);
        this.fpsLimit = fpsLimit;
        this.NANOS_BETWEEN_UPDATE /=fpsLimit;
        this.height = height;
        this.width = width;
        this.simulationRate = simulationRate;
        this.NANOS_BETWEEN_UPDATE_SIMULATION /= simulationRate;
        
        this.finish = false;
        this.stop = false;
        this.canvas = new Canvas();
        add(canvas);
        
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    public void start(IControler conwayControler){
        this.conway = new Modelo(DEFAULT_WIDTH_MAP,DEFAULT_HEIGHT_MAP);
          
        Thread thread = new Thread(this);
        this.conwayController = conwayControler;

        addKeyListener(this.conwayController.getActionStop());
        thread.start();
        

        
    }

    @Override
    public void run() {
        System.out.println("Entra");
        System.out.println(NANOS_BETWEEN_UPDATE);
        long lastFrame = System.nanoTime();
        long lastSimulation = System.nanoTime();
        
        long currentFrame = System.nanoTime();
        conway.restart();
        while (!finish) {
            currentFrame = System.nanoTime(); 
            //conwayController.procesInput();

            if (currentFrame - lastFrame > NANOS_BETWEEN_UPDATE) {
                render();

                lastFrame = currentFrame;
                
            }
            if (stop == false && currentFrame - lastSimulation > NANOS_BETWEEN_UPDATE_SIMULATION) {
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

    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        conway.print(g);
    }

    public void setStop(){
        this.stop = !this.stop;
    }

    public void addTimeLive(){
        this.NANOS_BETWEEN_UPDATE_SIMULATION += 1000000;
    }
    public void lessTimeLive(){
        this.NANOS_BETWEEN_UPDATE_SIMULATION -=1000000;
    }
    
    
}