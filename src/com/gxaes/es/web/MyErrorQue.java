package com.gxaes.es.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxaes.es.common.QuestionPage;
import com.gxaes.es.common.SystemConstants;
import com.gxaes.es.entity.Student;
import com.gxaes.es.service.StudentService;
import com.gxaes.es.service.StudentServiceImpl;

/**查看错题
 * Servlet implementation class MyErrorQue
 */
@WebServlet(urlPatterns="/MyErrorQue.action")
public class MyErrorQue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService ss = new StudentServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//根据当前loginName获取学生stuId
		String loginName = (String) request.getSession().getAttribute(SystemConstants.USERTDENTITY);
		if(loginName == null ){
			//重定向
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			Integer pageNo = 1;
			Integer pageSize = 10;
			//获取分页参数
			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			Student st = ss.findByLoginName(loginName);
			//根据stuId查询错误题目信息，调用studentservice层-》在questionDao层用表连接查询
			QuestionPage errorquestion = ss.getErrorQuestion(st.getStuId(), pageNo, pageSize);

			//返回给jsp
			request.setAttribute("errorquestion", errorquestion);
			//jsp跳转
			request.getRequestDispatcher("/my_errors.jsp").forward(request, response);
		}
	}


}
