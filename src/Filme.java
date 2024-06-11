public class Filme {
    private String titulo;
    private String posterImagem;
    private float nota;
    private int ano;
    
    public Filme(String titulo, String posterImagem, float nota, int ano) {
        this.titulo = titulo;
        this.posterImagem = posterImagem;
        this.nota = nota;
        this.ano = ano;
    }


    // metodos especiais
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPosterImagem() {
        return posterImagem;
    }

    public void setPosterImagem(String posterImagem) {
        this.posterImagem = posterImagem;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
