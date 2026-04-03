package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class DefendCommand implements ActionCommand {
    private final ArenaFighter target;
    private final double dodgeBoost;
    private boolean executed = false;

    public DefendCommand(ArenaFighter target, double dodgeBoost) {
        this.target = target;
        this.dodgeBoost = dodgeBoost;
    }

    @Override
    public void execute() {
        // Батырдың жалтару (dodge) мүмкіндігін уақытша арттырамыз
        target.modifyDodgeChance(dodgeBoost);
        this.executed = true;
        System.out.println("[Command] Defend: " + target.getName() + " boosted dodge chance by " + (dodgeBoost * 100) + "%.");
    }

    @Override
    public void undo() {
        if (executed) {
            target.modifyDodgeChance(-dodgeBoost);
            this.executed = false;
            System.out.println("[Command] Undo Defend: Removed dodge boost from " + target.getName() + ".");
        }
    }

    @Override
    public String getDescription() {
        // обьеснение
        return "Defend (dodge boost: +" + (int)(dodgeBoost * 100) + "%)";
    }
}