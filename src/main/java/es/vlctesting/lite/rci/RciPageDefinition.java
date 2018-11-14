package es.vlctesting.lite.rci;

import org.sonar.api.web.page.Context;
import org.sonar.api.web.page.Page;
import org.sonar.api.web.page.Page.Scope;
import org.sonar.api.web.page.PageDefinition;

public class RciPageDefinition implements PageDefinition {

	@Override
	public void define(Context context) {
		context.addPage(
				Page.builder("rci/rci_page").setName("Rules Compliance Index").setScope(Scope.COMPONENT).build());

	}

}
