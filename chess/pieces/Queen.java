package Chess.chess.pieces;

import Chess.Piece;
import Chess.board.Tile;

import java.util.Queue;

public class Queen extends Piece {

    public Queen(byte colour) {
        super(colour);
    }

    @Override
    public boolean moveIsLegal(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (checkPosition(newX, newY)) {
            Bishop tempBishop = new Bishop((byte) 0);
            Rook tempRook = new Rook((byte)0);
            if (tempBishop.moveIsLegal(fromX, fromY, newX, newY, playingBoard)
                    || tempRook.moveIsLegal(fromX, fromY, newX, newY, playingBoard)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TypeOfPiece getType() {
        return TypeOfPiece.Queen;
    }

}
