package fr.sghosting.commands;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Command {
	@Nonnull String name();

	String description() default "Pas de description";

	ExecutorType executorType() default ExecutorType.ALL;

	Category category() default Category.NO_CATEGORY;

	boolean showInHelp() default true;

	boolean usableInAllChannels() default false;

	enum ExecutorType {
		ALL, USER, ADMINS
	}

	enum Category {
		NIVEAUX("Niveaux"),
		UTILITAIRES("Utilitaires"),
		SANCTIONS("Sanctions"),
		NO_CATEGORY("Autres");

		private final String fancyName;

		Category(String fancyName) {
			this.fancyName = fancyName;
		}

		public String getFancyName() {
			return fancyName;
		}
	}
}
