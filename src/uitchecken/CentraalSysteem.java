package uitchecken;

public class CentraalSysteem {

    public double instapTarief;
    public boolean inSysteemOnline;

    public void registreerUitcheck(int transactieID, String beginStation) {
        System.out.println(transactieID + " " + beginStation);
    }
}