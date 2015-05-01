var postComment = function (post_id) {
    var wysiwygNumber = "summernoteEditor" + post_id;
    var editor = $("#" + wysiwygNumber);
    var postMessage = editor.code();
    var json = {text: postMessage};
    $.ajax({
        type: 'POST',
        url: '/post/' + post_id + '/comment',
        dataType: 'html',
        data: JSON.stringify(json),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader("Accept", "text/html");
        },
        success: function (response) {
            $("#response" + post_id).append(response);
            $("#errors" + post_id).empty();
            editor.code("");
        },
        error: function (xhr) {
            $("#errors" + post_id).html(xhr.responseText);
        }
    });
};

var getComments = function (post_id, count) {
    $.ajax({
        type: 'GET',
        url: '/post/' + post_id + '/comments',
        dataType: 'html',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "text/html");
        },
        success: function (response) {
            $("#responseCollapse" + count).html(response);
        }
    });
};

var deleteComment = function (post_id, comment_id) {
    $("#" + comment_id).remove();
    $.ajax({
        type: 'DELETE',
        url: "/post/" + post_id + "/comment/" + comment_id
        //on success
        //on error
    });
};

var editComment = function (comment_id) {
    $('#summernoteComment' + comment_id).summernote({
        height: 100,
        minHeight: 100,
        maxHeight: 200,
        focus: true
    });
    document.getElementById("delete" + comment_id).style.display = 'none';             //refactor repeats
    document.getElementById("edit" + comment_id).style.display = 'none';
    document.getElementById("save" + comment_id).style.display = 'inline-block';
    document.getElementById("cancel" + comment_id).style.display = 'inline-block';
};

var cancelEditing = function (comment_id) {
    $('#summernoteComment' + comment_id).destroy();
    document.getElementById("delete" + comment_id).style.display = 'inline-block';
    document.getElementById("edit" + comment_id).style.display = 'inline-block';
    document.getElementById("save" + comment_id).style.display = 'none';
    document.getElementById("cancel" + comment_id).style.display = 'none';
};

var saveComment = function (post_id, comment_id) {
    var summernote = '#summernoteComment' + comment_id;
    var putMessage = $(summernote).code();
    var json = {text: putMessage};
    $.ajax({
        type: 'PUT',
        url: "/post/" + post_id + "/comment/" + comment_id,
        data: JSON.stringify(json),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function () {
            $(summernote).destroy();
            document.getElementById("save" + comment_id).style.display = 'none';
            document.getElementById("cancel" + comment_id).style.display = 'none';
            document.getElementById("delete" + comment_id).style.display = 'inline-block';
            document.getElementById("edit" + comment_id).style.display = 'inline-block';
            $("#errors" + post_id).empty();
        },
        error: function (xhr) {
            $("#errors" + post_id).html(xhr.responseText);
        }
    });
};
