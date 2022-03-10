
package Lesson_2;

import java.util.Scanner;


public class Player {
    private final Scanner sc;

    private String name;
    private final String symbol;



    public Player(String symbol) {
        sc = new Scanner(System.in);
        this.symbol = symbol;
    }


    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void makeMove(TicTacToeBoard board) {
        int moveLocation = Integer.parseInt(sc.next());


        if (!board.placePiece(this.getSymbol(), moveLocation)) {
            System.out.println("Not a valid location, " + this.getName() + ", pick another. ");
            makeMove(board);
        }
    }
}