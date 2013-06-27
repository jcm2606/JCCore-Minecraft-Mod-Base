package jcm2606.mods.jccore.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandlerCore implements IPacketHandler {
    public static final String CHANNEL_COMPAT_CONTAINER = "JCCoreCContainer";
    
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        if(packet.channel.equals(CHANNEL_COMPAT_CONTAINER))
        {
            handleCompatContainerUpdatePostPacket(manager, packet, player);
        }
    }
    
    private void handleCompatContainerUpdatePostPacket(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        String containerName;
        int updateID;
        
        try {
            containerName = inputStream.readUTF();
            updateID = inputStream.readInt();
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }
        
        CompatibilityContainer.getContainerList().get(containerName).postUpdateToSubContainers(updateID, null);
    }
    
    public static Packet constructCompatContainerUpdatePostPacket(String containerName, int updateID)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
                outputStream.writeUTF(containerName);
                outputStream.writeInt(updateID);
        } catch (Exception ex) {
                ex.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = CHANNEL_COMPAT_CONTAINER;
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        
        return packet;
    }
}
