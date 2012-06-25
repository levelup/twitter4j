/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twitter4j.media;

import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpParameter;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/**
 * @author Steve Lhomme - slhomme at levelupstudio.com
 * @since Twitter4J 2.2.6
 */
public class MyPictMe extends AbstractImageUploadImpl {

	public MyPictMe(Configuration conf, OAuthAuthorization oauth) {
		super(conf, oauth);
	}

	@Override
	protected String postUpload() throws TwitterException {
		int statusCode = httpResponse.getStatusCode();
		if (statusCode != 200)
			throw new TwitterException("Mypict.me image upload returned invalid status code", httpResponse);

		String response = httpResponse.asString();

		try {
			JSONObject json = new JSONObject(response);
			if (!json.isNull("url"))
				return json.getString("url");
		} catch (JSONException e) {
			throw new TwitterException("Invalid MyMict.me response: " + response, e);
		}

		throw new TwitterException("Unknown MyMict.me response : "+httpResponse.asString(), httpResponse);
	}

	@Override
	protected void preUpload() throws TwitterException {
		uploadUrl = "http://mypict.me/uploader_auth.php";
		String signedVerifyCredentialsURL = generateVerifyCredentialsAuthorizationURL(TWITTER_VERIFY_CREDENTIALS_XML);

		HttpParameter[] params = {
				new HttpParameter("twitter_user", "touiteurtest"), // TODO
				new HttpParameter("twitter_uid", "93009608"), // TODO
				new HttpParameter("auth", signedVerifyCredentialsURL),
				new HttpParameter("is_verified", "NO"),
				new HttpParameter("bb_pin", "56f43f5736a5680e"), // TODO
				new HttpParameter("mime_type", this.image.getContentType()),
				new HttpParameter("pic_name", this.image.getName()),
				new HttpParameter("size", String.valueOf(this.image.getFile().length())),
				new HttpParameter("method", "new"),
				new HttpParameter("pic", this.image.getFile()),
				new HttpParameter("tweet", message==null ? "" : message.getValue()),
		};
		this.postParameter = params;
	}
}
