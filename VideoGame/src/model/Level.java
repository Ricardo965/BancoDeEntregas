package model;

public class Level {
    private static int identifier = 1;
    private int id;
    private int requiredScore;
    private Difficulty difficulty;
    private Treasure[] listOfTreasuresInLevel = new Treasure[50];
    private Monster[] listOfMonstersInLevel = new Monster[25];
    

    public Level(int requiredScore){
        this.id = identifier;
        this.requiredScore = requiredScore;
        this.difficulty = Difficulty.values()[1];
        identifier++;
    }


    public Monster[] getListOfMonstersInLevel() {
        return listOfMonstersInLevel;
    }


    public void setListOfMonstersInLevel(Monster[] listOfMonstersInLevel) {
        this.listOfMonstersInLevel = listOfMonstersInLevel;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }


    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }


    public int getId() {
        return id;
    }


    public double getRequiredScore() {
        return requiredScore;
    }


    public Treasure[] getListOfTreasuresInLevel() {
        return listOfTreasuresInLevel;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setRequiredScore(int requiredScore) {
        this.requiredScore = requiredScore;
    }


    public void setListOfTreasuresInLevel(Treasure[] listOfTreasuresInLevel) {
        this.listOfTreasuresInLevel = listOfTreasuresInLevel;
    }


    @Override
    public String toString(){
        return "Nivel "
                + "\nIdentificador = " + (id) 
                + "\nPuntaje requerido = " + requiredScore
                + "\nDificultad = " + difficulty;
    }
 
}
