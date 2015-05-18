define(['jquery', 'underscore', 'react'],
    function($, _, React){
        var dom = React.DOM;

        var SETTINGS_MODE = 'settings', PRE_PLAY_MODE = 'pre_play', PLAY_MODE = 'play', RESULTS_MODE = 'results',
            RPS_GESTURES = ['Rock', 'Paper', 'Scissors'], RPSLS_GESTURES =  ['Rock', 'Paper', 'Scissors', 'Lizard', 'Spock'],
            ROCK_PAPER_SCISSORS = 'RockPaperScissors', ROCK_PAPER_SCISSORS_LIZARD_SPOCK = 'RockPaperScissorsLizardSpock',
            HUM_VS_COMP = 'HumanVsComputer', COMP_VS_COM = 'ComputerVsComputer';

        var getGesturesBy = function(gameType) {
            return gameType == ROCK_PAPER_SCISSORS ? RPS_GESTURES : RPSLS_GESTURES;
        }

        var getMatchReport = function(result, user1, user2) {
            return 'Match ' + result.outcome
                + ', ' + user1 + ' choice: [' + result.player1Choice + ']'
                + ' vs '
                + user2 + ' choice: [' + result.player2Choice + ']'
        }

        var getGameReport = function(isInteractive, player1WinCount, player2WinCount) {
            var winner = isInteractive ? (player1WinCount == 3 ? 'User' : 'Computer') :
                                         (player1WinCount == 3 ? 'Computer 1' : 'Computer 2')
            return 'Game over, WON ' + winner;
        }

        return React.createClass({
            getInitialState: function () {
                return {
                    viewMode: 'settings',
                    gameType: ROCK_PAPER_SCISSORS,
                    playerConfig: HUM_VS_COMP,
                    userChoice: _.first(RPS_GESTURES),
                    waitingMode: false,
                    player1WinCount: 0,
                    player2WinCount: 0,
                    throwCount: 0
                }
            },

            render: function() {
                var self = this,
                    props = self.props,
                    appService = props.appService,
                    model = props.model,
                    viewMode = self.state.viewMode,
                    gameType = self.state.gameType,
                    playerConfig = self.state.playerConfig,
                    userChoice = self.state.userChoice;

                if (viewMode === SETTINGS_MODE) {
                    return dom.div({className: 'view-container'},
                        dom.label({className: 'page-title'}, 'Game settings'),
                        dom.p({}),
                        dom.label({className: 'checkbox'}, 'Game type:'),
                        dom.div({className: 'btn-group', 'data-toggle': 'buttons'},
                            dom.label({className: 'btn btn-primary ' + (gameType == ROCK_PAPER_SCISSORS ? 'active' : '')},
                                    dom.input({type: 'radio', name: 'options', key: ROCK_PAPER_SCISSORS,
                                        onClick: function(){
                                            self.setState({gameType: ROCK_PAPER_SCISSORS})
                                        }},
                                        'Rock Paper Scissors')),
                            dom.label({className: 'btn btn-primary ' + (gameType == ROCK_PAPER_SCISSORS_LIZARD_SPOCK ? 'active' : '')},
                                    dom.input({type: 'radio', name: 'options', key: ROCK_PAPER_SCISSORS_LIZARD_SPOCK,
                                        onClick: function() {
                                            self.setState({gameType: ROCK_PAPER_SCISSORS_LIZARD_SPOCK})
                                        }},
                                        'Rock Paper Scissors Lizard Spock'))
                        ),
                        dom.p({}),
                        dom.label({className: 'checkbox'}, 'Who plays:'),
                        dom.div({className: 'btn-group', 'data-toggle': 'buttons'},
                            dom.label({className: 'btn btn-primary ' + (playerConfig == HUM_VS_COMP ? 'active' : '')},
                                    dom.input({type: 'radio', name: 'options', key: HUM_VS_COMP,
                                        onClick: function(){
                                            self.setState({playerConfig: HUM_VS_COMP})
                                        }},
                                        'Human vs Computer')),
                            dom.label({className: 'btn btn-primary ' + (playerConfig == COMP_VS_COM ? 'active' : '')},
                                    dom.input({type: 'radio', name: 'options', key: COMP_VS_COM,
                                        onClick: function(){
                                            self.setState({playerConfig: COMP_VS_COM})
                                        }},
                                        'Computer vs Computer'))
                        ),
                        dom.p({}),
                        dom.button({className: 'btn btn-default', type: 'button', onClick: function(){
                            self.setState({viewMode: PRE_PLAY_MODE})
                        }}, 'Play')
                    )
                }

                var gameLabel = 'Game (' + (gameType == ROCK_PAPER_SCISSORS ? 'Rock Paper Scissors' : 'Rock Paper Scissors Lizard Spock')
                                + '): '
                                + (playerConfig == HUM_VS_COMP ? 'User vs Computer' : 'Computer vs Computer');
                if (viewMode === PRE_PLAY_MODE) {
                    return dom.div({className: 'view-container'},
                        dom.label({className: 'page-title'}, gameLabel),
                        dom.p({}),

                        dom.button({className: 'btn btn-default', type: 'button', onClick: function(){
                            self.setState({
                                viewMode: PLAY_MODE,
                                waitingMode: true
                            })
                        }}, 'Begin'),
                        dom.button({className: 'btn btn-default', type: 'button', onClick: function(){
                            self.setState({
                                player1WinCount: 0,
                                player2WinCount: 0,
                                throwCount: 0,
                                viewMode: SETTINGS_MODE
                            })
                        }}, 'Quit')
                    )

                }

                var gestureCollection = function(hideActive, readonly) {
                    return _.reduce(getGesturesBy(gameType), function(memo, gesture, i){
                            var active = gesture == userChoice ? 'active' : '';
                            if (hideActive) active = ''
                            memo.push(dom.label({className: 'btn btn-primary ' + active, key: gesture + '_label'},
                                              dom.input({type: 'radio', name: 'options', key: gesture,
                                                onClick: function(){
                                                    if (readonly) return;
                                                    self.setState({
                                                        userChoice: gesture,
                                                        waitingMode: false})
                                                }},
                                              gesture)));
                            return memo;
                        }, [])
                }

                var stats = 'Throws: ' + self.state.throwCount
                    + ', player1 wins: ' + self.state.player1WinCount
                    + ', player2 wins: ' + self.state.player2WinCount;

                if (viewMode === PLAY_MODE) {
                    var isInteractive = playerConfig === HUM_VS_COMP,
                        gestureCollection = gestureCollection(!isInteractive, !isInteractive);

                    if (self.state.waitingMode) {
                        var timeout = isInteractive ? 3000 : 2000;
                        setTimeout(function() {
                            var gameInput = {
                                gameType: self.state.gameType,
                                playerConfig: self.state.playerConfig
                            }
                            if (isInteractive) {
                                gameInput['userChoice'] = self.state.userChoice
                            }
                            appService.evaluateGameInput(gameInput).then(function(result){
                                var player1WinCount = self.state.player1WinCount,
                                    player2WinCount = self.state.player2WinCount,
                                    throwCount = self.state.throwCount;
                                if (result.outcome === 'WIN') player1WinCount++;
                                if (result.outcome === 'LOSE') player2WinCount++;
                                throwCount++;
                                self.setState({
                                    waitingMode: false,
                                    result: result,
                                    player1WinCount: player1WinCount,
                                    player2WinCount: player2WinCount,
                                    throwCount: throwCount,
                                    viewMode: RESULTS_MODE
                                })
                            });
                        }, timeout);
                    }

                    return dom.div({className: 'view-container'},
                        dom.label({className: 'page-title'}, gameLabel),
                        dom.p({}),
                        dom.label({}, isInteractive ? 'You have 3 seconds to choose your throw' : 'Computer makes a random throw...'),
                        dom.p({}),

                        dom.div({className: 'btn-group', 'data-toggle': 'buttons'}, gestureCollection),
                        dom.div({className: 'stats'}, stats)
                    )
                }

                if (viewMode === RESULTS_MODE) {
                    var isInteractive = playerConfig === HUM_VS_COMP,
                        gestureCollection = gestureCollection(isInteractive ? false : true, true),
                        result = self.state.result,
                        matchReport = isInteractive ? getMatchReport(result, 'User', 'Computer') :
                                                      getMatchReport(result, 'Computer1', 'Computer2'),
                        gameOver = self.state.player1WinCount == 3 || self.state.player2WinCount == 3,
                        gameReport = gameOver ? getGameReport(isInteractive, self.state.player1WinCount, self.state.player2WinCount) : '';


                    return dom.div({className: 'view-container'},
                        dom.label({className: 'page-title'}, gameLabel),
                        dom.p({}),
                        dom.label({}, matchReport),
                        dom.p({}),
                        dom.label({}, gameReport),
                        dom.p({}),

                        dom.div({className: 'btn-group', 'data-toggle': 'buttons'}, gestureCollection),

                        dom.p({}),
                        gameOver ?
                            dom.button({className: 'btn btn-default', type: 'button', onClick: function(){
                                self.setState({
                                    player1WinCount: 0,
                                    player2WinCount: 0,
                                    throwCount: 0,
                                    viewMode: PRE_PLAY_MODE,
                                    waitingMode: false})
                            }}, 'New game') :
                            dom.button({className: 'btn btn-default', type: 'button', onClick: function(){
                                self.setState({viewMode: PLAY_MODE, waitingMode: true})
                            }}, 'Next throw'),
                        dom.button({className: 'btn btn-default', type: 'button', onClick: function(){
                            self.setState({
                                player1WinCount: 0,
                                player2WinCount: 0,
                                throwCount: 0,
                                viewMode: SETTINGS_MODE
                            })
                        }}, 'Quit'),
                        dom.div({className: 'stats'}, stats)
                    )
                }
            },

            componentDidMount: function() {
                var self = this;
            }

        })

    })