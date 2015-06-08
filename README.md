## Description

This is my first git project which evolved from simple example Spitter of 'Spring in Action' by Craig Walls.

Started with simple web-application(book example with a few changes) where user can register an account and post messages,
have public profile and avatar img, the project has turned into a portal. It has features such as tags, favorites,
comments to posts, ratings(in p), wysiwyg summernote as editor and others.

This is a training project where I learn some Java frameworks (Spring, Sping MVC, Jpa-Hibernate, Jsp, Apache Tiles) 
and bit js, html, css as front-end of my app. I am learning Java, so my front-end will be very dirty.

##Usage

After gitclone you need to do some steps to start this project up:

0. Edit **data-access.properties** file to set url, username, password variables your mysql db (I'm using mysql, so you need to have it installed or download and install it from mysql.com website)

0. Change upload path in **file-uploads.props** to your 'uploads' folder where user images will be saved 

0. Install Tomcat 7 as a handler http requests/responses (Im using Tomcat so i dont know what can be used instead)

0. Edit in **Tomcat_folder/conf/server.xml** where you find 
    					
    	<Connector port="80" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" /> 

and add **URIEncoding="UTF-8"** for supporting cyrillic chars in urls
			
>login - **admin**
>pswd - **1111**
		
Recommended using Chrome browser
