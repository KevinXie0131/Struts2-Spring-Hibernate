package rml.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * Clob Converter
 * 
 */
public class ClobConverter extends StrutsTypeConverter {

	private static final Logger logger = Logger.getLogger(ClobConverter.class);

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null || values.length == 0) {
			return null;
		}
		return this.getClob(values[0]);
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof Clob) {
			return this.getString((Clob) o);
		}
		return "";
	}

	/**
	 * get String
	 * 
	 * @param c
	 *            java.sql.Clob
	 * @return String
	 */
	private String getString(Clob c) {
		StringBuffer s = new StringBuffer();
		if (c != null) {
			try {
				BufferedReader bufferRead = new BufferedReader(c.getCharacterStream());
				try {
					String str;
					while ((str = bufferRead.readLine()) != null) {
						s.append(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return s.toString();
	}

	/**
	 * get Clob
	 * 
	 * @param s
	 *            String
	 * @return java.sql.Clob
	 */
	private Clob getClob(String s) {
		Clob c = null;
		try {
			if (s != null) {
				c = new SerialClob(s.toCharArray());
			}
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
