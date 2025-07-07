package com.doan.MyWechat.com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErorrController {
	@GetMapping(value="/erorr")
	public String returnErorrPageNormal() {
		return "erorrpage";
	}
	
	@GetMapping(value="/erorrforbidden")
	public String returnErorrPageForbidden() {
		return "erorrpage";
	}
	
	@GetMapping(value="/block")
	public String blockUserLogin() {
		return "blockpage";
	}
}
