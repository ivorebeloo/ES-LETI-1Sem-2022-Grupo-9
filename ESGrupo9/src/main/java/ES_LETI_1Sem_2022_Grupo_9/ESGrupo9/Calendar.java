package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Calendar {

	public static void main (String[] args) throws Exception  {
		String httpsURL = "https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg";
		URL calendarURL = new URL(httpsURL);
		URLConnection URL = calendarURL.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(URL.getInputStream()));
		String inputline;
		String jsonString = "["; 
		boolean isInsideEvent = false;
		while((inputline = br.readLine()) != null){
			if(inputline.contains("BEGIN:VEVENT")){
				jsonString = jsonString + "{";
				isInsideEvent = true;
				continue;
			}
			if(!isInsideEvent){
				continue;
			}
			if(inputline.contains("END:VEVENT")){
				jsonString = jsonString + "},";
				isInsideEvent = false;
				continue;
			}
			if(inputline.contains("DTSTAMP:") || inputline.contains("DTSTART:")|| inputline.contains("DTEND:") ||
					inputline.contains("SUMMARY:") || inputline.contains("UID:") || inputline.contains("DESCRIPTION:") 
					|| inputline.contains("LOCATION:")) {
				jsonString = jsonString + "\"" + inputline.replaceFirst(":","\":\"") + "\",";
			} else {
				jsonString = jsonString.substring(0, jsonString.length() - 2) + inputline.substring(1,inputline.length()) + "\",";
			}
		}
		if(isInsideEvent) jsonString += "}]";
		else jsonString += "]";
		br.close();
		writeHTML(jsonString);
		System.out.println(jsonString);
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
				+ "			{title:\"Data de Início\", field:\"DSTART:\", sorter:\"date\", width:150},\r\n"
				+ "			{title:\"Data de Fim\", field:\"DTEND:\", sorter:\"date\", hozAlign:\"left\", formatter:\"progress\"},\r\n"
				+ "			{title:\"Unidade Curricular\", field:\"sala\", sorter:\"string\", headerSort:false},\r\n"
				+ "			{title:\"Sala\", field:\"sala\", sorter:\"string\", headerSort:false},\r\n"
				+ "			{title:\"Sumário\", field:\"SUMMARY\", sorter:\"string\", hozAlign:\"center\"},\r\n"
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
		BufferedWriter bw = new BufferedWriter(new FileWriter("ES-LETI-1Sem-2022-Grupo-9.html"));
		bw.write(site);
		bw.close();
		File htmlFile = new File("ES-LETI-1Sem-2022-Grupo-9.html");
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

}