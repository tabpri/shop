
package org.andromda.persistence.hibernate.usertypes;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class HibernateStringClobTypeTest {

    private HibernateStringClobType type;

    @Mocked
    private ResultSet resultSet;

    @Mocked
    private Reader reader;

    @Mocked
    private PreparedStatement statement;

    @Before
    public void setUp() {
        type = new HibernateStringClobType();
    }

    @Test
    public void testSqlTypes() {
    	System.out.println("HibernateStringClobType.sqlTypes:" + type.sqlTypes());
    }

    @Test
    public void testReturnedClass() {
    	System.out.println("HibernateStringClobType.returnedClass:" + type.returnedClass());
    	assertEquals(type.returnedClass(), String.class);
    }

    @Test
    public void testEquals() {

    	byte[] x = "testbyte123".getBytes();
    	byte[] y = "testbyte123".getBytes();

    	String val1 = "1";
    	String val2 = "1";

    	assertFalse(type.equals(null, y));
    	assertFalse(type.equals(x, y));
    	assertTrue(type.equals(val1, val2));
    }

    @Test
    public void testNullSafeGet1() throws Exception {
    	new NonStrictExpectations() {{
    		resultSet.getCharacterStream("column"); result= null;
    	}};
		try {
			assertNull(type.nullSafeGet(resultSet, new String[] {"column", "column"}, new Object()));
		} catch (Exception e) {
			fail();
		}
    }

    @Test
    public void testNullSafeGet2() throws Exception {
    	new NonStrictExpectations() {{
    		resultSet.getCharacterStream("column"); result= reader;
    		reader.read(new char[1024]); result = new IOException();
    	}};
		try {
			type.nullSafeGet(resultSet, new String[] {"column", "column"}, new Object());
		} catch (Exception e) {
			return;
		}
		fail();
    }

    @Test
    public void testNullSafeGet3() throws Exception {
    	new NonStrictExpectations() {{
    		resultSet.getCharacterStream("column"); result= reader;
    		reader.read(new char[1024]); result= 1; result=2; result=-1;
    	}};
		try {
			assertNotNull(type.nullSafeGet(resultSet, new String[] {"column", "column"}, new Object()));
		} catch (Exception e) {
			fail();
		}
    }

    @Test
    public void testNullSafeSet1() throws Exception {
		try {
			type.nullSafeSet(statement, "column", 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testNullSafeSet2() throws Exception {
		try {
			type.nullSafeSet(statement, null, 1);
		} catch (Exception e) {
			fail();
		}
    }

    @Test
    public void testNullSafeSet3() throws Exception {
    	new NonStrictExpectations() {{
    		statement.setBinaryStream(1, null, 0); result = new SQLException();
    	}};
		try {
			type.nullSafeSet(statement, null, 1);
		} catch (Exception e) {
			fail();
		}
    }

    @Test
    public void testDeepCopy1() {
    	assertNotNull(type.deepCopy("12345"));
    }

    @Test
    public void testDeepCopy2() {
    	assertNotNull(type.deepCopy(null));
    }

    @Test
    public void testIsMutable() {
    	assertFalse(type.isMutable());
    }

    @Test
    public void testReplace() {
    	assertNotNull(type.replace("1", "target", "owner"));
    }

    @Test
    public void testAssemble() {
    	assertNotNull(type.assemble(new String("11111"), null));
    }

    @Test
    public void testDisassemble() {
    	assertNotNull(type.disassemble(new String("11111")));
    }

    @Test
    public void testHashCode() {
    	assertNotNull(type.hashCode(new String("11111")));
    }

    @After
    public void checkExpectations() {
    }
}
