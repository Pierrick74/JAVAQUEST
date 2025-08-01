package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.Menu;

public abstract class Case implements CaseInterface {

    public Case() {
    }

    protected void show(String data){
        Menu.getInstance().showInformation(data);
    }
}
