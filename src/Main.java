import Application.GetByIdHandler;
import Domain.SchoolShooting;
import Infra.SchoolShootingCSV;
import Infra.SchoolShootingDB;

import java.io.*;
import java.text.ParseException;
import Application.Menu;

import static Application.GetByIdHandler.BuscarPorId;

public class Main {
    private static final String CSV_FILE_PATH = "school-shootings-data.csv"; //Aponta para o caminho do csv
    private static final String DB_FILE_PATH = "school-data.db";
    public static int tamArquivo;
    public static void main(String[] args) throws IOException, ParseException {
        /*
        Calcula o tamanho do arquivo para a instanciacao do vetor e depois faz a leitura do CSV.
         */
        //tamArquivo = SchoolShootingCSV.CalcularTamanhoArquivo(CSV_FILE_PATH);
        //SchoolShooting[] schoolShootings = SchoolShootingCSV.LerArquivoCSV(tamArquivo, CSV_FILE_PATH);

        /*
        Cria o arquivo .db
         */
        //SchoolShootingDB.CriarArquivoDB(schoolShootings, DB_FILE_PATH);


        //Chama o menu de CRUD
        Menu.CRUD(DB_FILE_PATH);

    }






}
