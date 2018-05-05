package net.journey.items;

import java.util.List;
import java.util.Map;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import com.google.common.collect.Maps;

public class ItemModRecord extends ItemRecord {

    private static final Map RECORDS = Maps.newHashMap();
    private String recordName = "";
    
	public ItemModRecord(String name, String finalName) {
		super(name);
		setUnlocalizedName(name + "Record");
		LangRegistry.addItem(name + "Record", finalName);
		setCreativeTab(JourneyTabs.util);
		JourneyItems.itemNames.add(name + "Record");
		this.recordName = name;
        this.maxStackSize = 1;
		RECORDS.put(name, this);
		GameRegistry.registerItem(this, name + "Record");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        if(iblockstate.getBlock() == Blocks.jukebox && !iblockstate.getValue(BlockJukebox.HAS_RECORD).booleanValue()) {
            if(worldIn.isRemote) {
                return true;
            } else {
                ((BlockJukebox)Blocks.jukebox).insertRecord(worldIn, pos, iblockstate, stack);
                worldIn.playAuxSFXAtEntity((EntityPlayer)null, 1005, pos, Item.getIdFromItem(this));
                --stack.stackSize;
                return true;
            }
        } else {
            return false;
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        tooltip.add(this.getRecordNameLocal());
    }

    @Override
	@SideOnly(Side.CLIENT)
    public String getRecordNameLocal() {
        return StatCollector.translateToLocal("item.record." + this.recordName + ".desc");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @SideOnly(Side.CLIENT)
    public static ItemModRecord getRecord(String name) {
        return (ItemModRecord)RECORDS.get(name);
    }
	
    @Override
	public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(SlayerAPI.PREFIX + "music." + name);
    }
}