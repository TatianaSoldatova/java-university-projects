import java.util.Scanner;

/**
 * The Battleship program allows you to play a simplified single-player version of the Battleship game.
 * No OOP, no arrays permitted
 * @author tatiana soldatova
 * @version 2025
 */
public class BattleshipMain {
    static Scanner input = new Scanner(System.in);

    // ------------
    // CONSTANTS
    // ------------

    // ANSI colors
    public final static String ANSI_RESET           = "\u001B[0m";
    public final static String ANSI_RED             = "\u001B[31m";
    public final static String ANSI_BLUE            = "\u001B[94m";
    public final static String ANSI_YELLOW          = "\u001B[33m";
    public final static String ANSI_GREEN           = "\u001B[92m";

    public final static String PRESENTATION         = """
                                                       ___  ____ ___ ___ _    ____ ____ _  _ _ ___\s
                                                       |__] |__|  |   |  |    |___ [__  |__| | |__]
                                                       |__] |  |  |   |  |___ |___ ___] |  | | |  \s
                                                       
                                                       """;

    public final static String MSG_MAIN_MENU        = """
                                                         
                                                         DIFFICULTY LEVEL :
                                                         1. Beginner (45 ammo)
                                                         2. Intermediate (35 ammo)
                                                         3. Expert (25 ammo)
                                                         
                                                         Enter your choice (1, 2 or 3) :\s""";

    public final static String EXIT_THE_GAME        = "no";
    public final static String CONTINUE_THE_GAME    = "yes";
    public final static String MSG_ASK_REPLAY       = "\nDo you want to play again (yes or no): ";
    public final static String MSG_ASK_COORDS       = "\nEnter the coordinates of the next draw (X,Y): ";
    public final static String MSG_END_OF_PROGRAM   = "\nEnd of program";

    public final static String MSG_ERR_MENU         = "\nERROR! Invalid choice! Try again!";
    public final static String MSG_ERR_REPLAY       = "\nERROR! You should answer yes or no... Try again!";
    public final static String MSG_ERR_COORDS       = "\nERROR! Invalid coordinates... Try again!";

    public final static String MSG_SHOT_SUCCESS     = "\n\n----------> SHOT WAS SUCCESSFUL ! <----------\n";
    public final static String MSG_SHOT_MISSED      = "\n\n----------> SHOT MISSED ! <----------\n";
    public final static String MSG_SHOT_REDUNDANT   = "\n\n----------> SHOT REDUNDANT ! <-------\n";

    public final static String MSG_BATTLESHIP_SUNK  = "\t       * BATTLESHIP SUNK! *\n";
    public final static String MSG_CRUISER_SUNK     = "\t       * CRUISER SUNK! *\n";
    public final static String MSG_SUBMARINE_SUNK   = "\t       * SUBMARINE SUNK! *\n";
    public final static String MSG_DESTROYER_SUNK   = "\t       * DESTROYER SUNK! *\n";

    public final static String BATTLESHIP_TYPE      = "Battleship";
    public final static String CRUISER_TYPE         = "Cruiser";
    public final static String SUBMARINE_TYPE       = "Submarine";
    public final static String DESTROYER_TYPE       = "Destroyer";

    public final static String MSG_VICTORY          = """
            
                                                         WELL DONE! You have destroyed the enemy fleet!
                                                         The used ammunition:\s""";
    public final static String MSG_FAILURE          = """
            
                                                         SORRY! You have run out of ammunition!
                                                         Number of ships sunk:\s""";

    public final static int AMMO_BEGINNER           = 45;
    public final static int AMMO_INTERMEDIATE       = 35;
    public final static int AMMO_EXPERT             = 25;

    public final static int NBR_LINES_GRID          = 8;
    public final static int NBR_COLUMNS_GRID        = 8;
    public final static int GRID_SIZE               = NBR_LINES_GRID * NBR_COLUMNS_GRID;

    // --------
    // METHODS
    // --------
    /**
     * Displays a message without new line.
     * @param message (message to display)
     */
    public static void displayMessage (String message) {
        System.out.print(message);
    }

    /**
     * Displays a message with a new line.
     * @param message (message to display)
     */
    public static void displayMessageLn (String message) {
        System.out.println(message);
    }

    /**
     * Display the name of the game
     * @param msgPresent (presentation message to display)
     */
    public static void presentTheGame(String msgPresent) {
        System.out.println();
        displayMessageLn(ANSI_BLUE + msgPresent + ANSI_RESET);
        UserInputUtils.readPressToContinue(input);
    }

    /**
     * Validates the player's choice to replay and returns her/his response
     */
    public static String newGameQuery() {
        String answer;
        do{
            answer = UserInputUtils.readString(input, MSG_ASK_REPLAY);

            if(!answer.equalsIgnoreCase(EXIT_THE_GAME) && !answer.equalsIgnoreCase(CONTINUE_THE_GAME)){
                displayMessageLn(MSG_ERR_REPLAY);
            }
        }while(!answer.equalsIgnoreCase(EXIT_THE_GAME) && !answer.equalsIgnoreCase(CONTINUE_THE_GAME));
        return answer;
    }

    /**
     * Determines whether a ship has sunk and returns the type of the sunk ship.
     *
     * @param solutionGrid the solution grid of the current game
     * @param playerGrid the player's grid of the current game
     * @param index the index of the shot in the grid
     */
    public static String determineShipDamage(String solutionGrid, String playerGrid, int index){
        boolean checkLeftCells      = false;
        boolean checkRightCells     = false;
        boolean checkUpCells        = false;
        boolean checkBottomCells    = false;
        boolean exitLoop            = false;

        int startLigneNb;
        int startColumnNb;

        int shipDamage = 0;
        int i; // index

        String sunkShip = " ";

        // Possible cases :
        // solutionGrid      playerGrid
        //       'B'     &&      'B'         ==> player made a hit ==> shipDamage++ ==> continue the loop
        //       'B'     &&      ' '         ==> player didn't make a hit at this index ==> exit the loop (no sunk ship)
        //       ' '     &&     (' ' || 'X') ==> end of the ship ==> player went through all the ship's cells on this side ==> checkCells = true

        // For the left/right check : i/NBR_LINES_GRID must be >= 0 ou < GRID_SIZE

        // LEFT side case : Check the cells to the left of the current cell
        // (the cell at the previous index of the current index in the grid)
        i = index - 1;
        startLigneNb = index / NBR_LINES_GRID;
        exitLoop = false;

        if(i < 0 || (i / NBR_LINES_GRID) < startLigneNb){
            checkLeftCells = true;
        }
        else{
            // Ensure i >= 0 and i didn't change the line
            while(!exitLoop && i >= 0 && (i / NBR_LINES_GRID) == startLigneNb){
                // case 1: we get at the end of the ship on the left
                if(solutionGrid.charAt(i) == ' ' && (playerGrid.charAt(i) == ' ' || playerGrid.charAt(i) == 'X')){
                    checkLeftCells = true;
                    exitLoop = true;
                }
                else{
                    // case 2: The player hasn’t hit the target at this index yet, so we exit the loop
                    if(solutionGrid.charAt(i) == 'B' && playerGrid.charAt(i) == ' '){
                        exitLoop = true;
                    }
                    // case 3: The player has already hit the target at this index, so we continue the loop
                    else if(solutionGrid.charAt(i) == 'B' && playerGrid.charAt(i) == 'B'){
                        shipDamage++;
                        i--;

                        if(i < 0 || (i / NBR_LINES_GRID) < startLigneNb){
                            checkLeftCells = true;
                            exitLoop = true;
                        }
                    }
                }
            }
        }

        // RIGHT side case : Check the cells to the right of the current cell
        // (the cell at the next index of the current index in the grid)
        i = index + 1;
        startLigneNb = index / NBR_LINES_GRID;
        exitLoop = false;

        if(i > GRID_SIZE || (i / NBR_LINES_GRID) > startLigneNb){
            checkRightCells = true;
        }
        else{
            //Ensure i <= 64 (grid size) and i didn't change the line
            while(!exitLoop && i <= GRID_SIZE && (i / NBR_LINES_GRID) == startLigneNb){
                // case 1: we get at the end of the ship on the right
                if(solutionGrid.charAt(i) == ' ' && (playerGrid.charAt(i) == ' ' || playerGrid.charAt(i) == 'X')){
                    checkRightCells = true;
                    exitLoop = true;
                }
                else{
                    // case 2: The player hasn’t hit the target at this index yet, so we exit the loop
                    if(solutionGrid.charAt(i) == 'B' && playerGrid.charAt(i) == ' '){
                        exitLoop = true;
                    }
                    // case 3: The player has already hit the target at this index, so we continue the loop
                    else if(solutionGrid.charAt(i) == 'B' && playerGrid.charAt(i) == 'B'){
                        shipDamage++;
                        i++;

                        if(i > GRID_SIZE || (i / NBR_LINES_GRID) > startLigneNb){
                            checkRightCells = true;
                            exitLoop = true;
                        }
                    }
                }
            }
        }

        // UP case : Check the cells above the current cell
        // (the cell above the current index in the grid)
        i = index - 8;
        exitLoop = false;

        if(i < 0){
            checkUpCells = true;
        }
        else{
            // Ensure i >= 0
            while(!exitLoop && i >= 0){
                // case 1: we get at the end of the ship
                if(solutionGrid.charAt(i) == ' ' && (playerGrid.charAt(i) == ' ' || playerGrid.charAt(i) == 'X')){
                    checkUpCells = true;
                    exitLoop = true;
                }
                else{
                    // case 2: The player hasn’t hit the target at this index yet, so we exit the loop
                    if(solutionGrid.charAt(i) == 'B' && playerGrid.charAt(i) == ' '){
                        exitLoop = true;
                    }
                    // case 3: The player has already hit the target at this index, so we continue the loop
                    else if(solutionGrid.charAt(i) == 'B' && playerGrid.charAt(i) == 'B'){
                        shipDamage++;
                        i -= 8;

                        // Check if i is not out of the grid bounds
                        if(i < 0){
                            checkUpCells = true;
                            exitLoop = true;
                        }
                    }
                }
            }
        }

        // BOTTOM case : Check the cells under the current cell
        // (the cell under the current index in the grid)
        i = index + 8;
        exitLoop = false;

        if(i >= GRID_SIZE){
            checkBottomCells = true;
        }
        else{
            // Ensure i <= 64 (grid size)
            while(!exitLoop && i < GRID_SIZE){
                // case 1: we get at the end of the ship
                if(solutionGrid.charAt(i) == ' ' && (playerGrid.charAt(i) == ' ' || playerGrid.charAt(i) == 'X')){
                    checkBottomCells = true;
                    exitLoop = true;
                }
                else{
                    // case 2: The player hasn’t hit the target at this index yet, so we exit the loop
                    if(solutionGrid.charAt(i) == 'B' && playerGrid.charAt(i) == ' '){
                        exitLoop = true;
                    }
                    // case 3: The player has already hit the target at this index, so we continue the loop
                    else if(solutionGrid.charAt(i) == 'B' && playerGrid.charAt(i) == 'B'){
                        shipDamage++;
                        i += 8;

                        // Check if i is not out of the grid bounds
                        if(i >= GRID_SIZE){
                            checkBottomCells = true;
                            exitLoop = true;
                        }
                    }
                }
            }
        }

        // Add damage for the current index
        shipDamage += 1;
        // Display sunk ship and its size
        if(checkLeftCells && checkRightCells && checkUpCells && checkBottomCells){
            if(shipDamage >= 2 && shipDamage <= 5){
                displaySunkenShip(shipDamage);
                sunkShip = determineTypeOfSunkShip(shipDamage);
            }
        }
        return sunkShip;
    }

    /**
     * Displays player's grid
     * @param ammo number of ammunition
     * @param grid player's grid
     * @param sunkShips all the ships that the player shot down
     */
    public static void displayPlayersGrid(int ammo, String grid, String sunkShips){
        JeuUtils.afficherGrille(grid);
        displayMessageLn("\nAVAILABLE AMMUNITION : " + ammo);
        displayMessage("SUNK SHIPS: ");
        if(!sunkShips.isEmpty()){
            System.out.println(sunkShips);
        } else{
            System.out.println();
        }
    }

    /**
     * Validates the format of the player's shot coordinates and returns the valid coordinates.
     * @param coords shot coordinates
     */
    public static String checkCoordinatesInput(String coords){
        boolean validCoords = false;
        if(coords != null){
            do{
                coords = UserInputUtils.readString(input, MSG_ASK_COORDS);
                validCoords = (coords.length() == 3) && (coords.charAt(1) == ',')
                        && (coords.charAt(0) >= '0') && (coords.charAt(0) <= '7')
                        && (coords.charAt(2) >= '0') && (coords.charAt(2) <= '7');

                if(!validCoords){
                    displayMessage(MSG_ERR_COORDS);
                }
            }while(!validCoords);
        }
        return coords;
    }

    /**
     * Converts coordinates of type String into an index of type integer.
     * @param coords shot coordinates
     */
    public static int convertCoordsIntoIndex(String coords){
        int lineNbr;
        int columnNbr;

        //Convert coords into substrings line and column
        String line = coords.substring(0,1);
        String column = coords.substring(2);

        lineNbr = Integer.parseInt(line);
        columnNbr = Integer.parseInt(column);

        return lineNbr * NBR_LINES_GRID + columnNbr;
    }

    /**
     * Displays the type of sunken ship above the player grid
     * @param damage the number of damages used to identify the sunken ship
     */
    public static void displaySunkenShip(int damage){
        switch(damage){
            case 5:
                displayMessageLn(ANSI_YELLOW + MSG_BATTLESHIP_SUNK + ANSI_RESET);
                break;
            case 4:
                displayMessageLn(ANSI_YELLOW + MSG_CRUISER_SUNK + ANSI_RESET);
                break;
            case 3:
                displayMessageLn(ANSI_YELLOW + MSG_SUBMARINE_SUNK  + ANSI_RESET);
                break;
            case 2:
                displayMessageLn(ANSI_YELLOW + MSG_DESTROYER_SUNK  + ANSI_RESET);
                break;
            default:
                break;
        }
    }

    /**
     * Determine what type of ship was sunk and return its name
     * @param damage number of damage to identify the sunk ship
     */
    public static String determineTypeOfSunkShip(int damage){
        return switch (damage) {
            case 5 -> BATTLESHIP_TYPE;
            case 4 -> CRUISER_TYPE;
            case 3 -> SUBMARINE_TYPE;
            case 2 -> DESTROYER_TYPE;
            default -> "No ship was sunk";
        };
    }

    /**
     * Play the game
     * @param ammo number of ammunition at the beginning
     * @param solutionGrid player's grid
     */
    public static void playGame(int ammo, String solutionGrid){
        String playerGrid        = "                                                                ";
        String coords            = " ";
        String sunkShipsMsg    = " ";
        String sunkShipType    = " ";

        int index                = -1;
        int totalShotsSuccess    = 0;
        int totalAmmoAtStart     = ammo;
        int nbSunkShips    = 0;

        //TEST: do not display in the game!
        //JeuUtils.afficherGrille(solutionGrid);

        //Display player's grid at start
        System.out.println();
        displayPlayersGrid(ammo, playerGrid, sunkShipsMsg);

        while(ammo > 0 && totalShotsSuccess < 14){
            coords = checkCoordinatesInput(coords);
            index = convertCoordsIntoIndex(coords);

            //Reduce ammo by 1
            ammo--;

            //Check if the shot was successful, missed or redundant
            if(solutionGrid.charAt(index) == 'B'){
                if(playerGrid.charAt(index) == ' '){
                    playerGrid = playerGrid.substring(0, index) + "B" + playerGrid.substring(index + 1);
                    displayMessageLn(ANSI_GREEN + MSG_SHOT_SUCCESS + ANSI_RESET);
                    totalShotsSuccess++;

                    //Check if the ship has sunk
                    sunkShipType  = determineShipDamage(solutionGrid, playerGrid, index);

                    if(sunkShipType != null){
                        if(sunkShipType .equals(BATTLESHIP_TYPE)){
                            nbSunkShips++;
                            sunkShipsMsg += (sunkShipType + ", ");

                        } else if(sunkShipType .equals(CRUISER_TYPE)){
                            nbSunkShips++;
                            sunkShipsMsg += (sunkShipType + ", ");

                        } else if(sunkShipType .equals(SUBMARINE_TYPE)){
                            nbSunkShips++;
                            sunkShipsMsg += (sunkShipType + ", ");

                        } else if(sunkShipType .equals(DESTROYER_TYPE)){
                            nbSunkShips++;
                            sunkShipsMsg += (sunkShipType + ", ");
                        }
                    }
                } else if (playerGrid.charAt(index) == 'B' || playerGrid.charAt(index) == 'X'){
                    displayMessageLn(ANSI_BLUE + MSG_SHOT_REDUNDANT + ANSI_RESET);
                }
            } else{
                if(playerGrid.charAt(index) == ' '){
                    playerGrid = playerGrid.substring(0, index) + "X" + playerGrid.substring(index + 1);
                    displayMessageLn(ANSI_RED + MSG_SHOT_MISSED + ANSI_RESET);

                } else{
                    displayMessageLn(ANSI_BLUE + MSG_SHOT_REDUNDANT + ANSI_RESET);
                }
            }

            displayPlayersGrid(ammo, playerGrid, sunkShipsMsg);

            //Display victory message
            if(totalShotsSuccess >= 14 && ammo >= 0){
                displayMessageLn(ANSI_BLUE + MSG_VICTORY + " " + (totalAmmoAtStart - ammo) + " / " + totalAmmoAtStart + ANSI_RESET);
            }
        }
        //Display failure message
        if(ammo <= 0){
            displayMessageLn(ANSI_RED + MSG_FAILURE + " " + nbSunkShips + ANSI_RESET);
        }
    }

    public static void main(String[] args) {
        //----------
        //Variables
        //----------
        String newGameChoice    = CONTINUE_THE_GAME;
        String menuChoice       = " ";
        String gameGrid         = " ";

        boolean validChoice     = false;

        presentTheGame(PRESENTATION);

        //Quit the program if the player answers 'no'
        do{
            gameGrid = JeuUtils.genererGrilleSolution();

            menuChoice = UserInputUtils.readString(input,ANSI_YELLOW + MSG_MAIN_MENU + ANSI_RESET);
            validChoice = false;

            switch(menuChoice){
                case "1":
                    playGame(AMMO_BEGINNER, gameGrid);
                    validChoice = true;
                    break;
                case "2":
                    playGame(AMMO_INTERMEDIATE, gameGrid);
                    validChoice = true;
                    break;
                case "3":
                    playGame(AMMO_EXPERT, gameGrid);
                    validChoice = true;
                    break;
                default:
                    displayMessageLn(MSG_ERR_MENU);
                    break;
            }

            //Query if the player wants to play a new game
            if(validChoice){
                newGameChoice = newGameQuery();
            }

        }while(!newGameChoice.equalsIgnoreCase(EXIT_THE_GAME));

        displayMessageLn(MSG_END_OF_PROGRAM);
    }
}
