package ejercicios.ejercicios71.ejercicio713;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static boolean repeatedLines(Path file) throws IOException {
        Set<String> lines = new HashSet<>();
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!lines.add(line)) {
                    System.out.println(line);
                    return true;
                }
            }
        }
        return false;
    }
}

