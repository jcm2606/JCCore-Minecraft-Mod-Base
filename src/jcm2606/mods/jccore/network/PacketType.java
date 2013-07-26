package jcm2606.mods.jccore.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Thanks to the EE3 source for this class!
 * 
 * @author pahimar
 */
public enum PacketType {
    COMPAT_CONTAINER_POST_UPDATE(PacketCompatContainerPostUpdate.class);
    
    private Class<? extends PacketBase> clazz;

    PacketType(Class<? extends PacketBase> clazz) {
        this.clazz = clazz;
    }

    public static PacketBase buildPacket(byte[] data) {

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);

        PacketBase packet = null;

        try {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        packet.readPopulate(dis);

        return packet;
    }

    public static PacketBase buildPacket(PacketType type) {

        PacketBase packet = null;

        try {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return packet;
    }

    public static Packet populatePacket(PacketBase packetBase, String channel) {

        byte[] data = packetBase.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = channel;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packetBase.isChunkDataPacket;

        return packet250;
    }
}
