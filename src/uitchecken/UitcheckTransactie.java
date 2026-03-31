package uitchecken;

public class UitcheckTransactie {
    public String beginStation;
    public String eindStation;
    public double ritPrijs;
    public String vertrekTijd;

    public double berekenRitPrijs(String beginStation) {
        String route = beginStation + "-" + eindStation;
            switch (route) {
                case "Amsterdam-Rotterdam":
                    ritPrijs = 17.60;
                    break;
                case "Amsterdam-Utrecht":
                    ritPrijs = 8.90;
                    break;
                case "Amsterdam-Arnhem":
                    ritPrijs = 17.20;
                    break;
                case "Rotterdam-Utrecht":
                    ritPrijs = 12.40;
                    break;
                case "Rotterdam-Arnhem":
                    ritPrijs = 21.30;
                    break;
                case "Utrecht-Arnhem":
                    ritPrijs = 8.90;
                    break;
                case "Rotterdam-Amsterdam":
                    ritPrijs = 17.60;
                    break;
                case "Utrecht-Amsterdam":
                    ritPrijs = 8.90;
                    break;
                case "Arnhem-Amsterdam":
                    ritPrijs = 17.20;
                    break;
                case "Utrecht-Rotterdam":
                    ritPrijs = 12.40;
                    break;
                case "Arnhem-Rotterdam":
                    ritPrijs = 21.30;
                    break;
                case "Arnhem-Utrecht":
                    ritPrijs = 8.90;
                    break;
            }
        return ritPrijs;
    }
}
