import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String CSV_FILE_PATH = "school-shootings-data.csv"; //Aponta para o caminho do csv
    private static final String DB_FILE_PATH = "school-data.db";

    public static void main(String[] args) {
        // Ler os dados do CSV
        List<School> schoolData = readCSV();

        School schoolRead = getSchool(schoolData, 3);

        if (schoolRead != null) {
            System.out.println("Registro lido: " + schoolRead);
        } else {
            System.out.println("Registro não encontrado.");
        }

        createAndWriteSchoolData(schoolData, "Escola Teste", "Nova Localidade", "Nova Data", "Novo Ano");

    }
    private static void createAndWriteSchoolData(List<School> schoolData, String schoolName, String locality, String date, String year) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(DB_FILE_PATH, true);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

            int id = generateUniqueId(schoolData); // Gere um ID único
            String idString = String.valueOf(id);


            // Crie um novo objeto School com os dados fornecidos
            School newSchool = new School(id, schoolName, locality, date, year);

            // Escreva os dados no arquivo
            dataOutputStream.writeUTF(idString);
            dataOutputStream.writeUTF(newSchool.getSchoolName());
            dataOutputStream.writeUTF(newSchool.getLocality());
            dataOutputStream.writeUTF(newSchool.getDate());
            dataOutputStream.writeUTF(newSchool.getYear());

            // Adicione o novo objeto à lista schoolData
            schoolData.add(newSchool);

            System.out.println("Registro criado e gravado: " + newSchool);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int generateUniqueId(List<School> schoolData) {
        // Implemente uma lógica para gerar um ID único, por exemplo, encontre o maior ID existente e adicione 1.
        int maxId = 0;
        for (School school : schoolData) {
            if (school.getId() > maxId) {
                maxId = school.getId();
            }
        }
        return maxId + 1;
    }
    private static List<School> readCSV() {
        List<School> schoolData = new ArrayList<>();

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
                School newSchool = new School(id, schoolName, country, date, year);
                schoolData.add(newSchool);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return schoolData;
    }

    private static School getSchool(List<School> schoolData, int id) {
        for (School schoolReadRecord : schoolData) {
            if (schoolReadRecord.getId() == id) {
                return schoolReadRecord;
            }
        }
        return null;
    }
}
