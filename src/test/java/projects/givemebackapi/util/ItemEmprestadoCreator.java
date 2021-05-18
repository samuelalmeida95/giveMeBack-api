package projects.givemebackapi.util;

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;

public class ItemEmprestadoCreator {
    
    public static ItemEmprestado criarItemEmprestado(){
        DonoItem donoItem = new DonoItem(2, "Fulano do teste", "887513");
        ItemEmprestado itemEmprestado = new ItemEmprestado(1, "Meia", "uma meia", donoItem);
        return itemEmprestado;
    }
    
}
