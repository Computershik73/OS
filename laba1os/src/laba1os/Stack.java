package laba1os;

import java.util.ArrayList;

public class Stack 
{
	ArrayList<String> stack=new ArrayList<>();
	// добавление в стек
	public void add(String element) 
	{
        stack.add(element);
    }
	//извлечение из стека
	public String remove() {
		String element=stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
		return element;
    }  
		
}