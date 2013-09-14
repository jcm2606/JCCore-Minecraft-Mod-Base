package jcm2606.mods.jccore.util;

import java.io.File;
import java.util.Random;

import jcm2606.mods.jccore.item.ItemBlockJC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class GeneralUtil {
    public static int getBlockIdFromNeighbour(int x, int y, int z, ForgeDirection side, World world)
    {
        return world.getBlockId(x + side.offsetX, y + side.offsetY, z + side.offsetZ);
    }
    
    public static TileEntity getBlockTileFromNeighbour(int x, int y, int z, ForgeDirection side, World world)
    {
        return world.getBlockTileEntity(x + side.offsetX, y + side.offsetY, z + side.offsetZ);
    }
    
    public static boolean doesNeighbourBlockExist(int x, int y, int z, ForgeDirection side, World world)
    {
        return world.getBlockId(x + side.offsetX, y + side.offsetY, z + side.offsetZ) != 0;
    }
    
    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    public static File getWorldBaseSaveLocation(World world)
    {
        File savedir = getWorldSaveLocation(world);
        if (savedir == null) {
            return null;
        } else
            if (savedir.getName().contains("DIM")) {
                return savedir.getParentFile();
            } else {
                return savedir;
            }
    }

    public static File getWorldSaveLocation(World world, int dimension)
    {
        File basesave = getWorldBaseSaveLocation(world);
        if (dimension != 0) {
            return new File(basesave, world.provider.getSaveFolder());
        } else {
            return basesave;
        }
    }

    private static File getWorldSaveLocation(World world)
    {
        try {
            ISaveHandler worldsaver = world.getSaveHandler();
            IChunkLoader loader = worldsaver.getChunkLoader(world.provider);
            if (loader instanceof AnvilChunkLoader) {
                return ((AnvilChunkLoader) loader).chunkSaveLocation;
            }
            return null;
        }
        catch (IllegalAccessError e) {
            return ((WorldServer) world).getChunkSaveLocation();
        }
        catch (Exception e) {
            FMLCommonHandler.instance().raiseException(e, "SorceryCraft", true);
            return null;
        }
    }

    public static String getWorldName(World world)
    {
        return world.getWorldInfo().getWorldName();
    }

    public static int getDimension(World world)
    {
        return world.provider.dimensionId;
    }

    private final static String seperator = String.valueOf(File.pathSeparatorChar);
    
    public static String toPath(String prePath){
        return prePath.replace("|", seperator);
    }

    public static int getFreeBlockID(int preferred)
    {
        for (int i = preferred; i < 255; i++) {
            if (Block.blocksList[i] == null) {
                return i;
            }
        }
        for (int i = preferred; i > 0; i++) {
            if (Block.blocksList[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getFreeItemID(int preferred)
    {
        for (int i = preferred; i < 255; i++) {
            if (Item.itemsList[i] == null) {
                return i;
            }
        }
        for (int i = preferred; i > 0; i++) {
            if (Item.itemsList[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static boolean emulateBonemeal(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, Random itemRand)
    {
        if (stack == null) {
            return false;
        }
        int id = world.getBlockId(x, y, z);
        BonemealEvent event = new BonemealEvent(player, world, id, x, y, z);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return false;
        }
        if (event.getResult() == Event.Result.ALLOW) {
            return true;
        }

        if (id == Block.sapling.blockID) {
            if (!world.isRemote) {
                ((BlockSapling) Block.sapling).growTree(world, x, y, z, world.rand);
            }
            return true;
        }

        if ((id == Block.mushroomBrown.blockID) || (id == Block.mushroomRed.blockID)) {
            if ((!world.isRemote) && (((BlockMushroom) Block.blocksList[id]).fertilizeMushroom(world, x, y, z, world.rand))) {
                ;
            }
            return true;
        }

        if ((id == Block.melonStem.blockID) || (id == Block.pumpkinStem.blockID)) {
            if (world.getBlockMetadata(x, y, z) == 7) {
                return false;
            }

            if (!world.isRemote) {
                ((BlockStem) Block.blocksList[id]).fertilizeStem(world, x, y, z);
            }
            return true;
        }

        if ((id > 0) && ((Block.blocksList[id] instanceof BlockCrops))) {
            if (world.getBlockMetadata(x, y, z) == 7) {
                return false;
            }

            if (!world.isRemote) {
                ((BlockCrops) Block.blocksList[id]).fertilize(world, x, y, z);
            }
            return true;
        }
        if (id == Block.cocoaPlant.blockID) {
            if (!world.isRemote) {
                world.setBlock(x, y, z, 0x8 | BlockDirectional.getDirection(world.getBlockMetadata(x, y, z)));
            }
            return true;
        }

        if (id == Block.grass.blockID) {
            if (!world.isRemote) {
                label541: for (int i = 0; i < 128; i++) {
                    int x1 = x;
                    int x2 = y + 1;
                    int x3 = z;

                    for (int j = 0; j < i / 16; j++) {
                        x1 += itemRand.nextInt(3) - 1;
                        x2 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                        x3 += itemRand.nextInt(3) - 1;

                        if ((world.getBlockId(x1, x2 - 1, x3) != Block.grass.blockID) || (world.isBlockNormalCube(x1, x2, x3))) {
                            break label541;
                        }
                    }
                    if (world.getBlockId(x1, x2, x3) == 0) {
                        if (itemRand.nextInt(10) != 0) {
                            if (Block.tallGrass.canBlockStay(world, x1, x2, x3)) {
                                world.setBlock(x1, x2, x3, Block.tallGrass.blockID, 1, 0x02);
                            }
                        } else {
                            ForgeHooks.plantGrass(world, x1, x2, x3);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isBlock(int ID)
    {
        return ID < Block.blocksList.length && Block.blocksList[ID] != null && Block.blocksList[ID].blockID != 0;
    }

    public static ItemStack convertObjectToItemStack(Object obj)
    {
        if (obj instanceof Item) {
            return new ItemStack((Item) obj);
        } else
            if (obj instanceof Block) {
                return new ItemStack((Block) obj);
            } else
                if (obj instanceof ItemStack) {
                    return (ItemStack) obj;
                } else {
                    return null;
                }
    }

    public static boolean isHostileEntity(EntityLiving entity)
    {
        if (entity instanceof EntityMob) {
            return true;
        } else {
            return false;
        }
    }

    public static void registerBlock(net.minecraft.block.Block block)
    {
        registerBlock(block, ItemBlockJC.class);
    }
    
    public static void registerBlock(net.minecraft.block.Block block, Class<? extends ItemBlock> itemBlock)
    {
        GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName());
    }

    public static MovingObjectPosition getTargetBlock(World world, EntityPlayer player, boolean par3, float maxDistance)
    {
        float var4 = 1.0F;
        float var5 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * var4;
        float var6 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * var4;
        double var7 = player.prevPosX + (player.posX - player.prevPosX) * var4;
        double var9 = player.prevPosY + (player.posY - player.prevPosY) * var4 + 1.62D - player.yOffset;
        double var11 = player.prevPosZ + (player.posZ - player.prevPosZ) * var4;
        Vec3 var13 = world.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
        float var14 = MathHelper.cos(-var6 * 0.01745329F - 3.141593F);
        float var15 = MathHelper.sin(-var6 * 0.01745329F - 3.141593F);
        float var16 = -MathHelper.cos(-var5 * 0.01745329F);
        float var17 = MathHelper.sin(-var5 * 0.01745329F);
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = maxDistance;
        Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
        return world.rayTraceBlocks_do_do(var13, var23, par3, !par3);
    }
    
    public static boolean areStacksEqual(ItemStack stack1, ItemStack stack2, boolean checkDamage)
    {
        if(stack1 == null || stack2 == null)
        {
            return false;
        }
        
        if(checkDamage)
        {
            return stack1.itemID == stack2.itemID && stack1.getItemDamage() == stack2.getItemDamage();
        } else {
            return stack1.itemID == stack2.itemID;
        }
    }
}
