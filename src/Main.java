import Application.GetByIdHandler;
import Domain.SchoolShooting;
import Infra.SchoolShootingCSV;
import Infra.SchoolShootingDB;

import java.io.*;
import java.text.ParseException;

import static Application.GetByIdHandler.BuscarPorId;

public class Main {
    private static final String CSV_FILE_PATH = "school-shootings-data.csv"; //Aponta para o caminho do csv
    private static final String DB_FILE_PATH = "school-data.db";
    public static int tamArquivo;
    public static void main(String[] args) throws IOException, ParseException {
        /*
        Calcula o tamanho do arquivo para a instanciacao do vetor e depois faz a leitura do CSV.
         */
        tamArquivo = SchoolShootingCSV.CalcularTamanhoArquivo(CSV_FILE_PATH);
        SchoolShooting[] schoolShootings = SchoolShootingCSV.LerArquivoCSV(tamArquivo, CSV_FILE_PATH);

        /*
        Cria o arquivo .db
         */
        SchoolShootingDB.CriarArquivoDB(schoolShootings, DB_FILE_PATH);

        SchoolShooting shooting = GetByIdHandler.BuscarPorId(89, DB_FILE_PATH);
        if (shooting != null) {
            System.out.println("Registro encontrado: " + shooting.toString());
        } else {
            System.out.println("Registro não encontrado.");
        }



    }



}
