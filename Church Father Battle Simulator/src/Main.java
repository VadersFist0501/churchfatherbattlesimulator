import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {

        final int TEAM_SIZE = 6;

        Scanner s = new Scanner(System.in);

        BattleFather[] battleFatherArray;
        battleFatherArray = new BattleFather[13];

        BattleFather[] team1;
        BattleFather[] team2;
        team1 = new BattleFather[TEAM_SIZE];
        team2 = new BattleFather[TEAM_SIZE];

        battleFatherArray[0] = new BattleFather("Augustine", 1000, 180, 140, 30);
        battleFatherArray[1] = new BattleFather("Nestorius", 750, 150, 150, 50);
        battleFatherArray[2] = new BattleFather("Constantine", 950, 200, 40, 75);
        battleFatherArray[3] = new BattleFather("Origen", 900 - 2, 170, 190, 70);
        battleFatherArray[4] = new BattleFather("Martin Luther", 980, 200, 120, 0);
        battleFatherArray[5] = new BattleFather("MLK Jr.", 20, 50, 10, 95);
        battleFatherArray[6] = new BattleFather("Karl Barth", 750, 50, 20, 75);
        battleFatherArray[7] = new BattleFather("Joel Osteen", 150, 70, 20, 70);
        battleFatherArray[8] = new BattleFather("Billy Graham", 600, 190, 80, 30);
        battleFatherArray[9] = new BattleFather("Thomas Aquinas", 1000, 70, 170, 50);
        battleFatherArray[10] = new BattleFather("Jay Dyer", 1000, 200, 200, 0);
        battleFatherArray[11] = new BattleFather("Joseph Smith Jr.", 800, 160, 200, 10);
        battleFatherArray[12] = new BattleFather("Russel Nelson", 500, 190, 150, 50);


        team1 = createTeam(s, battleFatherArray, TEAM_SIZE);
        team2 = createTeam(s, battleFatherArray, TEAM_SIZE, true);


//        // Fill battle teams
//        for (int i = 0; i < TEAM_SIZE; i++){
//            int rand1 = rand.nextInt(battleFatherArray.length);
//            int rand2 = rand.nextInt(battleFatherArray.length);
//
//            team1[i] = new BattleFather(battleFatherArray[rand1]);
//            team2[i] = new BattleFather(battleFatherArray[rand2]);
//        }


        // two player
        if (askIfTwoPlayer(s)) {
            while (team1[0].getHp() > 0 && team2[0].getHp() > 0) {
                gameState(team1, team2);
                System.out.println("Team 1 turn . . .");
                takeTurn(s, team1, team2);
                System.out.print("\n\n");

                gameState(team1,  team2);
                System.out.println("Team 2 turn . . .");
                takeTurn(s, team2, team1);
                System.out.print("\n\n");
            }
        // one player
        } else {
            while (team1[0].getHp() > 0 && team2[0].getHp() > 0) {
                gameState(team1, team2);
                takeTurn(s, team1, team2);
                computerTurn(team2[0], team1[0]);
            }
        }

    }

    static Random rand = new Random(System.currentTimeMillis());

    private static boolean askIfTwoPlayer(Scanner s) {
        System.out.print("1 or 2 players? (\"1\"/\"2\"): ");
        String input = s.nextLine();

        if (input.equals("1")) return false;
        if (input.equals("2")) return true;

        System.out.println("Invalid input . . .");
        return askIfTwoPlayer(s);
    }
    private static void takeTurn(Scanner s, BattleFather[] team1, BattleFather[] team2) {
        boolean hasLivingBattleFather = false;
        for (int i = 0; i < team1.length; i++) {
            if (team1[i].getHp() > 0) hasLivingBattleFather = true;
        }
        if (!hasLivingBattleFather) return;

        String[] parsedInput = getMove(s, team1, team2);
        String command = parsedInput[0];

        if (parsedInput[0].equalsIgnoreCase("help")) {

            System.out.println("---------------COMMAND HELP---------------");
            System.out.println("attack <actor id> <target id>\n" +
                    "\tBattle Father (actor) attacks another (target) on\n" +
                    "\topposing team, using 'pwr' to calculate damage with a\n" +
                    "\t'heresy' modifier\n" +
                    "\tE.g., 'attack 4 2' - Battle Father on allied team with id\n" +
                    "\t'4' attacks Battle Father on opposing team with id '2'\n");

            System.out.println("heal <actor id> <target id>\n" +
                    "\tBattle Father (actor) heals another (target) on\n" +
                    "\tallied team, using 'piety' to calculate heal with a\n" +
                    "\t'heresy' modifier\n" +
                    "\tE.g., 'heal 4 2' - Battle Father on allied team with id\n" +
                    "\t'4' heals Battle Father on opposing team with id '2'\n");

            System.out.println("status <BattleFather id>\n" +
                    "\tPrints out status information about Battle Father\n" +
                    "\ton allied team with id given, including health, pwr,\n" +
                    "\theresy, piety, etc.\n" +
                    "\tE.g., 'status 3' - Prints status information about\n" +
                    "\tBattle Father on allied team with id '3'\n");

            System.out.println("help\n" +
                    "\tDisplay this information again");
            System.out.println("---------------COMMAND HELP---------------");


            System.out.print("\n Press ENTER to continue . . .");
            s.nextLine();

            gameState(team1, team2);
            takeTurn(s, team1, team2);
            return;
        }

        int actor = Integer.parseInt(parsedInput[1]);

        if (parsedInput[0].equalsIgnoreCase("status")) {
            System.out.println("\nName:   " + team1[actor].getName());
            System.out.println("HP:     [" + team1[actor].getHp() + "/" + team1[actor].getMaxhp() + "]");
            System.out.println("Power:  " + team1[actor].getPwr());
            System.out.println("Heresy: " + team1[actor].getHeresy());
            System.out.println("Piety:  " + team1[actor].getPiety());

            System.out.print("\n Press ENTER to continue . . .");
            s.nextLine();

            gameState(team1, team2);
            takeTurn(s, team1, team2);
            return;
        }

        int target = Integer.parseInt(parsedInput[2]);

        if (command.equalsIgnoreCase("attack")) {
            //System.out.println("Target: " + target);
            int dmg = team1[actor].attack(team2[target]);
            System.out.println(team1[actor].getName() + " has done " + dmg + " damage to " + team2[target].getName() + "!!");
            return;
        }

        if (parsedInput[0].equalsIgnoreCase("heal")) {
            int heal = team1[actor].heal(team1[target]);
            System.out.println(team1[actor].getName() + " has given " + heal + " HP to " + team1[target].getName() + "!!");
        }

    }

    /*
    private static void takeTurn(Scanner s, BattleFather player, BattleFather otherplayer){
        if (player.getHp() <= 0) return;

        System.out.print(player.getName() + "'s turn. HP " + player.getHp() + "/" + player.getMaxhp() + " (\"attack\"/\"heal\"): ");
        String input = s.nextLine();

        if (input.equalsIgnoreCase("attack")) {
            int dmg = player.attack(otherplayer);
            System.out.println(player.getName() + " has done " + dmg + " damage to " + otherplayer.getName() + "!!");
            return;
        }
        if (input.equalsIgnoreCase("heal")) {
            int heal = player.heal();
            System.out.println(player.getName() + " has healed " + heal + " HP");
            if (player.getHp() > player.getMaxhp()) player.setHp(player.getMaxhp());
            return;
        }
        System.out.println("Invalid input . . .");
        takeTurn(s, player, otherplayer);
    }
    */

    private static String[] getMove(Scanner s, BattleFather[] team1, BattleFather[] team2){
        System.out.print("Next move: ");
        String input = s.nextLine();
        String[] parsedInput = input.split(" ", 3);

        switch (parsedInput[0]) {
            case "attack":
                if (parsedInput.length == 3 &&
                        isInteger(parsedInput[1]) &&
                        isInteger(parsedInput[2]) &&
                        isLivingBattleFather(team1, Integer.parseInt(parsedInput[1])) &&
                        isLivingBattleFather(team2, Integer.parseInt(parsedInput[2]))){
                    return parsedInput;
                }
            case "heal":
                if (parsedInput.length == 3 &&
                        isInteger(parsedInput[1]) &&
                        isInteger(parsedInput[2]) &&
                        isLivingBattleFather(team1, Integer.parseInt(parsedInput[1])) &&
                        isLivingBattleFather(team1, Integer.parseInt(parsedInput[2]))){
                    return parsedInput;
                }
            case "status":
                if (parsedInput.length == 2 &&
                        isInteger(parsedInput[1]) &&
                        isLivingBattleFather(team1, Integer.parseInt(parsedInput[1]))) {
                    return parsedInput;
                }
            case "help":
                if (parsedInput.length == 1) {
                    return parsedInput;
                }
            default:
                return getMove(s, team1, team2);
        }
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static void computerTurn(BattleFather player, BattleFather otherplayer) {
        if (player.getHp() <= 0) return;

        System.out.println(player.getName() + "'s turn. HP " + player.getHp() + "/" + player.getMaxhp());

        if (rand.nextInt(2) % 2 == 1) {
            int dmg = player.attack(otherplayer);
            System.out.println(player.getName() + " has done " + dmg + " damage to " + otherplayer.getName() + "!!");
            return;
        } else {
            int heal = player.heal(player);
            System.out.println(player.getName() + " has healed " + heal + " HP");
            if (player.getHp() > player.getMaxhp()) player.setHp(player.getMaxhp());
            return;
        }
    }

    private static void gameState(BattleFather[] team1, BattleFather[] team2) {

        int maxNameLen1 = getMaxBattleFatherNameLen(team1);
        int maxNameLen2 = getMaxBattleFatherNameLen(team2);

        System.out.println();
        //printBorder(maxNameLen1 + maxNameLen2 + 36);

        hyphenator(maxNameLen1, maxNameLen2);

        for (int i = 0; i < team1.length; i++) {

            String fatherString1 = String.format("%" + maxNameLen1 + "s", team1[i].getName());
            String fatherString2 = String.format("%" + maxNameLen2 + "s", team2[i].getName());

            String hpReading1 = String.format("%-12s", " [" + team1[i].getHp() + "/" + team1[i].getMaxhp() + "]");
            String hpReading2 = String.format("%-12s", " [" + team2[i].getHp() + "/" + team2[i].getMaxhp() + "]");

            System.out.println("["+i+"] " + fatherString1 + hpReading1 + " || " + fatherString2 + hpReading2 + " ["+i+"]");
        }
        printBorder(maxNameLen1 + maxNameLen2 + 36);
    }

    private static int getMaxBattleFatherNameLen(BattleFather[] team) {
        int maxLen = 0;
        for (int i = 0; i < team.length; i++){
            if (team[i].getName().length() > maxLen) {
                maxLen = team[i].getName().length();
            }
        }
        return maxLen;
    }

    private static void printBorder(int numCharacters) {
        for (int i = 0; i < numCharacters; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    private static void hyphenator(int maxNameLen1, int maxNameLen2) {
        System.out.print("    ");
        for (int i = 0; i < maxNameLen1 + 12; i++){
            if (  i + 3 <= ((maxNameLen1 + 12) / 2) && i + 5 >= ((maxNameLen1 + 12) / 2)) {
                    System.out.print("< Team 1 >");
                    i += 9;
                } else {
                    System.out.print("-");
                }
        }
        System.out.print(" || ");

        for (int i = 0; i < maxNameLen2 + 12; i++){
            if (  i + 3 <= ((maxNameLen2 + 12) / 2) && i + 5 >= ((maxNameLen2 + 12) / 2)) {
                System.out.print("< Team 2 >");
                i += 9;
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    private static boolean isLivingBattleFather(BattleFather[] team, int id) {
        if (id >= team.length) return false;
        return team[id].getHp() > 0;
    }


    private static BattleFather[] createTeam(Scanner s, BattleFather[] battleFatherArray, int teamSize, boolean randomByDefault) {

        if (!randomByDefault) {
            System.out.print("Randomize team? (y/n): ");
            String input = s.nextLine();
            switch (input.toLowerCase()) {
                case "y":
                case "yes":
                    randomByDefault = true;
                    break;

                case "n":
                case "no":
                    break;

                default:
                    System.out.println("Invalid input . . .");
                    return createTeam(s, battleFatherArray, teamSize, randomByDefault);
            }
        }


        BattleFather[] array = new BattleFather[teamSize];
        if (randomByDefault) {
            for (int i = 0; i < teamSize; i++) {
                int random = rand.nextInt(battleFatherArray.length);

                array[i] = new BattleFather(battleFatherArray[random]);
            }
            return array;
        }

        System.out.println("Battle Fathers:");
        for (int j = 0; j < battleFatherArray.length; j++) {
            System.out.print("[" + j + "]-" + battleFatherArray[j].getName() + " ");
            if (j % 4 == 3) System.out.println();
        }
        System.out.println();
        for (int i = 0; i < teamSize; i++) {
            System.out.print("Choose ID of father to add to team: ");
            if(s.hasNextInt()) {
                int id = s.nextInt();
                s.nextLine();
                array[i] = new BattleFather(battleFatherArray[id]);
            } else {
                s.nextLine();
                System.out.println("Invalid input . . .");
                i--;
            }
        }
        return array;
    }

    // POLYMORPHISM
    private static BattleFather[] createTeam(Scanner s, BattleFather[] battleFatherArray, int teamSize) {
        return createTeam(s, battleFatherArray, teamSize, false);
    }
}
