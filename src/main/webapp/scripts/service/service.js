define(['util/promise'], function(promise){
    'use strict';

    var Service = function(serviceHttp) {
        this.http = serviceHttp;
    }

    Service.prototype.evaluateGameInput = function(params) {
        return this.http.post("game/input")
            .data(params)
            .execute()
            .then(function(result){
                return result;
            })
    }

    return Service;
})