package com.DFS.MasterNode.Services;

import com.DFS.MasterNode.Models.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientDetailsService {

    static HashMap<String , Client> map;

    public  ClientDetailsService(){
        map = new HashMap<>();
    }

    public boolean isExistingClient(String client_id) {
        return map.containsKey(client_id);
    }

    public void AddClient(Client client){
        if(isExistingClient(client.id)) return;
        map.put(client.id, client);
    }

    public boolean isValidPassword(Client client){
        return isExistingClient(client.id) &&
                map.get(client.id).password.equals((client.password));
    }

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        for(Map.Entry<String, Client> entry : map.entrySet()){
            clients.add(entry.getValue());
        }
        return  clients;
    }

    public void updateClientLiveStatus(String id, boolean isAlive){
        map.get(id).isAlive = isAlive;
    }

}
