package com.william.demo.demo.testController;

import com.sun.deploy.net.HttpResponse;
import com.sun.net.httpserver.HttpServer;
import com.william.demo.demo.utils.myUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class ControllerOne {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/hello2")
    public String helloName(@RequestParam(name = "name") String name) {
        return "hello world , your name is " + name;
    }

    @RequestMapping("/Login_authentication")
    public String Login_authentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String session_vcode=(String) request.getSession().getAttribute("text");    //从session中获取真正的验证码
        return session_vcode;
    }

    @RequestMapping("/getVerifiCode")
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         *      1.生成验证码
         *      2.把验证码上的文本存在session中
         *      3.把验证码图片发送给客户端
         */
        myUtils utils = new myUtils();//用我们的验证码类，生成验证码类对象
        BufferedImage image = utils.getImage(); //获取验证码
        request.getSession().setAttribute("text", utils.getText()); //将验证码的文本存在session中
        myUtils.output(image,response.getOutputStream());//将验证码图片响应给客户端
    }
}
