import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
