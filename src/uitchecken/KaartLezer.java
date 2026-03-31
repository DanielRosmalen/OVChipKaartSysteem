package uitchecken;

import uitchecken.AnoniemeKaart;
import uitchecken.CentraalSysteem;

import java.awt.*;

public class KaartLezer {
    public int lezerID;
    public String locatie;
    public CentraalSysteem systeem;

    public void scanKaart(AnoniemeKaart deKaart, UitcheckTransactie transactie) {
        if (validSaldo(deKaart) && deKaart.isIngecheckt) {
            double prijs = transactie.berekenRitPrijs(transactie.beginStation);
            deKaart.updateSaldo(prijs);
            geefPiepje(2);
            deKaart.isIngecheckt = false;
            toonMelding("Uitgecheckt!");
            systeem.registreerUitcheck(deKaart.kaartNummer, locatie);
        }
        else if (!deKaart.isIngecheckt && validSaldo(deKaart)) {
            deKaart.isIngecheckt = true;
            toonMelding("Ingecheckt!");
            geefPiepje(2);
        }
        else {
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
            return deKaart.saldo;
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
