package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

//FDS Ano:2022 ,Mes:10 ,Dia:15 ,horaInicio:10 ,minutoInicio:0 
//Mes 2,3,4,5,8,9,10,11,12  Ano 2023

 /**
 * The class Abstract calendar extends J component
 */ 
public abstract class Calendar extends JComponent {
    protected static final LocalTime START_TIME = LocalTime.of(8, 0);
    protected static final LocalTime END_TIME = LocalTime.of(22, 0);
    protected static final int MIN_WIDTH = 600;
    protected static final int MIN_HEIGHT = 600;
    protected static final int HEADER_HEIGHT = 30;
    protected static final int TIME_COL_WIDTH = 100;
    private static final int FONT_LETTER_PIXEL_WIDTH = 7;
    private ArrayList<CalendarEvent> events;
    private double timeScale;
    private double dayWidth;
    private Graphics2D g2;
    private EventListenerList listenerList = new EventListenerList();
/** 
 *
 * Calendar
 *
 * @return public
 */
    public Calendar() { 
        this(new ArrayList<>());
    }
    Calendar(ArrayList<CalendarEvent> events) {
        this.events = events;
        setupEventListeners();
        setupTimer();
    }
/** 
 *
 * Round time
 *
 * @param time  the time
 * @param minutes  the minutes
 * @return LocalTime
 */
    public static LocalTime roundTime(LocalTime time, int minutes) { 

        LocalTime t = time;
        if (t.getMinute() % minutes > minutes / 2) {
            t = t.plusMinutes(minutes - (t.getMinute() % minutes));
        } else if (t.getMinute() % minutes < minutes / 2) {
            t = t.minusMinutes(t.getMinute() % minutes);
        }
        return t;
    }
/** 
 *
 * Setup event listeners
 *
 */
    private void setupEventListeners() { 
        this.addMouseListener(new MouseAdapter() {
            @Override
/** 
 *
 * Mouse clicked
 *
 * @param e  the e
 */
            public void mouseClicked(MouseEvent e) { 
                super.mouseClicked(e);
                if (!checkCalendarEventClick(e.getPoint())) {
                    checkCalendarEmptyClick(e.getPoint());
                }
            }
        });
    }
    protected abstract boolean dateInRange(LocalDate date);
/** 
 *
 * Check calendar event click
 *
 * @param p  the p
 * @return boolean
 */
    private boolean checkCalendarEventClick(Point p) { 
        double x0, x1, y0, y1;
        for (CalendarEvent event : events) {
            if (!dateInRange(event.getDate())) continue;
            x0 = dayToPixel(event.getDate().getDayOfWeek());
            y0 = timeToPixel(event.getStart());
            x1 = dayToPixel(event.getDate().getDayOfWeek()) + dayWidth;
            y1 = timeToPixel(event.getEnd());
            if (p.getX() >= x0 && p.getX() <= x1 && p.getY() >= y0 && p.getY() <= y1) {
                fireCalendarEventClick(event);
                return true;
            }
        }
        return false;
    }
/** 
 *
 * Check calendar empty click
 *
 * @param p  the p
 * @return boolean
 */
    private boolean checkCalendarEmptyClick(Point p) { 

        final double x0 = dayToPixel(getStartDay());
        final double x1 = dayToPixel(getEndDay()) + dayWidth;
        final double y0 = timeToPixel(START_TIME);
        final double y1 = timeToPixel(END_TIME);
        if (p.getX() >= x0 && p.getX() <= x1 && p.getY() >= y0 && p.getY() <= y1) {
            LocalDate date = getDateFromDay(pixelToDay(p.getX()));
            fireCalendarEmptyClick(LocalDateTime.of(date, pixelToTime(p.getY())));
            return true;
        }
        return false;
    }
    protected abstract LocalDate getDateFromDay(DayOfWeek day);
    // CalendarEventClick methods
/** 
 *
 * Add calendar event click listener
 *
 * @param l  the l
 */
    public void addCalendarEventClickListener(CalendarEventClickListener l) { 

        listenerList.add(CalendarEventClickListener.class, l);
    }

/** 
 *
 * Remove calendar event click listener
 *
 * @param l  the l
 */
    public void removeCalendarEventClickListener(CalendarEventClickListener l) { 
        listenerList.remove(CalendarEventClickListener.class, l);
    }
    // Notify all listeners that have registered interest for
    // notification on this event type.
/** 
 *
 * Fire calendar event click
 *
 * @param calendarEvent  the calendar event
 */
    private void fireCalendarEventClick(CalendarEvent calendarEvent) { 
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        CalendarEventClickEvent calendarEventClickEvent;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CalendarEventClickListener.class) {
                calendarEventClickEvent = new CalendarEventClickEvent(this, calendarEvent);
                ((CalendarEventClickListener) listeners[i + 1]).calendarEventClick(calendarEventClickEvent);
            }
        }
    }
    // CalendarEmptyClick methods
/** 
 *
 * Add calendar empty click listener
 *
 * @param l  the l
 */
    public void addCalendarEmptyClickListener(CalendarEmptyClickListener l) { 

        listenerList.add(CalendarEmptyClickListener.class, l);
    }
/** 
 *
 * Remove calendar empty click listener
 *
 * @param l  the l
 */
    public void removeCalendarEmptyClickListener(CalendarEmptyClickListener l) { 

        listenerList.remove(CalendarEmptyClickListener.class, l);
    }
/** 
 *
 * Fire calendar empty click
 *
 * @param dateTime  the date time
 */
    private void fireCalendarEmptyClick(LocalDateTime dateTime) { 

        Object[] listeners = listenerList.getListenerList();
        CalendarEmptyClickEvent calendarEmptyClickEvent;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CalendarEmptyClickListener.class) {
                calendarEmptyClickEvent = new CalendarEmptyClickEvent(this, dateTime);
                ((CalendarEmptyClickListener) listeners[i + 1]).calendarEmptyClick(calendarEmptyClickEvent);
            }
        }
    }
/** 
 *
 * Calculate scale vars
 *
 */
    private void calculateScaleVars() { 
        int width = getWidth();
        int height = getHeight();
        if (width < MIN_WIDTH) {
            width = MIN_WIDTH;
        }
        if (height < MIN_HEIGHT) {
            height = MIN_HEIGHT;
        }
        // Units are pixels per second
        timeScale = (double) (height - HEADER_HEIGHT) / (END_TIME.toSecondOfDay() - START_TIME.toSecondOfDay());
        dayWidth = (width - TIME_COL_WIDTH) / numDaysToShow();
    }
    protected abstract int numDaysToShow();

    // Gives x val of left most pixel for day col
    protected abstract double dayToPixel(DayOfWeek dayOfWeek);
/** 
 *
 * Time to pixel
 *
 * @param time  the time
 * @return double
 */
    private double timeToPixel(LocalTime time) { 

        return ((time.toSecondOfDay() - START_TIME.toSecondOfDay()) * timeScale) + HEADER_HEIGHT;
    }
/** 
 *
 * Pixel to time
 *
 * @param y  the y
 * @return LocalTime
 */
    private LocalTime pixelToTime(double y) { 

        return LocalTime.ofSecondOfDay((int) ((y - HEADER_HEIGHT) / timeScale) + START_TIME.toSecondOfDay()).truncatedTo(ChronoUnit.MINUTES);
    }
/** 
 *
 * Pixel to day
 *
 * @param x  the x
 * @return DayOfWeek
 */
    private DayOfWeek pixelToDay(double x) { 

        double pixel;
        DayOfWeek day;
        for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
            day = DayOfWeek.of(i);
            pixel = dayToPixel(day);
            if (x >= pixel && x < pixel + dayWidth) {
                return day;
            }
        }
        return null;
    }
    @Override
    protected void paintComponent(Graphics g) {
        calculateScaleVars();
        g2 = (Graphics2D) g;
        // Rendering hints try to turn anti-aliasing on which improves quality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Set background to white
        g2.setColor(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());
        // Set paint colour to black
        g2.setColor(Color.black);
        drawDayHeadings();
        drawTodayShade();
        drawGrid();
        drawTimes();
        drawEvents();
        drawCurrentTimeLine();
    }
    protected abstract DayOfWeek getStartDay();
    protected abstract DayOfWeek getEndDay();
/** 
 *
 * Draw day headings
 *
 */
    private void drawDayHeadings() { 
        int y = 20;
        int x;
        LocalDate day;
        DayOfWeek dayOfWeek;
        for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
            dayOfWeek = DayOfWeek.of(i);
            day = getDateFromDay(dayOfWeek);
            String text = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + day.getDayOfMonth() + "/" + day.getMonthValue();
            x = (int) (dayToPixel(DayOfWeek.of(i)) + (dayWidth / 2) - (FONT_LETTER_PIXEL_WIDTH * text.length() / 2));
            g2.drawString(text, x, y);
        }
    }
/** 
 *
 * Draw grid
 *
 */
    private void drawGrid() { 

        final Color ORIG_COLOUR = g2.getColor();
        Color alphaGray = new Color(128, 128, 128, 128);
        Color alphaGrayLighter = new Color(200, 200, 200, 128);
        g2.setColor(alphaGray);
        double x;
        for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
            x = dayToPixel(DayOfWeek.of(i));
            g2.draw(new Line2D.Double(x, HEADER_HEIGHT, x, timeToPixel(END_TIME)));
        }
        double y;
        int x1;
        for (LocalTime time = START_TIME; time.compareTo(END_TIME) <= 0; time = time.plusMinutes(30)) {
            y = timeToPixel(time);
            if (time.getMinute() == 0) {
                g2.setColor(alphaGray);
                x1 = 0;
            } else {
                g2.setColor(alphaGrayLighter);
                x1 = TIME_COL_WIDTH;
            }
            g2.draw(new Line2D.Double(x1, y, dayToPixel(getEndDay()) + dayWidth, y));
        }
        g2.setColor(ORIG_COLOUR);
    }
/** 
 *
 * Draw today shade
 *
 */
    private void drawTodayShade() { 

        LocalDate today = LocalDate.now();
        if (!dateInRange(today)) return;
        final double x = dayToPixel(today.getDayOfWeek());
        final double y = timeToPixel(START_TIME);
        final double width = dayWidth;
        final double height = timeToPixel(END_TIME) - timeToPixel(START_TIME);
        final Color origColor = g2.getColor();
        Color alphaGray = new Color(200, 200, 200, 64);
        g2.setColor(alphaGray);
        g2.fill(new Rectangle2D.Double(x, y, width, height));
        g2.setColor(origColor);
    }
/** 
 *
 * Draw current time line
 *
 */
    private void drawCurrentTimeLine() { 

        LocalDate today = LocalDate.now();
        if (!dateInRange(today)) return;
        final double x0 = dayToPixel(today.getDayOfWeek());
        final double x1 = dayToPixel(today.getDayOfWeek()) + dayWidth;
        final double y = timeToPixel(LocalTime.now());
        final Color origColor = g2.getColor();
        final Stroke origStroke = g2.getStroke();
        g2.setColor(new Color(255, 127, 110));
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Line2D.Double(x0, y, x1, y));
        g2.setColor(origColor);
        g2.setStroke(origStroke);
    }
/** 
 *
 * Draw times
 *
 */
    private void drawTimes() { 

        int y;
        for (LocalTime time = START_TIME; time.compareTo(END_TIME) <= 0; time = time.plusHours(1)) {
            y = (int) timeToPixel(time) + 15;
            g2.drawString(time.toString(), TIME_COL_WIDTH - (FONT_LETTER_PIXEL_WIDTH * time.toString().length()) - 5, y);
        }
    }
/** 
 *
 * Draw events
 *
 */
    private void drawEvents() { 

        double x;
        double y0;
        for (CalendarEvent event : events) {
            if (!dateInRange(event.getDate())) continue;
            x = dayToPixel(event.getDate().getDayOfWeek());
            y0 = timeToPixel(event.getStart());
            Rectangle2D rect = new Rectangle2D.Double(x, y0, dayWidth, (timeToPixel(event.getEnd()) - timeToPixel(event.getStart())));
            Color origColor = g2.getColor();
            g2.setColor(event.getColor());
            g2.fill(rect);
            g2.setColor(origColor);
            Font origFont = g2.getFont();
            final float fontSize = origFont.getSize() - 1.6F;
            Font newFont = origFont.deriveFont(Font.BOLD, fontSize);
            g2.setFont(newFont);
            g2.drawString(event.getStart() + " - " + event.getEnd(), (int) x + 5, (int) y0 + 11);
            g2.setFont(origFont.deriveFont(fontSize));
            g2.drawString(event.getText(), (int) x+5, (int) y0 + 23);
            g2.setFont(origFont.deriveFont(fontSize));
            String text = event.getText();
            for(int i = 0; i < 10; i++) {
                int toCut = cutString(text);
                String toPrint = text.substring(0, toCut);
                g2.drawString(toPrint, (int) x + 5, (int) y0 + 23 + 11*i);
                if (toCut == 0) break;
                text = text.substring(toCut).strip();
            }
            g2.setFont(origFont);
        }
    }

    protected double getDayWidth() {
        return dayWidth;
    }
/** 
 *
 * Setup timer
 *
 */
    private void setupTimer() { 

        Timer timer = new Timer(1000*60, e -> repaint());
        timer.start();
    }

    protected abstract void setRangeToToday();
/** 
 *
 * Go to today
 *
 */
    public void goToToday() { 

        setRangeToToday();
        repaint();
    }
/** 
 *
 * Add event
 *
 * @param event  the event
 */
    public void addEvent(CalendarEvent event) { 

        events.add(event);
        repaint();
    }
/** 
 *
 * Remove event
 *
 * @param event  the event
 * @return boolean
 */
    public boolean removeEvent(CalendarEvent event) { 

        boolean removed = events.remove(event);
        repaint();
        return removed;
    }
/** 
 *
 * Sets the events
 *
 * @param events  the events
 */
    public void setEvents(ArrayList<CalendarEvent> events) { 

        this.events = events;
        repaint();
    }
/** 
 *
 * Cut string
 *
 * @param text  the text
 * @return int
 */
    private int cutString(String text){ 

        String lastString = "";
        for (String cut : text.split(" ")) {
            if (g2.getFontMetrics().stringWidth((lastString +  " " + cut)) < dayWidth) {
                lastString += " " + cut;
            }
            else break;
        }
        return lastString.length() - 1;
    }
}