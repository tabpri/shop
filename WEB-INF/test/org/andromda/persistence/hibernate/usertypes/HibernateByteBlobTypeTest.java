
package org.andromda.persistence.hibernate.usertypes;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mockit.Mock;
import mockit.MockUp;
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
public class HibernateByteBlobTypeTest {

    private HibernateByteBlobType type;

    @Mocked
    private ResultSet resultSet;

    @Mocked
    private InputStream inputStream;

    @Mocked
    private PreparedStatement statement;

    @Before
    public void setUp() {
        type = new HibernateByteBlobType();
    }

    @Test
    public void testSqlTypes() {
    	System.out.println("HibernateByteBlobType.sqlTypes:" + type.sqlTypes());
    }

    @Test
    public void testReturnedClass() {
    	System.out.println("HibernateByteBlobType.returnedClass:" + type.returnedClass());
    	assertEquals(type.returnedClass(), byte[].class);
    }

    @Test
    public void testEquals() {

    	byte[] x = "testbyte123".getBytes();
    	byte[] y = "testbyte123".getBytes();
    	byte[] z = "testbyte1234".getBytes();

    	assertTrue(type.equals(x, y));
    	assertFalse(type.equals(x, z));
    }

    @Test
    public void testNullSafeGet1() throws Exception {
    	new NonStrictExpectations() {{
    		resultSet.getBinaryStream("column"); result= null;
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
    		resultSet.getBinaryStream("column"); result= new BufferedInputStream(inputStream);
    	}};
    	new MockUp<BufferedInputStream> () {
    		@Mock public int read(byte[] b) throws IOException {
    			throw new IOException();
    		}
		};
		try {
			assertNull(type.nullSafeGet(resultSet, new String[] {"column", "column"}, new Object()));
		} catch (Exception e) {
			return;
		}
		fail();
    }

    @Test
    public void testNullSafeGet3() throws Exception {
    	new NonStrictExpectations() {{
    		resultSet.getBinaryStream("column"); result= inputStream;
    		inputStream.read(new byte[65536]); result= 1; result=2; result=-1;
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
			type.nullSafeSet(statement, "12345".getBytes(), 1);
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
    	assertNull(type.deepCopy(null));
    }

    @Test
    public void testDeepCopy2() {
    	assertNotNull(type.deepCopy("12345".getBytes()));
    }

    @Test
    public void testIsMutable() {
    	assertTrue(type.isMutable());
    }

    @Test
    public void testReplace() {
    	assertNotNull(type.replace(new String[] {"1", "2", "3"}, "123".getBytes(), new Integer(10)));
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
