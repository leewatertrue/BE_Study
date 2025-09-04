package com.ll.demo_01;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home2")
public class Home2Controller {
	@Autowired // 객체가 자동으로 연결됨
	private ComponentA componentA;

	@GetMapping("/action1")
	@ResponseBody
	public String action1() {
		return componentA.action();
	}
}
