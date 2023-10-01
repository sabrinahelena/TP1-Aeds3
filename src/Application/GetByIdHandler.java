package Application;

import Domain.SchoolShooting;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetByIdHandler {

    public static SchoolShooting BuscarPorId(int id, String dbFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(dbFilePath);
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
            //Parse de data
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            /*
            Aqui lê os dados dos arquivos
             */
            while (dataInputStream.available() > 0) {
                int idLido = dataInputStream.readInt();
                String schoolName = dataInputStream.readUTF();
                String country = dataInputStream.readUTF();
                String dateStr = dataInputStream.readUTF();
                int year = dataInputStream.readInt();
                String[] weapons = dataInputStream.readUTF().split(",");
                Boolean exists = dataInputStream.readBoolean();

                /*
                Se o id lido, for igual ao id passado pelo usuário, retornamos o objeto.
                 */
                if (idLido == id) {
                    Date date;
                    try {
                        date = dateFormat.parse(dateStr);
                        return new SchoolShooting(id, schoolName, country, date, year, weapons, exists);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se o registro com o ID não for encontrado
    }




}
