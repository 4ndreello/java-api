package br.dev.andreello.demo;

import br.dev.andreello.demo.modules.Login;
import br.dev.andreello.demo.modules.Test;
import br.dev.andreello.demo.skeletons.Module;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

@SpringBootApplication
@RestController
public class DemoApplication {
	static HashMap<String, Class<?>> map = new HashMap<>();
	static {
		map.put("login", Login.class);
		map.put("test", Test.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/api/v1/{module}")
	public @ResponseBody String api(@PathVariable String module) throws InstantiationException, IllegalAccessException {
		if (!map.containsKey(module)) {
			return "This reference doesn't exist";
		}

		Module Reference = null;
		try {
			Constructor<?> constructor = map.get(module).getDeclaredConstructor();
			Reference = (Module) constructor.newInstance();
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
				 InvocationTargetException e) {
			e.fillInStackTrace();
		}

		if (Reference == null) {
			return "Internal Server Error";
		}

		return Reference.execute();
	}

//	@PostMapping(path = "/v1/api/")
//	public HashMap<String, Object> api(@RequestParam(value = "id", required = false) String id) {
//
//	}
}
