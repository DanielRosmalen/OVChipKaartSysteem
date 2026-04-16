package Opwaarderen;

public class CentraalSysteem {
    public int openstaandeBestellingen;
    public OpwaardeerTransactie bestelling;

    public void verwerkWebsiteBestelling(OpwaardeerTransactie transactie) {
        bestelling = transactie;
        openstaandeBestellingen++;
    }
}
