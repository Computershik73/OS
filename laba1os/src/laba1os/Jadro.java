package laba1os;

import java.util.HashMap;
import java.util.Set;

public class Jadro 
{
	//����
	Stack stack= new Stack();
	//���-������� � ��������
	public HashMap<Integer, SystemCall> SystemCalls;
	
	public Jadro(Stack stack)
	{
	        this.stack = stack;
	        SystemCalls = new HashMap<>();
	        //��������� ����� ������
	        SystemCalls.put(100,new SystemCall("lab1"));
	        SystemCalls.put(1,new SystemCall("1"));
	        SystemCalls.put(20,new SystemCall("operating", "systems", "1"));
	        SystemCalls.put(50,new SystemCall("2", "4"));
	        SystemCalls.put(4,new SystemCall("l", "a", "b","�","1"));
	}
	//����� ���������� ������
	public void ispolnenie_Call(int id)	{	
		//�������� �� ��, ��� ������ ���� � �������
	    if (!SystemCalls.containsKey(id))
	    {
	    	System.out.print("��������� ����� "+id+" �� ����������\n");
	        return;
	    }
	    //������ ���������� �� ���������� �������	    
	    
	    for (int i=SystemCalls.get(id).getArgs().size()-1; i>=0; i--)
	    {	    	
	    	if (!stack.remove().equals(SystemCalls.get(id).getArgs().get(i))) 
	        {	    		
	    		System.out.println("��������� �� ���������" + " id =" + id);
	            return;
	        }
	    }		    
	
	    System.out.println(SystemCalls.get(id).messedg()+ ": id = " + id + ", ��������� =" + SystemCalls.get(id).getArgs().toString());
	    
	}
	//����� ������ ��������� �������
	public  void printf_Calls()	{		
		
			Set<Integer> keys = SystemCalls.keySet();
			
			for (Integer key : keys) {
				System.out.println(key + " " + SystemCalls.get(key).getArgs().toString());
		}
	}

	
}