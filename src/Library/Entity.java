
package Library;


import Utilities.Consts;
import javafx.application.Platform;

public class Entity {

    protected int x;
    protected int y;
    protected final Grid grid;
    protected final EntityType type;
    protected String player;
    protected String imagePath;
    protected int hp;
    protected int damage;
    protected int range;

    public Entity(int x, int y, Grid grid, EntityType type, String player, String imagePath, int hp, int damage, int range) {
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.type = type;
        this.player = player;
        this.imagePath = imagePath;
        this.hp = hp;
        this.damage = damage;
        this.range = range;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }


    public int getY() {
        return this.y;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public EntityType getType() {
        return this.type;
    }


//    public boolean move(Entity entity) {
//        boolean ret = false;
//        switch (entity.getPlayer()) {
//            case "player1":
//                ret = entity.move(0, -1);
//                break;
//            case "player2":
//                ret = entity.move(0, 1);
//                break;
//        }
//        return ret;
//    }

    private boolean move(int xOffset, int yOffset) {
        if (this.grid.isMovable(x + xOffset, y + yOffset)) {
//            this.grid.getCells()[this.x][this.y] = new Entity(this.x, this.y, this.grid, EntityType.DEFAULT, "",
//                    Consts.getDefaultImgPath(),0,0,0);
            Entity defalt = new Entity(this.x, this.y, this.grid, EntityType.DEFAULT, "",
                    Consts.getDefaultImgPath(), 0, 0, 0);
            this.grid.setCell(defalt);
            this.setX(this.x);
            this.setY(this.y+yOffset);
            System.out.println("*");
            System.out.println(this.getY());
            grid.setCell(this);
//            this.grid.getCells()[this.x + xOffset][this.y + yOffset] = this;
//            this.grid.getCells()[this.x + xOffset][this.y + yOffset] = this;
            return true;
        }
        return false;
    }


    protected boolean move(Entity entity, int yOffset) {
        boolean ret = false;
        switch (entity.getPlayer()) {
            case "player1":
                ret = entity.move(0, -yOffset);
                break;
            case "player2":
                ret = entity.move(0, yOffset);
                break;
        }
        return ret;
    }

//    private boolean move(int xOffset, int yOffset) {
//        if (this.grid.isMovable(x + xOffset, y + yOffset)) {
//            this.grid.getCells()[this.x][this.y] = new Entity(this.x, this.y, this.grid, EntityType.DEFAULT, "",
//                    Consts.getDefaultImgPath(),0,0,0);
//            this.grid.getCells()[this.x + xOffset][this.y + yOffset] = this;
//            return true;
//        }
//        return false;
//    }

    public void takeDamage(int i) {
        hp =- i;
        if(hp <= 0){
            Entity defalt = new Entity(this.x, this.y, this.grid, EntityType.DEFAULT, "",
                    Consts.getDefaultImgPath(), 0, 0, 0);
            this.grid.setCell(defalt);
        }
    }


//    protected boolean move(int xOffset, int yOffset) {
//        boolean moveAble;
//        boolean lost = false;
//        moveAble = this.grid.isMovable(x + xOffset, y + yOffset);
//        boolean pacmanBoosted = Game.isPacmanBoosted();
//        if (this.type == EntityType.PACMAN && !pacmanBoosted) {
//            lost = this.grid.contains(x + xOffset, y + yOffset, EntityType.GHOST);
//        } else if (this.type == EntityType.GHOST && !pacmanBoosted) {
//            lost = this.grid.contains(x + xOffset, y + yOffset, EntityType.PACMAN);
//        }
//
//        boolean gum = this.grid.contains(x + xOffset, y + yOffset, EntityType.GUM);
//        boolean bigGum = this.grid.contains(x + xOffset, y + yOffset, EntityType.BIG_GUM);
//        if (moveAble & !lost) {
//            if (this instanceof Pacman) {
//                Entity entity = this.grid.getCells()[this.x + xOffset][this.y + yOffset];
//                Entity entity1 = this.grid.getCells()[this.x][this.y];
//                eatGhost(pacmanBoosted, entity);
//                eatGhost(pacmanBoosted, entity1);
//                this.grid.getCells()[this.x + xOffset][this.y + yOffset] = this;
//                this.grid.getCells()[this.x][this.y] = new Entity(this.x, this.y, this.grid, Consts.getDefaultImgPath(), EntityType.DEFAULT);
//                if (gum || bigGum) {
//                    this.grid.eatenGum();
//                    Game.addScore(1);
//                    if (bigGum) {
//                        Game.setPacmanBoosted();
//                        Game.addScore(4);
//                    }
//                }
//            }
//            if (this instanceof Ghost) {
//
//                boolean ghost = this.grid.contains(x + xOffset, y + yOffset, EntityType.GHOST);
//                if (ghost) {
//                    return false;
//                } else if (gum) {
//                    this.grid.getCells()[this.x][this.y] = new Entity(this.x, this.y, this.grid, Consts.getSmallGumImgPath(), EntityType.GUM);
//                } else if (bigGum) {
//                    this.grid.getCells()[this.x][this.y] = new Entity(this.x, this.y, this.grid, Consts.getBigGumImgPath(), EntityType.BIG_GUM);
//                } else {
//                    this.grid.getCells()[this.x][this.y] = new Entity(this.x, this.y, this.grid, Consts.getDefaultImgPath(), EntityType.DEFAULT);
//                }
//                this.grid.getCells()[this.x + xOffset][this.y + yOffset] = this;
//            }
//            this.y += yOffset;
//            this.x += xOffset;
//        }
//        if (lost) {
//            this.grid.setLost(true);
//        }
//        return moveAble;
//    }

//    private void eatGhost(boolean pacmanBoosted, Entity entity) {
//        if (entity instanceof Ghost && pacmanBoosted) {
//            Game.addEatenGhosts(entity);
//            entity.setX(9);
//            entity.setY(12);
//            Game.addScore(10);
//        }
//    }
}
