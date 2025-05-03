package Controller;

import Models.Aluno;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedWriter;


public class MenuController {

    public static ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
    public static Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        System.out.println("Bem-vindo ao sistema de cadastro de alunos!");

        // Opção de iniciar código com mock para agilizar testes
        //initMocks();

        try {
            while (true) {
                showMenuOptions();
                System.out.print("Escolha um item do menu: ");
                String menuSelection = scanner.next();

                if (menuSelection.equals("0")) {
                    System.out.println("\nFim da execução!");
                    return;
                }

                switch (menuSelection) {
                    case "1":
                        registerStudent();
                        break;
                    case "2":
                        listStudents();
                        break;
                    case "3":
                        searchStudent();
                        break;
                    case "4":
                        setStudentGrade();
                        break;
                    case "5":
                        rankingGrades();
                        break;
                    case "6":
                        classroomAverage();
                        break;
                    case "7":
                        saveReport();
                        break;
                    default:
                        invalidOptionMessage();
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("\nOcorreu algum erro inesperado no sistema.");
            System.out.println("Encerrando execução.");
        }
    }

    private static Aluno findAluno(String matricula){
        for(Aluno aluno: listaAlunos){
            if(aluno.getMatricula().equals(matricula)){
                return aluno;
            }
        }

        return null;
    }

    private static boolean isIdRepeated(String matricula){
        Aluno aluno = findAluno(matricula);
        if(aluno == null){
            System.out.println("\n A matricula informada já está cadastrada, informa uma matrícula válida \n");
            return false;
        }

        return true;
    }

    private static boolean isGradeValid(String grade){
        if(grade.isEmpty()){
            System.out.println("\nO valor informado é inválido para uma nota.");
            return false;
        }

        try {
            double value = Double.parseDouble(grade);
            return value >= 0.0 && value <= 10.0;
        } catch (NumberFormatException e) {
            System.out.println("\nO valor informado é inválido para uma nota.");
            return false;
        }
    }

    private static void showMenuOptions() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Cadastrar novo aluno");
        System.out.println("2. Listar alunos");
        System.out.println("3. Buscar aluno por matrícula");
        System.out.println("4. Atualizar nota de um aluno");
        System.out.println("5. Ordernar alunos por média");
        System.out.println("6. Exibir média da turma");
        System.out.println("7. Salvar relatório (em .txt)");
        System.out.println("0. Encerrar sistema\n");
    }

    private static void invalidOptionMessage() {
        System.out.println("\nOpção inválida!\n");
    }

    private static void registerStudent() {
        String novaMatricula;
        String novoNome;
        double[] notas = {0.0, 0.0, 0.0};

        System.out.print("\nQual o nome do novo aluno: ");
        novoNome = scanner.next();

        do{
            System.out.print("\nDigite a matrícula do novo aluno: ");
            novaMatricula = scanner.next();
        }while(isIdRepeated(novaMatricula));


        for(int i = 0; i < 3; i++){
            String grade = "";
            do{
                System.out.print("Informe a nota "+ i + " do aluno: ");
                grade = scanner.next();
            }while(!isGradeValid(grade));

            notas[i] = Double.parseDouble(grade);
            System.out.print("Nota cadastrada com sucesso!!!\n");
        }

        Aluno novoAluno = new Aluno(novoNome,novaMatricula,notas);
        listaAlunos.add(novoAluno);
        System.out.print("\nNovo aluno cadastrado com sucesso!!!\n");
    }

    private static void listStudents(){
        System.out.println("\nListando os alunos cadastrados no sistema: \n");
        int i = 1;

        System.out.println("Lista de alunos (" + listaAlunos.size() + "):" );
        for(Aluno student: listaAlunos  ){
            System.out.println(i + " - " + student.getNome() + " " + student.getMatricula());
            i++;
        }
        System.out.print("\nDigita algo para voltar para o menu ...");
        scanner.next();
    }

    private static void searchStudent(){
        do{
            System.out.print("\nInforme uma matrícula para buscar um aluno: ");
            String matricula = scanner.next();
            System.out.println("\nProcurando ...");

            Aluno aluno = findAluno(matricula);
            if(aluno == null){
                System.out.println("Aluno não encontrado para a matricula informada.");

                System.out.print("\nDeseja retornar ao menu (s/n): ");
                String response = scanner.next();
                if("s".equalsIgnoreCase(response)){
                    break;
                }

            } else{
                System.out.println("\n Aluno encontrado: \n");
                aluno.exibirRelatorio();

                System.out.print("\nDeseja retornar ao menu (s/n): ");
                String response = scanner.next();
                if("s".equalsIgnoreCase(response)){
                    break;
                }
            }
        }while(true);
    }

    private static void setStudentGrade(){
        Aluno aluno;
        do{
            System.out.print("\nInforme a matrícula do aluno: ");
            String matricula = scanner.next();
            System.out.println("\nProcurando ...\n");
            aluno = findAluno(matricula);

            if(aluno ==  null){
                System.out.println("Aluno não encontrado para a matricula informada.");

                System.out.print("\nDeseja retornar ao menu (s/n): ");
                String response = scanner.next();
                if("s".equalsIgnoreCase(response)){
                    return;
                }
            } else {
                System.out.println("Aluno encontrado\n");
                aluno.exibirRelatorio();

                System.out.print("\nContinuar para atualizar as notas (s/n): ");
                String response = scanner.next();

                if("s".equalsIgnoreCase(response)){
                    break;
                }

                System.out.print("\nDeseja retornar ao menu (s/n): ");
                String response2 = scanner.next();
                if("s".equalsIgnoreCase(response2)){
                    return;
                }

            }
        }while(true);

        System.out.println("\nEscolha uma nota para atualizar:\n");
        for(int i = 1; i <= aluno.getNotas().length; i++){
            System.out.println( i + " - " + aluno.getNotas()[i-1] );
        }
        System.out.println("Outros valores - Cancelar e retornar ao menu");

        System.out.print("\n Qual nota você deseja atualizar: ");

        String gradeIndex = scanner.next();

        if (!gradeIndex.equals("1") && !gradeIndex.equals("2") && !gradeIndex.equals("3")) {
            System.out.print("\n Procedimento cancelado, retornando ao menu... \n");
            return;
        }

        String grade;

        do{
            System.out.print("Informe a nova nota "+ gradeIndex + " do aluno: ");
            grade = scanner.next();
        }while(!isGradeValid(grade));


        int gradeIndexInt = Integer.parseInt(gradeIndex) - 1;
        double newGrade = Double.parseDouble(grade);
        aluno.setNota(newGrade, gradeIndexInt);

        System.out.print("\n Nota atualizada com Sucesso ...\n");

        aluno.exibirRelatorio();
        System.out.println("Digita algo para voltar para o menu ...");
        scanner.next();
    }

    private static void rankingGrades(){
        if(listaAlunos.isEmpty()){
            System.out.println("Lista de alunos vázia, cadastre mais alunos!");
            return;
        }

        ArrayList<Aluno> listaOrganizada = new ArrayList<>(listaAlunos);

        listaOrganizada.sort(new Comparator<Aluno>() {
            public int compare(Aluno a1, Aluno a2) {
                return Double.compare(a2.calcularMedia(), a1.calcularMedia());
            }
        });
        System.out.println("\nLista de alunos: ");
        int i = 1;
        for(Aluno aluno: listaOrganizada){
            System.out.println(i + ". " + aluno.getNome()+ " (" + aluno.getMatricula() + ")" + ", Média: " +  aluno.calcularMedia());
            i++;
        }

        System.out.println("\nDigite algo para voltar para o menu ...");
        scanner.next();
    }

    private static void classroomAverage(){
        double sum = 0.0;
        for(Aluno aluno: listaAlunos){
            sum += aluno.calcularMedia();
        }
        double average = sum/listaAlunos.size();

        System.out.println("\n A média da turma é: " + average+".");

        System.out.print("\n Exibir o ranking de alunos por média (s/n)? ");
        String response = scanner.next();

        if("s".equalsIgnoreCase(response)){
            rankingGrades();
        }

    }

    private static void saveReport() {
        System.out.println("\nO relatório será salvo na pasta de documentos do seu sistema atual.");

        scanner.nextLine();
        System.out.print("Escreva o nome que deseja salvar o arquivo: ");
        String nomeArquivo = scanner.nextLine();

        String documentos = System.getProperty("user.home") + File.separator + "Documents";
        File pastaDocumentos = new File(documentos);
        if (!pastaDocumentos.exists()) {
            pastaDocumentos.mkdirs();
        }

        String caminhoCompleto = documentos + File.separator + nomeArquivo + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto))) {
            writer.write("Relatório de notas:");
            writer.newLine();

            for (Aluno aluno : listaAlunos) {
                double[] notas = aluno.getNotas();

                writer.newLine();
                writer.write("Aluno: " + aluno.getNome() + ", Matrícula: " + aluno.getMatricula());
                writer.newLine();
                writer.write("Média: " + aluno.calcularMedia());
                writer.newLine();
                writer.write("Situação: " + aluno.getSituacao());
                writer.newLine();

                writer.write("Notas: ");
                for (int j = 0; j < notas.length; j++) {
                    writer.write("Nota 0" + (j + 1) + ": " + notas[j]);
                    if (j < notas.length - 1) {
                        writer.write(", ");
                    }
                }
                writer.newLine();
            }


        } catch (IOException e) {
            System.err.println("Erro ao salvar o relatório: " + e.getMessage());
        }

        System.out.println("\nArquivo criado com sucesso em: " + caminhoCompleto);
        System.out.print("Digite qualquer coisa para retornar ao menu...");
        scanner.nextLine();
    }

    private static void initMocks(){
        double[] notas1 = {9.0, 9.0, 9.0}, notas2 = {6.0, 7.0, 8.0}, notas3 = {10.0, 10.0, 10.0};
        Aluno novoAluno = new Aluno("pedro", "123", notas1);
        Aluno novoAluno2 = new Aluno("maria", "124", notas2);
        Aluno novoAluno3 = new Aluno("carlos", "212", notas3);
        listaAlunos.add(novoAluno);
        listaAlunos.add(novoAluno2);
        listaAlunos.add(novoAluno3);
    }
}
