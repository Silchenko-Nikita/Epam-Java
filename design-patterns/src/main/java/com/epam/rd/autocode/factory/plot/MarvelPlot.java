package com.epam.rd.autocode.factory.plot;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarvelPlot implements Plot {
    Character[] heroes;
    EpicCrisis epicCrisis;
    Character villain;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    @Override
    public String toString() {
        String heroesStr = Stream.of(heroes)
                .map(Character::name)
                .collect(Collectors.joining(", brave "));

        return String.format("%s threatens the world. But brave %s on guard. So, no way that intrigues of %s overcome the willpower of inflexible heroes", epicCrisis.name(), heroesStr, villain.name());
    }
}
