package UI;

import Domain.Student;
import Domain.Tema;
import Service.Service;
import Validator.ValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {
    private Service service;

    public UI(Service service) {
        this.service = service;
    }
    public void ConsoleUI() throws IOException, ValidationException,RuntimeException
    {
        while (true)
        {
            try {
                System.out.println("Saptamana curenta: "+service.getNrSaptamanaCurenta());
                System.out.println("1. Aduaga student");
                System.out.println("2. Actualizeaza student");
                System.out.println("3. Sterge student");
                System.out.println("4. Aduaga tema");
                System.out.println("5. Prelungire deadline");
                System.out.println("6. Adauga nota");
                System.out.println("7. Student GUI");
                System.out.println("0. Iesire");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String cmd;
                System.out.print("cmd = ");
                cmd = reader.readLine();
                if (cmd.equals("1")) {
                    String idStudent, grupa, nume, email, profesor;
                    System.out.println("idStudent: ");
                    idStudent = reader.readLine();

                    System.out.println("grupa: ");
                    grupa = reader.readLine();

                    System.out.println("nume: ");
                    nume = reader.readLine();

                    System.out.println("email: ");
                    email = reader.readLine();

                    System.out.println("profesor: ");
                    profesor = reader.readLine();

                    service.addStudent(idStudent, grupa, nume, email, profesor);
                    System.out.println("Studentul a fost adaugat");
                }
                else if (cmd.equals("2")) {
                    String idStudent, grupa, nume, email, profesor;
                    System.out.println("idStudent: ");
                    idStudent = reader.readLine();

                    System.out.println("grupa: ");
                    grupa = reader.readLine();

                    System.out.println("nume: ");
                    nume = reader.readLine();

                    System.out.println("email: ");
                    email = reader.readLine();

                    System.out.println("profesor: ");
                    profesor = reader.readLine();

                    service.updateStudent(idStudent, grupa, nume, email, profesor);
                    System.out.println("Studentul a fost actualizat");
                }
                else if (cmd.equals("3")) {
                    String idStudent, grupa, nume, email, profesor;
                    System.out.println("idStudent: ");
                    idStudent = reader.readLine();

                    System.out.println("grupa: ");
                    grupa = reader.readLine();

                    System.out.println("nume: ");
                    nume = reader.readLine();

                    System.out.println("email: ");
                    email = reader.readLine();

                    System.out.println("profesor: ");
                    profesor = reader.readLine();

                    service.deleteStudent(idStudent, grupa, nume, email, profesor);
                    System.out.println("Studentul a fost sters");
                }
                else if (cmd.equals("4")) {
                    String id, descriere;
                    int deadline, nrSaptamanaPrimire;

                    System.out.println("id: ");
                    id = reader.readLine();

                    System.out.println("descriere: ");
                    descriere = reader.readLine();

                    System.out.println("deadline: ");
                    deadline = Integer.parseInt(reader.readLine());

                    System.out.println("nrSaptamanaPrimire: ");
                    nrSaptamanaPrimire = Integer.parseInt(reader.readLine());

                    service.addTema(id, descriere, deadline, nrSaptamanaPrimire);
                    System.out.println("Tema a fost adaugata");
                }
                else if (cmd.equals("5")) {
                    String id;

                    System.out.println("id: ");
                    id = reader.readLine();
                    Boolean prelungit=service.prelungireDeadline(service.findTema(id));
                    if(prelungit)
                        System.out.println("Deadline-ul a fost prelungit");
                    else
                        System.out.println("Saptamna curenta este mai mare decat deadline-ul");
                }
                else if(cmd.equals("6"))
                {
                    System.out.println("idStudent: ");
                    String idStudent = reader.readLine();

                    System.out.println("idTema: ");
                    String idTema = reader.readLine();

                    System.out.println("valoare: ");
                    int valoare = Integer.parseInt(reader.readLine());

                    System.out.println("feedback: ");
                    String feedback = reader.readLine();

                    service.addNota(idStudent,idTema,valoare,feedback);

                }
                else if(cmd.equals("7"))
                {
                    String[] args=new String[] {" "};

                }
                else if(cmd.equals("0"))
                {
                    break;
                }
            }

            catch (ValidationException e)
            {
                System.out.println(e);
            }
            catch (RuntimeException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
