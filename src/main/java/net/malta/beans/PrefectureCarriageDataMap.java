/**
 * @author SB
 */
package net.malta.beans;

import java.util.HashMap;
import java.util.Map;

public class PrefectureCarriageDataMap {

	private static final Map<Integer,Integer> prefectureCarriageMap = new HashMap<Integer,Integer>();
	
	public PrefectureCarriageDataMap() {
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(new Integer(1), new Integer(1060));
		map.put(new Integer(2), new Integer(740));
		map.put(new Integer(3), new Integer(740));
		map.put(new Integer(4), new Integer(640));
		map.put(new Integer(5), new Integer(740));
		
		map.put(new Integer(6), new Integer(640));
		map.put(new Integer(7), new Integer(640));
		map.put(new Integer(8), new Integer(640));
		map.put(new Integer(9), new Integer(640));
		map.put(new Integer(10), new Integer(640));
		map.put(new Integer(11), new Integer(640));
		map.put(new Integer(12), new Integer(640));
		map.put(new Integer(13), new Integer(640));
		map.put(new Integer(14), new Integer(640));
		map.put(new Integer(15), new Integer(640));
		map.put(new Integer(16), new Integer(640));
		map.put(new Integer(17), new Integer(640));
		map.put(new Integer(18), new Integer(640));
		
		map.put(new Integer(19), new Integer(640)); // yamanashi
		map.put(new Integer(20), new Integer(640)); // nagano
		map.put(new Integer(21), new Integer(640)); // gifu
		map.put(new Integer(22), new Integer(640)); // Shizuoka
		map.put(new Integer(23), new Integer(640)); // Aichi
		map.put(new Integer(24), new Integer(640)); // Mie
		map.put(new Integer(25), new Integer(740)); // Shiga
		map.put(new Integer(26), new Integer(740)); // Kyoto
		map.put(new Integer(27), new Integer(740)); // Osaka
		map.put(new Integer(28), new Integer(740)); // Hyogo
		map.put(new Integer(29), new Integer(740)); // Naara
		map.put(new Integer(30), new Integer(740)); // Wakayama
		
		map.put(new Integer(31), new Integer(850)); // Tottori
		map.put(new Integer(32), new Integer(850)); // Shimane
		map.put(new Integer(33), new Integer(850)); // Okayama
		map.put(new Integer(34), new Integer(850)); // Hiroshima
		map.put(new Integer(35), new Integer(850)); // Yamaguchi
		
		map.put(new Integer(36), new Integer(950)); // Tokushima
		map.put(new Integer(37), new Integer(950)); // Kagawa
		map.put(new Integer(38), new Integer(950)); // Ehime
		map.put(new Integer(39), new Integer(950)); // kochi
		map.put(new Integer(40), new Integer(1060)); // fukuoka
		map.put(new Integer(41), new Integer(1060)); // saga
		map.put(new Integer(42), new Integer(1060)); // nagasaki
		map.put(new Integer(43), new Integer(1060)); // kumamoto
		map.put(new Integer(44), new Integer(1060)); // oita
		map.put(new Integer(45), new Integer(1060)); // miyazaki
		map.put(new Integer(46), new Integer(1060)); // kagoshima
		map.put(new Integer(47), new Integer(1160)); // Okinawa			
	}
	
	public static Integer getCarriage(Integer prefecture) {
		Integer carriage  = prefectureCarriageMap.get(prefecture);
		return carriage == null ? 0 : carriage;
	}
}
