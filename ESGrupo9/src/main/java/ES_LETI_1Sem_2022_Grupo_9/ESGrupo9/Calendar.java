package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class Calendar {

	public static void getCalendar() throws Exception {
		URL calendarURL = new URL("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg");
		URLConnection URL = calendarURL.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(URL.getInputStream()));
		String inputLine;
		while ((inputLine = br.readLine()) != null) 
			System.out.println(inputLine);
		br.close();
	}
	
	public static void main(String[] args) throws Exception {
        getCalendar();
    }




}