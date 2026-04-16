package ovsysteem;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class OpwaardeerTransactie {
    LocalDateTime ldt = LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm");
    public double bedrag;
    public String betaalMethode;
    public String datum = ldt.format(dtf);
}