package uitchecken;

public class Drive {
    public static void main (String[] args) {

        KaartLezer ovPaalAms = new KaartLezer();
        KaartLezer ovPaalUtr = new KaartLezer();
        KaartLezer ovPaalRot = new KaartLezer();
        KaartLezer ovPaalArn = new KaartLezer();

        CentraalSysteem centraalSyst = new CentraalSysteem();
        AnoniemeKaart anoniemKaart = new AnoniemeKaart();
        UitcheckTransactie transactie = new UitcheckTransactie();

        centraalSyst.inSysteemOnline = true;
        centraalSyst.instapTarief = 4.00;

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

        anoniemKaart.isIngecheckt  = true;
        anoniemKaart.saldo = 30;
        anoniemKaart.kaartNummer = 69420;

        transactie.beginStation = "Amsterdam";
        transactie.eindStation = "Utrecht";
        ovPaalUtr.scanKaart(anoniemKaart, transactie);
        System.out.println("Saldo na uitcheck: " + anoniemKaart.saldo);
    }
}
