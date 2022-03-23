package Lesson_2;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.List;

public interface GameToFileInterface {
    void writeGame(Player playerOne, Player playerTwo, Player playerTest, Player winner)
            throws ParserConfigurationException, TransformerException, FileNotFoundException, XMLStreamException;
}
