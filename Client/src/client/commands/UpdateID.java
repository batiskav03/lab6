package client.commands;

import Server.DatabaseManager;
import data.Dragon;

import java.util.LinkedHashMap;
import java.util.Map;

import static client.ClientAuthorization.ClientLogin;

public class UpdateID extends AbstractCommand {
    private String owner;
    private Integer id;
    private Dragon a;

    public UpdateID() {
        this.owner = ClientLogin();
    }

    @Override
    public String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {

        boolean isT = false;
        boolean isYour = false;
        for (Map.Entry<Integer, Dragon> entry : dragonsCollection.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                isT = true;
                if (entry.getValue().getOwner().equals(getOwnerClient())) {
                    System.out.println("Update element:");
                    boolean statusDB = DatabaseManager.addDragonToDatabase(entry.getKey(),a);
                    isYour = true;
                    if (statusDB) {
                        dragonsCollection.put(id, a);
                    } else {
                        return "Произошла ошибка в базе данных, попробуйте позже.";
                    }

                }
            }
        }

        if (isT && isYour) {
            return "Элемент успешно обновлен";
        } else if (isT && !isYour) {
            return "Данный обьект принадлежит другому пользователю";
        } else {
            return "Такого элемента не существует";
        }

    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDragon(Dragon a) {
        this.a = a;
    }
    public String getOwnerClient() {
        return owner;
    }
}

