define([], function(){
    var Promise = function () {
        this.finished = false;
        this.callbacks = [];
    }

    var _create = function () {
        return new Promise();
    }

    var _bind = function(f, context) {
        return function() {
            return f.apply(context, arguments);
        }
    }

    var _result = function (v) {
        var promise = _create();
        promise.done(v, null);
        return promise;
    }

    var _error = function(e) {
        var promise = _create();
        promise.done(null, e);
        return promise;
    }

    var _lift = function (p) {
        if (p instanceof Promise) {
            return p;
        } else if (p instanceof Function) {
            var prom = _create();
            try {
                prom.resolve(p());
            } catch (e) {
                console.log('function in promise threw exception', e.name, e.message, e.stack);
                prom.reject(e);
            }
            return prom;
        } else {
            return _result(p);
        }
    }

    var _when = function() {
        var args = arguments,
            argLength = args.length,
            promise = _create(),
            noDone = 0,
            errors = [],
            results = [],
            callback,
            keys = [],
            isMap = (argLength == 1 && (typeof(args[0]) == 'object') && !(args[0] instanceof Promise));
        if (isMap) {
            var map = args[0];
            args = [];
            for (var k in map) {
                if (map.hasOwnProperty(k)){
                    args.push(map[k]);
                    keys.push(k);
                }
            }
            argLength = keys.length;
        }

        callback = function(index) {
            return function (result, error) {
                noDone++;
                errors[index] = error;
                results[index] = result;
                if (noDone === argLength) {
                    if (isMap) {
                        var r = {}, e = {};
                        for (var i = 0; i < argLength; ++i) {
                            r[keys[i]] = results[i];
                            e[keys[i]] = errors[i];
                        }
                        promise.done(r, e);
                    } else {
                        promise.done(results, errors);
                    }
                }
            }
        };

        for(var i = argLength-1; i >= 0; --i) {
            _lift(args[i]).then(callback(i));
        }

        return promise;
    }

    Promise.prototype.done = function (result, error) {
        if (!this.finished) {
            this.finished = true;
            this.error = error;
            this.result = result;
            for (var i = this.callbacks.length -1; i >= 0; --i) {
                this.callbacks[i](this.result, this.error);
            }
        } else {
            console.log('cannot call done on promise more than once');
        }
    };

    Promise.prototype.resolve = function (result) {
        this.done(result, undefined);
    };

    Promise.prototype.reject = function (error) {
        this.done(undefined, error);
    };

    Promise.prototype.then = function (f, context) {
        var bound = _bind(f, context);
        if (this.finished) {
            return _lift(bound(this.result, this.error));
        } else {
            var p = _create();
            this.callbacks.push(function(r, e){
                try {
                    var res = bound(r, e);
                    if (res instanceof Promise) {
                        res.then(function (r2, e2){
                            p.done(r2, e2);
                        })
                    } else {
                        p.done(res, undefined);
                    }
                } catch (e) {
                    console.log('function in promise threw exception', e.name, e.message, e.stack, e);
                    p.done(undefined, e);
                }
            });
            return p;
        }
    };

    return {
        Promise: Promise,
        create: _create,
        lift: _lift,
        result: _result,
        error: _error,
        _when: _when
    }

})