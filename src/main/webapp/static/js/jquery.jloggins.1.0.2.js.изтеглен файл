//****************************************************************************************************************************************************
// Copyright (c) 2012 AbstractLabs
// Forked version by Konrad Gusz (2017)
// https://github.com/gufu/jloggins
//****************************************************************************************************************************************************
(function (jQuery) {
    var regExp = new RegExp(/log\:?(all|debug|info|warn|error)/gi);
    var storage = regExp.exec(localStorage.getItem('jlogginsLevel'));
    jQuery.log =
    {
        __window: null,
        __level: "none",
        __context: new Array(),
        __operation: new Array(),
        level: function (value) {
            if (value != this.__level && (value === 'all' || value === 'debug' || value === 'info' || value === 'warn' || value === 'error' || value === 'none')) {
                localStorage.setItem('jlogginsLevel', 'log:' + value);
                this.__level = value;
                this.write('Logging level is ' + this.__level)
            }
            return this.__level;
        },
        enabled: function (level) {
            var isAll = this.__level === 'all',
                isDebug = this.__level === 'debug',
                isInfo = this.__level === 'info',
                isWarn = this.__level === 'warn',
                isError = this.__level === 'error';
            var result;

            switch (level) {
                case 'debug':
                    result = (isAll || isDebug || isInfo || isWarn || isError);
                    break;
                case 'info':
                    result = (isAll || isInfo || isWarn || isError);
                    break;
                case 'warn':
                    result = (isAll || isWarn || isError);
                    break;
                case 'error':
                    result = (isAll || isError);
                    break;
                default:
                    result = false;
                    break;
            }
            return result;
        },
        context: function (depth) {
            var result = null;
            if (this.context.length > 0 && (typeof (depth) == 'undefined' || depth < this.__context.length)) result = this.__context[this.__context.length - 1];
            return result;
        },
        enter: function (context) {
            this.__context.push(context);
            return this;
        },
        exit: function () {
            if (this.__context.length > 0) this.__context.pop();
            return this;
        },
        start: function (operation) {
            this.__operation.push({name: operation, timestamp: new Date().getTime()});
            this.write('Start ' + operation, '->')
        },
        stop: function () {
            if (this.__operation.length > 0) {
                var operation = this.__operation.pop()
                var elapsed = new Date();
                elapsed.setTime(new Date().getTime() - operation.timestamp);
                this.write('Stop ' + operation.name + ' ' + elapsed.getMinutes() + ':' + elapsed.getSeconds() + ':' + elapsed.getMilliseconds() + ' elapsed', '<-')
            }
        },
        reset: function () {
            this.__context = new Array();
            this.__operations = new Array();
            return this;
        },
        write: function (message, type) {
            if (this.enabled('any')) {
                if (typeof (type) != 'undefined') message = '[' + type + '] ' + message;
                if (typeof (console) == 'object') console.log(message)
                else if (typeof (opara) == 'object') opera.postError(message);
                else {
                    if (this.__window == null) this.__window = window.open();
                    this.__window.document.write(message);
                }
            }
        },
        format: function () {
            var result = (this.__context.length > 0) ? this.__context.join(' ') + ':' : '';
            for (var index = 0; index < arguments.length; index++) {
                if (typeof(arguments[index]) == 'string') {
                    result += arguments[index]
                }
                else if (JSON && JSON.stringify) result += JSON.stringify(arguments[index]);
                else {
                    results += '{';
                    for (p in arguments[index]) result += p + ':' + arguments[index][p]
                    results += '}';
                }
            }
            return result;
        },
        debug: function () {
            if (this.enabled('debug')) {
                var message = this.format.apply(this, arguments);
                if (typeof (console.debug) == 'function') console.debug(message)
                else this.write(message, '*');
            }
        },
        info: function () {
            if (this.enabled('info')) {
                var message = this.format.apply(this, arguments);
                if (typeof (console.info) == 'function') console.info(message)
                else this.write(message, '?');
            }
        },
        warn: function () {
            if (this.enabled('warn')) {
                var message = this.format.apply(this, arguments);
                if (typeof (console.warn) == 'function') console.warn(message)
                else this.write(message, '+');
            }
        },
        error: function () {
            if (this.enabled('error')) {
                var message = this.format.apply(this, arguments);
                if (typeof (console.error) == 'function') console.error(message)
                else this.write(message, '!');
            }
        },
        exception: function (e) {
            if (this.enabled('error')) {
                var message = '';
                if ('fileName' in e) message += e.fileName + ' ';
                if ('lineNumber' in e) message += e.lineNumber + ' ';
                if ('number' in e) message += e.number;
                if (message.length > 0) message += ':';
                message += e.message;
                if ('description' in e) message += '-' + e.description;
            }
        }
    };
    jQuery.fn.log = function (jQuery) {
        var $this = $(this);
        var context = $this.prop('tagName');
        if ($this.attr('id')) context += ' #' + $this.attr('id');
        if ($this.attr('class')) context += ' ' + $this.attr('class');
        return {
            write: function (message, type) {
                $.log.enter(context);
                try {
                    $.log.write.call($._log, message, type);
                }
                finally {
                    $.log.exit();
                }
            }
            ,
            debug: function () {
                $.log.enter(context);
                try {
                    $.log.debug.apply($.log, arguments);
                }
                finally {
                    $.log.exit();
                }
            },
            info: function () {
                $.log.enter(context);
                try {
                    $.log.info.apply($.log, arguments);
                }
                finally {
                    $.log.exit();
                }
            },
            warn: function () {
                $.log.enter(context);
                try {
                    $.log.warn.apply($.log, arguments);
                }
                finally {
                    $.log.exit();
                }
            },
            error: function () {
                $.log.enter(context);
                try {
                    $.log.error.apply($.log, arguments);
                }
                finally {
                    $.log.exit();
                }
            }
        }
    };

    if (storage != null && storage.length > 1) try {
        $.log.level(storage[1])
    } finally {
    }
})(jQuery);