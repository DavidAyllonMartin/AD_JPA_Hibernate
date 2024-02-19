package ejercicios.ejercicios71.ejercicio713;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MHTMLLineScanner {
    private static final String HTML_TABLE_REGEX = "<table[^>]*>(.*?)</table>";
    private static final String MHTML_REPLACE = "=3D";
    private static final String HTML_TABLE_WRAPPER = "<table class=\"tableType-player hasGroups\">$1</table>";

    public static void main(String[] args) {
        Path carpetaContenedora = Path.of("D:\\Users\\Deiv\\Desktop\\season2023_dl");
        Path archivoDestino = Path.of("src/main/resources/scripts/dl_season2023");
        processMHTMLs(carpetaContenedora, archivoDestino);
    }

    public static void processMHTML(Path file, Path destinationFile) {
        try {
            List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            StringBuilder content = new StringBuilder();

            for (String line : lines) {
                if (line.endsWith("=")) {
                    content.append(line, 0, line.length() - 1);
                } else {
                    content.append(line);
                }
            }

            Pattern pattern = Pattern.compile(HTML_TABLE_REGEX);
            Matcher matcher = pattern.matcher(content.toString());
            if (matcher.find()) {
                String html = matcher.group(1).replaceAll(MHTML_REPLACE, "=");
                html = HTML_TABLE_WRAPPER.replace("$1", html);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile.toFile(), true))) {
                    writer.write(html);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo " + file + ": " + e.getMessage());
        }
    }

    public static void processMHTMLs(Path MHTMLFolder, Path destinationFile) {
        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(MHTMLFolder)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    files.add(file);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al acceder a la carpeta: " + e.getMessage());
            return;
        }

        files.forEach(file -> {
            processMHTML(file, destinationFile);
            System.out.println("Archivo procesado con éxito: " + file.toString());
        });
    }
}
