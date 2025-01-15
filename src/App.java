import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public List<Entry> leseTSV(String path) throws IOException {
        Path filePath = Path.of(path);
        List<Entry> entries = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(filePath)) {
            String header = reader.readLine();
            String[] fields = header.split("\t");

            String line = reader.readLine();
            while(line != null) {
                String[] values = line.split("\t");

                Entry entry = new Entry(0,null,null,null,null,null);

                for(int i = 0; i < fields.length; i++) {
                    String value = values[i].replace("\"", "");
                    switch (fields[i]) {
                        case "Id": {
                            entry.setId(Integer.parseInt(value));
                            break;
                        }
                        case "Patient": {
                            entry.setPatient(value);
                            break;
                        }
                        case "Symptom": {
                            entry.setSymptom(value);
                            break;
                        }
                        case "Diagnose": {
                            entry.setDiagnose(value);
                            break;
                        }
                        case "Datum": {
                            entry.setDatum(LocalDate.parse(value));
                            break;
                        }
                        case "Krankhaus": {
                            entry.setKrankenhaus(value);
                            break;
                        }
                    }
                }

                entries.add(entry);
                line = reader.readLine();
            }
        }
        return entries;
    }

    public void grossbuchstaben(char g, List<Entry> entries) {
        Set<String> patiente = entries.stream().
                map(entry -> entry.getPatient()).
                filter(studentName -> studentName.startsWith(String.valueOf(g))).
                collect(Collectors.toSet());

        for(String patient : patiente) {
            System.out.println(patient);
        }
    }

    public void fieberPatiente(List<Entry> entries) {
        Set<Entry> patiente = entries.stream().
                filter(entry -> entry.getSymptom().equals("Fieber")).
                collect(Collectors.toSet());

        Set<Entry> sortiertePatiente = patiente.stream().sorted(Comparator.comparing(Entry::getDatum)).collect(Collectors.toSet());

        for(Entry entry : sortiertePatiente) {
            System.out.println(entry.getDatum() + ": " + entry.getPatient()+ " - Diagnose: " + entry.getDiagnose());
        }
    }

    public void schreibeDatei(String path, List<Entry> entries) throws IOException {
        Path filePath = Path.of(path);
        Map<String, Integer> krankhausFalle = new HashMap<>();

        for(Entry entry : entries) {
            krankhausFalle.put(entry.getKrankenhaus(), krankhausFalle.getOrDefault(entry.getKrankenhaus(),0) + 1);
        }

        List<Map.Entry<String, Integer>> sortiert = krankhausFalle.entrySet().
                stream().
                sorted((a,b) -> a.getValue() > b.getValue() ? -1 : 1).
                collect(Collectors.toList());

        try(FileWriter writer = new FileWriter(filePath.toFile())) {
            for(Map.Entry<String, Integer> entry: sortiert) {
                writer.write(entry.getKey() + "$" + entry.getValue() + "\n");
            }
        }
    }
}
