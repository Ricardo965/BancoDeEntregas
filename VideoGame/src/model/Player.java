package model;

/**
 * Player
 */
public class Player {

    String nickname;
    int initialScore;
    String name;
    int amountLives;
    int currentLevel;
    public Player(String nickname, String name) {
        this.nickname = nickname; //Metodo verificar nickname
        this.name = name;
        this.initialScore = 10;
        this.amountLives = 5;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getInitialScore() {
        return initialScore;
    }
    public void setInitialScore(int initialScore) {
        this.initialScore = initialScore;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAmountLives() {
        return amountLives;
    }
    public void setAmountLives(int amountLives) {
        this.amountLives = amountLives;
    }
    public int getCurrentLevel() {
        return currentLevel;
    }
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    @Override
    public String toString() {
        return  " Nickname = " + nickname + 
                " Puntaje = " + initialScore +
                " Nombre = " + name +
                " Cantidad de vidas = " + amountLives +
                " Nivel actual = " + currentLevel;
    }

    



}