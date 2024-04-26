package conwaygame.lib;
public class Points {

    private int x;
    private int y;

    public Points(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Points(){
        this(0, 0);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    
}