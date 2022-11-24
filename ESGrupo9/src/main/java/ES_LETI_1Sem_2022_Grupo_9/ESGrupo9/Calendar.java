package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;


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
				jsonString =jsonString + "{";
				isInsideEvent = true;
				continue;
			}
			if(!isInsideEvent){
				continue;
			}
			if(inputline.contains("END:VEVENT")){
				jsonString =jsonString + "}";
				isInsideEvent = false;
				continue;
			}
			if(inputline.contains("DTSTAMP:") || inputline.contains("DSTART:")|| inputline.contains("DTEND:") ||
					inputline.contains("SUMMARY:") || inputline.contains("UID:") || inputline.contains("DESCRIPTION:") 
					|| inputline.contains("LOCATION:"))
				jsonString = jsonString + "\"" + inputline.replaceFirst(":","\":\"") + "\",";
			else jsonString = StringUtils.chop(StringUtils.chop(jsonString)) + inputline + "\",";
		}
		if (isInsideEvent) jsonString += "}]"; else jsonString += "]";
		writeHTML(jsonString);
		System.out.println(jsonString);
		br.close();

	}

	public static void writeHTML(String json) throws IOException{
		String site = "\r\n"
				
				+ "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "	<head>\r\n"
				+ "		<link href=\"/css/tabulator/5.4/tabulator.min.css\" rel=\"stylesheet\">\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<div id=\"example-table\"></div>\r\n"
				+ "				\r\n"
				+ "				<script src=\"https://cdn.jsdelivr.net/npm/luxon/build/global/luxon.min.js\"></script>\r\n"
				+ "				\r\n"
				+ "		<script type=\"text/javascript\" src=\"/js/tabulator/5.4/tabulator.min.js\"></script>\r\n"
				+ "		<script type=\"text/javascript\">\r\n"
				+ "	//sample data\r\n"
				+ "	var tabledata = [\r\n"
				// concatenar o conteúdo do JSON
				+ "		{id:1, name:\"Oli Bob\", age:\"12\", col:\"red\", dob:\"12/08/2017\"},\r\n"
				+ "		{id:2, name:\"Mary May\", age:\"1\", col:\"blue\", dob:\"14/05/1982\"},\r\n"
				+ "		{id:3, name:\"Christine Lobowski\", age:\"42\", col:\"green\", dob:\"22/05/1982\"},\r\n"
				+ "		{id:4, name:\"Brendon Philips\", age:\"125\", col:\"orange\", dob:\"01/08/1980\"},\r\n"
				+ "		{id:5, name:\"Margret Marmajuke\", age:\"16\", col:\"yellow\", dob:\"31/01/1999\"},\r\n"
				+ "	];\r\n"
				+ "	\r\n"
				+ "	var table = new Tabulator(\"#example-table\", {\r\n"
				+ "		height:200, // set height of table to enable virtual DOM\r\n"
				+ "		data:tabledata, //load initial data into table\r\n"
				+ "		layout:\"fitColumns\", //fit columns to width of table (optional)\r\n"
				+ "		columns:[ //Define Table Columns\r\n"
				+ "			{title:\"Name\", field:\"name\", sorter:\"string\", width:150},\r\n"
				+ "			{title:\"Age\", field:\"age\", sorter:\"number\", hozAlign:\"left\", formatter:\"progress\"},\r\n"
				+ "			{title:\"Favourite Color\", field:\"col\", sorter:\"string\", headerSort:false},\r\n"
				+ "			{title:\"Date Of Birth\", field:\"dob\", sorter:\"date\", hozAlign:\"center\"},\r\n"
				+ "		],\r\n"
				+ "	});\r\n"
				+ "	\r\n"
				+ "	//trigger an alert message when the row is clicked\r\n"
				+ "	table.on(\"rowClick\", function(e, row){\r\n"
				+ "		alert(\"Row \" + row.getIndex() + \" Clicked!!!!\");\r\n"
				+ "	});\r\n"
				+ "</script>\r\n"
				+ "	</body>\r\n"
				+ "</html>";
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("Calendario.html"));
		bw.write(site);
		bw.close();
		File htmlFile = new File("Calendario.html");
		Desktop.getDesktop().browse(htmlFile.toURI());
	}
}
