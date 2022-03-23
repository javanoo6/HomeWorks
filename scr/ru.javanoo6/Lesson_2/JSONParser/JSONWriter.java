package ru.javanoo6.Lesson_2.JSONParser;

import ru.javanoo6.Lesson_2.Tests.FIlePathManager;
import ru.javanoo6.Lesson_2.GameToFileInterface;
import ru.javanoo6.Lesson_2.Player;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter extends FIlePathManager implements GameToFileInterface {

    @Override
    public void writeGame(Player playerOne, Player playerTwo, Player playerTest, Player winner) {

        String filename = playerOne.getName() + "_VS_" + playerTwo.getName()+".";
        String FULLFILE = JSON_FILE_PATH+filename+FileType.json;

        JSONObject jObject = new JSONObject();

        JSONObject jGamePlay = new JSONObject();

        JSONObject jplayerOne = new JSONObject();
        jplayerOne.put("_id", String.valueOf( playerOne.getId()));
        jplayerOne.put("_name", playerOne.getName());
        jplayerOne.put("_symbol", playerOne.getSymbol());

        JSONObject jplayerTwo = new JSONObject();
        jplayerTwo.put("_id", String.valueOf(playerTwo.getId()));
        jplayerTwo.put("_name", playerTwo.getName());
        jplayerTwo.put("_symbol", playerTwo.getSymbol());

        JSONArray jPlayers = new JSONArray();
        jPlayers.put(jplayerOne);
        jPlayers.put(jplayerTwo);

        JSONObject jGame = new JSONObject();
        JSONArray jPlayerSteps = new JSONArray();
        for (int i = 0; i < playerTest.getSize(); i++) {
            JSONObject jStep = new JSONObject();
            jStep.put("_num",String.valueOf( playerTest.getPlayerStep(i).getCounter()));
            jStep.put("_playerId",String.valueOf( playerTest.getPlayerStep(i).getId()));
            jStep.put("__text", String.valueOf(playerTest.getPlayerStep(i).getPos()));
            jPlayerSteps.put(jStep);
        }
        jGame.put("Step", jPlayerSteps);

        JSONObject jGameResult = new JSONObject();
        JSONObject jPlayerWinner = new JSONObject();

        if (!winner.getName().equals("draw")) {
            jPlayerWinner.put("_id", String.valueOf( winner.getId()));
            jPlayerWinner.put("_name", winner.getName());
            jPlayerWinner.put("_symbol", winner.getSymbol());

        } else {

            jPlayerWinner.put("", "Draw!");

        }
        jGameResult.put("Player", jPlayerWinner);

        jGamePlay.put("Player", jPlayers);
        jGamePlay.put("Game", jGame);
        jGamePlay.put("GameResult", jGameResult);
        jObject.put("Gameplay", jGamePlay);

        try (FileWriter fileWriter = new FileWriter(FULLFILE ) ) {
            jObject.write(fileWriter, 1, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
