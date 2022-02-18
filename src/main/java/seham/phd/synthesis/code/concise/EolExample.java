package seham.phd.synthesis.code.concise;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

import seham.phd.synthesis.annotations.*;

public class EolExample {

	@Documentation
	public void docEol() throws Exception {
		EolModule module = parse("foo.eol");
		execute(module);
	}

	@Documentation
	public void docEolModuleWithEmfModel() throws Exception {
		EolModule module = parse("bar.eol");
		EmfModel model = new EmfModel();
		model.setMetamodelUri(/** param The URI of the metamodel */EcorePackage.eINSTANCE.eNS_URI);
		model.setModelFile(/** param The path of the model file */ "foo.ecore");
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(false);
		model.load();
		module.getContext().getModelRepository().addModel(model);
		execute(module);
	}

	@Utility
	public EolModule parse(String file) throws Exception {
		EolModule module = new EolModule();
		module.parse(/** param the path of the EOL file */file);
		if (!module.getParseProblems().isEmpty())
			throw new RuntimeException();
		return module;
	}

	@Utility
	public void execute(EolModule module) throws EolRuntimeException {
		module.execute();
		module.getContext().getModelRepository().dispose();
	}

}