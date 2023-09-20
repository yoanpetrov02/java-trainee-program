package com.sirma.javacourse.designpatterns.observer;

import com.sirma.javacourse.designpatterns.observer.observable.StockManager;
import com.sirma.javacourse.designpatterns.observer.observable.Stock;
import com.sirma.javacourse.designpatterns.observer.subscriber.Available;
import com.sirma.javacourse.designpatterns.observer.subscriber.Sold;

public class ObserverRunner {

	public static void main(String[] args) {
		StockManager list = new StockManager();
		list.addSubscriber(new Available());
		list.addSubscriber(new Sold());

		Stock[] stocks = new Stock[] {
				new Stock("pencil"),
				new Stock("glue"),
				new Stock("paper")
		};

		for (Stock stock : stocks) {
			list.add(stock);
			list.printState();
		}

		for (Stock stock : stocks) {
			list.sell(stock);
			list.printState();
		}
	}
}
