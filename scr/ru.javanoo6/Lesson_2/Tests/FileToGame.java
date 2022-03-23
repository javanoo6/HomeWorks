package ru.javanoo6.Lesson_2.Tests;

import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileToGame {
    static JSONToGame jsonToGame = new JSONToGame();
    static XMLToGame xmlToGame = new XMLToGame();
    static FileToGameInterface fileToGameInterface;
    static String fileDirectory;
    static File file;

    /**
     * Этот класс сделан для тестирования уже записанных файлов JSON / XML
     * Запускаемые отсюда классы: JSONToGame / XMLToGame
     * с помощью интерфейса FileToGameInterface
     */
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Введите абсолютный путь к файлу записанной игры");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fileDirectory = br.readLine();
        file = new File(fileDirectory);
        while (!file.exists()) {
            System.out.println("Вы указали не правильно абсолютный путь к файлу, либо такого файла не существует" +
                    "\nПожалуйста, повторите ввод");
            fileDirectory = br.readLine();
            file = new File(fileDirectory);
        }
        if (fileDirectory.contains(".xml")) {
            fileToGameInterface = xmlToGame;
            fileToGameInterface.reader(file);
        }
        if (fileDirectory.contains(".json")) {
            fileToGameInterface = jsonToGame;
            fileToGameInterface.reader(file);
        }

    }

}
