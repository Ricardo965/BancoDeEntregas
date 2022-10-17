package model;

import java.util.Random;

public class GameController {
    private Player[] listOfPlayers = new Player[20];
    private Level[] listOfLevels = new Level[10];
    private Treasure[] listOfTreasures = new Treasure[50];
    private Monster[] listOfMonsters = new Monster[25];
    private Resolution resolution;


    /**
     * Description: This method sets the game resolution
     * @param resolutionIndicative int: It is the resolution Indicative given by the user
     * @return boolean: When the resolution is changed, it returns true
     */
    public boolean setResolution(int resolutionIndicative) {
        this.resolution = Resolution.values()[resolutionIndicative-1];
        return true;
    }
    

    public Resolution getResolution() {
        return resolution;
    }


    /**
     * Description: This method verifies if there is an object in the list to analyze
     * @param listIndicative int: It is the indicative of the type of list to analyze. 1 = list of treasures 2 = list of monster
     * @param idLevel int: It is the id of the level´s position in the level array
     * @return boolean: If there is an object in the list, it returns true. If there is not, it returns false
     */
    public boolean isThereObjects(int listIndicative, int idLevel){
        Object[] listToAnalyze = {};
        switch (listIndicative) {
            case 1:
                listToAnalyze = listOfLevels[idLevel].getListOfTreasuresInLevel();
                break;
        
            case 2:
                listToAnalyze = listOfLevels[idLevel].getListOfMonstersInLevel();
                break;

        }
        for (int i = 0; i < listToAnalyze.length; i++) {
            if (listToAnalyze[i]!=null) {
                return true;
            }
        }
        return false;
    }


    /**
     * Description: This method register a player. It creates a Player object and saves it in the player array.
     * @param nickname String: It is the nickname of the player.
     * @param name String: It is the name of the player
     * @return boolean: If the register was successful, it returns true. If it was not, it returns false
     */
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


    /**
     * Description: This method checks that the player nickname is unique
     * @param nicknameToVerify String: It is the nickname that has to be checked
     * @return boolean: If the nickname is unique, it returns true. If it is not, it returns false
     */
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

    /**
     * Description: This method register the levels. It creates 10 Level objects and saves them in the level array.
     * @param growthConstant double: It saves the growth constant that is applied to increase the required score of the levels while creating them
     * @param requiredScore int: It saves the required score of the first level 
     * @return boolean: If the register was successful, it returns true. If it was not, it returns false
     */
    public boolean registerLevel(double growthConstant, int requiredScore) {
        for (int i = 0; i < listOfLevels.length; i++) {
            Level newLevel = new Level(Math.round(requiredScore));
            listOfLevels[i] = newLevel;
            requiredScore *= ((growthConstant/100)+1);
        }
        return true;
    }
   
    /**
     * Description: This method register a treasure. It create a Treasure object and saves it in the trasure array.
     * @param name String: It saves the name of the treasure
     * @param linkUrl String: It saves the url of the treasure
     * @param remPoints int: It saves the remuneration points  of the treasure
     * @return  boolean: If the register was successful, it returns true. If it was not, it returns false
     */
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

    /**
     * Description: This method register a treasure. It create a Treasure object and saves it in the trasure array.
     * @param name String: It saves the name of the monster
     * @param monsterIndicative int: It is the indicative of monster type
     * @param negativePoints int: It saves the negative points  of the monster
     * @param remPoints int: It saves the remuneration points  of the monster
     * @return boolean: If the register was successful, it returns true. If it was not, it returns false
     */
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



    /**
     * Description: This method creates a string that contains information about the levels and an indicative to each level
     * @return levels String: It saves the message of the indicative and information of the levels
     */
    public String showLevels() {

		String levels = "";

		for (int i = 0; i < listOfLevels.length; i++) {

			if (listOfLevels[i] != null) {

				levels += "\nNivel #" + (i+1) + "\n" + listOfLevels[i].toString();
			}

		}
		return levels;

	}

    /**
     * Description: This method creates a string that contains information about the treasures and an indicative to each treasure
     * @return treasures String: It saves the message of the indicative and information of the treasures
     */
    public String showTreasures() {

		String treasures = "";

		for (int i = 0; i < listOfTreasures.length; i++) {

			if (listOfTreasures[i] != null) {

                treasures += "\nTesoro #" + (i+1) + "\n" + listOfTreasures[i].toString();
			}

		}
		return treasures;

	}


    /**
     * Description: This method creates a string that contains information about the monsters and an indicative to each monster
     * @return monsters String: It saves the message of the indicative and information of the enemies
     */
    public String showMonsters() {

		String monsters = "";

		for (int i = 0; i < listOfMonsters.length; i++) {

			if (listOfMonsters[i] != null) {

                monsters += "\nEnemigo #" + (i+1) + "\n" + listOfMonsters[i].toString();
			}

		}
		return monsters;

	}


    /**
     * Description: This method creates a string that contains information about the players and an indicative to each player
     * @return monsters String: It saves the message of the indicative and information of the players
     */
    public String showPlayers() {

		String players = "";

		for (int i = 0; i < listOfPlayers.length; i++) {

			if (listOfPlayers[i] != null) {

                players += "\nJugador #" + (i+1) + "\n" + listOfPlayers[i].toString();
			}

		}
		return players;

	}


    /**
     * Description: This method associates a certain amount of treasure objects to a level saving them in the treasure array of a certain level chosen by the user.
     * @param idLevel int: It saves the level id of the selected level by the user
     * @param idTreasure int: It saves the treasure id of the selected treasure by the user
     * @param amountTreasures int: It saves the amount of treasures that the user wants to associate to determined level
     * @return boolean: If the association was successful, it returns true. If it was not, it returns false
     */
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


    /**
     * Description: This method associates a monster objects to a level saving it in the monster array of a certain level chosen by the user.
     * @param idLevel int: It saves the level id of the selected level by the user
     * @param idMonster int: It saves the monster id of the selected monster by the user
     * @return boolean: If the association was successful, it returns true. If it was not, it returns false
     */
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


    /**
     * Description: This method modifies the player score of a player selected by the user 
     * @param idPlayer int: It saves the player id of the player selected by the user
     * @param newScore int: It saves the new player score 
     * @return boolean: If the modification was successful, it returns true. If it was not, it returns false
     */
    public boolean modifyPlayerScore(int idPlayer, int newScore) {
        listOfPlayers[idPlayer].setInitialScore(newScore);
        identifyPlayerLevel(idPlayer);
        return true;
    }


    /**
     * Description: This method identifies and assigns the proper level to a certain player based on its score
     * @param idPlayer int: It saves the player id 
     * @return boolean: If the assignment was successful, it returns true. If it was not, it returns false
     */
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


    /**
     * Description: This method generates a random integer number between 0 and the upper limit 
     * @param upperRange int: It is the upper limit that delimits the interval where the new random number can be generated
     * @return int: It returns a random number between 0 and the upper limit
     */
    public int generatePosition(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange+1);
    }


    /**
     * Description: This method assigns the proper difficulty  to a level based on the remuneration points total of monsters and treasures
     * @param idLevel int: It saves the level id
     * @return levelDiff int: It is the difficulty identifier for the level
     */
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


    /**
     * Description: This method creates a message with the amount of each monster and treasure in a selected level
     * @param idLevel int: It saves the level id of the selected level
     * @return stats String: It is the message with the amount of each monster and treasure in a selected level
     */
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


    /**
     * Description: This method informs about the amount of a certain treasure in all the levels
     * @param idTreasure int: It is the treasure id of the selected treasure
     * @return treasureStats int: It is the amount of the selected treasure in the game
     */
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


    /**
     * Description: This method informs about the number of monsters of a selected monster type
     * @param idEnemyType int: This is the indicative of the monster type
     * @return monsterStats String: This is the string that contains the number of monsters of a selected monster type
     */
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


    /**
     * Description: This method informs about the most repeated treasure in the game
     * @return messageRepeatedTreasure String: This saves the string with the most repeated treasure in the game
     */
    public String informMostRepeatedTreasure() {
        int mostrepeatedTreasure = 0;
        String messageRepeatedTreasure = "El tesoro(s) más repetido(s) es: \n";
        for (int i = 1; i < listOfTreasures.length; i++) {
            if (listOfTreasures[i] != null) {
                if (informAmountTreasures(mostrepeatedTreasure)<=informAmountTreasures(i)) {
                    if (informAmountTreasures(mostrepeatedTreasure)<informAmountTreasures(i)) {
                        mostrepeatedTreasure = i;
                    } 
                }
            }
        }
        messageRepeatedTreasure += "\n" + listOfTreasures[mostrepeatedTreasure];
        for (int i = 0; i < listOfTreasures.length; i++) {
            if (listOfTreasures[i] != null){
                if ((informAmountTreasures(mostrepeatedTreasure)==informAmountTreasures(i)) && (i != mostrepeatedTreasure))  {
                    messageRepeatedTreasure += "\n" + listOfTreasures[i];
                }
            }
        }


        return messageRepeatedTreasure;
    }


    /**
     * Description: This method informs about the monster with the best remuneration and the levels where the monster is located
     * @return ubication String: This returns a string with the information about the monster with the best remuneration and the levels where the monster is located
     */
    public String bestRemMonster() {
        int bestMonster = 0;
        String ubication = "";
        for (int i = 1; i < listOfMonsters.length; i++) {
            if (listOfMonsters[i] != null) {
                if (listOfMonsters[bestMonster].getRemPoints()<listOfMonsters[i].getRemPoints()) {
                    bestMonster = i;
                }
            }
        }
        
        ubication += "\n| El enemigo(s) con mayor remuneración " + listOfMonsters[bestMonster] + " se encuentra en el nivel:";
            for (int f = 0; f < listOfLevels.length; f++) {
                for (int j = 0; j < listOfLevels[f].getListOfMonstersInLevel().length; j++) {
                    if (listOfLevels[f].getListOfMonstersInLevel()[j] != null) {
                        if (listOfMonsters[bestMonster].equals(listOfLevels[f].getListOfMonstersInLevel()[j])) {
                            ubication += " " + f + " |";
                        }
                    }
                }
            }
        for (int i = 0; i < listOfMonsters.length; i++) {
            
            if (listOfMonsters[i]!=null) {
                if (i != bestMonster && listOfMonsters[bestMonster].getRemPoints()==listOfMonsters[i].getRemPoints()){
                    ubication += "\n| El enemigo(s) con mayor remuneración " + listOfMonsters[i] + "se encuentra en el nivel:";
                    for (int f = 0; f < listOfLevels.length; f++) {
                        for (int j = 0; j < listOfLevels[f].getListOfMonstersInLevel().length; j++) {
                            if (listOfLevels[f].getListOfMonstersInLevel()[j] != null) {
                                if (listOfMonsters[i].equals(listOfLevels[f].getListOfMonstersInLevel()[j])) {
                                    ubication += " " + f + " |";
                                }
                            }
                        }
                    }
                }
            }
        }
        return ubication;
    }


    /**
     * Description: This method informs about the amount of consonants in a monster name and a total amount of consonants
     * @return numConsonants String: It saves the message with the amount of consonants in a monster name and a total amount of consonants
     */
    public String numberConsonantsMonster() {
        String numConsonants = "";
        int counter;
        int consonantsTotal = 0;
        for (int i = 0; i < listOfMonsters.length; i++) {
            counter = 0;
            if (listOfMonsters[i] != null) {
                char[] charName = listOfMonsters[i].getName().toCharArray();
                for (int j = 0; j < charName.length; j++) {
                    if (!(Character.toString(charName[j]).matches("a") || Character.toString(charName[j]).matches("A") || Character.toString(charName[j]).matches("e")||Character.toString(charName[j]).matches("E")||Character.toString(charName[j]).matches("i")||Character.toString(charName[j]).matches("I")||Character.toString(charName[j]).matches("o")||Character.toString(charName[j]).matches("O")||Character.toString(charName[j]).matches("u") || Character.toString(charName[j]).matches("U"))) {
                        counter++;
                    }
                }
                numConsonants += "\nEl nombre del enemigo " + listOfMonsters[i].getName() + " tiene " + counter +" consonantes.";
                consonantsTotal += counter;
            }
        }
        numConsonants += "\nTotal de consonantes:" + consonantsTotal;
        return numConsonants;
    }

    
    /**
     * Description: This method informs about the top 5 of players based in their score
     * @return topFIVE int[]: It is the array that contains the player id of the top 5 players
     */
    public int[] informTopPlayer() {
        int[] topFIVE = new int[5];
        int bestPlayer = 0;
            for (int j = 0; j < topFIVE.length; j++) {
                bestPlayer = 0;
                for (int i = 0; i < listOfPlayers.length; i++) {
                    if (listOfPlayers[i] != null){
                        if (!(i==topFIVE[0] || i==topFIVE[1] || i==topFIVE[2] || i==topFIVE[3] || i==topFIVE[4])) {
                            if (listOfPlayers[i].getInitialScore()>listOfPlayers[bestPlayer].getInitialScore()) {
                                bestPlayer=i;
                            }
                        }
                    }
                    
                }
                

                topFIVE[j]=bestPlayer;  
                
            }
            return topFIVE;
    }


    /**
     * Description: This method verifies if a treasure object exists
     * @return boolean: If there is a treasure object, it returns true. If there is not, it returns false
     */
    public boolean treasureValidation() {
        for (int i = 0; i < listOfTreasures.length; i++) {
            if (listOfTreasures[i]!= null) {
                return true;
            }
        }
        return false;
    }


    /**
     * Description: This method verifies if a monster object exists
     * @return boolean: If there is a monster object, it returns true. If there is not, it returns false
     */
    public boolean monsterValidation() {
        for (int i = 0; i < listOfMonsters.length; i++) {
            if (listOfMonsters[i]!= null) {
                return true;
            }
        }
        return false;
    }



    /**
     * Description: This method verifies if a player object exists
     * @return boolean: If there is a player object, it returns true. If there is not, it returns false
     */
    public boolean playerValidation() {
        for (int i = 0; i < listOfPlayers.length; i++) {
            if (listOfPlayers[i]!= null) {
                return true;
            }
        }
        return false;
    }


    /**
     * Description: This method verifies if a level object exists
     * @return boolean: If there is a level object, it returns true. If there is not, it returns false
     */
    public boolean levelValidation() {
        for (int i = 0; i < listOfLevels.length; i++) {
            if (listOfLevels[i]!= null) {
                return true;
            }
        }
        return false;
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
