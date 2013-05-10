package de.klingbeil.swag.core.view;

public interface MainLayout extends View {
	public static final String STYLE_ROOT = "root";
	public static final String STYLE_MAIN = "main";
	public static final String STYLE_HEADER = "header";
	public static final String STYLE_SIDEBAR = "sidebar";
	public static final String STYLE_CONTENT = "content";
	public static final String STYLE_LOGO = "logo";

	void setContent(View contentView);
}
