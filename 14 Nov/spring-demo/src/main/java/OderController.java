


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
public class OderController {
	@GetMapping("/order")
	String getOrder() {
		return "hello";
	}

}
