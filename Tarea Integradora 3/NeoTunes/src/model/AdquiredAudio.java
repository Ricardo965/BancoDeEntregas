package model;

import java.util.Date;

public class AdquiredAudio {
    private Date purchaseDate;
    private Sellable purchasedAudio;


    public Sellable getPurchasedAudio() {
        return purchasedAudio;
    }
    public void setPurchasedAudio(Sellable purchasedAudio) {
        this.purchasedAudio = purchasedAudio;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /*public Audio getPurchasedAudio() {
        return purchasedAudio;
    }
    public void setPurchasedAudio(Audio purchasedAudio) {
        this.purchasedAudio = purchasedAudio;
    }
    
    public AdquiredAudio(Date purchaseDate, Audio purchasedAudio) {
        this.purchaseDate = purchaseDate;
        this.purchasedAudio = purchasedAudio;
    }*/

    public AdquiredAudio(Date purchaseDate, Sellable purchasedAudio) {
        this.purchaseDate = purchaseDate;
        this.purchasedAudio = purchasedAudio;
    }


    @Override
    public String toString() {
        return "Audio comprado: " + "\nFecha de compra: " + purchaseDate + "\nDatos del audio " + purchasedAudio;
    }
    
    
}
