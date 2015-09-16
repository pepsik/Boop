$('article').readmore({
    speed: 500,
    collapsedHeight: 750,
    moreLink: '<a class="bg-primary" href="#">&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-down"></span>&nbsp;&nbsp;Read more</a>',
    lessLink: '<a class="bg-primary" href="#">&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-up"></span>&nbsp;&nbsp;Close</a>',

    afterToggle: function (trigger, element, expanded) {
        if (!expanded) {
            $('html, body').animate({scrollTop: $(element).offset().top}, {duration: 400});
        }
    }
});