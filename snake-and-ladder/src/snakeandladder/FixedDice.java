package snakeandladder;

import java.util.LinkedList;
import java.util.Queue;

public class FixedDice implements DiceRoller {
    private final Queue<Integer> rolls = new LinkedList<>();

    public FixedDice(int... values) {
        for (int value : values) {
            if (value < 1 || value > 6) {
                throw new IllegalArgumentException(
                        "Dice rolls must be between 1 and 6"
                );
            }
            rolls.add(value);
        }
    }

    @Override
    public int roll() {
        if (rolls.isEmpty()) {
            throw new IllegalStateException(
                    "No test dice rolls remaining"
            );
        }
        return rolls.remove();
    }
}
