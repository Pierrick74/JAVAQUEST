package fr.pierrickviret.javaquest.db;

import com.google.gson.*;
import fr.pierrickviret.javaquest.equipement.offensive.*;

import java.lang.reflect.Type;

public class SpellJsonDeserializer implements JsonDeserializer<Spell> {
    public Spell deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();

        switch(name.toLowerCase()) {
            case "eclair":
                return new Lightning();
            case "boule de feu":
                return new Fireball();
            default:
                throw new JsonParseException("Type inconnu : " + name);
        }
    }
}
