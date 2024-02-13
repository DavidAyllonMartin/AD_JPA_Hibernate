package ejercicios.ejercicios71.ejercicio713;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NFLParser {
    private static final Map<String, String> MONTH_MAP = new HashMap<>();
    private static final Map<String, Integer> TEAM_MAP = new HashMap<>();
    private static final String TRANSACTION_TABLE_CLASS = ".tableType-transaction";
    private static final String PLAYER_TABLE_CLASS = ".tableType-player";
    private static final int TRANSACTION_YEAR = 2023;
    private static int playerId = 10032;

    static {
        MONTH_MAP.put("JAN", "01");
        MONTH_MAP.put("FEB", "02");
        MONTH_MAP.put("MAR", "03");
        MONTH_MAP.put("APR", "04");
        MONTH_MAP.put("MAY", "05");
        MONTH_MAP.put("JUN", "06");
        MONTH_MAP.put("JUL", "07");
        MONTH_MAP.put("AUG", "08");
        MONTH_MAP.put("SEP", "09");
        MONTH_MAP.put("OCT", "10");
        MONTH_MAP.put("NOV", "11");
        MONTH_MAP.put("DEC", "12");

        TEAM_MAP.put("ARI", 1);
        TEAM_MAP.put("ATL", 2);
        TEAM_MAP.put("BAL", 3);
        TEAM_MAP.put("BUF", 4);
        TEAM_MAP.put("CAR", 5);
        TEAM_MAP.put("CHI", 6);
        TEAM_MAP.put("CIN", 7);
        TEAM_MAP.put("CLE", 8);
        TEAM_MAP.put("DAL", 9);
        TEAM_MAP.put("DEN", 10);
        TEAM_MAP.put("DET", 11);
        TEAM_MAP.put("GB", 12);
        TEAM_MAP.put("HOU", 13);
        TEAM_MAP.put("IND", 14);
        TEAM_MAP.put("JAX", 15);
        TEAM_MAP.put("KC", 16);
        TEAM_MAP.put("LAC", 17);
        TEAM_MAP.put("LA", 18);
        TEAM_MAP.put("MIA", 19);
        TEAM_MAP.put("MIN", 20);
        TEAM_MAP.put("NE", 21);
        TEAM_MAP.put("NO", 22);
        TEAM_MAP.put("NYG", 23);
        TEAM_MAP.put("NYJ", 24);
        TEAM_MAP.put("LV", 25);
        TEAM_MAP.put("PHI", 26);
        TEAM_MAP.put("PIT", 27);
        TEAM_MAP.put("SF", 28);
        TEAM_MAP.put("SEA", 29);
        TEAM_MAP.put("TB", 30);
        TEAM_MAP.put("TEN", 31);
        TEAM_MAP.put("WAS", 32);
    }

    public static List<String[]> parseTransactionsFromHTML(String html) {
        List<String[]> transactionsList = new ArrayList<>();

        Document document = Jsoup.parse(html);
        Element table = document.selectFirst(TRANSACTION_TABLE_CLASS);

        if (table != null) {
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");

                if (columns.size() == 7) {
                    String[] transactionData = extractTransactionData(columns);
                    transactionsList.add(transactionData);
                }
            }
        }

        return transactionsList;
    }

    private static String[] extractTransactionData(Elements columns) {
        String[] transactionData = new String[7];
        transactionData[0] = formatDate(columns.get(0).text());  // Date
        transactionData[1] = columns.get(1).text();  // Week
        transactionData[2] = columns.get(2).text();  // Type

        Element playerNameElement = columns.get(3).selectFirst(".playerCard");
        if (playerNameElement != null) {
            transactionData[3] = playerNameElement.text();  // Player Name
        }

        transactionData[4] = columns.get(4).text();  // From
        transactionData[5] = columns.get(5).text();  // To
        transactionData[6] = columns.get(6).text();  // By

        return transactionData;
    }

    public static List<String[]> parsePlayersFromHTML(String html) {
        List<String[]> transactionsList = new ArrayList<>();

        Document document = Jsoup.parse(html);
        Element table = document.selectFirst(PLAYER_TABLE_CLASS);

        if (table != null) {
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");

                String[] playerData = extractPlayerData(columns);
                transactionsList.add(playerData);
            }
        }

        return transactionsList;
    }

    private static String[] extractPlayerData(Elements columns) {
        String[] playerData = new String[3];

        Elements playerInfo = columns.get(1).getAllElements().get(0).getAllElements();
        String positionTeam = playerInfo.get(4).text();
        String[] strings = positionTeam.split("-");
        playerData[1] = strings[0].trim(); // Position

        if (strings.length == 2) {
            playerData[2] = strings[1].trim(); // Team
        }

        playerData[0] = playerInfo.get(1).selectFirst(".playerCard").text().trim(); // Name

        return playerData;
    }

    private static String formatDate(String originalDate) {
        String[] dateParts = originalDate.split(",");
        String[] dateInfo = dateParts[0].split(" ");

        String month = dateInfo[0];
        String day = (dateInfo[1].length() == 1) ? "0" + dateInfo[1] : dateInfo[1];

        String hour12 = dateParts[1].trim();
        String hour24 = formatHour(hour12);
        String parsedMonth = parseMonth(month);

        LocalDate localDate = LocalDate.of(NFLParser.TRANSACTION_YEAR, Integer.parseInt(parsedMonth), Integer.parseInt(day));
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return formattedDate + "T" + hour24;
    }

    private static String parseMonth(String month) {
        return MONTH_MAP.getOrDefault(month.toUpperCase(), month);
    }

    public static String formatHour(String hour12) {
        Pattern pattern = Pattern.compile("(\\d{1,2}):(\\d{2})(am|pm)");
        Matcher matcher = pattern.matcher(hour12);

        if (matcher.matches()) {
            int hour = Integer.parseInt(matcher.group(1));
            int minute = Integer.parseInt(matcher.group(2));
            String amPmIndicator = matcher.group(3);

            if (amPmIndicator.equalsIgnoreCase("pm") && hour != 12) {
                hour += 12;
            } else if (amPmIndicator.equalsIgnoreCase("am") && hour == 12) {
                hour = 0;
            }

            return LocalTime.of(hour, minute).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else {
            throw new IllegalArgumentException("Invalid hour format: " + hour12);
        }
    }
    public static String parseTeam(String team) {
        Integer number = TEAM_MAP.get(team);
        if (number != null) {
            return number.toString();
        } else {
            return "Equipo no encontrado";
        }
    }

    public static String buildInsertStatement(String[] playerInfo) {
        if (playerInfo == null || playerInfo.length != 3) {
            throw new IllegalArgumentException("El array de información del jugador debe contener exactamente 3 elementos.");
        }

        String playerName = playerInfo[0];
        String position = playerInfo[1];
        String nflTeam = playerInfo[2];
        if (nflTeam != null) {
            nflTeam = parseTeam(nflTeam);
        }

        return String.format("INSERT INTO players (id, player_name, position, nfl_team) VALUES (%d, \"%s\", '%s', %s);", playerId++, playerName, position, nflTeam);
    }

    private static void writeInserts(Path source, Path destination) {
        try (Stream<String> lines = Files.lines(source)) {
            List<String[]> playerList = lines
                    .flatMap(line -> parsePlayersFromHTML(line).stream())
                    .collect(Collectors.toList());

            List<String> insertStatements = playerList.stream()
                    .map(NFLParser::buildInsertStatement)
                    .collect(Collectors.toList());

            Files.write(destination, insertStatements, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        /*writeInserts(Path.of("src/main/resources/scripts/k"), Path.of("src/main/resources/scripts/inserts"));
        writeInserts(Path.of("src/main/resources/scripts/dl"), Path.of("src/main/resources/scripts/inserts"));
        writeInserts(Path.of("src/main/resources/scripts/lb"), Path.of("src/main/resources/scripts/inserts"));
        writeInserts(Path.of("src/main/resources/scripts/db"), Path.of("src/main/resources/scripts/inserts"));
    */
        System.out.println(Date.valueOf("2003/12/12"));
    }
}

