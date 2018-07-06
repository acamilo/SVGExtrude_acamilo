import eu.mihosoft.vrl.v3d.svg.*;
import eu.mihosoft.vrl.v3d.Extrude;

File f = ScriptingEngine
	.fileFromGit(
		"https://gist.github.com/1606010b686494cea33527b71c98570a.git",//git repo URL
		"master",//branch
		"airFoil.svg"// File from within the Git repo
	)
println "Extruding SVG "+f.getAbsolutePath()
SVGLoad s = new SVGLoad(f.toURI())
def foil = s.extrude(10,0.01)

return foil.collect{it.scale(10)}