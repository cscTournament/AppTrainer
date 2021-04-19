package by.gourianova.apptrainer.entity.tetris.model;

public class Coordinates {
    public final int x;
    public final int y;
    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Coordinates plus(int sx, int sy) {
        return new Coordinates(x+sx,y+sy);
    }
}
