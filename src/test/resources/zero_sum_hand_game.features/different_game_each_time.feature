Feature: Different Game each time

  Scenario Outline:
    Given User selects <GameType> and <PlayerConfig>
    Then The game instance is of <GameInstance>
    And Player is a <PlayerType>
    And Versus Player is a <VsPlayerType>

  Examples: Converts game config into game instance
    | GameType                     | PlayerConfig       | GameInstance                     | PlayerType             | VsPlayerType   |
    | RockPaperScissors            | HumanVsComputer    | RockPaperScissorsGame            | HumanPlayerInteractive | ComputerPlayer |
    | RockPaperScissors            | ComputerVsComputer | RockPaperScissorsGame            | ComputerPlayer         | ComputerPlayer |
    | RockPaperScissorsLizardSpock | HumanVsComputer    | RockPaperScissorsLizardSpockGame | HumanPlayerInteractive | ComputerPlayer |

