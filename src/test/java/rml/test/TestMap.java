package rml.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class TestMap {

	@Test
	public void T15() {
		Map m = new HashMap();
		m.put("name", "abcdef");
		m.put("pwd", "132456");
		for (Object o : m.keySet()) {
			System.out.println(o);
			System.out.println(m.get(o));
		}

	}

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
