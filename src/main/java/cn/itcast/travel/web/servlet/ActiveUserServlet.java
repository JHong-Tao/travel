package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Author: JHong.Tao
 * Date: 2019-09-13-13:28
 * Version：1.0.0
 * Description:
 */
@WebServlet(value = "/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码设置
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 1.获取激活码
        String code = request.getParameter("code");
        if(code != null){
            // 2.调用service完成激活
            UserServiceImpl service = new UserServiceImpl();
            boolean flag = service.active(code);

            // 3.判断标记
            String msg = null;
            if(flag){
                // 3.1激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else {
                //3.2 激活失败
                msg = "激活失败，请联系管理员";
            }
            response.getWriter().write(msg);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
