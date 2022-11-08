package model;

import java.util.ArrayList;
import java.util.Random;


public class Playlist {
    private ArrayList<Audio> listOfAudios = new ArrayList<Audio>();
    private String name;
    private String numericId;
    private String[][] codeMatrix;

    public String[][] getCodeMatrix() {
        return codeMatrix;
    }
    public void setCodeMatrix(String[][] codeMatrix) {
        this.codeMatrix = codeMatrix;
    }
    public ArrayList<Audio> getListOfAudios() {
        return listOfAudios;
    }
    public void setListOfAudios(ArrayList<Audio> listOfAudios) {
        this.listOfAudios = listOfAudios;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumericId() {
        return numericId;
    }
    public void setNumericId(String numericId) {
        this.numericId = numericId;
    }
    public Playlist(String name) {
        this.name = name;
        this.numericId = genNumericId();
    }

    public String genNumericId() {
        String[][] numericMat = genMatrix();
        setCodeMatrix(numericMat);
        String numericId = "";

        if (songVerifier() && podcastVerifier()) {
            numericId = codeMatrixR(numericMat);
        } else if (songVerifier()) {
            numericId = codeMatrixN(numericMat);
        } else if (podcastVerifier()) {
            numericId = codeMatrixT(numericMat);
        } else {
            Random random = new Random();
            for (int i = 0; i < 16 ; i++) {
                numericId += String.valueOf(random.nextInt(10));
            }
            setCodeMatrix(null);
        }
        return numericId;
    }


    public String[][] genMatrix() {
        String[][] mat = new String[6][6];
        Random random = new Random();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = String.valueOf(random.nextInt(10));
            }
        }
        return mat;
    }

    public String printNumericMatrix() {
		String print = "";
        if (codeMatrix != null) {
            for (int i = 0; i < codeMatrix.length; i++) { 
                for (int j = 0; j < codeMatrix[0].length; j++) { 
                    print += codeMatrix[i][j] + " ";
                }
                print += "\n";
            }
        }
		return print;
	}

    public boolean songVerifier() {
        if (!listOfAudios.isEmpty()) {
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) != null) {
                    if ( listOfAudios.get(i) instanceof Song) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean podcastVerifier() {
        if (!listOfAudios.isEmpty()) {
            for (int i = 0; i < listOfAudios.size(); i++) {
                if (listOfAudios.get(i) != null) {
                    if ( listOfAudios.get(i) instanceof Podcast) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String codeMatrixN(String[][] codeMatrix){
        String subcode1 = "";
        String subcode2 = "";
        String subcode3 = "";

        for (int i = 0; i < codeMatrix.length; i++) {
            subcode1 += codeMatrix[codeMatrix.length-(i+1)][0];
            if (i>0 && i<codeMatrix.length-1) {
                subcode2 += codeMatrix[i][i];  
            }
            subcode3 += codeMatrix[codeMatrix.length-(i+1)][5];
        }

        String code = subcode1 + subcode2 + subcode3;

        return code;
    }

    public String codeMatrixT(String[][] codeMatrix){
        String subcode1 = "";
        String subcode2 = "";
        String subcode3 = "";
        String subcode4 = "";
        // | 0,0 | 0,1 | 0,2 | 1,2 | 2,2 | 3,2 | 4,2 | 5,2 | 5,3 | 4,3 | 3,3 | 2,3 | 1,3 | 0,3 | 0,4 | 0,5 |
        for (int i = 0; i < codeMatrix.length; i++) {
            if (i<2) {
                subcode1 += codeMatrix[0][i]; 
            }
            subcode2 += codeMatrix[i][2];
            subcode3 += codeMatrix[codeMatrix.length-(i+1)][3];
            if (i>3) {
                subcode4 += codeMatrix[0][i];
            }
        }
        
        String code = subcode1 + subcode2 + subcode3 + subcode4;
        return code;
    }

    public String codeMatrixR(String[][] codeMatrix){
        // | 5,4 | 5,2 | 5,0 | 4,5 | 4,3 | 4,1 | 3,4 | 3,2 | 3,0 | 2,5 | 2,3 | 2,1 | 1,4 | 1,2 | 0,5 | 0,3 |
        String subcode1 = "";
        String subcode2 = "";
        String subcode3 = "";
        String subcode4 = "";
        String subcode5 = "";
        String subcode6 = "";
       
        for (int i = codeMatrix.length-1; i > -1; i--) {
            if (i%2 == 0) {
                subcode1 += codeMatrix[5][i];
                subcode3 += codeMatrix[3][i];
            }
            
            if (i%2 == 1) {
                subcode2 += codeMatrix[4][i];
                subcode4 += codeMatrix[2][i];
            }

            if (i>1) {
                if (i%2 == 0) {
                    subcode5 += codeMatrix[1][i];
                } else {
                    subcode6 += codeMatrix[0][i];
                }
            }
        }

        String code = subcode1 + subcode2 + subcode3 + subcode4 + subcode5 + subcode6;
        return code;
    }

    public boolean addAudio(Audio audioToAdd) {
        listOfAudios.add(audioToAdd);
        setNumericId(genNumericId());
        return true;
    }

    public boolean deleteAudio(Audio audioToRemove) {
        return listOfAudios.remove(audioToRemove);
    }

    public String showAudios() {
        String audios = "";
        String type = "";
        if (!listOfAudios.isEmpty()) {
            for (int i = 0; i < listOfAudios.size(); i++) {


                if (listOfAudios.get(i) instanceof Song) {
                    type = "Cancion";
                } else {
                    type = "Podcast";
                }
                
                audios += "\nAudio #" + (i+1) + "\nTipo: "+ type +"\n" + (listOfAudios.get(i)).toString();
            }
        }

        return audios;
    }

    @Override
    public String toString() {
        return "\nNombre de la playlist: " + name + "\nIdentificador: " + numericId;
    }

    
    
    
}
