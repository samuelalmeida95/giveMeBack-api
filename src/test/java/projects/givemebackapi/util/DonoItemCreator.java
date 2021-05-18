package projects.givemebackapi.util;

import projects.givemebackapi.model.DonoItem;

public class DonoItemCreator {

    public static DonoItem criarDonoItem() {
        DonoItem donoItem = new DonoItem(2, "Fulano do teste", "887513");
        return donoItem;
    }

}
