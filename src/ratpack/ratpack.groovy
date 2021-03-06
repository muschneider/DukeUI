
import ratpack.form.Form
import ratpack.groovy.templating.TemplatingModule

import com.github.dukeui.DukeWrapper

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

import ratpack.jackson.JacksonModule
import static ratpack.jackson.Jackson.json

ratpack {
	
	def CONFIG_DIR = System.getProperty('dukeui.config.dir')
 	def files = []
    def dw = new DukeWrapper()


	bindings {
		new File(CONFIG_DIR).listFiles().grep(~/.*xml$/).each { file->
			files << file.getName()
		}
			
		add new JacksonModule()
        add(TemplatingModule) { TemplatingModule.Config config -> config.staticallyCompile = true }
    }


    handlers {
		
		get("") {
			render groovyTemplate("index.html", title: "Data Matching")
		}

		get("rest/configs") {
			def result = [:]
			result.put("files", files)
			render json(result)
		}
		
		get("rest/datasource/:config") {
			def config = pathTokens["config"]
			
			def result = dw.exec(CONFIG_DIR+"/"+config)
			render json(result)
		}

		assets "public"
    }
}
