package com.hc.servlet.config.testServlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ConfigServlet extends GenericServlet {
    /**
     *
     *ServletConfig信息就是Tomcat将web.xml文件中的Servlet标签的配置信息自动包装到ServletConfig对象中
     *<servlet>
     *         <servlet-name>ConfigServlet</servlet-name>
     *         <servlet-class>com.hc.servlet.config.testServlet.ConfigServlet</servlet-class>
     *
     *         <!--    这里可以配置一个Servlet的初始化信息-->
     *         <init-param>
     *             <param-name>driver</param-name>
     *             <param-value>com.mysql.jdbc.Driver</param-value>
     *         </init-param>
     *         <init-param>
     *             <param-name>user</param-name>
     *             <param-value>root</param-value>
     *         </init-param>
     *         <init-param>
     *             <param-name>password</param-name>
     *             <param-value>hc</param-value>
     *         </init-param>
     *         <init-param>
     *             <param-name>url</param-name>
     *             <param-value>jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8&
     *                 rewriteBatchedStatements=true
     *             </param-value>
     *         </init-param>
     *     </servlet>
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        ServletConfig config = this.getServletConfig();
        String servletName = config.getServletName();
        writer.println("<servlet-name> =" + servletName);
        writer.println("<br>");
        writer.println("ServletConfig = " + config);
        writer.println("<br>");

        Enumeration<String> parameterNames = config.getInitParameterNames();
        while (parameterNames.hasMoreElements()){//是否有更多元素
            String nextElement = parameterNames.nextElement();//取元素 得到配置信息的名称
            String initParameter = config.getInitParameter(nextElement);//根据配置信息的名称获取对应的值
            writer.println(nextElement + "=" + initParameter);
            writer.println("<br>");
        }

        //GenericServlet父类提供了
//        public String getInitParameter(String name) {
//            return this.getServletConfig().getInitParameter(name);
//        }
//
//        public Enumeration<String> getInitParameterNames() {
//            return this.getServletConfig().getInitParameterNames();
//        }
        //可直接用this调用
        String root = this.getInitParameter("root");
        writer.println(root);

        //通过ServletConfig获取ServletContext
    }
}
