package com.ios.Persistance;

import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class studentModel implements ServletContextListener
{
    public static Connection con;

public void contextInitialized(ServletContextEvent arg0) 
{
	System.out.println("Made shit");
 //con.getInstance ();     
}//end contextInitialized method


public void contextDestroyed(ServletContextEvent arg0) 
{
	System.out.println("got Rid of Shit!");
 //con.close ();       
}//end constextDestroyed method

}