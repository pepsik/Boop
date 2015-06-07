## Description

This is my first git project which evolved from simple example Spitter of 'Spring in Action' by Craig Walls.

Started with simple web-application(book example with a few changes) where user can register an account and post messages,
have public profile and avatar img, the project has turned into a portal. It has features such as tags, favorites,
comments to posts, ratings(in p), wysiwyg summernote as editor and others that is under the hood.

This is a training project where I learning some Java frameworks (Spring, Sping MVC, Jpa-Hibernate, Jsp, Apache Tiles) 
and bit js, html, css as front-end of my app. I am learning Java, so my front-end will be very dirty.

##Usage

After gitclone you need to do some steps to startup this project:

0. Edit **data-access.properties** file to set url, username, password variables your mysql db (yeap I'm using mysql, so you need to have it or install from mysql.com website)

0. Change upload path in **file-uploads.props** to your 'uploads' folder where user images will be saved 

0. Install Tomcat 7 as a handler http requests/responses (Im using Tomcat so i dont know what can be used insted)

0. Edit in **Tomcat_folder/conf/server.xml** where you find 
    					
    					
    	<Connector port="80" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" /> 
or something about that and add **URIEncoding="UTF-8"** for supporting cyrillic chars in urls

0. Before you deploying artifact be sure that you set application context url to **"/"** (in some ajax request i could not change absolute to relative url paths, so if you deploy war like that "http:/domenname/abc/myprojecthome" some functions will not work because "abc" will be app context. You can deploy project from intellij idea where deploy settings are simple or if u using native tomcat u need to change **server.xml**)

			
			
