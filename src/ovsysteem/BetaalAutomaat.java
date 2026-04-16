package ovsysteem;

public class BetaalAutomaat {
    public int automaatID;
    public String locatie;
    public CentraalSysteem systeem;

    public boolean startPinBetaling() {
        System.out.println("Pin betaling gestart");
        return true;
    }

    public OpwaardeerTransactie haalOnlineBestellingOp() {
        return systeem.bestelling;
    }
}