import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextLogger implements ServletContextListener {


    private Logger log;

    public ContextLogger( ){}

    public void contextDestroyed(ServletContextEvent sce)  {

        String name = sce.getServletContext( ).getServletContextName( );

        //log request of the INFO level
        log.info("ServletContext shut down: " + (name == null ? "" : name ));

        //do other necessary work, like clean up any left-over resources
        //used by the web app
    }

    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext( );

        String realPath = context.getRealPath("/");
        String fileSep = System.getProperty("file.separator");

        //Make sure the real path ends with a file separator character ('/')
        if (realPath != null && (! realPath.endsWith(fileSep))){
            realPath = realPath + fileSep;}


        //Initialize logger here; the log4j properties filename is specified
        //by a context parameter named "logger-config"

        PropertyConfigurator.configure(realPath +
                "WEB-INF/" + context.getInitParameter("logger-config"));

        log = Logger.getLogger(ContextLogger.class);

        String name = context.getServletContextName( );

        //log request about servlet context being initialized
        log.info("ServletContext ready: " + (name == null ? "" : name ));

    }
}