package model;


import java.util.ArrayList;
import java.util.Date;


public class Premium extends UserConsumer{
    private ArrayList<AdquiredAudio> listOfAdquiredAudios = new ArrayList<AdquiredAudio>();
    private ArrayList<Playlist> listOfPlaylists = new ArrayList<Playlist>();

    
    public ArrayList<AdquiredAudio> getListOfAdquiredAudios() {
        return listOfAdquiredAudios;
    }
    public void setListOfAdquiredAudios(ArrayList<AdquiredAudio> listOfAdquiredAudios) {
        this.listOfAdquiredAudios = listOfAdquiredAudios;
    }
    public boolean addAdquiredAudio(AdquiredAudio purchasedAudioToAdd){
        return listOfAdquiredAudios.add(purchasedAudioToAdd);
    }

    public ArrayList<Playlist> getListOfPlaylists() {
        return listOfPlaylists;
    }
    public void setListOfPlaylists(ArrayList<Playlist> listOfPlaylists) {
        this.listOfPlaylists = listOfPlaylists;
    }
    public Premium(String nickname, String id, Date date) {
        super(nickname, id, date);
    }

    public boolean addPlaylist(Playlist playlistToAdd) {
        return listOfPlaylists.add(playlistToAdd);
    }

    @Override
    public String showPlaylists() {
        String playlists = "";
        if (!listOfPlaylists.isEmpty()) {
            for (int i = 0; i < listOfPlaylists.size(); i++) {

                playlists += "\nPlaylist #" + (i+1) + "\n" + (listOfPlaylists.get(i)).toString();

            }
        }
        return playlists;
        
    }

    @Override
    public String showAdquiredAudios() {
        String playlists = "";
        if (!listOfAdquiredAudios.isEmpty()) {
            for (int i = 0; i < listOfAdquiredAudios.size(); i++) {

                playlists += "\nAudio comprado #" + (i+1) + "\n" + (listOfAdquiredAudios.get(i)).toString();

            }
        }
        return playlists;
    }


    @Override
    public String reproduceAudioFromPlaylist(int idPlaylist, int idAudio) {
        return  "\n" + listOfPlaylists.get(idPlaylist).getListOfAudios().get(idAudio).bePlayed();
    
    }
    @Override

    public String reproduceAdquiredAudio(int idAudio) {
        return ((Audio)listOfAdquiredAudios.get(idAudio).getPurchasedAudio()).bePlayed();
    }
   

}
