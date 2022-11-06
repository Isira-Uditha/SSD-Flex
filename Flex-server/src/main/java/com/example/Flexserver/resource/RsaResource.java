package com.example.Flexserver.resource;


import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.domain.response.Status;
import com.example.Flexserver.utils.GenerateKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RsaResource {

    @Autowired
    private GenerateKeys generateKeys ;

    @RequestMapping(value = "/key", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Response> key(@RequestParam(value = "appId", required = true) String appId) {

        if (null != appId && !appId.equals("")) {

            return ResponseEntity.ok(Response.builder()
                    .data(Map.of("key", generateKeys.keyGenerateAndReturnPublicKey(appId)))
                    .status(Status.SUCCESS)
                    .message("Keys created successfully")
                    .build());

        } else {

            return ResponseEntity.ok(Response.builder()
                    .data(Map.of("key", ""))
                    .status(Status.FAILED)
                    .message("Keys not generated successfully")
                    .build());
        }

    }

}
