import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
import java.text.ParseException;

public class    Main {
    private static final String CSV_FILE_PATH = "school-shootings-data.csv"; //Aponta para o caminho do csv
    private static final String DB_FILE_PATH = "school-data.db";
    public static String DB_TEMP = "temp.db";
    public static int tamArquivo;

    public static void main(String[] args) throws IOException, ParseException {
        // Ler os dados do CSV


        SchoolShooting schoolShooting = new SchoolShooting();
        tamArquivo = schoolShooting.CalcularTamanhoArquivo(CSV_FILE_PATH);
        SchoolShooting[] schoolShootings = LerArquivoCSV(tamArquivo);
        schoolShooting.CriarArquivoDB(schoolShootings, DB_FILE_PATH);



    }

    private static SchoolShooting[] LerArquivoCSV(int tamArquivo) {
        SchoolShooting[] schoolShootingData = new SchoolShooting[tamArquivo];
        int index = 0; // index do vetor

        try (Reader reader = new FileReader(CSV_FILE_PATH);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String idStr = csvRecord.get(0); //pega o ID

                // Converte de string pra int o ID (precisa converter os outros para seus tipos correspodentes TODO sabrina)
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

                // Criar um objeto com os dados lidos e adicioná-lo ao vetor
                SchoolShooting newSchoolShooting = new SchoolShooting(id, schoolName, country, date, year);

                // Verifica se da pra colcoar dentro do vetor
                if (index < tamArquivo) {
                    schoolShootingData[index] = newSchoolShooting;
                    index++;
                } else {
                    System.err.println("O vetor está cheio. Não é possível adicionar mais elementos.");
                    break; // Sair do loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return schoolShootingData;
    }


}
