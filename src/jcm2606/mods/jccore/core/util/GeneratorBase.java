package jcm2606.mods.jccore.core.util;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * Base class for any {@link IWorldGenerator} classes.
 * 
 * @author Jcm2606
 */
public abstract class GeneratorBase implements IWorldGenerator {
    
    /**
     * Instance of {@link GenerationUtil}.
     */
    protected GenerationUtil util = new GenerationUtil();
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId)
        {
        case -1: generateNether(world, random, chunkX*16, chunkZ*16);
        case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
        case 1: generateEnd(world, random, chunkX*16, chunkZ*16);
        }
    }
    
    /**
     * Hook for terrain generation in the Overworld.
     * 
     * @param world
     * @param rand
     * @param blockX
     * @param blockZ
     */
    public abstract void generateSurface(World world, Random rand, int blockX, int blockZ);
    
    /**
     * Hook for terrain generation in the Nether.
     * 
     * @param world
     * @param rand
     * @param blockX
     * @param blockZ
     */
    public abstract void generateNether(World world, Random rand, int blockX, int blockZ);
    
    /**
     * Hook for generation in the End.
     * 
     * @param world
     * @param rand
     * @param blockX
     * @param blockZ
     */
    public abstract void generateEnd(World world, Random rand, int blockX, int blockZ);
    
    /**
     * Hook for generation in any other dimension.
     * 
     * @param dimensionID
     * @param world
     * @param rand
     * @param blockX
     * @param blockZ
     */
    public abstract void generateOther(int dimensionID, World world, Random rand, int blockX, int blockZ);
}
