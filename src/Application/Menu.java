package Application;

import Domain.SchoolShooting;

import java.io.IOException;
import java.text.ParseException;
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
                        System.out.println("Registro encontrado: " + shooting.toString());
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
                    /*
                    System.out.println("\t+++++++++ ATUALIZAR UM REGISTRO +++++++++\n");
                    System.out.println("ID: ");
                    ID = eScanner.nextInt();
                    update(ID, num_linhas);
                    */

                    break;
                }

                case 4: {
                    System.out.println("\t+++++++++ DELETAR UM REGISTRO +++++++++\n");
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

}


