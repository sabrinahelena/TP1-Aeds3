package Application;

import Domain.SchoolShooting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DeleteHandler {
    public static Boolean DeletarRegistro(int id, String dbFilePath) throws ParseException {
        /*
        Faz um get para saber qual vai ser o objeto que será deletado.
         */
        SchoolShooting schoolShooting = GetByIdHandler.BuscarPorId(id, dbFilePath);
        Scanner scanner = new Scanner(System.in);


        /*
        Criamos um excludeSchoolShooting para armazenar os dados daquele que será deletado. Já setamos o exists como false.
         */
        SchoolShooting excludeSchoolShooting = new SchoolShooting(id, schoolShooting.getSchoolName(), schoolShooting.getLocality(),
                schoolShooting.getDate(), schoolShooting.getYear(), schoolShooting.getWeaponComplete(), false);


        assert schoolShooting != null;

        //Coloca o false no schoolShooting antigo
        schoolShooting.setExists(excludeSchoolShooting.getExists());

        //Chama o método para deletar
        Infra.SchoolShootingDB.DeletarRegistroNoArquivoDB(excludeSchoolShooting, dbFilePath, id);

        System.out.println("Registro deletado com sucesso.");
        return true;
    }
}
