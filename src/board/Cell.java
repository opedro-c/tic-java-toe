package board;

public class Cell {
    private CellType type = CellType.EMPTY;

    public void setType(CellType cellType) {
        this.type = cellType;
    }

    public CellType getType() {
        return type;
    }

    @Override
    public String toString() {
        return switch (this.type) {
            case EMPTY -> " ";
            case O -> "O";
            case X -> "X";
        };
    }
}
