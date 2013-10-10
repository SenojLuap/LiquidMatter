package paujo.liquidMatter.mod;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class LiquidMatterConversionTable {

	public static Hashtable<String, Integer> table;
	
	
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
		table = new Hashtable<String, Integer>(256);
		
		Configuration config = new Configuration(new File("config/" + LiquidMatter.modid + "_ConversionValues.cfg"));
		config.load();
		
    System.out.println("Initializing alchemy values for Equivalent Exchange..");

    addLiquidMatterValue(config, Block.leaves.getUnlocalizedName(), 1);
    addLiquidMatterValue(config, Block.wood.getUnlocalizedName(), 32);
    addLiquidMatterValue(config, Block.sapling.getUnlocalizedName(), getLiquidMatterValue(Block.wood.getUnlocalizedName()));
    addLiquidMatterValue(config, getUnlocalizedName(Item.coal, 1), getLiquidMatterValue(Block.wood.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.redstone.getUnlocalizedName(), getLiquidMatterValue(getUnlocalizedName(Item.coal, 1)) * 2);
    addLiquidMatterValue(config, getUnlocalizedName(Item.coal, 0), getLiquidMatterValue(getUnlocalizedName(Item.coal, 1)) * 4);
    addLiquidMatterValue(config, Block.planks.getUnlocalizedName(), getLiquidMatterValue(Block.wood.getUnlocalizedName()) / 4);
    addLiquidMatterValue(config, Block.workbench.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Item.doorWood.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 6);
    addLiquidMatterValue(config, Block.chest.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 8);
    
//    addLiquidMatterValue(config, Block.stairCompactPlanks.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 6 / 4);
//    addLiquidMatterValue(config, Block.stairSingle.getUnlocalizedName(), 2, getLiquidMatterValue(Block.planks.getUnlocalizedName()) / 2);
    
    addLiquidMatterValue(config, Item.boat.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 5);
    addLiquidMatterValue(config, Block.pressurePlatePlanks.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Block.trapdoor.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 6 / 2);
    addLiquidMatterValue(config, Item.bowlEmpty.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 3 / 4);
    addLiquidMatterValue(config, Item.stick.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) / 2);
    addLiquidMatterValue(config, Block.fence.getUnlocalizedName(), getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 6 / 2);
    addLiquidMatterValue(config, Block.fenceGate.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Item.sign.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 6 + getLiquidMatterValue(Item.stick.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.ladder.getUnlocalizedName(), getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 7 / 2);
    addLiquidMatterValue(config, Item.fishingRod.getUnlocalizedName(), getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.silk.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.shovelWood.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.swordWood.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.hoeWood.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.pickaxeWood.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.axeWood.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);

    addLiquidMatterValue(config, Item.bone.getUnlocalizedName(), 144);

    for (int dmg = 0; dmg < 16; dmg++) {
      if (dmg == 15)
        addLiquidMatterValue(config, getUnlocalizedName(Item.dyePowder, dmg), getLiquidMatterValue(Item.bone.getUnlocalizedName()) / 3);
      else if (dmg == 4)
        addLiquidMatterValue(config, getUnlocalizedName(Item.dyePowder, dmg), 864);
      else if (dmg == 3)
        addLiquidMatterValue(config, getUnlocalizedName(Item.dyePowder, dmg), 128);
      else
        addLiquidMatterValue(config, getUnlocalizedName(Item.dyePowder, dmg), 8);
    }
    addLiquidMatterValue(config, Block.blockLapis.getUnlocalizedName(), getLiquidMatterValue(getUnlocalizedName(Item.dyePowder, 4)) * 9);
    addLiquidMatterValue(config, Block.plantYellow.getUnlocalizedName(), getLiquidMatterValue(getUnlocalizedName(Item.dyePowder, 11)) * 2);
    addLiquidMatterValue(config, Block.plantRed.getUnlocalizedName(), getLiquidMatterValue(getUnlocalizedName(Item.dyePowder, 1)) * 2);

    addLiquidMatterValue(config, Block.dirt.getUnlocalizedName(), 1);
    addLiquidMatterValue(config, Block.sand.getUnlocalizedName(), getLiquidMatterValue(Block.dirt.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.grass.getUnlocalizedName(), getLiquidMatterValue(Block.dirt.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.mycelium.getUnlocalizedName(), getLiquidMatterValue(Block.grass.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.tallGrass.getUnlocalizedName(), getLiquidMatterValue(Block.grass.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.deadBush.getUnlocalizedName(), getLiquidMatterValue(Block.tallGrass.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.waterlily.getUnlocalizedName(), 16);
    addLiquidMatterValue(config, Block.vine.getUnlocalizedName(), 8);
    addLiquidMatterValue(config, Block.sandStone.getUnlocalizedName(), getLiquidMatterValue(Block.sand.getUnlocalizedName()) * 4);
    
//    addLiquidMatterValue(config, Block.stairSingle.getUnlocalizedName(), 1, getLiquidMatterValue(Block.sandStone.getUnlocalizedName()) / 2);
    
    addLiquidMatterValue(config, Block.glass.getUnlocalizedName(), getLiquidMatterValue(Block.sand.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.glassBottle.getUnlocalizedName(), getLiquidMatterValue(Block.glass.getUnlocalizedName()));

    addLiquidMatterValue(config, Block.gravel.getUnlocalizedName(), getLiquidMatterValue(Block.sandStone.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.flint.getUnlocalizedName(), getLiquidMatterValue(Block.gravel.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.netherBrick.getUnlocalizedName(), getLiquidMatterValue(Block.gravel.getUnlocalizedName()));

    addLiquidMatterValue(config, Block.netherFence.getUnlocalizedName(), getLiquidMatterValue(Block.netherBrick.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.stairsNetherBrick.getUnlocalizedName(), getLiquidMatterValue(Block.netherBrick.getUnlocalizedName()) * 6 / 4);

    addLiquidMatterValue(config, Item.feather.getUnlocalizedName(), 48);
    addLiquidMatterValue(config, Item.arrow.getUnlocalizedName(), (getLiquidMatterValue(Item.stick.getUnlocalizedName()) + getLiquidMatterValue(Item.flint.getUnlocalizedName()) + getLiquidMatterValue(Item.feather.getUnlocalizedName())) / 4);

    addLiquidMatterValue(config, Block.cobblestone.getUnlocalizedName(), 1);
    
    addLiquidMatterValue(config, Block.furnaceIdle.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 8);
//    addLiquidMatterValue(config, Block.stairSingle.getUnlocalizedName(), 3, getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()));
//    addLiquidMatterValue(config, Block.stairCompactCobblestone.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 6 / 4);
    
    addLiquidMatterValue(config, Block.lever.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) + getLiquidMatterValue(Item.stick.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.netherrack.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.stone.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.whiteStone.getUnlocalizedName(), getLiquidMatterValue(Block.netherrack.getUnlocalizedName()));
    
//    addLiquidMatterValue(config, Block.button.getUnlocalizedName(), getLiquidMatterValue(Block.stone.getUnlocalizedName()) * 2);
    
    addLiquidMatterValue(config, Block.pressurePlateStone.getUnlocalizedName(), getLiquidMatterValue(Block.stone.getUnlocalizedName()) * 2);
    
//    addLiquidMatterValue(config, Block.stairSingle.getUnlocalizedName(), 0, getLiquidMatterValue(Block.stone.getUnlocalizedName()));

    addLiquidMatterValue(config, Block.stoneBrick.getUnlocalizedName(), getLiquidMatterValue(Block.stone.getUnlocalizedName()));
    
//    addLiquidMatterValue(config, Block.stairSingle.getUnlocalizedName(), 5, getLiquidMatterValue(Block.stoneBrick.getUnlocalizedName()));
//    addLiquidMatterValue(config, Block.stairsStoneBrickSmooth.getUnlocalizedName(), getLiquidMatterValue(Block.stoneBrick.getUnlocalizedName()) * 6 / 4);
    
    addLiquidMatterValue(config, Item.shovelStone.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.swordStone.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.hoeStone.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.pickaxeStone.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.axeStone.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);

    addLiquidMatterValue(config, Item.silk.getUnlocalizedName(), 12);
    addLiquidMatterValue(config, Item.bow.getUnlocalizedName(), getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.silk.getUnlocalizedName()) * 3);
    addLiquidMatterValue(config, Item.slimeBall.getUnlocalizedName(), 24);
    addLiquidMatterValue(config, Block.web.getUnlocalizedName(), (getLiquidMatterValue(Item.silk.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.slimeBall.getUnlocalizedName())) / 4);
    addLiquidMatterValue(config, Block.cobblestoneMossy.getUnlocalizedName(), getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) + getLiquidMatterValue(Item.seeds.getUnlocalizedName()) + getLiquidMatterValue(Item.slimeBall.getUnlocalizedName()) * 6);
    addLiquidMatterValue(config, Block.cloth.getUnlocalizedName(), getLiquidMatterValue(Item.silk.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Item.bed.getUnlocalizedName(), getLiquidMatterValue(Block.cloth.getUnlocalizedName()) * 3 + getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 3);
    addLiquidMatterValue(config, Item.painting.getUnlocalizedName(), getLiquidMatterValue(Block.cloth.getUnlocalizedName()) + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 8);

    addLiquidMatterValue(config, Item.seeds.getUnlocalizedName(), 16);
    addLiquidMatterValue(config, Item.reed.getUnlocalizedName(), 32);
    addLiquidMatterValue(config, Item.sugar.getUnlocalizedName(), getLiquidMatterValue(Item.reed.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.paper.getUnlocalizedName(), getLiquidMatterValue(Item.reed.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.book.getUnlocalizedName(), getLiquidMatterValue(Item.paper.getUnlocalizedName()) * 3);
    addLiquidMatterValue(config, Block.bookShelf.getUnlocalizedName(), getLiquidMatterValue(Item.book.getUnlocalizedName()) * 3 + getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 6);
    addLiquidMatterValue(config, Item.wheat.getUnlocalizedName(), 24);
    addLiquidMatterValue(config, Item.bread.getUnlocalizedName(), getLiquidMatterValue(Item.wheat.getUnlocalizedName()) * 3);
    addLiquidMatterValue(config, Item.cookie.getUnlocalizedName(), (getLiquidMatterValue(Item.wheat.getUnlocalizedName()) * 2 + getLiquidMatterValue(getUnlocalizedName(Item.dyePowder, 3))) / 8);
    addLiquidMatterValue(config, Block.cactus.getUnlocalizedName(), 8);
    addLiquidMatterValue(config, Item.melon.getUnlocalizedName(), 16);
    addLiquidMatterValue(config, Item.melonSeeds.getUnlocalizedName(), getLiquidMatterValue(Item.melon.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.melon.getUnlocalizedName(), getLiquidMatterValue(Item.melon.getUnlocalizedName()) * 9);
    addLiquidMatterValue(config, Block.pumpkin.getUnlocalizedName(), 144);
    addLiquidMatterValue(config, Block.pumpkinLantern.getUnlocalizedName(), getLiquidMatterValue(Block.pumpkin.getUnlocalizedName()) + getLiquidMatterValue(Block.torchWood.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.pumpkinSeeds.getUnlocalizedName(), getLiquidMatterValue(Block.pumpkin.getUnlocalizedName()) / 4);
    addLiquidMatterValue(config, Block.mushroomBrown.getUnlocalizedName(), 32);
    addLiquidMatterValue(config, Block.mushroomRed.getUnlocalizedName(), getLiquidMatterValue(Block.mushroomBrown.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.bowlSoup.getUnlocalizedName(), getLiquidMatterValue(Item.bowlEmpty.getUnlocalizedName()) + getLiquidMatterValue(Block.mushroomBrown.getUnlocalizedName()) * 2);

    addLiquidMatterValue(config, Item.leather.getUnlocalizedName(), 64);
    addLiquidMatterValue(config, Item.bootsLeather.getUnlocalizedName(), getLiquidMatterValue(Item.leather.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Item.helmetLeather.getUnlocalizedName(), getLiquidMatterValue(Item.leather.getUnlocalizedName()) * 5);
    addLiquidMatterValue(config, Item.legsLeather.getUnlocalizedName(), getLiquidMatterValue(Item.leather.getUnlocalizedName()) * 7);
    addLiquidMatterValue(config, Item.plateLeather.getUnlocalizedName(), getLiquidMatterValue(Item.leather.getUnlocalizedName()) * 8);
    addLiquidMatterValue(config, Item.rottenFlesh.getUnlocalizedName(), 24);
    addLiquidMatterValue(config, Item.appleRed.getUnlocalizedName(), 128);
    addLiquidMatterValue(config, Item.egg.getUnlocalizedName(), 32);
    addLiquidMatterValue(config, Item.blazeRod.getUnlocalizedName(), 1536);
    addLiquidMatterValue(config, Item.blazePowder.getUnlocalizedName(), getLiquidMatterValue(Item.blazeRod.getUnlocalizedName()) / 2);

    addLiquidMatterValue(config, Item.fireballCharge.getUnlocalizedName(), (getLiquidMatterValue(Item.blazePowder.getUnlocalizedName()) + getLiquidMatterValue(Item.coal.getUnlocalizedName()) + getLiquidMatterValue(Item.gunpowder.getUnlocalizedName())) / 3);

    addLiquidMatterValue(config, Item.magmaCream.getUnlocalizedName(), getLiquidMatterValue(Item.blazePowder.getUnlocalizedName()) + getLiquidMatterValue(Item.slimeBall.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.brewingStand.getUnlocalizedName(), getLiquidMatterValue(Item.blazeRod.getUnlocalizedName()) + getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 3);
    addLiquidMatterValue(config, Item.enderPearl.getUnlocalizedName(), 1024);
    addLiquidMatterValue(config, Item.eyeOfEnder.getUnlocalizedName(), getLiquidMatterValue(Item.enderPearl.getUnlocalizedName()) + getLiquidMatterValue(Item.blazePowder.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.spiderEye.getUnlocalizedName(), 128);
    addLiquidMatterValue(config, Item.fermentedSpiderEye.getUnlocalizedName(), getLiquidMatterValue(Item.spiderEye.getUnlocalizedName()) + getLiquidMatterValue(Block.mushroomBrown.getUnlocalizedName()) + getLiquidMatterValue(Item.sugar.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.netherStalkSeeds.getUnlocalizedName(), 24);

    addLiquidMatterValue(config, Item.porkRaw.getUnlocalizedName(), 64);
    addLiquidMatterValue(config, Item.beefRaw.getUnlocalizedName(), getLiquidMatterValue(Item.porkRaw.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.chickenRaw.getUnlocalizedName(), getLiquidMatterValue(Item.porkRaw.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.fishRaw.getUnlocalizedName(), getLiquidMatterValue(Item.porkRaw.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.porkCooked.getUnlocalizedName(), getLiquidMatterValue(Item.porkRaw.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.beefCooked.getUnlocalizedName(), getLiquidMatterValue(Item.beefRaw.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.chickenCooked.getUnlocalizedName(), getLiquidMatterValue(Item.chickenRaw.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.fishCooked.getUnlocalizedName(), getLiquidMatterValue(Item.fishRaw.getUnlocalizedName()));

    addLiquidMatterValue(config, Item.clay.getUnlocalizedName(), 16);
    addLiquidMatterValue(config, Block.blockClay.getUnlocalizedName(), getLiquidMatterValue(Item.clay.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Item.brick.getUnlocalizedName(), getLiquidMatterValue(Item.clay.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.brick.getUnlocalizedName(), getLiquidMatterValue(Item.brick.getUnlocalizedName()) * 4);
    
//    addLiquidMatterValue(config, Block.stairSingle.getUnlocalizedName(), 4, getLiquidMatterValue(Block.brick.getUnlocalizedName()) / 2);
    
    addLiquidMatterValue(config, Block.stairsBrick.getUnlocalizedName(), getLiquidMatterValue(Block.brick.getUnlocalizedName()) * 6 / 4);

    addLiquidMatterValue(config, Block.dispenser.getUnlocalizedName(), getLiquidMatterValue(Item.bow.getUnlocalizedName()) + getLiquidMatterValue(Item.redstone.getUnlocalizedName()) + getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 7);
    addLiquidMatterValue(config, Block.music.getUnlocalizedName(), getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 8 + getLiquidMatterValue(Item.redstone.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.torchRedstoneActive.getUnlocalizedName(), getLiquidMatterValue(Item.stick.getUnlocalizedName()) + getLiquidMatterValue(Item.redstone.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.redstoneRepeater.getUnlocalizedName(), getLiquidMatterValue(Block.stone.getUnlocalizedName()) * 3 + getLiquidMatterValue(Block.torchRedstoneActive.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.redstone.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.gunpowder.getUnlocalizedName(), 192);
    
    addLiquidMatterValue(config, Block.torchWood.getUnlocalizedName(), (getLiquidMatterValue(getUnlocalizedName(Item.coal, 1)) + getLiquidMatterValue(Item.stick.getUnlocalizedName())) / 4);
    
//    addLiquidMatterValue(config, Item.lightStoneDust.getUnlocalizedName(), getLiquidMatterValue(Item.redstone.getUnlocalizedName()) * 6);
    
//    addLiquidMatterValue(config, Block.glowStone.getUnlocalizedName(), getLiquidMatterValue(Item.lightStoneDust.getUnlocalizedName()) * 4);
//    addLiquidMatterValue(config, Block.slowSand.getUnlocalizedName(), (getLiquidMatterValue(Item.lightStoneDust.getUnlocalizedName()) + getLiquidMatterValue(Block.sand.getUnlocalizedName()) * 8) / 8);

    addLiquidMatterValue(config, Block.redstoneLampIdle.getUnlocalizedName(), getLiquidMatterValue(Block.glowStone.getUnlocalizedName()) + getLiquidMatterValue(Item.redstone.getUnlocalizedName()) * 4);

    addLiquidMatterValue(config, Block.tnt.getUnlocalizedName(), getLiquidMatterValue(Item.gunpowder.getUnlocalizedName()) * 5 + getLiquidMatterValue(Block.sand.getUnlocalizedName()) * 4);

    addLiquidMatterValue(config, Item.ingotIron.getUnlocalizedName(), 256);
    addLiquidMatterValue(config, Item.flintAndSteel.getUnlocalizedName(), getLiquidMatterValue(Item.flint.getUnlocalizedName()) + getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()));
    
//    addLiquidMatterValue(config, Item.shovelSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
//    addLiquidMatterValue(config, Item.swordSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()));
//    addLiquidMatterValue(config, Item.hoeSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
//    addLiquidMatterValue(config, Item.pickaxeSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
//    addLiquidMatterValue(config, Item.axeSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    
    addLiquidMatterValue(config, Block.pistonBase.getUnlocalizedName(), getLiquidMatterValue(Item.redstone.getUnlocalizedName()) + getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) + getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 3 + getLiquidMatterValue(Block.cobblestone.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Block.pistonStickyBase.getUnlocalizedName(), getLiquidMatterValue(Block.pistonBase.getUnlocalizedName()) + getLiquidMatterValue(Item.slimeBall.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.rail.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 6 / 16);
    addLiquidMatterValue(config, Block.railDetector.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.compass.getUnlocalizedName(), getLiquidMatterValue(Item.redstone.getUnlocalizedName()) + getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Item.map.getUnlocalizedName(), getLiquidMatterValue(Item.compass.getUnlocalizedName()) + getLiquidMatterValue(Item.paper.getUnlocalizedName()) * 8);
    addLiquidMatterValue(config, Block.fenceIron.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 6 / 16);
    
//    addLiquidMatterValue(config, Item.bootsSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 4);
//    addLiquidMatterValue(config, Item.helmetSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 5);
//    addLiquidMatterValue(config, Item.legsSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 7);
//    addLiquidMatterValue(config, Item.plateSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 8);
//    addLiquidMatterValue(config, Item.doorSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 6);
//    addLiquidMatterValue(config, Block.blockSteel.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 9);
    
    addLiquidMatterValue(config, Item.minecartEmpty.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 5);
    addLiquidMatterValue(config, Item.minecartCrate.getUnlocalizedName(), getLiquidMatterValue(Item.minecartEmpty.getUnlocalizedName()) + getLiquidMatterValue(Block.chest.getUnlocalizedName()));
    
//    addLiquidMatterValue(config, Item.minecartPowered.getUnlocalizedName(), getLiquidMatterValue(Item.minecartEmpty.getUnlocalizedName()) + getLiquidMatterValue(Block.stoneOvenIdle.getUnlocalizedName()));

    addLiquidMatterValue(config, Item.bucketEmpty.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 3);
    addLiquidMatterValue(config, Block.blockSnow.getUnlocalizedName(), 1);
    addLiquidMatterValue(config, Item.bucketWater.getUnlocalizedName(), getLiquidMatterValue(Item.bucketEmpty.getUnlocalizedName()) + getLiquidMatterValue(Block.blockSnow.getUnlocalizedName()));
    addLiquidMatterValue(config, Block.ice.getUnlocalizedName(), getLiquidMatterValue(Block.blockSnow.getUnlocalizedName()));
    
    addLiquidMatterValue(config, Item.bucketMilk.getUnlocalizedName(), getLiquidMatterValue(Item.bucketWater.getUnlocalizedName()) + getLiquidMatterValue(Item.sugar.getUnlocalizedName()) + getLiquidMatterValue(getUnlocalizedName(Item.dyePowder, 15)));
    
    addLiquidMatterValue(config, Item.bucketLava.getUnlocalizedName(), getLiquidMatterValue(Item.bucketEmpty.getUnlocalizedName()) + getLiquidMatterValue(Item.redstone.getUnlocalizedName()));

    addLiquidMatterValue(config, Block.obsidian.getUnlocalizedName(), getLiquidMatterValue(Item.redstone.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.cake.getUnlocalizedName(), getLiquidMatterValue(Item.bucketMilk.getUnlocalizedName()) * 3 - getLiquidMatterValue(Item.bucketEmpty.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.sugar.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.wheat.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.egg.getUnlocalizedName()));

    addLiquidMatterValue(config, Item.ingotGold.getUnlocalizedName(), 2048);
    addLiquidMatterValue(config, Item.goldNugget.getUnlocalizedName(), 227);
    addLiquidMatterValue(config, Item.speckledMelon.getUnlocalizedName(), getLiquidMatterValue(Item.goldNugget.getUnlocalizedName()) + getLiquidMatterValue(Item.melon.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.appleGold.getUnlocalizedName(), getLiquidMatterValue(Item.appleRed.getUnlocalizedName()) + getLiquidMatterValue(Item.goldNugget.getUnlocalizedName()) * 8);
    addLiquidMatterValue(config, Block.railPowered.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.shovelGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.swordGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.hoeGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.pickaxeGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.axeGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.pocketSundial.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 4 + getLiquidMatterValue(Item.redstone.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.bootsGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Item.helmetGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 5);
    addLiquidMatterValue(config, Item.legsGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 7);
    addLiquidMatterValue(config, Item.plateGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 8);
    addLiquidMatterValue(config, Block.blockGold.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()) * 9);

    addLiquidMatterValue(config, Item.diamond.getUnlocalizedName(), 8192);
    addLiquidMatterValue(config, Item.ghastTear.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) / 2);
    addLiquidMatterValue(config, Block.jukebox.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) + getLiquidMatterValue(Block.planks.getUnlocalizedName()) * 8);
    addLiquidMatterValue(config, Block.enchantmentTable.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 2 + getLiquidMatterValue(Block.obsidian.getUnlocalizedName()) * 4 + getLiquidMatterValue(Item.book.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.shovelDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.swordDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.hoeDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 2 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.pickaxeDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.axeDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 3 + getLiquidMatterValue(Item.stick.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.bootsDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 4);
    addLiquidMatterValue(config, Item.helmetDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 5);
    addLiquidMatterValue(config, Item.legsDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 7);
    addLiquidMatterValue(config, Item.plateDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 8);
    addLiquidMatterValue(config, Block.blockDiamond.getUnlocalizedName(), getLiquidMatterValue(Item.diamond.getUnlocalizedName()) * 9);

    addLiquidMatterValue(config, Item.shears.getUnlocalizedName(), getLiquidMatterValue(Item.ingotIron.getUnlocalizedName()) * 2);
    addLiquidMatterValue(config, Item.saddle.getUnlocalizedName(), getLiquidMatterValue(Item.leather.getUnlocalizedName()) * 3);
    
//    addLiquidMatterValue(config, Block.dragonEgg.getUnlocalizedName(), getLiquidMatterValue(EEItem.darkMatter.getUnlocalizedName()));

    addLiquidMatterValue(config, Item.record11.getUnlocalizedName(), getLiquidMatterValue(Item.ingotGold.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.record13.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordBlocks.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordCat.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordChirp.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordFar.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordMall.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordMellohi.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordStal.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordStrad.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
    addLiquidMatterValue(config, Item.recordWard.getUnlocalizedName(), getLiquidMatterValue(Item.record11.getUnlocalizedName()));
		
		config.save();
	}
	
	public static void addLiquidMatterValue(Configuration config, String unlocalizedName, int liquidMatterValue) {
		int actualValue = config.get("Conversions", unlocalizedName, liquidMatterValue).getInt();
		System.out.println("Added " + unlocalizedName + " with value " + liquidMatterValue);
		table.put(unlocalizedName, actualValue);
	}
	
	
	/**
	 * Looks up the appropriate amount of LiquidMatter for the given item
	 * @param getUnlocalizedName() The unlocalized name of the item/block to be converted
	 * @return The value of the item/block, measured in millibuckets. A return of 0 indicates no converstion
	 * is specified
	 */
	public static int getLiquidMatterValue(String unlocalizedName) {
		Integer value = (Integer)table.get(unlocalizedName);
		if (value == null)
			return 0;
		return value.intValue();
	}
	
	/**
	 * Returns true if the item can be reduced
	 * @param getUnlocalizedName() The item to be reduced
	 * @return true if the item can be reduced, false otherwise
	 */
	public static boolean isBurnable(String unlocalizedName) {
		return getLiquidMatterValue(unlocalizedName) != 0;
	}
	
}
