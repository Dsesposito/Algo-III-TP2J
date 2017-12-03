package fiuba.algo3.tp2.model;

public class Position {

    Integer xpos;
    Integer ypos;

    public Position(Integer initialXPos, Integer initialYPos){
        this.xpos = initialXPos;
        this.ypos = initialYPos;
    }

    public Position(Position position) {
        this.xpos = position.getXpos();
        this.ypos = position.getYpos();
    }

    public Integer getXpos() {
        return xpos;
    }

    public Integer getYpos() {
        return ypos;
    }

    public static Position inXY(Integer xPos, Integer yPos) {
        return new Position(xPos,yPos);
    }
}
