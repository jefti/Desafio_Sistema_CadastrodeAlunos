package Models;

public class Aluno {
    private String nome;
    private String matricula;
    private double[] notas = new double[3];

    public Aluno(String nome, String matricula, double[] notas) {
        this.matricula = matricula;
        this.nome = nome;
        this.notas = notas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    public void setNota(double nota, int indice){
        if(indice < 0 || indice > 2){
            System.out.println("O sistema só aceita 3 notas por aluno.");
            return;
        }
        this.notas[indice] = nota;
    }

    public String imprimeNotas() {
        String response = "";
        Boolean first = true;

        for(double nota: notas){
            if(!first){
                response += ", ";
            }

            response += nota;
            first = false;
        }

        return response;
    }

    public double calcularMedia(){
        double sum = 0;
        for(double nota: this.notas){
            sum+= nota;
        }

        return sum/3;
    }

    public String getSituacao(){
        double media = this.calcularMedia();

        if(media < 5.0){
            return "Reprovado";
        }else if(media < 7){
            return "Recuperação";
        }

        return "Aprovado";
    }

    public void exibirRelatorio(){
        System.out.println("Aluno: "+ this.nome);
        System.out.println("Matricula: "+ this.matricula);

        for(int i = 1; i <= this.notas.length; i++){
            System.out.println("nota " + i + ": " + notas[i-1]);
        }

        System.out.println("Media: "+ this.calcularMedia());
        System.out.println("Situação: "+ this.getSituacao());

    }
}
