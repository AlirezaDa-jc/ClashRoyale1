
package Library;


import Utilities.Consts;

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

    private boolean move(int yOffset) {
        int y = yOffset;
        boolean secondFlag = false;
        boolean firstFlag = false;
        while(y != 0){
            firstFlag = this.grid.isMovable(x, this.y + y);
            secondFlag = this.grid.isMovable(x, this.y - y);
            if(!firstFlag && !secondFlag){
                break;
            }
            y--;
        }
        if(player.equals("player1")){
            if (moveEntity(yOffset, secondFlag)) return true;
        }else if(player.equals("player2")) {
            if (moveEntity(yOffset, firstFlag)) return true;
        }
        this.grid.setCell(this);
        return false;
    }

    private boolean moveEntity(int yOffset, boolean firstFlag) {
        if (firstFlag) {
            Entity defalt = new Entity(this.x, this.y, this.grid, EntityType.DEFAULT, "",
                    Consts.getDefaultImgPath(), 0, 0, 0);
            this.grid.setCell(defalt);
            this.setX(this.x);
            this.setY(this.y + yOffset);
            grid.setCell(this);

            return true;
        }
        return false;
    }


    protected boolean move(Entity entity, int yOffset) {
        boolean ret = false;
        switch (entity.getPlayer()) {
            case "player1":
                ret = entity.move(-yOffset);
                break;
            case "player2":
                ret = entity.move(yOffset);
                break;
        }
        return ret;
    }



    public void takeDamage(int i) {
        hp = hp - i;
        if(hp <= 0){
            this.grid.removeEntity(this);
            if(this.getType()==EntityType.BlackTower || this.getType()== EntityType.ElectricTower){
                if(this.getPlayer().equals("player1")){
                    grid.remove1TowerPlayer1();
                }else {
                    grid.remove1TowerPlayer2();
                }
            }
            Entity defalt = new Entity(this.x, this.y, this.grid, EntityType.DEFAULT, "",
                    Consts.getDefaultImgPath(), 0, 0, 0);
            this.grid.setCell(defalt);
        }
    }


}
