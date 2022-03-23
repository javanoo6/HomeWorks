package Lesson_2.StaxParser;

import Lesson_2.Tests.FIlePathManager;
import Lesson_2.GameToFileInterface;
import Lesson_2.Player;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class StaxWriter extends FIlePathManager implements GameToFileInterface {

    public XMLEventWriter eventWriter;
    public XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    public XMLEvent end = eventFactory.createDTD("\n");
    public XMLEvent tab = eventFactory.createDTD("\t");
    public XMLEvent tabtab = eventFactory.createDTD("\t\t");


    @Override
    public void writeGame(Player playerOne, Player playerTwo, Player playerTest, Player winner)
            throws FileNotFoundException, XMLStreamException {

        String filename = playerOne.getName() + "_VS_" + playerTwo.getName()+".";
        String FULLFILE = STAX_XML_FILEPATH+filename+FileType.xml;


        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream(FULLFILE));
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


        XMLEventFactory eventFactoryGame = XMLEventFactory.newInstance();
        StartElement sElementGame = eventFactoryGame.createStartElement("", "", "Game");
        eventWriter.add(tab);
        eventWriter.add(sElementGame);
        eventWriter.add(end);


        for (int i = 0; i < playerTest.getSize(); i++) {

            XMLEventFactory eventFactoryStep = XMLEventFactory.newInstance();
            StartElement sElementStep = eventFactoryStep.createStartElement("", "", "Step");
            eventWriter.add(tab);
            eventWriter.add(tab);
            eventWriter.add(sElementStep);
            eventWriter.add(eventFactoryStep.createAttribute("num", String.valueOf(playerTest.getPlayerStep(i).getCounter())));
            eventWriter.add(eventFactoryStep.createAttribute("playerId", String.valueOf(playerTest.getPlayerStep(i).getId())));
            Characters characters = eventFactoryStep.createCharacters(String.valueOf(playerTest.getPlayerStep(i).getPos()));
            eventWriter.add(characters);
            EndElement eElementStep = eventFactoryStep.createEndElement("", "", "Step");
            eventWriter.add(eElementStep);
            eventWriter.add(end);

        }

        eventWriter.add(tab);
        eventWriter.add(eventFactory.createEndElement("", "", "Game"));
        eventWriter.add(end);

        XMLEventFactory eventFactoryGameResult = XMLEventFactory.newInstance();

        StartElement sElementGameRes = eventFactoryGameResult.createStartElement("", "", "GameResult");
        eventWriter.add(tab);
        eventWriter.add(sElementGameRes);

        if (winner.getName().equals("draw")) {
            Characters characters = eventFactory.createCharacters("Draw!");
            eventWriter.add(characters);
        } else {
            StartElement sElement3 = eventFactory.createStartElement("", "", "Player");

            eventWriter.add(sElement3);
            eventWriter.add(eventFactory.createAttribute("id", String.valueOf(winner.getId())));
            eventWriter.add(eventFactory.createAttribute("name", winner.getName()));
            eventWriter.add(eventFactory.createAttribute("symbol", String.valueOf(winner.getSymbol())));
            EndElement eElement2 = eventFactory.createEndElement("", "", "Player");
            eventWriter.add(eElement2);
        }

        EndElement eElement = eventFactory.createEndElement("", "", "GameResult");
        eventWriter.add(eElement);
        eventWriter.add(end);

        eventWriter.add(eventFactory.createEndElement("", "", "Gameplay"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();


    }

    private void createPlayerNode(Player playerOneTwo) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        StartElement sElement = eventFactory.createStartElement("", "", "Player");
        eventWriter.add(tab);
        eventWriter.add(sElement);

        eventWriter.add(eventFactory.createAttribute("id", String.valueOf(playerOneTwo.getId())));
        eventWriter.add(eventFactory.createAttribute("name", playerOneTwo.getName()));
        eventWriter.add(eventFactory.createAttribute("symbol", String.valueOf(playerOneTwo.getSymbol())));
        EndElement eElement2 = eventFactory.createEndElement("", "", "Player");
        eventWriter.add(eElement2);
        eventWriter.add(end);
    }

}
