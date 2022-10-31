package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class Calendar {

	public static void getCalendar() throws Exception {
		String calendarURL = "http://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg";
		URL url = new URL(calendarURL);
		HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
		InputStream is = conexao.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String inputLine;
		String jsonString = "J";
		boolean isInsideEvent = false; 
		while((inputLine = br.readLine()) != null) {
			if(inputLine.compareTo("BEGINEVENT") == 0) {
				jsonString = jsonString + "J";
				isInsideEvent = true;
			} else if(inputLine.compareTo("ENDEVENT") == 0 && isInsideEvent) {
				jsonString = jsonString + inputLine + "J";
			}
		}
		jsonString = calendarURL;
		System.out.println(jsonString);
		br.close(); 
	}

	public static void main(String[] args) throws Exception {
		getCalendar();
	}

}