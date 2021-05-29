package projects.givemebackapi.util;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;

public class ItemEmprestadoCreator {

    public static ItemEmprestado criarItemEmprestado() {
        DonoItem donoItem = new DonoItem(null, "Fulano do teste", "887513");

        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(null, "Fulano do teste", "887513474",
                "rua das palmeiras", donoItem);

        ItemEmprestado itemEmprestado = new ItemEmprestado(null, "Meia", "uma meia", donoItem, amigoEmprestimo);

        return itemEmprestado;
    }

}
