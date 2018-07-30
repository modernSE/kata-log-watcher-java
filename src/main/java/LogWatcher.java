import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

	private List<String> subscribers;
	private List<String> subscribersToNotifyOnError;
	private List<String> subscribersToNotifyOnException42;
	private String weekendSlave;
	private NextLineDeliverer nextLineDeliverer;
	private Notificator notificator;
	private Date date;

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setWeekendSlave(String weekendSlave) {
		this.weekendSlave = weekendSlave;
	}

	public void setSubscribers(List<String> subscribers) {
		this.subscribers = subscribers;
	}

	public void setSubscribersToNotifyOnError(List<String> subscribersToNotifyOnError) {
		this.subscribersToNotifyOnError = subscribersToNotifyOnError;
	}

	public void setSubscribersToNotifyOnException42(List<String> subscribersToNotifyOnException42) {
		this.subscribersToNotifyOnException42 = subscribersToNotifyOnException42;
	}

	public void setNotificator(Notificator notificator) {
		this.notificator = notificator;
	}

	public void setNextLineDeliverer(NextLineDeliverer nextLineDeliverer) {
		this.nextLineDeliverer = nextLineDeliverer;
	}

	public void watchAndAlert() throws Exception {

		if (nextLineDeliverer == null) {
			throw new Exception("Next Line Deliverer not set");
		}

		Optional<String> logEntry = nextLineDeliverer.popNextLine();

		if (isWorkingDay(date)) {
			if (isWorkingHour(date)) {
				logEntry.ifPresent(s -> notificator.notifyUsersInChat("some chat", s));
			}

			logEntry.ifPresent(entry -> notifySubscribers(entry, subscribers));
		} else {
			if (nextLineDeliverer.numOfErrors() >= 10) {
				logEntry.ifPresent(s -> notificator.notifyUsersInChat("sime chat", s));
				logEntry.ifPresent(s -> notificator.notifyUser(weekendSlave, nextLineDeliverer.createMessageWithErrors()));
			}
		}

	}

	private boolean isWorkingHour(Date date) {
		int hours = date.getHours();
		return hours >= 8 && hours <= 17;
	}

	private boolean isWorkingDay(Date date) {
		int day = date.getDay();
		return day < 6 && day > 0;
	}

	
	private void notifySubscribers(String logMessage, List<String> subscribers) {

		for (String subscriber : subscribers) {

			if (Log.EXCEPTION_CODE_42.equals(logMessage)) {
				subscribersToNotifyOnException42.forEach(s -> notificator.notifyUser(createEmailFromName(subscriber),
						nextLineDeliverer.createMessageWithTrace()));
				nextLineDeliverer.clearTrace();
			}

			if (!Log.AN_ERROR_OCCURED.equals(logMessage)) {
				subscribersToNotifyOnError.forEach(s -> notificator.notifyUser(createEmailFromName(subscriber),
						nextLineDeliverer.popNextLine().get()));
			}

		}
	}

	private String createEmailFromName(String name) {
		String email = name.toLowerCase();
		email.replace("ü", "ue");
		email.replace("ä", "ae");
		email.replace("ö", "oe");
		email.replace(" ", ".");
		email = email + "@cas.de";
		return email;
	}

	public String[] getSubscribers() {
		return (String[]) subscribers.toArray();
	}
}
