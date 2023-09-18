import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class    Main {
    private static final String CSV_FILE_PATH = "school-shootings-data.csv"; //Aponta para o caminho do csv
    private static final String DB_FILE_PATH = "school-data.db";
    public static String DB_TEMP = "temp.db";
    public static int numero_linhas;

    public static void main(String[] args) throws IOException {
        // Ler os dados do CSV
        List<SchoolShooting> schoolShootingData = readCSV();


        SchoolShooting schoolShooting = new SchoolShooting();
        schoolShooting.ArmazenarEmArquivoDb(schoolShootingData, DB_FILE_PATH);


    }

    private static List<SchoolShooting> readCSV() {
        List<SchoolShooting> schoolShootingData = new ArrayList<>();

        try (Reader reader = new FileReader(CSV_FILE_PATH);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String idStr = csvRecord.get(0); // Obter a stringA do ID

                // Tentar converter a string do ID em um número inteiro, para trabalhar com o ID
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

                // Criar um objeto com os dados lidos
                SchoolShooting newSchoolShooting = new SchoolShooting(id, schoolName, country, date, year);
                schoolShootingData.add(newSchoolShooting);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return schoolShootingData;
    }

}
