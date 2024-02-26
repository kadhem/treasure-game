package org.game.service;

import org.game.exceptions.TreasureGameException;
import org.game.model.Adventurer;
import org.game.model.Cell;
import org.game.model.TreasureMap;
import org.game.utils.Constants;

import java.util.List;

import static org.game.utils.Constants.SEPARATOR;

public class TreasureMapService {

    private TreasureMap createMap(List<String> lines) throws TreasureGameException {
        TreasureMap treasureMap = null;
        boolean isMapLineFound = false;
        for (String line : lines) {
            if (line.startsWith(Constants.CARTE)) {
                treasureMap = new TreasureMap();
                isMapLineFound = true;

                final var split = line.split(SEPARATOR);

                var capacityX = Integer.parseInt(split[1].strip());
                var capacityY = Integer.parseInt(split[2].strip());

                treasureMap.setLengthX(capacityX);
                treasureMap.setLengthY(capacityY);
                treasureMap.setMap(new Cell[capacityX][capacityY]);

                for (int i = 0; i < capacityX; i++) {
                    for (int j = 0; j < capacityY; j++) {
                        treasureMap.getMap()[i][j] = new Cell();
                    }
                }
            }
        }

        if (!isMapLineFound) {
            throw new TreasureGameException(TreasureGameException.TreasureGameError.NO_MAP_LINE_FOUND);
        }

        return treasureMap;
    }

    private void placeTreasuresAndMountainsInMap(List<String> lines, TreasureMap treasureMap) {
        for (String line : lines) {
            if (line.startsWith(Constants.MOUNTAIN)) {
                final var split = line.split(SEPARATOR);
                var xAxis = Integer.parseInt(split[1].strip());
                var yAxis = Integer.parseInt(split[2].strip());
                assert treasureMap != null && treasureMap.getMap() != null && treasureMap.getMap()[xAxis][yAxis] != null;
                treasureMap.getMap()[xAxis][yAxis].setMountain(true);
                treasureMap.getMap()[xAxis][yAxis].setxAxis(xAxis);
                treasureMap.getMap()[xAxis][yAxis].setyAxis(yAxis);
            } else if (line.startsWith(Constants.TREASURE)) {
                final var split = line.split(SEPARATOR);
                var xAxis = Integer.parseInt(split[1].strip());
                var yAxis = Integer.parseInt(split[2].strip());
                var treasureCount = Integer.parseInt(split[3].strip());
                assert treasureMap != null && treasureMap.getMap()[xAxis][yAxis] != null;
                treasureMap.getMap()[xAxis][yAxis].setTreasure(true);
                treasureMap.getMap()[xAxis][yAxis].setTreasureCount(treasureCount);
                treasureMap.getMap()[xAxis][yAxis].setxAxis(xAxis);
                treasureMap.getMap()[xAxis][yAxis].setyAxis(yAxis);
            }
        }
    }

    private void placeAdventurerInMap(TreasureMap treasureMap, List<Adventurer> adventurers) {
        for (Adventurer adventurer : adventurers) {
            treasureMap.getMap()[adventurer.getxAxis()][adventurer.getyAxis()].setAdventurer(adventurer);
        }
    }

    public TreasureMap createAndFeedTreasureMap(List<String> lines, List<Adventurer> adventurers) throws TreasureGameException {
        final var treasureMap = createMap(lines);
        placeAdventurerInMap(treasureMap, adventurers);
        placeTreasuresAndMountainsInMap(lines, treasureMap);
        return treasureMap;
    }

    public void displayTreasureMap(TreasureMap treasureMap) {
        int j = 0;
        int i = 0;
        for (; j < treasureMap.getMap()[i].length; j++) {
            for (; i < treasureMap.getMap().length; i++) {
                if (treasureMap.getMap()[i][j].isMountain()) {
                    System.out.print("\tM\t");
                } else {
                    if (treasureMap.getMap()[i][j].isTreasure()) {
                        System.out.print("\tT(" + treasureMap.getMap()[i][j].getTreasureCount() + ")");
                    } else {
                        if (treasureMap.getMap()[i][j].isAdventurer()) {
                            System.out.print("A(" + treasureMap.getMap()[i][j].getAdventurer().getName() + ")");
                        } else {
                            System.out.print("\t.\t");
                        }
                    }
                }
            }
            System.out.println("");
            i = 0;
        }
    }


}
