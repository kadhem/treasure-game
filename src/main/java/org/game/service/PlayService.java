package org.game.service;

import org.game.enums.MoveEnum;
import org.game.exceptions.TreasureGameException;
import org.game.model.Adventurer;
import org.game.model.Cell;
import org.game.model.TreasureMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.game.utils.Constants.*;

public class PlayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayService.class);

    public TreasureMap play(TreasureMap treasureMap, List<Adventurer> adventurers) {

        for (Adventurer adventurer : adventurers) {
            int nextAxisX;
            int nextAxisY;
            for (MoveEnum move : adventurer.getMoves()) {
                int currentAxisX = adventurer.getxAxis();
                int currentAxisY = adventurer.getyAxis();

                if (move.equals(MoveEnum.G)) {
                    adventurer.setOrientation(adventurer.getOrientation().getLeft());
                } else if (move.equals(MoveEnum.D)) {
                    adventurer.setOrientation(adventurer.getOrientation().getRight());
                }

                nextAxisX = currentAxisX + adventurer.getOrientation().getNextX();
                nextAxisY = currentAxisY + adventurer.getOrientation().getNextY();

                LOGGER.info("Move {} -\t CURRENT X AXIS {} -\t CURRENT Y AXIS {} -\t Next X AXIS {} -\t Next Y AXIS {}",
                        move,
                        currentAxisX,
                        currentAxisY,
                        nextAxisX,
                        nextAxisY);

                if (nextAxisX < 0 || nextAxisX >= treasureMap.getMap().length || nextAxisY < 0 || nextAxisY >= treasureMap.getMap()[currentAxisX].length) {
                    continue;
                }

                if (treasureMap.getMap()[nextAxisX][nextAxisY].isMountain()) {
                    continue;
                }

                if (move.equals(MoveEnum.A)) {

                    if (!treasureMap.getMap()[nextAxisX][nextAxisY].isAdventurer() || treasureMap.getMap()[nextAxisX][nextAxisY].getAdventurer().getPriority() > adventurer.getPriority()) {
                        forwardAdventurer(treasureMap.getMap(), adventurer, nextAxisX, nextAxisY, treasureMap.getMap()[currentAxisX][currentAxisY]);

                        if (treasureMap.getMap()[nextAxisX][nextAxisY].isTreasure()) {
                            collectTreasure(treasureMap.getMap(), adventurer, nextAxisX, nextAxisY);
                        }
                    }
                }

            }
        }
        return treasureMap;
    }

    public void outputResult(TreasureMap treasureMap) throws TreasureGameException {
        try (FileWriter writer = new FileWriter(new File(RESULT_FILE_PATH))){
            writer.append(String.format(MAP_INFO_LINE, treasureMap.getLengthX(), treasureMap.getLengthY()));
            extractMapElements(treasureMap, writer);
        } catch (IOException e) {
            LOGGER.error("Une erreur s'est produite lors de l'écriture des résultats");
            throw new TreasureGameException(TreasureGameException.TreasureGameError.RESULT_WRITE_ERROR);
        }
    }

    private void extractMapElements(TreasureMap treasureMap, FileWriter writer) throws IOException {
        StringBuilder mountainsInfo = new StringBuilder();
        StringBuilder treasuresInfo = new StringBuilder();
        StringBuilder adventurersInfo = new StringBuilder();
        for (Cell[] cells : treasureMap.getMap()) {
            for (Cell cell : cells) {
                if (cell.isMountain()) {
                    mountainsInfo.append(String.format(MOUNTAIN_INFO_LINE, cell.getxAxis(), cell.getyAxis()));
                } else if (cell.isTreasure()) {
                    treasuresInfo.append(String.format(TREASURE_INFO_LINE, cell.getxAxis(), cell.getyAxis(), cell.getTreasureCount()));
                } else if (cell.isAdventurer()) {
                    adventurersInfo.append(String.format(ADVENTURER_INFO_LINE, cell.getAdventurer().getName(), cell.getxAxis(), cell.getyAxis(), cell.getAdventurer().getOrientation(), cell.getAdventurer().getTreasuresWinned()));
                }
            }
        }
        writer.append(mountainsInfo).append(treasuresInfo).append(adventurersInfo);
    }

    private void forwardAdventurer(Cell[][] treasureMapCells, Adventurer adventurer, int nextAxisX, int nextAxisY, Cell cell) {
        adventurer.setxAxis(nextAxisX);
        adventurer.setyAxis(nextAxisY);
        cell.setAdventurer(null);
        treasureMapCells[nextAxisX][nextAxisY].setAdventurer(adventurer);
    }


    private void collectTreasure(Cell[][] treasureMapCells, Adventurer adventurer, int x, int y) {
        adventurer.setTreasuresWinned(adventurer.getTreasuresWinned() + 1);
        treasureMapCells[x][y].setTreasureCount(treasureMapCells[x][y].getTreasureCount() - 1);
        if (treasureMapCells[x][y].getTreasureCount() == 0) {
            treasureMapCells[x][y].setTreasure(false);
        }
    }
}
