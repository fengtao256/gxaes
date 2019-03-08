package com.gxaes.es.web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ESSessionListener
 *
 */
@WebListener
public class ESSessionListener implements ServletRequestListener, HttpSessionListener {
	private HttpServletRequest request ;
  
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	//读取RemmberMe的Cookied
    	Cookie[] cookies = request.getCookies();
    	String rememberMe = null ;
    	if(cookies != null && cookies.length > 0){
    		for(Cookie cookie : cookies)
    		if("rememberMe".equals(cookie.getName())){
    			rememberMe = cookie.getValue();
    		}
    	}
    	if(rememberMe != null){
    		se.getSession().setAttribute("USERTDENTITY", rememberMe);
    	}
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    	this.request = (HttpServletRequest) sre.getServletRequest();
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
