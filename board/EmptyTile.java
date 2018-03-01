package Chess.board;

import Chess.Piece;

public final class EmptyTile extends Tile {

    public EmptyTile(final int tileCoordinateX, final int tileCoordinateY) {
        super(tileCoordinateX, tileCoordinateY);
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
