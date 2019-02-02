# spring-boot-awt-gui-demo
Demonstration on how to create an AWT GUI in Spring Boot.

The project contains the needed tweeks to your Spring Boot configuration to allow an AWT or Swing GUI application to
run in Spring Boot.

The changes are essentially the following:

## Disable Headless Mode

`.headless(false)`

By default a Spring Boot application is set to run in *headless* mode. Meaning can be run on a server or other environment where there is no graphical interface (the 'head') available. Without doing this, any attempt to instantiate an AWT GUI element will cause a `java.awt.HeadlessException` to be thrown.

## Disable Web Server Mode

`.web(false)`

Spring Boot make the assumption that you will be creating a Web Application. Be default it will bundle Tomcat with your application, unless you add Tomcat to your classpath. For most GUI applications you won't be using Tomcat at all, so we need to completely disable the Web Server behaviour.

## Use the AWT EventQueue Thread

    java.awt.EventQueue.invokeLater(new Runnable() {
               @Override
                public void run() {
                    frame.setVisible(true);
                }
            });

This isn't specific to Spring Boot and applies to any AWT application.
