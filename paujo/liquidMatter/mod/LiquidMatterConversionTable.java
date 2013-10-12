package paujo.liquidMatter.mod;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import paujo.liquidMatter.mod.items.LiquidMatterItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class LiquidMatterConversionTable {
	
	static private class LMVTableEntry {
		public final int id;
		public final int meta;
		public LMVTableEntry(int id, int meta) {
			this.id = id;
			this.meta = meta;
		}
		@Override
		public boolean equals(Object other) {
			if (other instanceof LMVTableEntry) {
				LMVTableEntry otherEntry = (LMVTableEntry)other;
				return otherEntry.id == this.id && (otherEntry.meta == this.meta || this.meta == -1);
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return id;
		}
	}

	public static Hashtable<LMVTableEntry, Integer> table;
	
	
	/**
	 * Returns the unlocalized name of an item with the specified damage value
	 * @param item the base item
	 * @param damage the damage value of the item
	 * @return the unlocalized name of an item with the specified damage value
	 */
	public static String getUnlocalizedName(Item item, int damage) {
		return item.getUnlocalizedName(new ItemStack(item, 1, damage));
	}
	
	public static void init() {
		table = new Hashtable<LMVTableEntry, Integer>(256);
		
		Configuration config = new Configuration(new File("config/" + LiquidMatter.modid + "_ConversionValues.cfg"));
		config.load();
		
    System.out.println("Initializing alchemy values for Equivalent Exchange..");

    for (int dmg = 0; dmg < 4; dmg++) {
      addLiquidMatterValue(config, Block.leaves.blockID, dmg, 1, Block.leaves.getUnlocalizedName());
      addLiquidMatterValue(config, Block.wood.blockID, dmg, 32, Block.wood.getUnlocalizedName());
      addLiquidMatterValue(config, Block.sapling.blockID, dmg, getLiquidMatterValue(Block.wood.blockID), Block.sapling.getUnlocalizedName());
    }
    addLiquidMatterValue(config, Item.coal.itemID, 1, getLiquidMatterValue(Block.wood.blockID), getUnlocalizedName(Item.coal, 1));
    addLiquidMatterValue(config, Item.redstone.itemID, 0, getLiquidMatterValue(Item.coal.itemID, 1) * 2, Item.redstone.getUnlocalizedName());
    addLiquidMatterValue(config, Item.coal.itemID, 0, getLiquidMatterValue(Item.coal.itemID, 1) * 4, getUnlocalizedName(Item.coal, 0));
    addLiquidMatterValue(config, Block.planks.blockID, getLiquidMatterValue(Block.wood.blockID) / 4, Block.planks.getUnlocalizedName());
    addLiquidMatterValue(config, Block.workbench.blockID, getLiquidMatterValue(Block.planks.blockID) * 4, Block.workbench.getUnlocalizedName());
    addLiquidMatterValue(config, Item.doorWood.itemID, getLiquidMatterValue(Block.planks.blockID) * 6, Item.doorWood.getUnlocalizedName());
    addLiquidMatterValue(config, Block.chest.blockID, getLiquidMatterValue(Block.planks.blockID) * 8, Block.chest.getUnlocalizedName());
    
    addLiquidMatterValue(config, Block.stairsWoodOak.blockID, getLiquidMatterValue(Block.planks.blockID) * 6 / 4, Block.stairsWoodOak.getUnlocalizedName());
    addLiquidMatterValue(config, Block.stairsWoodBirch.blockID, getLiquidMatterValue(Block.planks.blockID) * 6 / 4, Block.stairsWoodBirch.getUnlocalizedName());
    addLiquidMatterValue(config, Block.stairsWoodJungle.blockID, getLiquidMatterValue(Block.planks.blockID) * 6 / 4, Block.stairsWoodJungle.getUnlocalizedName());
    addLiquidMatterValue(config, Block.stairsWoodSpruce.blockID, getLiquidMatterValue(Block.planks.blockID) * 6 / 4, Block.stairsWoodSpruce.getUnlocalizedName());

    addLiquidMatterValue(config, Block.woodenButton.blockID, getLiquidMatterValue(Block.planks.blockID), Block.woodenButton.getUnlocalizedName());
//    addLiquidMatterValue(config, Block.woodSingleSlab.blockID, getLiquidMatterValue(Block.planks.blockID) / 2, Block.woodSingleSlab.getUnlocalizedName());
    
    addLiquidMatterValue(config, Item.silk.itemID, 12, Item.silk.getUnlocalizedName());

    addLiquidMatterValue(config, Item.boat.itemID, getLiquidMatterValue(Block.planks.blockID) * 5, Item.boat.getUnlocalizedName());
    addLiquidMatterValue(config, Block.pressurePlatePlanks.blockID, getLiquidMatterValue(Block.planks.blockID) * 2, Block.pressurePlatePlanks.getUnlocalizedName());
    addLiquidMatterValue(config, Block.trapdoor.blockID, getLiquidMatterValue(Block.planks.blockID) * 6 / 2, Block.trapdoor.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bowlEmpty.itemID, getLiquidMatterValue(Block.planks.blockID) * 3 / 4, Item.bowlEmpty.getUnlocalizedName());
    addLiquidMatterValue(config, Item.stick.itemID, getLiquidMatterValue(Block.planks.blockID) / 2, Item.stick.getUnlocalizedName());
    addLiquidMatterValue(config, Block.fence.blockID, getLiquidMatterValue(Item.stick.itemID) * 6 / 2, Block.fence.getUnlocalizedName());
    addLiquidMatterValue(config, Block.fenceGate.blockID, getLiquidMatterValue(Block.planks.blockID) * 2 + getLiquidMatterValue(Item.stick.itemID) * 4, Block.fenceGate.getUnlocalizedName());
    addLiquidMatterValue(config, Item.sign.itemID, getLiquidMatterValue(Block.planks.blockID) * 6 + getLiquidMatterValue(Item.stick.itemID), Item.sign.getUnlocalizedName());
    addLiquidMatterValue(config, Block.ladder.blockID, getLiquidMatterValue(Item.stick.itemID) * 7 / 2, Block.ladder.getUnlocalizedName());
    addLiquidMatterValue(config, Item.fishingRod.itemID, getLiquidMatterValue(Item.stick.itemID) * 3 + getLiquidMatterValue(Item.silk.itemID) * 2, Item.fishingRod.getUnlocalizedName());
    addLiquidMatterValue(config, Item.shovelWood.itemID, getLiquidMatterValue(Block.planks.blockID) + getLiquidMatterValue(Item.stick.itemID) * 2, Item.shovelWood.getUnlocalizedName());
    addLiquidMatterValue(config, Item.swordWood.itemID, getLiquidMatterValue(Block.planks.blockID) * 2 + getLiquidMatterValue(Item.stick.itemID), Item.swordWood.getUnlocalizedName());
    addLiquidMatterValue(config, Item.hoeWood.itemID, getLiquidMatterValue(Block.planks.blockID) * 2 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.hoeWood.getUnlocalizedName());
    addLiquidMatterValue(config, Item.pickaxeWood.itemID, getLiquidMatterValue(Block.planks.blockID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.pickaxeWood.getUnlocalizedName());
    addLiquidMatterValue(config, Item.axeWood.itemID, getLiquidMatterValue(Block.planks.blockID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.axeWood.getUnlocalizedName());

    addLiquidMatterValue(config, Item.bone.itemID, 144, Item.bone.getUnlocalizedName());

    for (int dmg = 0; dmg < 16; dmg++) {
      if (dmg == 15)
        addLiquidMatterValue(config, Item.dyePowder.itemID, dmg, getLiquidMatterValue(Item.bone.itemID) / 3, getUnlocalizedName(Item.dyePowder, dmg));
      else if (dmg == 4)
        addLiquidMatterValue(config, Item.dyePowder.itemID, dmg, 864, getUnlocalizedName(Item.dyePowder, dmg));
      else if (dmg == 3)
        addLiquidMatterValue(config, Item.dyePowder.itemID, dmg, 128, getUnlocalizedName(Item.dyePowder, dmg));
      else
        addLiquidMatterValue(config, Item.dyePowder.itemID, dmg, 8, getUnlocalizedName(Item.dyePowder, dmg));
    }
    
    addLiquidMatterValue(config, Block.blockLapis.blockID, getLiquidMatterValue(Item.dyePowder.itemID, 4) * 9, Block.blockLapis.getUnlocalizedName());
    addLiquidMatterValue(config, Block.plantYellow.blockID, getLiquidMatterValue(Item.dyePowder.itemID, 11) * 2, Block.plantYellow.getUnlocalizedName());
    addLiquidMatterValue(config, Block.plantRed.blockID, getLiquidMatterValue(Item.dyePowder.itemID, 1) * 2, Block.plantRed.getUnlocalizedName());

    addLiquidMatterValue(config, Block.dirt.blockID, 1, Block.dirt.getUnlocalizedName());
    addLiquidMatterValue(config, Block.sand.blockID, getLiquidMatterValue(Block.dirt.blockID), Block.sand.getUnlocalizedName());
    addLiquidMatterValue(config, Block.grass.blockID, getLiquidMatterValue(Block.dirt.blockID), Block.grass.getUnlocalizedName());
    addLiquidMatterValue(config, Block.mycelium.blockID, getLiquidMatterValue(Block.grass.blockID), Block.mycelium.getUnlocalizedName());
    addLiquidMatterValue(config, Block.tallGrass.blockID, getLiquidMatterValue(Block.grass.blockID), Block.tallGrass.getUnlocalizedName());
    addLiquidMatterValue(config, Block.deadBush.blockID, getLiquidMatterValue(Block.tallGrass.blockID), Block.deadBush.getUnlocalizedName());
    addLiquidMatterValue(config, Block.waterlily.blockID, 16, Block.waterlily.getUnlocalizedName());
    addLiquidMatterValue(config, Block.vine.blockID, 8, Block.vine.getUnlocalizedName());
    addLiquidMatterValue(config, Block.sandStone.blockID, getLiquidMatterValue(Block.sand.blockID) * 4, Block.sandStone.getUnlocalizedName());
    
    addLiquidMatterValue(config, Block.stairsSandStone.blockID, getLiquidMatterValue(Block.sandStone.blockID) / 2, Block.stairsSandStone.getUnlocalizedName());
    
    addLiquidMatterValue(config, Block.glass.blockID, getLiquidMatterValue(Block.sand.blockID), Block.glass.getUnlocalizedName());
    addLiquidMatterValue(config, Item.glassBottle.itemID, getLiquidMatterValue(Block.glass.blockID), Item.glassBottle.getUnlocalizedName());

    addLiquidMatterValue(config, Block.gravel.blockID, getLiquidMatterValue(Block.sandStone.blockID), Block.gravel.getUnlocalizedName());
    addLiquidMatterValue(config, Item.flint.itemID, getLiquidMatterValue(Block.gravel.blockID), Item.flint.getUnlocalizedName());
    addLiquidMatterValue(config, Block.netherBrick.blockID, getLiquidMatterValue(Block.gravel.blockID), Block.netherBrick.getUnlocalizedName());

    addLiquidMatterValue(config, Block.netherFence.blockID, getLiquidMatterValue(Block.netherBrick.blockID), Block.netherFence.getUnlocalizedName());
    addLiquidMatterValue(config, Block.stairsNetherBrick.blockID, getLiquidMatterValue(Block.netherBrick.blockID) * 6 / 4, Block.stairsNetherBrick.getUnlocalizedName());

    addLiquidMatterValue(config, Item.feather.itemID, 48, Item.feather.getUnlocalizedName());
    addLiquidMatterValue(config, Item.arrow.itemID, (getLiquidMatterValue(Item.stick.itemID) + getLiquidMatterValue(Item.flint.itemID) + getLiquidMatterValue(Item.feather.itemID)) / 4, Item.arrow.getUnlocalizedName());

    addLiquidMatterValue(config, Block.cobblestone.blockID, 1, Block.cobblestone.getUnlocalizedName());
    
    addLiquidMatterValue(config, Block.furnaceIdle.blockID, getLiquidMatterValue(Block.cobblestone.blockID) * 8, Block.furnaceIdle.getUnlocalizedName());
//    addLiquidMatterValue(config, Block.co.blockID, 3, getLiquidMatterValue(Block.cobblestone.blockID));
    addLiquidMatterValue(config, Block.stairsCobblestone.blockID, getLiquidMatterValue(Block.cobblestone.blockID) * 6 / 4, Block.stairsCobblestone.getUnlocalizedName());
    
    addLiquidMatterValue(config, Block.lever.blockID, getLiquidMatterValue(Block.cobblestone.blockID) + getLiquidMatterValue(Item.stick.itemID), Block.lever.getUnlocalizedName());
    addLiquidMatterValue(config, Block.netherrack.blockID, getLiquidMatterValue(Block.cobblestone.blockID), Block.netherrack.getUnlocalizedName());
    addLiquidMatterValue(config, Block.stone.blockID, getLiquidMatterValue(Block.cobblestone.blockID), Block.stone.getUnlocalizedName());
    addLiquidMatterValue(config, Block.whiteStone.blockID, getLiquidMatterValue(Block.netherrack.blockID), Block.whiteStone.getUnlocalizedName());
    
    addLiquidMatterValue(config, Block.stoneButton.blockID, getLiquidMatterValue(Block.stone.blockID) * 2, Block.stoneButton.getUnlocalizedName());
    
    addLiquidMatterValue(config, Block.pressurePlateStone.blockID, getLiquidMatterValue(Block.stone.blockID) * 2, Block.pressurePlateStone.getUnlocalizedName());
    
//    addLiquidMatterValue(config, Block.stairSingle.blockID, 0, getLiquidMatterValue(Block.stone.blockID));

    for (int dmg = 0; dmg < 4; dmg++)
      addLiquidMatterValue(config, Block.stoneBrick.blockID, dmg, getLiquidMatterValue(Block.stone.blockID), Block.stoneBrick.getUnlocalizedName());
    
    
//    addLiquidMatterValue(config, Block.stairSingle.blockID, 5, getLiquidMatterValue(Block.stoneBrick.blockID));
    addLiquidMatterValue(config, Block.stairsStoneBrick.blockID, getLiquidMatterValue(Block.stoneBrick.blockID) * 6 / 4, Block.stairsStoneBrick.getUnlocalizedName());
    
    addLiquidMatterValue(config, Item.shovelStone.itemID, getLiquidMatterValue(Block.cobblestone.blockID) + getLiquidMatterValue(Item.stick.itemID) * 2, Item.shovelStone.getUnlocalizedName());
    addLiquidMatterValue(config, Item.swordStone.itemID, getLiquidMatterValue(Block.cobblestone.blockID) * 2 + getLiquidMatterValue(Item.stick.itemID), Item.swordStone.getUnlocalizedName());
    addLiquidMatterValue(config, Item.hoeStone.itemID, getLiquidMatterValue(Block.cobblestone.blockID) * 2 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.hoeStone.getUnlocalizedName());
    addLiquidMatterValue(config, Item.pickaxeStone.itemID, getLiquidMatterValue(Block.cobblestone.blockID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.pickaxeStone.getUnlocalizedName());
    addLiquidMatterValue(config, Item.axeStone.itemID, getLiquidMatterValue(Block.cobblestone.blockID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.axeStone.getUnlocalizedName());

    addLiquidMatterValue(config, Item.bow.itemID, getLiquidMatterValue(Item.stick.itemID) * 3 + getLiquidMatterValue(Item.silk.itemID) * 3, Item.bow.getUnlocalizedName());
    addLiquidMatterValue(config, Item.slimeBall.itemID, 24, Item.slimeBall.getUnlocalizedName());
    addLiquidMatterValue(config, Block.web.blockID, (getLiquidMatterValue(Item.silk.itemID) * 2 + getLiquidMatterValue(Item.slimeBall.itemID)) / 4, Block.web.getUnlocalizedName());
    addLiquidMatterValue(config, Block.cobblestoneMossy.blockID, getLiquidMatterValue(Block.cobblestone.blockID) + getLiquidMatterValue(Item.seeds.itemID) + getLiquidMatterValue(Item.slimeBall.itemID) * 6, Block.cobblestoneMossy.getUnlocalizedName());
    
    for (int dmg = 0; dmg < 16; dmg++) {
      addLiquidMatterValue(config, Block.cloth.blockID, dmg, getLiquidMatterValue(Item.silk.itemID) * 4, Block.cloth.getUnlocalizedName());
    }
    
    addLiquidMatterValue(config, Item.bed.itemID, getLiquidMatterValue(Block.cloth.blockID) * 3 + getLiquidMatterValue(Block.planks.blockID * 3), Item.bed.getUnlocalizedName());
    addLiquidMatterValue(config, Item.painting.itemID, getLiquidMatterValue(Block.cloth.blockID) + getLiquidMatterValue(Item.stick.itemID) * 8, Item.painting.getUnlocalizedName());

    addLiquidMatterValue(config, Item.seeds.itemID, 16, Item.seeds.getUnlocalizedName());
    addLiquidMatterValue(config, Item.reed.itemID, 32, Item.reed.getUnlocalizedName());
    addLiquidMatterValue(config, Item.sugar.itemID, getLiquidMatterValue(Item.reed.itemID), Item.sugar.getUnlocalizedName());
    addLiquidMatterValue(config, Item.paper.itemID, getLiquidMatterValue(Item.reed.itemID), Item.paper.getUnlocalizedName());
    addLiquidMatterValue(config, Item.book.itemID, getLiquidMatterValue(Item.paper.itemID) * 3, Item.book.getUnlocalizedName());
    addLiquidMatterValue(config, Block.bookShelf.blockID, getLiquidMatterValue(Item.book.itemID) * 3 + getLiquidMatterValue(Block.planks.blockID) * 6, Block.bookShelf.getUnlocalizedName());
    addLiquidMatterValue(config, Item.wheat.itemID, 24, Item.wheat.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bread.itemID, getLiquidMatterValue(Item.wheat.itemID) * 3, Item.bread.getUnlocalizedName());
    addLiquidMatterValue(config, Item.cookie.itemID, (getLiquidMatterValue(Item.wheat.itemID) * 2 + getLiquidMatterValue(Item.dyePowder.itemID, 3)) / 8, Item.cookie.getUnlocalizedName());
    addLiquidMatterValue(config, Block.cactus.blockID, 8, Block.cactus.getUnlocalizedName());
    addLiquidMatterValue(config, Item.melon.itemID, 16, Item.melon.getUnlocalizedName());
    addLiquidMatterValue(config, Item.melonSeeds.itemID, getLiquidMatterValue(Item.melon.itemID), Item.melonSeeds.getUnlocalizedName());
    addLiquidMatterValue(config, Block.melon.blockID, getLiquidMatterValue(Item.melon.itemID) * 9, Block.melon.getUnlocalizedName());
    addLiquidMatterValue(config, Block.pumpkin.blockID, 144, Block.pumpkin.getUnlocalizedName());
    addLiquidMatterValue(config, Block.pumpkinLantern.blockID, getLiquidMatterValue(Block.pumpkin.blockID) + getLiquidMatterValue(Block.torchWood.blockID), Block.pumpkinLantern.getUnlocalizedName());
    addLiquidMatterValue(config, Item.pumpkinSeeds.itemID, getLiquidMatterValue(Block.pumpkin.blockID) / 4, Item.pumpkinSeeds.getUnlocalizedName());
    addLiquidMatterValue(config, Block.mushroomBrown.blockID, 32, Block.mushroomBrown.getUnlocalizedName());
    addLiquidMatterValue(config, Block.mushroomRed.blockID, getLiquidMatterValue(Block.mushroomBrown.blockID), Block.mushroomRed.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bowlSoup.itemID, getLiquidMatterValue(Item.bowlEmpty.itemID) + getLiquidMatterValue(Block.mushroomBrown.blockID) * 2, Item.bowlSoup.getUnlocalizedName());

    addLiquidMatterValue(config, Item.leather.itemID, 64, Item.leather.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bootsLeather.itemID, getLiquidMatterValue(Item.leather.itemID) * 4, Item.bootsLeather.getUnlocalizedName());
    addLiquidMatterValue(config, Item.helmetLeather.itemID, getLiquidMatterValue(Item.leather.itemID) * 5, Item.helmetLeather.getUnlocalizedName());
    addLiquidMatterValue(config, Item.legsLeather.itemID, getLiquidMatterValue(Item.leather.itemID) * 7, Item.legsLeather.getUnlocalizedName());
    addLiquidMatterValue(config, Item.plateLeather.itemID, getLiquidMatterValue(Item.leather.itemID) * 8, Item.plateLeather.getUnlocalizedName());
    addLiquidMatterValue(config, Item.rottenFlesh.itemID, 24, Item.rottenFlesh.getUnlocalizedName());
    addLiquidMatterValue(config, Item.appleRed.itemID, 128, Item.appleRed.getUnlocalizedName());
    addLiquidMatterValue(config, Item.egg.itemID, 32, Item.egg.getUnlocalizedName());
    addLiquidMatterValue(config, Item.blazeRod.itemID, 1536, Item.blazeRod.getUnlocalizedName());
    addLiquidMatterValue(config, Item.blazePowder.itemID, getLiquidMatterValue(Item.blazeRod.itemID) / 4, Item.blazePowder.getUnlocalizedName());

    addLiquidMatterValue(config, Item.fireballCharge.itemID, (getLiquidMatterValue(Item.blazePowder.itemID) + getLiquidMatterValue(Item.coal.itemID) + getLiquidMatterValue(Item.gunpowder.itemID)) / 3, Item.fireballCharge.getUnlocalizedName());

    addLiquidMatterValue(config, Item.magmaCream.itemID, getLiquidMatterValue(Item.blazePowder.itemID) + getLiquidMatterValue(Item.slimeBall.itemID), Item.magmaCream.getUnlocalizedName());
    addLiquidMatterValue(config, Block.brewingStand.blockID, getLiquidMatterValue(Item.blazeRod.itemID) + getLiquidMatterValue(Block.cobblestone.blockID) * 3, Block.brewingStand.getUnlocalizedName());
    addLiquidMatterValue(config, Item.enderPearl.itemID, 1024, Item.enderPearl.getUnlocalizedName());
    addLiquidMatterValue(config, Item.eyeOfEnder.itemID, getLiquidMatterValue(Item.enderPearl.itemID) + getLiquidMatterValue(Item.blazePowder.itemID), Item.eyeOfEnder.getUnlocalizedName());
    addLiquidMatterValue(config, Item.spiderEye.itemID, 128, Item.spiderEye.getUnlocalizedName());
    addLiquidMatterValue(config, Item.fermentedSpiderEye.itemID, getLiquidMatterValue(Item.spiderEye.itemID) + getLiquidMatterValue(Block.mushroomBrown.blockID) + getLiquidMatterValue(Item.sugar.itemID), Item.fermentedSpiderEye.getUnlocalizedName());
    addLiquidMatterValue(config, Item.netherStalkSeeds.itemID, 24, Item.netherStalkSeeds.getUnlocalizedName());

    addLiquidMatterValue(config, Item.porkRaw.itemID, 64, Item.porkRaw.getUnlocalizedName());
    addLiquidMatterValue(config, Item.beefRaw.itemID, getLiquidMatterValue(Item.porkRaw.itemID), Item.beefRaw.getUnlocalizedName());
    addLiquidMatterValue(config, Item.chickenRaw.itemID, getLiquidMatterValue(Item.porkRaw.itemID), Item.chickenRaw.getUnlocalizedName());
    addLiquidMatterValue(config, Item.fishRaw.itemID, getLiquidMatterValue(Item.porkRaw.itemID), Item.fishRaw.getUnlocalizedName());
    addLiquidMatterValue(config, Item.porkCooked.itemID, getLiquidMatterValue(Item.porkRaw.itemID), Item.porkCooked.getUnlocalizedName());
    addLiquidMatterValue(config, Item.beefCooked.itemID, getLiquidMatterValue(Item.beefRaw.itemID), Item.beefCooked.getUnlocalizedName());
    addLiquidMatterValue(config, Item.chickenCooked.itemID, getLiquidMatterValue(Item.chickenRaw.itemID), Item.chickenCooked.getUnlocalizedName());
    addLiquidMatterValue(config, Item.fishCooked.itemID, getLiquidMatterValue(Item.fishRaw.itemID), Item.fishCooked.getUnlocalizedName());

    addLiquidMatterValue(config, Item.clay.itemID, 16, Item.clay.getUnlocalizedName());
    addLiquidMatterValue(config, Block.blockClay.blockID, getLiquidMatterValue(Item.clay.itemID) * 4, Block.blockClay.getUnlocalizedName());
    addLiquidMatterValue(config, Item.brick.itemID, getLiquidMatterValue(Item.clay.itemID), Item.brick.getUnlocalizedName());
    addLiquidMatterValue(config, Block.brick.blockID, getLiquidMatterValue(Item.brick.itemID) * 4, Block.brick.getUnlocalizedName());
    
//    addLiquidMatterValue(config, Block.stairSingle.blockID, 4, getLiquidMatterValue(Block.brick.blockID) / 2);
    addLiquidMatterValue(config, Block.stairsBrick.blockID, getLiquidMatterValue(Block.brick.blockID) * 6 / 4, Block.stairsBrick.getUnlocalizedName());

    addLiquidMatterValue(config, Block.dispenser.blockID, getLiquidMatterValue(Item.bow.itemID) + getLiquidMatterValue(Item.redstone.itemID) + getLiquidMatterValue(Block.cobblestone.blockID) * 7, Block.dispenser.getUnlocalizedName());
    addLiquidMatterValue(config, Block.music.blockID, getLiquidMatterValue(Block.planks.blockID) * 8 + getLiquidMatterValue(Item.redstone.itemID), Block.music.getUnlocalizedName());
    addLiquidMatterValue(config, Block.torchRedstoneActive.blockID, getLiquidMatterValue(Item.stick.itemID) + getLiquidMatterValue(Item.redstone.itemID), Block.torchRedstoneActive.getUnlocalizedName());
    addLiquidMatterValue(config, Item.redstoneRepeater.itemID, getLiquidMatterValue(Block.stone.blockID) * 3 + getLiquidMatterValue(Block.torchRedstoneActive.blockID) * 2 + getLiquidMatterValue(Item.redstone.itemID),
    		Item.redstoneRepeater.getUnlocalizedName());
    addLiquidMatterValue(config, Item.gunpowder.itemID, 192, Item.gunpowder.getUnlocalizedName());
    addLiquidMatterValue(config, Block.torchWood.blockID, (getLiquidMatterValue(Item.coal.itemID, 1) + getLiquidMatterValue(Item.stick.itemID)) / 4, Block.torchWood.getUnlocalizedName());
    
    addLiquidMatterValue(config, Item.glowstone.itemID, getLiquidMatterValue(Item.redstone.itemID) * 6, Item.glowstone.getUnlocalizedName());
    addLiquidMatterValue(config, Block.glowStone.blockID, getLiquidMatterValue(Item.glowstone.itemID) * 4, Block.glowStone.getUnlocalizedName());
    addLiquidMatterValue(config, Block.slowSand.blockID, (getLiquidMatterValue(Item.glowstone.itemID) + getLiquidMatterValue(Block.sand.blockID) * 8) / 8, Block.slowSand.getUnlocalizedName());

    addLiquidMatterValue(config, Block.redstoneLampIdle.blockID, getLiquidMatterValue(Block.glowStone.blockID) + getLiquidMatterValue(Item.redstone.itemID) * 4, Block.redstoneLampIdle.getUnlocalizedName());

    addLiquidMatterValue(config, Block.tnt.blockID, getLiquidMatterValue(Item.gunpowder.itemID) * 5 + getLiquidMatterValue(Block.sand.blockID) * 4, Block.tnt.getUnlocalizedName());

    addLiquidMatterValue(config, Item.ingotIron.itemID, 256, Item.ingotIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.flintAndSteel.itemID, getLiquidMatterValue(Item.flint.itemID) + getLiquidMatterValue(Item.ingotIron.itemID), Item.flintAndSteel.getUnlocalizedName());
    addLiquidMatterValue(config, Item.shovelIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) + getLiquidMatterValue(Item.stick.itemID) * 2, Item.shovelIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.swordIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 2 + getLiquidMatterValue(Item.stick.itemID), Item.swordIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.hoeIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 2 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.hoeIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.pickaxeIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.pickaxeIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.axeIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.axeIron.getUnlocalizedName());
    addLiquidMatterValue(config, Block.pistonBase.blockID, getLiquidMatterValue(Item.redstone.itemID) + getLiquidMatterValue(Item.ingotIron.itemID) + getLiquidMatterValue(Block.planks.blockID) * 3 + getLiquidMatterValue(Block.cobblestone.blockID) * 4, Block.pistonBase.getUnlocalizedName());
    addLiquidMatterValue(config, Block.pistonStickyBase.blockID, getLiquidMatterValue(Block.pistonBase.blockID) + getLiquidMatterValue(Item.slimeBall.itemID), Block.pistonStickyBase.getUnlocalizedName());
    addLiquidMatterValue(config, Block.rail.blockID, getLiquidMatterValue(Item.ingotIron.itemID) * 6 / 16, Block.rail.getUnlocalizedName());
    addLiquidMatterValue(config, Block.railDetector.blockID, getLiquidMatterValue(Item.ingotIron.itemID), Block.railDetector.getUnlocalizedName());
    addLiquidMatterValue(config, Item.compass.itemID, getLiquidMatterValue(Item.redstone.itemID) + getLiquidMatterValue(Item.ingotIron.itemID) * 4, Item.compass.getUnlocalizedName());
    addLiquidMatterValue(config, Item.map.itemID, getLiquidMatterValue(Item.compass.itemID) + getLiquidMatterValue(Item.paper.itemID) * 8, Item.map.getUnlocalizedName());
    addLiquidMatterValue(config, Block.fenceIron.blockID, getLiquidMatterValue(Item.ingotIron.itemID) * 6 / 16, Block.fenceIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bootsIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 4, Item.bootsIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.helmetIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 5, Item.helmetIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.legsIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 7, Item.legsIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.plateIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 8, Item.plateIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.doorIron.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 6, Item.doorIron.getUnlocalizedName());
    addLiquidMatterValue(config, Block.blockIron.blockID, getLiquidMatterValue(Item.ingotIron.itemID) * 9, Block.blockIron.getUnlocalizedName());
    addLiquidMatterValue(config, Item.minecartEmpty.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 5, Item.minecartEmpty.getUnlocalizedName());
    addLiquidMatterValue(config, Item.minecartCrate.itemID, getLiquidMatterValue(Item.minecartEmpty.itemID) + getLiquidMatterValue(Block.chest.blockID), Item.minecartCrate.getUnlocalizedName());
    
    addLiquidMatterValue(config, Item.minecartPowered.itemID, getLiquidMatterValue(Item.minecartEmpty.itemID) + getLiquidMatterValue(Block.furnaceIdle.blockID), Item.minecartPowered.getUnlocalizedName());

    addLiquidMatterValue(config, Item.bucketEmpty.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 3, Item.bucketEmpty.getUnlocalizedName());
    addLiquidMatterValue(config, Block.blockSnow.blockID, 1, Block.blockSnow.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bucketWater.itemID, getLiquidMatterValue(Item.bucketEmpty.itemID) + getLiquidMatterValue(Block.blockSnow.blockID), Item.bucketWater.getUnlocalizedName());
    addLiquidMatterValue(config, Block.ice.blockID, getLiquidMatterValue(Block.blockSnow.blockID), Block.ice.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bucketMilk.itemID, getLiquidMatterValue(Item.bucketWater.itemID) + getLiquidMatterValue(Item.sugar.itemID) + getLiquidMatterValue(Item.dyePowder.itemID, 15), Item.bucketMilk.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bucketLava.itemID, getLiquidMatterValue(Item.bucketEmpty.itemID) + getLiquidMatterValue(Item.redstone.itemID), Item.bucketLava.getUnlocalizedName());

    addLiquidMatterValue(config, Block.obsidian.blockID, getLiquidMatterValue(Item.redstone.itemID), Block.obsidian.getUnlocalizedName());
    addLiquidMatterValue(config, Item.cake.itemID, getLiquidMatterValue(Item.bucketMilk.itemID) * 3 - getLiquidMatterValue(Item.bucketEmpty.itemID) * 3 + getLiquidMatterValue(Item.sugar.itemID) * 2 + getLiquidMatterValue(Item.wheat.itemID) * 3 + getLiquidMatterValue(Item.egg.itemID), Item.cake.getUnlocalizedName());

    addLiquidMatterValue(config, Item.ingotGold.itemID, 2048, Item.ingotGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.goldNugget.itemID, getLiquidMatterValue(Item.ingotGold.itemID) / 9, Item.goldNugget.getUnlocalizedName());
    addLiquidMatterValue(config, Item.speckledMelon.itemID, getLiquidMatterValue(Item.goldNugget.itemID) + getLiquidMatterValue(Item.melon.itemID), Item.speckledMelon.getUnlocalizedName());
    addLiquidMatterValue(config, Item.appleGold.itemID, getLiquidMatterValue(Item.appleRed.itemID) + getLiquidMatterValue(Item.goldNugget.itemID) * 8, Item.appleGold.getUnlocalizedName());
    addLiquidMatterValue(config, Block.railPowered.blockID, getLiquidMatterValue(Item.ingotGold.itemID), Block.railPowered.getUnlocalizedName());
    addLiquidMatterValue(config, Item.shovelGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) + getLiquidMatterValue(Item.stick.itemID) * 2, Item.shovelGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.swordGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 2 + getLiquidMatterValue(Item.stick.itemID), Item.swordGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.hoeGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 2 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.hoeGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.pickaxeGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.pickaxeGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.axeGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.axeGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.pocketSundial.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 4 + getLiquidMatterValue(Item.redstone.itemID), Item.pocketSundial.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bootsGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 4, Item.bootsGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.helmetGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 5, Item.helmetGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.legsGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 7, Item.legsGold.getUnlocalizedName());
    addLiquidMatterValue(config, Item.plateGold.itemID, getLiquidMatterValue(Item.ingotGold.itemID) * 8, Item.plateGold.getUnlocalizedName());
    addLiquidMatterValue(config, Block.blockGold.blockID, getLiquidMatterValue(Item.ingotGold.itemID) * 9, Block.blockGold.getUnlocalizedName());

    addLiquidMatterValue(config, Item.diamond.itemID, 8192, Item.diamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.ghastTear.itemID, getLiquidMatterValue(Item.diamond.itemID) / 2, Item.ghastTear.getUnlocalizedName());
    addLiquidMatterValue(config, Block.jukebox.blockID, getLiquidMatterValue(Item.diamond.itemID) + getLiquidMatterValue(Block.planks.blockID) * 8, Block.jukebox.getUnlocalizedName());
    addLiquidMatterValue(config, Block.enchantmentTable.blockID, getLiquidMatterValue(Item.diamond.itemID) * 2 + getLiquidMatterValue(Block.obsidian.blockID) * 4 + getLiquidMatterValue(Item.book.itemID), Block.enchantmentTable.getUnlocalizedName());
    addLiquidMatterValue(config, Item.shovelDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) + getLiquidMatterValue(Item.stick.itemID) * 2, Item.shovelDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.swordDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) * 2 + getLiquidMatterValue(Item.stick.itemID), Item.swordDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.hoeDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) * 2 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.hoeDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.pickaxeDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.pickaxeDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.axeDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) * 3 + getLiquidMatterValue(Item.stick.itemID) * 2, Item.axeDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.bootsDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) * 4, Item.bootsDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.helmetDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) * 5, Item.helmetDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.legsDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) * 7, Item.legsDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Item.plateDiamond.itemID, getLiquidMatterValue(Item.diamond.itemID) * 8, Item.plateDiamond.getUnlocalizedName());
    addLiquidMatterValue(config, Block.blockDiamond.blockID, getLiquidMatterValue(Item.diamond.itemID) * 9, Block.blockDiamond.getUnlocalizedName());

    addLiquidMatterValue(config, Item.shears.itemID, getLiquidMatterValue(Item.ingotIron.itemID) * 2, Item.shears.getUnlocalizedName());
    addLiquidMatterValue(config, Item.saddle.itemID, getLiquidMatterValue(Item.leather.itemID) * 3, Item.saddle.getUnlocalizedName());
    
    addLiquidMatterValue(config, LiquidMatterItems.itemCarbon.itemID, getLiquidMatterValue(Block.blockDiamond.blockID) + (getLiquidMatterValue(Item.diamond.itemID) * 8), LiquidMatterItems.itemCarbon.getUnlocalizedName());
    
    addLiquidMatterValue(config, Block.dragonEgg.blockID, getLiquidMatterValue(LiquidMatterItems.itemCarbon.itemID), Block.dragonEgg.getUnlocalizedName());

    addLiquidMatterValue(config, Item.record11.itemID, getLiquidMatterValue(Item.ingotGold.itemID), Item.record11.getUnlocalizedName());
    addLiquidMatterValue(config, Item.record13.itemID, getLiquidMatterValue(Item.record11.itemID), Item.record13.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordBlocks.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordBlocks.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordCat.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordCat.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordChirp.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordChirp.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordFar.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordFar.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordMall.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordMall.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordMellohi.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordMellohi.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordStal.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordStal.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordStrad.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordStrad.getUnlocalizedName());
    addLiquidMatterValue(config, Item.recordWard.itemID, getLiquidMatterValue(Item.record11.itemID), Item.recordWard.getUnlocalizedName());

		config.save();
	}
	
	public static void addLiquidMatterValue(Configuration config, int id, int liquidMatterValue, String nameInConfig) {
		int actualValue = config.get("Conversions", "" + id, liquidMatterValue, nameInConfig).getInt();
		table.put(new LMVTableEntry(id, -1), actualValue);
	}
	
	public static void addLiquidMatterValue(Configuration config, int id, int meta, int liquidMatterValue, String nameInConfig) {
		int actualValue = config.get("Conversions", "" + id + "-" + meta, liquidMatterValue, nameInConfig).getInt();
		table.put(new LMVTableEntry(id, meta), actualValue);
	}
	
	/**
	 * Returns the liquid matter value of the item (does not multiply by quantity) in millibuckets
	 * @param itemStack the ItemStack representing the item
	 * @return the liquid matter value of the item (does not multiply by quantity) in millibuckets
	 */
	public static int getLiquidMatterValue(ItemStack itemStack) {
		return getLiquidMatterValue(itemStack.itemID, itemStack.getItemDamage());
	}
	
	/**
	 * Returns the liquid matter value of the item in millibuckets
	 * @param itemID the item/block ID to be looked up
	 * @return the liquid matter value of the item in millibuckets
	 */
	public static int getLiquidMatterValue(int itemID) {
		Integer value = table.get(new LMVTableEntry(itemID, -1));
		if (value != null) return value.intValue();
		return 0;
	}
	
	/**
	 * Returns the liquid matter value of the item in millibuckets
	 * @param itemID the item/block ID to be looked up
	 * @param meta the damage/meta value of the item to be looked up
	 * @return the liquid matter value of the item in millibuckets
	 */
	public static int getLiquidMatterValue(int itemID, int meta) {
		Integer value = table.get(new LMVTableEntry(itemID, meta));
		if (value != null) return value.intValue();
		return 0;
	}
	
	public static boolean isBurnable(ItemStack itemStack) {
		return getLiquidMatterValue(itemStack) > 0;
	}
	
}
