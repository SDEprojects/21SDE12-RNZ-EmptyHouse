package com.players.npc;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Quotes {
    STR1("You need to hurry or you'll end up stuck here like me"),
    STR2("GET OUT!!!!!!"),
    STR3("There are items scattered around the house to help you escape"),
    STR4("you should go find your friend"),
    STR5("you won't find a way out here"),
    STR6("This house is full of secrets and darkness, be careful"),
    ;

    Quotes(String s) {

    }
    private static final List<Quotes> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Quotes randomQuotes()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}

