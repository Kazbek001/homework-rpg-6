package com.narxoz.rpg.command;

import java.util.ArrayList;
import java.util.List;

public class ActionQueue {
    private final List<ActionCommand> queue = new ArrayList<>();

    public void enqueue(ActionCommand cmd) {
        if (cmd != null) {
            queue.add(cmd);
        }
    }

    public void undoLast() {
        // is queue empty?
        if (!queue.isEmpty()) {
            int lastIndex = queue.size() - 1;
            queue.remove(lastIndex);
        }

    }

    public void executeAll() {
        if (queue.isEmpty()) {
            System.out.println("[Queue] No actions to execute.");
            return;
        }

        for (ActionCommand cmd : queue) {
            cmd.execute();
        }

        queue.clear();
    }

    public List<String> getCommandDescriptions() {
        List<String> descriptions = new ArrayList<>();
        // Description
        for (ActionCommand cmd : queue) {
            descriptions.add(cmd.getDescription());
        }
        // Snapshot
        return descriptions;
    }
}