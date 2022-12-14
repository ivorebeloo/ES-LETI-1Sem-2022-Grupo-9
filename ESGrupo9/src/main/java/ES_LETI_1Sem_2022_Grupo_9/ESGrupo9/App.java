package ES_LETI_1Sem_2022_Grupo_9.ESGrupo9;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


 /**
 * The class Application
 */ 
public class App {
/** 
 *
 * Main
 *
 * @param args  the args
 * @throws   Exception 
 */
	public static void main(String[] args) throws Exception { 
		JFrame frm = new JFrame();
		ArrayList<CalendarEvent> events = new ArrayList<>();
		ArrayList<String>httpsURLs = new ArrayList<>(4);
		httpsURLs.add("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=mmsas2@iscte.pt&password=2mWzk5rPqYRIbfyPN7vLW1aTGarJwbCKUsbzw4zZw3zpao7VtvLvjuQfsm0xfi3OBHXck6Ox9unjiHlMsBl9eC8zqgyCRJRrX7SH7R398ffwVbc1b3MND0D7GxNERwFF");
		httpsURLs.add("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg");
		httpsURLs.add("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=lsnso@iscte.pt&password=k2ywJChIZ72vsQYoQSvqb7pmbnhZO7W8TW9e4cXyvny9gq3sT2ANfjsXRJcAAG8QVVVc5AY6CRqG9MlrmQpA2G3tj7MepBBx9yM1Pxjs3xImG7WYAGOCgAZX0rWOjrxg");
		httpsURLs.add("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=msmsa7@iscte.pt&password=6gaNComTFjmqylprulbIgMDqLziwsMp3yQcUE2rW8jmKSvyUhWwvwrOM3BRFZTkQ9RUYTUE3jTyYtzBntKxrJiS8lN7ILH7vFwqkOBZHQynP2zyGOaMADZzSY22sRWug");
		Color c = null;
		for (int i=0; i<httpsURLs.size(); i++){
			if(httpsURLs.get(i)=="https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=mmsas2@iscte.pt&password=2mWzk5rPqYRIbfyPN7vLW1aTGarJwbCKUsbzw4zZw3zpao7VtvLvjuQfsm0xfi3OBHXck6Ox9unjiHlMsBl9eC8zqgyCRJRrX7SH7R398ffwVbc1b3MND0D7GxNERwFF"){
				c = new Color(250,218,185);
			}
			if(httpsURLs.get(i)=="https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg"){
				c = new Color(193,255,193);
			}
			if(httpsURLs.get(i)=="https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=lsnso@iscte.pt&password=k2ywJChIZ72vsQYoQSvqb7pmbnhZO7W8TW9e4cXyvny9gq3sT2ANfjsXRJcAAG8QVVVc5AY6CRqG9MlrmQpA2G3tj7MepBBx9yM1Pxjs3xImG7WYAGOCgAZX0rWOjrxg"){
				c = new Color(209,238,238);
			}
			if(httpsURLs.get(i)=="https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=msmsa7@iscte.pt&password=6gaNComTFjmqylprulbIgMDqLziwsMp3yQcUE2rW8jmKSvyUhWwvwrOM3BRFZTkQ9RUYTUE3jTyYtzBntKxrJiS8lN7ILH7vFwqkOBZHQynP2zyGOaMADZzSY22sRWug"){
				c = new Color(255,187,255);
			}
			//    	String httpsURL = "https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=mmsas2@iscte.pt&password=2mWzk5rPqYRIbfyPN7vLW1aTGarJwbCKUsbzw4zZw3zpao7VtvLvjuQfsm0xfi3OBHXck6Ox9unjiHlMsBl9eC8zqgyCRJRrX7SH7R398ffwVbc1b3MND0D7GxNERwFF";
			//    	String httpsURL = "https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=imsro@iscte.pt&password=aXWvsniEJIyWHxKZ4X4VMovKuXhJEAt7j1u450VOnlnp28QCJIhhwZWCaIP4CWrOGFpden1pjFDY3qQjO549FL3EMNv3lEpXQopNsMJdHdlkIAkGSFmnbYV0LD4Dziwg";
			URL calendarURL = new URL(httpsURLs.get(i));
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
			String [] vetor = jsonString.split("},");
			for(String s : vetor){
				int count=(s.length()-1) - (s.replace(":","").length()-1);
				String [] vetor2 = s.split(":");
				if(s.contains("]")){
					break;
				}
				if(count==24){
					String [] vetor3 = vetor2[12].split("-");
					int ano=Integer.parseInt(vetor3[0].replaceAll("\\s+",""));
					int mes=Integer.parseInt(vetor3[1]);
					String [] dia0=vetor3[2].replaceFirst(" ", "-").split("-");
					int dia=Integer.parseInt(dia0[0]);
					int horaInicio=Integer.parseInt(dia0[1]);
					int minutoInicio = Integer.parseInt(vetor2[13].substring(0,2));
					String [] hora0=vetor2[14].replaceAll(" ","-").split("-");
					int horaFim=Integer.parseInt(hora0[4]);
					int minutoFim=Integer.parseInt(vetor2[15].substring(0,2));
					String[] aula1=vetor2[5].split("-");
					String aula =vetor2[4].replaceAll("\"", " ") + ":" + aula1[0];
					//					System.out.println("Ano:" + ano + " ,Mes:" + mes + " ,Dia:" + dia + " ,horaInicio:" + horaInicio + " ,minutoInicio:" + minutoInicio + " ,horaFim:" + horaFim + " ,minutoFim:" + minutoFim);
					events.add(new CalendarEvent(LocalDate.of(ano, mes, dia), LocalTime.of(horaInicio, minutoInicio), LocalTime.of(horaFim, minutoFim), aula, c));
				} else if (count!=24){
					String [] vetor3 = vetor2[11].split("-");
					int ano=Integer.parseInt(vetor3[0].replaceAll("\\s+",""));
					int mes=Integer.parseInt(vetor3[1]);
					String [] dia0=vetor3[2].replaceFirst(" ", "-").split("-");
					int dia=Integer.parseInt(dia0[0]);
					int horaInicio=Integer.parseInt(dia0[1]);
					int minutoInicio = Integer.parseInt(vetor2[14].substring(0,2));
					String[] hora0=vetor2[13].replaceAll(" ","-").split("-");
					int horaFim=Integer.parseInt(hora0[4]);
					int minutoFim=Integer.parseInt(vetor2[14].substring(0,2));
					String [] aula1=vetor2[4].split("-");
					String aula =aula1[0].replaceAll("\"", " ");
					//					System.out.println("Ano:" + ano + " ,Mes:" + mes + " ,Dia:" + dia + " ,horaInicio:" + horaInicio + " ,minutoInicio:" + minutoInicio + " ,horaFim:" + horaFim + " ,minutoFim:" + minutoFim);
					events.add(new CalendarEvent(LocalDate.of(ano, mes, dia), LocalTime.of(horaInicio, minutoInicio), LocalTime.of(horaFim, minutoFim), aula, c));
				}else if (count==0){
					break;
				}
			}
			WeekCalendar cal = new WeekCalendar(events);
			cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
			cal.addCalendarEmptyClickListener(e -> {
				System.out.println(e.getDateTime());
				System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
			});
			JButton goToTodayBtn = new JButton("Today");
			goToTodayBtn.addActionListener(e -> cal.goToToday());
			JButton nextWeekBtn = new JButton(">");
			nextWeekBtn.addActionListener(e -> cal.nextWeek());
			JButton prevWeekBtn = new JButton("<");
			prevWeekBtn.addActionListener(e -> cal.prevWeek());
			JButton nextMonthBtn = new JButton(">>");
			nextMonthBtn.addActionListener(e -> cal.nextMonth());
			JButton prevMonthBtn = new JButton("<<");
			prevMonthBtn.addActionListener(e -> cal.prevMonth());
			JPanel weekControls = new JPanel();
			weekControls.add(prevMonthBtn);
			weekControls.add(prevWeekBtn);
			weekControls.add(goToTodayBtn);
			weekControls.add(nextWeekBtn);
			weekControls.add(nextMonthBtn);
			frm.add(weekControls, BorderLayout.NORTH);
			frm.add(cal, BorderLayout.CENTER);
			frm.setSize(1000, 900);
			frm.setVisible(true);
			frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
	}
}