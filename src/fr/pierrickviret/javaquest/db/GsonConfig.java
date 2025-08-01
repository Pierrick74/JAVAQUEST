package fr.pierrickviret.javaquest.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.pierrickviret.javaquest.board.Case.Case;
import fr.pierrickviret.javaquest.character.Character;


public class GsonConfig {
    private static Gson instance;

    private GsonConfig() {
    }

    public static Gson getInstance() {
        if (instance == null) {
            synchronized (GsonConfig.class) {
                if (instance == null) {
                    instance = new GsonBuilder()
                            .registerTypeAdapter(Case.class, new CaseJsonDeserializer())
                            .registerTypeAdapter(Character.class, new CharacterJsonDeserializer())
                            .create();
                }
            }
        }
        return instance;
    }
}