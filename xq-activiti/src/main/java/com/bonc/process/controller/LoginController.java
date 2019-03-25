package com.bonc.process.controller;

import com.bonc.process.dao.model.ProcessUser;
import com.bonc.process.service.IUserService;
import com.bonc.process.util.MsgReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="login")
	@ResponseBody
	public Map<String, Object>login(HttpServletRequest request) {
		try {
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			ProcessUser user =new ProcessUser();
			user.setUserId(username);
			user.setPassword(password);
			boolean success = userService.userLogind(user);
			if (success) {
				request.getSession().setAttribute("processUser", user);
				return MsgReturn.mapOk("登陆成功");
			}
		} catch (Exception e) {
			return MsgReturn.mapOk("系统异常，请联系管理员");
		}
		return MsgReturn.mapError("用户名或密码错误");
	}
	
}
