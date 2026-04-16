package uitchecken;

import uitchecken.AnoniemeKaart;
import uitchecken.CentraalSysteem;

import java.awt.*;

public class KaartLezer {

    private int lezerID;
    private String locatie;
    private CentraalSysteem systeem;

    public int getLezerID() {
        return lezerID;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLezerID(int lezerID) {
        this.lezerID = lezerID;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public void setSysteem(CentraalSysteem systeem) {
        this.systeem = systeem;
    }

    public void scanKaart(AnoniemeKaart deKaart, UitcheckTransactie transactie) {
        if (validSaldo(deKaart) && deKaart.isIngecheckt()) {
            double prijs = transactie.berekenRitPrijs(transactie.beginStation);
            deKaart.updateSaldo(prijs);
            geefPiepje(2);
            deKaart.setIncheckStatus(false);
            toonMelding("Uitgecheckt!");
            systeem.registreerUitcheck(deKaart.getKaartNummer(), locatie);
        } else if (!deKaart.isIngecheckt() && validSaldo(deKaart)) {
            deKaart.setIncheckStatus(true);
            toonMelding("Ingecheckt!");
            geefPiepje(2);
        } else {
            toonMelding("Niet Genoeg Saldo!");
        }
    }

    public void toonMelding(String melding) {
        System.out.println(melding);
    }

    public void geefPiepje(int piep) {
        if (piep == 2) {
            for (int i = 0; i < 2; i++) {
                Toolkit.getDefaultToolkit().beep();
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public double checkSaldo(AnoniemeKaart deKaart) {
        return deKaart.getSaldo();
    }

    public boolean validSaldo(AnoniemeKaart deKaart) {
        if (checkSaldo(deKaart) >= systeem.instapTarief) {
            return true;
        } else {
            return false;
        }
    }

    public void schrijfAf(UitcheckTransactie transactie, AnoniemeKaart deKaart, String beginStation) {
        deKaart.updateSaldo(transactie.berekenRitPrijs(beginStation));
    }
}