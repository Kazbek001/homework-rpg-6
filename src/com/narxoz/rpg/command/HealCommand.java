package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class HealCommand implements ActionCommand {
    private final ArenaFighter target;
    private final int healAmount;
    private int actualHealApplied;

    public HealCommand(ArenaFighter target, int healAmount) {
        this.target = target;
        this.healAmount = healAmount;
    }

    @Override
    public void execute() {
        // 1. Емделмес бұрын қазіргі денсаулықты сақтап аламыз
        int initialHealth = target.getHealth();

        // 2. Батырды емдейміз (ArenaFighter ішіндегі heal әдісі банканың бар-жоғын өзі тексереді)
        target.heal(healAmount);

        // 3. Нақты қанша HP қосылғанын есептейміз
        // Мысалы: Max HP 100 болса, ал батырда 90 HP болса, 20 HP емдегенде тек 10 HP қосылады.
        this.actualHealApplied = target.getHealth() - initialHealth;

        if (actualHealApplied > 0) {
            System.out.println("[Command] Executed Heal: " + target.getName() + " healed for " + actualHealApplied + " HP.");
        } else {
            System.out.println("[Command] Executed Heal: " + target.getName() + " tried to heal, but failed (no potions or already at max HP).");
        }
    }

    @Override
    public void undo() {
        // Егер расымен де өмір қосылған болса, оны кері қайтарамыз (takeDamage арқылы)
        if (actualHealApplied > 0) {
            target.takeDamage(actualHealApplied);
            System.out.println("[Command] Undone Heal: Removed " + actualHealApplied + " HP from " + target.getName() + ".");
            actualHealApplied = 0;
            // Ескерту: Толыққанды ойында біз бұл жерде қолданылған банканы (potion) да қайтаруымыз керек еді.
            // Бірақ тапсырма шарты бойынша тек HP-ді кері қайтару жеткілікті.
        }
    }

    @Override
    public String getDescription() {
        return "Heal for " + healAmount + " HP";
    }
}