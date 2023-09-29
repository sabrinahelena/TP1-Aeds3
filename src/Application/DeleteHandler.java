package Application;

import Domain.SchoolShooting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DeleteHandler {
    public static Boolean DeletarRegistro(int id, String dbFilePath) throws ParseException {
        SchoolShooting schoolShooting = GetByIdHandler.BuscarPorId(id, dbFilePath);
        Scanner scanner = new Scanner(System.in);


        SchoolShooting excludeSchoolShooting = new SchoolShooting(id, schoolShooting.getSchoolName(), schoolShooting.getLocality(),
                schoolShooting.getDate(), schoolShooting.getYear(), schoolShooting.getWeaponComplete(), false);


        assert schoolShooting != null;

        schoolShooting.setExists(excludeSchoolShooting.getExists());

        Infra.SchoolShootingDB.DeletarRegistroNoArquivoDB(excludeSchoolShooting, dbFilePath, id);

        System.out.println("Registro deletado com sucesso.");
        return true;
    }
}
