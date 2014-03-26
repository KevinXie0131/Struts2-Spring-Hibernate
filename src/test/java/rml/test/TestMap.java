package rml.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class TestMap {

	//JDK 1.5
	@Test
	public void T15() {
		Map m = new HashMap();
		m.put("name", "abcdef1");
		m.put("pwd", "1324561");
		System.out.println("key:  "+m.keySet());
		for (Object o : m.keySet()) {
			System.out.println(o);
			System.out.println(m.get(o));
		}

	}

	//JDK 1.4
	public void T14() {
		Map map = new HashMap();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
		}
	}

}
