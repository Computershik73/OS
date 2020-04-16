package laba1os;

import java.util.Arrays;
import java.util.List;

public class SystemCall
{
	 private List<String> arguments;
	    public SystemCall(String... args) 
	    {
	        arguments = Arrays.asList(args);
	    }
	    
	    public List getArgs()
	    {
	        return arguments;
	    }

	    public String messedg()
	    {
	    	return "Вызов выполнен";
	    }	    	    
	  
}
