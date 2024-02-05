package ejercicios.ejercicios71.ejercicio713;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

    public static void main(String[] args) {
        String html = "<div class=\"tableWrap\" id=\"yui_3_15_0_1_1707095130301_237\"><table cellpadding=\"0\" cellspacing=\"0\" class=\"tableType-transaction noGroups\" id=\"yui_3_15_0_1_1707095130301_236\"><thead id=\"yui_3_15_0_1_1707095130301_305\"><tr class=\"last\" id=\"yui_3_15_0_1_1707095130301_304\"><th class=\"transactionDate first\" scope=\"col\" id=\"yui_3_15_0_1_1707095130301_307\"><span>Date</span></th><th class=\"transactionWeek\" scope=\"col\" id=\"yui_3_15_0_1_1707095130301_303\"><span id=\"yui_3_15_0_1_1707095130301_306\">Week</span></th><th class=\"transactionType\" scope=\"col\"><span>Type</span></th><th class=\"playerNameAndInfo\" scope=\"col\"><span>Player(s)</span></th><th class=\"transactionFrom\" scope=\"col\"><span>From</span></th><th class=\"transactionTo\" scope=\"col\"><span>To</span></th><th class=\"transactionOwner last\" scope=\"col\"><span>By</span></th></tr></thead><tbody id=\"yui_3_15_0_1_1707095130301_235\"><tr class=\"transaction-add-178-2 odd first\" id=\"yui_3_15_0_1_1707095130301_301\"><td class=\"transactionDate first\" id=\"yui_3_15_0_1_1707095130301_308\">Sep 12, 12:01am</td><td class=\"transactionWeek\">2</td><td class=\"transactionType\" id=\"yui_3_15_0_1_1707095130301_302\">Add</td><td class=\"playerNameAndInfo\" id=\"yui_3_15_0_1_1707095130301_300\"><div class=\"c c-was\" id=\"yui_3_15_0_1_1707095130301_299\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2543767\" class=\"playerCard playerName playerNameFull playerNameId-2543767 what-playerCard\">Logan Thomas</a> <em id=\"yui_3_15_0_1_1707095130301_298\">TE - WAS</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/2\" class=\"teamName teamId-2\">Catacronk NFL</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-161023\">Daniel</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-177-2 even\" id=\"yui_3_15_0_1_1707095130301_296\"><td class=\"transactionDate first\">Sep 12, 12:00am</td><td class=\"transactionWeek\">2</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\" id=\"yui_3_15_0_1_1707095130301_297\"><div class=\"c c-min\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2558167\" class=\"playerCard playerName playerNameFull playerNameId-2558167 what-playerCard\">Joshua Dobbs</a> <em>QB - MIN</em>    </div></td><td class=\"transactionFrom\" id=\"yui_3_15_0_1_1707095130301_295\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/2\" class=\"teamName teamId-2\">Catacronk NFL</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-161023\">Daniel</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-176-2 odd\" id=\"yui_3_15_0_1_1707095130301_293\"><td class=\"transactionDate first\">Sep 11, 9:23pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-lac\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2565054\" class=\"playerCard playerName playerNameFull playerNameId-2565054 what-playerCard\">Joshua Kelley</a> <em>RB - LAC</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_292\"><a href=\"/league/54538/team/6\" class=\"teamName teamId-6\" id=\"yui_3_15_0_1_1707095130301_294\">The Stampede</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-5537341\">Carlos</span></div></td></tr><tr class=\"transaction-add-175-2 even\" id=\"yui_3_15_0_1_1707095130301_289\"><td class=\"transactionDate first\">Sep 10, 11:11pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-ne\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2570122\" class=\"playerCard playerName playerNameFull playerNameId-2570122 what-playerCard\">Christian Gonzalez</a> <em>DB - NE</em> <strong class=\"status status-ir\" title=\"Injured Reserve\">IR</strong>   </div></td><td class=\"transactionFrom\" id=\"yui_3_15_0_1_1707095130301_288\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_291\"><a href=\"/league/54538/team/14\" class=\"teamName teamId-14\" id=\"yui_3_15_0_1_1707095130301_290\">Alcorcon Raiders</a></td><td class=\"transactionOwner last\" id=\"yui_3_15_0_1_1707095130301_315\"><div class=\"teamOwner\" id=\"yui_3_15_0_1_1707095130301_314\"><span class=\"userName userId-7822031\" id=\"yui_3_15_0_1_1707095130301_313\">Victor</span></div></td></tr><tr class=\"transaction-add-174-2 odd\" id=\"yui_3_15_0_1_1707095130301_285\"><td class=\"transactionDate first\">Sep 10, 10:46pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\" id=\"yui_3_15_0_1_1707095130301_284\"><div class=\"c c-pit\" id=\"yui_3_15_0_1_1707095130301_286\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2568132\" class=\"playerCard playerName playerNameFull playerNameId-2568132 what-playerCard\">Calvin Austin III</a> <em>WR - PIT</em>    </div></td><td class=\"transactionFrom\" id=\"yui_3_15_0_1_1707095130301_287\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/6\" class=\"teamName teamId-6\">The Stampede</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-5537341\">Carlos</span></div></td></tr><tr class=\"transaction-add-173-2 even\" id=\"yui_3_15_0_1_1707095130301_310\"><td class=\"transactionDate first\">Sep 10, 3:10pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\" id=\"yui_3_15_0_1_1707095130301_309\">Add</td><td class=\"playerNameAndInfo\" id=\"yui_3_15_0_1_1707095130301_311\"><div class=\"c c-la\" id=\"yui_3_15_0_1_1707095130301_312\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2567862\" class=\"playerCard playerName playerNameFull playerNameId-2567862 what-playerCard\">Kyren Williams</a> <em>RB - LA</em> <strong class=\"status status-ia\" title=\"Inactive\">IA</strong>   </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/1\" class=\"teamName teamId-1\">Villalba Rams</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-160832\">Alberto</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-172-2 odd\" id=\"yui_3_15_0_1_1707095130301_243\"><td class=\"transactionDate first\">Sep 10, 2:08pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-bal\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2562407\" class=\"playerCard playerName playerNameFull playerNameId-2562407 what-playerCard\">Justice Hill</a> <em>RB - BAL</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_242\"><a href=\"/league/54538/team/14\" class=\"teamName teamId-14\">Alcorcon Raiders</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-7822031\">Victor</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-171-2 even\" id=\"yui_3_15_0_1_1707095130301_234\"><td class=\"transactionDate first\">Sep 10, 1:59pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-la\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2570026\" class=\"playerCard playerName playerNameFull playerNameId-2570026 what-playerCard\">Puka Nacua</a> <em>WR - LA</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_241\"><a href=\"/league/54538/team/10\" class=\"teamName teamId-10\">Vir Saints</a></td><td class=\"transactionOwner last\" id=\"yui_3_15_0_1_1707095130301_233\"><div class=\"teamOwner\" id=\"yui_3_15_0_1_1707095130301_232\"><span class=\"userName userId-12315091\" id=\"yui_3_15_0_1_1707095130301_240\">Virginia</span></div></td></tr><tr class=\"transaction-add-168-2 odd\"><td class=\"transactionDate first\">Sep 10, 12:48pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-car\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2560747\" class=\"playerCard playerName playerNameFull playerNameId-2560747 what-playerCard\">Hayden Hurst</a> <em>TE - CAR</em> <strong class=\"status status-ir\" title=\"Injured Reserve\">IR</strong>   </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/3\" class=\"teamName teamId-3\">Templars Colonia Garden</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-27936451\">Miguel Angel</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-157-2 even\"><td class=\"transactionDate first\">Sep 9, 9:09pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-bal\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2567283\" class=\"playerCard playerName playerNameFull playerNameId-2567283 what-playerCard\">Isaiah Likely</a> <em>TE - BAL</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/8\" class=\"teamName teamId-8\">Atletico de David</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-18176177\">David</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-152-2 odd\"><td class=\"transactionDate first\">Sep 9, 2:21am</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-det\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2540158\" class=\"playerCard playerName playerNameFull playerNameId-2540158 what-playerCard\">Zach Ertz</a> <em>TE - DET</em> <strong class=\"status status-q\" title=\"Questionable\">Q</strong>   </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/4\" class=\"teamName teamId-4\">Allen or Nothing</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-21825468\">Mark</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-146-2 even\"><td class=\"transactionDate first\">Sep 8, 3:53am</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-min\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2570077\" class=\"playerCard playerName playerNameFull playerNameId-2570077 what-playerCard\">DeWayne McBride</a> <em>RB - MIN</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/3\" class=\"teamName teamId-3\">Templars Colonia Garden</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-27936451\">Miguel Angel</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-144-2 odd\" id=\"yui_3_15_0_1_1707095130301_283\"><td class=\"transactionDate first\">Sep 8, 3:47am</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\" id=\"yui_3_15_0_1_1707095130301_282\"><div class=\"c c-was\" id=\"yui_3_15_0_1_1707095130301_281\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2570371\" class=\"playerCard playerName playerNameFull playerNameId-2570371 what-playerCard\">Emmanuel Forbes</a> <em>DB - WAS</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/3\" class=\"teamName teamId-3\">Templars Colonia Garden</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-27936451\">Miguel Angel</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-131-2 even\" id=\"yui_3_15_0_1_1707095130301_264\"><td class=\"transactionDate first\">Sep 7, 12:10pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-det\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2558194\" class=\"playerCard playerName playerNameFull playerNameId-2558194 what-playerCard\">Josh Reynolds</a> <em>WR - DET</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_263\"><a href=\"/league/54538/team/11\" class=\"teamName teamId-11\">MexicanVikings</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-29735198\">Chalo</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-130-2 odd\" id=\"yui_3_15_0_1_1707095130301_266\"><td class=\"transactionDate first\">Sep 7, 12:09pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-atl\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2564358\" class=\"playerCard playerName playerNameFull playerNameId-2564358 what-playerCard\">Van Jefferson</a> <em>WR - ATL</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_265\"><a href=\"/league/54538/team/11\" class=\"teamName teamId-11\">MexicanVikings</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-29735198\">Chalo</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-107-2 even\" id=\"yui_3_15_0_1_1707095130301_268\"><td class=\"transactionDate first\">Sep 6, 9:49am</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-chi\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2560713\" class=\"playerCard playerName playerNameFull playerNameId-2560713 what-playerCard\">Tremaine Edmunds</a> <em>LB - CHI</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_267\"><a href=\"/league/54538/team/13\" class=\"teamName teamId-13\">Rhapsody Dragons</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-1398847\">Juan Enrique</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-106-2 odd\"><td class=\"transactionDate first\">Sep 6, 9:47am</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-bal\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2561324\" class=\"playerCard playerName playerNameFull playerNameId-2561324 what-playerCard\">Gus Edwards</a> <em>RB - BAL</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\"><a href=\"/league/54538/team/13\" class=\"teamName teamId-13\">Rhapsody Dragons</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-1398847\">Juan Enrique</span> <a href=\"https://www.nfl.com/mobile/fantasy\" title=\"via Mobile Device\" target=\"_blank\" class=\"mobileImg\">via Mobile Device</a></div></td></tr><tr class=\"transaction-add-98-2 even\" id=\"yui_3_15_0_1_1707095130301_271\"><td class=\"transactionDate first\">Sep 6, 3:06am</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-atl\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2567256\" class=\"playerCard playerName playerNameFull playerNameId-2567256 what-playerCard\">Desmond Ridder</a> <em>QB - ATL</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_270\"><a href=\"/league/54538/team/9\" class=\"teamName teamId-9\" id=\"yui_3_15_0_1_1707095130301_269\">Santa Eugenia Reapers</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-10065550\">David</span></div></td></tr><tr class=\"transaction-add-83-2 odd\" id=\"yui_3_15_0_1_1707095130301_277\"><td class=\"transactionDate first\">Sep 4, 3:03pm</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\"><div class=\"c c-dal\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2567508\" class=\"playerCard playerName playerNameFull playerNameId-2567508 what-playerCard\">Jalen Tolbert</a> <em>WR - DAL</em>    </div></td><td class=\"transactionFrom\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_276\"><a href=\"/league/54538/team/10\" class=\"teamName teamId-10\">Vir Saints</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-12315091\">Virginia</span></div></td></tr><tr class=\"transaction-add-81-2 even last\" id=\"yui_3_15_0_1_1707095130301_274\"><td class=\"transactionDate first\">Sep 4, 2:40am</td><td class=\"transactionWeek\">1</td><td class=\"transactionType\">Add</td><td class=\"playerNameAndInfo\" id=\"yui_3_15_0_1_1707095130301_280\"><div class=\"c c-det\" id=\"yui_3_15_0_1_1707095130301_279\"><b></b><a onclick=\"return false\" href=\"/players/card?leagueId=54538&amp;playerId=2569729\" class=\"playerCard playerName playerNameFull playerNameId-2569729 what-playerCard\">Brian Branch</a> <em>DB - DET</em>    </div></td><td class=\"transactionFrom\" id=\"yui_3_15_0_1_1707095130301_278\">Free Agents</td><td class=\"transactionTo\" id=\"yui_3_15_0_1_1707095130301_273\"><a href=\"/league/54538/team/1\" class=\"teamName teamId-1\" id=\"yui_3_15_0_1_1707095130301_272\">Villalba Rams</a></td><td class=\"transactionOwner last\"><div class=\"teamOwner\"><span class=\"userName userId-160832\">Alberto</span></div></td></tr></tbody></table></div>";
        List<String[]> list = parseHTMLTable(html);
        list.forEach(array -> Arrays.stream(array).forEach(System.out::println));

    }
}

