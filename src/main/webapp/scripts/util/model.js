define(['mori', 'underscore'], function(mori, _) {
    var EMPTY_ARRAY = [],
        Model = function (parent, path) {
            this.parent = parent;
            this.path = path;
            this.data = mori.hashMap();
            this.listeners = {};
        },

        asArray = function(o) {
            return o instanceof Array ? o : [o];
        },

        foreach = function(o, f) {
            for (var k in o) {
                if (o.hasOwnProperty(k)) {
                    f(o[k], k);
                }
            }
        };

    _.extend(Model.prototype, {
        pathFor: function(path){
            if (this.parent && this.path) {
                return [].concat(thia.path).concat(asArray(path))
            }
        },

        listen: function (id, listener) {
            this.listeners[id] = listener;
        },

        unlisten: function (id) {
            delete this.listeners[id]
        },

        update: function (path, f) {
            var vec = asArray(path);
            if (!this.parent) {
                this.data = mori.updateIn(this.data, vec, f);
            } else {
                this.parent.update(this.pathFor(path), f);
            }
            foreach(this.listeners, function(l){
                l.call(null, vec, this.get(vec))
            }.bind(this));
        },

        set: function(path, v) {
            var vec = asArray(path);
            if(!this.parent) {
                if (typeof path == 'object' && !(path instanceof Array)) {
                    foreach(path, function(value, key){
                        this.data = mori.assoc(this.data, key, value);
                    }.bind(this));
                    foreach(this.listeners, function(l){
                        l.call(null, EMPTY_ARRAY, v);
                    }.bind(this));
                } else {
                    this.update(path, mori.constantly(v));
                }
            } else {
                this.parent.set(this.pathFor(path), v);
            }
        },

        get: function(path) {
            if (!this.parent) {
                return mori.getIn(this.data, asArray(path));
            } else {
                return this.parent.get(this.pathFor(path));
            }
        }
    })

    return Model;
})