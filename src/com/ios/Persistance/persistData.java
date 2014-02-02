package com.ios.Persistance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class persistData implements ServletContextListener
{
    public static dataModel persistance = dataModel.getInstance();


public void contextInitialized(ServletContextEvent arg0) 
{
	System.out.println("Made shit");
 //con.getInstance ();     
}//end contextInitialized method


public void contextDestroyed(ServletContextEvent arg0) 
{
	try {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("persistance.sp"));
        out.writeObject(persistance);
        System.out.println("I put that shit away?");
        out.flush();
        out.close();
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
	System.out.println("got Rid of Shit!");
 //con.close ();       
}//end constextDestroyed method


}