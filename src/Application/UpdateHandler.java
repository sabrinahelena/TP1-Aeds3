package Application;
import Domain.SchoolShooting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UpdateHandler {
    public static Boolean AtualizarRegistro(int id, String dbFilePath) throws ParseException {
        SchoolShooting schoolShooting = GetByIdHandler.BuscarPorId(id, dbFilePath);
        Scanner scanner = new Scanner(System.in);

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

        // Cria um objeto SchoolShooting com os dados fornecidos e o novo ID
        String[] weapons = weaponsStr.split(", ");
        SchoolShooting newSchoolShooting = new SchoolShooting(id, schoolName, locality, date, year, weaponsStr, true);


        /*
        Pega todas as informações do novo school shooting e passa para o "antigo"
         */
        assert schoolShooting != null;
        newSchoolShooting.setId(id);
        schoolShooting.setDate(newSchoolShooting.getDate());
        schoolShooting.setSchoolName(newSchoolShooting.getSchoolName());
        schoolShooting.setWeaponComplete(newSchoolShooting.getWeaponComplete());
        schoolShooting.setLocality(newSchoolShooting.getLocality());
        schoolShooting.setYear(newSchoolShooting.getYear());

        //Chama a função para atualizar o registro
        Infra.SchoolShootingDB.AtualizarRegistroNoArquivoDB(newSchoolShooting, dbFilePath, id);

        System.out.println("Registro atualizado com sucesso.");
        return true;
    }
}
