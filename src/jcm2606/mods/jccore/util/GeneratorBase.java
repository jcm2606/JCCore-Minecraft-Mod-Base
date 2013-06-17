package jcm2606.mods.jccore.util;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public abstract class GeneratorBase implements IWorldGenerator {
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
    
    public abstract void generateSurface(World world, Random rand, int blockX, int blockZ);
    
    public abstract void generateNether(World world, Random rand, int blockX, int blockZ);
    
    public abstract void generateEnd(World world, Random rand, int blockX, int blockZ);
}
