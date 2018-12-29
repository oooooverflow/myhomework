package battleground;

import creatures.Creatures;

public class Blocks {
    //该块地面上是否站立生物
    private boolean isAnyCreature;
    //该块地面上站立着什么生物
    private Creatures OnWardCreature;

    public Blocks() {
        isAnyCreature = false;
        OnWardCreature = null;
    }
    //召唤某个生物
    public void Summon(Creatures creature) {
        isAnyCreature = true;
        OnWardCreature = creature;
    }
    //赶走某个生物
    public void DeSummon() {
        OnWardCreature = null;
        isAnyCreature = false;
    }

    public boolean isAnyCreature() {
        return isAnyCreature;
    }

    public Creatures getCreature() {
        return OnWardCreature;
    }
}
