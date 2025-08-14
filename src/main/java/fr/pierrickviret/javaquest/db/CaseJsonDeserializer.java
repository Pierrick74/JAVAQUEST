package fr.pierrickviret.javaquest.db;

import com.google.gson.*;
import fr.pierrickviret.javaquest.board.Case.*;
import fr.pierrickviret.javaquest.character.Character;
import fr.pierrickviret.javaquest.equipement.defensive.Potion;
import fr.pierrickviret.javaquest.equipement.offensive.Spell;
import fr.pierrickviret.javaquest.equipement.offensive.Weapon;

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
            Weapon weapon = context.deserialize(jsonObject.get("weapon"), Weapon.class);
            return new WeaponCase(weapon);
        } else if (jsonObject.has("potion")) {
            Potion potion = context.deserialize(jsonObject.get("potion"), Potion.class);
            return new PotionCase(potion);
        } else if (jsonObject.has("spell")) {
            Spell spell = context.deserialize(jsonObject.get("spell"), Spell.class);
            return new SpellCase(spell);
        } else {
            return new EmptyCase();
        }
    }
}