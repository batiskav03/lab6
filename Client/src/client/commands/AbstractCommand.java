package client.commands;

import data.Dragon;

import java.io.Serializable;
import java.util.LinkedHashMap;

abstract public class AbstractCommand implements Serializable {
    private String owner;

    public String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {
        return "";
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerClient() {
        return owner;
    }
}
