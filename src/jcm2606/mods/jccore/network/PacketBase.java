package jcm2606.mods.jccore.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

/**
 * Thanks to the EE3 source for this class!
 * 
 * @author pahimar
 */
public abstract class PacketBase
{
    public PacketType packetType;
    public boolean isChunkDataPacket;
    
    public PacketBase(PacketType packetType, boolean isChunkDataPacket)
    {
        this.packetType = packetType;
        this.isChunkDataPacket = isChunkDataPacket;
    }
    
    public byte[] populate()
    {
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        
        try
        {
            dos.writeByte(packetType.ordinal());
            this.writeData(dos);
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
        
        return bos.toByteArray();
    }
    
    public void readPopulate(DataInputStream data)
    {
        
        try
        {
            this.readData(data);
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    public abstract void readData(DataInputStream data) throws IOException;
    
    public abstract void writeData(DataOutputStream dos) throws IOException;
    
    public abstract void execute(INetworkManager network, Player player);
    
    public void setKey(int key)
    {
        
    }
}
