package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;


public class Calendar {

	public static void getCalendar() throws Exception {

		Scanner sc = new Scanner(System.in);
		System.out.println("Insira aqui o seu URI:" + "\n");	
		String inputLine =sc.nextLine();
		sc.close();

		//		System.out.println("Insira aqui o seu nome:" + "\n");
		//		Scanner scanner = new Scanner(System.in);
		//		String nome= scanner.nextLine();
		//		String nomeSemEspaco=nome.replaceAll("\\s+","");
		//		scanner.close();

		URL calendarURL = new URL(inputLine);
		URLConnection URL = calendarURL.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(URL.getInputStream()));


		//	File file = new File("JSONCalendar/" + nomeSemEspaco+"URL"+ ".json");
		File file = new File("JSONCalendar/" +"Calendar"+ ".json");
		if (!file.exists()){
			file.createNewFile();
		}	

		FileWriter fileWriter = new FileWriter(file,false);

		while ((inputLine = br.readLine()) != null) {
			//fileWriter.write(inputLine + "\n");
			fileWriter.write(new Gson().toJson(inputLine) + "\n");
		}

		fileWriter.close();
		br.close();
		System.out.println("JA ACABEI");

	}


	public static void main(String[] args) throws Exception {
		getCalendar();
	}




}