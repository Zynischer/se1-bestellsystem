package system;

import java.util.function.Consumer;

import datamodel.Order;
import datamodel.OrderItem;

final class OrderProcessor implements Components.OrderProcessor {

	private InventoryManager inventoryManager;

	OrderProcessor(InventoryManager inventory) {
		inventoryManager = inventory;
	}

	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(Order order, Consumer<Order> acceptCode, Consumer<Order> rejectCode,
			Consumer<OrderItem> rejectedOrderItemCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue) { // rateIndex = 1
		return vat(grossValue, 1);
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		switch (rateIndex) {
		case 1: // 19
			return Math.round((grossValue / 1.19) * 0.19);
		case 2: // 7
			return Math.round((grossValue / 1.07) * 0.07);
		default:
			return 0;
		}
	}

}
