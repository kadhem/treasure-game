package org.game.exceptions;

public class TreasureGameException extends Exception{

    private String message;



    public TreasureGameException(TreasureGameError error) {
        super(error.getMessage());
    }

    public enum TreasureGameError {

        INPUT_FILE_NOT_FOUND("Le fichier spécifié est introuvable"),
        RESULT_WRITE_ERROR("Erreur lors de l'écriture des résultats dans le fichier de sortie"),
        NO_MAP_LINE_FOUND("Aucune ligne définissant une carte existe");

        private String message;

        TreasureGameError(String message) {
        }


        public String getMessage() {
            return message;
        }
    }
}
