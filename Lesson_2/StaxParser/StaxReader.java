package Lesson_2.StaxParser;

import Lesson_2.Player;
import Lesson_2.TicTacToeBoard;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;

public class StaxReader {


    static String fileDirectory;
    static File file;


    public static void main(String[] args) throws  IOException {
        System.out.println("Введите абсолютный путь к файлу записанной игры");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fileDirectory = br.readLine();
        file = new File(fileDirectory);
        while (!file.exists()) {
            System.out.println("Вы указали не правильно абсолютный путь к файлу, либо такого файла не существует" +
                    "\nПожалуйста, повторите ввод");
            fileDirectory = br.readLine();
            file = new File(fileDirectory);
        }
        staxFileReader(file);
        br.close();
    }

    static Player player = new Player();
    static TicTacToeBoard ttb = new TicTacToeBoard();

    public static void staxFileReader(File file) throws FileNotFoundException {
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

                        System.out.println("результат игры: "+reader.next());

                    }

                }
            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }



    }


}


