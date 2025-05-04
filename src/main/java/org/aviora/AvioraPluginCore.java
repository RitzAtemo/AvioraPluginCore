package org.aviora;

import org.aviora.utils.AvioraUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class AvioraPluginCore extends JavaPlugin {
	@Override
	public void onEnable() {
		AvioraUtils.log(AvioraUtils.getBanner(this.getName()));
		AvioraUtils.log(AvioraUtils.getEnabled(this.getName()));
	}

	@Override
	public void onDisable() {
		AvioraUtils.log(AvioraUtils.getDisabled(this.getName()));
	}
}
