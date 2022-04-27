package client.commands;

import Server.DatabaseManager;
import data.Dragon;

import java.util.LinkedHashMap;

public class Clear extends AbstractCommand{



    @Override
    public String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {
        DatabaseManager.clearDatabase();
        dragonsCollection.clear();
        return "Очистка успешно проведена!";
    }
}
