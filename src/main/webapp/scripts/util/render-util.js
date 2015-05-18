define([], function(){
    var raf = window.requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame ||
        window.webkitRequestAnimationFrame || window.msRequestAnimationFrame;

    return {
        makeRenderFunction: function(f) {
            var queueRefresh = false;
            return function () {
                if (!queueRefresh) {
                    queueRefresh = true;
                    raf(function() {
                        queueRefresh = false;
                        f();
                    })
                }
            }
        }
    }
})