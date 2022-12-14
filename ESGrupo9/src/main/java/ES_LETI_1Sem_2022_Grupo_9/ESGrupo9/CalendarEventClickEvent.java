package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.awt.*;

public class CalendarEventClickEvent extends AWTEvent {
    private CalendarEvent calendarEvent;

    public CalendarEventClickEvent(Object source, CalendarEvent calendarEvent) {
        super(source, 0);
        this.calendarEvent = calendarEvent;
    }

    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }
    
}