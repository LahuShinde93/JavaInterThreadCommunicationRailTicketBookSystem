
package com.nit.MultiThreadingITC;

class InterThread {

	private int availableTickets = 5;

	public synchronized void bookTickets(int wantedTickets) {

		while (availableTickets < wantedTickets) {

			System.out.println("Sorry! Tickets are not available wating for cancelletion tickets...");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 

		availableTickets = availableTickets - wantedTickets;
		System.out.println("booked " + wantedTickets + " tickets successfully..!");
		System.out.println("Now Available tickets : " + availableTickets);

		notify();

	}

	public synchronized void cancelTickets(int cancleTicket) {
		availableTickets = availableTickets + cancleTicket;
		System.out.println("Canclled " + cancleTicket + " tickets..!");
		System.out.println("Now Available tickets : " + availableTickets);
		notify();
	}

	public static void main(String[] args) {

		InterThread book = new InterThread();

		Thread bookingTickets = new Thread() {

			@Override
			public void run() {
				int passengers[] = { 2, 4, 1, 4 };

				for (int ticket : passengers) {
					book.bookTickets(ticket);
					try {
						Thread.sleep(1000); // give some time b/w booking
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};

		bookingTickets.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread cancleTicket = new Thread() {

			@Override
			public void run() {
				int cancleTicket[] = { 1, 3 };

				for (int cancle : cancleTicket) {

					book.cancelTickets(cancle);
					try {
						Thread.sleep(1000); // give some time b/w booking
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};

		cancleTicket.start();

	}

}
