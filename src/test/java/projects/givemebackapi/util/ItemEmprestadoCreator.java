package projects.givemebackapi.util;

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;

public class ItemEmprestadoCreator {
    
    public static ItemEmprestado criarItemEmprestado(){
        DonoItem donoItem = new DonoItem(null, "Fulano do teste", "887513");
        ItemEmprestado itemEmprestado = new ItemEmprestado(null, "Meia", "uma meia", donoItem);
        return itemEmprestado;
    }
    
}
