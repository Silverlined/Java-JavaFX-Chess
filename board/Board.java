package Chess.board;


import Chess.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Board {
    private Tile[][] playingBoard;
    private Player whitePlayer;
    private Player blackPlayer;
    private OccupiedTile checkingPiece;
    private int[] checkingPath;

    public Board(Player whitePlayer, Player blackPlayer) {
        this.playingBoard = new Tile[8][8];
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        initTheBoard();
        setPiecesToPlayers(whitePlayer, blackPlayer);
    }

    private void setPiecesToPlayers(Player whitePlayer, Player blackPlayer) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
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
            playingBoard[1][i] = new OccupiedTile(i, 1, new Pawn((byte) 0));
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
                    player.remove((OccupiedTile) selected);
                    playingBoard[toY][toX] = new OccupiedTile(toX, toY, selected.getPiece());
                    playingBoard[fromY][fromX] = new EmptyTile(selected.getTileCoordinateX(), selected.getTileCoordinateY());
                    player.add((OccupiedTile) playingBoard[toY][toX]);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheck(Player playerOnMove, Player playerInDanger) {
        OccupiedTile kingLocation = playerInDanger.returnTheKing();
        for (OccupiedTile tile : playerOnMove.getPieces()) {
            if (tile.getPiece().moveIsLegal(tile.getTileCoordinateX(), tile.getTileCoordinateY(),
                    kingLocation.getTileCoordinateX(), kingLocation.getTileCoordinateY(), playingBoard)) {
                checkingPiece = tile;
                checkingPath = new int[2];
                checkingPath[0] = checkingPiece.getTileCoordinateX() - kingLocation.getTileCoordinateX();
                checkingPath[1] = checkingPiece.getTileCoordinateY() - kingLocation.getTileCoordinateY();
                return true;
            }
        }
        return false;
    }

    public String isCheckMate(Player playerOnMove, Player playerInDanger) {
        AtomicBoolean isCheckMate = new AtomicBoolean(true);
        isTheKingAbleToMove(playerOnMove, playerInDanger, isCheckMate);
        canYouDefendTheKing(playerOnMove, playerInDanger, isCheckMate);
        return isCheckMate.toString();
    }

    private void canYouDefendTheKing(Player playerOnMove, Player playerInDanger, AtomicBoolean isCheckMate) {
        OccupiedTile kingLocation = playerInDanger.returnTheKing();
        Player playerOnMoveCopy = new Player(playerOnMove);
        Player playerInDangerCopy = new Player(playerInDanger);
        for (OccupiedTile e : playerInDanger.getPieces()) {
            isAbleToMove(e, playerOnMoveCopy, playerInDangerCopy, isCheckMate, kingLocation);
        }
    }

    private void isAbleToMove(OccupiedTile e, Player playerOnMoveCopy, Player playerInDangerCopy, AtomicBoolean isCheckMate, OccupiedTile kingLocation) {
        if (e.getPiece().moveIsLegal(e.getTileCoordinateX(), e.getTileCoordinateY(),
                checkingPiece.getTileCoordinateX(), checkingPiece.getTileCoordinateY(), playingBoard)) {
            playerOnMoveCopy.remove(checkingPiece);
            playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = new EmptyTile(e.getTileCoordinateX(), e.getTileCoordinateY());
            playingBoard[checkingPiece.getTileCoordinateY()][checkingPiece.getTileCoordinateX()] = e;
            if (!isCheck(playerOnMoveCopy, playerInDangerCopy)) {
                isCheckMate.set(false);
            }
            playerOnMoveCopy.add(checkingPiece);
            playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = e;
            playingBoard[checkingPiece.getTileCoordinateY()][checkingPiece.getTileCoordinateX()] = checkingPiece;
        }
        if (checkingPiece.getPiece().getType() != TypeOfPiece.Knight) {
            int coordinatesX = checkingPiece.getTileCoordinateX();
            int coordinateY = checkingPiece.getTileCoordinateY();
            if (checkingPieceInTopRight()) {
                while (coordinatesX != kingLocation.getTileCoordinateX() && coordinateY != kingLocation.getTileCoordinateY()) {
                    if (e.getPiece().moveIsLegal(e.getTileCoordinateX(), e.getTileCoordinateY(),
                            coordinatesX - 1, coordinateY + 1, playingBoard)) {
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = new EmptyTile(e.getTileCoordinateX(), e.getTileCoordinateY());
                        playingBoard[coordinateY + 1][coordinatesX - 1] = e;
                        if (!isCheck(playerOnMoveCopy, playerInDangerCopy)) {
                            isCheckMate.set(false);
                        }
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = e;
                        playingBoard[coordinateY + 1][coordinatesX - 1] = new EmptyTile(coordinatesX - 1, coordinateY + 1);
                    }
                    coordinatesX--;
                    coordinateY++;
                }
            } else if (checkingPieceAboveKing()) {
                while (coordinateY != kingLocation.getTileCoordinateY()) {
                    if (e.getPiece().moveIsLegal(e.getTileCoordinateX(), e.getTileCoordinateY(),
                            coordinatesX, coordinateY + 1, playingBoard)) {
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = new EmptyTile(e.getTileCoordinateX(), e.getTileCoordinateY());
                        playingBoard[coordinateY + 1][coordinatesX] = e;
                        if (!isCheck(playerOnMoveCopy, playerInDangerCopy)) {
                            isCheckMate.set(false);
                        }
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = e;
                        playingBoard[coordinateY + 1][coordinatesX] = new EmptyTile(coordinatesX, coordinateY + 1);
                    }
                    coordinateY++;
                }
            } else if (checkingPieceInTopLeft()) {
                while (coordinatesX != kingLocation.getTileCoordinateX() && coordinateY != kingLocation.getTileCoordinateY()) {
                    if (e.getPiece().moveIsLegal(e.getTileCoordinateX(), e.getTileCoordinateY(),
                            coordinatesX + 1, coordinateY + 1, playingBoard)) {
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = new EmptyTile(e.getTileCoordinateX(), e.getTileCoordinateY());
                        playingBoard[coordinateY + 1][coordinatesX + 1] = e;
                        if (!isCheck(playerOnMoveCopy, playerInDangerCopy)) {
                            isCheckMate.set(false);
                        }
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = e;
                        playingBoard[coordinateY + 1][coordinatesX + 1] = new EmptyTile(coordinatesX + 1, coordinateY + 1);
                    }
                    coordinatesX++;
                    coordinateY++;
                }
            } else if (checkingPieceInBottomLeft()) {
                while (coordinatesX != kingLocation.getTileCoordinateX() && coordinateY != kingLocation.getTileCoordinateY()) {
                    if (e.getPiece().moveIsLegal(e.getTileCoordinateX(), e.getTileCoordinateY(),
                            coordinatesX + 1, coordinateY - 1, playingBoard)) {
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = new EmptyTile(e.getTileCoordinateX(), e.getTileCoordinateY());
                        playingBoard[coordinateY - 1][coordinatesX + 1] = e;
                        if (!isCheck(playerOnMoveCopy, playerInDangerCopy)) {
                            isCheckMate.set(false);
                        }
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = e;
                        playingBoard[coordinateY - 1][coordinatesX + 1] = new EmptyTile(coordinatesX + 1, coordinateY - 1);
                    }
                    coordinatesX++;
                    coordinateY--;
                }
            } else if (checkingPieceBelowKing()) {
                while (coordinateY != kingLocation.getTileCoordinateY()) {
                    if (e.getPiece().moveIsLegal(e.getTileCoordinateX(), e.getTileCoordinateY(),
                            coordinatesX, coordinateY - 1, playingBoard)) {
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = new EmptyTile(e.getTileCoordinateX(), e.getTileCoordinateY());
                        playingBoard[coordinateY - 1][coordinatesX] = e;
                        if (!isCheck(playerOnMoveCopy, playerInDangerCopy)) {
                            isCheckMate.set(false);
                        }
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = e;
                        playingBoard[coordinateY - 1][coordinatesX] = new EmptyTile(coordinatesX, coordinateY - 1);
                    }
                    coordinateY--;
                }
            } else if (checkingPieceInBottomRight()) {
                while (coordinatesX != kingLocation.getTileCoordinateX() && coordinateY != kingLocation.getTileCoordinateY()) {
                    if (e.getPiece().moveIsLegal(e.getTileCoordinateX(), e.getTileCoordinateY(),
                            coordinatesX - 1, coordinateY - 1, playingBoard)) {
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = new EmptyTile(e.getTileCoordinateX(), e.getTileCoordinateY());
                        playingBoard[coordinateY - 1][coordinatesX - 1] = e;
                        if (!isCheck(playerOnMoveCopy, playerInDangerCopy)) {
                            isCheckMate.set(false);
                        }
                        playingBoard[e.getTileCoordinateY()][e.getTileCoordinateX()] = e;
                        playingBoard[coordinateY - 1][coordinatesX - 1] = new EmptyTile(coordinatesX - 1, coordinateY - 1);
                    }
                    coordinatesX--;
                    coordinateY--;
                }
            }
        }
    }

    private boolean checkingPieceInBottomRight() {
        return checkingPath[0] > 0 && checkingPath[1] > 0;
    }

    private boolean checkingPieceBelowKing() {
        return checkingPath[0] == 0 && checkingPath[1] > 0;
    }

    private boolean checkingPieceInBottomLeft() {
        return checkingPath[0] < 0 && checkingPath[1] > 0;
    }

    private boolean checkingPieceInTopLeft() {
        return checkingPath[0] < 0 && checkingPath[1] < 0;
    }

    private boolean checkingPieceAboveKing() {
        return checkingPath[0] == 0 && checkingPath[1] < 0;
    }

    private boolean checkingPieceInTopRight() {
        return checkingPath[0] > 0 && checkingPath[1] < 0;
    }

    private void isTheKingAbleToMove(Player playerOnMove, Player playerInDanger, AtomicBoolean isCheckMate) {
        OccupiedTile kingLocation = playerInDanger.returnTheKing();
        King tempKing = (King) kingLocation.getPiece();
        tempKing.getPossibleMoveOfKing(kingLocation.getTileCoordinateX(), kingLocation.getTileCoordinateY(), playingBoard).forEach(
                (k, v) -> {
                    if (!isCheckFuture(playerOnMove, new OccupiedTile(k, v, new King(tempKing.getColour())))) {
                        isCheckMate.set(false);
                    }
                });
    }

    private boolean isCheckFuture(Player playerOnMove, OccupiedTile possibleLocation) {
        for (OccupiedTile tile : playerOnMove.getPieces()) {
            if (tile.getPiece().moveIsLegal(tile.getTileCoordinateX(), tile.getTileCoordinateY(),
                    possibleLocation.getTileCoordinateX(), possibleLocation.getTileCoordinateY(), playingBoard)) {
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
