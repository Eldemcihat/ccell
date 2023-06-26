package com.ocs.apiv1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ocs.apiv1.Models.SubscriberDto;
import com.ocs.apiv1.Service.SubscriberDtoService;

@RestController
@RequestMapping("/subscriber-dto")
public class SubscriberDtoController {

    @Autowired
    private SubscriberDtoService subscriberDtoService;

    @GetMapping("")
    public List<SubscriberDto> list() {
        return subscriberDtoService.listAll();
    }

    @PostMapping("")
    public ResponseEntity<SubscriberDto> create(@RequestBody SubscriberDto subscriberDto) {
        SubscriberDto newSubscriberDto = subscriberDtoService.save(subscriberDto);
        return new ResponseEntity<SubscriberDto>(newSubscriberDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriberDto> get(@PathVariable Integer id) {
        SubscriberDto subscriberDto = subscriberDtoService.get(id);
        if (subscriberDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(subscriberDto, HttpStatus.OK);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<SubscriberDto> login(@RequestParam("msisdn") String msisdn,
            @RequestParam("password") String password) {
        SubscriberDto subscriberDto = subscriberDtoService.login(msisdn, password);
        if (subscriberDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(subscriberDto, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<SubscriberDto> register(@RequestBody SubscriberDto subscriberDto) {
        SubscriberDto newSubscriberDto = subscriberDtoService.save(subscriberDto);
        return new ResponseEntity<SubscriberDto>(newSubscriberDto, HttpStatus.CREATED);
    }
}
