package com.repitch.worldclasstest.network.models;

import com.repitch.worldclasstest.R;

import java.io.Serializable;

/**
 * Created by repitch on 23.05.16.
 */
public class Gym implements Serializable {
    public static final String DIVISION_WORLD_CLASS = "World Class";
    public static final String DIVISION_WORLD_CLASS_LITE = "World Class LITE";
    public static final String ZHUKOVKA = "Жуковка";
    public static final String ROMANOV = "Романов";

    public int getColorResId() {
        if (name.equals(ZHUKOVKA) || name.equals(ROMANOV)) {
            return R.color.colorZhukovkaRomanov;
        }
        switch (division) {
            case DIVISION_WORLD_CLASS:
                return R.color.colorWorldClass;
            case DIVISION_WORLD_CLASS_LITE:
                return R.color.colorWorldClassLite;
        }
        return R.color.colorAccent;
    }

    public String id;
    public String name;
    public String division;
    public String gymAddress;

    public Gym() {

    }
}
