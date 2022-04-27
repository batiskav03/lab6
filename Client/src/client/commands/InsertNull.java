package client.commands;

import Server.DatabaseManager;
import data.Dragon;

import java.util.LinkedHashMap;

public class InsertNull extends AbstractCommand{
    public void setDragon(Dragon a) {
        this.a = a;
    }

    Dragon a;

    public void setKey(Integer key) {
        this.key = key;
    }

    Integer key;
    @Override
    public synchronized String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {
        boolean statusDB = DatabaseManager.addDragonToDatabase(key,a);
        if (statusDB) {
            dragonsCollection.put(key, a);
        } else {
            return "Произошла ошибка в базе данных, попробуйте позже.";
        }
        return "Объект успешно добавлен";
    }

}
