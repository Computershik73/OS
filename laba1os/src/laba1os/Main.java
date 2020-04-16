package laba1os;

public class Main 
{
	public static void main(String[] args) 
	{
		
	    Stack stack =new Stack();
	    Jadro core=new Jadro(stack);
	    
	    core.ispolnenie_Call(5);
	    
	    stack.add("2");
	    stack.add("4");	     	     
	    core.ispolnenie_Call(50);
	    
	    stack.add("2");
	    stack.add("4");
	    core.ispolnenie_Call(4);
	    
	    stack.add("1");	    
	    core.ispolnenie_Call(1);
	    
	    stack.add("operating");
	    stack.add("systems");
	    stack.add("1");
	    core.ispolnenie_Call(20);
	    
	    core.printf_Calls();  
	   
	}
}