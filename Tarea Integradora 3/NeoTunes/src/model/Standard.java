package model;


import java.util.Date;
import java.util.Random;

/**
 * Standard
 */
public class Standard extends UserConsumer implements Advertisement{

    private AdquiredAudio[] listOfAdquiredAudios = new AdquiredAudio[100];
    private Playlist[] listOfPlaylists = new Playlist[20];
    private int counter = 0;
    

    public AdquiredAudio[] getListOfAdquiredAudios() {
        return listOfAdquiredAudios;
    }
    public void setListOfAdquiredAudios(AdquiredAudio[] listOfAdquiredAudios) {
        this.listOfAdquiredAudios = listOfAdquiredAudios;
    }
    public boolean addAdquiredAudio(AdquiredAudio purchasedAudioToAdd) {
        for (int i = 0; i < listOfAdquiredAudios.length; i++) {
            if (listOfAdquiredAudios[i] == null) {
                listOfAdquiredAudios[i] = purchasedAudioToAdd;
                return true;
            }
        }
        return false;
    }
    public Playlist[] getListOfPlaylists() {
        return listOfPlaylists;
    }
    public void setListOfPlaylists(Playlist[] listOfPlaylists) {
        this.listOfPlaylists = listOfPlaylists;
    }
    public Standard(String nickname, String id, Date date) {
        super(nickname, id, date);
    }
    
    @Override
    public boolean addPlaylist(Playlist playlistToAdd) {
        for (int i = 0; i < listOfPlaylists.length; i++) {
            if (listOfPlaylists[i] == null) {
                listOfPlaylists[i] = playlistToAdd;
                return true;
            }
        }
        return false;
    }
    
    
    @Override
    public Playlist searchPlaylist(String numericId) {
        for (int i = 0; i < listOfPlaylists.length; i++) {
            if (listOfPlaylists[i] != null && listOfPlaylists[i].genNumericId().equals(numericId)) {
                return listOfPlaylists[i];
            }
        }

        return null;
    }

    @Override
    public String showPlaylists() {
        String playlists = "";

		for (int i = 0; i < listOfPlaylists.length; i++) {

			if (listOfPlaylists[i] != null) {

				playlists += "\nPlaylist #" + (i+1) + "\n" + listOfPlaylists[i].toString();
			}

		}
		return playlists;
    }

    @Override
    public String showAdquiredAudios() {
        String adquiredAudios = "";

		for (int i = 0; i < listOfAdquiredAudios.length; i++) {

			if (listOfAdquiredAudios[i] != null) {

				adquiredAudios += "\nAudio comprado #" + (i+1) + "\n" + listOfAdquiredAudios[i].toString();
			}

		}
		return adquiredAudios;

    }

   
    @Override
    public String reproduceAudioFromPlaylist(int idPlaylist, int idAudio) {
        String reproduction = "";
        //a??adir reproducci??n a la copia del audio en listened audios
        if (listOfPlaylists[idPlaylist].getListOfAudios().get(idAudio) instanceof Song) {
            if (counter == 2) {
                reproduction += "\n" + playAd();
                counter = 0;
            }
            counter++;
        } else {
            reproduction += "\n" + playAd();
        }

        registerListenedAudio(listOfPlaylists[idPlaylist].getListOfAudios().get(idAudio));

        reproduction += "\n" + listOfPlaylists[idPlaylist].getListOfAudios().get(idAudio).bePlayed();

        return reproduction;
    }
    
    @Override
    public String reproduceAdquiredAudio(int idAudio) {
        String reproduction = "";
        if (counter == 2) {
            reproduction += "\n" + playAd();
            counter = 0;
        }
        counter++;
        registerListenedAudio((Audio)listOfAdquiredAudios[idAudio].getPurchasedAudio());
        reproduction += "\n" + ((Audio)listOfAdquiredAudios[idAudio].getPurchasedAudio()).bePlayed();
        return reproduction;
    }
    
    
    @Override
    public String playAd() {
        Random ran = new Random();
        return "Reproduciendo anuncio " + ads[ran.nextInt(3)];
    }
    @Override
    public boolean playlistValidation() {
        for (int i = 0; i < listOfPlaylists.length; i++) {
            if (listOfPlaylists[i] != null) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean playlistAudioValidation(int idPlaylist) {
        return listOfPlaylists[idPlaylist].audioValidation();
    }
    @Override
    public boolean adquiredAudioValidation() {
        for (int i = 0; i < listOfAdquiredAudios.length; i++) {
            if (listOfAdquiredAudios[i] != null) {
                return true;
            }
        }
        return false;
    }
    

    
    
    
    

    


}