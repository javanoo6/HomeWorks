package ru.javanoo6.Lesson_2;

import java.util.*;

public class Player {


//    public Player(String id, String name, String symbol) {
//        this.idForWeb=id;
//        this.name=name;
//        this.symbol=symbol;
//    }

    public int getCounter() {
        return counter;
    }

    private int counter;
    private Scanner sc;
    private int id;
    private String name;
    private String symbol;
    private int pos;

    public String getIdForWeb() {
        return idForWeb;
    }

    public void setIdForWeb(String idForWeb) {
        this.idForWeb = idForWeb;
    }

    private String idForWeb;


    public Player(int id, int num, String name, int counter) {
        this.id = id;
        this.name = name;
        this.pos = num;
        this.counter = counter;

    }
   public List<Player> listOfPlayerSteps = new ArrayList<>();

    public void setListOfPlayerSteps(int id, int boardPos, String name, int counter) {
        listOfPlayerSteps.add(new Player(id, boardPos, name, counter));
    }

    public int getSize() {
        return listOfPlayerSteps.size();
    }

    public Player getPlayerStep(int index) {
        return listOfPlayerSteps.get(index);
    }

    public void setWinner(String name, int id, String symbol) {
        this.name = name;
        this.id = id;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }
    public int getPos() {
        return pos;
    }
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

    public void makeMove(TicTacToeBoard board){
        int moveLocation = Integer.parseInt(sc.next());

        if (!board.placePiece(this.getSymbol(), moveLocation)) {
            System.out.println("Сюда нельзя походить, " + this.getName() + ", выберите другую ячейку ");

        }
    }

}