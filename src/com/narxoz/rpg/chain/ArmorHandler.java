package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class ArmorHandler extends DefenseHandler {
    private final int armorValue;

    public ArmorHandler(int armorValue) {
        this.armorValue = armorValue;
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        int reducedDamage = incomingDamage - armorValue;

        if (reducedDamage < 0) {
            reducedDamage = 0;
        }

        int absorbed = incomingDamage - reducedDamage;

        System.out.println("[Armor] " + target.getName() + "'s armor absorbed " + absorbed + " damage.");

        super.passToNext(reducedDamage, target);
    }
}