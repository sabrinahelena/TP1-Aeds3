package Infra;

import Domain.SchoolShooting;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SchoolShootingDB {
    public static void CriarArquivoDB(SchoolShooting[] schoolShootings, String dbFilePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(dbFilePath);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

            for (SchoolShooting shooting : schoolShootings) {
                dataOutputStream.writeInt(shooting.getId());
                dataOutputStream.writeUTF(shooting.getSchoolName());
                dataOutputStream.writeUTF(shooting.getLocality());
                dataOutputStream.writeUTF(shooting.getDate());
                dataOutputStream.writeUTF(shooting.getYear());
            }

            System.out.println("Arquivo .db criado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
