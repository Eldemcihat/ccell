package com.ocs.apiv1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocs.apiv1.Models.SubscriberDto;

public interface SubscriberDtoRepository extends JpaRepository<SubscriberDto, Object> {

    SubscriberDto findByMsisdnAndPassword(String msisdn, String password);

    SubscriberDto findByEmail(String email);

    SubscriberDto findByMsisdn(String msisdn);

}
