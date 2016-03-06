package net.malta.dao.meta;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.malta.model.PrefectureCarriage;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml"})
@Transactional
@Ignore
public class PrefectureCarriageDAOTest {

	@Autowired
	PrefectureCarriageDAO dao;
	
	@Test
	public void testGetPrefectureCarriageValues() {
		List<PrefectureCarriage> prefectureCarriageValues = dao.getPrefectureCarriageValues();
		assertNotNull(prefectureCarriageValues);
		assertTrue("prefecture carriage values must not be empty", prefectureCarriageValues.size()>0);
		for (PrefectureCarriage prefectureCarriage : prefectureCarriageValues) {
			System.out.println(prefectureCarriage.getPrefecture() + " value:" + prefectureCarriage.getCarriageValue());
		}
	}
	
	@Test
	public void testGetPrefectureCarriageValue() {
		Integer carriageValue = dao.getCarriageValue(1);
		assertNotNull(carriageValue);
		assertTrue("carriage value must be greater than zero",carriageValue.intValue() > 0 );
		assertTrue(carriageValue.intValue() == 1060);		
	}
}
