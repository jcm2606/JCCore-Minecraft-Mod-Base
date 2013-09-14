package jcm2606.mods.jccore.util;

import java.util.Random;

import jcm2606.mods.jccore.world.gen.WorldGenNetherMinable;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * Utilities based around terrain generation.
 * 
 * @author Jcm2606
 */
public class GenerationUtil {
    /**
     * Add an Overworld ore generation entry with ease.
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
    
    /**
     * Add a Nether ore generation entry with ease.
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
    public static void addNetherOreGen(int maxYLevel, int blockID, int rarity, int veinSize, int blockX, int blockZ, Random rand, World world)
    {
        for(int i = 0; i < rarity; i++)
        {
            int Xcoord = blockX + rand .nextInt(16);
            int Ycoord = rand.nextInt(maxYLevel);
            int Zcoord = blockZ + rand.nextInt(16);
            
            (new WorldGenNetherMinable(blockID, veinSize)).generate(world, rand, Xcoord, Ycoord, Zcoord);
        }
    }
    
    /**
     * Add a structure generation entry with ease.
     * 
     * @param generator
     * @param blockX
     * @param blockZ
     * @param occurance
     * @param rand
     * @param world
     */
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
    
    public static void addFlowerGen(int blockID, int blockX, int blockZ, int occurance, int spread, Random rand, World world)
    {
        if(rand.nextInt(100) <= occurance)
        {
            int Xcoord1 = blockX + rand.nextInt(spread);
            int Ycoord1 = rand.nextInt(128);
            int Zcoord1 = blockZ + rand.nextInt(spread);
            
            (new WorldGenFlowers(blockID)).generate(world, rand, Xcoord1, Ycoord1, Zcoord1);
        }
    }
}
