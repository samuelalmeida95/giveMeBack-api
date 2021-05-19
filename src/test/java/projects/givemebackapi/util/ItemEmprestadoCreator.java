package projects.givemebackapi.util;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;

public class ItemEmprestadoCreator {
    
    public static ItemEmprestado criarItemEmprestado(){
        DonoItem donoItem = new DonoItem(null, "Fulano do teste", "887513");
        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(null, "Fulano do teste", "887513","887513","887513","887513");
        ItemEmprestado itemEmprestado = new ItemEmprestado(null, "Meia", "uma meia", TipoStatus.EMPRESTADO, donoItem, amigoEmprestimo);
        return itemEmprestado;
    }
    
}
