import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

	private Util util;
	private ChatBot chatBot;
	private WeekendUtil weekendUtil;

    public LogWatcher(Util util, ChatBot chatBot, WeekendUtil weekendUtil) {
		this.util = util;
		this.chatBot = chatBot;
		this.weekendUtil = weekendUtil;
	}
    
    public void watchAndAlert() {
    	Optional<String> logLine = Log.popNextLine();

    	util.notify(logLine);
    	chatBot.notify(logLine);
    	weekendUtil.notify(logLine);
    }

}
