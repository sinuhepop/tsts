import groovy.transform.CompileStatic

@CompileStatic
//@TypeChecked
class T extends GroovyObjectSupport {

	def x() {
		this.newProp='';
	}

	def y() {
		this.x();
	}
}