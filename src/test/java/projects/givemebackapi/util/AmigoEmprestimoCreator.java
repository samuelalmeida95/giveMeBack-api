package projects.givemebackapi.util;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.DonoItem;

public class AmigoEmprestimoCreator {

    public static AmigoEmprestimo criarAmigoEmprestimo() {

        DonoItem donoItem = new DonoItem(null, "Fulano do teste", "887513");
        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(1, "Steve", "987", "rua do steve", donoItem);

        return amigoEmprestimo;
    }
}
