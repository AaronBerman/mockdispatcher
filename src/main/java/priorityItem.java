
import java.lang.String;

public class priorityItem {

	private String processName;
	private String priority;
	private String idNum;
	private String context;
	
	public priorityItem() {
		idNum = "1";
		processName = "generic";
		priority = "3";
		context = "Ready";
	}
	
	public priorityItem(int x) {
		String temp = Integer.toString(x);
		idNum = temp;
		processName = "generic";
		priority = "3";
		context = "Ready";
	}
	
	public priorityItem(int x, String name) {
		String temp = Integer.toString(x);
		idNum = temp;
		processName = name;
		priority = "3";
		context = "Ready";
	}
	
	public priorityItem(int x, String name, String y) {
		String temp = Integer.toString(x);
		idNum = temp;
		processName = name;
		priority = y;
		context = "Ready";
	}
	
	public String getProcessName() {
		return processName;
	}
    public void setProcessName(String newName) {
    	processName = newName;
    }
    
    public String getPriority() {
    	return priority;
    }
    public void setPriority(String newPriority) {
    	priority = newPriority;
    }
    
    public String getIdNum() {
    	return idNum;
    }
    public void setIdNum(String newId) {
    	idNum = newId;
    }
    
    public String getContext() {
    	return context;
    }
    public void setContext(String newContext) {
    	context = newContext;
    }
    
    @Override
    public String toString() {
    	return processName;
    }
}
