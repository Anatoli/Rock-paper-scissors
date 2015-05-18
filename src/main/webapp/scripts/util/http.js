define(['underscore', 'jquery', 'util/promise'], function(_, $, promise){

    var Request = function (method, url, options) {
        this.method = method;
        this.url = url;
        this.options = options;
        this.type = 'application/json';
        this.headers = {}
    }

    var Builder = function (base, options) {
        this.base = base;
        this.options = options;
        if (this.base && this.base.endsWith("/")) {
            this.base = this.base.substring(0, this.base.length - 1);
        }
    }

    if (!String.prototype.endsWith) {
        Object.defineProperty(String.prototype, 'endsWith', {
            value: function(searchString, position) {
                var subjectString = this.toString();
                if (position === undefined || position > subjectString.length) {
                    position = subjectString.length;
                }
                position -= searchString.length;
                var lastIndex = subjectString.indexOf(searchString, position);
                return lastIndex !== -1 && lastIndex === position;
            }
        });
    }

    _.extend(Builder.prototype, {
        method: function (method, url) {
            var finalUrl = url;
            if (this.base) {
                finalUrl = this.base + (url ? ('/' + url) : '')
            }
            return new Request(method, finalUrl, this.options);
        },
        get: function (url) {
            return this.method('get', url);
        },
        post: function (url) {
            return this.method('post', url);
        },
        head: function (url) {
            return this.method('head', url);
        },
        put: function (url) {
            return this.method('put', url);
        },
        delete: function (url) {
            return this.method('delete', url);
        }
    })

    _.extend(Request.prototype, {
        header: function (key, value) {
            this.headers[key] = value;
            return this;
        },
        extras: function(extra) {
            this.extra = extra;
            return this;
        },
        data: function(data) {
            this.data = data;
            return this;
        },
        execute: function() {
            var p = promise.create(),
                defaultHeaders = (this.options && this.options.defaultHeaders) || {};
            $.ajax(_.extend({
                url: this.url,
                type: this.method,
                data: this.data,
                headers: _.extend({}, defaultHeaders, this.headers),
                success: function (result) {
                    p.resolve(result);
                },
                error: function(error) {
                    p.reject(error);
                },
                xhrFields: {
                    withCredentials: true
                }
            }, this.extra || {}));
            return p;
        }
    })

    return Builder;
})