requirejs.config(requirejsConfig);

define(['react', 'util/render-util', 'util/model', 'util/http', 'service/service', 'view/game-board'],
    function(React, RenderUtil, Model, Http, AppService, GameBoard){
    'use strict'

        window.React = React;

        var DEFAULT_GAME_TYPE = 'player_vs_computer'

        var model = new Model(),
            pageEl = document.getElementById('page');

        var serviceHttp = new Http('service', {
            }),
            appService = new AppService(serviceHttp);

        model.set({
            gameType: DEFAULT_GAME_TYPE,
            viewMode: 'settings'
        });

        var render = RenderUtil.makeRenderFunction(function(){
            React.renderComponent(GameBoard({
                model: model,
                appService: appService
            }), pageEl);
        });

        // Whenever the model is changed. render from root
        model.listen('listener', render);

        render();

})