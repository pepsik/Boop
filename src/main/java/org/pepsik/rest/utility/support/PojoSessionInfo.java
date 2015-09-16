package org.pepsik.rest.utility.support;

import org.joda.time.DateTime;

/**
 * Created by pepsik on 7/7/15.
 */
public class PojoSessionInfo {

    private String sessionId;

    private DateTime creationDate;

    private DateTime lastAccessedTime;

    private String userAgent;

    private String userRemoteIp;

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastAccessedTime(DateTime lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setUserRemoteIp(String userRemoteIp) {
        this.userRemoteIp = userRemoteIp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public DateTime getLastAccessedTime() {
        return lastAccessedTime;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getUserRemoteIp() {
        return userRemoteIp;
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "sessionId=" + sessionId +
                ", creationDate=" + creationDate +
                ", lastAccessedTime=" + lastAccessedTime +
                ", userAgent='" + userAgent + '\'' +
                ", userRemoteIp='" + userRemoteIp + '\'' +
                '}';
    }
}
