package fr.pierrickviret.javaquest.db;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import fr.pierrickviret.javaquest.board.Case.*;

import java.lang.reflect.Type;

/**
 * permet de decoder le JSON de board
 */
public class CaseJsonDeserializer implements JsonDeserializer<Case> {
    public Case deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.getAsJsonObject().get("cases") == null) {
            return context.deserialize(json, EmptyCase.class);
        }

        String type = json.getAsJsonObject().get("cases").getAsString();

        switch(type) {
            case "enemy":
                return context.deserialize(json, EnemyCase.class);
            case "weapon":
                return context.deserialize(json, WeaponCase.class);
            case "potion":
                return context.deserialize(json, PotionCase.class);
            case "spell":
                return context.deserialize(json, SpellCase.class);
            default:
                return context.deserialize(json, EmptyCase.class);
        }
    }
}
