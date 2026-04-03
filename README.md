# Homework 6: RPG Grand Arena Tournament ⚔️

## Overview
This homework continues the RPG series. In this phase, we implement two **behavioral design patterns** in an arena tournament setting, building on the game world introduced in previous homeworks.

| Pattern | Role in this homework |
| :--- | :--- |
| **Command** | Encapsulate hero actions as objects, queue them, and support pre-execution undo. |
| **Chain of Responsibility** | Route incoming damage through a sequence of defense handlers. |

### Connection to Previous Homeworks
| Homework | Patterns | Scenario |
| :--- | :--- | :--- |
| **HW3** | Singleton + Adapter | `BattleEngine` manages hero vs enemy combatants. |
| **HW4** | Bridge + Composite | Raid mode — team hierarchies and skill-effect combinations. |
| **HW5** | Decorator + Facade | Dungeon run — decorated attacks, `DungeonFacade` workflow. |
| **HW6** | **Chain of Resp. + Command** | **Grand Arena Tournament (Current)** |

---

## What Was Built

### 1. Command Pattern
- `ActionCommand` interface
- `AttackCommand`, `HealCommand`, `DefendCommand` — encapsulate hero actions with execute/undo logic.
- `ActionQueue` — the invoker: enqueue, undoLast, executeAll.

### 2. Chain of Responsibility Pattern
- `DefenseHandler` abstract class.
- `DodgeHandler`, `BlockHandler`, `ArmorHandler`, `HpHandler` — concrete defense handlers forming a chain to reduce incoming damage.

### 3. Integration
- `TournamentEngine` runs multi-round battles using both patterns together.
- `Main.java` demo proves both patterns work independently and together, including a demonstration of the `undo()` functionality.

---
UML Diagrams
Command Pattern UML (Action Hierarchy)  
## Project Structure
```text
homework-rpg-6/
├── src/com/narxoz/rpg/
│   ├── Main.java
│   ├── command/           ActionCommand, AttackCommand, HealCommand, DefendCommand, ActionQueue
│   ├── chain/             DefenseHandler, DodgeHandler, BlockHandler, ArmorHandler, HpHandler
│   ├── arena/             ArenaFighter, ArenaOpponent, TournamentResult
│   ├── tournament/        TournamentEngine
└── README.md
