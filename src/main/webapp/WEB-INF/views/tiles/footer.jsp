<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/21/15
  Time: 16:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    Language : <a href="?locale=en_US">English</a>|<a href="?locale=uk_UA">Ukraine</a>

    Current Locale : ${pageContext.response.locale}
</div>