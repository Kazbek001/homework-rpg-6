package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class HpHandler extends DefenseHandler {

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        target.takeDamage(incomingDamage);

        // 2. выводим урон на экран
        System.out.println("[HP] " + target.getName() + " took " + incomingDamage + " direct damage to health.");


    }
}