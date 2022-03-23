package Lesson_2.Tests;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public interface FileToGameInterface {
    void reader(File file) throws IOException, ParseException;
}
