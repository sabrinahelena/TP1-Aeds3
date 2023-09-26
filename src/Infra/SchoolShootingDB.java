package Infra;

import Domain.SchoolShooting;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class SchoolShootingDB {
    public static void CriarArquivoDB(SchoolShooting[] schoolShootings, String dbFilePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(dbFilePath);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");


            for (SchoolShooting shooting : schoolShootings) {
                dataOutputStream.writeInt(shooting.getId());
                dataOutputStream.writeUTF(shooting.getSchoolName());
                dataOutputStream.writeUTF(shooting.getLocality());
                String dateStr = dateFormat.format(shooting.getDate());
                dataOutputStream.writeUTF(dateStr);
                dataOutputStream.writeInt(shooting.getYear());
                dataOutputStream.writeUTF(shooting.getWeaponComplete());
            }

            System.out.println("Arquivo .db criado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void AdicionarRegistroAoArquivoDB(SchoolShooting newShooting, String dbFilePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(dbFilePath, true);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            dataOutputStream.writeInt(newShooting.getId());
            dataOutputStream.writeUTF(newShooting.getSchoolName());
            dataOutputStream.writeUTF(newShooting.getLocality());
            String dateStr = dateFormat.format(newShooting.getDate());
            dataOutputStream.writeUTF(dateStr);
            dataOutputStream.writeInt(newShooting.getYear());
            dataOutputStream.writeUTF(newShooting.getWeaponComplete());

            System.out.println("Registro adicionado ao arquivo .db com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
