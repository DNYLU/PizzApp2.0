import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ETA {
    private LocalDateTime eta;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public ETA(String option, int time) {
        final String MINUTE_OPTION = "-m";
        final String HOUR_OPTION = "-h";

        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();

        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();

        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);

        switch (option) {
            case MINUTE_OPTION:
                dateTime = dateTime.plusMinutes(time);
                break;
            case HOUR_OPTION:
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
}
