package client.commands;

import data.Dragon;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Show extends AbstractCommand{
    static public LinkedList<Dragon> dragon = new LinkedList<>();

    @Override
    public synchronized String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {

        String ans = "";

        for (Map.Entry<Integer, Dragon> entry : dragonsCollection.entrySet()) {
            ans += entry.toString() + "\n";
            dragon.add(entry.getValue());
        }
        return ans;
    }

    public static LinkedList<Dragon> getDragonList() {
        return dragon;
    }
}
