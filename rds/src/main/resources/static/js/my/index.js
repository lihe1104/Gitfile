$('.myLeftMenu').click(function (e) {
    var url = $(this).attr('data');
    var resName = $(this).attr('resName');
    $('#listTitle').html(resName)
    $('#container').load(url);
});