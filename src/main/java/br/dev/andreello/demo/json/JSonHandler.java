package br.dev.andreello.demo.json;

import br.dev.andreello.demo.DemoApplication;
import org.springframework.boot.SpringApplication;

import java.util.HashMap;

public class JSonHandler {
    HashMap<String, Object> json = new HashMap<>();

    public void addValue(String key, Object value) {
        json.put(key, value);
    }

    public HashMap<String, Object> getJSon() {
        return json;
    }
}
