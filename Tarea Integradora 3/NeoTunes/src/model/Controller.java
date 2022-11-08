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

    public boolean addAudioToPlaylist(int idUser, int idPlaylist, int idAudio ) {
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

}
