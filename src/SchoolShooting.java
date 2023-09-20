import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchoolShooting implements Serializable {

    private int id; // ID único para cada registro
    private String schoolName;
    private String locality;
    private String date;
    private String year;


    public SchoolShooting(int id, String schoolName, String locality, String date, String year) {
        this.id = id;
        this.schoolName = schoolName;
        this.locality = locality;
        this.date = date;
        this.year = year;
    }

    public SchoolShooting() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", locality='" + locality + '\'' +
                ", year='" + year + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    /*
     * Funções conversoes
     */
    public int CalcularTamanhoArquivo(String caminhoArquivo) throws ParseException {
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

    public void CriarArquivoDB(SchoolShooting[] schoolShootings, String dbFilePath) {
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

