package fr.pierrickviret.javaquest.db;

import com.google.gson.*;
import fr.pierrickviret.javaquest.equipement.offensive.Club;
import fr.pierrickviret.javaquest.equipement.offensive.Sword;
import fr.pierrickviret.javaquest.equipement.offensive.Weapon;

import java.lang.reflect.Type;

public class WeaponJsonDeserializer implements JsonDeserializer<Weapon> {
    public Weapon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();

        switch(name.toLowerCase()) {
            case "massue":
                return new Club();
            case "epée":
                return new Sword();
            default:
                throw new JsonParseException("Type inconnu : " + name);
        }
    }
}
