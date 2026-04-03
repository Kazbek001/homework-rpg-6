package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public abstract class DefenseHandler {
    private DefenseHandler next;

    public DefenseHandler setNext(DefenseHandler next) {
        // Fluent setter: тізбекті dodge.setNext(block).setNext(armor) түрінде құруға мүмкіндік береді.
        this.next = next;
        return next;
    }

    protected DefenseHandler getNext() {
        return next;
    }

    protected void passToNext(int damage, ArenaFighter target) {
        if (next != null && damage > 0) {
            next.handle(damage, target);
        } else if (next == null && damage > 0) {
            System.out.println("[System] Warning: Damage " + damage + " leaked through the defense chain!");
        }
    }

    public abstract void handle(int incomingDamage, ArenaFighter target);
}