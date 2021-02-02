
package Library;

import FileAndUserIO.FileIO;
import Utilities.Consts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Grid {

    private final int rows;
    private final int columns;
    private final Entity[][] cells;
    private final Set<Entity> entities = new HashSet<Entity>();
    private char[][] map;
    private int player1Towers;
    private int player2Towers;
    private int player1HomeX;
    private int player1HomeY;
    private int player2HomeX;
    private int player2HomeY;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;
        this.cells = new Entity[rows][cols];
        this.map = Utilities.StrUtilities.StringTo2D_CharArray(FileIO.readTextFile(Consts.getMapTxtPath()));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                switch (this.map[i][j]) {
                    case '0':
                        this.cells[i][j] = new Entity(i, j, this, EntityType.DEFAULT, "",
                                Consts.getDefaultImgPath(), 0, 0, 0);
                        this.entities.add(this.cells[i][j]);
                        break;
                    case 'E':
                        this.cells[i][j] = new ElectricTower(i, j, this, EntityType.ElectricTower, Consts.getElectricalTowerImgPath(), "player1");
                        this.entities.add(this.cells[i][j]);
                        break;
                    case 'B':
                        this.cells[i][j] = new BlackTower(i, j, this, EntityType.BlackTower, Consts.getBlackTowerImgPath(), "player1");
                        this.entities.add(this.cells[i][j]);
                        break;
                    case 'b':
                        this.cells[i][j] = new BlackTower(i, j, this, EntityType.BlackTower, Consts.getBlackTowerImgPath(), "player2");
                        this.entities.add(this.cells[i][j]);
                        break;
                    case 'e':
                        this.cells[i][j] = new ElectricTower(i, j, this, EntityType.ElectricTower, Consts.getElectricalTowerImgPath(), "player2");
                        this.entities.add(this.cells[i][j]);
                        break;
                    case 'H':
                        this.cells[i][j] = new Home(i, j, this, EntityType.Home, Consts.getHomeImgPath(), "player1");
                        this.entities.add(this.cells[i][j]);
                        player1HomeX = i;
                        player1HomeY = j;
                        break;
                    case 'h':
                        this.cells[i][j] = new Home(i, j, this, EntityType.Home, Consts.getHomeImgPath(), "player2");
                        this.entities.add(this.cells[i][j]);
                        player2HomeX = i;
                        player2HomeY = j;
                        break;
                }
            }
        }
        this.player1Towers = 2;
        this.player2Towers = 2;
    }

    public void remove1TowerPlayer1() {
        player1Towers =- 1;
    }
    public void remove1TowerPlayer2() {
        player2Towers =- 1;
    }

    public int getPlayer1HomeX() {
        return player1HomeX;
    }

    public int getPlayer1HomeY() {
        return player1HomeY;
    }

    public int getPlayer2HomeX() {
        return player2HomeX;
    }

    public int getPlayer2HomeY() {
        return player2HomeY;
    }

    public void setCell(Entity entity) {
        int y = entity.getY();
        int x = entity.getX();

        if(y < 24 && y >= 0) {
            entities.add(entity);
            cells[x][y] = entity;
        }
    }


    public Entity[][] getCells() {
        return this.cells;
    }


    public boolean isMovable(int x, int y) {
        if ((x < this.rows) && (y < this.columns) && (x >= 0) && (y >= 0)) {
            if(this.cells[x][y].getType() == EntityType.BlackTower){
                return false;
            }
            if(this.cells[x][y].getType() == EntityType.ElectricTower){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(int x, int y, EntityType type) {
        if ((x < this.rows) && (y < this.columns) && (x >= 0) && (y >= 0)) {
            return ((this.cells[x][y].getType() == type));
        } else {
            return false;
        }
    }

//    private int countGums() {
//        int gums = 0;
//        for (int i = 0; i < this.entities.size(); i++) {
//            if (this.entities.get(i).getType() == EntityType.GUM || this.entities.get(i).getType() == EntityType.BIG_GUM) {
//                gums++;
//            }
//        }
//        return gums;
//    }


//    public void eatenGum() {
//        this.nbGums--;
//    }


    public boolean noMoreFirstPlayerTowers() {
        return this.player1Towers == 0;
    }

    public boolean noMoreSecondPlayerTowers() {
        return this.player2Towers == 0;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

//    public void ChangeGhostState(boolean afraid) throws EntityNotFoundException {
//        for (int i = 0; i < this.getGhosts().size(); i++) {
//            if (afraid) {
//                this.getGhosts().get(i).setImgPath(Consts.getAfraidGhostImgPath());
//            } else {
//                switch (i) {
//                    case 0:
//                        this.getGhosts().get(i).setImgPath(Consts.getPinkGhostImgPath());
//                        break;
//                    case 1:
//                        this.getGhosts().get(i).setImgPath(Consts.getRedGhostImgPath());
//                        break;
//                    case 2:
//                        this.getGhosts().get(i).setImgPath(Consts.getOrangeGhostImgPath());
//                        break;
//                }
//            }
//        }
//    }


    public ArrayList<Goblin> getGoblins() {
        ArrayList<Goblin> goblins = new ArrayList<>();
        for (Entity entity : this.entities) {
            if (entity instanceof Goblin) {
                goblins.add((Goblin) entity);
            }
        }
        return goblins;
    }

    public ArrayList<BlackTower> getBlackTowers() {
        ArrayList<BlackTower> blackTowers = new ArrayList<>();
        for (Entity entity : this.entities) {
            if (entity instanceof BlackTower) {
                blackTowers.add((BlackTower) entity);
            }
        }
        return blackTowers;
    }

    public ArrayList<ElectricTower> getElectricTowers() {
        ArrayList<ElectricTower> electricTowers = new ArrayList<>();
        for (Entity entity : this.entities) {
            if (entity instanceof ElectricTower) {
                electricTowers.add((ElectricTower) entity);
            }
        }
        return electricTowers;
    }

    public ArrayList<Archer> getArchers() {
        ArrayList<Archer> archers = new ArrayList<>();
        for (Entity entity : this.entities) {
            if (entity instanceof Archer) {
                archers.add((Archer) entity);
            }
        }
        return archers;
    }

    public ArrayList<Knight> getKnights() {
        ArrayList<Knight> knights = new ArrayList<>();
        for (Entity entity : this.entities) {
            if (entity instanceof Knight) {
                knights.add((Knight) entity);
            }
        }
        return knights;
    }

    public ArrayList<Shield> getShields() {
        ArrayList<Shield> shields = new ArrayList<>();
        for (Entity entity : this.entities) {
            if (entity instanceof Shield) {
                shields.add((Shield) entity);
            }
        }
        return shields;
    }



    public boolean checkPlayer1Lost() {
        return player1Towers == 0;
    }
    public boolean checkPlayer2Lost() {
        return player2Towers == 0;
    }


    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
}
