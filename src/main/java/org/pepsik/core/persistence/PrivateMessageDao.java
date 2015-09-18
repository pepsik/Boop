package org.pepsik.core.persistence;

import org.pepsik.core.models.entities.PrivateMessage;
import org.pepsik.core.models.entities.User;

import java.util.List;

/**
 * Created by pepsik on 6/2/15.
 */
public interface PrivateMessageDao {

    void createPrivateMessage(PrivateMessage message);

    PrivateMessage getPrivateMessage(long id);

    List<PrivateMessage> getOutputPrivateMessages(long userId);

    List<PrivateMessage> getInputPrivateMessages(long userId);

    long getOutputPrivateMessageCount(User user);

    long getInputPrivateMessageCount(User user);

    void updatePrivateMessage(PrivateMessage message);

    void deletePrivateMessage(PrivateMessage message);
}
