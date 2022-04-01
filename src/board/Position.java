package board;

public class Position {
    private final short xCoordinate;
    private final short yCoordinate;

    public Position(short xCoordinate, short yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public short getXCoordinate() {
        return xCoordinate;
    }

    public short getYCoordinate() {
        return yCoordinate;
    }
}
