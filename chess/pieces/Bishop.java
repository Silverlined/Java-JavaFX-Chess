package Chess.chess.pieces;

import Chess.board.Tile;

public class Bishop extends Piece {

    public Bishop(byte colour) {
        super(colour);
    }

    @Override
    public boolean moveIsLegal(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (checkPosition(newX, newY)) {
            if (Math.abs(newX - fromX) == Math.abs(newY - fromY)) {
                if (checkRightTopDiagonalIsOccupied(fromX, fromY, newX, newY, playingBoard)) {
                    return false;
                }
                if (checkLeftTopDiagonalIsOccupied(fromX, fromY, newX, newY, playingBoard)) {
                    return false;
                }
                if (checkRightBottomDiagonalIsOccupied(fromX, fromY, newX, newY, playingBoard)) {
                    return false;
                }
                if (checkLeftBottomDiagonalIsOccupied(fromX, fromY, newX, newY, playingBoard)) {
                    return false;
                }
                if (playingBoard[newY][newX].isTileOccupied()
                        && playingBoard[newY][newX].getPiece().getColour() == playingBoard[fromY][fromX].getPiece().getColour()) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkLeftBottomDiagonalIsOccupied(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (newY > fromY && newX < fromX) {
            for (int i = fromX - 1, j = fromY + 1; i > newX && j < newY; i--, j++) {
                if (playingBoard[j][i].isTileOccupied()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRightBottomDiagonalIsOccupied(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (newY > fromY && newX > fromX) {
            for (int i = fromX + 1, j = fromY + 1; i < newX && j < newY; i++, j++) {
                if (playingBoard[j][i].isTileOccupied()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLeftTopDiagonalIsOccupied(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (newY < fromY && newX < fromX) {
            for (int i = fromX - 1, j = fromY - 1; i > newX && j > newY ; i--, j--) {
                if (playingBoard[j][i].isTileOccupied()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRightTopDiagonalIsOccupied(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (newY < fromY && newX > fromX) {
            for (int i = fromX + 1, j = fromY - 1; i < newX && j > newY; i++, j--) {
                if (playingBoard[j][i].isTileOccupied()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TypeOfPiece getType() {
        return TypeOfPiece.Bishop;
    }

}
