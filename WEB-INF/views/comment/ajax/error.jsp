<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/1/15
  Time: 18:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="label label-danger">
    ${errors.getFieldError().getDefaultMessage()}
</div>