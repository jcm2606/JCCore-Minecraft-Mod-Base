package jcm2606.mods.jccore.util;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GenerationUtil {
    /**
     * Adds ore generation to whatever generator this method is called within.
     * 
     * @param maxYLevel
     * @param blockID
     * @param rarity
     * @param veinSize
     * @param blockX
     * @param blockZ
     * @param rand
     * @param world
     */
    public static void addOreGen(int maxYLevel, int blockID, int rarity, int veinSize, int blockX, int blockZ, Random rand, World world)
    {
        for(int i = 0; i < rarity; i++)
        {
            int Xcoord = blockX + rand .nextInt(16);
            int Ycoord = rand.nextInt(maxYLevel);
            int Zcoord = blockZ + rand.nextInt(16);
            
            (new WorldGenMinable(blockID, veinSize)).generate(world, rand, Xcoord, Ycoord, Zcoord);
        }
    }
    
    public static void addStructureGen(WorldGenerator generator, int blockX, int blockZ, int occurance, Random rand, World world)
    {
        if(rand.nextInt(100) <= occurance)
        {
            int Xcoord1 = blockX + rand.nextInt(16);
            int Ycoord1 = rand.nextInt(60);
            int Zcoord1 = blockZ + rand.nextInt(16);
            
            (generator).generate(world, rand, Xcoord1, Ycoord1, Zcoord1);
        }
    }
}
