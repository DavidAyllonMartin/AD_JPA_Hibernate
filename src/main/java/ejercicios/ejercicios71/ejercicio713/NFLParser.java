package ejercicios.ejercicios71.ejercicio713;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
    private static final Map<String, Integer> FANTASY_TEAM_MAP = new HashMap<>();

    private static final String TRANSACTION_TABLE_CLASS = ".tableType-transaction";
    private static final String PLAYER_TABLE_CLASS = ".tableType-player";
    private static final int TRANSACTION_YEAR = 2023;

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

        FANTASY_TEAM_MAP.put("Free Agents", 0);
        FANTASY_TEAM_MAP.put("Waivers", 0);
        FANTASY_TEAM_MAP.put("Villalba Rams", 1);
        FANTASY_TEAM_MAP.put("Catacronk NFL", 2);
        FANTASY_TEAM_MAP.put("Templars Colonia Garden", 3);
        FANTASY_TEAM_MAP.put("Allen or Nothing", 4);
        FANTASY_TEAM_MAP.put("Aluche Minions", 5);
        FANTASY_TEAM_MAP.put("The Stampede", 6);
        FANTASY_TEAM_MAP.put("Madrid Rexilons", 7);
        FANTASY_TEAM_MAP.put("Atletico de David", 8);
        FANTASY_TEAM_MAP.put("Santa Eugenia Reapers", 9);
        FANTASY_TEAM_MAP.put("Vir Saints", 10);
        FANTASY_TEAM_MAP.put("MexicanVikings", 11);
        FANTASY_TEAM_MAP.put("Help me Obi-Quon", 12);
        FANTASY_TEAM_MAP.put("Rhapsody Dragons", 13);
        FANTASY_TEAM_MAP.put("Alcorcon Raiders", 14);
        FANTASY_TEAM_MAP.put("Vallekas Cats", 15);
        FANTASY_TEAM_MAP.put("LSI", 16);
    }

    public static List<Transaction> parseTransactionsFromHTML(String html) {
        List<Transaction> transactionsList = new ArrayList<>();

        Document document = Jsoup.parse(html);
        Element table = document.selectFirst(TRANSACTION_TABLE_CLASS);

        if (table != null) {
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");

                if (columns.size() == 7) {
                    Transaction transaction = extractTransactionData(columns);
                    transactionsList.add(transaction);
                }
            }
        }

        return transactionsList;
    }


    private static Transaction extractTransactionData(Elements columns) {
        Transaction transaction = new Transaction();
        String date = formatDate(columns.get(0).text());
        String week = columns.get(1).text();
        String type = columns.get(2).text().toLowerCase();
        String from = columns.get(4).text();
        String to = columns.get(5).text();

        String id = null;
        String attributes = columns.get(3).selectFirst(".playerCard").attributes().toString();
        String playerIdRegex = "playerNameId-(\\d+)";
        Pattern pattern = Pattern.compile(playerIdRegex);
        Matcher matcher = pattern.matcher(attributes);
        if (matcher.find()) {
            id = matcher.group(1);
        }

        transaction.setDate(date);
        transaction.setWeek(Integer.parseInt(week));
        transaction.setType(type);
        transaction.setFrom(FANTASY_TEAM_MAP.get(from));
        transaction.setTo(FANTASY_TEAM_MAP.get(to));
        transaction.setPlayer(Integer.parseInt(id));

        return transaction;
    }

    public static List<OffensivePlayerWeeklyStats> parseOffensivePlayersFromHTML(String html) {
        List<OffensivePlayerWeeklyStats> transactionsList = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Element table = document.selectFirst(PLAYER_TABLE_CLASS);

        if (table != null) {
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");

                OffensivePlayerWeeklyStats offensivePlayer = extractOffensivePlayerData(columns);
                transactionsList.add(offensivePlayer);
            }
        }

        return transactionsList;
    }

    public static List<DefensivePlayerWeeklyStats> parseDefensivePlayersFromHTML(String html) {
        List<DefensivePlayerWeeklyStats> transactionsList = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Element table = document.selectFirst(PLAYER_TABLE_CLASS);

        if (table != null) {
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");

                DefensivePlayerWeeklyStats defensivePlayer = extractDefensivePlayerData(columns);
                transactionsList.add(defensivePlayer);
            }
        }

        return transactionsList;
    }

    private static OffensivePlayerWeeklyStats extractOffensivePlayerData(Elements columns) {
        OffensivePlayerWeeklyStats player = new OffensivePlayerWeeklyStats();

        Elements playerInfo = columns.get(1).getAllElements().get(0).getAllElements();
        String positionTeam = playerInfo.get(4).text();
        String[] strings = positionTeam.split("-");
        player.setPosition(strings[0].trim());

        if (strings.length == 2) {
            player.setNflTeam(strings[1].trim());
        }

        player.setPlayerName(playerInfo.get(1).selectFirst(".playerCard").text().trim());

        // Extraer la ID del jugador del atributo "class"
        String classAttribute = playerInfo.get(0).parent().className();
        String playerIdRegex = "player-(\\d+)";
        Pattern pattern = Pattern.compile(playerIdRegex);
        Matcher matcher = pattern.matcher(classAttribute);
        if (matcher.find()) {
            String id = matcher.group(1);
            player.setId(Integer.parseInt(id));
        }
        String opponent = columns.get(2).selectFirst(".playerOpponent").text().trim().replaceAll("@", "");

        player.setOpponent(parseTeam(opponent));

        player.setCompletions(getIntValue(columns.get(4).text()));
        player.setIncompletePasses(getIntValue(columns.get(5).text()));
        player.setPassingYards(getIntValue(columns.get(6).text()));
        player.setPassingTouchdowns(getIntValue(columns.get(7).text()));
        player.setInterceptions(getIntValue(columns.get(8).text()));
        player.setTimesSacked(getIntValue(columns.get(9).text()));
        player.setRushYards(getIntValue(columns.get(10).text()));
        player.setRushTouchdowns(getIntValue(columns.get(11).text()));
        player.setReceptions(getIntValue(columns.get(12).text()));
        player.setReceivingYards(getIntValue(columns.get(13).text()));
        player.setReceivingTouchdowns(getIntValue(columns.get(14).text()));
        player.setReturnYards(getIntValue(columns.get(15).text()));
        player.setReturnTouchdowns(getIntValue(columns.get(16).text()));
        player.setFumbleReturnTouchdowns(getIntValue(columns.get(17).text()));
        player.setTwoPointConversions(getIntValue(columns.get(18).text()));
        player.setFumblesLost(getIntValue(columns.get(19).text()));
        player.setFantasyPoints(new BigDecimal(columns.get(20).text()));

        String seasonWeek = columns.get(20).getAllElements().get(1).attributes().get("class");

        String weekRegex = "Week-(\\d+)";
        Pattern weekPattern = Pattern.compile(weekRegex);
        Matcher weekMatcher = weekPattern.matcher(seasonWeek);
        if (weekMatcher.find()) {
            String week = weekMatcher.group(1);
            player.setWeek(Integer.parseInt(week));
        }

        String seasonRegex = "Season-(\\d+)";
        Pattern seasonPattern = Pattern.compile(seasonRegex);
        Matcher seasonMatcher = seasonPattern.matcher(seasonWeek);
        if (seasonMatcher.find()) {
            String season = seasonMatcher.group(1);
            player.setSeason(Integer.parseInt(season));
        }

        return player;
    }
    private static DefensivePlayerWeeklyStats extractDefensivePlayerData(Elements columns) {
        DefensivePlayerWeeklyStats player = new DefensivePlayerWeeklyStats();

        Elements playerInfo = columns.get(1).getAllElements().get(0).getAllElements();
        String positionTeam = playerInfo.get(4).text();
        String[] strings = positionTeam.split("-");
        player.setPosition(strings[0].trim());

        if (strings.length == 2) {
            player.setNflTeam(strings[1].trim());
        }

        player.setPlayerName(playerInfo.get(1).selectFirst(".playerCard").text().trim());

        // Extraer la ID del jugador del atributo "class"
        String classAttribute = playerInfo.get(0).parent().className();
        String playerIdRegex = "player-(\\d+)";
        Pattern pattern = Pattern.compile(playerIdRegex);
        Matcher matcher = pattern.matcher(classAttribute);
        if (matcher.find()) {
            String id = matcher.group(1);
            player.setId(Integer.parseInt(id));
        }
        String opponent = columns.get(2).selectFirst(".playerOpponent").text().trim().replaceAll("@", "");

        player.setOpponent(parseTeam(opponent));

        player.setTotalTackles(getIntValue(columns.get(4).text()));
        player.setAssistedTackles(getIntValue(columns.get(5).text()));
        try {
            player.setSacks(new BigDecimal(columns.get(6).text()));
        }catch (NumberFormatException e){
            player.setSacks(new BigDecimal(0));
        }
        player.setTacklesForLoss(getIntValue(columns.get(7).text()));
        try {
            player.setSackYards(new BigDecimal(columns.get(8).text()));
        }catch (NumberFormatException e){
            player.setSackYards(new BigDecimal(0));
        }
        player.setInterceptions(getIntValue(columns.get(9).text()));
        player.setForcedFumbles(getIntValue(columns.get(10).text()));
        player.setFumblesRecovered(getIntValue(columns.get(11).text()));
        player.setInterceptionsTouchdown(getIntValue(columns.get(12).text()));
        player.setFumblesTouchdown(getIntValue(columns.get(13).text()));
        player.setBlockedKickTouchdowns(getIntValue(columns.get(14).text()));
        player.setSafeties(getIntValue(columns.get(15).text()));
        player.setBlockedKicks(getIntValue(columns.get(16).text()));
        player.setPassDeflections(getIntValue(columns.get(17).text()));
        player.setQuarterbackHits(getIntValue(columns.get(18).text()));
        player.setInterceptionReturnYards(getIntValue(columns.get(19).text()));
        player.setFumbleReturnYards(getIntValue(columns.get(20).text()));
        player.setFantasyPoints(new BigDecimal(columns.get(21).text()));

        String seasonWeek = columns.get(21).getAllElements().get(1).attributes().get("class");

        String weekRegex = "Week-(\\d+)";
        Pattern weekPattern = Pattern.compile(weekRegex);
        Matcher weekMatcher = weekPattern.matcher(seasonWeek);
        if (weekMatcher.find()) {
            String week = weekMatcher.group(1);
            player.setWeek(Integer.parseInt(week));
        }

        String seasonRegex = "Season-(\\d+)";
        Pattern seasonPattern = Pattern.compile(seasonRegex);
        Matcher seasonMatcher = seasonPattern.matcher(seasonWeek);
        if (seasonMatcher.find()) {
            String season = seasonMatcher.group(1);
            player.setSeason(Integer.parseInt(season));
        }

        return player;
    }


    private static int getIntValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
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
    public static int parseTeam(String team) {
        Integer number = TEAM_MAP.get(team);
        if (number != null) {
            return number;
        } else {
            return 0;
        }
    }

    public static void writeOffensivePlayersInserts(Path source, Path destination) {
        try (Stream<String> lines = Files.lines(source)) {
            List<OffensivePlayerWeeklyStats> playerList = lines
                    .flatMap(line -> parseOffensivePlayersFromHTML(line).stream())
                    .collect(Collectors.toList());

            List<String> insertStatements = new ArrayList<>();
            playerList.forEach(p -> insertStatements.add(p.buildMySQLInsert()));

            Files.write(destination, insertStatements, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Path transactionsFile = Path.of("src/main/resources/source_code/2023/transactions/trades");
        Path destination = Path.of("src/main/resources/scripts/2023/2023_trades_insert");
        List<String> insertStatements = new ArrayList<>();
        try (Stream<String> lines = Files.lines(transactionsFile)){
            List<Transaction> transactions = lines.flatMap(line -> parseTransactionsFromHTML(line).stream()).collect(Collectors.toList());
            transactions.forEach(p -> insertStatements.add(p.buildMySQLInsert()));

            Files.write(destination, insertStatements, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void writeDefensivePlayersInserts(Path source, Path destination) {
        try (Stream<String> lines = Files.lines(source)) {
            List<DefensivePlayerWeeklyStats> playerList = lines
                    .flatMap(line -> parseDefensivePlayersFromHTML(line).stream())
                    .collect(Collectors.toList());

            List<String> insertStatements = new ArrayList<>();
            playerList.forEach(p -> insertStatements.add(p.buildMySQLInsert()));

            Files.write(destination, insertStatements, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

