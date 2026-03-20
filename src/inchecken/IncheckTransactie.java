package inchecken;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class IncheckTransactie {
    LocalDateTime lt = LocalDateTime.now();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.HH.mm");
    public int transactieID;
    public String beginTijd = lt.format(df);
    public String locatie;

    public String getTransactieData() {
        return "ID: " + transactieID + "- Inchecktijd: " + beginTijd + "- Locate: " + locatie;
    }
}
