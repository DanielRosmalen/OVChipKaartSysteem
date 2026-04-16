package uitchecken;

public class Drive {
    public static void main(String[] args) {

        KaartLezer ovPaalAms = new KaartLezer();
        KaartLezer ovPaalUtr = new KaartLezer();
        KaartLezer ovPaalRot = new KaartLezer();
        KaartLezer ovPaalArn = new KaartLezer();

        CentraalSysteem centraalSyst = new CentraalSysteem();
        AnoniemeKaart anoniemKaart = new AnoniemeKaart();
        UitcheckTransactie transactie = new UitcheckTransactie();

        centraalSyst.inSysteemOnline = true;
        centraalSyst.instapTarief = 4.00;

        ovPaalAms.setLezerID(1);
        ovPaalAms.setLocatie("Amsterdam");
        ovPaalAms.setSysteem(centraalSyst);

        ovPaalUtr.setLezerID(2);
        ovPaalUtr.setLocatie("Utrecht");
        ovPaalUtr.setSysteem(centraalSyst);

        ovPaalArn.setLezerID(3);
        ovPaalArn.setLocatie("Arnhem");
        ovPaalArn.setSysteem(centraalSyst);

        ovPaalRot.setLezerID(4);
        ovPaalRot.setLocatie("Rotterdam");
        ovPaalRot.setSysteem(centraalSyst);

        anoniemKaart.setIncheckStatus(true);
        anoniemKaart.setSaldo(30);
        anoniemKaart.setKaartNummer(69420);

        transactie.beginStation = "Amsterdam";
        transactie.eindStation = "Utrecht";
        ovPaalUtr.scanKaart(anoniemKaart, transactie);
        System.out.println("Saldo na uitcheck: " + anoniemKaart.getSaldo());
    }
}