package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class HangmanDictionary {

    final static String word = getRandomWord();

    final static String definition = getDefinitionforRandomWord();
    String line;
    final static String filePath
            = "src/main/resources/russianNounsWithDefinition.txt";

    public static Map<String, String> createDictionaryFromFile(String path){
        Map<String, String> dictionary = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.filter(line -> line.contains(":"))
                    .forEach(line -> {
                                String[] keyValuePair = line.split(":", 2);
                                String key = keyValuePair[0];
                                String value = keyValuePair[1];
                        dictionary.put(key, value);
                            }
                    );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
}

    public static String getRandomWord(){
        Map<String, String> dictionary = createDictionaryFromFile(filePath);
        List<String> wordsAsArray = new ArrayList<>(dictionary.keySet());
        Random r = new Random();
        return wordsAsArray.get(r.nextInt(wordsAsArray.size()));
    }

    public static String getDefinitionforRandomWord(){
        Map<String, String> dictionary = createDictionaryFromFile(filePath);
        List<String> wordsAsArray = new ArrayList<>(dictionary.keySet());
        Random r = new Random();
        return dictionary.get(word);
    }
}
