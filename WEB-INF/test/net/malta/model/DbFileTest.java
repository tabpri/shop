
package net.malta.model;

import static org.junit.Assert.*;
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
public class DbFileTest {

    private DbFile file;

    private DbFile file2;

    private DbFile file3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	file = DbFile.Factory.newInstance();

    	file.setId(new Integer(1));
    	file.setData("data".getBytes());

    	file.getId();
    	file.getData();

    	file2 = DbFile.Factory.newInstance();
    	file2.setId(new Integer(2));

    	file3 = DbFile.Factory.newInstance();
    	file3.setId(new Integer(1));

    	assertTrue(file.equals(file));
    	assertFalse(file.equals(Item.Factory.newInstance()));
    	assertFalse(file.equals(file2));
    	assertTrue(file.equals(file3));

    	file.hashCode();
    	DbFile.Factory.newInstance().hashCode();

    	DbFile.Factory.newInstance("data".getBytes());

    }

    @After
    public void checkExpectations() {
    }
}
