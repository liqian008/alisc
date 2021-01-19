package com.shan.yellowpages.controller.temp;

import com.shan.yellowpages.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author bruce
 */
@Slf4j
@RequestMapping
@Controller
public class JwtTestController implements InitializingBean {

	@Autowired private IUserService userService;

//	@Autowired
//	private RedisTemplate redisTemplate;


	@GetMapping("/createToken") public @ResponseBody String jwt(int userId) {
		String result = JwtUtil.sign("username", "userId");
		return result;
	}

	@GetMapping("/getToken") public @ResponseBody String jwt(HttpServletRequest req, String token) {

		HttpSession session = req.getSession();
		//		String result =
		JwtUtil.verity(token);
		return "token";
	}

	private String secretKey = "123456";

//	private String createToken(int userId){
//		String result = JWT.create().withAudience(String.valueOf(userId)).sign(Algorithm.HMAC256(secretKey));
//		return result;
//	}
//
//	private String getToken(String token){
//		String result = JWT.decode(token).getAudience().get(0);
//		return result;
//	}




//	private String createToken2(int userId){
//		String result = JWT.create().withClaim("", "").sign(Algorithm.HMAC256(secretKey));
//		return result;
//	}



	@Override public void afterPropertiesSet() throws Exception {
//		Assert.notNull(redisTemplate, "redisTemplate can't be null");
		Assert.notNull(userService, "userService can't be null");

	}
}
