package Chess.chess.pieces;

import Chess.Piece;
import Chess.board.Tile;

public class Pawn extends Piece {

    public Pawn(byte colour) {
        super(colour);
    }

    @Override
    public boolean moveIsLegal(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (checkPosition(newX, newY)) {
            if (playingBoard[fromY][fromX].getPiece().getColour() == 0) {
                if (playingBoard[fromY + 1][fromX].isTileOccupied()) {
                    return diagonalCheckWhite(fromX, fromY, newX, newY, playingBoard);
                } else if (fromY == 1) {
                    return newX == fromX && (newY == fromY + 1 || newY == fromY + 2) || diagonalCheckWhite(fromX, fromY, newX, newY, playingBoard);
                } else {
                    return newX == fromX && newY == fromY + 1 || diagonalCheckWhite(fromX, fromY, newX, newY, playingBoard);
                }
            } else {
                if (playingBoard[fromY - 1][fromX].isTileOccupied()) {
                    return diagonalCheckBlack(fromX, fromY, newX, newY, playingBoard);
                } else if (fromY == 6) {
                    return newX == fromX && newY == fromY - 1 || newY == fromY - 2 || diagonalCheckBlack(fromX, fromY, newX, newY, playingBoard);
                } else {
                    return newX == fromX && newY == fromY - 1 || diagonalCheckBlack(fromX, fromY, newX, newY, playingBoard);
                }
            }
        }
        return false;
    }

    private boolean diagonalCheckWhite(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        try {
            if (fromX < 7
                    && playingBoard[fromY + 1][fromX + 1].isTileOccupied()
                    && playingBoard[fromY + 1][fromX + 1].getPiece().getColour() != playingBoard[fromY][fromX].getPiece().getColour()
                    && fromX + 1 == newX
                    && fromY + 1 == newY) {
                return true;
            } else if (fromX > 0
                    && playingBoard[fromY + 1][fromX - 1].isTileOccupied()
                    && playingBoard[fromY + 1][fromY + 1].getPiece().getColour() != playingBoard[fromY][fromX].getPiece().getColour()
                    && fromX - 1 == newX
                    && fromY + 1 == newY) {
                return true;
            }
        } catch (NullPointerException e) {
        }
        return false;
    }

    private boolean diagonalCheckBlack(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        try {
            if (fromX < 7
                    && playingBoard[fromY - 1][fromX + 1].isTileOccupied()
                    && playingBoard[fromY - 1][fromX - 1].getPiece().getColour() != playingBoard[fromY][fromX].getPiece().getColour()
                    && fromX + 1 == newX
                    && fromY - 1 == newY) {
                return true;
            } else if (fromX > 0
                    && playingBoard[fromY - 1][fromX - 1].isTileOccupied()
                    && playingBoard[fromY - 1][fromX + 1].getPiece().getColour() != playingBoard[fromY][fromX].getPiece().getColour()
                    && fromX - 1 == newX
                    && fromY - 1 == newY) {
                return true;
            }
        } catch (NullPointerException e) {
        }
        return false;
    }

    @Override
    public TypeOfPiece getType() {
        return TypeOfPiece.Pawn;
    }
}
