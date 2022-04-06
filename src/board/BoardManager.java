package board;

import java.util.ArrayList;

public class BoardManager {
    private ArrayList<ArrayList<Cell>> board;
    public static final short BOARD_LENGTH = 3;

    public BoardManager() {
        this.board = new ArrayList<ArrayList<Cell>>();
        for (short i = 0; i < BOARD_LENGTH; i++){
            board.add(new ArrayList<Cell>());
            for (short j = 0; j < BOARD_LENGTH; j++) {
                board.get(i).add(new Cell());
            }
        }
    }

    public void setBoardPosition(Position position, Cell cell) {
        this.board
                .get(position.getXCoordinate())
                .set(position.getYCoordinate(), cell);
    }

    public void print() {
        ArrayList<Cell> boardRow = null;
        int lastBoardCharacterPosition = 2;
        int lastBoardRowPosition = 2;

        System.out.println(" 1  2  3");
        for (int i = 0; i < BOARD_LENGTH; i++) {
            boardRow =  this.board.get(i);
            System.out.printf("%d", ++i);
            for (int j = 0; j <= BOARD_LENGTH; j++) {
                if (j == lastBoardCharacterPosition) {
                    System.out.printf(" %s%n", boardRow.get(j));
                } else {
                    System.out.printf(" %s | ", boardRow.get(j));
                }
            }
            if ( i != lastBoardRowPosition) {
                System.out.println(" ---+---+---");
            }
        }
    }

    public ArrayList<Position> getEmptyPositions() {
        var emptyPositions = new ArrayList<Position>();
        short xCoordinate = 0, yCoordinate = 0;
        for (final var boardRow : this.board) {
            for (final var cell : boardRow) {
                if (cell.getType() == CellType.EMPTY) {
                    emptyPositions.add(new Position(xCoordinate, yCoordinate));
                }
                yCoordinate++;
            }
            xCoordinate++;
        }
        return emptyPositions;
    }
}
