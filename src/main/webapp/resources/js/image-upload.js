$(function () {

    $('#imageForm').submit(function (e) {
        e.preventDefault();
        // Move cropped image data to hidden input
        var imageData = $('.image-editor').cropit('export');
        $('.hidden-image-data').val(imageData);

        // Print HTTP request params
        var blob = dataURItoBlob(imageData);
        var formData = new FormData(document.forms[0]);
        formData.append("image", blob);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/user/avatar");
        xhr.send(formData);

        return false;
    });
});

function dataURItoBlob(dataURI) {
    var binary = atob(dataURI.split(',')[1]);
    var array = [];
    for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i));
    }
    return new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
}