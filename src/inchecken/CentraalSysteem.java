package inchecken;

public class CentraalSysteem {

    public double instapTarief;
    public boolean inSysteemOnline;


    public void registreerIncheck(IncheckTransactie kaartTransactie) {
        System.out.println(kaartTransactie.getTransactieData());
    }
}
