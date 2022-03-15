package Lesson_2.StaxParser;


import Lesson_2.Player;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalTime;

public class StaxWriter {

    public XMLEventWriter eventWriter;
    public XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    public XMLEvent end = eventFactory.createDTD("\n");
    public XMLEvent tab = eventFactory.createDTD("\t");
    public XMLEvent tabtab = eventFactory.createDTD("\t\t");

    private String configFile;


    public void setFile(StaxWriter configFile) {
        this.configFile = gameplay();
    }
    private String gameplay() {
        LocalTime localTime = LocalTime.now();
        return localTime+".xml";
    }
    public void GamePlayNode(Player playerOne, Player playerTwo) throws FileNotFoundException, XMLStreamException {

        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream(configFile));
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);

        StartElement configStartElement = eventFactory.createStartElement("",
                "", "Gameplay");
        eventWriter.add(end);
        eventWriter.add(configStartElement);
        eventWriter.add(end);

        createPlayerNode(playerOne);
        createPlayerNode(playerTwo);

    }


    public void createPlayerNode(Player player) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        StartElement sElement = eventFactory.createStartElement("", "", "Player");
        eventWriter.add(tabtab);
        eventWriter.add(sElement);
        eventWriter.add(eventFactory.createAttribute("id", String.valueOf(player.getId())));
        eventWriter.add(eventFactory.createAttribute("name", player.getName()));
        eventWriter.add(eventFactory.createAttribute("symbol", String.valueOf(player.getSymbol())));
        EndElement eElement = eventFactory.createEndElement("", "", "Player");
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

    public void createGameNode() throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        StartElement sElement = eventFactory.createStartElement("", "", "Game");
        eventWriter.add(tab);
        eventWriter.add(sElement);
        eventWriter.add(end);
    }



    public void closeAllNodes(Player player) throws XMLStreamException {
        eventWriter.add(tab);
        eventWriter.add(eventFactory.createEndElement("", "", "Game"));
        eventWriter.add(end);

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        StartElement sElement = eventFactory.createStartElement("", "", "GameResult");
        eventWriter.add(tab);
        eventWriter.add(sElement);

        eventWriter.add(eventFactory.createAttribute("id", String.valueOf(player.getId())));
        eventWriter.add(eventFactory.createAttribute("name", player.getName()));
        eventWriter.add(eventFactory.createAttribute("symbol", String.valueOf(player.getSymbol())));
        EndElement eElement = eventFactory.createEndElement("", "", "GameResult");
        eventWriter.add(eElement);
        eventWriter.add(end);

        eventWriter.add(eventFactory.createEndElement("", "", "Gameplay"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();

    }

    public void createStepNode(Player player, int moveLocation, int counter) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        StartElement sElement = eventFactory.createStartElement("", "", "Step");
        eventWriter.add(tab);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        eventWriter.add(eventFactory.createAttribute("num", String.valueOf(counter)));
        eventWriter.add(eventFactory.createAttribute("playerId", String.valueOf(player.getId())));
        Characters characters = eventFactory.createCharacters(String.valueOf(moveLocation));
        eventWriter.add(characters);
        EndElement eElement = eventFactory.createEndElement("", "", "Step");
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

    public void closeAllNodes2() throws XMLStreamException {
        eventWriter.add(tab);
        eventWriter.add(eventFactory.createEndElement("", "", "Game"));
        eventWriter.add(end);

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        StartElement sElement = eventFactory.createStartElement("", "", "GameResult");
        eventWriter.add(tab);
        eventWriter.add(sElement);

        Characters characters = eventFactory.createCharacters("Draw!");
        eventWriter.add(characters);

        EndElement eElement = eventFactory.createEndElement("", "", "GameResult");
        eventWriter.add(eElement);
        eventWriter.add(end);

        eventWriter.add(eventFactory.createEndElement("", "", "Gameplay"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();

    }
}
