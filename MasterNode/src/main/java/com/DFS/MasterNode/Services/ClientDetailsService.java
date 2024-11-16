package com.DFS.MasterNode.Services;

import com.DFS.MasterNode.Models.Client;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ClientDetailsService {

    static HashMap<String , Client> map;

    public  ClientDetailsService(){
        map = new HashMap<>();
    }

    public boolean isExistingClient(String client_id) {
        return map.containsKey(client_id);
    }

    public boolean AddClient(Client client){
        if(isExistingClient(client.id)) return false;
        map.put(client.id, client);
        return true;
    }

    public boolean isValidPassword(Client client){
        return isExistingClient(client.id) &&
                map.get(client.id).password.equals((client.password));
    }
}
