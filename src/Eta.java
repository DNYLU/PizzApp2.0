import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Eta {
    private static final String MINUTE_OPTION = "-m";
    private static final String HOUR_OPTION = "-h";

    private LocalDateTime eta;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public Eta(String option, int time) {
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();

        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();

        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);

        switch (option) {
            case Eta.MINUTE_OPTION:
                dateTime = dateTime.plusMinutes(time);
                break;
            case Eta.HOUR_OPTION:
                dateTime = dateTime.plusHours(time);
                break;
        }

        this.setEta(dateTime);
    }

    public void setEta(LocalDateTime eta) {
        this.eta = eta;
    }

    public String toString() {
        return this.formatter.format(this.eta);
    }

    public LocalDateTime getEta() {
        return this.eta;
    }

    public static String getMinuteOption() {
        return Eta.MINUTE_OPTION;
    }

    public static String getHourOption() {
        return Eta.HOUR_OPTION;
    }
}
