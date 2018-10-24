package pieces;

import Chess.board.Tile;

public class Knight extends Piece {

    public Knight(byte colour) {
        super(colour);
    }

    @Override
    public boolean moveIsLegal(int fromX, int fromY, int newX, int newY, Tile[][] playingBoard) {
        if (checkPosition(newX, newY)) {
            if (playingBoard[newY][newX].isTileOccupied()
                    && playingBoard[newY][newX].getPiece().getColour() == playingBoard[fromY][fromX].getPiece().getColour()) {
                return false;
            }
            return checkTheTopTwoPositions(fromX, fromY, newX, newY)
                    || checkLowerTopPositions(fromX, fromY, newX, newY)
                    || checkHigherBottomPositions(fromX, fromY, newX, newY)
                    || checkTheBottomTwoPositions(fromX, fromY, newX, newY);
        }
        return false;
    }

    private boolean checkLowerTopPositions(int fromX, int fromY, int newX, int newY) {
        if (fromY + 1 < 8) {
            if (fromX - 2 >= 0) {
                if (newX == fromX - 2 && newY == fromY + 1) {
                    return true;
                }
            }
            if (fromX + 2 < 8) {
                return newX == fromX + 2 && newY == fromY + 1;
            }
        }
        return false;
    }

    private boolean checkTheTopTwoPositions(int fromX, int fromY, int newX, int newY) {
        if (fromY + 2 < 8) {
            if (fromX - 1 >= 0) {
                if (newX == fromX - 1 && newY == fromY + 2) {
                    return true;
                }
            }
            if (fromX + 1 < 8) {
                return newX == fromX + 1 && newY == fromY + 2;
            }
        }
        return false;
    }

    private boolean checkTheBottomTwoPositions(int fromX, int fromY, int newX, int newY) {
        if (fromY - 2 > 0) {
            if (fromX - 1 >= 0) {
                if (newX == fromX - 1 && newY == fromY - 2) {
                    return true;
                }
            }
            if (fromX + 1 < 8) {
                return newX == fromX + 1 && newY == fromY - 2;
            }
        }
        return false;
    }

    private boolean checkHigherBottomPositions(int fromX, int fromY, int newX, int newY) {
        if (fromY - 1 < 8) {
            if (fromX - 2 >= 0) {
                if (newX == fromX - 2 && newY == fromY - 1) {
                    return true;
                }
            }
            if (fromX + 2 < 8) {
                return newX == fromX + 2 && newY == fromY - 1;
            }
        }
        return false;
    }

    @Override
    public TypeOfPiece getType() {
        return TypeOfPiece.Knight;
    }
}
