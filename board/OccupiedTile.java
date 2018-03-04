package Chess.board;

import Chess.chess.pieces.Piece;

public final class OccupiedTile extends Tile {

    private final Piece piece;

    public OccupiedTile(final int tileCoordinateX, final int tileCoordinateY, Piece piece) {
        super(tileCoordinateX, tileCoordinateY);
        this.piece = piece;
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
