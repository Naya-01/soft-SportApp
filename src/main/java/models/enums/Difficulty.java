package models.enums;

public enum Difficulty {

        BEGINNER("Beginner"),
        INTERMEDIATE("Intermediate"),
        ADVANCED("Advanced");

        private String difficulty;

        Difficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getDifficulty() {
            return difficulty;
        }
}
