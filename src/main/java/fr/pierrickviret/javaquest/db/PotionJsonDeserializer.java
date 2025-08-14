package fr.pierrickviret.javaquest.db;

import com.google.gson.*;
import fr.pierrickviret.javaquest.equipement.defensive.BigPotion;
import fr.pierrickviret.javaquest.equipement.defensive.Potion;
import fr.pierrickviret.javaquest.equipement.defensive.SmallPotion;
import fr.pierrickviret.javaquest.equipement.defensive.Thunderclap;

import java.lang.reflect.Type;

public class PotionJsonDeserializer implements JsonDeserializer<Potion> {
    public Potion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();

        return switch (name.toLowerCase()) {
            case "potion de vie standard" -> new SmallPotion();
            case "grande potion" -> new BigPotion();
            case "potion coup de tonnerre" -> new Thunderclap();
            default -> throw new JsonParseException("Type inconnu : " + name);
        };
    }
}