package snakeandladder;

import java.util.Random;

public class Dice implements DiceRoller{
    private final int numberofSides;
    private Random random = new Random();

    public Dice(int numberofSides) {
        if(numberofSides<1)
            throw new IllegalArgumentException("Number of sides can't be less than 1");
        this.numberofSides = numberofSides;
    }

    @Override
    public int roll()
    {
        return random.nextInt(numberofSides)+1;
    }
}
