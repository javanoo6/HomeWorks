package ru.javanoo6.Lesson_2.Web_Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javanoo6.Lesson_2.Web_Part.Service.BoardWebService;
import ru.javanoo6.Lesson_2.Web_Part.Service.PlayerWebService;
@Service
public class PlayerWebModel {
    private PlayerWebService playersService;
    private BoardWebService boardService;

    @Autowired
    public PlayerWebModel(PlayerWebService playersService,BoardWebService boardService){
        this.playersService=playersService;
        this.boardService=boardService;
    }

    public PlayerWebModel(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }
    private String id;
    private String name;
    private String symbol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public PlayerWebModel findWinner() {
        String winner = boardService.checkIfGameOver();
        PlayerWebModel playerWinner;

        if (winner != null) {
            switch (winner) {
                case "X":
                case "O":
                    playerWinner = playersService.getPlayerBySymbol(winner);
                    boardService.theGameEnd(true);
                    break;
                case "draw":
                    playerWinner = new PlayerWebModel("ничья", "-победила дружба","!");
                    boardService.theGameEnd(true);
                    break;
                default:
                    playerWinner = null;
            }
            return playerWinner;
        }

        return null;
    }

}
