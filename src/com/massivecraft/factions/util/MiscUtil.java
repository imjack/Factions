package com.massivecraft.factions.util;

import cn.nukkit.utils.TextFormat;

import java.util.Arrays;
import java.util.HashSet;

public class MiscUtil {
//	public static <T extends Entity> T creatureTypeFromEntity(Entity entity)
//	{
//		if ( ! (entity instanceof Creature))
//		{
//			return null;
//		}
//
//		String name = entity.getClass().getSimpleName();
//		name = name.substring(5); // Remove "Craft"
//
//		return EntityType.fromName(name);
//	}
//
//
//    public <T extends Entity> T getEntity(int id){
//
//        switch (id){
//            case 1:
//                break;
//            default:
//                break;
//        }
//
//        return type.PrimedTNT.class;
//    }

    /// TODO create tag whitelist!!
    public static HashSet<String> substanceChars = new HashSet<String>(Arrays.asList(new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z"
    }));

    // Inclusive range
    public static long[] range(long start, long end) {
        long[] values = new long[(int) Math.abs(end - start) + 1];

        if (end < start) {
            long oldstart = start;
            start = end;
            end = oldstart;
        }

        for (long i = start; i <= end; i++) {
            values[(int) (i - start)] = i;
        }

        return values;
    }

    public static String getComparisonString(String str) {
        StringBuilder ret = new StringBuilder();

        str = TextFormat.clean(str);
        str = str.toLowerCase();

        for (char c : str.toCharArray()) {
            if (substanceChars.contains(String.valueOf(c))) {
                ret.append(c);
            }
        }
        return ret.toString().toLowerCase();
    }

}

