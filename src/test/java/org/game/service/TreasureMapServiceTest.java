package org.game.service;

import org.game.enums.MoveEnum;
import org.game.enums.Orientation;
import org.game.exceptions.TreasureGameException;
import org.game.model.Adventurer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class TreasureMapServiceTest {

    TreasureMapService treasureMapService;

    @BeforeEach
    void setUp() {
        treasureMapService = new TreasureMapService();
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void createAndFeedTreasureMap_test_nominal(List<String> lines,
                                               List<Adventurer> adventurers,
                                               int xLength,
                                               int yLength,
                                               int treasuresCount,
                                               int moutainsCount,
                                               int adventurersCount) throws TreasureGameException {

        final var treasureMap = treasureMapService.createAndFeedTreasureMap(lines, adventurers);
        Assertions.assertEquals(treasureMap.getLengthX(), xLength);
        Assertions.assertEquals(treasureMap.getLengthY(), yLength);
        Assertions.assertEquals(treasuresCount, treasureMap.getTreasuresCount());
        Assertions.assertEquals(moutainsCount, treasureMap.getMountainsCount());
        Assertions.assertEquals(adventurersCount, treasureMap.getAdventurersCount());
    }

    @Test
    void createAndFeedTreasureMap_when_no_map_def_input_then_throwException() {
        Assertions.assertThrows(TreasureGameException.class,
                        () -> treasureMapService.createAndFeedTreasureMap(List.of(), null));
    }

    static Stream<Arguments> paramsProvider() {
        return Stream.of(
                arguments(List.of("C-3-4", "T-1-3-1", "M-1-2"),
                        List.of(new Adventurer.AdventurerBuilder()
                                .xAxis(1).yAxis(1).name("adv").orientation(Orientation.S)
                                .moves(List.of(MoveEnum.A, MoveEnum.A, MoveEnum.G, MoveEnum.D)).build()),
                        3, 4, 1, 1, 1
                ),
                arguments(List.of("C-3-4", "T-1-3-1", "T-2-3-1", "M-1-2"),
                        List.of(new Adventurer.AdventurerBuilder()
                                        .xAxis(1).yAxis(1).name("adv1").orientation(Orientation.S)
                                        .moves(List.of(MoveEnum.A, MoveEnum.A, MoveEnum.G, MoveEnum.D)).build(),
                                new Adventurer.AdventurerBuilder()
                                        .xAxis(1).yAxis(2).name("adv2").orientation(Orientation.S)
                                        .moves(List.of(MoveEnum.A, MoveEnum.A, MoveEnum.G, MoveEnum.D)).build()),
                        3, 4, 2, 1, 2
                )
        );
    }
}