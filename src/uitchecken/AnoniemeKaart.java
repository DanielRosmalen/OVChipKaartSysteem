package uitchecken;

public class AnoniemeKaart {

    private double saldo;
    private boolean isIngecheckt;
    private int kaartNummer;

    public double getSaldo() {
        return saldo;
    }

    public boolean isIngecheckt() {
        return isIngecheckt;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public void setIncheckStatus(boolean status) {
        isIngecheckt = status;
    }

    public void updateSaldo(double bedrag) {
        saldo = saldo - bedrag;
    }
}