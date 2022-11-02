package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;


public class Calendar {

	public static void getCalendar() throws Exception {
		System.out.println("------------------------------NOVA LEITURA------------------------------------------------");	
		String inputLine;
		URL calendarURL = new URL("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg");
		URLConnection URL = calendarURL.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(URL.getInputStream()));
		
		File file = new File("JSONCalendar/" + "Calendar"+ ".json");

		if (!file.exists()){
			file.createNewFile();
		}	
		FileWriter fileWriter = new FileWriter(file,false);
		
		while ((inputLine = br.readLine()) != null) {
			//System.out.println(new Gson().toJson(inputLine));
			//System.out.println(inputLine);
			fileWriter.write(inputLine + "\n");
	}
		fileWriter.close();
		br.close();
		System.out.println("JA ACABEI");
	}
		

	
	public static void main(String[] args) throws Exception {
        getCalendar();
    }




}