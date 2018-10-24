package pieces;

import Chess.board.Tile;

public abstract class Piece {
    private final byte colour;

    public Piece(byte colour) {
        this.colour = colour;
    }

    public abstract boolean moveIsLegal(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard);

    public abstract TypeOfPiece getType();

    public byte getColour() {
        return colour;
    }

    boolean checkPosition(int newX, int newY) {
        return (newX >= 0 && newX < 8) && (newY >= 0 && newY < 8);
    }
}
