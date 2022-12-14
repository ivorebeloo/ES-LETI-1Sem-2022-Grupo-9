package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.awt.*;
import java.time.LocalDateTime;
 /**
 * The class Calendar empty click event extends AWT event
 */ 
public class CalendarEmptyClickEvent extends AWTEvent {
    private LocalDateTime dateTime;
/** 
 *
 * Calendar empty click event
 *
 * @param source  the source
 * @param dateTime  the date time
 * @return public
 */
    public CalendarEmptyClickEvent(Object source, LocalDateTime dateTime) { 

        super(source, 0);
        this.dateTime = dateTime;
    }
/** 
 *
 * Gets the date time
 *
 * @return the date time
 */
    public LocalDateTime getDateTime() { 

        return dateTime;
    }
}