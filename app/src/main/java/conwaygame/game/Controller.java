package conwaygame.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import conwaygame.game.Interfaz.IControler;

public class Controller implements IControler {
    private final Game game;
    private final KeyListener actionStop;

    public Controller(Game game){
        this.game  = game;

        this.actionStop = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    game.lessTimeLive();
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    game.addTimeLive();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    game.setStop();
                }
            }
            
        };
    }

    @Override
    public void procesInput() {
       
    }

    @Override
    public KeyListener getActionStop() {
        return actionStop;
    }

    

    
}
