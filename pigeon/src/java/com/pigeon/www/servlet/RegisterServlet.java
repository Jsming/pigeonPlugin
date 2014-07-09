package com.pigeon.www.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jivesoftware.admin.AuthCheckFilter;
import org.jivesoftware.openfire.user.DefaultUserProvider;
import org.jivesoftware.openfire.user.UserAlreadyExistsException;
import org.jivesoftware.util.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注册用户Servlet
 * 
 * @author Jsming
 * 
 */
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -3891909661288738154L;
	private static final Logger Log = LoggerFactory.getLogger(RegisterServlet.class);
	
	private static final String URI = "pigeon/userRegister";

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		AuthCheckFilter.addExclude(URI);
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName"); // 用户名
		String password = request.getParameter("password"); // 密码
		if (StringUtils.isBlank(userName) 
				|| StringUtils.isBlank(password)) {

			returnError("用户名及密码不能为空", response, out);
		}
		
		if (userName.indexOf("@") == -1) {

			returnError("用户名为邮箱格式", response, out);
		}

		String email = userName;
		String name = userName.split("@")[0];
		String username = userName.replace("@", "_");

		DefaultUserProvider provider = new DefaultUserProvider();
		try {
			provider.createUser(username, password, name, email);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			returnError("注册用户失败", response, out);
			Log.error(LocaleUtils.getLocalizedString("user.register.error"), e);
		}
		
		returnOk(response, out);
	}
	
	/**
	 * 返回成功
	 * @param response
	 * @param out
	 */
	private void returnOk(HttpServletResponse response, PrintWriter out){
        response.setContentType("text/xml");        
        out.println("{'result':'ok'}");
        out.flush();
    }

	/**
	 * 返回失败
	 * @param error
	 * @param response
	 * @param out
	 */
    private void returnError(String error, HttpServletResponse response, PrintWriter out){
        response.setContentType("text/xml");        
        out.println("{'result':'error', 'error':'"+error+"'}");
        out.flush();
    }

	@Override
	public void destroy() {
		super.destroy();
		AuthCheckFilter.removeExclude(URI);
	}
}
