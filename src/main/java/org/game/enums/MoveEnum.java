package org.game.enums;

import java.util.ArrayList;
import java.util.List;

public enum MoveEnum {
    A,
    D,
    G;

    public static List<MoveEnum> valueOf(char[] chars) {
        List<MoveEnum> moves = new ArrayList<>();
        for (char aChar : chars) {
            moves.add(MoveEnum.valueOf(String.valueOf(aChar)));
        }
        return moves;
    }
}
