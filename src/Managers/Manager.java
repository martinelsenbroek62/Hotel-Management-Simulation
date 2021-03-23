package Managers;

import java.util.Map;

import layoutHandler.JSONReader;

public abstract class Manager implements Icontrol {
   JSONReader reader;

	public Manager(JSONReader reader) {
		this.reader = reader;
	}

	protected Map.Entry<String, String> convertData(Map<String, String> data) {
		return data.entrySet().iterator().next();
	}
}
