$(function() {
    $('body').css({ 'padding-bottom': $('.footer').outerHeight(true) });

    $(window).on('resize', function () {
        $('body').css({ 'padding-bottom': $('.footer').outerHeight(true) });
    });

    $("#recherche-prix").on("keypress", function (evt) {
        var $txtBox = $(this);
        var charCode = (evt.which) ? evt.which : evt.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 46)
            return false;
        else {
            var len = $txtBox.val().length;
            var index = $txtBox.val().indexOf('.');
            if (index > 0 && charCode == 46) {
                return false;
            }
            if (index > 0) {
                var charAfterdot = (len + 1) - index;
                if (charAfterdot > 3) {
                    return false;
                }
            }
        }
        return $txtBox; //for chaining
    });


    $('.keep-open').on({
        "shown.bs.dropdown": function() {
            $(this).data('closable', false);
        },
        "click": function(event) {
           $(this).data('closable', false);
        },
        "hide.bs.dropdown": function(event) {
            temp = $(this).data('closable');
            $(this).data('closable', true);
            return temp;
        }
    });
})