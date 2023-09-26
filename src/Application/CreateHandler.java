package Application;

import Domain.SchoolShooting;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateHandler {
    public int ultimoId = 391;

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    private static final String DB_FILE_PATH = "school-data.db";

    public void createRecord() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        int ultId = PegarUltimoID(DB_FILE_PATH);
        // Solicita os dados do usuário
        System.out.print("Informe o nome da escola: ");
        String schoolName = scanner.nextLine();
        System.out.print("Informe a localidade: ");
        String locality = scanner.nextLine();
        System.out.print("Informe a data do tiroteio (no formato dd/MM/yyyy): ");
        String dateStr = scanner.nextLine();
        System.out.print("Informe o ano: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Informe as armas usadas (separadas por vírgula): ");
        String weaponsStr = scanner.nextLine();

        // Parse a data
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        // Crie um objeto SchoolShooting com os dados fornecidos e o novo ID
        String[] weapons = weaponsStr.split(", ");
        SchoolShooting schoolShooting = new SchoolShooting(ultId, schoolName, locality, date, year, weaponsStr);

        // Salve o objeto no arquivo .db
        Infra.SchoolShootingDB.AdicionarRegistroAoArquivoDB(schoolShooting, DB_FILE_PATH);

        System.out.println("Registro criado com sucesso.");
    }
    public void saveToDB(SchoolShooting schoolShooting) {
        try {
            // Abra o arquivo .db para escrita em modo append
            FileOutputStream fos = new FileOutputStream(DB_FILE_PATH, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Escreva o objeto no arquivo
            oos.writeObject(schoolShooting);

            // Feche os fluxos
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int PegarUltimoID(String dbFilePath) {
        int novoUltimoId = 0;
        try (FileInputStream fileInputStream = new FileInputStream(dbFilePath);
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {

            while (dataInputStream.available() > 0) {
                int idLido = dataInputStream.readInt();
                String schoolName = dataInputStream.readUTF();
                String country = dataInputStream.readUTF();
                String dateStr = dataInputStream.readUTF();
                int year = dataInputStream.readInt();
                String[] weapons = dataInputStream.readUTF().split(",");
                if (idLido == ultimoId) {
                    novoUltimoId = idLido + 1;
                    setUltimoId(novoUltimoId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return novoUltimoId; // Retorna o novo último ID
    }


}
