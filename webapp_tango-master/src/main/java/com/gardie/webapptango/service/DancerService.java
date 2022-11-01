package com.gardie.webapptango.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardie.webapptango.model.Dancer;
import com.gardie.webapptango.repository.DancerProxy;
import lombok.Data;

@Data
@Service
public class DancerService {

    @Autowired
    private DancerProxy dancerProxy;

    public Dancer getDancer(final int id) {
        return dancerProxy.getDancer(id);
    }

    public Iterable<Dancer> getDancers() {
        return dancerProxy.getDancers();
    }

    public void deleteDancer(final int id) {
    	dancerProxy.deleteDancer(id);;
    }

     public Dancer saveDancer(Dancer dancer) {
    	 Dancer savedDancer;

        // Règle de gestion : Le nom du danseur doit être mis en majuscule.
    	 dancer.setLastname(dancer.getLastname().toUpperCase());

        if(dancer.getId() == null) {
            // Si l'id est nul, alors c'est un nouveau danseur.
        	savedDancer = dancerProxy.createDancer(dancer);
        } else {
        	savedDancer = dancerProxy.updateDancer(dancer);
        }
        
        return savedDancer;
    }

}
