package Chess.chess.pieces;

import Chess.Piece;
import Chess.board.Tile;

public class King extends Piece {

    public King(byte colour) {
        super(colour);
    }

    @Override
    public boolean moveIsLegal(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (checkPosition(newX, newY)) {
            if (playingBoard[newY][newX].isTileOccupied()
                    && playingBoard[newY][newX].getPiece().getColour() == playingBoard[fromY][fromX].getPiece().getColour()) {
                return false;
            }
            if (newX == fromX && newY == fromY) {
                return false;
            }
            if ((newX == fromX + 1 || newX == fromX - 1 || newX == fromX) && (newY == fromY + 1 || newY == fromY - 1 || newY == fromY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TypeOfPiece getType() {
        return TypeOfPiece.King;
    }

}
