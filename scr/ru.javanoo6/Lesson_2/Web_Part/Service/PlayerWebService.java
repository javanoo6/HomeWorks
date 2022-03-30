package ru.javanoo6.Lesson_2.Web_Part.Service;

import org.springframework.stereotype.Service;
import ru.javanoo6.Lesson_2.Web_Part.Exeptions.PlayerNotFoundException;
import ru.javanoo6.Lesson_2.Web_Part.PlayerWebModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerWebService {

   public List<PlayerWebModel> players = new ArrayList<>(2);


    public List<PlayerWebModel> getPlayers() {
        return players;
    }

    public void POST(String id, String name, String symbol) {
        players.add(new PlayerWebModel(id, name, symbol));
    }

    public void PUT(int test, String id, String name, String symbol) {
        players.set((test-1),new PlayerWebModel(id,name,symbol));
    }

    public void DELETE(int test) {
        players.remove(test-1);
    }

    public PlayerWebModel getPlayer(String player) {
        List<PlayerWebModel> collect = players.stream()
                .filter(e -> e.getId().equals(player))
                .collect(Collectors.toList());
        if (collect.size() > 0) {
            return collect.get(0);
        }
        throw new PlayerNotFoundException("Игрока с " + player + " не найдено");
    }
    public PlayerWebModel getPlayerBySymbol(String player) {
        List<PlayerWebModel> collect = players.stream()
                .filter(e -> e.getSymbol().equalsIgnoreCase(player))
                .collect(Collectors.toList());
        if (collect.size() > 0) {
            return collect.get(0);
        }
        throw new PlayerNotFoundException("Игрока с  " + player + " не найдено");
    }

}
