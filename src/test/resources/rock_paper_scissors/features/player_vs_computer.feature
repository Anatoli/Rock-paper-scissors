Feature: Human player can play vs Computer player

  Scenario Outline:
    Given A human player plays vs computer player
    When Human player choose <UserChoice>
    And Computer player choose <ComputerChoice>
    Then The outcome of the game should be <Outcome>

    Examples: Human player wins
        | UserChoice | ComputerChoice | Outcome |
        | Rock       | Scissors       | Win     |
        | Scissors   | Paper          | Win     |
        | Paper      | Rock           | Win     |

    Examples: The game is tied
        | UserChoice | ComputerChoice | Outcome |
        | Rock       | Rock           | Tie     |
        | Scissors   | Scissors       | Tie     |
        | Paper      | Paper          | Tie     |

    Examples: Human player loses
        | UserChoice | ComputerChoice | Outcome |
        | Rock       | Paper          | Lose    |
        | Scissors   | Rock           | Lose    |
        | Paper      | Scissors       | Lose    |