package Chess.board;

import Chess.chess.pieces.Piece;

public abstract class Tile {
    private final int tileCoordinateX;
    private final int tileCoordinateY;

    Tile(final int tileCoordinateX, final int tileCoordinateY) {
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
}
