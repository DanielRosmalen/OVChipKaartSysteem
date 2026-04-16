package ovsysteem;

public class CentraalSysteem {

    public double instapTarief;
    public boolean inSysteemOnline;
    public int openstaandeBestellingen;
    public OpwaardeerTransactie bestelling;

    public void registreerIncheck(IncheckTransactie kaartTransactie) {
        System.out.println(kaartTransactie.getTransactieData());
    }

    public void registreerUitcheck(int transactieID, String beginStation) {
        System.out.println("Transactie ID: " + transactieID + " - Locatie: " + beginStation);
    }

    public void verwerkWebsiteBestelling(OpwaardeerTransactie transactie) {
        bestelling = transactie;
        openstaandeBestellingen++;
    }
}