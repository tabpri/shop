package net.malta.model.payment;

public enum PaymentStatusEnum {	
	PENDING,
	ACS_CONFIRM,
	COMPLETED,
	FAILED, 
	ACS_CONFIRM_FAILED, 
	WAITING_FOR_PAYMENT;
	
	public static void main(String[] args) {
		for (PaymentStatusEnum paymentStatus : PaymentStatusEnum.values()) {
			System.out.println(paymentStatus.toString());
		}
	}
}