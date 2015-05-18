(function() {
    window.requirejsConfig = {
        catchError: true,
        baseUrl: 'scripts',
        paths: {
            'jquery': 'lib/jquery/jquery',
            'underscore': 'lib/underscore/underscore',
            'mori': 'lib/mori/mori',
            'bootstrap': 'lib/bootstrap/js/bootstrap',
            'react': 'lib/react/react'
        },
        shim: {
            'underscore': {exports: '_'},
            'bootstrap': {deps: ['jquery']}
        }
    }
})();
