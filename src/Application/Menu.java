package Application;

import Domain.SchoolShooting;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import Domain.SchoolShooting;
import Application.CreateHandler;



public class Menu {
    public static void CRUD(String dbFilePath) throws ParseException, IOException {

        Scanner eScanner = new Scanner(System.in);
        int ESCOLHA = 0;


        do {
            System.out.println("\n+++++++++ CRUD +++++++++\n");
            System.out.println("    OPÇÕES: " +
                    "\n\t1    -   LER UM REGISTRO POR ID" +
                    "\n\t2    -   ADICIONAR UM REGISTRO" +
                    "\n\t3    -   ATUALIZAR UM REGISTRO" +
                    "\n\t4    -   DELETAR UM REGISTRO" +
                    "\n\t0    -   SAIR\n");

            ESCOLHA = eScanner.nextInt();

            switch (ESCOLHA) {
                case 1: {
                    System.out.println("\t+++++++++ LER UM REGISTRO POR ID +++++++++\n");
                    System.out.print("\t\nDigite um ID: ");
                    int id = eScanner.nextInt();
                    SchoolShooting shooting = GetByIdHandler.BuscarPorId(id, dbFilePath);
                    if (shooting != null) {
                        System.out.println("Registro encontrado: " + Imprimir(shooting.getDate(), shooting.getId(),shooting.getWeapons(), shooting.getYear(), shooting.getLocality(), shooting.getSchoolName(), shooting.getExists() ));
                    } else {
                        System.out.println("Registro não encontrado.");
                    }

                    break;
                }



                case 2: {
                    System.out.println("\t+++++++++ ADICIONAR UM REGISTRO +++++++++\n");

                    // Chame o método main da classe Create para adicionar um registro
                    CreateHandler createHandler = new CreateHandler();
                    createHandler.createRecord();

                    break;
                }

                case 3: {

                    System.out.println("\t+++++++++ ATUALIZAR UM REGISTRO +++++++++\n");
                    System.out.println("ID: ");
                    int ID = eScanner.nextInt();
                    UpdateHandler.AtualizarRegistro(ID, dbFilePath);


                    break;
                }

                case 4: {
                    System.out.println("\t+++++++++ DELETAR UM REGISTRO +++++++++\n");
                    System.out.println("ID: ");
                    int ID = eScanner.nextInt();
                    DeleteHandler.DeletarRegistro(ID, dbFilePath);


                    break;
                }

                case 0: {
                    System.out.println("\n+++++++++ ADEUS! +++++++++");
                    break;
                }

                default:
                    System.out.println("\n+++++++++ OPÇÃO INVÁLIDA +++++++++\n");
                    break;
            }
        } while (ESCOLHA != 0);
    }

    public static String Imprimir(Date date, int id, String[] weapons, int year, String locality, String schoolName, Boolean exists) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = dateFormat.format(date);


        StringBuilder weaponsStr = new StringBuilder();
        for (String weapon : weapons) {
            weaponsStr.append(weapon).append(", ");
        }

        if (weaponsStr.length() > 0) {
            weaponsStr.setLength(weaponsStr.length() - 2);  // isso e pra remover a última vírgula e espaço em branco

        }

        return "Record{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", locality='" + locality + '\'' +
                ", year='" + year + '\'' +
                ", date='" + formattedDate + '\'' +
                ", weapons='" + weaponsStr.toString() + '\'' +
                ", exists='" + exists + '\'' +

                '}';

    }

}


