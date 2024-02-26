package org.game.service;

import org.game.enums.MoveEnum;
import org.game.enums.Orientation;
import org.game.exceptions.TreasureGameException;
import org.game.model.Adventurer;
import org.game.model.TreasureMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayServiceTest {

    PlayService playService;
    TreasureMapService treasureMapService;

    @BeforeEach
    void setUp() {
        playService = new PlayService();
        treasureMapService = new TreasureMapService();
    }

    @Test
    void play_single_player_win_all_treasures_and_end_to_right_cell() throws TreasureGameException {
        final var adventurer = new Adventurer.AdventurerBuilder()
                .xAxis(1).yAxis(1).name("adv1").orientation(Orientation.S)
                .moves(List.of(MoveEnum.A,
                        MoveEnum.A,
                        MoveEnum.D,
                        MoveEnum.A,
                        MoveEnum.D,
                        MoveEnum.A,
                        MoveEnum.G,
                        MoveEnum.G,
                        MoveEnum.A)).build();
        TreasureMap treasureMap = getTreasureMap(List.of("C-3-4", "M-1-0", "T-0-3-2", "T-1-3-3"), List.of(adventurer));
        Assertions.assertEquals(0, adventurer.getTreasuresWinned());
        playService.play(treasureMap, List.of(adventurer));
        Assertions.assertEquals(3, adventurer.getTreasuresWinned());
        Assertions.assertEquals(0, adventurer.getxAxis());
        Assertions.assertEquals(3, adventurer.getyAxis());
    }

    @Test
    void play_single_player_no_treasure_winned() throws TreasureGameException {
        final var adventurer = new Adventurer.AdventurerBuilder()
                .xAxis(1).yAxis(1).name("adv1").orientation(Orientation.S)
                .moves(List.of(MoveEnum.A,
                        MoveEnum.A,
                        MoveEnum.D,
                        MoveEnum.A,
                        MoveEnum.D,
                        MoveEnum.A)).build();
        TreasureMap treasureMap = getTreasureMap(List.of("C-3-4", "M-0-2", "T-0-1-2"), List.of(adventurer));
        Assertions.assertEquals(0, adventurer.getTreasuresWinned());
        playService.play(treasureMap, List.of(adventurer));
        Assertions.assertEquals(0, adventurer.getTreasuresWinned());
        Assertions.assertEquals(0, adventurer.getxAxis());
        Assertions.assertEquals(3, adventurer.getyAxis());
    }

    @Test
    void play_multi_players_win_all_treasures_and_end_to_right_cell() throws TreasureGameException {
        final var adventurer1 = new Adventurer.AdventurerBuilder()
                .xAxis(1).yAxis(1).name("adv1").orientation(Orientation.S)
                .moves(List.of(MoveEnum.A,
                        MoveEnum.A,
                        MoveEnum.D,
                        MoveEnum.A,
                        MoveEnum.D,
                        MoveEnum.A,
                        MoveEnum.G,
                        MoveEnum.G,
                        MoveEnum.A)).build();

        final var adventurer2 = new Adventurer.AdventurerBuilder()
                .xAxis(1).yAxis(1).name("adv1").orientation(Orientation.S)
                .moves(List.of(MoveEnum.A,
                        MoveEnum.A,
                        MoveEnum.D,
                        MoveEnum.A,
                        MoveEnum.D,
                        MoveEnum.A,
                        MoveEnum.G,
                        MoveEnum.G,
                        MoveEnum.A)).build();
        TreasureMap treasureMap = getTreasureMap(List.of("C-3-4", "M-1-0", "T-0-3-2", "T-1-3-3"), List.of(adventurer1, adventurer2));
        Assertions.assertEquals(0, adventurer1.getTreasuresWinned());
        Assertions.assertEquals(0, adventurer2.getTreasuresWinned());

        playService.play(treasureMap, List.of(adventurer1, adventurer2));

        Assertions.assertEquals(3, adventurer1.getTreasuresWinned());
        Assertions.assertEquals(0, adventurer1.getxAxis());
        Assertions.assertEquals(3, adventurer1.getyAxis());

        Assertions.assertEquals(2, adventurer2.getTreasuresWinned());
        Assertions.assertEquals(1, adventurer2.getxAxis());
        Assertions.assertEquals(3, adventurer2.getyAxis());
    }

    private TreasureMap getTreasureMap(List<String> lines, List<Adventurer> adventurers) throws TreasureGameException {
        return treasureMapService.createAndFeedTreasureMap(lines,
                adventurers);
    }

}