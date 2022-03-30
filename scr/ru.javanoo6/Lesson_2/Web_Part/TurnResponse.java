package ru.javanoo6.Lesson_2.Web_Part;

import java.util.Map;

public class TurnResponse {
    public Boolean isGameOver = false;
    public PlayerWebModel winner;
    public Map<String, String> gameBoard;

    public TurnResponse(PlayerWebModel winner, Map<String, String> gameBoard) {
        if(winner != null) {
            this.winner = winner;
            this.isGameOver = true;
        }
        this.gameBoard = gameBoard;
    }

}



