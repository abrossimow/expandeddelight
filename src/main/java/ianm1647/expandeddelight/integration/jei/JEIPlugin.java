package ianm1647.expandeddelight.integration.jei;

import ianm1647.expandeddelight.ExpandedDelight;
import ianm1647.expandeddelight.client.gui.JuicerScreen;
import ianm1647.expandeddelight.common.block.entity.container.JuicerMenu;
import ianm1647.expandeddelight.common.registry.EDItems;
import ianm1647.expandeddelight.common.registry.EDMenuTypes;
import ianm1647.expandeddelight.integration.jei.category.JuicingRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@JeiPlugin
public class JEIPlugin implements IModPlugin {
   private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(ExpandedDelight.MODID, "jei_plugin");

   public void registerCategories(IRecipeCategoryRegistration registry) {
      registry.addRecipeCategories(new JuicingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
   }

   public void registerRecipes(IRecipeRegistration registration) {
      JEIRecipes modRecipes = new JEIRecipes();
      registration.addRecipes(JEIRecipeTypes.JUICING, modRecipes.getJuicerRecipes());
      registration.addIngredientInfo(List.of(new ItemStack(EDItems.WILD_ASPARAGUS.get()), new ItemStack(EDItems.ASPARAGUS.get())), VanillaTypes.ITEM_STACK, Component.translatable("expandeddelight.jei.info.wild_asparagus"));
      registration.addIngredientInfo(List.of(new ItemStack(EDItems.WILD_SWEET_POTATO.get()), new ItemStack(EDItems.SWEET_POTATO.get())), VanillaTypes.ITEM_STACK, Component.translatable("expandeddelight.jei.info.wild_sweet_potato"));
      registration.addIngredientInfo(List.of(new ItemStack(EDItems.WILD_CHILI_PEPPER.get()), new ItemStack(EDItems.CHILI_PEPPER.get())), VanillaTypes.ITEM_STACK, Component.translatable("expandeddelight.jei.info.wild_chili_pepper"));
      registration.addIngredientInfo(List.of(new ItemStack(EDItems.WILD_PEANUTS.get()), new ItemStack(EDItems.PEANUT.get())), VanillaTypes.ITEM_STACK, Component.translatable("expandeddelight.jei.info.wild_peanuts"));
   }

   public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
      registration.addRecipeCatalyst(new ItemStack(EDItems.JUICER.get()), JEIRecipeTypes.JUICING);
   }

   public void registerGuiHandlers(IGuiHandlerRegistration registration) {
      registration.addRecipeClickArea(JuicerScreen.class, 71, 25, 30, 17, JEIRecipeTypes.JUICING);
   }

   public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
      registration.addRecipeTransferHandler(JuicerMenu.class, EDMenuTypes.JUICER.get(), JEIRecipeTypes.JUICING, 0, 2, 5, 36);
   }

   public ResourceLocation getPluginUid() {
      return ID;
   }
}
