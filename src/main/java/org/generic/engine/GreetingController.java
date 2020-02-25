package src.main.java.org.generic.engine.genericEngine;
import java.util.concurrent.atomic.AtomicLong;
import java.lang.ClassLoader;
import java.lang.Class;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController 
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Value("${service.class.names}")
	private String serviceClassNames;
	
	ClassLoader classLoader = GreetingController.class.getClassLoader();
	
	@GetMapping("/service")
	public Greeting greeting(@RequestParam(required=false, defaultValue="World") String name,@RequestParam(required=false, defaultValue="World") String serviceName) {
		System.out.println("==== in greeting ====");	
		try { Class aClass = classLoader.loadClass(serviceClassNames);
		aClass.newInstance();}
		catch(Exception e) { e.printStackTrace();}
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}