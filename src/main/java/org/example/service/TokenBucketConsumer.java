package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class TokenBucketConsumer {
    public int REQUEST_LIMIT = 100;
    private Integer key =1;

    public boolean isAllow() {
        if(key >= REQUEST_LIMIT) {
            key =1;
            return  true ;
        } else {
            key++;
        }
        return false;
    }
}
