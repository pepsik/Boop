package org.pepsik.persistence;

import org.joda.time.DateTime;
import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.*;

/**
 * Created by pepsik on 4/8/15.
 */

@Repository
public class SmartDaoImpl implements SmartDao {

    private List<Thread> threadList;

    public SmartDaoImpl() {
        Thread thread1 = new Thread();
        thread1.setText("Velocity and FreeMarker are two templating languages that can be used as view technologies within Spring MVC applications. The languages are quite similar and serve similar needs and so are considered together in this section. For semantic and syntactic differences between the two languages, see the FreeMarker web site.");
        thread1.setWhen(new DateTime());
        thread1.setId(0L);
        thread1.setTitle("18.4 Velocity & FreeMarker");

        Account account = new Account();
        account.setUserName("lolka");

        thread1.setAccount(account);

        Thread thread2 = new Thread();
        thread2.setText("Your web application will need to include velocity-1.x.x.jar or freemarker-2.x.jar in order to work with Velocity or FreeMarker respectively and commons-collections.jar is required for Velocity. Typically they are included in the WEB-INF/lib folder where they are guaranteed to be found by a Java EE server and added to the classpath for your application. It is of course assumed that you already have the spring-webmvc.jar in your 'WEB-INF/lib' directory too! If you make use of Spring's 'dateToolAttribute' or 'numberToolAttribute' in your Velocity views, you will also need to include the velocity-tools-generic-1.x.jar");
        thread2.setTitle("18.4.1 Dependencies");
        thread2.setId(1L);
        thread2.setWhen(new DateTime(new Date().getTime() - 100000));
        threadList = new LinkedList<>();
        threadList.add(thread1);
        threadList.add(thread2);
    }

    @Override
    public List<Thread> getAllThreads() {
        return threadList;
    }

    @Override
    public List<Thread> getRecentThreads() {
        return null;
    }

    @Override
    public void addAccount(Account account) {

    }

    @Override
    public void addThread(Thread thread) {
        threadList.add(0, thread);
    }

    @Override
    public void addMessage(Post message) {

    }

    @Override
    public Thread getThreadById(long id) {
        return threadList.get((int) id);
    }   //TODO: bad

    @Override
    public Post getMessageById(long id) {
        return null;
    }
}
