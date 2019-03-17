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

/**
 * Servlet implementation class LoadMyFavorite
 */
@WebServlet(urlPatterns="/LoadMyFavorite.action")
public class LoadMyFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService ss = new StudentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取用户名
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
            //获取用户信息
            Student st = ss.findByLoginName(loginName);
            //获取用户收藏的错题
            QuestionPage myfavorite = ss.getFavorite(st.getStuId(), pageNo, pageSize);
            //发送给浏览器
            request.setAttribute("myfavorite", myfavorite);
            //转到我的收藏
            request.getRequestDispatcher("/my_favorite.jsp").forward(request, response);
        }
	}
}
