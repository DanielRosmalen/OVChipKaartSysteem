package inchecken;

public class Drive {
    public static void main(String[] args) {
        AnoniemeKaart anoniemeKaart = new AnoniemeKaart();
        CentraalSysteem centraalSyst = new CentraalSysteem();
        KaartLezer ovPaal = new KaartLezer();

        ovPaal.systeem = centraalSyst;

        anoniemeKaart.saldo = 20.0;
        anoniemeKaart.kaartNummer = 69420;
        centraalSyst.instapTarief = 5.0;
        ovPaal.locatie = "Nijmegen";

        ovPaal.scanKaart(anoniemeKaart);
    }
}
