var removeFavorite = function (post_id, authorizedUser) {
    var json = post_id;
    $.ajax({
        type: 'DELETE',
        url: '/user/' + authorizedUser + '/favorite',
        data: JSON.stringify(json),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-Type", "application/json");
        },

        success: function () {
            var favoriteButton = document.getElementById("favorite" + post_id);
            favoriteButton.className =
                favoriteButton.className.replace(/(?:^|\s)btn-success(?!\S)/g, '');
            favoriteButton.className += " btn-info";

            favoriteButton.firstElementChild.className =
                favoriteButton.firstElementChild.className.replace(/(?:^|\s)glyphicon-ok(?!\S)/g, '');
            favoriteButton.firstElementChild.className += " glyphicon-star";

            $("#favorite" + post_id).attr("onclick", "addFavorite(" + post_id + ", '" + authorizedUser + "')");
        }

        //error: function (xhr) {
        //    $("#errors" + post_id).html(xhr.responseText);
        //}
    });
};

var addFavorite = function (post_id, authorizedUser) {
    var json = post_id;
    $.ajax({
        type: 'POST',
        url: '/user/' + authorizedUser + '/favorite',
        data: JSON.stringify(json),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function () {
            var favoriteButton = document.getElementById("favorite" + post_id);
            favoriteButton.className =
                favoriteButton.className.replace(/(?:^|\s)btn-info(?!\S)/g, '');
            favoriteButton.className += " btn-success";

            favoriteButton.firstElementChild.className =
                favoriteButton.firstElementChild.className.replace(/(?:^|\s)glyphicon-star(?!\S)/g, '');
            favoriteButton.firstElementChild.className += " glyphicon-ok";

            $("#favorite" + post_id).attr("onclick", "removeFavorite(" + post_id + ", '" + authorizedUser + "')");
        }
        //error: function (xhr) {
        //    $("#errors" + post_id).html(xhr.responseText);
        //}
    });
};
