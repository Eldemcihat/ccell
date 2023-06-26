package com.ocs.apiv1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocs.apiv1.Models.Subscriber;
import com.ocs.apiv1.Repository.SubscriberRepository;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository repo;

    public List<Subscriber> listAll() {
        return repo.findAll();
    }

    public Subscriber save(Subscriber subscriber) {
        return repo.save(subscriber);
    }

    public Subscriber get(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public Subscriber getByMsisdn(String msisdn) {

        List<Subscriber> subscriberList = repo.findAll();

        for (Subscriber subscriber : subscriberList) {
            if (subscriber.getMsisdn().equals(msisdn)) {
                return subscriber;
            }
        }

        return null;
    }

}
