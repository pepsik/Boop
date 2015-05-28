$('article').readmore({
    speed: 500,
    collapsedHeight: 550,
    moreLink: '<a class="bg-info" href="#">Read more</a>',
    lessLink: '<a class="bg-info" href="#">Close</a>',

    afterToggle: function (trigger, element, expanded) {
        if (!expanded) {
            $('html, body').animate({scrollTop: $(element).offset().top}, {duration: 500});
        }
    }
});