package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaOpponent;

public class AttackCommand implements ActionCommand {
    private final ArenaOpponent target;
    private final int attackPower;
    private int damageDealt;

    public AttackCommand(ArenaOpponent target, int attackPower) {
        this.target = target;
        this.attackPower = attackPower;
    }

    @Override
    public void execute() {

        int initialHealth = target.getHealth();

        // do attack
        target.takeDamage(attackPower);


        this.damageDealt = initialHealth - target.getHealth();

        System.out.println("[Command] Executed Attack: " + target.getName() + " lost " + damageDealt + " HP.");
    }

    @Override
    public void undo() {
        if (damageDealt > 0) {
            target.restoreHealth(damageDealt);
            System.out.println("[Command] Undone Attack: Restored " + damageDealt + " HP to " + target.getName() + ".");
            damageDealt = 0;
        }
    }

    @Override
    public String getDescription() {
        return "Attack " + target.getName() + " for " + attackPower + " power";
    }
}