package HC.Banco_Talentos.Enum;

public enum Senioridade {
    JUNIOR("Júnior"),
    PLENO("Pleno"),
    SENIOR("Sênior"),
    ESPECIALISTA("Especialista"),
    TECH_LEADER("Tech Leader");

    private final String descricao;

    Senioridade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
