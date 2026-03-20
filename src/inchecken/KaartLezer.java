package inchecken;

import java.awt.*;

public class KaartLezer {
    public int lezerID;
    public String locatie;
    public CentraalSysteem systeem;

    public void scanKaart(AnoniemeKaart deKaart) {
        if (validSaldo(deKaart) == true) {
            deKaart.setIncheckStatus(true);
            toonMelding("Ingecheckt!");

            IncheckTransactie bon = new IncheckTransactie();
            bon.locatie = this.locatie;
            bon.transactieID = (int) (Math.random() * 10000);
            systeem.registreerIncheck(bon);

            geefPiepje(2);

        } else {
            deKaart.setIncheckStatus(false);
            toonMelding("Onvoldoende saldo!");
            geefPiepje(1);
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
}
