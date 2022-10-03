package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GameController {
    private Player[] listOfPlayers = new Player[20];
    private Level[] listOfLevels = new Level[10];
    private Treasure[] listOfTreasures = new Treasure[50];
    private Monster[] listOfMonsters = new Monster[25];
    private Resolution resolution;


    public boolean setResolution(int resolutionIndicative) {
        this.resolution = Resolution.values()[resolutionIndicative-1];
        return true;
    }
    

    public Resolution getResolution() {
        return resolution;
    }


    public boolean isThereObjects(int listIndicative){
        switch (listIndicative) {
            case 1:
                
                break;
        
            case 2:
                
                break;

            case 3:
                
                break;

            case 4:
                
                break;
        }
        return true;
    }


    public boolean registerPlayer(String nickname, String name) {
        Player newPlayer = new Player(nickname, name);
		for (int i = 0; i < listOfPlayers.length; i++) {

			if (listOfPlayers[i] == null) {

				listOfPlayers[i] = newPlayer;
				return true;
			}

		}
		return false;
    }


    public boolean verifyNickname(String nicknameToVerify){
        for (int i = 0; i < listOfPlayers.length; i++) {

			if (listOfPlayers[i] != null) {

				if ((listOfPlayers[i].getNickname().equals(nicknameToVerify))) {
                    return false;
                }
				
			}

		}
        return true;
    }

    public boolean registerLevel(double growthConstant, int requiredScore) {
        for (int i = 0; i < listOfLevels.length; i++) {
            Level newLevel = new Level(Math.round(requiredScore));
            listOfLevels[i] = newLevel;
            requiredScore *= ((growthConstant/100)+1);
        }
        return true;
    }
   
    public boolean registerTreasure(String name, String linkUrl, int remPoints) {
        Treasure newTreasure = new Treasure(name, linkUrl, remPoints);
        for (int i = 0; i < listOfTreasures.length; i++) {
            if (listOfTreasures[i] == null) {
                
                listOfTreasures[i] = newTreasure;
				return true;
				
			} else if (listOfTreasures[i].equals(newTreasure)){
                return false;
            }
        }
        
        return false;
    }

    public boolean registerMonster(String name, int monsterIndicative, int negativePoints, int remPoints){
        EnemyType type = EnemyType.values()[monsterIndicative];
        Monster newMonster = new Monster(name, type, negativePoints, remPoints);
        for (int i = 0; i < listOfMonsters.length; i++) {
            if (listOfMonsters[i] == null) {
                
                listOfMonsters[i] = newMonster;
				return true;
				
			} else if (listOfMonsters[i].equals(newMonster)){
                
                return false;
            }
        }
        return false;
    }



    public String showLevels() {

		String levels = "";

		for (int i = 0; i < listOfLevels.length; i++) {

			if (listOfLevels[i] != null) {

				levels += "\nNivel #" + (i+1) + "\n" + listOfLevels[i].toString();
			}

		}
		return levels;

	}

    public String showTreasures() {

		String treasures = "";

		for (int i = 0; i < listOfTreasures.length; i++) {

			if (listOfTreasures[i] != null) {

                treasures += "\nTesoro #" + (i+1) + "\n" + listOfTreasures[i].toString();
			}

		}
		return treasures;

	}


    public String showMonsters() {

		String monsters = "";

		for (int i = 0; i < listOfMonsters.length; i++) {

			if (listOfMonsters[i] != null) {

                monsters += "\nEnemigo #" + (i+1) + "\n" + listOfMonsters[i].toString();
			}

		}
		return monsters;

	}


    public String showPlayers() {

		String players = "";

		for (int i = 0; i < listOfPlayers.length; i++) {

			if (listOfPlayers[i] != null) {

                players += "\nJugador #" + (i+1) + "\n" + listOfPlayers[i].toString();
			}

		}
		return players;

	}


    public boolean associateTreasureToLevel(int idLevel, int idTreasure, int amountTreasures){
        int counter = 0;
        for (int j = 0; j < listOfLevels[idLevel].getListOfTreasuresInLevel().length; j++) {
            if (listOfLevels[idLevel].getListOfTreasuresInLevel()[j] == null) {
                Treasure treasureCopy = new Treasure(listOfTreasures[idTreasure]);
                treasureCopy.setxPos(generatePosition(getResolution().getxPixel()));
                treasureCopy.setyPos(generatePosition(getResolution().getyPixel()));
                listOfLevels[idLevel].getListOfTreasuresInLevel()[j] = treasureCopy;
                listOfLevels[idLevel].setDifficulty(Difficulty.values()[setLevelDifficulty(idLevel)]);
                counter++;
                if (counter == amountTreasures) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean associateMonsterToLevel(int idLevel, int idMonster) {
        Monster monsterCopy = new Monster(listOfMonsters[idMonster]);
        for (int j = 0; j < listOfLevels[idLevel].getListOfMonstersInLevel().length; j++) {
            if (listOfLevels[idLevel].getListOfMonstersInLevel()[j] == null) {
                monsterCopy.setxPos(generatePosition(getResolution().getxPixel()));
                monsterCopy.setyPos(generatePosition(getResolution().getyPixel()));
                listOfLevels[idLevel].getListOfMonstersInLevel()[j] = monsterCopy;
                listOfLevels[idLevel].setDifficulty(Difficulty.values()[setLevelDifficulty(idLevel)]);
                return true;
            } else if ((listOfMonsters[idMonster].equals(monsterCopy))){
                return false;
            }
        }
        return false;
    }


    public boolean modifyPlayerScore(int idPlayer, int newScore) {
        listOfPlayers[idPlayer].setInitialScore(newScore);
        identifyPlayerLevel(idPlayer);
        return true;
    }


    public int identifyPlayerLevel(int idPlayer) {
        for (int i = 0; i < listOfLevels.length; i++) {
            if (listOfLevels[i] != null) {
                if (listOfPlayers[idPlayer].getInitialScore()>=listOfLevels[i].getRequiredScore()) {
                    listOfPlayers[idPlayer].setCurrentLevel(i+1);
                }
            }
        }
        return listOfPlayers[idPlayer].getCurrentLevel();
    }


    public int generatePosition(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange+1);
    }


    public int setLevelDifficulty(int idLevel) {
        
        int treasurePoints = 0;
        int enemyPoints = 0;

        for (int i = 0; i < listOfLevels[idLevel].getListOfTreasuresInLevel().length; i++) {
            if (listOfLevels[idLevel].getListOfTreasuresInLevel()[i] != null) {
                treasurePoints += listOfLevels[idLevel].getListOfTreasuresInLevel()[i].getRemPoints();
            }
        }
        for (int i = 0; i < listOfLevels[idLevel].getListOfMonstersInLevel().length; i++) {
            if (listOfLevels[idLevel].getListOfMonstersInLevel()[i] != null) {
                enemyPoints += listOfLevels[idLevel].getListOfMonstersInLevel()[i].getRemPoints();
            }
        }

        int levelDiff = 1;
        if (treasurePoints>enemyPoints) {
            levelDiff = 2;
        } else if(treasurePoints<enemyPoints) {
            levelDiff = 0;
        } else {
            levelDiff = 1;
        }

        return levelDiff;
    }


    public String informMonstersTreasuresLevel(int idLevel) {
        String stats = "";
        int counter;
        for (int i = 0; i < listOfMonsters.length; i++) {
            counter = 0;
            if (listOfMonsters[i] != null) {
                for (int j = 0; j < listOfLevels[idLevel].getListOfMonstersInLevel().length; j++) {
                    if (listOfLevels[idLevel].getListOfMonstersInLevel()[j] != null) {
                        if (listOfMonsters[i].getName().equals(listOfLevels[idLevel].getListOfMonstersInLevel()[j].getName())) {
                            counter++;
                        }
                    }
                    
                }
                stats += "\nEnemigo: " + " " + listOfMonsters[i].getName() + " Cantidad: " + counter;
            }
            
        }
        
        for (int i = 0; i < listOfTreasures.length; i++) {
            counter = 0;
            if (listOfTreasures[i] != null) {
                for (int j = 0; j < listOfLevels[idLevel].getListOfTreasuresInLevel().length; j++) {
                    if (listOfLevels[idLevel].getListOfTreasuresInLevel()[j] != null) {
                        if (listOfTreasures[i].getName().equals(listOfLevels[idLevel].getListOfTreasuresInLevel()[j].getName())) {
                            counter++;
                        }
                     
                    }
                }
                stats += "\nTesoro: " + "" + listOfTreasures[i].getName() + " Cantidad: " + counter;
            }
            
        }
        
        return stats;
    }


    public int informAmountTreasures(int idTreasure) {
        int treasureStats = 0;
        for (int i = 0; i < listOfLevels.length; i++) {
            for (int j = 0; j < listOfLevels[i].getListOfTreasuresInLevel().length; j++) {
                if (listOfLevels[i].getListOfTreasuresInLevel()[j] != null) {
                    if (listOfTreasures[idTreasure].equals(listOfLevels[i].getListOfTreasuresInLevel()[j])) {
                        treasureStats++;
                    }

                }
            }
        }
        return treasureStats;
    }


    public String informAmountMonstersType(int idEnemyType) {
        int counter = 0;
        String monsterStats = "";
        for (int f = 0; f < listOfLevels.length; f++) {
            for (int j = 0; j < listOfLevels[f].getListOfMonstersInLevel().length; j++) {
                if (listOfLevels[f].getListOfMonstersInLevel()[j] != null) {
                    if (EnemyType.values()[idEnemyType].equals(listOfLevels[f].getListOfMonstersInLevel()[j].getType())) {
                        counter++;
                    }

                }
            }
        }
        monsterStats += "\nHay " + counter + " enemigos del tipo " + EnemyType.values()[idEnemyType];
       
        return monsterStats;
    }


    public int informMostRepeatedTreasure() {
        int mostrepeatedTreasure = 0;
        for (int i = 1; i < listOfTreasures.length; i++) {
            if (listOfTreasures[i] != null) {
                if (informAmountTreasures(mostrepeatedTreasure)<informAmountTreasures(i)) {
                    mostrepeatedTreasure = i;
                }
            }
        }

        return mostrepeatedTreasure;
    }


    public String bestRemMonster() {
        int bestMonster = 0;
        for (int i = 1; i < listOfMonsters.length; i++) {
            if (listOfMonsters[i] != null) {
                if (listOfMonsters[bestMonster].getRemPoints()<listOfMonsters[i].getRemPoints()) {
                    bestMonster = i;
                }
            }
        }
        String ubication = "| El enemigo con mayor remuneración " + listOfMonsters[bestMonster] + "se encuentra en el nivel:";
        for (int i = 0; i < listOfLevels.length; i++) {
            for (int j = 0; j < listOfLevels[i].getListOfMonstersInLevel().length; j++) {
                if (listOfLevels[i].getListOfMonstersInLevel()[j] != null) {
                    if (listOfMonsters[bestMonster].equals(listOfLevels[i].getListOfMonstersInLevel()[j])) {
                        ubication += " " + i + " |";
                    }
                }
            }
        }
        return ubication;
    }


    public String numberConsonantsMonster() {
        String numConsonants = "";
        int counter;
        for (int i = 0; i < listOfMonsters.length; i++) {
            counter = 0;
            if (listOfMonsters[i] != null) {
                char[] charName = listOfMonsters[i].getName().toCharArray();
                for (int j = 0; j < charName.length; j++) {
                    if ((charName[j]=='a')||(charName[j]=='e')||(charName[j]=='i')||(charName[j]=='o')||(charName[j]=='u')||(charName[j]==' ')) {
                        
                    }
                    if (!(Character.toString(charName[j]).matches("a-A-e-E-i-I-o-O-u-U- "))) {
                        counter++;
                    }
                }
                numConsonants += "\nEl nombre del enemigo " + listOfMonsters[i].getName() + " tiene " + counter +" consonantes.";
            }
        }
        
        return numConsonants;
    }

    public int[] pruebaTop5(){
        int[] topFIVE = {0,0,0,0,0};
        for (int i = 0; i < listOfPlayers.length; i++) {
            for (int j = i+1; j < topFIVE.length; j++) {
                if (listOfPlayers[i] != null){
                    if (topFIVE[j]!=i) {
                        if (listOfPlayers[i].getInitialScore()>listOfPlayers[topFIVE[j]].getInitialScore()) {
                            topFIVE[j]=i;
                        }
                    }
                    
                }
                
            }
        }
        return topFIVE;
    }
    public Integer[] informTopPlayers() {
        Integer[] scores = new Integer[25];
        for (int i = 0; i < listOfPlayers.length; i++) {
            if (listOfPlayers[i] != null) {
                scores[i] = listOfPlayers[i].getInitialScore();
                
            }
        }
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == null) {
                scores[i] = 0;
            }
        }
        Arrays.sort(scores, Comparator.reverseOrder());
        Integer[] topFive = Arrays.copyOfRange(scores, 0, 5);



        













        return topFive;
    }
    public Player[] getListOfPlayers() {
        return listOfPlayers;
    }




    public Level[] getListOfLevels() {
        return listOfLevels;
    }




    public Treasure[] getListOfTreasures() {
        return listOfTreasures;
    }




    public Monster[] getListOfMonsters() {
        return listOfMonsters;
    }

    
}
