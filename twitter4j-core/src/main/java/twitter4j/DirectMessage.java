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

package twitter4j;

import java.util.Date;

/**
 * A data interface representing sent/received direct message.
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public interface DirectMessage extends TwitterResponse, java.io.Serializable {

    long getId();

    String getText();

    long getSenderId();

    long getRecipientId();

    /**
     * @return created_at
     * @since Twitter4J 1.1.0
     */
    Date getCreatedAt();

    String getSenderScreenName();

    String getRecipientScreenName();


    User getSender();


    User getRecipient();

    /**
     * Returns an array if URLEntity mentioned in the tweet, or null if no URLs were mentioned.
     *
     * @return An array of URLEntity mentioned in the tweet.
     * @since Twitter4J 2.2.5
     */
    URLEntity[] getURLEntities();

    /**
     * Returns an array of MediaEntities if medias are available in the tweet, or null if no media is included in the tweet.
     *
     * @return an array of MediaEntities.
     * @since Twitter4J 2.2.3
     */
    MediaEntity[] getMediaEntities();
}
