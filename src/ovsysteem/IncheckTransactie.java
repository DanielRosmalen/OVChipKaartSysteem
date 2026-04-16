package ovsysteem;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class IncheckTransactie {
    LocalDateTime lt = LocalDateTime.now();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.HH.mm");
    public int transactieID = (int) (Math.random() * 10000);
    public String beginTijd = lt.format(df);
    public String locatie;

    public String getTransactieData() {
        return "Transactie ID: " + transactieID + " - Inchecktijd: " + beginTijd + " - Locatie: " + locatie;
    }
}