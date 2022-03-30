## HomeWorks
### Данный репозиторий содержит решения задач стажировки

- Игра запускается из класса TicTacToeGame

- Парсеры находятся в пакетах:
  - DOMParser
  - StaxParser
  - JSONParser
- Игра записывается в 3 пакета:
  - DOM_XML_FILES
  - JSON_FILES
  - STAX_XML_FILES
- Записанные игры можно воспроизвести из:
  - JSONReader
  - StaxReader
  - DOMReader 
- Пакет Tests содержит 
  - FilePathManager (он содержит пути куда именно будет записаны игры)
  - FileToGame (служит для воспроизведения любой записанной игры из выше упомянутых папок)
   
## Крестики-нолики на REST API
### Игра запускается из ApplicationStart
## ENDPOINTS:
## POST/players - с этого начинается игра, так как необходимо задать игроков и их параметры
### Пример описания двух игроков:
```json
{
    "id": "1",
    "name": "nameOne",
    "symbol": "X"
}
```
```json
{
    "id": "2",
    "name": "nameTwo",
    "symbol": "O"
}
```
### Важно: символы задаются на английской раскладке X и O
## GET/players будет выглядеть так
```json
{
    "id": "1",
    "name": "nameOne",
    "symbol": "X"
},
{
    "id": "2",
    "name": "nameTwo",
    "symbol": "O"
}
```
# Если хотите изменить игрока, то воспользуйтесь 
## PUT/players/1
либо
## PUT/players/2
где 1 и 2 это номера последовательности, в которой создавались игроки

как пример

было:

```json
{
    "id": "1",
    "name": "nameOne",
    "symbol": "X"
},
{
    "id": "2",
    "name": "nameTwo",
    "symbol": "O"
}
```
PUT/players/1
необходимо ввести:
```json
{
    "id": "1",
    "name": "nameOne-AfterChange",
    "symbol": "X"
}
```

стало:
```json

{
    "id": "1",
    "name": "nameOne-AfterChange",
    "symbol": "X"
},
{
    "id": "2",
    "name": "nameTwo",
    "symbol": "O"
}
```
## DELETE/players/1
либо
## DELETE/players/2
данным endpoint можно удалить первого или второго игрока

## POST/turn
для того, чтобы играть, необходимо передавать request в таком виде
```json
{
    "playerId": "1",
    "position": 5
}
```
либо
```json
{
    "playerId": "2",
    "position": 3
}
```
вы увидите:
```json
{
    "isGameOver": false,
    "winner": null,
    "gameBoard": {
        "1": null,
        "2": null,
        "3": null,
        "4": null,
        "5": "X",
        "6": null,
        "7": null,
        "8": null,
        "9": null
    }
}
```
(ходите, пожалуйста, по очереди)
## GET/state
выдаст вам
```json
"gameBoard": {
    "1": null,
    "2": null,
    "3": null,
    "4": null,
    "5": "X",
    "6": null,
    "7": null,
    "8": null,
    "9": null
}
```
