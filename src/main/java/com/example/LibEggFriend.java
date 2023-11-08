package com.example;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class LibEggFriend {
    public static enum States {EGG, BABY, CHILD, TEENAGER, ADULT, SPECIAL;
        public States next() {
            if (ordinal() == values().length - 1)
                return values()[ordinal()];
            return values()[ordinal() + 1];
        }

        public static States last() {
            return SPECIAL;
        }
    };
    private static final Map<States, Integer> stageLengths = Map.ofEntries(
            entry(States.EGG, 1),
            entry(States.BABY, 1),
            entry(States.CHILD, 1),
            entry(States.TEENAGER, 1),
            entry(States.ADULT, 1),
            entry(States.SPECIAL, 1)
    );

    private static String[] saveKeys = {"egg/simTime", "egg/currentState", "egg/evolutionTime", "egg/hasAlert"};

    private long simTime;
    public States currentState;
    private long evolutionTime;
    private boolean hasAlert;
    private int alertLength;
    private int alertStartTime;

    private static long seconds() {
        return System.currentTimeMillis() / 1000;
    }

    private static long minutes() {
        return seconds() / 60;
    }

    public LibEggFriend()
    {
        simTime = minutes();
        currentState = States.EGG;
        evolutionTime = simTime;
    }

    public boolean shouldSimulate() {
//        ExampleMod.LOGGER.info("" + minutes() + ", " + simTime);
        return minutes() > simTime;
    }

    public NbtCompound saveData(NbtCompound entry) throws Exception {
            return entry.copyFrom(saveData());
    }

    public NbtCompound saveData() throws Exception {
        NbtCompound data = new NbtCompound();

        for (String key : saveKeys)
        {
            switch (key)
            {
                case "egg/simTime":
                    data.putLong(key, simTime);
                    break;
                case "egg/hasAlert":
                    data.putBoolean(key, hasAlert);
                    break;
                case "egg/currentState":
                    data.putInt(key, currentState.ordinal());
                    break;
                case "egg/evolutionTime":
                    data.putLong(key, evolutionTime);
                    break;
                default:
                    throw new Exception("Malformed egg friend write save data on key: " + key);
            }
        }
        return data;
    }

    public LibEggFriend(NbtCompound data) throws Exception {
        for (String key : saveKeys)
        {
            switch (key)
            {
                case "egg/simTime":
                    simTime = data.getLong(key);
                    break;
                case "egg/hasAlert":
                    hasAlert = data.getBoolean(key);
                    break;
                case "egg/currentState":
                    currentState = States.values()[data.getInt(key)];
                    break;
                case "egg/evolutionTime":
                    evolutionTime = data.getLong(key);
                    break;
                default:
                    throw new Exception("Malformed egg friend load save data on key: " + key);
            }
        }
    }

    public void doTick(long tick)
    {

        if(tick >= evolutionTime + stageLengths.get(currentState))
        {
            if(currentState != States.last())
            {
                currentState = currentState.next();
                evolutionTime = tick;
            }
        }
    }

    public void simulate()
    {
        long newTime = minutes();
        for(long i = simTime; i < newTime; i++)
        {
            // do tick
            doTick(i);
            simTime = i+1;
        }
    }
}

