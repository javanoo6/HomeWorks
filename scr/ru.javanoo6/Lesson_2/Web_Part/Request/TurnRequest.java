package ru.javanoo6.Lesson_2.Web_Part.Request;
//import javax.validation.constraints.*;

import javax.validation.constraints.*;

public class TurnRequest {
    @Size(max=1,message = "вводимое значение символа либо 1 либо 2")
    @NotBlank
    private String playerId;

    @Max(9)
    @Min(1)
    @NotNull
    private Integer position;

    @Override
    public String toString() {
        return "TurnRequest{" +
                "playerId='" + playerId + '\'' +
                ", position=" + position +
                '}';
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
