package ru.javanoo6.Lesson_2.Tests;

import ru.javanoo6.Lesson_2.TicTacToeBoard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONToGame implements FileToGameInterface {

    static TicTacToeBoard ttb = new TicTacToeBoard();

    @Override
    public void reader(File file) throws IOException, ParseException {

            JSONParser parser = new JSONParser();

            JSONObject jObject;

            try (FileReader reader = new FileReader(file)) {
                jObject = (JSONObject) parser.parse(reader);
            }

            JSONObject jGameplay = (JSONObject) jObject.get("Gameplay");
            JSONArray jArrayPlayer = (JSONArray) jGameplay.get("Player");

            for (Object o : jArrayPlayer) {
                JSONObject player = (JSONObject) o;
                if (player.get("_id").equals("1")) {
                    String name = (String) player.get("_name");
                    String symbol = (String) player.get("_symbol");
                    String id = (String) player.get("_id");

                    System.out.println("ID участника: " + id + ", имя участника №1: " + name + " символ, которым ходит учатстник: " + symbol);
                } else {
                    String name = (String) player.get("_name");
                    String symbol = (String) player.get("_symbol");
                    String id = (String) player.get("_id");

                    System.out.println("ID участника: " + id + ", имя участника №2: " + name + " символ, которым ходит учатстник: " + symbol);

                }
            }
            System.out.println("\nХод записанной игры: \n");

            JSONObject jGame = (JSONObject) jGameplay.get("Game");

            JSONArray jArrayStep = (JSONArray) jGame.get("Step");

            for (Object o : jArrayStep) {
                JSONObject jsonStep = (JSONObject) o;
                Integer playerId = Integer.valueOf((String) jsonStep.get("_playerId"));
                Integer num = Integer.valueOf((String) jsonStep.get("_num"));
                String xy = (String) jsonStep.get("__text");
                ttb.placePiece(playerId, num, xy);
            }

            JSONObject jGameResult = (JSONObject) jGameplay.get("GameResult");

            JSONObject jPlayer = (JSONObject) jGameResult.get("Player");

            if (jPlayer.size() != 1) {
                String name = (String) jPlayer.get("_name");
                String symbol = (String) jPlayer.get("_symbol");
                String id = (String) jPlayer.get("_id");
                System.out.println("победитель: " + name + ", чей ID: " + id + ", символ которым ходил игрок: " + symbol);
            }else System.out.println("Draw!");

        }
    }

