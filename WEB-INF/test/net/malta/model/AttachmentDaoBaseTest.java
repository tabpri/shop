
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class AttachmentDaoBaseTest {

    private AttachmentDaoBase base;

    private Attachment attachment;

    private List attachmentList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new AttachmentDaoImpl();

        attachment = Attachment.Factory.newInstance();

        attachmentList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	Attachment attachment = Attachment.Factory.newInstance();
        	attachment.setId(new Integer(i + 1));
        	attachmentList.add(attachment);
        }

    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.AttachmentImpl.class, anyInt); result= attachment;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((Attachment) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.AttachmentImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((Attachment) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.AttachmentImpl.class, anyInt); result= attachment;
    	}};

    	try {
    		base.load(0, null);
    	} catch (Exception e) {
    		return;
    	}
    	fail();
    }

    @Test
    public void testLoad4() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.AttachmentImpl.class, anyInt); result= attachment;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.AttachmentImpl.class, anyInt); result= attachment;
    	}};

    	try {
    		base.load(null);
    	} catch (Exception e) {
    		return;
    	}
    	fail();
    }

    @Test
    public void testLoadALL1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.loadAll(net.malta.model.AttachmentImpl.class); result= attachmentList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(attachment); result= attachment;
    	}};

    	Attachment obj = Attachment.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(attachment); result= attachment;
    	}};

    	Attachment obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(attachmentList);
    }

    @Test
    public void testCreate4() {

    	try {
    		attachmentList = null;
			base.create(attachmentList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(attachment); result= attachment;
    	}};

    	String fileType = "AttachMent";
    	float width = 100;
    	float height = 100;
    	boolean wide = true;

    	base.create(fileType, width, height, wide);
    }

    @Test
    public void testUpdate1() {

    	base.update(attachment);
    }

    @Test
    public void testUpdate2() {

    	try {
    		attachment = null;
			base.update(attachment);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(attachmentList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		attachmentList = null;
			base.update(attachmentList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(attachment);
    }

    @Test
    public void testRemove2() {

    	try {
    		attachment = null;
			base.remove(attachment);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(attachmentList);
    }

    @Test
    public void testRemove4() {

    	try {
    		attachmentList = null;
			base.remove(attachmentList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.AttachmentImpl.class, anyInt); result= attachment;
    	}};

    	Integer id = new Integer(1);

    	base.remove(id);
    }

    @Test
    public void testRemove6() {

    	Integer id = null;

    	try {
			base.remove(id);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @After
    public void checkExpectations() {
    }
}
