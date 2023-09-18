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
        /*
        SchoolShooting schoolShootingRead = getSchool(schoolShootingData, 3);

        if (schoolShootingRead != null) {
            System.out.println("Registro lido: " + schoolShootingRead);
        } else {
            System.out.println("Registro não encontrado.");
        }

         */

        SchoolShooting schoolShooting = new SchoolShooting();
        schoolShooting.ArmazenarEmArquivoDb(schoolShootingData, DB_FILE_PATH);

       // createAndWriteSchoolData(schoolShootingData, "Escola Teste", "Nova Localidade", "Nova Data", "Novo Ano");

    }
    private static void createAndWriteSchoolData(List<SchoolShooting> schoolShootingData, String schoolName, String locality, String date, String year) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(DB_FILE_PATH, true);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

            int id = generateUniqueId(schoolShootingData); // Gere um ID único
            String idString = String.valueOf(id);


            // Crie um novo objeto School com os dados fornecidos
            SchoolShooting newSchoolShooting = new SchoolShooting(id, schoolName, locality, date, year);

            // Escreva os dados no arquivo
            dataOutputStream.writeUTF(idString);
            dataOutputStream.writeUTF(newSchoolShooting.getSchoolName());
            dataOutputStream.writeUTF(newSchoolShooting.getLocality());
            dataOutputStream.writeUTF(newSchoolShooting.getDate());
            dataOutputStream.writeUTF(newSchoolShooting.getYear());

            // Adicione o novo objeto à lista schoolData
            schoolShootingData.add(newSchoolShooting);

            System.out.println("Registro criado e gravado: " + newSchoolShooting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int generateUniqueId(List<SchoolShooting> schoolShootingData) {
        // Implemente uma lógica para gerar um ID único, por exemplo, encontre o maior ID existente e adicione 1.
        int maxId = 0;
        for (SchoolShooting schoolShooting : schoolShootingData) {
            if (schoolShooting.getId() > maxId) {
                maxId = schoolShooting.getId();
            }
        }
        return maxId + 1;
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

    private static SchoolShooting getSchool(List<SchoolShooting> schoolShootingData, int id) {
        for (SchoolShooting schoolShootingReadRecord : schoolShootingData) {
            if (schoolShootingReadRecord.getId() == id) {
                return schoolShootingReadRecord;
            }
        }
        return null;
    }
}
