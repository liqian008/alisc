package com.shan.yellowpages.service.impl;

import com.shan.yellowpages.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 */
@Service
public class UserServiceImpl implements IUserService {
	@Override public String test(String name) {
		return "test";
	}
}
