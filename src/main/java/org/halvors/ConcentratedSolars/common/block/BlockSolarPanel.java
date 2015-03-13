package org.halvors.ConcentratedSolars.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.halvors.ConcentratedSolars.ConcentratedSolars;
import org.halvors.ConcentratedSolars.Reference;
import org.halvors.ConcentratedSolars.common.tileentity.TileEntitySolarPanel;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSolarPanel extends BlockContainer {
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon, sideIcon, bottomIcon;

	public BlockSolarPanel() {
		super(Material.iron);

		setBlockName("blockSolarPanel");
		setHardness(3.5F);
		setResistance(8F);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3F, 1.0F);
		setCreativeTab(ConcentratedSolars.tabConcentratedSolars);
		setStepSound(soundTypeMetal);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntitySolarPanel();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		topIcon = iconRegister.registerIcon(Reference.PREFIX
				+ "blockSolarPanel_top");
		sideIcon = iconRegister.registerIcon(Reference.PREFIX
				+ "blockSolarPanel_side");
		bottomIcon = iconRegister.registerIcon(Reference.PREFIX
				+ "blockSolarPanel_bottom");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (side) {
		case 1:
			return topIcon;

		case 0:
			return bottomIcon;

		default:
			return sideIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int metadata, float what, float these,
			float are) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if (tileEntity == null || player.isSneaking()) {
			return false;
		}

		player.openGui(ConcentratedSolars.instance, 0, world, x, y, z);

		return true;
	}
}
