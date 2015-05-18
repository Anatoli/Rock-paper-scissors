Feature: Extended game: human vs computer

  Scenario Outline:
    Given Extended Human vs Computer game (Paper Rock Scissors Lizard Spock)
    When Human user choice <UserChoice>
    And Computer user choice <ComputerChoice>
    Then The game ends as <Outcome>

    Examples: Human player wins
        | UserChoice | ComputerChoice | Outcome |
        | Lizard     | Spock       | Win     |
        | Spock      | Rock        | Win     |
        | Lizard     | Paper       | Win     |

