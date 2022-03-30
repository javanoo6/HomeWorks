package ru.javanoo6.Lesson_2.Web_Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javanoo6.Lesson_2.Web_Part.Request.PlayerRequest;
import ru.javanoo6.Lesson_2.Web_Part.Request.TurnRequest;
import ru.javanoo6.Lesson_2.Web_Part.Service.BoardWebService;
import ru.javanoo6.Lesson_2.Web_Part.Service.PlayerWebService;

import java.util.List;
import java.util.Map;

@RestController
public class WebController {


    private PlayerWebService playersService;
    private BoardWebService boardService;
    private PlayerWebModel playerModel;
    @Autowired
    public WebController(PlayerWebService playersService,BoardWebService boardService,PlayerWebModel playerModel) {
        this.playersService = playersService;
        this.boardService=boardService;
        this.playerModel=playerModel;
    }

    @GetMapping("/players")
    public List<PlayerWebModel> GET(@RequestParam(value = "id", defaultValue = "") String id) {
        return playersService.getPlayers();
    }

    @PostMapping("/players")
    public List<PlayerWebModel> POST(@RequestBody PlayerRequest request){
        playersService.POST(request.getId(),request.getName(),request.getSymbol());
        return playersService.getPlayers();
    }

    @PutMapping("/players/{id}")
    public List<PlayerWebModel> PUT(@PathVariable(value="id") int test, @RequestBody PlayerRequest request){
        playersService.PUT(test,request.getId(),request.getName(),request.getSymbol());
        return playersService.getPlayers();

    }
    @DeleteMapping("/players/{id}")
    public List<PlayerWebModel> DELETE(@PathVariable(value="id") int test){
        playersService.DELETE(test);
        return playersService.getPlayers();
    }

    @PostMapping("/turn")
    public TurnResponse turn(@RequestBody TurnRequest turnRequest) {
        if (boardService.isGameOver()) {
            System.out.println("Game is already over!");
        } else {
            boardService.updateBoard(String.valueOf(turnRequest.getPosition()), playersService.getPlayer(turnRequest.getPlayerId()));
        }
        return new TurnResponse(playerModel.findWinner(), boardService.board);
    }

    @GetMapping("/state")
    public Map<String, String> getState() {
        return boardService.board;
    }


}
/*
{
	"id" : "1",
	"name" : "nick",
    "symbol":"X"
}
 */



