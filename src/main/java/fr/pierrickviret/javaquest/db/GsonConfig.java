package fr.pierrickviret.javaquest.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.pierrickviret.javaquest.board.Case.Case;
import fr.pierrickviret.javaquest.character.Character;
import fr.pierrickviret.javaquest.equipement.defensive.Potion;
import fr.pierrickviret.javaquest.equipement.offensive.Spell;
import fr.pierrickviret.javaquest.equipement.offensive.Weapon;


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
                            .registerTypeAdapter(Weapon.class, new WeaponJsonDeserializer())
                            .registerTypeAdapter(Potion.class, new PotionJsonDeserializer())
                            .registerTypeAdapter(Spell.class, new SpellJsonDeserializer())
                            .create();
                }
            }
        }
        return instance;
    }
}