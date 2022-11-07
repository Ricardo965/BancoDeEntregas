package ui;

import model.Controller;
import model.Playlist;
import model.Podcast;
import model.Song;

import java.util.Scanner;

public class Manager {
    public static Scanner sc = new Scanner(System.in);
    public static Controller controller = new Controller();

    public static void main(String[] args) {
        genMenu();
    }   
    
    /**
     * Description: This method displays the general menu 
     */
    public static void genMenu() {
        
        boolean exit = false;
        while ((exit == false)) {
            System.out.println("Que acción desea realizar?");
            System.out.println("""
                    1.  = Registrar usuario (Prueba id numerico)
                    2.  = Registrar canción
                    3.  = Registrar podcast
                    4.  = Crear Playlist
                    5.  = Editar Playlist
                    6.  = Compartir una Playlist
                    7.  = Modificar el puntaje de un jugador
                    8.  = Mostrar el nivel de un jugador
                    9.  = Mostrar la dificultad de un nivel
                    10. = Desplegar menu de informes
                    11. = Seleccionar resolución
                    12. = Salir""");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    Playlist newPLay = new Playlist("Pruebita");
                    //newPLay.addAudio();
                    System.out.println(newPLay.printNumericMatrix());
                    System.out.println(newPLay.getNumericId());
                    break;
                case 2:
                    
                    break;
                case 3:
                
                    break;
                case 4:
            
                    break;
                case 5:
                  
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    
                    break;
                case 9:
                    
                    break;
                case 10:
                    
                    break;
                case 11:
                    
                    break;
                default:
                    exit = true;
                    break;
            }
        }
        
        
    }
    /*
     * System.out.println("Type the new User's sign up date (YYYY-MM-DD): ");
        String date = reader.nextLine();

        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
    */



    public static void regUser() {
        System.out.println("""
                Digite el tipo de usuario 
                1.  = Registrar usuario consumidor
                2.  = Registrar usuario productor""");
        byte userType = Byte.parseByte(sc.nextLine());
        System.out.println("Escribe el nickname del usuario ");
        String nickname = sc.nextLine();
        System.out.println("Escribe la cedula del usuario");
        String id = sc.nextLine();
        System.out.println("Escribe la fecha de vinculación (YYYY-MM-DD)");
        String date = sc.nextLine();
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
        int type;
        switch (userType) {
            case 1:
                System.out.println("Escribe el tipo de usuario consumidor | 1 = Standard | 2 = Premium |");
                type = Integer.parseInt(sc.nextLine());
                controller.regConUser(nickname, id, year, month, day, type);

                break;
            case 2:
                System.out.println("Escribe el tipo de usuario productor | 1 = Artista | 2 = Creador de contenido |");
                type = Integer.parseInt(sc.nextLine());
                String urlPhoto = sc.nextLine();
                String name = sc.nextLine();
                controller.regProdUser(nickname, id, year, month, day, urlPhoto, name, type);
                break;
            default:
                break;
        }
    }

    public static void regSong() {
        System.out.println(controller.showArtists());
        System.out.println("Digite el identificador del artista");
        int idArtist = (Integer.parseInt(sc.nextLine()))-1;
        System.out.println("Digite el nombre de la canción");
        String name = sc.nextLine(); 
        System.out.println("Digite la URL de la imagen de la canción");
        String url = sc.nextLine(); 
        System.out.println("Digite la duración (Ej: 3:09) ");
        String duration = sc.nextLine(); 
        int mins = Integer.parseInt(duration.split(":")[0]);
        int segs = Integer.parseInt(duration.split(":")[1]);
        System.out.println("Digite el precio de la canción");
        double price = Double.parseDouble(sc.nextLine()); 
        System.out.println("Digite el nombre del album");
        String album = sc.nextLine(); 
        System.out.println("Digite el indicativo del género | 1 =  ROCK | 2 = POP | 3 = TRAP | 4 = HOUSE |");
        int genreIndicative = (Integer.parseInt(sc.nextLine()))-1;
        if (controller.regSong(idArtist, name, url, mins, segs, price, album, genreIndicative)) {
            System.out.println("Canción registrada exitosamente");
        } else {
            System.out.println("Error en el registro de la canción");
        }

    }

    public static void regPodcast() {
        System.out.println(controller.showContentCreators());
        System.out.println("Digite el identificador del creador de contenido");
        int idCCreator = (Integer.parseInt(sc.nextLine()))-1;
        System.out.println("Digite el nombre del podcast");
        String name = sc.nextLine(); 
        System.out.println("Digite la URL de la imagen del podcast");
        String url = sc.nextLine(); 
        System.out.println("Digite la duración (Ej: 3:09) ");
        String duration = sc.nextLine(); 
        int mins = Integer.parseInt(duration.split(":")[0]);
        int segs = Integer.parseInt(duration.split(":")[1]);
        System.out.println("Digite la descripcion");
        String description = sc.nextLine(); 
        System.out.println("Digite el indicativo de la categoria | 1 =  POLITICA | 2 = ENTRETENIMIENTO | 3 = VIDEOJUEGOS | 4 = MODA |");
        int categoryIndicative = (Integer.parseInt(sc.nextLine()))-1;
        if (controller.regPodcast(idCCreator, name, url, mins, segs, description, categoryIndicative)) {
            System.out.println("Podcast registrado exitosamente");
        } else {
            System.out.println("Error en el registro del podcast");
        }
    }

    public static void createPlaylist() {
        System.out.println(controller.showConsumerUsers());
        System.out.println("Digite el identificador del creador de contenido");
        int idConUser = (Integer.parseInt(sc.nextLine()))-1;
        System.out.println("Digite el nombre de la playlist");
        String name = sc.nextLine(); 
        if (controller.createPlaylist(idConUser, name)) {
            System.out.println("Playlist creada exitosamente");
        } else {
            System.out.println("Error en el registro del playlist");
        }
    }

    public static void editPlaylist() {
        System.out.println("¿Que accion desea realizar?  | 1 = eliminar cancion de playlist | 2 = agregar cancion a playlist |");
        int action = Integer.parseInt(sc.nextLine());
        System.out.println(controller.showConsumerUsers());
        System.out.println("Digite el identificador del usuario consumidor que posee la playlist");
        int idUser = (Integer.parseInt(sc.nextLine()))-1;
        System.out.println(controller.showConUserPlaylists(idUser));
        System.out.println("Digite el identificador de la playlist");
        int idPlaylist = (Integer.parseInt(sc.nextLine()))-1;
        int idAudio;
        switch (action) {
            case 1:
                System.out.println(controller.showPlaylistAudios(idUser, idPlaylist));
                System.out.println("Digite el identificador del audio a eliminar");
                idAudio = (Integer.parseInt(sc.nextLine()))-1;
                if (controller.deleteAudioFromPlaylist(idUser, idPlaylist, idAudio)) {
                    System.out.println("Audio eliminado correctamente");
                } else {
                    System.out.println("Hubo un error");
                }
                break;
            case 2:
                System.out.println(controller.showAudios());
                System.out.println("Digite el identificador del audio a agregar");
                idAudio = (Integer.parseInt(sc.nextLine()))-1;
                if (controller.addAudioToPlaylist(idUser, idPlaylist, idAudio)) {
                    System.out.println("Audio agregado correctamente");
                } else {
                    System.out.println("Hubo un error");
                }
                break;
            default:
                break;
        }
    }

    public static void sharePlaylist() {
        System.out.println(controller.showConsumerUsers());
        System.out.println("Digite el identificador del usuario consumidor que posee la playlist");
        int idUser = (Integer.parseInt(sc.nextLine()))-1;
        System.out.println(controller.showConUserPlaylists(idUser));
        System.out.println("Digite el identificador de la playlist");
        int idPlaylist = (Integer.parseInt(sc.nextLine()))-1;
        System.out.println(controller.sharePlaylist(idUser, idPlaylist));
    }



    

}
