package model;


import java.util.Date;

/**
 * Standard
 */
public class Standard extends UserConsumer implements Advertisement{

    private Sellable[] listOfAdquiredAudios = new Sellable[100];
    private Playlist[] listOfPlaylists = new Playlist[20];

    

    public Sellable[] getListOfAdquiredAudios() {
        return listOfAdquiredAudios;
    }
    public void setListOfAdquiredAudios(Sellable[] listOfAdquiredAudios) {
        this.listOfAdquiredAudios = listOfAdquiredAudios;
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
            if (listOfPlaylists[i] != null) {
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

				playlists += "\nNivel #" + (i+1) + "\n" + listOfPlaylists[i].toString();
			}

		}
		return playlists;
    }

    


}