package ovsysteem;

public class AnoniemeKaart {

    private int kaartNummer;
    private double saldo;
    private boolean isIngecheckt;
    private String incheckStation;

    public int getKaartNummer() { return kaartNummer; }
    public double getSaldo() { return saldo; }
    public boolean isIngecheckt() { return isIngecheckt; }
    public String getIncheckStation() { return incheckStation; }

    public void setKaartNummer(int kaartNummer) { this.kaartNummer = kaartNummer; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public void setIncheckStation(String incheckStation) { this.incheckStation = incheckStation; }

    public void setIncheckStatus(boolean status) {
        isIngecheckt = status;
    }

    public void updateSaldo(double bedrag) {
        saldo = saldo - bedrag;
    }

    public void verhoogSaldo(double bedrag) {
        if (bedrag <= 0) {
            System.out.println("Ongeldig bedrag. U kunt alleen een positief bedrag storten.");
            return;
        }
        saldo = saldo + bedrag;
    }
}
