package seham.phd.synthesis.modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.javaparser.Position;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;	
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.ModifierVisitor;

import seham.phd.synthesis.visitors.MethodDeclarationVisitor;


public class MethodDeclarationModifier extends ModifierVisitor<Void> {
	
	public MethodDeclarationModifier() {
		
	}

	@Override
	public MethodDeclaration visit(MethodDeclaration md, Void arg) {
		
		super.visit(md, arg);
		return md;

	}
	
	public BlockStmt removeNode (BlockStmt bs, int index) {
		
		super.visit(bs, null);
		
		// What if I want to remove a node of a certain type ?
		bs.getStatements().get(index).remove();
		return bs;
		
	}
	
	public MethodDeclaration modifyAllDocMethods(CompilationUnit cu, MethodDeclarationVisitor visitor) {
		
		// This is what should happen, change all Doc methods in the CU of this visitor
//		visitor.getDocumentationMethods().stream().forEach(method -> modifyMethod(method, visitor));
		
		// here I am just experimenting with one method doc
		MethodDeclaration md = visitor.getDocumentationMethods().get(0);
		Map<Integer, MethodDeclaration > utilityCallsMap = visitor.locateUtilityCalls(md);
		
		modifyMethod (md,utilityCallsMap);
		
		cu.recalculatePositions();
		return md;
		
	}
	
	private MethodDeclaration modifyMethod(MethodDeclaration md, Map<Integer, MethodDeclaration> utilityCallsMap) {

		BlockStmt newBlockStmt = new BlockStmt();
		NodeList<Statement> originalBlockStmt = md.getBody().get().getStatements();

		for (int i = 0; i < originalBlockStmt.size(); i++) {
			if (utilityCallsMap.containsKey(originalBlockStmt.get(i).getRange().get().begin.line)) {

				BlockStmt utilityMethodBody = cloneBody(utilityCallsMap.get(originalBlockStmt.get(i).getRange().get().begin.line));
				NodeList<Statement> newStatements = utilityMethodBody.getStatements();
				newBlockStmt.getStatements().addAll(newStatements);

			} else {
				newBlockStmt.getStatements().add(originalBlockStmt.get(i));
			}
		}

		md.replace(md.getBody().get(), newBlockStmt);

		return md;

	}
	
	public int getInsertionPosition (int lineNumber) {
		
		int position = 0;
		
		// specify insertion position, using line no?
		
		return position;
	}
	
	
	public BlockStmt cloneBody (MethodDeclaration utilityMethod) {
		
		CloneVisitor visitor = new CloneVisitor();
		
		// The branch of our tree that represents the BlockStmt of a utility method
		BlockStmt body = utilityMethod.getBody().get();
		BlockStmt clonedBody = (BlockStmt) visitor.visit(body, null);
		
		return clonedBody;
	}
}