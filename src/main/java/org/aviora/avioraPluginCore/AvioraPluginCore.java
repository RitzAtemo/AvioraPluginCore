package org.aviora.avioraPluginCore;

import org.aviora.avioraPluginCore.utils.AvioraUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class AvioraPluginCore extends JavaPlugin {

	@Override
	public void onEnable() {
		// Plugin startup logic

		AvioraUtils.log(AvioraUtils.getBanner(this.getName()));
		AvioraUtils.log(AvioraUtils.getEnabled(this.getName()));
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic

		AvioraUtils.log(AvioraUtils.getDisabled(this.getName()));
	}
}
