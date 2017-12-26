package fr.lokm.model.utils;

import java.util.HashMap;
import java.util.Map;

import fr.lokm.servlets.FrontServlet;

public final class ActionManager  { //final pour que personne puisse la modifier et personne la prenne en heritage

    

    private ActionManager() {} // pour la protger et quon puisse pas linstancier

    

    private static Map <String, Action> actions;// recoit le nom de laction string et cree un action

    

    static {

        actions = new HashMap<String, Action>();

        actions.put(FrontServlet.ACTION_ADD, new AddBook() );

        actions.put(FrontServlet.ACTION_DELETE, new DeleteBook() );     // on met dans la map tout les actions et leurs cle (la string)

        actions.put(FrontServlet.ACTION_EDIT, new EditBook() );            // des quon entre la cle on lance un new action en ffonction de la cle

        actions.put(FrontServlet.ACTION_LIST_BOOKS, new ListBooks() );

    }

    public static Action getAction(String actionName) {   // c la que en fonction de laction (string) envoyer par la frontservlet on fait une action

        return actions.get(actionName);                // get permet de recuperer l'acion de la map grace a la cle

    }

}
