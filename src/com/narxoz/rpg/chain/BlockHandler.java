package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class BlockHandler extends DefenseHandler {
    private final double blockPercent;

    public BlockHandler(double blockPercent) {
        this.blockPercent = blockPercent;
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        // 1. Қалқанмен қанша уронды қайтарғанымызды есептейміз
        int blockedAmount = (int) (incomingDamage * blockPercent);

        // 2. оставший урон
        int remainder = incomingDamage - blockedAmount;

        if (remainder < 0) {
            remainder = 0;
        }

        // 3. собщения про блок
        System.out.println("[Block] " + target.getName() + " blocked " + blockedAmount +
                " damage (" + (int)(blockPercent * 100) + "% reduction).");

        // 4. отпрвим на хендлер
        super.passToNext(remainder, target);
    }
}