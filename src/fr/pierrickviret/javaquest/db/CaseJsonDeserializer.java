package fr.pierrickviret.javaquest.db;

import com.google.gson.*;
import fr.pierrickviret.javaquest.board.Case.*;
import fr.pierrickviret.javaquest.character.Character;

import java.lang.reflect.Type;

/**
 * permet de decoder le JSON de board
 */
public class CaseJsonDeserializer implements JsonDeserializer<Case> {
    public Case deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonNull()) {
            return new EmptyCase();
        }

        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.isEmpty()) {
            return new EmptyCase();
        }

        if (jsonObject.has("enemy")) {
            Character enemy = context.deserialize(jsonObject.get("enemy"), Character.class);
            return new EnemyCase(enemy);

        } else if (jsonObject.has("weapon")) {
            return context.deserialize(jsonObject.get("weapon"), WeaponCase.class);
        } else if (jsonObject.has("potion")) {
            return context.deserialize(jsonObject.get("potion"), PotionCase.class);
        } else if (jsonObject.has("spell")) {
            return context.deserialize(jsonObject.get("spell"), SpellCase.class);
        } else {
            return new EmptyCase();
        }
    }
}