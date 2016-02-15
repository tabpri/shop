package net.malta.service.purchase;

import net.malta.model.Choise;

public interface IChoiseService {

	Choise getChoise(Integer id);

	Choise addChoise(Integer purchaseId, Choise choise);

	Choise updateChoise(Integer purchaseId, Choise choise);

	void deleteChoise(Integer purchaseId, Integer choiseId);

}