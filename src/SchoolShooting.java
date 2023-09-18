import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

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


    public static SchoolShooting fromByteArray(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        SchoolShooting schoolShooting = (SchoolShooting) objectInputStream.readObject();
        objectInputStream.close();
        return schoolShooting;
    }

    /*
     * Funções conversoes
     */
    public static long CalcularTamanhoArquivoCSV(String src) throws FileNotFoundException {
        Path arq = Paths.get(src);
        long i = 0;

        try {
            i = Files.lines(arq).count();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return i;
    }


    /*
     * Recebe uma lista do tipo SchoolShooting e armazena em um arquivo
     */
    public void ArmazenarEmArquivoDb(List<SchoolShooting> schoolShootingList, String dbFilePath) throws IOException {
        RandomAccessFile arq = new RandomAccessFile(dbFilePath, "rw");
        int j = 0;

        for (SchoolShooting schoolShooting : schoolShootingList) {
            // Verifica se o objeto existe dentro da lista passada como parâmetro
            if (schoolShooting != null) {
                // Se existe, serializa e salva no arquivo .db
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

                objectOutputStream.writeObject(schoolShooting);
                objectOutputStream.close();

                byte[] arrayBytes = byteArrayOutputStream.toByteArray();
                arq.writeInt(arrayBytes.length);
                arq.write(arrayBytes);
                j++;
            }
        }

        System.out.println("NUM REGISTROS ARMAZENADOS: " + j);
        arq.close();
    }

    // Exibe dados no db
    public static void ExibirDadosDb(String dbFilePath, int num_reg) throws IOException, ParseException {
        SchoolShooting schoolShooting = new SchoolShooting();
        int tam;
        byte[] arrayBytes;

        RandomAccessFile arq = new RandomAccessFile(dbFilePath, "rw");
        long filePointer = arq.readLong();

        for (int i = 0; i < num_reg; i++) {
            try {
                filePointer = arq.getFilePointer();
                tam = arq.readInt();
                arrayBytes = new byte[tam];
                arq.read(arrayBytes);
                // Agora, você precisa desserializar o objeto
                schoolShooting = SchoolShooting.fromByteArray(arrayBytes);
                System.out.println(schoolShooting);

            } catch (NullPointerException | ClassNotFoundException e) {
                System.out.println("NullPointerException thrown!");
            }
        }

        arq.seek(0);
        arq.writeLong(filePointer);
        arq.close();
    }



}
