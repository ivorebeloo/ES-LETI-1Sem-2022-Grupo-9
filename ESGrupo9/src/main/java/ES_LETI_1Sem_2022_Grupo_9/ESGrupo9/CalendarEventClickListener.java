package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.util.EventListener;

public interface CalendarEventClickListener extends EventListener {
    // Event dispatch methods
    void calendarEventClick(CalendarEventClickEvent e);
}