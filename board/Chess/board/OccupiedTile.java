package Chess.board;

import pieces.Piece;

public final class OccupiedTile extends Tile {

    private final Piece piece;

    public OccupiedTile(final int tileCoordinateX, final int tileCoordinateY, Piece piece) {
        super(tileCoordinateX, tileCoordinateY);
        this.piece = piece;
    }

    public OccupiedTile(OccupiedTile e) {
        super(e.getTileCoordinateX(), e.getTileCoordinateY());
        this.piece = e.getPiece();
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }
}
