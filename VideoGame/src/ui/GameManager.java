package ui;



import java.util.Scanner;
import model.GameController;

public class GameManager {
    public static Scanner sc = new Scanner(System.in);
    public static GameController controller = new GameController();
    public static void main(String[] args) {
        genMenu();
    }   
    
    /**
     * Description: This method displays the general menu 
     */
    public static void genMenu() {
        resolution();
        boolean exit = false;
        while ((exit == false)) {
            System.out.println("Que acción desea realizar?");
            System.out.println("""
                    1.  = Registrar jugador
                    2.  = Registrar nivel
                    3.  = Registrar tesoro
                    4.  = Registrar enemigo
                    5.  = Asociar tesoro a nivel
                    6.  = Asociar enemigo a nivel
                    7.  = Modificar el puntaje de un jugador
                    8.  = Mostrar el nivel de un jugador
                    9.  = Mostrar la dificultad de un nivel
                    10. = Desplegar menu de informes
                    11. = Seleccionar resolución
                    12. = Salir""");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    regPlayer();
                    break;
                case 2:
                    regLevel();
                    break;
                case 3:
                    regTreasure();
                    break;
                case 4:
                    regMonster();
                    break;
                case 5:
                    associateTreasure();
                    break;
                case 6:
                    associateMonster();
                    break;
                case 7:
                    modifyPlayerScore();
                    break;
                case 8:
                    identifyPlayerLevel();
                    break;
                case 9:
                    showLevelDifficulty();
                    break;
                case 10:
                    informMenu();
                    break;
                case 11:
                    resolution();
                    break;
                default:
                    exit = true;
                    break;
            }
        }
        
        
    }

    /**
     * Description: This method displays a menu with some reporting options
     */
    public static void informMenu() {
        boolean exit = false;
        while (exit == false) {
            System.out.println("Que acción desea realizar?");
            System.out.println("""
                1. = Informar los tesoros y enemigos de un nivel
                2. = Informar la cantidad encontrada de un tesoro en todos los niveles
                3. = Informar la cantidad encontrada de un tipo de enemigo en todos los niveles
                4. = Informar el tesoro más repetido en todos los niveles.
                5. = Informar el enemigo que otorga mayor puntaje y el nivel donde se ubica.
                6. = Informar la cantidad de consonantes encontradas en los nombres de los enemigos del juego.
                7. = Informar el top 5 de los jugadores de acuerdo al puntaje.
                8. = Salir del menu de informes""");

            int election = Integer.parseInt(sc.nextLine());
            switch (election) {
                case 1:
                    informTreasureMonstersInLevel();
                    break;
                case 2:
                    informAmountTreasures();
                    break;
                case 3:
                    informAmountMonstersType();
                    break;
                case 4:
                    informMostRepeatedTreasure();
                    break;
                case 5:
                    if (controller.monsterValidation() && controller.levelValidation()) {
                        System.out.println(controller.bestRemMonster());
                    } else {
                        System.out.println("Por favor registre enemigos y/o niveles antes de solicitar esta información");
                    }
                    break;
                case 6:
                    if (controller.monsterValidation()) {
                        System.out.println(controller.numberConsonantsMonster());
                    } else {
                        System.out.println("Por favor registre enemigos antes de solicitar esta información");
                    }
                    break;
                case 7:
                    informTopPlayer();
                    break;
                default:
                    exit = true;
                    break;
            }
        }
    }

    /**
     * Description: This method displays the most repeated tresure inform
     */
    public static void informMostRepeatedTreasure() {
        if (controller.treasureValidation()){
            System.out.println(controller.informMostRepeatedTreasure());
        } else {
            System.out.println("No existen tesoros");
        }
        
    }


    /**
     * Description: This method displays an inform with the amount of a monsters of a monster type selected by the user
     */
    public static void informAmountMonstersType() {
        if (controller.monsterValidation() && controller.levelValidation()) {
            System.out.println("Digite el tipo de enemigo | 1 = Ogro |  2 = Abstracto | 3 = Jefe | 4 = Mágico |");
            int idEnemyType = Integer.parseInt(sc.nextLine());
            System.out.println(controller.informAmountMonstersType(idEnemyType-1));
        } else {
            System.out.println("No existen enemigos y/o niveles en el juego");
        }
    }


    /**
     * Description: This method displays an inform with the amount of a treaasures of a treasure selected by the user
     */
    public static void informAmountTreasures() {
        if (controller.treasureValidation() && controller.levelValidation()){
            System.out.println(controller.showTreasures());
            System.out.println("Digite el identificador del tesoro");
            int idTreasure = Integer.parseInt(sc.nextLine());
            System.out.println("La cantidad del tesoro seleccionado es: " + controller.informAmountTreasures(idTreasure-1));
        } else {
            System.out.println("No existen tesoros");
        }
        
    }


    /**
     * Description: This method displays the top 5 of players based on their score
     */
    public static void informTopPlayer() {
        String topPlayers = "";
        if (controller.playerValidation()) {
            controller.informTopPlayer();
            for (int i = 0; i < controller.informTopPlayer().length; i++) {
                topPlayers += "\nJugador en el top " + (i+1) + " con la puntuación " + controller.getListOfPlayers()[controller.informTopPlayer()[i]].getInitialScore();
            }
        } else {
            System.out.println("Registre jugadores antes de solicitar el top");
        }
        System.out.println(topPlayers);
    }



    /**
     * Description: This method informs about the amount of monsters and treasures in a selected level
     */
    public static void informTreasureMonstersInLevel() {
        if (controller.levelValidation()) {
            if (controller.monsterValidation() || controller.treasureValidation()) {
                System.out.println(controller.showLevels());
                System.out.println("Digite el identificador del nivel para el cual quiere conocer los tesoros y enemigos");
                int idLevel = Integer.parseInt(sc.nextLine());
                if (controller.isThereObjects(1, idLevel-1) || controller.isThereObjects(2, idLevel-1)) {
                    System.out.println(controller.informMonstersTreasuresLevel(idLevel-1));
                } else {
                    System.out.println("Error! Asocie un tesoro o enemigo al nivel seleccionado previamente.");
                }
            } else {
                System.out.println("No existen enemigos y/o tesoros en el juego");
            }
        } else {
            System.out.println("Registre los niveles antes de solicitar esta información");
        }
    }


    /**
     * Description: This method asks for the needed information to set the game resolution
     */
    public static void resolution() {
        System.out.println("Digite la resolución");
        System.out.println("| 1 = SD | 2 = QHD | 3 = HD | 4 = FHD | 5 = WQHD | 6 = UHD | 7 = UHD8K |");
        int resolutionIndicative = Byte.parseByte(sc.nextLine());
        if (controller.setResolution(resolutionIndicative)) {
            System.out.println("Resolución seleccionada correctamente");
        }
    }
    

    /**
     * Description: This method asks for the needed information to register a player
     */
    public static void regPlayer() {
        System.out.println("Digite el nombre del jugador");
        String name = sc.nextLine();
        System.out.println("Digite el nickname del jugador");
        String nickname = sc.nextLine();
        if (controller.verifyNickname(nickname)) {
            if (controller.registerPlayer(nickname, name)) {
                System.out.println("Jugador registrado exitosamente");
            } else {
                System.out.println("Error en el registro, espacio insuficiente");
            }

        } else {
            System.out.println("Digite un nickname diferente, el digitado ya está en uso");
        }
    }



    /**
     * Description: This method asks for the needed information to register a level
     */
    public static void regLevel() {
        System.out.println("Digite un puntaje inicial para el nivel 1");
        int requiredScore = Integer.parseInt(sc.nextLine());
        System.out.println("Digite una constante de crecimiento para aplicarla a los niveles superiores al 1");
        System.out.println("Ejemplo: 10% | Los niveles siguientes al 1 tendrán un 10% de puntaje requerido mayor al inmediatamente anterior");
        double growthConstant = Integer.parseInt(sc.nextLine());
        if (controller.registerLevel(growthConstant, requiredScore)) {
            System.out.println("Nivel registrado exitosamente");
        } else {
            System.out.println("Error en el registro del nivel");
        }
    }



    /**
     * Description: This method asks for the needed information to register a treasure
     */
    public static void regTreasure() {
        System.out.println("Digite el nombre del tesoro");
        String name = sc.nextLine();
        System.out.println("Digite la URL de la imagen del tesoro");
        String linkUrl = sc.nextLine();
        System.out.println("Digite la cantidad de puntos que otorga al ser encontrado");
        int remPoints = Integer.parseInt(sc.nextLine());
        if (controller.registerTreasure(name, linkUrl, remPoints)) {
            System.out.println("Tesoro registrado exitosamente");
        } else {
            System.out.println("Error en el registro de tesoro");
        }
    }




    /**
     * Description: This method asks for the needed information to register a monster
     */
    public static void regMonster() {
        System.out.println("Digite el nombre del mounstro");
        String name = sc.nextLine();
        System.out.println("Digite el tipo de mounstro");
        System.out.println("| 1 = OGRE | 2 = ABSTRACT | 3 = BOSS | 4 = MAGICAL |");
        int monsterIndicative = (Integer.parseInt(sc.nextLine())-1);
        System.out.println("Digite los puntos que diminuye");
        int negativePoints = Integer.parseInt(sc.nextLine());
        System.out.println("Digite los puntos de remuneración");
        int remPoints = Integer.parseInt(sc.nextLine());
        if (controller.registerMonster(name, monsterIndicative, negativePoints, remPoints)) {
            System.out.println("Enemigo registrado exitosamente");
        } else {
            System.out.println("Error en el registro de enemigo");
        }
    }




    /**
     * Description: This method asks for the needed information to associate a treasure to a level
     */
    public static void associateTreasure() {
        if (controller.treasureValidation()) {
            System.out.println(controller.showLevels());
            System.out.println("Digite el indice del nivel al cual le quiere asociar tesoros");
            int idLevel = Integer.parseInt(sc.nextLine());
            System.out.println(controller.showTreasures());
            System.out.println("Digite el indice del tesoro que quiere asociar");
            int idTreasure = Integer.parseInt(sc.nextLine());
            System.out.println("Digite la cantidad de tesoros de este tipo que quiere en el nivel elegido");
            int amountTreasures = Integer.parseInt(sc.nextLine());
            if (controller.associateTreasureToLevel(idLevel-1, idTreasure-1, amountTreasures)) {
                System.out.println("Tesoros asociados correctamente");
            } else {
                System.out.println("Error en la asociación de tesoros");
            }
        } else {
            System.out.println("No existen tesoros para asociar");
        }
    }



    /**
    * Description: This method asks for the needed information to associate a monster to a level
    */
    public static void associateMonster() {
        if (controller.monsterValidation()) {
            System.out.println(controller.showLevels());
            System.out.println("Digite el indice del nivel al cual le quiere asociar tesoros");
            int idLevel = Integer.parseInt(sc.nextLine());
            System.out.println(controller.showMonsters());
            System.out.println("Digite el indice del monstruo que quiere asociar");
            int idMonster = Integer.parseInt(sc.nextLine());
            if (controller.associateMonsterToLevel(idLevel-1, idMonster-1)) {
                System.out.println("Monstruo asociado correctamente");
            } else {
                System.out.println("Error! El monstruo ya se encuentra registrado en ese nivel");
            }
        } else {
            System.out.println("No existe enemigo para asociar");
        }
    }


    /**
     * Description: This method asks for the needed information to modify a player score
     */
    public static void modifyPlayerScore() {
        if (controller.playerValidation()) {
            System.out.println(controller.showPlayers());
            System.out.println("Digite el indice del jugador al cual quiere modificar el puntaje");
            int idPlayer = Integer.parseInt(sc.nextLine());
            System.out.println("Digite el nuevo puntaje");
            int newScore = Integer.parseInt(sc.nextLine());
            if (controller.modifyPlayerScore(idPlayer-1, newScore)) {
                System.out.println("Puntaje modificado exitosamente");            
            }
        } else {
            System.out.println("No existe jugador");
        }

    }



    /**
     * Description: This method shows the level of a selected player and the required score to advance to the next level
     */
    public static void identifyPlayerLevel() {
        if (controller.playerValidation()) {
            System.out.println(controller.showPlayers());
            System.out.println("Digite el indice del jugador al cual quiere saber el nivel");
            int idPlayer = Integer.parseInt(sc.nextLine());
            System.out.println("El nivel del jugador es: " + (controller.identifyPlayerLevel(idPlayer-1)+1));
            System.out.println("El puntaje necesario para aumentar de nivel es: " + controller.getListOfLevels()[(controller.identifyPlayerLevel(idPlayer-1))].getRequiredScore());
        } else {
            System.out.println("No existe jugador");
        }
    }




    /**
     * Description: This method the difficulty of a selected level
     */
    public static void showLevelDifficulty() {
       if (controller.levelValidation()) {
            System.out.println(controller.showLevels());
            System.out.println("Digite el indice del nivel para el cual quiere conocer la dificultad");
            int idLevel = Integer.parseInt(sc.nextLine());
            System.out.println("La dificultad del nivel seleccionado es" + "\nNivel #" + idLevel + " " + controller.getListOfLevels()[idLevel-1].getDifficulty());
       } else {
        System.out.println("No existen niveles");
       }
    }
}
