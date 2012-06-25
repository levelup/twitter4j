package twitter4j.internal.util;

import java.util.Date;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

import twitter4j.internal.logging.Logger;

public class z_T4JTime {

	private static final long MIN_SET_INTERVAL = 20000; // 20s
	private static Long lastSet = new Long(0);
	private static long twitterDelayMillis = 0;
	private static final Logger logger = Logger.getLogger(z_T4JTime.class);

	public static long getTwitterTimeDiff() {
		return twitterDelayMillis;
	}
	
	public static long getTwitterTimeMillis() {
		return System.currentTimeMillis() + twitterDelayMillis;
	}

	public static void setTwitterTime(String date) {
		synchronized (lastSet) {
			if (lastSet==0 || System.currentTimeMillis()>(lastSet+MIN_SET_INTERVAL))
				try {
					Date currentLocal = new Date();
					Date currentTwitter = DateUtils.parseDate(date);//z_T4JInternalParseUtil.getDate(date, "EEE, dd MMM yyyy HH:mm:ss z");
					twitterDelayMillis = currentTwitter.getTime() - currentLocal.getTime();
					lastSet = System.currentTimeMillis();
					if (twitterDelayMillis < -120000 || twitterDelayMillis > 120000)
						logger.info("The client time is off by "+twitterDelayMillis+" ms");
				} catch (DateParseException e) {
					e.printStackTrace();
				}
		}
	}
}
