package seham.phd.synthesis.modifiers;

import java.util.regex.Pattern;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.ModifierVisitor;

public class IntegerLiteralModifier extends ModifierVisitor<Void> {
	
	private static final Pattern LOOK_AHEAD_THREE = Pattern.compile("(\\d)(?=(\\d{3})+$)");
	
	@Override
	public FieldDeclaration visit(FieldDeclaration fd, Void arg) {
		
		super.visit(fd, arg);
		
		// modify, with the help of the helper method
		fd.getVariables().forEach(v -> v.getInitializer()
				.ifPresent(i -> i.ifIntegerLiteralExpr(il -> v.setInitializer(formatWithUnderscores(il.getValue())))));
		
		return fd;
		
	}
	
	static String formatWithUnderscores (String value ){
		String withoutUnderscores = value.replaceAll("_", "");
		return LOOK_AHEAD_THREE.matcher(withoutUnderscores).replaceAll("$1_");
	}
	

}
