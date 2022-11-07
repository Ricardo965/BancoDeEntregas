package model;


import java.util.ArrayList;
import java.util.Date;

public class Premium extends UserConsumer{
    private ArrayList<Sellable> listOfAdquiredAudios = new ArrayList<Sellable>();
    private ArrayList<Playlist> listOfPlaylists = new ArrayList<Playlist>();
    
    public ArrayList<Sellable> getListOfAdquiredAudios() {
        return listOfAdquiredAudios;
    }
    public void setListOfAdquiredAudios(ArrayList<Sellable> listOfAdquiredAudios) {
        this.listOfAdquiredAudios = listOfAdquiredAudios;
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
   

}
