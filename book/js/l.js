function H$(i) {
    return document.getElementById(i)
}

function H$$(c, p) {
    return p.getElementsByTagName(c)
}
var slider = function() {
    function inits(o) {
        this.id = o.id;
        this.at = o.auto ? o.auto : 3;
        this.o = 0;
        this.pos();
    }
    inits.prototype = {
        pos: function() {
            clearInterval(this.__b);
            this.o = 0;
            var el = H$(this.id),
                li = H$$('li', el),
                l = li.length;
            var _t = li[l - 1].offsetHeight;
            var cl = li[l - 1].cloneNode(true);
            cl.style.opacity = 0;
            cl.style.filter = 'alpha(opacity=0)';
            el.insertBefore(cl, el.firstChild);
            el.style.top = -_t + 'px';
            this.anim();
        },
        anim: function() {
            var _this = this;
            this.__a = setInterval(function() {
                _this.animH()
            }, 20);
        },
        animH: function() {
            var _t = parseInt(H$(this.id).style.top),
                _this = this;
            if (_t >= -1) {
                clearInterval(this.__a);
                H$(this.id).style.top = 0;
                var list = H$$('li', H$(this.id));
                H$(this.id).removeChild(list[list.length - 1]);
                this.__c = setInterval(function() {
                    _this.animO()
                }, 20);
                //this.auto();
            } else {
                var __t = Math.abs(_t) - Math.ceil(Math.abs(_t) * .07);
                H$(this.id).style.top = -__t + 'px';
            }
        },
        animO: function() {
            this.o += 2;
            if (this.o == 100) {
                clearInterval(this.__c);
                H$$('li', H$(this.id))[0].style.opacity = 1;
                H$$('li', H$(this.id))[0].style.filter = 'alpha(opacity=100)';
                this.auto();
            } else {
                H$$('li', H$(this.id))[0].style.opacity = this.o / 100;
                H$$('li', H$(this.id))[0].style.filter = 'alpha(opacity=' + this.o + ')';
            }
        },
        auto: function() {
            var _this = this;
            this.__b = setInterval(function() {
                _this.pos()
            }, this.at * 1000);
        }
    }
    return inits;
}();
$(function() {
    $('.index-focus').slidesjs({
        width: 535,
        height: 220,
        navigation: false,
        play: {
            active: true,
            auto: true,
            interval: 4000,
            swap: true,
            pauseOnHover: true,
            restartDelay: 2500
        }
    })

    $('.history-list-del').live('click', function(event) {

        if ($(this).closest('ul').find('.history-list-item').length == 1) {
            $(this).parent().fadeOut('slow', function() {
                $(this).remove()
            });
            $(this).closest('.history').fadeOut('slow', function() {
                $(this).remove()
            });
            $(this).closest('.ajax-content').fadeOut('slow');
        } else {
            $(this).parent().fadeOut('slow', function() {
                $(this).remove()
            });
        }
        $(this).closest('.sr_item').fadeOut('slow', function() {
            $(this).remove()
        });

    });
    $('.add_or_edit').click(function(event) {
        $(".user-center-right").find(".settings_user_data").show();
        $(".user-center-right").find(".settings_user").hide();
        $(this).closest('.settings_user_data').hide();
        $(this).closest('.setting-item').find('.settings_user').show()
     });
    $('.cancel_action').live("click",function(event) {
        $(this).closest('.setting-item').find('.settings_user_data').show()
        $(this).closest('.setting-item').find('.settings_user').hide()
     });
    $('.history-pup-close').live('click', function(event) {
        $(this).closest('.ajax-content').fadeOut('slow');
    });
    $('.history-del').live('click', function(event) {
        $(this).parent().fadeOut('slow', function() {
            $(this).remove()
        });

    });
    $('.input-wrapper input[type="text"]').focus(function(event) {
        $(this).parent().find('.input-pop-dest').show()
        $(this).parent().find('.input-icon-arrow').text('▾')
    });
    $('.input-wrapper input[type="text"],.input-wrapper .input-icon').mouseup(function(event) {
        return false
    });
    $('body').mouseup(function(event) {
        $('.input-pop-dest').hide()
        $('.input-wrapper .input-icon-arrow').text('▸')
    });
    $('.input-wrapper .input-icon').click(function(event) {
        if ($('.input-pop-dest').is(':visible')) {
            $(this).parent().find('.input-pop-dest').hide()
            $(this).find('.input-icon-arrow').text('▸')
        } else {
            $(this).parent().find('.input-pop-dest').show()
            $(this).find('.input-icon-arrow').text('▾')
        }
    });
    $('.advanced-search-title').click(function(event) {
        event.preventDefault();
        if ($('.advanced-search-content').is(':visible')) {
            $(this).parent().find('.advanced-search-content').slideUp();
            $('.advanced-search-icon').removeClass('advanced-search-close')
        } else {
            $(this).parent().find('.advanced-search-content').slideDown();
            $('.advanced-search-icon').addClass('advanced-search-close')
        }
    })
   $('.tab-nav-item').live('hover',function() {
        var _index = $(this).index();
        $(this).addClass('tab-nav-item-active').siblings().removeClass('tab-nav-item-active')
        var _parent = $(this).closest('.destination-section')
        _parent.find('.tab-panel').eq(_index).show().siblings().hide()
    });


    $('[data-trigger="select"]').select2();
    $('.tips-login-del').live('click', function(event) {
        $('.tips-login').fadeOut()
    });
    $('.loginIn').live('click',function(event) {
        $target = $(this).closest('.nav-item').find('.login-user-content')
        if ($target.is(':visible')) {
            $target.hide();
        } else {
            $target.show();
        }
    });
    $(document).click(function(event) {
        var $target = $(event.target)
        if ($target.closest('.nav-item').find('.login-user-content').length == 0) {
            $('.login-user-content').hide()
        }

    });
    $('.JS-scroll a').click(function(e) {
        e.preventDefault()
        $(this).closest('.JS-scroll').find('a').removeClass('on');
        $(this).addClass("on");
        var top = $($(this).attr("href")).offset().top-55
        $("html, body").animate({
            scrollTop: top + 'px'
        }, {
            duration: 1000,
            easing: "swing"
        });

    });

});
