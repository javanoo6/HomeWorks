package Lesson_2;

import Lesson_2.StaxParser.StaxWriter;

import javax.xml.stream.XMLStreamException;
import java.util.Scanner;


public class Player {
    private Scanner sc;

    public int getId() {
        return id;
    }


    private int id;
    private String name;
    private String symbol;


    public void setId(int id) {
        this.id = id;
    }

    public Player(String symbol, int id) {
        sc = new Scanner(System.in);
        this.symbol = symbol;
        this.id = id;
    }

    public Player() {

    }


    public String getName() {
        return name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void makeMove(TicTacToeBoard board, Player player, StaxWriter configFile) throws XMLStreamException {
        int moveLocation = Integer.parseInt(sc.next());

        if (!board.placePiece(this.getSymbol(), moveLocation, player, configFile)) {
            System.out.println("Not a valid location, " + this.getName() + ", pick another. ");

        }
    }
}