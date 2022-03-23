package Lesson_2.DOMParer;

import Lesson_2.Tests.FIlePathManager;
import Lesson_2.GameToFileInterface;
import Lesson_2.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class DOMWriter extends FIlePathManager implements GameToFileInterface {

    @Override

    public void writeGame(Player playerOne, Player playerTwo, Player playerTest, Player winner) throws ParserConfigurationException, TransformerException, FileNotFoundException {

        String filename = playerOne.getName() + "_VS_" + playerTwo.getName()+".";
        String FULLFILE = DOM_XML_FILEPATH+filename+FileType.xml;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Element elementGamePlay = document.createElement("GamePlay");
        Element elementPlayerOne = document.createElement("Player");
        Element elementPlayerTwo = document.createElement("Player");
        Element elementGame = document.createElement("Game");
        Element elementGameResult = document.createElement("GameResult");
        Element elementPlayer = document.createElement("Player");

        document.appendChild(elementGamePlay);

        elementGamePlay.appendChild(elementPlayerOne);
        elementPlayerOne.setAttribute("id", String.valueOf(playerOne.getId()));
        elementPlayerOne.setAttribute("name", playerOne.getName());
        elementPlayerOne.setAttribute("symbol", playerOne.getSymbol());

        elementGamePlay.appendChild(elementPlayerTwo);
        elementPlayerTwo.setAttribute("id", String.valueOf(playerTwo.getId()));
        elementPlayerTwo.setAttribute("name", playerTwo.getName());
        elementPlayerTwo.setAttribute("symbol", playerTwo.getSymbol());

        elementGamePlay.appendChild(elementGame);
        for (int i = 0; i < playerTest.getSize(); i++) {
            Element elementStep = document.createElement("Step");
            elementGame.appendChild(elementStep);
            elementStep.setAttribute("num", String.valueOf(playerTest.getPlayerStep(i).getCounter()));
            elementStep.setAttribute("playerId", String.valueOf(playerTest.getPlayerStep(i).getId()));
            Text textPos = document.createTextNode(String.valueOf(playerTest.getPlayerStep(i).getPos()));
            elementStep.appendChild(textPos);
        }

        elementGamePlay.appendChild(elementGameResult);
        if (!winner.getName().equals("draw")) {
            elementGameResult.appendChild(elementPlayer);
            elementPlayer.setAttribute("id", String.valueOf(winner.getId()));
            elementPlayer.setAttribute("name", winner.getName());
            elementPlayer.setAttribute("symbol", winner.getSymbol());
        } else {
            Text textDraw = document.createTextNode("Draw!");
            elementGameResult.appendChild(textDraw);
        }

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream(FULLFILE)));

    }

}
