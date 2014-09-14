package com.aft.encuestas.control.core;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;



public class InitLog extends HttpServlet {

    public void init() throws ServletException{

        String log4jfile = getInitParameter("initLog");
        if(log4jfile != null){
            String propfile = getServletContext().getRealPath(log4jfile);
            PropertyConfigurator.configure(propfile);
        }

    }
}
