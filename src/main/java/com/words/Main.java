package com.words;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// read and write
import java.io.FileReader;
import java.io.FileWriter;

// collections
import java.util.*;
import java.util.List;

// path
import java.nio.file.Path;

//charset
import java.nio.charset.StandardCharsets;

// typetoken
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;



public class Main {
    public static void main(String[] args) throws Exception{ // throws Exception ради отсутствия unreported exception java.io.IOException; must be caught or declared to be thrown в компиляции
        Config config;
        List<String> dict;
        List<String> main_words;

        Gson gson = new GsonBuilder().create();
        Path base = Path.of("").toAbsolutePath();

        // for reading List<String>
        Type listType = new TypeToken<List<String>>() {}.getType();


        try(FileReader reader = new FileReader(base.resolve("config.json").toFile(),StandardCharsets.UTF_8)) {
            config = gson.fromJson(reader,Config.class);
            System.out.println("config.json - success readed");
        }
        try(FileReader reader = new FileReader(base.resolve("data/main_words.json").toFile(), StandardCharsets.UTF_8)) {
            main_words = gson.fromJson(reader,listType);
            System.out.println("main_words.json - success readed");
        }
        try(FileReader reader = new FileReader(base.resolve("data/dict.json").toFile(), StandardCharsets.UTF_8)) {
            dict = gson.fromJson(reader,listType);
            System.out.println("dictionary.json - success readed");
        }


    }
}