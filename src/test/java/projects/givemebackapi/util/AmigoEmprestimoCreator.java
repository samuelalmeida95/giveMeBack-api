package projects.givemebackapi.util;

import projects.givemebackapi.model.AmigoEmprestimo;

public class AmigoEmprestimoCreator {

    public static AmigoEmprestimo criarAmigoEmprestimo() {

        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(1, "Steve", "987", "rua do steve");

        return amigoEmprestimo;
    }
}
