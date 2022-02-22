package seham.phd.synthesis.modifiers;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.ModifierVisitor;

public class MethodModifier extends ModifierVisitor<Void> {
	
	@Override
	public MethodDeclaration visit(MethodDeclaration md, Void arg) {
		
		super.visit(md, arg);
		
		// modify this method's body 
		
		return md;
		
	}

}
