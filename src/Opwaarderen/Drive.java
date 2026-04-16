package Opwaarderen;

import java.util.Scanner;

public class Drive {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

        BetaalAutomaat betaalAutomaat = new BetaalAutomaat();
        CentraalSysteem centraalSysteem = new CentraalSysteem();
        AnoniemeKaart anoniemKaart = new AnoniemeKaart();

        betaalAutomaat.locatie = "Nijmegen";
        betaalAutomaat.automaatID = 1;
        betaalAutomaat.systeem = centraalSysteem;

        anoniemKaart.saldo = 20;

        System.out.println("Je saldo is: " + anoniemKaart.saldo);
        System.out.println("Wil je online bestellen of naar de betaal Automaat?");
        System.out.println("(1) bestellen");
        System.out.println("(2) Betaal Automaat");
        System.out.println("Kies uit 1 of 2");
        String keuze = scanner.nextLine();

        if (keuze.equals("1")) {
            OpwaardeerTransactie transactie = new OpwaardeerTransactie();
            System.out.println("Hoeveel wilt u bestellen?");
            double gekozenBedrag = scanner.nextDouble();
            transactie.bedrag = gekozenBedrag;
            transactie.betaalMethode = "Ideal";
            centraalSysteem.verwerkWebsiteBestelling(transactie);

        } if (keuze.equals("2")) {
            OpwaardeerTransactie transactie = new OpwaardeerTransactie();
            System.out.println("Hoeveel wilt u storten?");
            double gekozenBedrag = scanner.nextDouble();
            transactie.bedrag = gekozenBedrag;
            transactie.betaalMethode = "Pin";
            anoniemKaart.verhoogSaldo(transactie.bedrag);
            System.out.println("Saldo na pin opwaarderen: " + anoniemKaart.saldo);
        }
        if (betaalAutomaat.haalOnlineBestellingOp() != null && keuze.equals("1")){
            System.out.println("Openstaande online bestelling ophalen.");
            OpwaardeerTransactie opgehaald = betaalAutomaat.haalOnlineBestellingOp();
            anoniemKaart.verhoogSaldo(opgehaald.bedrag);
        }

        System.out.println("U nieuwe saldo is: " + anoniemKaart.saldo);
    }
}
