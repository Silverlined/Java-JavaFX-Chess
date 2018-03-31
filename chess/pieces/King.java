package Chess.chess.pieces;

import Chess.board.Tile;

import java.util.HashMap;
import java.util.Map;

public class King extends Piece {

    public King(byte colour) {
        super(colour);
    }

    public Map<Integer, Integer> getPossibleMoveOfKing(int fromX, int fromY, Tile[][] playingBoard) {
        HashMap<Integer, Integer> legalMoves = new HashMap<>();
        if (fromX < 7 && moveIsLegal(fromX, fromY, fromX + 1, fromY, playingBoard)) {
            legalMoves.put(fromX + 1, fromY);
        }
        if (fromX > 0 && moveIsLegal(fromX, fromY, fromX - 1, fromY, playingBoard)) {
            legalMoves.put(fromX - 1, fromY);
        }
        if (fromY < 7 && moveIsLegal(fromX, fromY, fromX, fromY + 1, playingBoard)) {
            legalMoves.put(fromX, fromY + 1);
        }
        if (fromY > 0 && moveIsLegal(fromX, fromY, fromX, fromY - 1, playingBoard)) {
            legalMoves.put(fromX, fromY - 1);
        }
        if (fromX < 7 && fromY < 7 && moveIsLegal(fromX, fromY, fromX + 1, fromY + 1, playingBoard)) {
            legalMoves.put(fromX + 1, fromY + 1);
        }
        if (fromX > 0 && fromY < 7 && moveIsLegal(fromX, fromY, fromX - 1, fromY + 1, playingBoard)) {
            legalMoves.put(fromX - 1, fromY + 1);
        }
        if (fromX > 0 && fromY > 0 && moveIsLegal(fromX, fromY, fromX - 1, fromY - 1, playingBoard)) {
            legalMoves.put(fromX - 1, fromY - 1);
        }
        if (fromX < 7 && fromY > 0 && moveIsLegal(fromX, fromY, fromX + 1, fromY - 1, playingBoard)) {
            legalMoves.put(fromX + 1, fromY - 1);
        }
        return legalMoves;
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
