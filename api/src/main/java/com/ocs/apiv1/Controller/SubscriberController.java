package com.ocs.apiv1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ocs.apiv1.Models.Balance;
import com.ocs.apiv1.Models.Pack;
import com.ocs.apiv1.Models.Subscriber;
import com.ocs.apiv1.Service.SubscriberService;

import io.swagger.annotations.*;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        Subscriber existingSubscriber = service.get(id);
        if (existingSubscriber == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{msisdn}/balance")
    @ApiOperation(value = "Get balance by MSISDN")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Balance.class),
            @ApiResponse(code = 404, message = "Not Found", response = Void.class)
    })
    public ResponseEntity<Balance> getBalanceByMsisdn(@PathVariable String msisdn) {
        Subscriber subscriber = service.getByMsisdn(msisdn);
        if (subscriber == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Balance> balanceList = subscriber.getBalances();
            if (balanceList == null || balanceList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Balance balance = balanceList.get(0); // Assuming you want to return the first balance object
                return ResponseEntity.ok().body(balance);
            }
        }
    }

    @GetMapping("/{id}/pack")
    @ApiOperation(value = "Get pack by subscriber id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Pack.class),
            @ApiResponse(code = 404, message = "Not Found", response = Void.class)
    })
    public ResponseEntity<Pack> getPack(@PathVariable Integer id) {
        Subscriber subscriber = service.get(id);
        if (subscriber == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Pack pack = subscriber.getPack();
            if (pack == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok().body(pack);
            }
        }
    }

}
