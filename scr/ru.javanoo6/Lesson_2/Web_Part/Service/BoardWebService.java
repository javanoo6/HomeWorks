package ru.javanoo6.Lesson_2.Web_Part.Service;

import org.springframework.stereotype.Service;
import ru.javanoo6.Lesson_2.Web_Part.Exeptions.InvalidMoveException;
import ru.javanoo6.Lesson_2.Web_Part.PlayerWebModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BoardWebService {
    public Boolean gameOver;
    public Map<String, String> board;

    public BoardWebService() {
        this.board = new HashMap<>();
        this.board.put("1", null);
        this.board.put("2", null);
        this.board.put("3", null);
        this.board.put("4", null);
        this.board.put("5", null);
        this.board.put("6", null);
        this.board.put("7", null);
        this.board.put("8", null);
        this.board.put("9", null);
        this.gameOver = false;
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public void theGameEnd(Boolean isGameOver) {
        this.gameOver = isGameOver;
    }

    public void updateBoard(String position, PlayerWebModel player) {
        if (board.get(position) != null) {
            throw new InvalidMoveException("ячейка уже занята");
        } else board.put(position, player.getSymbol() );
    }

    public String checkIfGameOver() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> board.get("1") + board.get("2") + board.get("3");
                case 1 -> board.get("4") + board.get("5") + board.get("6");
                case 2 -> board.get("7") + board.get("8") + board.get("9");
                case 3 -> board.get("1") + board.get("4") + board.get("7");
                case 4 -> board.get("2") + board.get("5") + board.get("8");
                case 5 -> board.get("3") + board.get("6") + board.get("9");
                case 6 -> board.get("1") + board.get("5") + board.get("9");
                case 7 -> board.get("3") + board.get("5") + board.get("7");
                default -> null;
            };
            if (line.equalsIgnoreCase("xxx")) {
                return "X";
            } else if (line.equalsIgnoreCase("ooo")) {
                return "O";
            }
        }

        if (board.values().stream().filter(Objects::nonNull).collect(Collectors.toList()).size() == 8) {
            return "draw";
        }

        return null;
    }


}