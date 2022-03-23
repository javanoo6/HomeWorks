package ru.javanoo6.Lesson_2.Tests;

import ru.javanoo6.Lesson_2.Player;
import ru.javanoo6.Lesson_2.TicTacToeBoard;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Данный ридер умеет парсить Stax и DOM XML файлы
 */

public class XMLToGame implements FileToGameInterface {

    static Player player = new Player();
    static TicTacToeBoard ttb = new TicTacToeBoard();

    @Override
    public void reader(File file) throws FileNotFoundException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().getLocalPart().equals("Player")) {
                        Attribute attribute = startElement.getAttributeByName(new QName("id"));
                        player.setId(Integer.parseInt(attribute.getValue()));
                        attribute = startElement.getAttributeByName(new QName("name"));
                        player.setName(attribute.getValue());
                        attribute = startElement.getAttributeByName(new QName("symbol"));
                        player.setSymbol(attribute.getValue());
                        System.out.println("ID участника: " + player.getId() + "," + " Имя участника: " + player.getName() + "," + " Символ, которым ходит участник: " + player.getSymbol());
                    }
                    if (startElement.getName().getLocalPart().equals("Step")) {
                        Attribute attribute = startElement.getAttributeByName(new QName("num"));

                        Attribute attribute2 = startElement.getAttributeByName(new QName("playerId"));
                        event = reader.nextEvent();
                        int stringStep = Integer.parseInt(event.asCharacters().getData());
                        ttb.placePiece(attribute, attribute2, stringStep);
                    }
                    if (startElement.getName().getLocalPart().equals("GameResult")) {

                        System.out.println("результат игры: " + reader.next());

                    }

                }
            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

}

