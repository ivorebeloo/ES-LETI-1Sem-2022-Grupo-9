package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;


public class Calendar {
//
//	public static void getCalendar() throws Exception {
//
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Insira aqui o seu URI:" + "\n");	
//		String inputLine =sc.nextLine();
//		sc.close();
//		System.out.println("URI liso");
//
//		//		System.out.println("Insira aqui o seu nome:" + "\n");
//		//		Scanner scanner = new Scanner(System.in);
//		//		String nome= scanner.nextLine();
//		//		String nomeSemEspaco=nome.replaceAll("\\s+","");
//		//		scanner.close();
//
//		URL calendarURL = new URL(inputLine);
//		URLConnection URL = calendarURL.openConnection();
//		BufferedReader br = new BufferedReader(new InputStreamReader(URL.getInputStream()));
//
//
//		//	File file = new File("JSONCalendar/" + nomeSemEspaco+"URL"+ ".json");
//		//File file = new File("JSONCalendar/" +"Calendar"+ ".json");
//		File file = new File("calendar.json");
//		if (!file.exists()){
//			file.createNewFile();
//		}	
//
//		FileWriter fileWriter = new FileWriter(file,false);
//
//		while ((inputLine = br.readLine()) != null) {
//			//fileWriter.write(inputLine + "\n");
//			fileWriter.write(new Gson().toJson(inputLine) + "\n");
//		}
//
//		fileWriter.close();
//		br.close();
//		System.out.println("JA ACABEI");
//
//	}
//
//
//	public static void main(String[] args) throws Exception {
//		getCalendar();
//	}
//
//
//
//
//}


public static void main (String[] args) throws Exception  {
	
	String httpsURL ="https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg";
	URL calendarURL = new URL(httpsURL);
	URLConnection URL = calendarURL.openConnection();
	BufferedReader br = new BufferedReader(new InputStreamReader(URL.getInputStream()));
	
	String inputline;
	String jsonString = "["; 
	boolean isInsideEvent =false;
	while ((inputline=br.readLine()) != null){
		if(inputline.contains("BEGIN:VEVENT")){
			jsonString =jsonString + "(";
			isInsideEvent = true;
			continue;
		}
		if(!isInsideEvent){
			continue;
		}
		if(inputline.contains("END:VEVENT")){
			jsonString =jsonString + ")";
			isInsideEvent = false;
			continue;
		}
		if(inputline.contains("DTSTAMP:") || inputline.contains("DSTART:")|| inputline.contains("DTEND:") ||
		inputline.contains("SUMMARY:") || inputline.contains("UID:") || inputline.contains("DESCRIPTION:") 
		|| inputline.contains("LOCATION:"))
			jsonString = jsonString + "\"" + inputline.replaceFirst(":","\":\"") + "\",";
		else jsonString = b.chop(StringUtils.chop(jsonString)) + inputline + "\",";
	}
			
			
			
}
}
