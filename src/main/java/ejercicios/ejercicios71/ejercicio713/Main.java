package ejercicios.ejercicios71.ejercicio713;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path mhtmlFolder = Path.of("D:\\Users\\Deiv\\Desktop\\Season_2023\\season2023_db");
        Path sourceCodeStat = Path.of("src/main/resources/source_code/2023/stats/2023_db_stats_source");
        Path insertFile = Path.of("src/main/resources/scripts/2023/2023_db_stats_insert");
        MHTMLLineScanner.processMHTMLs(mhtmlFolder, sourceCodeStat);

        NFLParser.writeDefensivePlayersInserts(sourceCodeStat, insertFile);

        try {
            System.out.println(Utils.repeatedLines(insertFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
