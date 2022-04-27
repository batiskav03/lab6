package client.commands;

import Server.DatabaseManager;
import data.Dragon;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static client.ClientAuthorization.ClientLogin;

public class RemoveLowerKey extends AbstractCommand{
    private String owner;
    private Integer Key;
    public void setKey(Integer key) {
        Key = key;
    }

    public RemoveLowerKey() {
        this.owner = ClientLogin();
    }
    @Override
    public synchronized String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {

        Iterator<Map.Entry<Integer,Dragon>> i = dragonsCollection.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<Integer, Dragon> entry = i.next();
            if(Key > entry.getKey() || !entry.getValue().getOwner().equals(getOwnerClient())) {
                DatabaseManager.removeDragonByKey(entry.getKey());
                i.remove();
            }
        }
        return "Операция завершена";
    }
    public String getOwnerClient() {
        return owner;
    }
}
