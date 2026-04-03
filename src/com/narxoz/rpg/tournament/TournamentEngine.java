package com.narxoz.rpg.tournament;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;
import com.narxoz.rpg.arena.TournamentResult;
import com.narxoz.rpg.chain.ArmorHandler;
import com.narxoz.rpg.chain.BlockHandler;
import com.narxoz.rpg.chain.DefenseHandler;
import com.narxoz.rpg.chain.DodgeHandler;
import com.narxoz.rpg.chain.HpHandler;
import com.narxoz.rpg.command.ActionQueue;
import com.narxoz.rpg.command.AttackCommand;
import com.narxoz.rpg.command.DefendCommand;
import com.narxoz.rpg.command.HealCommand;
import java.util.Random;

public class TournamentEngine {
    private final ArenaFighter hero;
    private final ArenaOpponent opponent;
    private Random random = new Random(1L);

    public TournamentEngine(ArenaFighter hero, ArenaOpponent opponent) {
        this.hero = hero;
        this.opponent = opponent;
    }

    public TournamentEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public TournamentResult runTournament() {
        TournamentResult result = new TournamentResult();
        int round = 0;
        final int maxRounds = 20;

        // 1.Chain of Responsibility
        DefenseHandler defenseChain = new DodgeHandler(hero.getDodgeChance(), random.nextLong());
        DefenseHandler block = new BlockHandler(hero.getBlockRating() / 100.0);
        DefenseHandler armor = new ArmorHandler(hero.getArmorValue());
        DefenseHandler hp = new HpHandler();

        //ТDodge -> Block -> Armor -> HP
        defenseChain.setNext(block).setNext(armor).setNext(hp);

        // Command Pattern - Invoker)
        ActionQueue actionQueue = new ActionQueue();

        System.out.println("=== TOURNAMENT BATTLE STARTS ===");

        while (hero.isAlive() && opponent.isAlive() && round < maxRounds) {
            round++;
            System.out.println("\n--- Round " + round + " ---");

            // Enqueue
            // Dodge +10%
            actionQueue.enqueue(new DefendCommand(hero, 0.10));
            // attack
            actionQueue.enqueue(new AttackCommand(opponent, hero.getAttackPower()));
            if (hero.getHealth() <= hero.getMaxHealth() - 20) {
                actionQueue.enqueue(new HealCommand(hero, 20));
            }

            System.out.println("[Queue] Hero plans to: " + actionQueue.getCommandDescriptions());

            actionQueue.executeAll();

            if (opponent.isAlive()) {
                System.out.println("[Opponent] " + opponent.getName() + " attacks for " + opponent.getAttackPower() + " damage!");
                defenseChain.handle(opponent.getAttackPower(), hero);
            }

            String logLine = String.format("[Round %d] %s HP: %d | %s HP: %d",
                    round, opponent.getName(), opponent.getHealth(), hero.getName(), hero.getHealth());
            System.out.println(logLine);
            result.addLine(logLine);
        }

        System.out.println("\n=== TOURNAMENT FINISHED ===");

        // 4. hero
        if (hero.isAlive() && !opponent.isAlive()) {
            result.setWinner(hero.getName());
        } else if (!hero.isAlive() && opponent.isAlive()) {
            result.setWinner(opponent.getName());
        } else {
            result.setWinner("Draw (Timeout max rounds)");
        }

        result.setRounds(round);
        return result;
    }
}