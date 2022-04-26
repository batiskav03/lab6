package client.commands;

import data.Dragon;

import java.util.LinkedHashMap;
import java.util.Map;

import static client.ClientAuthorization.ClientLogin;

public class UpdateID extends AbstractCommand {

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDragon(Dragon a) {
        this.a = a;
    }

    private Dragon a;

    @Override
    public String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {
        for (Map.Entry<Integer, Dragon> entry : dragonsCollection.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                if (entry.getValue().getOwner().equals(ClientLogin())){
                    System.out.println("Update element:");
                    dragonsCollection.put(id,a);
                } else {
                    return "У вас нет доступа к данному элементу!";
                }

            } else {
                return "Элемент не найден";
            }

        }
        return "Элемент успешно обновлен";
    }
}