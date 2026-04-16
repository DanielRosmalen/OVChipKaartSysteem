package ovsysteem;

import java.util.ArrayList;
import java.util.Scanner;

public class Drive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AnoniemeKaart anoniemKaart = new AnoniemeKaart();
        CentraalSysteem centraalSyst = new CentraalSysteem();
        BetaalAutomaat betaalAutomaat = new BetaalAutomaat();

        KaartLezer ovPaalAms = new KaartLezer();
        KaartLezer ovPaalUtr = new KaartLezer();
        KaartLezer ovPaalArn = new KaartLezer();
        KaartLezer ovPaalRot = new KaartLezer();

        centraalSyst.inSysteemOnline = true;
        centraalSyst.instapTarief = 5.0;

        anoniemKaart.setSaldo(50.0);
        anoniemKaart.setKaartNummer(69420);

        ovPaalAms.locatie = "Amsterdam";
        ovPaalAms.lezerID = 1;
        ovPaalAms.systeem = centraalSyst;

        ovPaalUtr.locatie = "Utrecht";
        ovPaalUtr.lezerID = 2;
        ovPaalUtr.systeem = centraalSyst;

        ovPaalArn.locatie = "Arnhem";
        ovPaalArn.lezerID = 3;
        ovPaalArn.systeem = centraalSyst;

        ovPaalRot.locatie = "Rotterdam";
        ovPaalRot.lezerID = 4;
        ovPaalRot.systeem = centraalSyst;

        betaalAutomaat.locatie = "Amsterdam";
        betaalAutomaat.automaatID = 1;
        betaalAutomaat.systeem = centraalSyst;

        ArrayList<KaartLezer> stations = new ArrayList<>();
        stations.add(ovPaalAms); // index 0
        stations.add(ovPaalUtr); // index 1
        stations.add(ovPaalArn); // index 2
        stations.add(ovPaalRot); // index 3

        // Aangrenzende stations per station (op basis van index)
        int[][] aangrenzend = {
            {1, 3},
            {0, 2, 3},
            {1, 3},
            {2, 0, 1}
        };

        int stationIndex = 0;
        int volgendStation = 0;
        boolean inDeTrein = false;
        boolean doorgaan = true;

        System.out.println("Welkom je bevindt je op station " + stations.get(stationIndex).locatie + ".");

        while (doorgaan) {
            if (!inDeTrein) {
                // Stationsmenu
                KaartLezer huidigePaal = stations.get(stationIndex);
                System.out.println("Saldo: " + anoniemKaart.getSaldo());
                System.out.println("(1) Inchecken");
                System.out.println("(2) Uitchecken");
                System.out.println("(3) Opwaarderen via pin");
                System.out.println("(4) Opwaarderen via iDeal");
                System.out.println("(5) Online bestelling ophalen");
                System.out.println("(6) Stoppen");
                System.out.print("Keuze: ");
                String keuze = scanner.nextLine();

                if (keuze.equals("1")) {
                    if (anoniemKaart.isIngecheckt()) {
                        System.out.println("Je bent al ingecheckt.");
                    } else {
                        huidigePaal.scanKaart(anoniemKaart);
                        if (anoniemKaart.isIngecheckt()) {
                            int[] buren = aangrenzend[stationIndex];
                            System.out.println("Naar welk station wil je reizen?");
                            for (int i = 0; i < buren.length; i++) {
                                System.out.println("(" + (i + 1) + ") " + stations.get(buren[i]).locatie);
                            }
                            System.out.print("Keuze: ");
                            int pick = Integer.parseInt(scanner.nextLine()) - 1;
                            volgendStation = buren[pick];
                            inDeTrein = true;
                            System.out.println("Je reist naar " + stations.get(volgendStation).locatie + ".");
                        }
                    }
                } else if (keuze.equals("2")) {
                    if (!anoniemKaart.isIngecheckt()) {
                        System.out.println("Je bent niet ingecheckt.");
                    } else {
                        UitcheckTransactie transactie = new UitcheckTransactie();
                        huidigePaal.scanKaart(anoniemKaart, transactie);
                        System.out.println("Saldo na uitchecken: " + anoniemKaart.getSaldo());
                    }
                } else if (keuze.equals("3")) {
                    OpwaardeerTransactie opTransactie = new OpwaardeerTransactie();
                    System.out.print("Hoeveel wilt u storten? ");
                    double bedrag = Double.parseDouble(scanner.nextLine());
                    opTransactie.bedrag = bedrag;
                    opTransactie.betaalMethode = "Pin";
                    anoniemKaart.verhoogSaldo(opTransactie.bedrag);
                    System.out.println("Saldo na pin opwaarderen: " + anoniemKaart.getSaldo());
                } else if (keuze.equals("4")) {
                    OpwaardeerTransactie opTransactie = new OpwaardeerTransactie();
                    System.out.print("Hoeveel wilt u bestellen? ");
                    double bedrag = Double.parseDouble(scanner.nextLine());
                    opTransactie.bedrag = bedrag;
                    opTransactie.betaalMethode = "Ideal";
                    centraalSyst.verwerkWebsiteBestelling(opTransactie);
                    System.out.println("Bestelling van " + bedrag + " geplaatst! Haal deze op bij een automaat.");
                } else if (keuze.equals("5")) {
                    if (betaalAutomaat.haalOnlineBestellingOp() != null) {
                        OpwaardeerTransactie opgehaald = betaalAutomaat.haalOnlineBestellingOp();
                        anoniemKaart.verhoogSaldo(opgehaald.bedrag);
                        centraalSyst.bestelling = null;
                        centraalSyst.openstaandeBestellingen--;
                        System.out.println("Bestelling opgehaald! Saldo is nu: " + anoniemKaart.getSaldo());
                    } else {
                        System.out.println("Geen openstaande online bestellingen.");
                    }
                } else if (keuze.equals("6")) {
                    if (anoniemKaart.isIngecheckt()) {
                        anoniemKaart.updateSaldo(10.0);
                        anoniemKaart.setIncheckStatus(false);
                        anoniemKaart.setIncheckStation(null);
                        System.out.println("Je was nog ingecheckt! 10 euro boete afgeschreven.");
                        System.out.println("Saldo: " + anoniemKaart.getSaldo());
                    }
                    doorgaan = false;
                    System.out.println("Tot ziens!");
                } else {
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            } else {
                // Treinmenu
                System.out.println("--- In de trein ---");
                System.out.println("Saldo: " + anoniemKaart.getSaldo());
                System.out.println("(1) Uitstappen op " + stations.get(volgendStation).locatie);
                System.out.println("(2) Doorreizen");
                System.out.print("Keuze: ");
                String keuze = scanner.nextLine();

                if (keuze.equals("1")) {
                    stationIndex = volgendStation;
                    inDeTrein = false;
                    System.out.println("Je stapt uit op station " + stations.get(stationIndex).locatie + ".");
                } else if (keuze.equals("2")) {
                    stationIndex = volgendStation;
                    int[] buren = aangrenzend[stationIndex];
                    System.out.println("Naar welk station wil je doorreizen?");
                    for (int i = 0; i < buren.length; i++) {
                        System.out.println("(" + (i + 1) + ") " + stations.get(buren[i]).locatie);
                    }
                    System.out.print("Keuze: ");
                    int pick = Integer.parseInt(scanner.nextLine()) - 1;
                    volgendStation = buren[pick];
                    System.out.println("Je reist door naar " + stations.get(volgendStation).locatie + ".");
                } else {
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            }
        }
    }
}
