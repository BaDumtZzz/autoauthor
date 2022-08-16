package ru.rsreu.autoauthor.listener;

import ru.rsreu.autoauthor.dao.oracle.OracleDao;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

public class SessionListener implements HttpSessionListener {

    private final AtomicInteger activeSessions;

    public SessionListener() {
        super();

        activeSessions = new AtomicInteger();
    }

    public int getTotalActiveSession() {
        return activeSessions.get();
    }

    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
    }

    public void sessionDestroyed(final HttpSessionEvent event) {
        OracleUserDao oracleUserDao = OracleDao.instance().getUserDAO();
        oracleUserDao.setOfflineStatus(String.valueOf(event.getSession().getAttribute("id")));
        activeSessions.decrementAndGet();
    }
}