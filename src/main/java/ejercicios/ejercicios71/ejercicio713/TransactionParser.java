package ejercicios.ejercicios71.ejercicio713;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionParser {

    public static List<String[]> parseHTMLTable(String html) {
        List<String[]> transactionsList = new ArrayList<>();

        Document document = Jsoup.parse(html);
        Element table = document.selectFirst(".tableType-transaction");

        if (table != null) {
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");

                if (columns.size() == 7) {
                    String[] transactionData = new String[7];

                    // Extracting data from each column
                    transactionData[0] = formatDate(columns.get(0).text());  // Date
                    transactionData[1] = columns.get(1).text();  // Week
                    transactionData[2] = columns.get(2).text();  // Type

                    // Extracting only the player's name without position and team
                    Element playerNameElement = columns.get(3).selectFirst(".playerCard");
                    if (playerNameElement != null) {
                        transactionData[3] = playerNameElement.text();
                    }

                    transactionData[4] = columns.get(4).text();  // From
                    transactionData[5] = columns.get(5).text();  // To
                    transactionData[6] = columns.get(6).text();  // By

                    transactionsList.add(transactionData);
                }
            }
        }

        return transactionsList;
    }

    private static String formatDate(String originalDate) {
        String[] strings = originalDate.split(",");
        String[] strings1 = strings[0].split(" ");
        String month = strings1[0];
        String day = strings1[1];
        if (day.length() == 1){
            day = "0"+day;
        }
        String hour12 = strings[1].trim();
        String hour24 = parseHour(hour12);

        String parsedMonth = parseMonth(month);

        return "2023-"+parsedMonth+"-"+day+"T"+hour24;
    }

    private static String parseMonth(String month) {
        month = month.toUpperCase();
        switch (month){
            case "JAN":
                return "01";
            case "FEB":
                return "02";
            case "MAR":
                return "03";
            case "APR":
                return "04";
            case "MAY":
                return "05";
            case "JUN":
                return "06";
            case "JUL":
                return "07";
            case "AUG":
                return "08";
            case "SEP":
                return "09";
            case "OCT":
                return "10";
            case "NOV":
                return "11";
            case "DEC":
                return "12";

        }

        return month;
    }

    public static String parseHour(String hour12) {
        String hhmm = hour12.substring(0, hour12.length() -  2);
        String am = hour12.substring(hour12.length() - 2);
        String[] strings = hhmm.split(":");
        String hour = strings[0];
        String mins = strings[1];

        if (am.equals("am")){
            if (Integer.parseInt(hour) < 10){
                hour = "0"+hour;
            }
        }else if (am.equals("pm")){
            hour = String.valueOf(Integer.parseInt(hour) + 12);
            if (hour.equals("24")){
                hour = "00";
            }
        }
        return hour+":"+mins+":00";
    }

    public static List<String[]> parseHTMLTablePlayer(String html) {
        List<String[]> transactionsList = new ArrayList<>();

        Document document = Jsoup.parse(html);
        Element table = document.selectFirst(".tableType-player");

        if (table != null) {
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");

                Elements playerInfo = columns.get(1).getAllElements().get(0).getAllElements();
                String positionTeam = playerInfo.get(3).getAllElements().get(0).text();
                String name = playerInfo.get(1).selectFirst(".playerCard").text();
                System.out.println(positionTeam);
                System.out.println(name);
            }
        }

        return transactionsList;
    }

    public static void main(String[] args) throws IOException {
        List<String[]> list = parseHTMLTablePlayer(Files.readString(Path.of("src/main/resources/scripts/a")));
        list.forEach(array -> Arrays.stream(array).forEach(System.out::println));

    }
}

