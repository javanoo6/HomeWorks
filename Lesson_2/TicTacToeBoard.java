package Lesson_2;

import java.util.Arrays;

public class TicTacToeBoard {
    private final String[] board;


    public TicTacToeBoard() {
        this.board = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    }

    public TicTacToeBoard(String[] board) {

        this.board = new String[9];
        System.arraycopy(board, 0, this.board, 0, board.length);
    }


    public String[] getBoard() {
        return this.board;
    }

    public boolean placePiece(String xo, int pos) {
        if (pos < 0 || pos > 9) return false;

        else if (board[pos - 1].contains("X") || board[pos - 1].contains("0")) return false;

        else this.getBoard()[pos - 1] = xo;
        return true;
    }

    public boolean checkIfGameOver() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> null;
            };


            if (line.equals("XXX")) {
                return true;
            } else if (line.equals("OOO")) {
                return true;
            }
        }
        return false;
    }


    public void drawBoard() {
        String[] board = this.getBoard();
        System.out.println(" " + board[0] + " ¦ " + board[1] + " ¦ " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " ¦ " + board[4] + " ¦ " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " ¦ " + board[7] + " ¦ " + board[8]);
    }


    public boolean checkifGameDraw() {
        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return true;
            }
        }
        return false;
    }
}


