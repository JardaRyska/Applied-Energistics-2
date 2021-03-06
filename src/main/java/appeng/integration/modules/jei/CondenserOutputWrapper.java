package appeng.integration.modules.jei;


import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

import com.google.common.base.Splitter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.HoverChecker;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;

import appeng.api.config.CondenserOutput;


class CondenserOutputWrapper extends BlankRecipeWrapper
{
	private final ItemStack outputItem;

	private final CondenserOutput condenserOutput;

	private final HoverChecker buttonHoverChecker;

	private final IDrawable buttonIcon;

	CondenserOutputWrapper( CondenserOutput condenserOutput, ItemStack outputItem, IDrawable buttonIcon )
	{
		this.condenserOutput = condenserOutput;
		this.outputItem = outputItem;
		this.buttonIcon = buttonIcon;
		this.buttonHoverChecker = new HoverChecker( 28, 28 + 16, 78, 78 + 16, 0 );
	}

	@Override
	public void getIngredients( IIngredients ingredients )
	{
		ingredients.setOutput( ItemStack.class, outputItem );
	}

	public CondenserOutput getCondenserOutput()
	{
		return condenserOutput;
	}

	@Nullable
	@Override
	public List<String> getTooltipStrings( int mouseX, int mouseY )
	{
		if( buttonHoverChecker.checkHover( mouseX, mouseY ) )
		{
			String key;

			switch( condenserOutput )
			{
				case MATTER_BALLS:
					key = "gui.tooltips.appliedenergistics2.MatterBalls";
					break;
				case SINGULARITY:
					key = "gui.tooltips.appliedenergistics2.Singularity";
					break;
				default:
					return Collections.emptyList();
			}

			return Splitter.on( "\\n" ).splitToList( I18n.format( key, condenserOutput.requiredPower ) );
		}
		return Collections.emptyList();
	}

	@Override
	public void drawInfo( Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY )
	{
		buttonIcon.draw( minecraft );
	}
}
