package net.journey.items;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangHelper;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModRecord extends ItemRecord {

    private static final Map RECORDS = Maps.newHashMap();
    private final SoundEvent sound;
    private String soundName;

	public ItemModRecord(String name, String finalName, SoundEvent sound) {
		super(name, sound);
		setUnlocalizedName(name + "Record");
		soundName = name;
		LangRegistry.addItem(name + "Record", finalName);
		setCreativeTab(JourneyTabs.util);
		JourneyItems.itemNames.add(name + "Record");
		this.sound = sound;
        this.maxStackSize = 1;
		RECORDS.put(name, this);
		JourneyItems.items.add(this);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        if(iblockstate.getBlock() == Blocks.JUKEBOX && !iblockstate.getValue(BlockJukebox.HAS_RECORD).booleanValue()) {
            if(worldIn.isRemote) {
                return true;
            } else {
                ((BlockJukebox)Blocks.JUKEBOX).insertRecord(worldIn, pos, iblockstate, stack);
                worldIn.playAuxSFXAtEntity((EntityPlayer)null, 1005, pos, Item.getIdFromItem(this));
                stack.shrink(1);
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
        return LangHelper.getFormattedText("item.record." + this.soundName + ".desc");
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