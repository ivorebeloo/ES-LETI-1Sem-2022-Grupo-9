package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The class Week
 */ 
public class Week {
	private ArrayList<LocalDate> days;

	/** 
	 *
	 * It is a constructor. 
	 *
	 * @param date  the date
	 */
	public Week(LocalDate date) { 
		days = new ArrayList<>();
		LocalDate monday = getStartOfWeek(date);
		days.add(monday);
		for (int i = 1; i < 5; i++) {
			days.add(monday.plusDays(i));
		}
	}

	/** 
	 *
	 * Gets the start of week
	 *
	 * @param date  the date
	 * @return the start of week
	 */
	public static LocalDate getStartOfWeek(LocalDate date) { 
		LocalDate day = date;
		while (day.getDayOfWeek() != DayOfWeek.MONDAY) {
			day = day.minusDays(1);
		}
		return day;
	}

	/** 
	 *
	 * Gets the day
	 *
	 * @param dayOfWeek  the day of week
	 * @return the day
	 */
	public LocalDate getDay(DayOfWeek dayOfWeek) { 
		return days.get(dayOfWeek.getValue() - 1);
	}

	/** 
	 *
	 * It is a constructor. 
	 *
	 */
	public Week nextWeek() { 
		final LocalDate friday = getDay(DayOfWeek.FRIDAY);
		return new Week(friday.plusDays(3));
	}

	/** 
	 *
	 * It is a constructor. 
	 *
	 */
	public Week prevWeek() { 
		final LocalDate monday = getDay(DayOfWeek.MONDAY);
		return new Week(monday.minusDays(3));
	}

	/** 
	 *
	 * To string
	 *
	 * @return String
	 */
	public String toString() { 
		return "Week of the " + getDay(DayOfWeek.MONDAY);
	}

	/** 
	 *
	 * Main
	 *
	 * @param args  the args
	 */
	public static void main(String[] args) { 
		LocalDate now = LocalDate.now();
		Week currentWeek = new Week(now);
		System.out.println(currentWeek);
		System.out.println(currentWeek.prevWeek());
		System.out.println(currentWeek.nextWeek());
	}

}