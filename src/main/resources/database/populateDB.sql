INSERT IGNORE INTO accounts VALUES (1, 'admin', 'Admin Local', '1111', '1976-01-01');
INSERT IGNORE INTO accounts VALUES (2, 'Lux', 'Lux Hilanen', '1234', '1992-02-02');
INSERT IGNORE INTO accounts VALUES (3, 'Jinx', 'Jinx Badgirl', '1234', '1666-06-06');
INSERT IGNORE INTO accounts VALUES (4, 'Ashe', 'Ashe Frozen', '1234', '1990-01-01');


INSERT IGNORE INTO threads VALUES (1, 1, 'First Thread',
                                   'If you use INSERT IGNORE, then the row won''t actually be inserted if it results in a duplicate key. But the statement won''t generate an error. It generates a warning instead.',
                                   '2015-04-09');
INSERT IGNORE INTO threads VALUES (2, 1, '18.4 Velocity & FreeMarker',
                                   'Velocity and FreeMarker are two templating languages that can be used as view technologies within Spring MVC applications. The languages are quite similar and serve similar needs and so are considered together in this section. For semantic and syntactic differences between the two languages, see the FreeMarker web site.',
                                   '2015-04-10');
INSERT IGNORE INTO threads VALUES (3, 1, '18.4.1 Dependencies',
                                   'Your web application will need to include velocity-1.x.x.jar or freemarker-2.x.jar in order to work with Velocity or FreeMarker respectively and commons-collections.jar is required for Velocity. Typically they are included in the WEB-INF/lib folder where they are guaranteed to be found by a Java EE server and added to the classpath for your application. It is of course assumed that you already have the spring-webmvc.jar in your ''WEB-INF/lib'' directory too! If you make use of Spring''s ''dateToolAttribute'' or ''numberToolAttribute'' in your Velocity views, you will also need to include the velocity-tools-generic-1.x.jar',
                                   '2015-04-11');

INSERT IGNORE INTO posts VALUES (1, 1, 1, 'Post1', '1990-02-02');
INSERT IGNORE INTO posts VALUES (2, 1, 1, 'Post2', '1991-02-02');
INSERT IGNORE INTO posts VALUES (3, 1, 1, 'Post3', '1992-02-02');
