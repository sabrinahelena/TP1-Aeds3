package Infra;

import Domain.SchoolShooting;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


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

    public static void AtualizarRegistroNoArquivoDB(SchoolShooting updatedShooting, String dbFilePath, int id) {
        try (FileInputStream fileInputStream = new FileInputStream(dbFilePath);
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            List<SchoolShooting> shootings = new ArrayList<>();

            while (dataInputStream.available() > 0) {
                int currentId = dataInputStream.readInt();
                String schoolName = dataInputStream.readUTF();
                String locality = dataInputStream.readUTF();
                String dateStr = dataInputStream.readUTF();
                int year = dataInputStream.readInt();
                String weaponComplete = dataInputStream.readUTF();

                SchoolShooting shooting = new SchoolShooting(currentId, schoolName, locality, dateFormat.parse(dateStr), year, weaponComplete);
                shootings.add(shooting);
            }

            // Encontrar e atualizar o registro com o ID especificado
            boolean updated = false;
            for (int i = 0; i < shootings.size(); i++) {
                if (shootings.get(i).getId() == id) {
                    shootings.set(i, updatedShooting);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                System.out.println("Registro com ID " + id + " não encontrado.");
                return;
            }

            // Escrever todos os registros atualizados de volta no arquivo .db
            try (FileOutputStream fileOutputStream = new FileOutputStream(dbFilePath);
                 DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

                for (SchoolShooting shooting : shootings) {
                    dataOutputStream.writeInt(shooting.getId());
                    dataOutputStream.writeUTF(shooting.getSchoolName());
                    dataOutputStream.writeUTF(shooting.getLocality());
                    String dateStr = dateFormat.format(shooting.getDate());
                    dataOutputStream.writeUTF(dateStr);
                    dataOutputStream.writeInt(shooting.getYear());
                    dataOutputStream.writeUTF(shooting.getWeaponComplete());
                }

                System.out.println("Registro com ID " + id + " atualizado no arquivo .db com sucesso.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException | java.text.ParseException e) {
            e.printStackTrace();
        }
    }


}
