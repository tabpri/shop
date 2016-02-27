
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
public class AttachmentTest {

    private Attachment attachment;

    private Attachment attachment2;

    private Attachment attachment3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	attachment = Attachment.Factory.newInstance();

    	attachment.setId(new Integer(1));
    	attachment.setFiletype("filetype");
    	attachment.setWidth(0);
    	attachment.setHeight(0);
    	attachment.setWide(true);
    	attachment.setDbFile(DbFile.Factory.newInstance());
    	attachment.setAszoom(Item.Factory.newInstance());
    	attachment.setAsdetailed(Item.Factory.newInstance());
    	attachment.setAsdefault(Item.Factory.newInstance());

    	attachment.getId();
    	attachment.getFiletype();
    	attachment.getWidth();
    	attachment.getHeight();
    	attachment.isWide();
    	attachment.getDbFile();
    	attachment.getAszoom();
    	attachment.getAsdetailed();
    	attachment.getAsdefault();

    	attachment2 = Attachment.Factory.newInstance();
    	attachment2.setId(new Integer(2));

    	attachment3 = Attachment.Factory.newInstance();
    	attachment3.setId(new Integer(1));

    	assertTrue(attachment.equals(attachment));
    	assertFalse(attachment.equals(Item.Factory.newInstance()));
    	assertFalse(attachment.equals(attachment2));
    	assertTrue(attachment.equals(attachment3));

    	attachment.hashCode();
    	Attachment.Factory.newInstance().hashCode();

    	Attachment.Factory.newInstance("filetype", 0, 0, true);
    	Attachment.Factory.newInstance("filetype", 0, 0, true, DbFile.Factory.newInstance(),
    			Item.Factory.newInstance(), Item.Factory.newInstance(), Item.Factory.newInstance());

    }

    @After
    public void checkExpectations() {
    }
}
