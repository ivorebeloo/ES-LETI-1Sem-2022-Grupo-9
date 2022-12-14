package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

 /**
 * The class Calendar event
 */ 
public class CalendarEvent {
    private static final Color DEFAULT_COLOR = Color.PINK;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String text;
    private Color color;

/** 
 *
 * It is a constructor. 
 *
 * @param date  the date
 * @param start  the start
 * @param end  the end
 * @param text  the text
 */
    public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text) { 
        this(date, start, end, text, DEFAULT_COLOR);
    }

/** 
 *
 * It is a constructor. 
 *
 * @param date  the date
 * @param start  the start
 * @param end  the end
 * @param text  the text
 * @param color  the color
 */
    public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text, Color color) { 
        this.date = date;
        this.start = start;
        this.end = end;
        this.text = text;
        this.color = color;
    }

/** 
 *
 * Gets the date
 *
 * @return the date
 */
    public LocalDate getDate() { 
        return date;
    }

/** 
 *
 * Sets the date
 *
 * @param date  the date
 */
    public void setDate(LocalDate date) { 
        this.date = date;
    }

/** 
 *
 * Gets the start
 *
 * @return the start
 */
    public LocalTime getStart() { 
        return start;
    }

/** 
 *
 * Sets the start
 *
 * @param start  the start
 */
    public void setStart(LocalTime start) { 
        this.start = start;
    }

/** 
 *
 * Gets the end
 *
 * @return the end
 */
    public LocalTime getEnd() { 
        return end;
    }

/** 
 *
 * Sets the end
 *
 * @param end  the end
 */
    public void setEnd(LocalTime end) { 
        this.end = end;
    }

/** 
 *
 * Gets the text
 *
 * @return the text
 */
    public String getText() { 
        return text;
    }

/** 
 *
 * Sets the text
 *
 * @param text  the text
 */
    public void setText(String text) { 
        this.text = text;
    }

/** 
 *
 * To string
 *
 * @return String
 */
    public String toString() { 
        return "Data: " + getDate() + " " + getStart() + "-" + getEnd() + "\n Sumário: " + getText();
    }

/** 
 *
 * Gets the color
 *
 * @return the color
 */
    public Color getColor() { 
        return color;
    }

/** 
 *
 * Sets the color
 *
 * @param color  the color
 */
    public void setColor(Color color){ 
    	this.color=color;
    }

    @Override
/** 
 *
 * Equals
 *
 * @param o  the object
 * @return boolean
 */
    public boolean equals(Object o) { 
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarEvent that = (CalendarEvent) o;
        if (!date.equals(that.date)) return false;
        if (!start.equals(that.start)) return false;
        return end.equals(that.end);
    }

    @Override
/** 
 *
 * Hash code
 *
 * @return int
 */
    public int hashCode() { 
        int result = date.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
    
}