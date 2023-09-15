import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String CSV_FILE_PATH = "school-shootings-data.csv";

    public static void main(String[] args) {
        // Ler os dados do CSV
        List<SchoolData> schoolData = readCSV();

        SchoolData schoolDataRead = readRecord(schoolData, 23);

        if (schoolDataRead != null) {
            System.out.println("Registro lido: " + schoolDataRead);
        } else {
            System.out.println("Registro não encontrado.");
        }
    }

    private static List<SchoolData> readCSV() {
        List<SchoolData> schoolData = new ArrayList<>();

        try (Reader reader = new FileReader(CSV_FILE_PATH);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String idStr = csvRecord.get(0); // Obter a string do ID

                // Tentar converter a string do ID em um número inteiro
                int id;
                try {
                    id = Integer.parseInt(idStr);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter ID: " + idStr);
                    continue;
                }

                String schoolName = csvRecord.get(2);
                String country = csvRecord.get(46);
                String date = csvRecord.get(5);
                String year = csvRecord.get(7);

                // Criar um objeto Record com os dados lidos
                SchoolData newSchoolData = new SchoolData(id, schoolName, country, date, year);
                schoolData.add(newSchoolData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return schoolData;
    }

    private static SchoolData readRecord(List<SchoolData> schoolData, int id) {
        for (SchoolData schoolDataReadRecord : schoolData) {
            if (schoolDataReadRecord.getId() == id) {
                return schoolDataReadRecord;
            }
        }
        return null;
    }
}
