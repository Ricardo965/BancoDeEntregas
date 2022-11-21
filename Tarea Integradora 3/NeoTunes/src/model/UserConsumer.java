package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class UserConsumer extends User implements Reproduction {

    private ArrayList<Song> listOfListenedSongs = new ArrayList<Song>();

    public ArrayList<Song> getListOfListenedSongs() {
        return listOfListenedSongs;
    }

    public void setListOfListenedSongs(ArrayList<Song> listOfListenedSongs) {
        this.listOfListenedSongs = listOfListenedSongs;
    }

    private ArrayList<Podcast> listOfListenedPodcasts = new ArrayList<Podcast>();
    

    public ArrayList<Podcast> getListOfListenedPodcasts() {
        return listOfListenedPodcasts;
    }

    public void setListOfListenedPodcasts(ArrayList<Podcast> listOfListenedPodcasts) {
        this.listOfListenedPodcasts = listOfListenedPodcasts;
    }

    public UserConsumer(String nickname, String id, Date date) {
        super(nickname, id, date);
    }
    
    public boolean addPlaylist(Playlist playlistToAdd) {
        return false;
    }

    public Playlist searchPlaylist(String numericId) {
        return null;
    }
    

    public String showPlaylists() {
        String playlists = "";

        return playlists;
    }

    public String showAdquiredAudios() {
        String adquiredAudios = "";

        return adquiredAudios;
    }

    public boolean addAdquiredAudio(AdquiredAudio purchasedAudioToAdd){
        return false;
    }


    public void registerListenedAudio(Audio audioToCheck) { // revisar el equals
        boolean flag = true;

        if (audioToCheck instanceof Song) {
            for (int i = 0; i < listOfListenedSongs.size(); i++) {
                if (((listOfListenedSongs.get(i)).equals(((Song)audioToCheck)))) {
                    listOfListenedPodcasts.get(i).setAmountReproductions(listOfListenedPodcasts.get(i).getAmountReproductions()+1);
                    flag = false;
                } 
            }
            if (flag) {
                listOfListenedSongs.add(new Song((Song)audioToCheck));
            }
        } else if (audioToCheck instanceof Podcast) {
            for (int i = 0; i < listOfListenedPodcasts.size(); i++) {
                if (((listOfListenedPodcasts.get(i)).equals(((Podcast)audioToCheck)))) {
                    listOfListenedPodcasts.get(i).setAmountReproductions(listOfListenedPodcasts.get(i).getAmountReproductions()+1);
                    flag = false;
                }
            }
            if (flag) {
                listOfListenedPodcasts.add(new Podcast((Podcast)audioToCheck));
            }
        }
    }


    public String mostListenedGenre() {
        if (!listOfListenedSongs.isEmpty()) {
            int[] genreAmount = {0,0,0,0};
            String[] genreName = {"Rock", "Pop" , "Trap" , "House" };
            for (int i = 0; i < getListOfListenedSongs().size(); i++) {
                if (getListOfListenedSongs().get(i) instanceof Song) {
                    
                    switch (((Song)getListOfListenedSongs().get(i)).getGenre()) {
                        case ROCK:
                            genreAmount[0] = genreAmount[0] + getListOfListenedSongs().get(i).getAmountReproductions();
                            break;

                        case POP:
                            genreAmount[1] = genreAmount[1] + getListOfListenedSongs().get(i).getAmountReproductions();
                            break;

                        case TRAP:
                            genreAmount[2] = genreAmount[2] + getListOfListenedSongs().get(i).getAmountReproductions();
                            break;

                        case HOUSE:
                            genreAmount[3] = genreAmount[3] + getListOfListenedSongs().get(i).getAmountReproductions();
                            break;

                        default:
                            break;
                    }
                } 
            }
            int mostInd = 0;
            for (int i = 1; i < genreAmount.length; i++) {
                if (genreAmount[mostInd] < genreAmount[i]) {
                    mostInd = i;
                }
            }

            return "El género más repetido del usuario es " + genreName[mostInd] + " con " + genreAmount[mostInd] + " reproducciones";
        } else {
            return "No existen canciones escuchadas por el usuario";
        }
    }

    public String mostListenedCategory() {
        if (!listOfListenedPodcasts.isEmpty()) {
            int[] categoryAmount = {0,0,0,0};
            String[] categoryName = {"Politica", "Entretenimiento" , "Videojuegos" , "Moda" };
            for (int i = 0; i < listOfListenedPodcasts.size(); i++) {
                if (listOfListenedPodcasts.get(i) instanceof Podcast) {
                    
                    switch (((Podcast)listOfListenedPodcasts.get(i)).getCategory()) {
                        case POLITICS:
                            categoryAmount[0] = categoryAmount[0] + listOfListenedPodcasts.get(i).getAmountReproductions();
                            break;

                        case ENTERTAINMENT:
                            categoryAmount[1] = categoryAmount[1] + listOfListenedPodcasts.get(i).getAmountReproductions();
                            break;

                        case VIDEOGAMES:
                            categoryAmount[2] = categoryAmount[2] + listOfListenedPodcasts.get(i).getAmountReproductions();
                            break;

                        case FASHION:
                            categoryAmount[3] = categoryAmount[3] + listOfListenedPodcasts.get(i).getAmountReproductions();
                            break;

                        default:
                            break;
                    }
                } 
            }
            int mostInd = 0;
            for (int i = 1; i < categoryAmount.length; i++) {
                if (categoryAmount[mostInd] < categoryAmount[i]) {
                    mostInd = i;
                }
            }

            return "La categoria más repetido del usuario es " + categoryName[mostInd] + " con " + categoryAmount[mostInd] + " reproducciones";
        } else {
            return "No existen podcasts escuchados por el usuario";
        }
    }

    public abstract boolean playlistValidation();
    public abstract boolean playlistAudioValidation(int idPlaylist);
    public abstract boolean adquiredAudioValidation();

}
