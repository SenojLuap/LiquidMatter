package paujo.liquidMatter.mod.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import paujo.liquidMatter.mod.tileentities.TileEntityCrucible;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {
	
	public static final byte CRUCIBLE_PACKET_ID = 0;

	@Override
  public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
		
		byte packetId = reader.readByte();
		switch (packetId) {
			case CRUCIBLE_PACKET_ID: {
				EntityPlayer entPlayer = (EntityPlayer)player;
				TileEntityCrucible tileEntity = (TileEntityCrucible)entPlayer.worldObj.getBlockTileEntity(reader.readInt(), reader.readInt(),
						reader.readInt());
				if (tileEntity != null) tileEntity.setFluidLevel(reader.readInt());
				}
			}
		}
	
	/**
	 * Updates the client side crucibles with the current liquid matter amount
	 * @param crucible
	 */
	public static void sendCrucibleInfo(TileEntityCrucible crucible) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try {
			dataStream.writeByte(CRUCIBLE_PACKET_ID);
			
			dataStream.writeInt(crucible.xCoord);
			dataStream.writeInt(crucible.yCoord);
			dataStream.writeInt(crucible.zCoord);
			
			dataStream.writeInt(crucible.tank.getFluidAmount());
			
			PacketDispatcher.sendPacketToAllAround(crucible.xCoord, crucible.yCoord, crucible.zCoord, 80,
					crucible.getWorldObj().provider.dimensionId, (PacketDispatcher.getPacket("lm_comms",
							byteStream.toByteArray())));
		} catch (IOException ex) {
			System.err.append("Failed to send Crucible Info");
		}
	}

}
