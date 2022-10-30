package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.net.URL;

public class Calendar {

	public static void getCalendar(String[] args) {
		String callendarURL = "webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?\r\nmethod=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg";
		URL url = new URL(callendarURL);
		
	}



}