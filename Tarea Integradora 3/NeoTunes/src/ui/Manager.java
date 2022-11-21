package ui;



import model.Controller;

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
        int choice;
        boolean exit = false;
        while ((exit == false)) {
            System.out.println("Que acción desea realizar?");
            System.out.println("""
                    1.  = Registrar usuario
                    2.  = Registrar canción
                    3.  = Registrar podcast
                    4.  = Crear Playlist
                    5.  = Editar Playlist
                    6.  = Compartir una Playlist
                    7.  = Reproducir audio
                    8.  = Comprar audio
                    9.  = Informe del acumulado total de reproducciones de la plataforma
                    10. = Informe del género más escuchado
                    11. = Informe de la categoria más escuchada
                    12. = Informe Top 5 artistas
                    13. = Informe Top 5 creadores de contenido
                    14. = Informe Top 10 canciones
                    15. = Informe Top 10 podcasts
                    16. = Informar el número de canciones vendidas y el valor total de ventas ($) por cada genero. 
                    17. = Informar el número total de ventas y el valor total de venta ($) de la canción más vendida.
                    18. = Salir""");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    regUser();
                    break;
                case 2:
                    regSong();
                    break;
                case 3:
                    regPodcast();
                    break;
                case 4:
                    createPlaylist();
                    break;
                case 5:
                    editPlaylist();
                    break;
                case 6:
                    sharePlaylist();
                    break;
                case 7:
                    reproduceAudio();
                    break;
                case 8:
                    buyAudio();
                    break;
                case 9:
                    System.out.println(controller.informTotalReproductions());
                    break;
                case 10:
                    System.out.println("Que tipo de informe desea?");
                    System.out.println("1 = Genero mas escuchado en la plataforma");
                    System.out.println("2 = Genero mas escuchado para un usuario a eleccion");
                    choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            informMostListenedGenre();
                            break;
                        case 2:
                            informUserMostListenedGenre();
                            break;
                    }
                    break;
                case 11:
                    System.out.println("Que tipo de informe desea?");
                    System.out.println("1 = Categoria mas escuchada en la plataforma");
                    System.out.println("2 = Categoria mas escuchada para un usuario a eleccion");
                    choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            informMostListenedCategory();
                            break;
                        case 2:
                            informUserMostListenedCategory();
                            break;
                    }
                    break;
                case 12:
                    System.out.println(controller.informTop5Artists());
                    break;
                case 13:
                    System.out.println(controller.informTop5ContentCreators());
                    break;
                case 14:
                    System.out.println(controller.informTop10Songs());
                    break;
                case 15:
                    System.out.println(controller.informTop10Podcasts());
                    break;
                case 16:
                    System.out.println(controller.amountSoldSongsPerGenre());
                    break;
                case 17:
                    System.out.println(controller.bestSellingSong());
                    break;
                case 18:
                    exit = true;
                    break;
                
            }
        }
        
        
    }
    
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
            int year = (Integer.parseInt(date.split("-")[0]))-1900;
            int month = (Integer.parseInt(date.split("-")[1]))-1;
            int day = Integer.parseInt(date.split("-")[2]);
            int type;
        switch (userType) {
            case 1:
                System.out.println("Escribe el tipo de usuario consumidor | 1 = Standard | 2 = Premium |");
                type = Integer.parseInt(sc.nextLine());
                if (controller.regConUser(nickname, id, year, month, day, type)) {
                    System.out.println("Registro exitoso");
                } else {
                    System.out.println("Error en el registro");
                }
                break;
            case 2:
                System.out.println("Escribe el tipo de usuario productor | 1 = Artista | 2 = Creador de contenido |");
                type = Integer.parseInt(sc.nextLine());
                System.out.println("Digie el URL de la photo");
                String urlPhoto = sc.nextLine();
                System.out.println("Digite el nombre del artista");
                String name = sc.nextLine();
                if (controller.regProdUser(nickname, id, year, month, day, urlPhoto, name, type)) {
                    System.out.println("Registro exitoso");
                } else {
                    System.out.println("Error en el registro");
                }
                break;
        }
    }

    public static void regSong() {
        if (controller.artistValidation()) {
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
        } else {
            System.out.println("No existen artistas");
        }

    }

    public static void regPodcast() {
        if (controller.podcastValidation()) {
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
        } else {
            System.out.println("No existen creadores de contenido");
        }
    }

    public static void createPlaylist() {
        if (controller.consumerUserValidation()) {
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
        } else {
            System.out.println("No existen usuarios consumidores");
        }
    }

    public static void editPlaylist() {
        if (controller.consumerUserValidation()) {
            System.out.println("¿Que accion desea realizar?  | 1 = eliminar cancion de playlist | 2 = agregar cancion a playlist |");
            int action = Integer.parseInt(sc.nextLine());
            System.out.println(controller.showConsumerUsers());
            System.out.println("Digite el identificador del usuario consumidor que posee la playlist");
            int idUser = (Integer.parseInt(sc.nextLine()))-1;
            if (controller.conUserPlaylistValidation(idUser)) {
                System.out.println(controller.showConUserPlaylists(idUser));
                System.out.println("Digite el identificador de la playlist");
                int idPlaylist = (Integer.parseInt(sc.nextLine()))-1;
                int idAudio;
                switch (action) {
                    case 1:
                        if (controller.conUserPlaylistAudioValidation(idUser, idPlaylist)) {
                            System.out.println(controller.showPlaylistAudios(idUser, idPlaylist));
                            System.out.println("Digite el identificador del audio a eliminar");
                            idAudio = (Integer.parseInt(sc.nextLine()))-1;
                            if (controller.deleteAudioFromPlaylist(idUser, idPlaylist, idAudio)) {
                                System.out.println("Audio eliminado correctamente");
                            } else {
                                System.out.println("Hubo un error");
                            }
                        } else {
                            System.out.println("La playlist escogida no tiene audios");
                        }
                        break;
                    case 2:
                        if (controller.songsValidation() || controller.podcastValidation()) {
                            System.out.println(controller.showAudios());
                            System.out.println("Digite el identificador del audio a agregar");
                            idAudio = (Integer.parseInt(sc.nextLine()))-1;
                            if (controller.addAudioToPlaylist(idUser, idPlaylist, idAudio)) {
                                System.out.println("Audio agregado correctamente");
                            } else {
                                System.out.println("Hubo un error");
                            }
                        } else {
                            System.out.println("No existen audios para agregar");
                        }
                        break;
                }
            } else {
                System.out.println("El usuario elegido no tiene playlists creadas");
            }
        } else {
            System.out.println("No existen usuarios consumidores");
        }
    }

    public static void sharePlaylist() {
        if (controller.consumerUserValidation()) {
            System.out.println(controller.showConsumerUsers());
            System.out.println("Digite el identificador del usuario consumidor que posee la playlist");
            int idUser = (Integer.parseInt(sc.nextLine()))-1;
            if (controller.conUserPlaylistValidation(idUser)) {
                System.out.println(controller.showConUserPlaylists(idUser));
                System.out.println("Digite el identificador de la playlist");
                int idPlaylist = (Integer.parseInt(sc.nextLine()))-1;
                System.out.println(controller.sharePlaylist(idUser, idPlaylist));
            } else {
                System.out.println("El usuario elegido no tiene playlists creadas");
            }
        } else {
            System.out.println("No existen usuarios consumidores");
        }
    }

    public static void reproduceAudio() {
        if (controller.consumerUserValidation()) {
            System.out.println(controller.showConsumerUsers());
            System.out.println("Digite el identificador del usuario consumidor que posee el audio a reproducir");
            int idUser = (Integer.parseInt(sc.nextLine()))-1;
            System.out.println("Seleccione que el origen del audio a reproducir | 1 = Audio adquirido | 2 = Audio desde playlist |");
            int originType = Integer.parseInt(sc.nextLine());
            int idAudio;
            switch (originType) {
                case 1:
                    if (controller.conUserAdquiredAudioValidation(idUser)) {
                        System.out.println(controller.showUserAdquiredAudios(idUser));
                        System.out.println("Digite el identificador del audio a reproducir");
                        idAudio = (Integer.parseInt(sc.nextLine()))-1;
                        System.out.println(controller.reproduceAudio(idUser, idAudio));
                    } else {
                        
                    }
                    break;
                case 2:
                    if (controller.conUserPlaylistValidation(idUser)) {
                        System.out.println(controller.showConUserPlaylists(idUser));
                        System.out.println("Digite el identificador de la playlist");
                        int idPlaylist = (Integer.parseInt(sc.nextLine()))-1;
                        if (controller.conUserPlaylistAudioValidation(idUser, idPlaylist)) {
                            System.out.println(controller.showPlaylistAudios(idUser, idPlaylist));
                            System.out.println("Digite el identificador del audio a reproducir");
                            idAudio = (Integer.parseInt(sc.nextLine()))-1;
                            System.out.println(controller.reproduceAudio(idUser, idPlaylist, idAudio));
                        } else {
                            System.out.println("La playlist escogida está vacia");
                        }
                    } else {
                        System.out.println("El usuario elegido no tiene playlists creadas");
                    }
                    break;
            }
        } else {
            System.out.println("No existen usuarios consumidores");
        }
        
    }


    public static void buyAudio() {
        if (controller.songsValidation() || controller.podcastValidation()) {
            int idAudio;
            System.out.println("Escribe la fecha de compra (YYYY-MM-DD)");
            String date = sc.nextLine();
            int year = (Integer.parseInt(date.split("-")[0]))-1900;
            int month = (Integer.parseInt(date.split("-")[1]))-1;
            int day = Integer.parseInt(date.split("-")[2]);
            System.out.println(controller.showAudios());
            System.out.println("Digite el identificador del audio a comprar");
            idAudio = (Integer.parseInt(sc.nextLine()))-1;
            System.out.println(controller.showConsumerUsers());
            if (controller.consumerUserValidation()) {
                System.out.println("Digite el identificador del usuario consumidor que va a comprar el audio");
                int idUser = (Integer.parseInt(sc.nextLine()))-1;
                if (controller.buyAudio(idUser, idAudio, year, month, day)) {
                    System.out.println("Audio comprado exitosamente");
                } else {
                    System.out.println("Error en la compra");
                }
            } else {
                System.out.println("No existen usuarios consumidores");
            }
        } else {
            System.out.println("No existen audios en la plataforma");
        }
    }

    public static void informUserMostListenedGenre() { 
        if (controller.consumerUserValidation()) {
            System.out.println(controller.showConsumerUsers());
            int idUser = Integer.parseInt(sc.nextLine())-1;
            System.out.println(controller.userMostListenedGenre(idUser));
        } else {
            System.out.println("No hay usuarios consumidores");
        }

    }

    public static void informMostListenedGenre() {
        System.out.println(controller.informMostListenedGenre());
    }

    public static void informUserMostListenedCategory() { 
        if (controller.consumerUserValidation()) {
            System.out.println(controller.showConsumerUsers());
            int idUser = Integer.parseInt(sc.nextLine())-1;
            System.out.println(controller.userMostListenedCategory(idUser));
        } else {
            System.out.println("No hay usuarios consumidores");
        }

    }

    public static void informMostListenedCategory() {
        System.out.println(controller.informMostListenedCategory());
    }



    

}
