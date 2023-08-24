package io.github.acdeasdff.infinityCompress.items.blocks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.mooy1.infinitylib.machines.MachineLore;
import io.github.mooy1.infinitylib.machines.MenuBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;

/**
 * Solar panels and some other basic generators
 *
 * @author Mooy1
 *
 * Thanks to panda for some stuff to work off of
 */
@ParametersAreNonnullByDefault
public class TweakedGenerator extends MenuBlock implements EnergyNetProvider {

    private final GenerationType type;
    private final int generation;

    public TweakedGenerator(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe,
                           int generation, GenerationType type) {
        super(category, item, recipeType, recipe);
        this.type = type;
        this.generation = generation;
    }

    @Override
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(new int[] {
                0, 1, 2, 3, 4, 5, 6, 7, 8
        });
    }

    @Nonnull
    @Override
    protected int[] getInputSlots(DirtyChestMenu dirtyChestMenu, ItemStack itemStack) {
        return new int[0];
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    public int getGeneratedOutput(Location l, Config data) {
        int gen = this.type.generate(l.getWorld(), l.getBlock(), this.generation);

        BlockMenu inv = BlockStorage.getInventory(l);
        if (inv != null && inv.hasViewer()) {
            if (gen == 0) {
                inv.replaceExistingItem(4, new CustomItemStack(
                        Material.GREEN_STAINED_GLASS_PANE,
                        "&c未发电",
                        "&7已存储: &6" + MachineLore.format(getCharge(l)) + " J"
                ));
            }
            else {
                inv.replaceExistingItem(4, new CustomItemStack(
                        Material.GREEN_STAINED_GLASS_PANE,
                        "&a发电",
                        "&7类型: &6" + this.type,
                        "&7发电中: &6" + MachineLore.formatEnergy(gen) + " J/s ",
                        "&7已存储: &6" + MachineLore.format(getCharge(l)) + " J"
                ));
            }
        }

        return gen;
    }

    @Override
    public int getCapacity() {
//        * 100 -> * 10
//        the only change i did
        return this.generation * 10;
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

}
