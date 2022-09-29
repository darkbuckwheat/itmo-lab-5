package Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringJoiner;

import Classes.Coordinates;
import Classes.Dragon;
import Classes.DragonCave;

import JSONstaff.CaveSerializer;
import JSONstaff.CaveDeserializer;
import JSONstaff.DragonSerializer;
import JSONstaff.DragonDeserializer;
import JSONstaff.CollectionSerializer;
import JSONstaff.CoordinatesDeserializer;
import JSONstaff.CoordinatesSerializer;
import JSONstaff.CollectionDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parser {
    private Parser() {
    }

    public static CollectionManager convertToJavaObject(File file) throws FileNotFoundException{
        Scanner scan = new Scanner(file);
        StringJoiner data = new StringJoiner("");
        while (scan.hasNextLine()){
            data.add(scan.nextLine());
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(CollectionManager.class, new CollectionDeserializer())
                .registerTypeAdapter(Dragon.class, new DragonDeserializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
                .registerTypeAdapter(DragonCave.class, new CaveDeserializer())
                .create();
        CollectionManager result = gson.fromJson(data.toString(), CollectionManager.class);
        result.setFilePath(file.getPath());
        return result;
    }

    public static String convertToJSON(CollectionManager data){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(CollectionManager.class, new CollectionSerializer())
                .registerTypeAdapter(Dragon.class, new DragonSerializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerializer())
                .registerTypeAdapter(DragonCave.class, new CaveSerializer())
                .create();
        return gson.toJson(data);
    }
}