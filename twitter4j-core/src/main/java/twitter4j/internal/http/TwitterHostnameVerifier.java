package twitter4j.internal.http;

import java.net.URI;
import java.net.URISyntaxException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import com.levelup.socialapi.TouitContext;

public class TwitterHostnameVerifier implements HostnameVerifier {
	private final String restServer;
	
	public TwitterHostnameVerifier(String url) throws URISyntaxException {
		URI u = new URI(url);
		restServer = u.getHost();
	}

	@Override
	public boolean verify(String hostname, SSLSession session) {
		hostname = hostname.toLowerCase();
		
		if (hostname.endsWith(".twitter.com"))
			return true;
		if (hostname.equals(restServer))
			return true;
		
		if (hostname.endsWith("yfrog.com"))
			return true;
		if (hostname.endsWith("twitpic.com"))
			return true;
		if (hostname.endsWith("plixi.com"))
			return true;
		if (hostname.endsWith("img.ly"))
			return true;
		if (hostname.endsWith("mobypicture.com"))
			return true;
		if (hostname.endsWith("img.ly"))
			return true;
		if (hostname.endsWith("posterous.com"))
			return true;
		if (hostname.endsWith("mypict.me"))
			return true;
		if (hostname.endsWith("twitgoo.com"))
			return true;
		if (hostname.endsWith("twipl.net"))
			return true;
		if (hostname.endsWith("twipple.jp"))
			return true;

		TouitContext.getLogger().e("didn't verify "+hostname);
		return false;
	}

}
