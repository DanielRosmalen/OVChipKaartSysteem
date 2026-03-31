package uitchecken;

public class AnoniemeKaart {

    public double saldo;
    public boolean isIngecheckt;
    public int kaartNummer;

    public void setIncheckStatus(boolean status) {
        isIngecheckt = status;
    }

    public void updateSaldo(double bedrag) {
        saldo = saldo - bedrag;
    }
}
