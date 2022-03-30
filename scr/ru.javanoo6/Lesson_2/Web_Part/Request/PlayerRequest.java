package ru.javanoo6.Lesson_2.Web_Part.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PlayerRequest {
    @Size(max=1,message="вводимый ID должен быть либо 1 либо 2")
    @NotBlank
    String id;
    @NotBlank
    String name;
    @Size(max = 1,message = "вводимое значение символа должно быть либо X(eng) либо 0(zero) ")
    @NotBlank
    String symbol;

    @Override
    public String toString() {
        return "PlayerCreateRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }

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
}
