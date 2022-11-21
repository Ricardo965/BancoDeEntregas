package model;

import java.util.ArrayList;
import java.util.Date;



public class Controller {
    
    ArrayList<User> listOfUsers = new ArrayList<User>();
    ArrayList<Audio> listOfAudios = new ArrayList<Audio>();



    public boolean regConUser(String nickname, String id, int year, int month, int day, int type) {
        UserConsumer newUser;
        switch (type) {
            case 1:
                newUser = new Standard(nickname, id, new Date(  year,  month,  day));
                return listOfUsers.add(newUser);
            case 2:
                newUser = new Premium(nickname, id, new Date(  year,  month,  day));
                return listOfUsers.add(newUser);  
        }

        return false;
    }

    public boolean regProdUser(String nickname, String id, int year, int month, int day, String urlPhoto, String name, int type) {
        UserProducer newUser;
        switch (type) {
            case 1:
                newUser = new Artist(nickname, id, new Date(  year,  month,  day), urlPhoto, name);
                return listOfUsers.add(newUser);
            case 2:
                newUser = new ContentCreator(nickname, id, new Date(  year,  month,  day), urlPhoto, name);
                return listOfUsers.add(newUser);
        }
        return false;
    }

    public String showAudios() {
        String audios = "";
        String type = "";
        String numSales = "";
        if (!listOfAudios.isEmpty()) {
            for (int i = 0; i < listOfAudios.size(); i++) {


                if (listOfAudios.get(i) instanceof Song) {
                    type = "Cancion";
                    numSales += "\nNumero de ventas: " + String.valueOf(((Song)listOfAudios.get(i)).getNumSales());
                } else {
                    type = "Podcast";
                }
                
                audios += "\nAudio #" + (i+1) +  numSales + "\nTipo: "+ type +"\n" + (listOfAudios.get(i)).toString();
            }
        }

        return audios;
    }


    public String showArtists() {

		String artists = "";

		for (int i = 0; i < listOfUsers.size(); i++) {

			if (!listOfUsers.isEmpty()) {
                if (listOfUsers.get(i) instanceof Artist) {
                    artists += "\nUsuario Artista #" + (i+1) + "\n" + ( (UserProducer) listOfUsers.get(i)).toString();
                }
			}

		}
		return artists;

	}

    public String showContentCreators() {

		String cCreators = "";

		for (int i = 0; i < listOfUsers.size(); i++) {

			if (!listOfUsers.isEmpty()) {
                if (listOfUsers.get(i) instanceof ContentCreator) {
                    cCreators += "\nUsuario Creador de contenido #" + (i+1) + "\n" + ( (UserProducer) listOfUsers.get(i)).toString();
                }
			}

		}
		return cCreators;

	}

    public String showConsumerUsers() {

		String conUser = "";
        if (!listOfUsers.isEmpty()) {
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i) instanceof UserConsumer) {
                    conUser += "\nUsuario consumidor #" + (i+1) + "\n" + (listOfUsers.get(i)).toString();
                }

            }
        }
		
        
		return conUser;

	}

    public boolean regSong(int idArtist, String name, String url, int mins, int segs, double price, String album, int genreIndicative) {
        double duration = (mins * 60 ) + segs;
        return listOfAudios.add(new Song(name, url, duration, ( (UserProducer) listOfUsers.get(idArtist)), price, album, Genre.values()[genreIndicative]));
    }

    public boolean regPodcast(int idCCreator, String name, String url, int mins, int segs, String description, int categoryIndicative) {
        double duration = (mins * 60 ) + segs;
        return listOfAudios.add(new Podcast(name, url, duration, ( (UserProducer) listOfUsers.get(idCCreator)), description, Category.values()[categoryIndicative]));
    }

    public boolean createPlaylist(int idConUser, String name) {
        Playlist newPLaylist = new Playlist(name);
        if (listOfUsers.get(idConUser) instanceof UserConsumer) {
            if (listOfUsers.get(idConUser) instanceof Standard) {
                return ( (Standard) listOfUsers.get(idConUser)).addPlaylist(newPLaylist);
            } else {
                return ( (Premium) listOfUsers.get(idConUser)).addPlaylist(newPLaylist);
            }
        }
        
        return false;
    }

    public boolean deleteAudioFromPlaylist(int idUser, int idPlaylist, int idAudio ) {
        if (listOfUsers.get(idUser) instanceof Standard) {
            return ((Standard) listOfUsers.get(idUser)).getListOfPlaylists()[idPlaylist].deleteAudio(listOfAudios.get(idAudio));
        } else {
            return ((Premium) listOfUsers.get(idUser)).getListOfPlaylists().get(idPlaylist).deleteAudio(listOfAudios.get(idAudio));
        }
    }

    public boolean addAudioToPlaylist(int idUser, int idPlaylist, int idAudio ) { //Corregir encapsulamiento xd (Hacer metodo en user de añadir la playlist)
        if (listOfUsers.get(idUser) instanceof Standard) {
            return ((Standard) listOfUsers.get(idUser)).getListOfPlaylists()[idPlaylist].addAudio(listOfAudios.get(idAudio));
        } else {
            return ((Premium) listOfUsers.get(idUser)).getListOfPlaylists().get(idPlaylist).addAudio(listOfAudios.get(idAudio));
        }
        
    }

    public String showPlaylistAudios(int idUser, int idPlaylist) {
        if (listOfUsers.get(idUser) instanceof Standard) {
            return ((Standard) listOfUsers.get(idUser)).getListOfPlaylists()[idPlaylist].showAudios();
        } else {
            return ((Premium) listOfUsers.get(idUser)).getListOfPlaylists().get(idPlaylist).showAudios();
        }
        
    }

    public String showConUserPlaylists(int idUser) {
        if (listOfUsers.get(idUser) instanceof Standard) {
            return ((Standard) listOfUsers.get(idUser)).showPlaylists();
        } else {
            return ((Premium) listOfUsers.get(idUser)).showPlaylists();
        }
    }

    public String showUserAdquiredAudios(int idUser) {
        if (listOfUsers.get(idUser) instanceof Standard) {
            return ((Standard) listOfUsers.get(idUser)).showAdquiredAudios();
        } else {
            return ((Premium) listOfUsers.get(idUser)).showAdquiredAudios();
        }
    }

    public String sharePlaylist(int idUser, int idPlaylist) {
        String playlistCode = "";
        if (listOfUsers.get(idUser) instanceof Standard) {
            playlistCode += ((Standard) listOfUsers.get(idUser)).getListOfPlaylists()[idPlaylist].printNumericMatrix();
            playlistCode += "\n" + "Codigo numerico: " + ((Standard) listOfUsers.get(idUser)).getListOfPlaylists()[idPlaylist].getNumericId();
        } else {
            playlistCode += ((Premium) listOfUsers.get(idUser)).getListOfPlaylists().get(idPlaylist).printNumericMatrix();
            playlistCode += "\n" + "Codigo numerico: " + ((Premium) listOfUsers.get(idUser)).getListOfPlaylists().get(idPlaylist).getNumericId();
            
        }
        return playlistCode;
           
    }

    public String reproduceAudio(int idUser, int idPlaylist, int idAudio) {
        if (listOfUsers.get(idUser) instanceof Standard) {
            return ((Standard) listOfUsers.get(idUser)).reproduceAudioFromPlaylist(idPlaylist , idAudio);
        } else {
            return ((Premium) listOfUsers.get(idUser)).reproduceAudioFromPlaylist(idPlaylist , idAudio);
        }
    }

    public String reproduceAudio(int idUser, int idAudio) {
        if (listOfUsers.get(idUser) instanceof Standard) {
            return ((Standard) listOfUsers.get(idUser)).reproduceAdquiredAudio(idAudio);
        } else {
            return ((Premium) listOfUsers.get(idUser)).reproduceAdquiredAudio(idAudio);
        }
    }

    public boolean buyAudio(int idUser, int idAudio, int year, int month, int day){
        if (listOfAudios.get(idAudio) instanceof Sellable) {
            if (listOfUsers.get(idUser) instanceof Standard) {
                ((Standard) listOfUsers.get(idUser)).addAdquiredAudio(((Song)listOfAudios.get(idAudio)).beSold(year, month, day, ((Sellable)listOfAudios.get(idAudio))));
            } else {
                ((Premium) listOfUsers.get(idUser)).addAdquiredAudio(((Song)listOfAudios.get(idAudio)).beSold(year, month, day, ((Sellable)listOfAudios.get(idAudio))));
            }
            
            return true;

        } 
        
        return false;
    }

    public boolean conUserPlaylistValidation(int idUser) {
        return ((UserConsumer)listOfUsers.get(idUser)).playlistValidation();
    }

    public boolean conUserPlaylistAudioValidation(int idUser, int idPlaylist) {
        return ((UserConsumer)listOfUsers.get(idUser)).playlistAudioValidation(idPlaylist);
    }

    public boolean conUserAdquiredAudioValidation(int idUser) {
        return ((UserConsumer)listOfUsers.get(idUser)).adquiredAudioValidation();
    }


    public boolean songsValidation() {
        if (!listOfAudios.isEmpty()) {
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Song){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean podcastValidation() {
        if (!listOfAudios.isEmpty()) {
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Podcast){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean consumerUserValidation() {
        if (!listOfUsers.isEmpty()) {
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i) instanceof UserConsumer){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean artistValidation() {
        if (!listOfUsers.isEmpty()) {
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i) instanceof Artist){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cCreatorValidation() {
        if (!listOfUsers.isEmpty()) {
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i) instanceof ContentCreator){
                    return true;
                }
            }
        }
        return false;
    }


    public String informTotalReproductions() {
        String total = "";
        int podcastCounter = 0;
        int songCounter = 0;
        if (!listOfAudios.isEmpty()) {
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Song) {
                    songCounter = listOfAudios.get(i).getAmountReproductions();
                } else {
                    podcastCounter = listOfAudios.get(i).getAmountReproductions();
                }
            }   
            total = "Total de reproducciones" + "\nCancion: " + songCounter + "\nPodcast: " + podcastCounter; 
        } else {
            total = "No existen audios";
        }
        return total;
    }


    public String informMostListenedGenre() {
        if (songsValidation()) {
            //ROCK, POP, TRAP, HOUSE
            int[] genreAmount = {0,0,0,0};
            String[] genreName = {"Rock", "Pop" , "Trap" , "House" };
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Song) {
                    
                    switch (((Song)listOfAudios.get(i)).getGenre()) {
                        case ROCK:
                            genreAmount[0] = genreAmount[0] + listOfAudios.get(i).getAmountReproductions();
                            break;

                        case POP:
                            genreAmount[1] = genreAmount[1] + listOfAudios.get(i).getAmountReproductions();
                            break;

                        case TRAP:
                            genreAmount[2] = genreAmount[2] + listOfAudios.get(i).getAmountReproductions();
                            break;

                        case HOUSE:
                            genreAmount[3] = genreAmount[3] + listOfAudios.get(i).getAmountReproductions();
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

            return "El género más repetido de la plataforma es " + genreName[mostInd] + " con " + genreAmount[mostInd] + " reproducciones";
            
        } else {
            return "No existen canciones reproducidas";
        }
    }

    public String userMostListenedGenre(int idUser) {
        return ((UserConsumer)listOfUsers.get(idUser)).mostListenedGenre();
    }

    public String userMostListenedCategory(int idUser) {
        return ((UserConsumer)listOfUsers.get(idUser)).mostListenedCategory();
    }


    public String informMostListenedCategory() {
        if (podcastValidation()) {
            //ROCK, POP, TRAP, HOUSE
            int[] categoryAmount = {0,0,0,0};
            String[] categoryName = {"Politica", "Entretenimiento" , "Videojuegos" , "Moda" };
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Podcast) {
                    
                    switch (((Podcast)listOfAudios.get(i)).getCategory()) {
                        case POLITICS:
                            categoryAmount[0] = categoryAmount[0] + listOfAudios.get(i).getAmountReproductions();
                            break;

                        case ENTERTAINMENT:
                            categoryAmount[1] = categoryAmount[1] + listOfAudios.get(i).getAmountReproductions();
                            break;

                        case VIDEOGAMES:
                            categoryAmount[2] = categoryAmount[2] + listOfAudios.get(i).getAmountReproductions();
                            break;

                        case FASHION:
                            categoryAmount[3] = categoryAmount[3] + listOfAudios.get(i).getAmountReproductions();
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

            return "La categoria más repetida de la plataforma es " + categoryName[mostInd] + " con " + categoryAmount[mostInd] + " reproducciones";

        } else {
            return "No existen podcasts reproducidos";
        }
    }




    public String informTop5Artists() {
        
        if (artistValidation()) {
            ArrayList<Artist> listOfArtistUsers = new ArrayList<Artist>();
        
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i) instanceof Artist){
                    listOfArtistUsers.add(((Artist)listOfUsers.get(i)));
                }
                
            }

            Artist temporalArtist;


            for (int i = 0; i< listOfArtistUsers.size();i++){
                for (int j = i; j< listOfArtistUsers.size();j++){
                    if (listOfArtistUsers.get(i).getNumReproduction() < listOfArtistUsers.get(j).getNumReproduction()){
                        temporalArtist = listOfArtistUsers.get(i);
                        listOfArtistUsers.set(i,listOfArtistUsers.get(j));
                        listOfArtistUsers.set(j,temporalArtist);
                    }
                }
            }

            String reportTop5 = "Artistas: \n";

            for (int i = 0; i< listOfArtistUsers.size();i++){
                if (i <= 5){
                    reportTop5 += (i+1) +".) " + listOfArtistUsers.get(i).getName() +" | Reproducciones: " + listOfArtistUsers.get(i).getNumReproduction() + ". \n";
                } else break;
            }
            return reportTop5;
        } else {
            return "No existen artistas";
        }
    }

    public String informTop5ContentCreators() {
        if (cCreatorValidation()) {
            ArrayList<ContentCreator> listOfCCreators = new ArrayList<ContentCreator>();
        
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i) instanceof ContentCreator){
                    listOfCCreators.add(((ContentCreator)listOfUsers.get(i)));
                }
                
            }

            ContentCreator temporalArtist;


            for (int i = 0; i< listOfCCreators.size();i++){
                for (int j = i; j< listOfCCreators.size();j++){
                    if (listOfCCreators.get(i).getNumReproduction() < listOfCCreators.get(j).getNumReproduction()){
                        temporalArtist = listOfCCreators.get(i);
                        listOfCCreators.set(i,listOfCCreators.get(j));
                        listOfCCreators.set(j,temporalArtist);
                    }
                }
            }

            String reportTop5 = "Artistas: \n";

            for (int i = 0; i< listOfCCreators.size();i++){
                if (i <= 5){
                    reportTop5 += (i+1) +".) " + listOfCCreators.get(i).getName() +" | Reproducciones: " + listOfCCreators.get(i).getNumReproduction() + ". \n";
                } else break;
            }
            return reportTop5;
        } else {
            return "No existen creadores de contenido";
        }
    }


    public String informTop10Songs() {

        if (songsValidation()) {
            ArrayList<Song> listOfSongs = new ArrayList<Song>();

            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Song){
                    listOfSongs.add(((Song)listOfAudios.get(i)));
                }
                
            }

            Song temporalSong;


            for (int i = 0; i< listOfSongs.size();i++){
                for (int j = i; j< listOfSongs.size();j++){
                    if (listOfSongs.get(i).getAmountReproductions() < listOfSongs.get(j).getAmountReproductions()){
                        temporalSong = listOfSongs.get(i);
                        listOfSongs.set(i,listOfSongs.get(j));
                        listOfSongs.set(j,temporalSong);
                    }
                }
            }

            String reportTop10 = "Top 10 Canciones: \n";

            for (int i = 0; i< listOfSongs.size();i++){
                if (i <= 10){
                    reportTop10 += (i+1) +".) " + listOfSongs.get(i).getName() +" | Reproducciones: " + listOfSongs.get(i).getAmountReproductions() + " | Género: " + listOfSongs.get(i).getGenre() + ". \n";
                } else break;
            }
            return reportTop10;

        } else {
            return "No existen canciones";
        }
    
    }

    public String informTop10Podcasts() {

        if (podcastValidation()) {
            ArrayList<Podcast> listOfPodcasts = new ArrayList<Podcast>();

            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Podcast){
                    listOfPodcasts.add(((Podcast)listOfAudios.get(i)));
                }
                
            }

            Podcast temporalPodcast;


            for (int i = 0; i< listOfPodcasts.size();i++){
                for (int j = i; j< listOfPodcasts.size();j++){
                    if (listOfPodcasts.get(i).getAmountReproductions() < listOfPodcasts.get(j).getAmountReproductions()){
                        temporalPodcast = listOfPodcasts.get(i);
                        listOfPodcasts.set(i,listOfPodcasts.get(j));
                        listOfPodcasts.set(j,temporalPodcast);
                    }
                }
            }

            String reportTop10 = "Top 10 Podcasts: \n";

            for (int i = 0; i< listOfPodcasts.size();i++){
                if (i <= 10){
                    reportTop10 += (i+1) +".) " + listOfPodcasts.get(i).getName() +" | Reproducciones: " + listOfPodcasts.get(i).getAmountReproductions() + " | Categoria: " + listOfPodcasts.get(i).getCategory() + ". \n";
                } else break;
            }
            return reportTop10;
        } else {
            return "No existen podcasts";
        }

    }


    public String amountSoldSongsPerGenre() {
        if (songsValidation()) {
            //ROCK, POP, TRAP, HOUSE
            int[] genreAmountSoldSongs = {0,0,0,0};
            double[] genreSoldSongsSales = {0,0,0,0};
            String[] genreName = {"Rock", "Pop" , "Trap" , "House" };
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Song) {
                    if (((Song)listOfAudios.get(i)).getNumSales()>0) {
                        switch (((Song)listOfAudios.get(i)).getGenre()) {
                            case ROCK:
                                genreAmountSoldSongs[0]++;
                                genreSoldSongsSales[0] = genreSoldSongsSales[0] + (((Song)listOfAudios.get(i)).getNumSales() * ((Song)listOfAudios.get(i)).getPrice()) ;
                                break;
        
                            case POP:
                                genreAmountSoldSongs[0]++;
                                genreSoldSongsSales[1] = genreSoldSongsSales[1] + (((Song)listOfAudios.get(i)).getNumSales() * ((Song)listOfAudios.get(i)).getPrice()) ;
                                break;
        
                            case TRAP:
                                genreAmountSoldSongs[0]++;
                                genreSoldSongsSales[2] = genreSoldSongsSales[2] + (((Song)listOfAudios.get(i)).getNumSales() * ((Song)listOfAudios.get(i)).getPrice()) ;
                                break;
        
                            case HOUSE:
                                genreAmountSoldSongs[0]++;
                                genreSoldSongsSales[3] = genreSoldSongsSales[3] + (((Song)listOfAudios.get(i)).getNumSales() * ((Song)listOfAudios.get(i)).getPrice()) ;
                                break;
        
                            default:
                                break;
                        }
                    }
                    
                } 
            }
            String report = "";
            for (int i = 0; i < genreName.length; i++) {
                report += "El género " + genreName[i] + " tiene " + genreAmountSoldSongs + " canciones vendidas y ha producido un valor total de venta de " + genreSoldSongsSales[i];
            }

            return report;

        } else {
            return "No existen canciones";
        }
    }

    public String bestSellingSong() {
        if (songsValidation()) {
            ArrayList<Song> listOfSongs = new ArrayList<Song>();
            int bestSong = 0;
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) instanceof Song){
                    listOfSongs.add(((Song)listOfAudios.get(i)));
                }
                
            }

            for (int i = 0; i < listOfSongs.size(); i++) {
                if (((Song)listOfSongs.get(i)).getNumSales()>((Song)listOfSongs.get(bestSong)).getNumSales()) {
                    bestSong = i;
                }
            }

            String bestSellingSong = "La canción más vendida de la plataforma es: " + "\n" +((Song)listOfSongs.get(bestSong)).getName() + "\nNumero de ventas: " + ((Song)listOfSongs.get(bestSong)).getNumSales() + "\nValor total de venta: " + (((Song)listOfSongs.get(bestSong)).getNumSales() * ((Song)listOfSongs.get(bestSong)).getPrice());
            return bestSellingSong;
        } else {
            return "No existen canciones";
        }
    }


}
