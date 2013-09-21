package jcm2606.mods.jccore.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

public class PacketCompatContainerPostUpdate extends PacketBase
{
    public String containerName;
    public int updateID;
    
    public PacketCompatContainerPostUpdate()
    {
        super(PacketType.COMPAT_CONTAINER_POST_UPDATE, false);
    }
    
    public PacketCompatContainerPostUpdate(String containerName, int updateID)
    {
        super(PacketType.COMPAT_CONTAINER_POST_UPDATE, false);
        this.containerName = containerName;
        this.updateID = updateID;
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        this.containerName = data.readUTF();
        this.updateID = data.readInt();
    }
    
    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
        dos.writeUTF(containerName);
        dos.writeInt(updateID);
    }
    
    @Override
    public void execute(INetworkManager network, Player player)
    {
        CompatibilityContainer.getContainerList().get(containerName).postUpdate(updateID, null);
    }
}
