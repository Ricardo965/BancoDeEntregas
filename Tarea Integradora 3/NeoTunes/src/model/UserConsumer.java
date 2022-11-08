package model;

import java.util.Date;

public abstract class UserConsumer extends User implements Reproduction {

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



}
