package Lesson_2;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {


    private TicTacToeBoard gameBoard;


    public TicTacToeGame() {
        this.gameBoard = new TicTacToeBoard();
    }

    public TicTacToeBoard getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(TicTacToeBoard gameBoard) {
        this.gameBoard = gameBoard;
    }


    public void twoPlayer() throws IOException {
        Scanner sc = new Scanner(System.in);

        Player playerOne = new Player("X");
        Player playerTwo = new Player("O");

        Player[] players = new Player[]{playerOne, playerTwo};

        System.out.print("Введите имя первого участника: ");
        playerOne.setName(sc.next());
        System.out.print("Введите имя второго участника: ");
        playerTwo.setName(sc.next());

        this.getGameBoard().drawBoard();
        boolean gameOver = false;
        do {
            for (Player player : players) {
                System.out.println(player.getName() + ", Ваш ход " + "(вы играете " + player.getSymbol() + "-ми)");

                player.makeMove(this.getGameBoard());
                this.getGameBoard().drawBoard();

                if (this.getGameBoard().checkIfGameOver()) {
                    FileWriter fileWriter = new FileWriter("/Users/user_nick/IdeaProjects/Y_LabHomeworks/src/Lesson_2/LeaderBoard",true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.newLine();
                    bufferedWriter.write(player.getName() + " - Победитель");
                    bufferedWriter.flush();

                    System.out.println(player.getName() + " Победитель");
                    gameOver = true;

                    break;
                } else if (this.getGameBoard().checkifGameDraw()) {
                    System.out.println("Победила дружба");
                    gameOver = true;
                    break;
                }
//
            }
        } while (!gameOver);

    }


    public static void main(String[] args) throws IOException {

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


        Scanner myScan = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Хотите сыграть? y/n (y=да/n=нет)"+" Для просмотра записи побед нажмите l");
            ticTacToeGame.setGameBoard(new TicTacToeBoard(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
            String response = myScan.next();
            if (response.equalsIgnoreCase("n")) {
                System.out.println("Всего доброго!");
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                ticTacToeGame.twoPlayer();
            }else if(response.equalsIgnoreCase("l")){
                 FileReader fileReader = new FileReader("/Users/user_nick/IdeaProjects/Y_LabHomeworks/src/Lesson_2/LeaderBoard");
                char[] buf = new char[256];
                int c;
                while((c = fileReader.read(buf))>0){

                    if(c < 256){
                        buf = Arrays.copyOf(buf, c);
                    }
                    System.out.print(buf);
                }
            }else
                System.out.println("Программа вас не так поняла, " +
                        "пожалуйста введите на английской раскладке буквы y или n");

        }

    }
}

