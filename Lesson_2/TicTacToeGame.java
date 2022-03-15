package Lesson_2;

import Lesson_2.StaxParser.StaxWriter;

import javax.xml.stream.XMLStreamException;
import java.util.Scanner;

public class TicTacToeGame {

    public StaxWriter configFile = new StaxWriter();

    private TicTacToeBoard gameBoard;
    private LeaderBoard leaderBoard = new LeaderBoard();

    public TicTacToeGame() {
        this.gameBoard = new TicTacToeBoard();
    }


    public TicTacToeBoard getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(TicTacToeBoard gameBoard) {
        this.gameBoard = gameBoard;
    }


    public void twoPlayer() throws XMLStreamException {

        Scanner sc = new Scanner(System.in);

        Player playerOne = new Player("X", 1);

        Player playerTwo = new Player("O", 2);

        Player[] players = new Player[]{playerOne, playerTwo};


        configFile.setFile((configFile));

        System.out.print("Введите имя первого участника: ");
        playerOne.setName(sc.next());
        System.out.print("Введите имя второго участника: ");
        playerTwo.setName(sc.next());


        try {
            configFile.GamePlayNode(playerOne, playerTwo);
            configFile.createGameNode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.getGameBoard().drawBoard();
        boolean gameOver = false;
        do {
            for (Player player : players) {
                System.out.println(player.getName() + ", Ваш ход " + "(вы играете " + player.getSymbol() + "-ми)");

                player.makeMove(this.getGameBoard(), player, configFile);

                this.getGameBoard().drawBoard();

                if (this.getGameBoard().checkIfGameOver()) {
                    leaderBoard.LeaderBoardIfWinner(player.getName());
                    System.out.println(player.getName() + "-Победитель");
                    configFile.closeAllNodes(player);
                    gameOver = true;

                    break;
                } else if (this.getGameBoard().checkifGameDraw()) {
                    leaderBoard.LeaderBoardIfDraw(playerOne.getName(), playerTwo.getName());
                    System.out.println("Между " + playerOne.getName() + " и " + playerTwo.getName() + "-Победила дружба");
                    configFile.closeAllNodes2();
                    gameOver = true;
                    break;
                }
            }

        } while (!gameOver);


    }


    public static void main(String[] args) throws XMLStreamException {

        System.out.print("""
                ########################
                  ИГРА КРЕСТИКИ-НОЛИКИ\s
                ########################
                """);
        System.out.println("Чтобы сделать ход, необходимо ввести число от 1 до 9 " + "(X- ходят первые)");
        TicTacToeGame info = new TicTacToeGame();
        info.setGameBoard(new TicTacToeBoard(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
        info.getGameBoard().drawBoard();

        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        LeaderBoard leaderBoard = new LeaderBoard();

        Scanner myScan = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Хотите сыграть? y/n (y=да/n=нет)" + " Для просмотра записи побед нажмите l");
            ticTacToeGame.setGameBoard(new TicTacToeBoard(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
            String response = myScan.next();
            if (response.equalsIgnoreCase("n")) {
                System.out.println("Всего доброго!");
                break;
            } else if (response.equalsIgnoreCase("y")) {
                ticTacToeGame.twoPlayer();
            } else if (response.equalsIgnoreCase("l")) {
                leaderBoard.LeaderBoardShowResults();
            } else
                System.out.println("Программа вас не так поняла, " +
                        "пожалуйста введите на английской раскладке буквы y или n");

        }

    }


}

