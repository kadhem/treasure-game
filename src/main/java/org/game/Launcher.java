package org.game;

import org.game.exceptions.TreasureGameException;
import org.game.model.Adventurer;
import org.game.model.TreasureMap;
import org.game.service.PlayService;
import org.game.service.TreasureMapService;
import org.game.utils.FileInputExtractor;

import java.util.List;

public class Launcher {

    public static void main(String[] args) throws Exception {
        final var lines = FileInputExtractor.readFileLines(args[0]);
        final var adventurers = FileInputExtractor.extractAdventurer(lines);
        TreasureMapService treasureMapService = new TreasureMapService();
        var treasureMap = treasureMapService.createAndFeedTreasureMap(lines, adventurers);
        treasureMapService.displayTreasureMap(treasureMap);
        playAndDisplayResult(adventurers, treasureMap);
        treasureMapService.displayTreasureMap(treasureMap);
    }

    private static void playAndDisplayResult(List<Adventurer> adventurers, TreasureMap treasureMap) throws TreasureGameException {
        var playService = new PlayService();
        final var playedTreasureMap = playService.play(treasureMap, adventurers);
        playService.outputResult(playedTreasureMap);
    }
}
