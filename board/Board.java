package Chess.board;


import Chess.chess.pieces.*;

public class Board {
    private Tile[][] playingBoard;
    private Player whitePlayer;
    private Player blackPlayer;

    public Board(Player whitePlayer, Player blackPlayer) {
        this.playingBoard = new Tile[8][8];
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        initTheBoard();
        setPiecesToPlayers(whitePlayer, blackPlayer);
    }

    private void setPiecesToPlayers(Player whitePlayer, Player blackPlayer) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < playingBoard[i].length; j++) {
                whitePlayer.add((OccupiedTile) playingBoard[i][j]);
            }
        }
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < playingBoard[i].length; j++) {
                blackPlayer.add((OccupiedTile) playingBoard[i][j]);
            }
        }
    }

    private void initTheBoard() {
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    playingBoard[0][i] = new OccupiedTile(i, 0, new Rook((byte) 0));
                    break;
                case 1:
                    playingBoard[0][i] = new OccupiedTile(i, 0, new Knight((byte) 0));
                    break;
                case 2:
                    playingBoard[0][i] = new OccupiedTile(i, 0, new Bishop((byte) 0));
                    break;
                case 3:
                    playingBoard[0][i] = new OccupiedTile(i, 0, new Queen((byte) 0));
                    break;
                case 4:
                    playingBoard[0][i] = new OccupiedTile(i, 0, new King((byte) 0));
                    break;
                case 5:
                    playingBoard[0][i] = new OccupiedTile(i, 0, new Bishop((byte) 0));
                    break;
                case 6:
                    playingBoard[0][i] = new OccupiedTile(i, 0, new Knight((byte) 0));
                    break;
                case 7:
                    playingBoard[0][i] = new OccupiedTile(i, 0, new Rook((byte) 0));
            }
        }
        for (int i = 0; i < 8; i++) {
            playingBoard[1][i] = new OccupiedTile(1, i, new Pawn((byte) 0));
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                playingBoard[i][j] = new EmptyTile(j, i);
            }
        }

        for (int i = 0; i < 8; i++) {
            playingBoard[6][i] = new OccupiedTile(i, 6, new Pawn((byte) 1));
        }

        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    playingBoard[7][i] = new OccupiedTile(i, 7, new Rook((byte) 1));
                    break;
                case 1:
                    playingBoard[7][i] = new OccupiedTile(i, 7, new Knight((byte) 1));
                    break;
                case 2:
                    playingBoard[7][i] = new OccupiedTile(i, 7, new Bishop((byte) 1));
                    break;
                case 3:
                    playingBoard[7][i] = new OccupiedTile(i, 7, new Queen((byte) 1));
                    break;
                case 4:
                    playingBoard[7][i] = new OccupiedTile(i, 7, new King((byte) 1));
                    break;
                case 5:
                    playingBoard[7][i] = new OccupiedTile(i, 7, new Bishop((byte) 1));
                    break;
                case 6:
                    playingBoard[7][i] = new OccupiedTile(i, 7, new Knight((byte) 1));
                    break;
                case 7:
                    playingBoard[7][i] = new OccupiedTile(i, 7, new Rook((byte) 1));
            }
        }
    }

    public boolean play(Player player, int fromX, int fromY, int toX, int toY) {
        Tile selected = playingBoard[fromY][fromX];
        if (selected.isTileOccupied()) {
            if (player.getPieces().contains(selected)) {
                if (selected.getPiece().moveIsLegal(fromX, fromY, toX, toY, playingBoard)) {
                    player.remove((OccupiedTile)selected);
                    playingBoard[toY][toX] = new OccupiedTile(toX, toY, selected.getPiece());
                    playingBoard[fromY][fromX] = new EmptyTile(selected.getTileCoordinateX(), selected.getTileCoordinateY());
                    player.add((OccupiedTile) playingBoard[toY][toX]);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheck(Player playerOnMove, Player player) {
        OccupiedTile kingLocation = player.returnTheKing();
        for (OccupiedTile tile : playerOnMove.getPieces()) {
            if (tile.getPiece().moveIsLegal(tile.getTileCoordinateX(), tile.getTileCoordinateY(), kingLocation.getTileCoordinateX(), kingLocation.getTileCoordinateY(), playingBoard)) {
                return true;
            }
        }
        return false;
    }

    public Tile[][] getPlayingBoard() {
        return playingBoard;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }
}
