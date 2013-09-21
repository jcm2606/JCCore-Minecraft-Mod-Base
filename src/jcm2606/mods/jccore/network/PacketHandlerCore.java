package jcm2606.mods.jccore.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandlerCore implements IPacketHandler
{
    public static final String CHANNEL_JCCORE = "JCCore";
    
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        PacketBase packetBase = PacketType.buildPacket(packet.data);
        
        packetBase.execute(manager, player);
    }
}
