package ZstProcessing;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import examples.TestNode;
import ZST.*;


public class Showtime extends ZstNode{

	public Showtime(String nodeId, int stagePort) {
		super(nodeId, stagePort);
		start();
	}
	
	public Showtime(String nodeId, String stageAddress) {
		super(nodeId, stageAddress);
		requestRegisterNode();
		start();
	}
	
	public void registerMethod(String methodName, String accessMode, Object owner){
		registerMethod(methodName, accessMode, owner, new String[0]);
	}
	
	public void registerMethod(String methodName, String accessMode, Object owner, String[] args){
		Map<String, Object> nodeArgs = new HashMap<String, Object>();
		
		for(String arg : args)
			nodeArgs.put(arg, "");
		
		Method callback = null;
		try {
			callback = owner.getClass().getDeclaredMethod(methodName, new Class[]{ZstMethod.class});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        requestRegisterMethod(methodName, accessMode, nodeArgs, owner, callback);
	}

}
