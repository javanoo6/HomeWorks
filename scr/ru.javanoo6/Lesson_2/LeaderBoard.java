package ru.javanoo6.Lesson_2;

import java.io.*;

public class LeaderBoard {
    private static final String FILENAME = "./src/main/java/Lesson_2/LeaderBoard";

    public void LeaderBoardIfWinner(String name) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILENAME, true))) {
            bufferedWriter.newLine();
            bufferedWriter.write(name + " -Победитель");
            bufferedWriter.flush();

        } catch (IOException e) {
            System.out.println("имя файла задано не верно либо файла не существует");
        }
    }

    public void LeaderBoardIfDraw(String player1, String player2) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILENAME, true))) {
            bufferedWriter.newLine();
            bufferedWriter.write("между" + player1 + " и " + player2 + " -Победила дружба");
            bufferedWriter.flush();

        } catch (IOException e) {
            System.out.println("имя файла задано не верно либо файла не существует");
        }
    }

    public void LeaderBoardShowResults() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String strCurrentLine;

            while ((strCurrentLine = bufferedReader.readLine()) != null) {
                System.out.println(strCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
