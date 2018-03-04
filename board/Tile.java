package Chess.board;

import Chess.chess.pieces.Piece;

public abstract class Tile {
    private int tileCoordinateX;
    private int tileCoordinateY;

    Tile(int tileCoordinateX, int tileCoordinateY) {
        this.tileCoordinateX = tileCoordinateX;
        this.tileCoordinateY = tileCoordinateY;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public int getTileCoordinateX() {
        return tileCoordinateX;
    }

    public int getTileCoordinateY() {
        return tileCoordinateY;
    }

    public void setTileCoordinateX(int tileCoordinateX) {
        this.tileCoordinateX = tileCoordinateX;
    }

    public void setTileCoordinateY(int tileCoordinateY) {
        this.tileCoordinateY = tileCoordinateY;
    }
}
