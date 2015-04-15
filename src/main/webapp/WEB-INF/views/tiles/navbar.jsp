<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home">SmartSite</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Sign Up</a></li>
                <li><a href="#" class="btn" data-toggle="modal" data-target="#myModal">Login</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Profile
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Messages</a></li>
                        <li><a href="#">Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Log Out</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>SmartSite Login</h3>
            </div>
            <div class="modal-body">
                <form>

                    <div class="form-group">
                        <label for="username" class="control-label">Username</label>
                        <input type="text" class="form-control" id="username"/>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">Password</label>
                        <input class="form-control" id="password"/>
                    </div>

                    <button type="submit" class="btn btn-success">Login</button>
                    <button type="reset" class="btn">Clear</button>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<s:url value="/resources" /> /js/bootstrap.js"></script>

