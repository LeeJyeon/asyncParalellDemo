package com.test.project.async.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

public class Sleep {

    @SneakyThrows
    public void sleep(String dest, String uuid) {
        if (uuid.substring(0).compareTo("1") <= 0) {
            switch (dest) {
                case "A":
                    Thread.sleep((long) (Math.random() * 1000L));
                    break;
                case "B":
                    Thread.sleep((long) (Math.random() * 5000L));
                    break;
                case "C":
                    Thread.sleep((long) (Math.random() * 3000L));
                    break;
            }
        }else if(uuid.substring(0).compareTo("3") <= 0){
            switch (dest) {
                case "A":
                    Thread.sleep((long) (Math.random() * 3000L));
                    break;
                case "B":
                    Thread.sleep((long) (Math.random() * 1000L));
                    break;
                case "C":
                    Thread.sleep((long) (Math.random() * 5000L));
                    break;
            }
        } else if (uuid.substring(0).compareTo("5") <= 0) {
            switch (dest) {
                case "A":
                    Thread.sleep((long) (Math.random() * 5000L));
                    break;
                case "B":
                    Thread.sleep((long) (Math.random() * 3000L));
                    break;
                case "C":
                    Thread.sleep((long) (Math.random() * 1000L));
                    break;
            }
        }

    }
}
