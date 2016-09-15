package malte0811.industrialWires.wires;

import blusunrize.immersiveengineering.api.energy.wires.ImmersiveNetHandler.Connection;
import blusunrize.immersiveengineering.api.energy.wires.WireType;
import malte0811.industrialWires.IndustrialWires;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IC2Wiretype extends WireType{
	final int type;
	final int[] ic2Rates = {32*8, 128*8, 512*8, 2048*8};
	final int[] ic2Colors = {0xa5bcc7, 0xbc7945, 0xfeff73, 0xb9d6d9};
	final String[] ic2Names = {"ic2Tin", "ic2Copper", "ic2Gold", "ic2Hv"};
	final double[] lossPerBlock = {.2, .2, .4, .8};
	final double[] ic2RenderDiameter = {.03125, .03125, .046875, .0625};
	public static final IC2Wiretype[] IC2_TYPES = {new IC2Wiretype(0), new IC2Wiretype(1), new IC2Wiretype(2), new IC2Wiretype(3)};
	public IC2Wiretype(int ordinal) {
		super();
		this.type = ordinal;
	}
	/**
	 * In this case, this does not return the loss RATIO but the loss PER BLOCK
	 */
	@Override
	public double getLossRatio() {
		return lossPerBlock[type];
	}
	@Override
	public int getTransferRate() {
		return ic2Rates[type];
	}
	@Override
	public int getColour(Connection connection) {
		return ic2Colors[type];
	}
	@Override
	public double getSlack() {
		return type==2?1.03:1.005;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getIcon(Connection connection) {
		return iconDefaultWire;
	}
	@Override
	public int getMaxLength() {
		return type==3?32:16;
	}
	@Override
	public ItemStack getWireCoil() {
		return new ItemStack(IndustrialWires.coil,1,type);
	}
	@Override
	public String getUniqueName() {
		return ic2Names[type];
	}
	@Override
	public double getRenderDiameter() {
		return ic2RenderDiameter[type];
	}
	@Override
	public boolean isEnergyWire() {
		return true;
	}
}