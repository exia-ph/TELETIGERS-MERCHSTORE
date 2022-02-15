package ContextListeners;

import javax.servlet.*;

public class UserContextListener implements ServletContextListener
{    
    public void contextInitialized(ServletContextEvent sce)
    {
        ServletContext sc = sce.getServletContext();
        
        User user = new User();
        user = (User) sc.getAttribute("userCon");
        
        sc.setAttribute("user1", user);
    }
    
    
    public void contextDestroyed(ServletContextEvent sce) {} //Does nothing because no need for destroy
}
