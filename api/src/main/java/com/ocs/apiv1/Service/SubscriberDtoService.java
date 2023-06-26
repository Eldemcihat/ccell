package com.ocs.apiv1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocs.apiv1.Models.SubscriberDto;
import com.ocs.apiv1.Repository.SubscriberDtoRepository;

@Service
public class SubscriberDtoService {

    @Autowired
    private SubscriberDtoRepository repo;

    public List<SubscriberDto> listAll() {
        return repo.findAll();
    }

    public SubscriberDto save(SubscriberDto subscriber) {
        return repo.save(subscriber);
    }

    public SubscriberDto get(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public SubscriberDto login(String msisdn, String password) {
        return repo.findByMsisdnAndPassword(msisdn, password);
    }

    public SubscriberDto register(SubscriberDto subscriber) {

        SubscriberDto existingSubscriberByEmail = repo.findByEmail(subscriber.getEmail());
        if (existingSubscriberByEmail != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        SubscriberDto existingSubscriberByMsisdn = repo.findByMsisdn(subscriber.getMsisdn());
        if (existingSubscriberByMsisdn != null) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        return repo.save(subscriber);
    }
}
