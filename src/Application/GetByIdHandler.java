package Application;

import Domain.SchoolShooting;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class GetByIdHandler {

    public static SchoolShooting BuscarPorId(int id, String dbFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(dbFilePath);
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {

            while (dataInputStream.available() > 0) {
                int idLido = dataInputStream.readInt();
                String schoolName = dataInputStream.readUTF();
                String country = dataInputStream.readUTF();
                String date = dataInputStream.readUTF();
                int year = dataInputStream.readInt();

                if (idLido == id) {
                    return new SchoolShooting(id, schoolName, country, date, year);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se o registro com o ID não for encontrado
    }
}
