package ovsysteem;

import java.awt.*;

public class KaartLezer {
    public int lezerID;
    public String locatie;
    public CentraalSysteem systeem;

    // Inchecken: scanKaart met alleen een kaart
    public void scanKaart(AnoniemeKaart deKaart) {
        if (validSaldo(deKaart) == true) {
            deKaart.setIncheckStatus(true);
            deKaart.setIncheckStation(this.locatie);
            toonMelding("Ingecheckt!");

            IncheckTransactie bon = new IncheckTransactie();
            bon.locatie = this.locatie;
            systeem.registreerIncheck(bon);

            geefPiepje(2);

        } else {
            deKaart.setIncheckStatus(false);
            toonMelding("Onvoldoende saldo!");
            geefPiepje(1);
        }
    }

    // Uitchecken: scanKaart met kaart en uitchecktransactie
    public void scanKaart(AnoniemeKaart deKaart, UitcheckTransactie transactie) {
        if (validSaldo(deKaart) && deKaart.isIngecheckt()) {
            transactie.beginStation = deKaart.getIncheckStation();
            transactie.eindStation = this.locatie;
            double prijs = transactie.berekenRitPrijs(transactie.beginStation);
            deKaart.updateSaldo(prijs);
            geefPiepje(2);
            deKaart.setIncheckStatus(false);
            deKaart.setIncheckStation(null);
            toonMelding("Uitgecheckt!");
            systeem.registreerUitcheck((int) (Math.random() * 10000), locatie);
        }
        else if (!deKaart.isIngecheckt() && validSaldo(deKaart)) {
            deKaart.setIncheckStatus(true);
            deKaart.setIncheckStation(this.locatie);
            toonMelding("Ingecheckt!");
            geefPiepje(2);
        }
        else {
            toonMelding("Niet Genoeg Saldo!");
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

    public void schrijfAf(UitcheckTransactie transactie, AnoniemeKaart deKaart, String beginStation) {
        deKaart.updateSaldo(transactie.berekenRitPrijs(beginStation));
    }
}
