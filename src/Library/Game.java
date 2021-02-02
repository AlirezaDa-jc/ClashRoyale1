
package Library;

import Utilities.Consts;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class Game extends Observable implements Runnable {

    private static final int lines = 5;
    private static final int columns = 24;
    private final Grid grid;
    private Direction dir = Direction.RIGHT;
    private Direction triedDir = null;
    private boolean exit = false;
    private static int boostDuration = 0;
    private static boolean boosted = false;
    private static final int defaultSleepTime = 1000;
    private static int player1Mana = 0;
    private static int player2Mana = 0;


    public Game() {
        super();
        this.grid = new Grid(lines, columns);
//        Thread thread = new Thread(()->{
//
//        });
//        thread.start();
    }


    @Override
    public synchronized void run() {
        while (!this.exit) {
            try {
            this.setChanged();
            this.notifyObservers();
            player1Mana = player1Mana + 10;
            player2Mana = player2Mana + 10;
            Thread.sleep(defaultSleepTime);
            System.out.println(player1Mana);
            System.out.println(player2Mana);
//                this.manageBoostInteraction();

            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    private void manageBoostInteraction() throws EntityNotFoundException {
//        if (boosted) {
//            boostDuration--;
//            this.getState().ChangeGhostState(true);
//            if (boostDuration == 0) {
//                boosted = false;
//                this.getState().ChangeGhostState(false);
//            }
//        }
//    }


//    public static void setPacmanBoosted() {
//        Game.boosted = true;
//        Game.boostDuration = 15;
//    }


//    public static boolean isPacmanBoosted() {
//        return Game.boostDuration > 0;
//    }


    public void stop() {
        this.exit = true;
    }


    public Grid getState() {
        return this.grid;
    }


//    public Direction getDirection() {
//        return this.dir;
//    }
//
//
//    public void setDirection(Direction dir) {
//        this.dir = dir;
//    }
//
//
//    public Direction getLastTriedDirection() {
//        return this.triedDir;
//    }
//
//
//    public void setLastTriedDirection(Direction dir) {
//        this.triedDir = dir;
//    }

//    public void movePacman() {
//        try {
//            if (this.getLastTriedDirection() != null) {
//                if (this.getState().getPacman().move(this.getLastTriedDirection())) {
//                    this.setDirection(this.getLastTriedDirection());
//                    this.setLastTriedDirection(null);
//                } else {
//                    this.getState().getPacman().move(this.getDirection());
//                }
//            } else {
//                this.getState().getPacman().move(this.getDirection());
//            }
//
//        } catch (EntityNotFoundException ex) {
//            System.out.println(ex + "||| Error occurred in movePacman()");
//        }
//    }

    public void moveEntities() {
        ArrayList<Goblin> goblins = this.getState().getGoblins();
        for (Goblin ghost : goblins) {
            System.out.println(goblins.size());
            ghost.move();
        }
        ArrayList<Archer> archers = this.getState().getArchers();
        for (Archer archer : archers) {

                    archer.move();

        }
        ArrayList<Knight> knights = this.getState().getKnights();
        for (Knight knight : knights) {

                    knight.move();

        }
        ArrayList<Shield> shields = this.getState().getShields();
        for (Shield shield : shields) {

                    shield.move();


        }
    }

    public void makeGoblin(String player) {
        if (player.equals("player1")) {
            if (player1Mana >= 10) {
                Goblin goblin = new Goblin(grid.getPlayer1HomeX(), grid.getPlayer1HomeY(), grid, EntityType.Goblin, Consts.getPlayer1GoblinImgPath(), player);
                grid.setCell(goblin);
                player1Mana = player1Mana - 10;
            }
        } else {
            if (player2Mana >= 10) {
                Goblin goblin = new Goblin(grid.getPlayer2HomeX(), grid.getPlayer2HomeY(), grid, EntityType.Goblin, Consts.getPlayer2GoblinImgPath(), player);
                grid.setCell(goblin);
                player2Mana = player2Mana - 10;

            }
        }
    }

    public void makeShield(String player) {
        if (player.equals("player1")) {
            if (player1Mana >= 10) {
                Shield shield = new Shield(grid.getPlayer1HomeX(), grid.getPlayer1HomeY(), grid, EntityType.Shield, Consts.getShieldImgPath(), player);
                grid.setCell(shield);
                player1Mana = player1Mana - 10;
            }
        } else {
            if (player2Mana >= 10) {
                Shield shield = new Shield(grid.getPlayer2HomeX(), grid.getPlayer2HomeY(), grid, EntityType.Shield, Consts.getShieldImgPath(), player);
                grid.setCell(shield);
                player2Mana = player2Mana - 10;
            }
        }
    }

    public void makeKnight(String player) {
        if (player.equals("player1")) {
            if (player1Mana >= 30) {
                Knight knight = new Knight(grid.getPlayer1HomeX(), grid.getPlayer1HomeY(), grid, EntityType.Knight, Consts.getPlayer2KnightImgPath(), player);
                grid.setCell(knight);
                player1Mana = player1Mana - 30;

            }
        } else {
            if (player2Mana >= 30) {
                Knight knight = new Knight(grid.getPlayer2HomeX(), grid.getPlayer2HomeY(), grid, EntityType.Knight, Consts.getPlayer2KnightImgPath(), player);
                grid.setCell(knight);
                player2Mana = player2Mana - 30;
            }
        }
    }

    public void makeArcher(String player) {
        if (player.equals("player1")) {
            if (player1Mana >= 15) {
                Archer archer = new Archer(grid.getPlayer1HomeX(), grid.getPlayer1HomeY(), grid, EntityType.Archer, Consts.getPlayer1ArcherImgPath(), player);
                grid.setCell(archer);
                player1Mana = player1Mana - 15;
            }
        } else {
            if (player2Mana >= 15) {
                Archer archer = new Archer(grid.getPlayer2HomeX(), grid.getPlayer2HomeY(), grid, EntityType.Archer, Consts.getPlayer2ArcherImgPath(), player);
                grid.setCell(archer);
                player2Mana = player2Mana - 15;
            }
        }
    }

    public void attackTowers() {
        ArrayList<BlackTower> blackTowers = getState().getBlackTowers();
        ArrayList<ElectricTower> electricTowers = getState().getElectricTowers();
        try {
            Optional<BlackTower> blackTowerFirstPlayerOptional = blackTowers.stream().filter(c -> c.getPlayer().equals("player1")).findFirst();
            Optional<BlackTower> blackTowerSecondPlayerOptional = blackTowers.stream().filter(c -> c.getPlayer().equals("player2")).findFirst();
            Optional<ElectricTower> electricTowerFirstPlayerOptional = electricTowers.stream().filter(c -> c.getPlayer().equals("player1")).findFirst();
            Optional<ElectricTower> electricTowerSecondPlayerOptional = electricTowers.stream().filter(c -> c.getPlayer().equals("player2")).findFirst();

            List<Entity> player2Soldiers = getState().getEntities().stream().filter(c -> c.getPlayer().equals("player2")).collect(Collectors.toList());
            List<Entity> player1Soldiers = getState().getEntities().stream().filter(c -> c.getPlayer().equals("player1")).collect(Collectors.toList());
            attackTowers(blackTowerFirstPlayerOptional, electricTowerFirstPlayerOptional, player2Soldiers);
            attackTowers(blackTowerSecondPlayerOptional, electricTowerSecondPlayerOptional, player1Soldiers);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private void attackTowers(Optional<BlackTower> blackTowerOptional, Optional<ElectricTower> electricTowerOptional, List<Entity> playerSoldiers) {
        if(playerSoldiers.isEmpty()){
            return;
        }
        for (int i = 0; i < playerSoldiers.size(); i++) {
            Entity entity = playerSoldiers.get(i);
            //Todo Bar Asas Grid bayad Anjm Shavad
            if (blackTowerOptional.isPresent()) {
                BlackTower blackTower = blackTowerOptional.get();
                if (entity.getX() - 2 <= blackTower.getX()) {
                    entity.takeDamage(blackTower.getDamage());
                    if (entity.getRange() >= entity.getX() - 2) {
                        blackTower.takeDamage(entity.getDamage());
                    }
                }
                getState().setCell(blackTower);
            }
            if (electricTowerOptional.isPresent()) {
                ElectricTower electricTower = electricTowerOptional.get();
                if (entity.getX() - 3 <= electricTower.getX()) {
                    entity.takeDamage(electricTower.getDamage());
                    if (entity.getRange() >= entity.getX() - 2) {
                        electricTower.takeDamage(entity.getDamage());
                    }
                }
                getState().setCell(electricTower);
            }
            getState().setCell(entity);
        }
    }
}
