package Chess.board;

import Chess.chess.pieces.TypeOfPiece;

import java.util.ArrayList;
import java.util.List;

public final class Player {
    private String name;
    private List<OccupiedTile> pieces;

    public Player() {
        pieces = new ArrayList<>(16);
    }

    public Player(String name) {
        this.name = name;
        pieces = new ArrayList<>(16);
    }

    public Player(Player player) {
        this.name = null;
        this.pieces = player.cloneList();
    }

    public List<OccupiedTile> getPieces() {
        return pieces;
    }

    public String getName() {
        return name;
    }

    public void add(OccupiedTile piece) {
        pieces.add(piece);
    }

    public void remove(OccupiedTile piece) {
        pieces.remove(piece);
    }

    public OccupiedTile returnTheKing() {
        for (OccupiedTile piece : pieces) {
            if (piece.getPiece().getType() == TypeOfPiece.King) {
                return piece;
            }
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<OccupiedTile> cloneList() {
        return new ArrayList<>(pieces);
    }
}
