package ru.javanoo6.Lesson_2;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;

public interface GameToFileInterface {
    void writeGame(Player playerOne, Player playerTwo, Player playerTest, Player winner)
            throws ParserConfigurationException, TransformerException, FileNotFoundException, XMLStreamException;
}
