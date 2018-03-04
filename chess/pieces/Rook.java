package Chess.chess.pieces;

import Chess.board.Tile;

public class Rook extends Piece {

    public Rook(byte colour) {
        super(colour);
    }

    @Override
    public boolean moveIsLegal(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (checkPosition(newX, newY)) {
            if (playingBoard[newY][newX].isTileOccupied()
                    && playingBoard[newY][newX].getPiece().getColour() == playingBoard[fromY][fromX].getPiece().getColour()) {
                return false;
            }
            if (newX == fromX && newY != fromY) {
                return (checkVertically(fromX, fromY, newX, newY, playingBoard)
                        &&
                        (!playingBoard[newY][newX].isTileOccupied()
                                || playingBoard[newY][newX].getPiece().getColour()
                                != playingBoard[fromY][fromX].getPiece().getColour()));
            } else if (newX != fromX && newY == fromY) {
                return (checkHorizontally(fromX, fromY, newX, newY, playingBoard)
                        && (!playingBoard[newY][newX].isTileOccupied()
                        || playingBoard[newY][newX].getPiece().getColour()
                        != playingBoard[fromY][fromX].getPiece().getColour()));
            }
        }
        return false;
    }

    private boolean checkHorizontally(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (newX > fromX) {
            for (int i = fromX + 1; i < newX; i++) {
                if (playingBoard[newY][i].isTileOccupied()) {
                    return false;
                }
            }
        } else {
            for (int i = fromX - 1; i > newX; i--) {
                if (playingBoard[newY][i].isTileOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkVertically(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (newY > fromY) {
            for (int i = fromY + 1; i < newY; i++) {
                if (playingBoard[i][fromX].isTileOccupied()) {
                    return false;
                }
            }
        } else {
            for (int i = fromY - 1; i > newY; i--) {
                if (playingBoard[i][fromX].isTileOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public TypeOfPiece getType() {
        return TypeOfPiece.Rook;
    }
}
