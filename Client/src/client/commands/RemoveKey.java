package client.commands;

import data.Dragon;

import java.util.LinkedHashMap;
import java.util.Map;

import static client.ClientAuthorization.ClientLogin;

public class RemoveKey extends AbstractCommand{
    private String owner;
    private Integer Key;
    public void setKey(Integer key) {
        Key = key;
    }

    public RemoveKey() {
        this.owner = ClientLogin();
    }


    @Override
    public String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {

        boolean isT = false;
        boolean isYour = false;
        for (Map.Entry<Integer, Dragon> entry : dragonsCollection.entrySet()) {
            if (entry.getKey().equals(Key)) {
                isT = true;
                if (entry.getValue().getOwner().equals(getOwnerClient())) {
                    System.out.println("Remove element:");
                    dragonsCollection.remove(Key);
                    isYour = true;
                }
            }

        }
        if (isT && isYour) {
            return "Элемент успешно удален";
        } else if (isT && !isYour) {
            return "Данный обьект принадлежит другому пользователю";
        } else {
            return "Такого элемента не существует";
        }
    }
    public String getOwnerClient() {
        return owner;
    }
}
