package Infra;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Domain.SchoolShooting;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Scanner;

public class SchoolShootingCSV {

    public static SchoolShooting[] LerArquivoCSV(int tamArquivo, String csvFilePath) {
        SchoolShooting[] schoolShootingData = new SchoolShooting[tamArquivo];
        int index = 0; // index do vetor

        try (Reader reader = new FileReader(csvFilePath);
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
                String dateStr = csvRecord.get(5);

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                Date date;
                try {
                    date = dateFormat.parse(dateStr);
                } catch (ParseException e) {
                    System.err.println("Erro ao converter data: " + dateStr);
                    continue;
                }

                String yearStr = csvRecord.get(7);
                // Converte de string pra int o ID (precisa converter os outros para seus tipos correspodentes TODO sabrina)
                int year;
                try {
                    year = Integer.parseInt(yearStr);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter year: " + yearStr);
                    continue;
                }

                String weapon = csvRecord.get(38);

                // Cria um objeto com os dados lidos
                SchoolShooting newSchoolShooting = new SchoolShooting(id, schoolName, country, date, year, weapon);

                // Verifica se da pra colcoar dentro do vetor
                if (index < tamArquivo) {
                    schoolShootingData[index] = newSchoolShooting;
                    index++;
                } else {
                    System.err.println("O vetor está cheio. Não é possível adicionar mais elementos.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return schoolShootingData;
    }
    public static int CalcularTamanhoArquivo(String caminhoArquivo) throws ParseException {
        File arquivo = new File(caminhoArquivo);
        int i = 0;
        if (arquivo.exists()) {
            Scanner in;

            try {
                in = new Scanner(arquivo);
                String s = in.nextLine();
                while (in.hasNextLine()) {
                    s = in.nextLine();
                    i++;
                }
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("erro ao abrir o arquivo");
        }
        return i;
    }
}
