package inchecken;

public class Drive {
    public static void main(String[] args) {
        AnoniemeKaart anoniemKaart = new AnoniemeKaart();
        CentraalSysteem centraalSyst = new CentraalSysteem();
        KaartLezer ovPaal = new KaartLezer();

        ovPaal.systeem = centraalSyst;

        anoniemKaart.saldo = 20.0;
        anoniemKaart.kaartNummer = 69420;
        centraalSyst.instapTarief = 5.0;
        ovPaal.locatie = "Nijmegen";

        ovPaal.scanKaart(anoniemKaart);
    }
}
