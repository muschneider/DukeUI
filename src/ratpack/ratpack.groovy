

import ratpack.form.Form
import ratpack.groovy.templating.TemplatingModule

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {

	bindings {
        add(TemplatingModule) { TemplatingModule.Config config -> config.staticallyCompile = true }
    }

    handlers {
		get("run") {
			render groovyTemplate("run.html", title: "Data Matching")
		}
    }
}
