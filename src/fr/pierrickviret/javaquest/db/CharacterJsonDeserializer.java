package fr.pierrickviret.javaquest.db;

import com.google.gson.*;
import fr.pierrickviret.javaquest.character.Character;
import fr.pierrickviret.javaquest.character.Dragon;
import fr.pierrickviret.javaquest.character.Gobelin;
import fr.pierrickviret.javaquest.character.Sorcerer;
import java.lang.reflect.Type;

public class CharacterJsonDeserializer implements JsonDeserializer<Character> {
    public Character deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();

        switch(name.toLowerCase()) {
            case "dragon":
                return new Dragon();
            case "gobelin":
                return new Gobelin();
            case "sorcerer":
                return new Sorcerer();
            default:
                throw new JsonParseException("Type inconnu : " + name);
        }
    }
}
