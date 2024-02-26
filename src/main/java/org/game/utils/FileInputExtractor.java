package org.game.utils;

import org.game.enums.MoveEnum;
import org.game.enums.Orientation;
import org.game.exceptions.TreasureGameException;
import org.game.model.Adventurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInputExtractor {

    public static List<String> readFileLines(String filePath) throws TreasureGameException {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new TreasureGameException(TreasureGameException.TreasureGameError.INPUT_FILE_NOT_FOUND);
        }
    }

    public static List<Adventurer> extractAdventurer(List<String> lines) {
        var adventurers = new ArrayList<Adventurer>();
        int priority = 0;
        for (String line : lines) {
            if (line.startsWith(Constants.ADVENTURER)) {
                final var split = line.split("-");
                var name = split[1].strip();
                var axisX = Integer.parseInt(split[2].strip());
                var axisY = Integer.parseInt(split[3].strip());
                var orientation = Orientation.valueOf(split[4].strip());
                var moves = MoveEnum.valueOf(split[5].strip().toCharArray());
                Adventurer adventurer = new Adventurer(name, priority, orientation, moves, axisX, axisY, 0);
                adventurers.add(adventurer);
                priority++;
            }
        }
        return adventurers;
    }
}
